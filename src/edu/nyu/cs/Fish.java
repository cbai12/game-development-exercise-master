package edu.nyu.cs;

/**
 * this is a fish
 */
public class Fish {
    // instance properties
    private Game app; // will hold a reference to the main Game object
    private int x; // will hold the x coordinate of this object on the screen
    private int y; // will hold the y coordinate of this object on the screen

    /**
     * Constructor to create a Fish object at a specific position on the screen
     * 
     * @param app a reference to the Game object that created this object
     * @param x   the x coordinate of this object on the screen
     * @param y   the y coordinate of this object on the screen
     */

    public Fish(Game app, int x, int y) {

        this.app = app; // store a reference to the main game object
        // store the x and y coordinates of this object on the screen
        this.x = x;
        this.y = y;

    }

    /**
     * Draw this to the screen at the appropriate coordinates
     */
    public void draw() {
        this.app.noStroke();
        this.app.fill(200, 90, 10);
        this.app.ellipse(this.x, this.y, 20, 20);
    }

    /**
     * Updates the fish's location
     */

    public void move(int xdir, int ydir) {
        this.x = this.x + (xdir * 5);
        this.y = this.y + (ydir * 10);
        // prevent from going beyond border
        if (this.y > 390) {
            this.y = 390;
        } else if (this.x < 10) {
            this.x = 10;
        } else if (this.x > 400) {
            this.x = 390;
        }
    }

    /**
     * getter for fish x
     * 
     * @return this.x
     */

    public int fishX() {
        return this.x;
    }

    /**
     * getter for fish y
     * 
     * @return this.y
     */
    public int fishY() {
        return this.y;
    }

}
