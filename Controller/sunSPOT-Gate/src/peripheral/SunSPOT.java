package peripheral;

/**
 * class through which we will access the hardware we wish and operations provided
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
    public SunSPOT(String add, int pPort) {
        super(add, pPort);
        initLed();
        initSwitch();
        initLightSensor();
        initTempSensor();
    }

    private void initTempSensor() {
        tempSensor = new TThresholdSensor(this, 3);
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

    public synchronized TThresholdSensor getTemperatureSensor() {
        return tempSensor;
    }

    /**
     * method to get access to the leds through our program
     *
     * @return the led object
     */
    public synchronized IColorLed getLEDs() {
        return led;
    }

    /**
     *
     * @param i id for switch
     * @return Switch object
     */
    public synchronized Switch getSwitch(int i) {
        int v=i-1;
        return Switches[v];
    }

    /**
     * method to get access to lightSensor
     *
     * @return lightSensor object
     */

    public synchronized LightThresholdSensor getLightSensor() {
        return lightSensor;

    }


}
