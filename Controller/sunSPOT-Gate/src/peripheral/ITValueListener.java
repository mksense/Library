package peripheral;

/**
 * interface implemented by classes to listen to temperature values
 */
public interface ITValueListener {
    /**
     * @param s   the temperature sensor
     * @param val the incoming value
     */
    public void TemperatureValue(TemperatureSensor s, int val);
}
