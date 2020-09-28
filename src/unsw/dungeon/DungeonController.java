package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController implements SpriteObserver {

	@FXML
	private GridPane outerPane;
	
    @FXML
    private GridPane squares;

    private List<ImageView> initialEntities;

    private Player player;
    private Dungeon dungeon;
    
    private DeadScreen deadScreen;
    private WinScreen winScreen;
    
    private int goalX;

    /**
     * Controller for dungeon to setup entities.
     * @param dungeon			Dungeon for entities to be controlled.
     * @param initialEntities   List of imageviews for entity generation.
     */
    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        goalX = 3;
    }
    
    public void setDeadScreen(DeadScreen deadScreen) {
    	this.deadScreen = deadScreen;
    }
    
    public void setWinScreen(WinScreen winScreen) {
    	this.winScreen = winScreen;
    }

    @FXML
    public void initialize() {
        Image ground = new Image("/dirt3.PNG");

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }
        player.playMusic("sounds/bg.wav", 1);
        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);
    
        for (Entity entity : dungeon.getEntities()) {
        	entity.registerSpriteObserver(this);
        }
        
        // setting up the UI for the dungeon goals
        TextField goalUI = new TextField();
        goalUI.setText("GOALS:");
        goalUI.setDisable(true);
        goalUI.setOpacity(1);
    	squares.add(goalUI, 1, dungeon.getHeight() - 1, dungeon.getWidth() - 2, 1);
        setupGoalUI(dungeon.getGoals());
        
        
       // setting up the UI for the player's inventory
       TextField inventoryUI = new TextField();
       inventoryUI.setText("Inventory:");
       inventoryUI.setDisable(true);
       inventoryUI.setOpacity(1);
       inventoryUI.setMaxWidth(75);
       squares.add(inventoryUI, 1, 0, 3, 1);
       
       TextField treasure = new TextField();
       treasure.setText("T");
       treasure.setDisable(true);
       treasure.setOpacity(1);
       treasure.setMaxWidth(25);
   	   squares.add(treasure, 4, 0, 1, 1);
   	   
   	   TextField key = new TextField();
   	   key.setText("K");
   	   key.setDisable(true);
   	   key.setOpacity(1);
   	   key.setMaxWidth(25);
	   squares.add(key, 6, 0, 1, 1);
	   
	   TextField bomb = new TextField();
	   bomb.setText("B");
	   bomb.setDisable(true);
	   bomb.setOpacity(1);
	   bomb.setMaxWidth(25);
   	   squares.add(bomb, 8, 0, 1, 1);
   	   
   	   TextField sword = new TextField();
   	   sword.setText("S");
   	   sword.setDisable(true);
   	   sword.setOpacity(1);
   	   sword.setMaxWidth(25);
	   squares.add(sword, 10, 0, 1, 1);
	   
	   TextField potion = new TextField();
	   potion.setText("P");
	   potion.setDisable(true);
	   potion.setOpacity(1);
	   potion.setMaxWidth(25);
   	   squares.add(potion, 12, 0, 1, 1);
   	   
   	   TextField ladder = new TextField();
   	   ladder.setText("L");
   	   ladder.setDisable(true);
   	   ladder.setOpacity(1);
   	   ladder.setMaxWidth(25);
	   squares.add(ladder, 14, 0, 1, 1);
    }
    
    public void setupGoalUI(Goal goal) {
        if (goal instanceof CompositeGoal) {
        	
    		Text text = new Text();
        	text.setText("[" + goal.getName());
        	squares.add(text, goalX, dungeon.getHeight() - 1);
        	goalX += 1;
        	
        	for(Goal child : ((CompositeGoal) goal).getChildren()) {
        		setupGoalUI(child);
    		}
        	
        	Text text2 = new Text();
        	text2.setText("]");
        	squares.add(text2, goalX, dungeon.getHeight() - 1);
        	goalX += 1;
        } else {
        	
        	Text text3 = new Text();
        	text3.setText(goal.getName() + " ");
        	squares.add(text3, goalX, dungeon.getHeight() - 1, 3, 1);
        	goalX += 2;
        	
        	CheckBox cb = new CheckBox();
        	cb.setDisable(true);
        	cb.setOpacity(1);
        	squares.add(cb, goalX, dungeon.getHeight() - 1);
        	goalX += 1;
        	goal.getStatus().addListener(new ChangeListener<Boolean>() {

	            @Override
	            public void changed(ObservableValue<? extends Boolean> observable,
	                    Boolean oldValue, Boolean newValue) {
	                cb.setSelected(newValue);
	            }
	        }); 
        }
    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
        case UP:
            player.moveUp();
            break;
        case DOWN:
            player.moveDown();
            break;
        case LEFT:
            player.moveLeft();
            break;
        case RIGHT:
            player.moveRight();
            break;
        case B:
        	// TODO: should be better way then creating bomb object at 999,999
        	Bomb tmpBomb = new Bomb(999,999);
        	Bomb bomb = (Bomb) player.getInventory().findEntity(tmpBomb);
        	
        	// if the player has a bomb in their inventory, then we want to use it
        	if (bomb != null) {
            	player.useBomb(bomb);
            	bomb.playAudio("sounds/hiss.wav");
        	}
        	break;
        case S:
        	// TODO: should be better way then creating bomb object at 999,999
        	Sword tmpSword = new Sword(dungeon, 999,999);
        	Sword sword = (Sword) player.getInventory().findEntity(tmpSword);
        	
        	// if the player has a sword in their inventory, then we want to use it
        	if (sword != null) {    		
        		player.useSword(sword);
        		sword.playAudio("sounds/sword.wav");
        	}
        	break;
        case L:
        	// TODO: should be better way then creating bomb object at 999,999
        	Ladder tmpLadder = new Ladder(999,999);
        	Ladder ladder = (Ladder) player.getInventory().findEntity(tmpLadder);
        	
        	// if the player has a sword in their inventory, then we want to use it
        	if (ladder != null) {    		
        		player.useLadder(ladder);
        	}
        	break;
        case ESCAPE:
        	Platform.exit();
            System.exit(0);
        default:
            break;
        }
        
        // if the player isn't alive, then we return to the start screen       
        if (!player.isAlive()) {
    		System.out.println("You died!");
        	deadScreen.start();
    	}
        
        if (dungeon.getGoals().isCompleted()) {
        	
        	System.out.println("testnig testing 123!");
        	System.out.println("You have completed the dungeon!");
        	player.playAudio("sounds/thunder.wav");
        	winScreen.start();
        }
    }
    
    @Override
	public void update(SpriteSubject obj, Image image) {
    	
    	if (!player.isAlive()) {
    		System.out.println("You died!");
    		deadScreen.start();
    	}
    	
    	if (image == null) {
    		squares.getChildren().remove(((Entity) obj).getImage());
    		return;
    	}
    	
    	ImageView view = new ImageView(image);
		squares.getChildren().add(view);
		
		trackPosition((Entity) obj, view);
		
		squares.getChildren().remove(((Entity) obj).getImage());
    	((Entity) obj).setImage(view);
	}
    
    private void trackPosition(Entity entity, Node node) {
    	
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        
        ChangeListener<Number> xListener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
            	GridPane.setColumnIndex(node, newValue.intValue());
            }
        };
        
        ChangeListener<Number> yListener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
            	GridPane.setRowIndex(node, newValue.intValue());
            }
        };
        
        entity.x().addListener(xListener);
        entity.y().addListener(yListener);
    }

}

