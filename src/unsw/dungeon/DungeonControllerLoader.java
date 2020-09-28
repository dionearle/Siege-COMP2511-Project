package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;

    //Images
    private Image playerImage;
    private Image wallImage;
    private Image treasureImage;
    private Image exitImage;
    private Image doorImage;
    private Image keyImage;
    private Image boulderImage;
    private Image switchImage;
    private Image invincibilityImage;
    private Image bombImage;
    private Image enemyImage;
    private Image swordImage;
    private Image acidImage;
    private Image ladderImage;

    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        playerImage = new Image("/player_nosword.png");
        wallImage = new Image("/wall.jpg");
        treasureImage = new Image("_coin_gold.png");
        exitImage = new Image("exit.png");
        doorImage = new Image("closed_door.png");
        keyImage = new Image("_key.png");
        boulderImage = new Image("boulder.png");
        switchImage = new Image("pressure_plate.png");
        invincibilityImage = new Image("_potion_green.png");
        bombImage = new Image("bomb_unlit.png");
        enemyImage = new Image("_orc_red.png");
        swordImage = new Image("_sword_plat.png");
        acidImage = new Image("acid.png");
        ladderImage = new Image("ladder.png");
    }

    @Override
    public ImageView onLoad(Entity player) {
        ImageView view = new ImageView(playerImage);
        addEntity(player, view);
        return view;
    }

    @Override
    public ImageView onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        addEntity(wall, view);
        return view;
    }
    
    @Override
    public ImageView onLoad(Treasure treasure) {
        ImageView view = new ImageView(treasureImage);
        addEntity(treasure, view);
        return view;
    }
    
    @Override
    public ImageView onLoad(Exit exit) {
        ImageView view = new ImageView(exitImage);
        addEntity(exit, view);
        return view;
    }
    
    @Override
    public ImageView onLoad(Door door) {
        ImageView view = new ImageView(doorImage);
        addEntity(door, view);
        return view;
    }
    
    @Override
    public ImageView onLoad(Key key) {
        ImageView view = new ImageView(keyImage);
        addEntity(key, view);
        return view;
    }
    
    @Override
    public ImageView onLoad(Boulder boulder) {
        ImageView view = new ImageView(boulderImage);
        addEntity(boulder, view);
        return view;
    }
    
    @Override
    public ImageView onLoad(Switch switch_in) {
        ImageView view = new ImageView(switchImage);
        addEntity(switch_in, view);
        return view;
    }
    
    @Override
    public ImageView onLoad(Invincibility invincibility) {
        ImageView view = new ImageView(invincibilityImage);
        addEntity(invincibility, view);
        return view;
    }
    
    @Override
    public ImageView onLoad(Bomb bomb) {
        ImageView view = new ImageView(bombImage);
        addEntity(bomb, view);       
        return view;
    }

    
    @Override
    public ImageView onLoad(Enemy enemy) {
        ImageView view = new ImageView(enemyImage);
        addEntity(enemy, view);
        return view;
    }

    
    @Override
    public ImageView onLoad(Sword sword) {
        ImageView view = new ImageView(swordImage);
        addEntity(sword, view);
        return view;
    }
    
    @Override
	public ImageView onLoad(Acid acid) {
    	ImageView view = new ImageView(acidImage);
        addEntity(acid, view);
		return view;
	}
    
    @Override
    public ImageView onLoad(Ladder ladder) {
    	ImageView view = new ImageView(ladderImage);
        addEntity(ladder, view);
		return view;
	}
    
    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entities.add(view);
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
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

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities);
    }
}