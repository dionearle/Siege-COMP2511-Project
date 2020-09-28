package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javafx.scene.image.ImageView;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return 		when successful.
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");
        
        JSONObject jsonGoals = json.getJSONObject("goal-condition");
        
        Goal goals = loadGoal(jsonGoals);
        
        Dungeon dungeon = new Dungeon(width, height, goals);

        JSONArray jsonEntities = json.getJSONArray("entities");
        
        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        
        System.out.println("Goals are: " + dungeon.nameGoal());
        
        return dungeon;
    }

    private Goal loadGoal(JSONObject json) {
		
    	// extracts the name of the goal
    	String name = json.getString("goal");
    	
    	// is composite node
    	if (json.has("subgoals")) {
    		
    		CompositeGoal compositeGoal = new CompositeGoal(name);
    		if (json.getString("goal").equals("AND")) {
    			compositeGoal.setLogicalOperator(new AndOperator(compositeGoal));
    		} else {
    			compositeGoal.setLogicalOperator(new OrOperator(compositeGoal));
    		}
    		
        	JSONArray jsonSubgoals = json.getJSONArray("subgoals");
        	
        	for (int j = 0; j < jsonSubgoals.length(); j++) {
        		
        		// recursively calls this method on subgoals
        		Goal subgoal = loadGoal(jsonSubgoals.getJSONObject(j));
        		compositeGoal.add(subgoal);
            }
        	return compositeGoal;
        	
        } else {
        	// is leaf node
        	LeafGoal leafGoal = new LeafGoal(name);
        	return leafGoal;
        }
	}

	private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
        case "player":
        	Inventory inv = new Inventory();
            Player player = new Player(dungeon, x, y, inv);
            ImageView playerImage = onLoad(player);
            player.setImage(playerImage);
            dungeon.setPlayer(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            ImageView wallImage = onLoad(wall);
            wall.setImage(wallImage);
            entity = wall;
            break;
        case "treasure":
        	Goal treasureGoal = dungeon.getGoalFromName("treasure");
            Treasure treasure = new Treasure(dungeon, treasureGoal, x, y);
            ImageView treasureImage = onLoad(treasure);
            treasure.setImage(treasureImage);
            treasure.registerGoalObserver(dungeon);
            entity = treasure;
            break;
        case "exit":
        	Goal exitGoal = dungeon.getGoalFromName("exit");
            Exit exit = new Exit(dungeon, exitGoal, x, y);
            ImageView exitImage = onLoad(exit);
            exit.setImage(exitImage);
            exit.registerGoalObserver(dungeon);
            entity = exit;
            break;
        case "door":
        	int doorID = json.getInt("id");
            Door door = new Door(doorID, x, y);
            ImageView doorImage = onLoad(door);
            door.setImage(doorImage);
            entity = door;
            break;
        case "key":
        	int keyID = json.getInt("id");
            Key key = new Key(keyID, x, y);
            ImageView keyImage = onLoad(key);
            key.setImage(keyImage);
            entity = key;
            break;
        case "boulder":
            Boulder boulder = new Boulder(dungeon, x, y);
            ImageView boulderImage = onLoad(boulder);
            boulder.setImage(boulderImage);
            entity = boulder;
            break;
        case "switch":
        	Goal switchGoal = dungeon.getGoalFromName("boulders");
            Switch switch_in = new Switch(dungeon, switchGoal, x, y);
            switch_in.registerGoalObserver(dungeon);
            ImageView switchImage = onLoad(switch_in);
            switch_in.setImage(switchImage);
            entity = switch_in;
            break;
        case "invincibility":
            Invincibility invincibility = new Invincibility(x, y);
            ImageView invinciblityImage = onLoad(invincibility);
            invincibility.setImage(invinciblityImage);
            entity = invincibility;
            break;
        case "bomb":
            Bomb bomb = new Bomb(x, y);
            ImageView bombImage = onLoad(bomb);
            bomb.setImage(bombImage);
            entity = bomb;
            break;
        case "enemy":
        	Goal enemyGoal = dungeon.getGoalFromName("enemies");
            Enemy enemy = new Enemy(dungeon, enemyGoal, x, y);
            enemy.registerGoalObserver(dungeon);
            ImageView enemyImage = onLoad(enemy);
            enemy.setImage(enemyImage);
            entity = enemy;
            break;
        case "sword":
            Sword sword = new Sword(dungeon, x, y);
            ImageView swordImage = onLoad(sword);
            sword.setImage(swordImage);
            entity = sword;
            break;
        case "acid":
        	Acid acid = new Acid(dungeon, x, y);
        	ImageView acidImage = onLoad(acid);
        	acid.setImage(acidImage);
        	entity = acid;
        	break;
        case "ladder":
        	Ladder ladder = new Ladder(x, y);
        	ImageView ladderImage = onLoad(ladder);
        	ladder.setImage(ladderImage);
        	entity = ladder;
        	break;
        }
        dungeon.addEntity(entity);
    }

    public abstract ImageView onLoad(Entity player);

    public abstract ImageView onLoad(Wall wall);

	public abstract ImageView onLoad(Treasure treasure);

	public abstract ImageView onLoad(Exit exit);

	public abstract ImageView onLoad(Door door);
	
	public abstract ImageView onLoad(Key key);
	
	public abstract ImageView onLoad(Boulder boulder);
	
	public abstract ImageView onLoad(Switch switch_in);
	
	public abstract ImageView onLoad(Invincibility potion);
	
	public abstract ImageView onLoad(Bomb bomb);
	
	public abstract ImageView onLoad(Enemy enemy);
	
	public abstract ImageView onLoad(Sword sword);
	
	public abstract ImageView onLoad(Acid acid);
	
	public abstract ImageView onLoad(Ladder ladder);

}
