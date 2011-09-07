import peripheral.ITThresholdListener;
import peripheral.TThresholdSensor;
import processing.core.PApplet;

public class processing extends PApplet implements ITThresholdListener {

    public void setup() {
        size(500, 500);

        background(100, 200, 50);

        /*devices are subscribed*/
        Sensors.getInstance().addSPOT("5EF0", 37);

        /*set thresholds and subscribe to observers*/
        Sensors.getInstance().getSPOT("5EF0").getTemperatureSensor().setThresholdsTime(200,500,1000);
        Sensors.getInstance().getSPOT("5EF0").getTemperatureSensor().addThresholdListener(this);
    }

    public void draw() {
    }

    public void TempThresholdExceed(TThresholdSensor sensor, double val) {
        /*do something when threshold is exceeded*/
    }
}

