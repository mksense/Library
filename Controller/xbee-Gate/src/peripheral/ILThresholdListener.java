package peripheral;

/**
 * interface implements by user to listen for light threshold events
 */
public interface ILThresholdListener {
    /**
     * @param sensor the sensor from which the value comes
     * @param val    the value that exceeded the thresholds
     */
    public void thresholdExceed(LightThresholdSensor sensor, int val);
}
