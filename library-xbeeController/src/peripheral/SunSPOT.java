package peripheral;

import com.rapplogic.xbee.api.XBeeAddress16;

/**
 * class that represents a sunSpot
 */
public class SunSPOT extends SmartObject {
    private IColorLed led;
    private Switch[] Switches;
    private LightThresholdSensor lightSensor;
    private TThresholdSensor tempSensor;


    /**
     * constructor
     *
     * @param add   remote address
     * @param pPort port
     */
    public SunSPOT(XBeeAddress16 add, int pPort) {
        super(add, pPort);
        initLed();
        initSwitch();
        initLightSensor();
        initTempSensor();
    }

    private void initTempSensor() {
        tempSensor = new TThresholdSensor(this,3);
    }

    /*initialize the led */

    private void initLed() {
        led = new ColorLed(this);
    }
    /*initialize switches*/

    private void initSwitch() {
        Switches = new Switch[2];
        Switches[0] = new Switch(this, 1);
        Switches[1] = new Switch(this, 2);

    }
    /*initialize lightSensor*/


    private void initLightSensor() {
        lightSensor = new LightThresholdSensor(this, 1);
    }

    /**
     * getter for temperature sensor
     * @return  temperature object
     */
    public synchronized TThresholdSensor getTemperatureSensor() {
        return tempSensor;
    }

    /**
     * getter for LEDs
     * @return the led object
     */

    public synchronized IColorLed getLEDs() {
        return led;
    }

    /**
     * getter for switches
     * @return the switch object
     */
    public synchronized Switch[] getSwitches() {
        return Switches;
    }

    /**
     * getter for light sensor
     * @return lightSensor object
     */

    public synchronized LightThresholdSensor getLightSensor() {
        return lightSensor;

    }


}
