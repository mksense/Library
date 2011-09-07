import peripheral.ISListener;
import peripheral.Switch;
import processing.core.PApplet;

public class processing extends PApplet implements ISListener {
    Switch sw1, sw2;

    public void setup() {
        size(500, 500);

        background(100, 200, 50);

        /*devices are subscribed*/
        Sensors.getInstance().addArduino("88F0", 100);

        Sensors.getInstance().addSPOT("5EF0", 37);

        sw1 = Sensors.getInstance().getSPOT("5EF0").getSwitch(1);
        sw2 = Sensors.getInstance().getArduino("88F0").getSwitch(2);

        /*subscribe to observer of sunSPOT's switch #1 and arduino's switch at pin 2 */
        sw1.addListener(this);
        sw2.addListener(this);

    }

    public void draw() {
    }

    public void SwitchPressed(Switch sw) {
        if (sw == sw1) {
            /* do something */
        } else if (sw == sw2) {
            /*do something*/
        }

    }
}

