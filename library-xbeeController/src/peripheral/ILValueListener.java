package peripheral;

/**
 * interface implemented by the user to listen for light-value events
 */
public interface ILValueListener {

    /**
     *
     * @param light the sensor
     * @param val   the value
     */
    public void lightSensorValue(LightSensor light, int val);


}