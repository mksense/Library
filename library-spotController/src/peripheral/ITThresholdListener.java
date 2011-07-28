package peripheral;

/**
 * interface implemented by classes to listen to temperature thresholds
 */
public interface ITThresholdListener {
    /**
     * @param sensor the temperature sensor
     * @param val    the incoming threshold/value
     */
    public void TempThresholdExceed(TThresholdSensor sensor, double val);

}
