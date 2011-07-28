package peripheral;

/**
 * interface implemented by the user to listen for temperature threshold events
 */
public interface ITThresholdListener {
    /**
     *
     * @param sensor  the temperature sensor
     * @param val     the incoming threshold/value
     */
    public void TempThresholdExceed(TThresholdSensor sensor, double val);

}
