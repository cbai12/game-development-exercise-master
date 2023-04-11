package edu.nyu.cs;

/**
 * this is for lilypad
 */
public class Lilypad {
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

    public Lilypad(Game app, int x, int y) {

        this.app = app; // store a reference to the main game object
        // store the x and y coordinates of this object on the screen
        this.x = x;
        this.y = y;

    }

    /**
     * Constructor to create a Fish object at a specific position on the screen
     * 
     * @param app a reference to the Game object that created this object
     */

    public Lilypad(Game app) {
        this(app, 300, 370);
    }

    /**
     * Draw this to the screen at the appropriate coordinates
     */

    public void draw() {
        this.app.noStroke();
        this.app.fill(76, 161, 76);
        this.app.ellipse(this.x, this.y, 40, 25);
    }

    /**
     * Have the lilypad move across the screen
     */
    public void move() {
        // update x with speed to swim across screen
        int speed = 1;
        this.x = this.x + speed;
        // when crosses border
        if ((this.x - 20) > 400) {
            this.x = speed * -1;
        }
    }

    /**
     * getter for lilypad x
     * 
     * @return this.x
     */
    public int lilyX() {
        return this.x;
    }

    /**
     * getter for lilypad y
     * 
     * @return this.y
     */
    public int lilyY() {
        return this.y;
    }

}
