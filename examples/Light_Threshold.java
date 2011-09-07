import peripheral.ILThresholdListener;
import peripheral.LightThresholdSensor;
import processing.core.PApplet;

public class processing extends PApplet implements ILThresholdListener {

    public void setup() {
        size(500, 500);

        background(100, 200, 50);

        /*devices are subscribed*/
        Sensors.getInstance().addSPOT("5EF0", 37);

        /*set thresholds and subscribe to observers*/
        Sensors.getInstance().getSPOT("5EF0").getLightSensor().setThresholdsTime(200,500,1000);
        Sensors.getInstance().getSPOT("5EF0").getLightSensor().addThresholdListener(this);
    }

    public void draw() {
    }



    public void thresholdExceed(LightThresholdSensor sensor, int val) {
        /*do something when threshold is exceeded*/
    }
}
