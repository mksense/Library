package peripheral;

import com.rapplogic.xbee.api.XBeeAddress16;

/**
 * class that represents an arduino board
 */

public class Arduino extends SmartObject {

    private IColorLed led;
    private Switch[] Switches;
    private LightSensor[] light;
    private TemperatureSensor[] temperature;


    public Arduino(XBeeAddress16 add, int pPort) {
        super(add, pPort);
        initLed();
        initSwitch();
        initLight();
        initTemperature();
    }

    /**
     * initialize LEDs
     */
    private void initLed() {
        led = new ColorLed(this);
    }

    /**
     * initialize switches
     */
    private void initSwitch(){
        Switches=new Switch[12];
        for(int i=0;i<12;i++){
            Switches[i]=new Switch(this,i+2);
        }
    }

    /**
     * initialize light
     */
    private void initLight(){
        light=new LightSensor[6];
        for(int i=0;i<6;i++){
            light[i]=new LightSensor(this,i);
        }
    }

    /**
     * initialize temperature
     */
    private void initTemperature(){
        temperature=new TemperatureSensor[6];
        for(int i=0;i<6;i++){
            temperature[i]=new TemperatureSensor(this,i);
        }
    }


   //returns an object for handling digital LEDs
    public synchronized IColorLed getLEDs() {
        return led;
    }

    /**
     * getter for switch
     * @param i pin
     * @return  switch object
     */
    public synchronized Switch getSwitch(int i) {
        int v=i-2;
        return  Switches[v];
    }

    /**
     * getter for light sensor
     * @param i pin
     * @return  light object
     */
    public synchronized LightSensor getLightSensor(int i) {
        return  light[i];
    }

    /**
     * getter for temperature
     * @param i  pin
     * @return  temperature object
     */
    public synchronized TemperatureSensor getTemperatureSensor(int i){
        return  temperature[i];
    }

}
