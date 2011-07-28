package peripheral;

/**
 * interface implemented by classes to listen for light values
 */
public interface ILValueListener {

    /**
     * @param light the sensor
     * @param val   the value
     */
    public void lightSensorValue(LightSensor light, int val);


}
