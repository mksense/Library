import peripheral.ITValueListener;
import peripheral.TemperatureSensor;
import processing.core.PApplet;

public class processing extends PApplet implements ITValueListener {

    public void setup() {
        size(500, 500);

        background(100, 200, 50);

        /*devices are subscribed*/
        Sensors.getInstance().addArduino("88F0", 100);

        Sensors.getInstance().addSPOT("5EF0", 37);

        /*subscribe to observers for temperature value*/
        Sensors.getInstance().getSPOT("5EF0").getTemperatureSensor().addListener(this);
        Sensors.getInstance().getArduino("88F0").getTemperatureSensor(5).addListener(this);
        noLoop();
    }

    public void draw() {
        /*ask to be informed about the current temperature value*/
        Sensors.getInstance().getSPOT("5EF0").getTemperatureSensor().askValue();
        Sensors.getInstance().getArduino("88F0").getTemperatureSensor(5).askValue();
    }


    public void TemperatureValue(TemperatureSensor s, int val) {
        if (s == Sensors.getInstance().getSPOT("5EF0").getTemperatureSensor()) {
            /*do something with the incoming value*/
        } else if (s == Sensors.getInstance().getArduino("88F0").getTemperatureSensor(5)) {
            /*do something*/
        }
    }
}


