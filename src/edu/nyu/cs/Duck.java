package edu.nyu.cs;

import processing.core.PApplet;
import processing.core.PImage;
//import java.util.ArrayList;

public class Duck {
    // instance properties
    private Game app; // will hold a reference to the main Game object
    private PImage img; // will hold a reference to an image of a star
    private int x; // will hold the x coordinate of this object on the screen
    private int y; // will hold the y coordinate of this object on the screen
    //private ArrayList<Integer> history;
    private int speed;

    /**
     * Constructor to create a Star object at a specific position on the screen
     * @param app a reference to the Game object that created this object
     * @param x the x coordinate of this object on the screen
     * @param y the y coordinate of this object on the screen
     */
    public Duck(Game app, int x, int y, int speed) {
        this.app = app; // store a reference to the main game object

        // store the x and y coordinates of this object on the screen
        this.x = x;
        this.y = y;
        this.speed = speed;

        // initialize history array
        //this.history = new ArrayList<Integer>();

    }

    /**
     * Draw this star's image to the screen at the appropriate coordinates
     */
    public void draw() {
        // draw this object's image at its x and y coordinates
        //this.app.imageMode(PApplet.CENTER); // setting so the image is drawn centered on the specified x and y coordinates
        //this.app.image(this.img, this.x, this.y);

        // Draw the duck image at x and y coordinates 
        // duck body
        this.app.noStroke();
        this.app.fill(255, 211, 66);
        this.app.ellipse(this.x, this.y, 55, 48);
        this.app.ellipse(this.x,(this.y - 24), 25,15);
        this.app.ellipse(this.x,(this.y + 24),25,15);
        
        //beak
        this.app.noStroke();
        this.app.fill(230,125,21);
        this.app.ellipse((this.x + 18), this.y,15,12);

        // duck head
        this.app.fill(255,220,84);
        this.app.ellipse((this.x + 5), this.y, 30, 27);
        
        /** trail
        this.app.strokeWeight(3);
        this.app.stroke(209,243,255);
        beginShape(TRIANGLES);
        for (int i = 0; i < this.history.size; i++) {
            int[] pos = this.history[i];
            this.app.noFill();
            vertex(pos.x - 28, pos.y - 10);
            vertex(pos.x - 28, pos.y + 10);
            vertex(pos.x - 58, pos.y);   
        }
        endShape();
        */
    }

    /**
     * Have the duck swim across the screen
     */
    public void swim() {
        // update x with speed to swim across screen
        this.x = this.x + this.speed;
        
        /**
         * update trail behind duck
         * if ((this.x - 27.5) > width) {
            this.x = this.speed * -1;
            this.history = [];
        }
         * let v = createVector(this.x,this.y)
         * this.history.push(v);
         * if (this.history.length > 30) {
         * this.history.splice(0, 1);
         * } 
         *
        */
        

    }

    /**
     * Determines whether a given x, y coordinate overlaps with this Star.
     * @param x The x coordinate of interest.
     * @param y The y coordinate of interest.
     * @param fudgeFactor An amount by which to expand the area we consider overlap
     * @return Boolean true if the x,y coordinate overlaps with this star, false otherwise.
     */
    public boolean overlaps(int x, int y, int fudgeFactor) {
        // get the coordinates of all edges of this Star's image
        int l = this.x - this.img.width/2 - fudgeFactor; // the left edge's x coord
        int r = this.x + this.img.width/2 + fudgeFactor; // the right edge's x coord
        int t = this.y - this.img.height/2 - fudgeFactor; // the top edge's y coord
        int b = this.y + this.img.height/2 + fudgeFactor; // the bottom edge's y coord
        // return whether the x,y coords are within the bounds of this Star's image
        return (x > l && x < r && y > t && y < b);
    }

}
