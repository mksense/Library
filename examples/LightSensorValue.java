import peripheral.ILValueListener;
import peripheral.LightSensor;
import peripheral.Switch;
import processing.core.PApplet;

public class processing extends PApplet implements ILValueListener {

    public void setup() {
        size(500, 500);

        background(100, 200, 50);

        /*devices are subscribed*/
        Sensors.getInstance().addArduino("88F0", 100);

        Sensors.getInstance().addSPOT("5EF0", 37);

        /*subscribe to observers for light value*/
        Sensors.getInstance().getSPOT("5EF0").getLightSensor().addListener(this);
        Sensors.getInstance().getArduino("88F0").getLightSensor(5).addListener(this);
    	noLoop();
    }

    public void draw() {
        /*ask to be informed about the current light value*/
        Sensors.getInstance().getSPOT("5EF0").getLightSensor().askValue();
        Sensors.getInstance().getArduino("88F0").getLightSensor(5).askValue();
    }


    public void lightSensorValue(LightSensor light, int val) {
        if(light==Sensors.getInstance().getSPOT("5EF0").getLightSensor()){
            /*do something with the incoming value*/
        }else if(light==Sensors.getInstance().getArduino("88F0").getLightSensor(5)){
            /*do something*/
        }
    }
}


