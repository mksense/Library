import ddf.minim.AudioSample;
import ddf.minim.Minim;
import processing.core.PApplet;

import java.util.Observable;
import java.util.Observer;

public class Processing extends PApplet implements Observer {

    Minim minim;
    AudioSample[] audioSamples = new AudioSample[6];
    AudioSample[] audioSamplesUp = new AudioSample[6];

    private int[] player = new int[6];


    public void setup() {


        size(1400 ,800);

        minim = new Minim(this);

        audioSamples[0] = minim.loadSample("/home/anastasia/Desktop/c.mp3", 1024);
        audioSamples[1] = minim.loadSample("/home/anastasia/Desktop/d.mp3", 1024);
        audioSamples[2] = minim.loadSample("/home/anastasia/Desktop/e.mp3", 1024);
        audioSamples[3] = minim.loadSample("/home/anastasia/Desktop/f.mp3", 1024);
        audioSamples[4] = minim.loadSample("/home/anastasia/Desktop/g.mp3", 1024);
        audioSamples[5] = minim.loadSample("/home/anastasia/Desktop/a.mp3", 1024);

        audioSamplesUp[0] = minim.loadSample("/home/anastasia/Desktop/c#.mp3", 1024);
        audioSamplesUp[1] = minim.loadSample("/home/anastasia/Desktop/d#.mp3", 1024);
        audioSamplesUp[2] = minim.loadSample("/home/anastasia/Desktop/b.mp3", 1024);
        audioSamplesUp[3] = minim.loadSample("/home/anastasia/Desktop/f#.mp3", 1024);
        audioSamplesUp[4] = minim.loadSample("/home/anastasia/Desktop/g#.mp3", 1024);
        audioSamplesUp[5] = minim.loadSample("/home/anastasia/Desktop/a#.mp3", 1024);

        for (int i = 0; i < player.length; i++) {
            player[i] = -1;
        }

    }

    public void draw() {
        for (int i = 0; i < 6; i++) {
            if (player[i] == 0) {
                audioSamples[i].trigger();
            }
            if (player[i] == 1) {
                audioSamplesUp[i].trigger();
            }
            player[0] = -1;
        }
    }


    public void update(Observable observable, Object o) {
        if (!(observable instanceof Between)) {
            return;
        }

        player = (int[]) o;

        (new PianoGraphics(this, player)).start();

    }

}
