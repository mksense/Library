package peripheral;

/**
 * represents an arduino board
 */
public class Arduino extends SmartObject {

    private IColorLed led;
    private Switch[] Switches;
    private LightSensor[] light;
    private TemperatureSensor[] temperature;


    public Arduino(String add, int pPort) {
        super(add, pPort);
        initLed();
        initSwitch();
        initLight();
        initTemperature();

    }

    /**
     * initialize switches
     */
    private void initSwitch() {
        Switches = new Switch[12];
        for (int i = 0; i < 12; i++) {
            Switches[i] = new Switch(this, i + 2);
        }
    }

    /**
     * initialize light sensors
     */
    private void initLight() {
        light = new LightSensor[6];
        for (int i = 0; i < 6; i++) {
            light[i] = new LightSensor(this, i);
        }
    }

    /**
     * initialize temperature sensors
     */
    private void initTemperature() {
        temperature = new TemperatureSensor[6];
        for (int i = 0; i < 6; i++) {
            temperature[i] = new TemperatureSensor(this, i);
        }
    }

    /**
     * initialize LEDs
     */
    private void initLed() {
        led = new ColorLed(this);
    }

    /**
     * getter method for LEDs
     *
     * @return led
     */
    public synchronized IColorLed getLEDs() {
        return led;
    }

    /**
     * getter method for switches
     *
     * @param i pin
     * @return switch
     */
    public synchronized Switch getSwitch(int i) {
        int v = i - 2;
        return Switches[v];
    }

    /**
     * getter method for light sensors
     *
     * @param i pin
     * @return lightSensor
     */
    public synchronized LightSensor getLightSensor(int i) {
        return light[i];
    }

    /**
     * getter method for temperatureSensor
     *
     * @param i pin
     * @return temperatureSensor
     */
    public synchronized TemperatureSensor getTemperatureSensor(int i) {
        return temperature[i];
    }

}
