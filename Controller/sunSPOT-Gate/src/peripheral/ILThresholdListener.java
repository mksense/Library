package peripheral;

/**
 * interface implemented by classes ,which want to listen for lightSensor thresholds
 */
public interface ILThresholdListener {
    /**
     * @param sensor the sensor from which the value comes
     * @param val    the value that exceeded the thresholds
     */
    public void thresholdExceed(LightThresholdSensor sensor, int val);
}
