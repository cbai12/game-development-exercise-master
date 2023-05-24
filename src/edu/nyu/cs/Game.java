package edu.nyu.cs;

import java.util.ArrayList;

import org.apache.commons.lang3.SystemUtils;

import processing.core.*; // import the base Processing library

/**
 * 
 * Crossy Pond
 * @author Claire Bai and Aimee Yu
 * @version 0.1
 */
public class Game extends PApplet {

  private ArrayList<Duck> ducks = new ArrayList<Duck>(); // will hold an ArrayList of Duck objects
  private Fish fish;
  private ArrayList<Lilypad> lilys = new ArrayList<Lilypad>();

  // window size of this app
  private static final int WIDTH = 400;
  private static final int HEIGHT = 400;

  private boolean start;
  private boolean hit = false;
  private boolean game = true;
  private boolean eaten = false;

  /**
   * A method that can be used to modify settings of the window, such as set its
   * size.
   * This method shouldn't really be used for anything else.
   * Use the setup() method for most other tasks to perform when the program first
   * runs.
   */
  public void settings() {
    size(Game.WIDTH, Game.HEIGHT); // set the map window size, using the OpenGL 2D rendering engine
    System.out.println(String.format("Set up the window size: %d, %d.", width, height));
  }

  /**
   * This method will be automatically called by Processing when the program runs.
   * - Use it to set up the initial state of any instance properties you may use
   * in the draw method.
   * Called once on load. Used to create the window and "global" settings.
   */
  public void setup() {
    // set the cursor to crosshairs
    this.cursor(PApplet.CROSS);

    this.ellipseMode(PApplet.CENTER); // setting so ellipses radiate away from the x and y coordinates we specify.
    this.imageMode(PApplet.CENTER); // setting so the ellipse radiates away from the x and y coordinates we specify.

    // initialize fish
    fish = new Fish(this, 200, 390);

    // initialize ducks with random speeds
    float s = (float) Math.random() * 2 + 1;
    for (int i = 0; i < 3; i++) {
      Duck duck = new Duck(this, 0, (i * 100) + 45, s);
      ducks.add(duck);
      s = (float) Math.random() * 2 + 1;
    }
    // overloaded constructor ducks
    Duck slowDuck = new Duck(this, 200, 200);
    ducks.add(slowDuck);

    // overloaded lilypad
    //Lilypad sleepLilypad = new Lilypad(this);
    //lilys.add(sleepLilypad);
    // initialize lilypads w different locations
    for (int i = 0; i < 4; i++) {
      lilys.add(new Lilypad(this, (i + 60) * 20, (i * 100) + 45));
    }

    start = false;

  }

  /**
   * This method is called automatically by Processing every 1/60th of a second by
   * default.
   * - Use it to modify what is drawn to the screen.
   * - There are methods for drawing various shapes, including `ellipse()`,
   * `circle()`, `rect()`, `square()`, `triangle()`, `line()`, `point()`, etc.
   */
  public void draw() {
    if (game == true) {
      // fill the window with solid color
      this.background(171, 221, 255);
      // display the start screen
      this.startScreen();
      // mouse clicked
      // play game
      if (start == true) {
        // end zone
        this.noStroke();
        this.fill(144, 238, 144);
        this.rect(0, 0, 400, 30);

        // fish
        fish.draw();

        // lilypads
        for (int j = 0; j < this.lilys.size(); j++) {
          Lilypad lily = this.lilys.get(j);
          lily.draw();
          lily.move();
        }

        // for loop to make all the ducks swim
        for (int i = 0; i < this.ducks.size(); i++) {
          Duck duck = this.ducks.get(i); // get the current Duck object from the ArrayList
          duck.draw();
          duck.swim();
        }
      }
      // call endGame
      this.endGame();
      if (game == false) {
        this.finalMessage();
      }
    }

  }

  public void startScreen() {
    // start screen
    if (start == false) {
      this.fill(255);
      this.noStroke();
      this.textAlign(CENTER, CENTER);
      this.textSize(40);
      this.text("CROSSY POND", width / 2, height / 2 - 30);
      this.fill(255);
      this.textSize(20);
      this.text("Use the arrow keys to cross the pond.", width / 2, height / 2 + 10);
      this.text("Don't get eaten by a duck!", width / 2, height / 2 + 40);
      this.text("Click the screen to start", width / 2, height / 2 + 70);
    }
  }

  /**
   * This method is automatically called by Processing every time the user presses
   * a key while viewing the map.
   * - The `key` variable (type char) is automatically is assigned the value of
   * the key that was pressed.
   * - The `keyCode` variable (type int) is automatically is assigned the numeric
   * ASCII/Unicode code of the key that was pressed.
   */
  public void keyPressed() {
    // the `key` variable holds the char of the key that was pressed, the `keyCode`
    // variable holds the ASCII/Unicode numeric code for that key.
    System.out.println(String.format("Key pressed: %s, key code: %d.", this.key, this.keyCode));
    // key movement
    if (this.keyCode == LEFT) {
      fish.move(-1, 0);
    } else if (this.keyCode == RIGHT) {
      fish.move(1, 0);
    } else if (this.keyCode == DOWN) {
      fish.move(0, 1);
    } else if (this.keyCode == UP) {
      fish.move(0, -1);
    }

  }

