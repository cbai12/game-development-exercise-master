package edu.nyu.cs;

import processing.core.PApplet;

/**
 * This represents the duck
 */
public class Duck {
    // instance properties
    private Game app; // will hold a reference to the main Game object
    private float x; // will hold the x coordinate of this object on the screen
    private int y; // will hold the y coordinate of this object on the screen
    // private ArrayList<Integer> history;
    private float speed;

    /**
     * Constructor to create a Star object at a specific position on the screen
     * 
     * @param app   a reference to the Game object that created this object
     * @param x     the x coordinate of this object on the screen
     * @param y     the y coordinate of this object on the screen
     * @param speed the speed of this object on the screen
     */

    // constructor used when all dimensions
    public Duck(Game app, int x, int y, float speed) {
        this.app = app; // store a reference to the main game object

        // store the x and y coordinates of this object on the screen
        this.x = x;
        this.y = y;
        this.speed = speed;

    }

    /**
     * Constructor to create a Duck object at a specific position on the screen
     * 
     * @param app a reference to the Game object that created this object
     */
    // constructor used when no dimensions specified
    public Duck(Game app) {
        this(app, 200, 300, 0);
    }

    /**
     * Constructor to create a Duck object at a specific position on the screen
     * 
     * @param app a reference to the Game object that created this object
     * @param x   the x coordinate of this object on the screen
     * @param y   the y coordinate of this object on the screen
     */
    // constructor used when only x and y specified
    public Duck(Game app, int x, int y) {
        this(app, x, y, 1);
    }

    /**
     * Draw this duck's image to the screen at the appropriate coordinates
     */
    public void draw() {
        // draw this object's image at its x and y coordinates
        // this.app.imageMode(PApplet.CENTER); // setting so the image is drawn centered
        // on the specified x and y coordinates
        // this.app.image(this.img, this.x, this.y);

        // Draw the duck image at x and y coordinates
        // duck body
        this.app.noStroke();
        this.app.fill(255, 211, 66);
        this.app.ellipse(this.x, this.y, 55, 48);
        this.app.ellipse(this.x, (this.y - 24), 25, 15);
        this.app.ellipse(this.x, (this.y + 24), 25, 15);

        // beak
        this.app.noStroke();
        this.app.fill(230, 125, 21);
        this.app.ellipse((this.x + 18), this.y, 15, 12);

        // duck head
        this.app.fill(255, 220, 84);
        this.app.ellipse((this.x + 5), this.y, 30, 27);

    }

    /**
     * Have the duck swim across the screen
     */
    public void swim() {
        // update x with speed to swim across screen
        float currentX = (float) this.x;
        this.x = currentX + this.speed;

        if ((this.x - 27.5) > 400) {
            this.x = this.speed * -1;
            // this.history = [];
        }

    }

    /**
     * getter for duck x
     * 
     * @return this.x
     */
    public float duckX() {
        return this.x;
    }

    /**
     * getter for duck y
     * 
     * @return this.y
     */
    public int duckY() {
        return this.y;
    }

}
