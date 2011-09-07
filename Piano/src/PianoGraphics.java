import processing.core.PApplet;

public class PianoGraphics extends Thread  {

    private PApplet myParent;
    private int[] buttons = new int[6];

    public PianoGraphics(PApplet parent, int[] array) {
        myParent = parent;

        buttons = array;
    }

    public void run() {
            display();

    }


    public void display() {


        //nto
        if (buttons[0] == 0) {
            myParent.fill(myParent.random(0, 250), myParent.random(0, 250), myParent.random(0, 250));
        } else {
            myParent.fill(247, 225, 225);
        }
        myParent.rect(0, 0, 200, myParent.height);

        //re
        if (buttons[1] == 0) {
            myParent.fill(myParent.random(0, 250), myParent.random(0, 250), myParent.random(0, 250));
        } else {
            myParent.fill(247, 225, 225);
        }
        myParent.rect(200, 0, 400, myParent.height);

        //mi
        if (buttons[2] == 0) {
            myParent.fill(myParent.random(0, 250), myParent.random(0, 250), myParent.random(0, 250));
        } else {
            myParent.fill(247, 225, 225);
        }
        myParent.rect(400, 0, 600, myParent.height);

        //fa
        if (buttons[3] == 0) {
            myParent.fill(myParent.random(0, 250), myParent.random(0, 250), myParent.random(0, 250));
        } else {
            myParent.fill(247, 225, 225);
        }
        myParent.rect(600, 0, 800, myParent.height);

        //sol
        if (buttons[4] == 0) {
            myParent.fill(myParent.random(0, 250), myParent.random(0, 250), myParent.random(0, 250));
        } else {
            myParent.fill(247, 225, 225);
        }
        myParent.rect(800, 0, 1000, myParent.height);
        //la
        if (buttons[5] == 0) {
            myParent.fill(myParent.random(0, 250), myParent.random(0, 250), myParent.random(0, 250));
        } else {
            myParent.fill(247, 225, 225);
        }
        myParent.rect(1000, 0, 1200, myParent.height);


        //si
        if (buttons[2] == 1) {
            myParent.fill(myParent.random(0, 250), myParent.random(0, 250), myParent.random(0, 250));
        } else {
            myParent.fill(247, 225, 225);
        }
        myParent.rect(1200, 0, 1400, myParent.height);


        if (buttons[0] == 1) {
            myParent.fill(160, 155, 155);
        } else {
            myParent.fill(0);
        }
        myParent.rect(140, 0, 160, 450);


        if (buttons[1] == 1) {
            myParent.fill(160, 155, 155);
        } else {
            myParent.fill(0);
        }
        myParent.rect(340, 0, 160, 450);


        if (buttons[3] == 1) {
            myParent.fill(160, 155, 155);
        } else {
            myParent.fill(0);
        }
        myParent.rect(720, 0, 160, 450);


        if (buttons[4] == 1) {
            myParent.fill(160, 155, 155);
        } else {
            myParent.fill(0);
        }
        myParent.rect(920, 0, 160, 450);


        if (buttons[5] == 1) {
            myParent.fill(160, 155, 155);
        } else {
            myParent.fill(0);
        }
        myParent.rect(1110, 0, 160, 450);

    }

}
