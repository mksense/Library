import peripheral.LColor;
import processing.core.PApplet;

public class processing extends PApplet {

    public void setup() {
        size(500, 500);

        background(100, 200, 50);

        /*devices are subscribed*/
        Sensors.getInstance().addArduino("88F0", 100);
        Sensors.getInstance().addSPOT("5EF0", 37);

        noLoop();
    }

    public void draw() {
        Sensors.getInstance().getSPOT("5EF0").getLEDs().setON(0, 7, new LColor(250, 250, 250));
        Sensors.getInstance().getArduino("88F0").getLEDs().setON(2, 13);
    }
}
