package edu.nyu.cs;

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
  private PImage imgMe; // will hold a photo of me
  private ArrayList<Duck> ducks; // will hold an ArrayList of Star objects
  private final int NUM_DUCKS = 4; // the number of stars to create
  private Fish fish;

	/**
	 * This method will be automatically called by Processing when the program runs.
   * - Use it to set up the initial state of any instance properties you may use in the draw method.
	 */
	public void setup() {
    // set the cursor to crosshairs
    this.cursor(PApplet.CROSS);

    // load up a sound file and play it once when program starts up
		String cwd = Paths.get("").toAbsolutePath().toString(); // the current working directory as an absolute path
		String path = Paths.get(cwd, "sounds", "vibraphon.mp3").toString(); // e.g "sounds/vibraphon.mp3" on Mac/Unix vs. "sounds\vibraphon.mp3" on Windows
    this.soundStartup = new SoundFile(this, path);
    this.soundStartup.play();

    // load up a sound file and play it once when the user clicks
		path = Paths.get(cwd, "sounds", "thump.aiff").toString(); // e.g "sounds/thump.aiff" on Mac/Unix vs. "sounds\thump.aiff" on Windows
    this.soundClick = new SoundFile(this, path); // if you're on Windows, you may have to change this to "sounds\\thump.aiff"
 
    // load up an image of me
		path = Paths.get(cwd, "images", "me.png").toString(); // e.g "images/me.png" on Mac/Unix vs. "images\me.png" on Windows
    this.imgMe = loadImage(path);

    // some basic settings for when we draw shapes
    this.ellipseMode(PApplet.CENTER); // setting so ellipses radiate away from the x and y coordinates we specify.
    this.imageMode(PApplet.CENTER); // setting so the ellipse radiates away from the x and y coordinates we specify.

    // initialize fish
    fish = new Fish(app, 200, 390);
    
	}

	/**
	 * This method is called automatically by Processing every 1/60th of a second by default.
   * - Use it to modify what is drawn to the screen.
   * - There are methods for drawing various shapes, including `ellipse()`, `circle()`, `rect()`, `square()`, `triangle()`, `line()`, `point()`, etc.
	 */
	public void draw() {
    // fill the window with solid color
    this.background(0, 0, 0); // fill the background with the specified r, g, b color.

    // show an image of me that wanders around the window
    image(this.imgMe, this.width / 2, this.height/2); // draw image to center of window

    // draw an ellipse at the current position of the mouse
    this.fill(255, 255, 255); // set the r, g, b color to use for filling in any shapes we draw later.
    this.ellipse(this.mouseX, this.mouseY, 60, 60); // draw an ellipse wherever the mouse is

    // draw all ducks to their current position
    for (int i=0; i < this.ducks.size(); i++) {
      Duck duck = this.ducks.get(i); // get the current Star object from the ArrayList
      duck.draw(); // draw the star to the screen
      duck.swim();
    }

    // show the score at the bottom of the window
    String scoreString = String.format("SCORE: %d", this.score);
    text(scoreString, this.width/2, this.height-50);

	}

  public void startScreen() {
    // start screen
      if (game == false) {
        fill(255);
        noStroke();
        textAlign(CENTER, CENTER);
        textSize(40);
        textStyle(BOLD);
        text("CROSSY POND", width/2, height/2 - 30);
        fill(255);
        textSize(20);
        textStyle(NORMAL);
        text("Use the arrow keys to cross the pond.", width/2, height/2 +10);
        text("Don't get eaten by a duck!", width/2, height/2 + 40);
        textStyle(ITALIC);
        text("Click the screen to start", width/2, height/2 + 70);
        
      // start button
      if (mouseIsPressed == true) {
        background(171,221,255);
        game = true;
      } 
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
    switch (key):
    case (this.keyCode)

    if (this.keyCode == LEFT_ARROW) {
      fish.move(-1, 0);
    } 
    else if (keyIsDown(RIGHT_ARROW)) {
        fish.move(1, 0);
    } 
    else if (keyIsDown(DOWN_ARROW)) {
        fish.move(0, 1);
    } 
    else if (keyIsDown(UP_ARROW)) {
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

	}

	/**
	 * This method is automatically called by Processing every time the user presses down and drags the mouse.
	 * The `mouseX` and `mouseY` variables are automatically is assigned the coordinates on the screen when the mouse was clicked.
   */
	public void mouseDragged() {
		System.out.println(String.format("Mouse dragging at: %d:%d.", mouseX, mouseY));
	}

  /**
   * A method that can be used to modify settings of the window, such as set its size.
   * This method shouldn't really be used for anything else.  
   * Use the setup() method for most other tasks to perform when the program first runs.
   */
  public void settings() {
		size(400, 400); // set the map window size, using the OpenGL 2D rendering engine
		System.out.println(String.format("Set up the window size: %d, %d.", width, height));    
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
