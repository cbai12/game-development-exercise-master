package edu.nyu.cs;

import static org.mockito.ArgumentMatchers.floatThat;

import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.commons.lang3.SystemUtils;

import processing.core.*; // import the base Processing library
import processing.sound.*; // import the processing sound library

/**
 * Describe your game succinctly here, and update the author info below.
 * Some starter code has been included for your reference - feel free to delete or modify it.
 * 
 * Crossy Road Dupe
 * 
 * 
 *  
 * @author Claire Bai and Aimee Yu
 * @version 0.1
 */
public class Game extends PApplet {

  private SoundFile soundStartup; // will refer to a sound file to play when the program first starts
  private SoundFile soundClick; // will refer to a sound file to play when the user clicks the mouse

  private ArrayList<Duck> ducks; // will hold an ArrayList of Duck objects
  private Fish fish;

  //window size of this app
	private static final int WIDTH = 400;
	private static final int HEIGHT = 400;

  private boolean game;
  private boolean eaten;

  /**
   * A method that can be used to modify settings of the window, such as set its size.
   * This method shouldn't really be used for anything else.  
   * Use the setup() method for most other tasks to perform when the program first runs.
   */
  public void settings() {
		size(Game.WIDTH, Game.HEIGHT); // set the map window size, using the OpenGL 2D rendering engine
		System.out.println(String.format("Set up the window size: %d, %d.", width, height));    
  }

	/**
	 * This method will be automatically called by Processing when the program runs.
   * - Use it to set up the initial state of any instance properties you may use in the draw method.
   * Called once on load. Used to create the  window and "global" settings.
	 */
	public void setup() {
    // set the cursor to crosshairs
    this.cursor(PApplet.CROSS);
    /** 
     * // load up a sound file and play it once when program starts up
		String cwd = Paths.get("").toAbsolutePath().toString(); // the current working directory as an absolute path
		String path = Paths.get(cwd, "sounds", "vibraphon.mp3").toString(); // e.g "sounds/vibraphon.mp3" on Mac/Unix vs. "sounds\vibraphon.mp3" on Windows
    this.soundStartup = new SoundFile(this, path);
    this.soundStartup.play();

    // load up a sound file and play it once when the user clicks
		path = Paths.get(cwd, "sounds", "thump.aiff").toString(); // e.g "sounds/thump.aiff" on Mac/Unix vs. "sounds\thump.aiff" on Windows
    this.soundClick = new SoundFile(this, path); // if you're on Windows, you may have to change this to "sounds\\thump.aiff"
  */
    
    // some basic settings for when we draw shapes
    this.ellipseMode(PApplet.CENTER); // setting so ellipses radiate away from the x and y coordinates we specify.
    this.imageMode(PApplet.CENTER); // setting so the ellipse radiates away from the x and y coordinates we specify.

    // initialize fish
    fish = new Fish(200, 390);

    // initialize ducks with random speeds
    float s = (float) Math.random()*3 + 1;
    for (int i = 0; i < 4; i++) {
      ducks.add(new Duck(0,(i*100)+45,s));
      s = (float) Math.random()*3 +1 ;      
    }

    game = false;
    
	}

	/**
	 * This method is called automatically by Processing every 1/60th of a second by default.
   * - Use it to modify what is drawn to the screen.
   * - There are methods for drawing various shapes, including `ellipse()`, `circle()`, `rect()`, `square()`, `triangle()`, `line()`, `point()`, etc.
	 */
	public void draw() {
    // fill the window with solid color
    this.background(171,221,255); 

    startScreen();
    mouseClicked();

    //play game
    if (game == true) {
      //end zone
      noStroke();
      fill(144,238,144);
      rect(0,0,400,30);
      
      //fish
      fish.draw();

      //for loop to make all the ducks swim
      for (int i=0; i < this.ducks.size(); i++) {
        Duck duck = this.ducks.get(i); // get the current Duck object from the ArrayList
        duck.draw(); 
        duck.swim();
      }

      //call endGame
      endGame();
    }
	}