  /**
   * This method is automatically called by Processing every time the user clicks
   * a mouse button.
   * - The `mouseX` and `mouseY` variables are automatically is assigned the
   * coordinates on the screen when the mouse was clicked.
   * - The `mouseButton` variable is automatically assigned the value of either
   * the PApplet.LEFT or PApplet.RIGHT constants, depending upon which button was
   * pressed.
   */
  public void mouseClicked() {
    System.out.println(String.format("Mouse clicked at: %d:%d.", this.mouseX, this.mouseY));
    if (game == true) {
      this.background(171, 221, 255);
      this.start = true;
    }
  }

  public void endGame() {
    this.eaten = false;
    this.game = true;
    this.hit = false;

    // check if fish overlaps with duck
    for (int i = 0; i < this.ducks.size(); i++) {
      Duck duck = this.ducks.get(i); // get the current Duck object from the ArrayList
      // duck boundaries
      int duckTop = duck.duckY() + 24;
      int duckBottom = duck.duckY() - 24;
      float duckLeft = duck.duckX() - 30;
      float duckRight = duck.duckX() + 30;

      if (fish.fishY() <= duckTop && fish.fishY() >= duckBottom && fish.fishX() <= duckRight
          && fish.fishX() >= duckLeft) {
        this.game = false;
        this.eaten = true;
        this.finalMessage();
        return;
      }
    }

    // check if fish overlaps with lilypad
    for (int j = 0; j < this.lilys.size(); j++) {
      Lilypad lily = this.lilys.get(j); // get the current object from the ArrayList
      // lilupad boundaries
      int lilyTop = lily.lilyY() + 12;
      int lilyBottom = lily.lilyY() - 12;
      float lilyLeft = lily.lilyX() - 20;
      float lilyRight = lily.lilyX() + 20;

      if (fish.fishY() <= lilyTop && fish.fishY() >= lilyBottom && fish.fishX() <= lilyRight
          && fish.fishX() >= lilyLeft) {
        this.game = false;
        this.hit = true;
        this.finalMessage();
        return;
      }
    }

    // check if fish successfully reaches end zone
    if ((fish.fishY() + 10) < 30) {
      this.game = false;
      this.eaten = false;
      this.hit = false;
      this.finalMessage();
      return;
    }

  }

  public void finalMessage() {
    // win game
    if (start && eaten == false && hit == false && game == false) {
      this.background(144, 238, 144);
      this.fill(255);
      this.noStroke();
      this.textAlign(CENTER, CENTER);
      this.textSize(20);
      this.text("You safely made it to the other side!", width / 2, height / 2);
    }
    // lose game bc eaten
    else if (start == true && eaten == true && game == false) {
      this.background(200, 90, 10); // red
      this.fill(255);
      this.noStroke();
      this.textAlign(CENTER, CENTER);
      this.textSize(20);
      this.text("You were eaten by a duck :/", width / 2, height / 2);
    }

    else if (start == true && hit == true && game == false) {
      this.background(200, 90, 10); // red
      this.fill(255);
      this.noStroke();
      this.textAlign(CENTER, CENTER);
      this.textSize(20);
      this.text("You were hit by a lilypad :/", width / 2, height / 2);
    }
  }

  /**
   * This method is automatically called by Processing every time the user presses
   * down and drags the mouse.
   * The `mouseX` and `mouseY` variables are automatically is assigned the
   * coordinates on the screen when the mouse was clicked.
   */
  public void mouseDragged() {
    System.out.println(String.format("Mouse dragging at: %d:%d.", mouseX, mouseY));
  }

  /**
   * The main function is automatically called first in a Java program.
   * When using the Processing library, this method must call PApplet's main
   * method and pass it the full class name, including package.
   * You shouldn't need to modify this method.
   * 
   * @param args An array of any command-line arguments.
   */
  public static void main(String[] args) {
    // make sure we're using Java 1.8
    System.out.printf("\n###  JDK IN USE ###\n- Version: %s\n- Location: %s\n### ^JDK IN USE ###\n\n",
        SystemUtils.JAVA_VERSION, SystemUtils.getJavaHome());
    boolean isGoodJDK = SystemUtils.IS_JAVA_1_8;
    if (!isGoodJDK) {
      System.out.printf("Fatal Error: YOU MUST USE JAVA 1.8, not %s!!!\n", SystemUtils.JAVA_VERSION);
    } else {
      PApplet.main("edu.nyu.cs.Game"); // do not modify this!
    }
  }

}
