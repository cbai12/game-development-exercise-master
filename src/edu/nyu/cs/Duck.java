package edu.nyu.cs;

import processing.core.PApplet;
import processing.core.PImage;
//import java.util.ArrayList;

public class Duck {
    // instance properties
    private Game app; // will hold a reference to the main Game object
    private float x; // will hold the x coordinate of this object on the screen
    private int y; // will hold the y coordinate of this object on the screen
    //private ArrayList<Integer> history;
    private float speed;

    /**
     * Constructor to create a Star object at a specific position on the screen
     * @param app a reference to the Game object that created this object
     * @param x the x coordinate of this object on the screen
     * @param y the y coordinate of this object on the screen
     */
    
     // constructor used when all dimensions
     public Duck(Game app, int x, int y, float speed) {
        this.app = app; // store a reference to the main game object

        // store the x and y coordinates of this object on the screen
        this.x = x;
        this.y = y;
        this.speed = speed;

        // initialize history array
        //this.history = new ArrayList<Integer>();

    }

    // constructor used when no dimensions specified
    public Duck(Game app) {
        this.app = app;
        this.x = 0;
        this.y = 250;
        this.speed = 0;
    }

    // constructor used when only x and y specified
    public Duck(Game app, int x, int y) {
        this.app = app;
        this.x = x;
        this.y = y;
        this.speed = 1;
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
        float currentX = (float) this.x;
        this.x = currentX + this.speed;

        if ((this.x - 27.5) > 400) {
            this.x = this.speed * -1;
            //this.history = [];
        }
        
        /**
         * update trail behind duck
         * let v = createVector(this.x,this.y)
         * this.history.push(v);
         * if (this.history.length > 30) {
         * this.history.splice(0, 1);
         * } 
         *
        */
        

    }

    // getters
    public float duckX() {
        return this.x;
    }

    public int duckY() {
        return this.y;
    }

}
