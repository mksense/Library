package peripheral;

/**
 * interface implemented by the user to listen to temperature value events
 */
public interface ITValueListener {
    /**
     *
     * @param s  the temperature sensor
     * @param val the incoming value
     */
    public void TemperatureValue(TemperatureSensor s, int val);
}