  public void startScreen() {
    //start screen
      if (game == false) {
        fill(255);
        noStroke();
        textAlign(CENTER, CENTER);
        textSize(40);
        text("CROSSY POND", width/2, height/2 - 30);
        fill(255);
        textSize(20);
        text("Use the arrow keys to cross the pond.", width/2, height/2 +10);
        text("Don't get eaten by a duck!", width/2, height/2 + 40);
        text("Click the screen to start", width/2, height/2 + 70);
    }
  }

	/**
	 * This method is automatically called by Processing every time the user presses a key while viewing the map.
	 * - The `key` variable (type char) is automatically is assigned the value of the key that was pressed.
	 * - The `keyCode` variable (type int) is automatically is assigned the numeric ASCII/Unicode code of the key that was pressed.
	 */
	public void keyPressed() {
    // the `key` variable holds the char of the key that was pressed, the `keyCode` variable holds the ASCII/Unicode numeric code for that key.
		System.out.println(String.format("Key pressed: %s, key code: %d.", this.key, this.keyCode));
    // key movement
    if (this.keyCode == LEFT) {
      fish.move(-1, 0);
    } 
    else if (this.keyCode == RIGHT) {
      fish.move(1, 0);
    } 
    else if (this.keyCode == DOWN) {
      fish.move(0, 1);
    } 
    else if (this.keyCode == UP) {
      fish.move(0, -1);
    }

	}  

	/**
	 * This method is automatically called by Processing every time the user clicks a mouse button.
	 * - The `mouseX` and `mouseY` variables are automatically is assigned the coordinates on the screen when the mouse was clicked.
   * - The `mouseButton` variable is automatically assigned the value of either the PApplet.LEFT or PApplet.RIGHT constants, depending upon which button was pressed.
   */
	public void mouseClicked() {
		System.out.println(String.format("Mouse clicked at: %d:%d.", this.mouseX, this.mouseY));
    background(171,221,255);
    game = true;
	}

  public void endGame() {
    // check if fish overlaps with duck
    for (int i = 0; i < this.ducks.size(); i++) {
      Duck duck = this.ducks.get(i); // get the current Duck object from the ArrayList
      // duck boundaries
      int duckTop = duck.duckY()+24;
      int duckBottom = duck.duckY()-24;
      float duckLeft = duck.duckX()-30;
      float duckRight = duck.duckX()+30;

      if (fish.fishY() <= duckTop && fish.fishY() >= duckBottom && fish.fishX() <= duckRight && fish.fishX() >= duckLeft)  {
        game = false;
        eaten = true;
      }

    // check if fish successfully reaches end zone
    if ((fish.fishY() + 10) < 30) {
      game = false;
      eaten = false;
    }

  }

	/**
	 * This method is automatically called by Processing every time the user presses down and drags the mouse.
	 * The `mouseX` and `mouseY` variables are automatically is assigned the coordinates on the screen when the mouse was clicked.
   */
	public void mouseDragged() {
		System.out.println(String.format("Mouse dragging at: %d:%d.", mouseX, mouseY));
	}

  
  /**
   * The main function is automatically called first in a Java program.
   * When using the Processing library, this method must call PApplet's main method and pass it the full class name, including package.
   * You shouldn't need to modify this method.
   * 
   * @param args An array of any command-line arguments.
   */
  public static void main(String[] args) {
    // make sure we're using Java 1.8
		System.out.printf("\n###  JDK IN USE ###\n- Version: %s\n- Location: %s\n### ^JDK IN USE ###\n\n", SystemUtils.JAVA_VERSION, SystemUtils.getJavaHome());
		boolean isGoodJDK = SystemUtils.IS_JAVA_1_8;
		if (!isGoodJDK) {
			System.out.printf("Fatal Error: YOU MUST USE JAVA 1.8, not %s!!!\n", SystemUtils.JAVA_VERSION);
		}
		else {
			PApplet.main("edu.nyu.cs.Game"); // do not modify this!
		}
  }

}
