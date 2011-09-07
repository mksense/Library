package peripheral;

/**
 * interface implemented by the user to listen for switch-pressed events
 */
public interface ISListener {
    /**
     * @param sw the switch that was pressed
     */
    public void SwitchPressed(Switch sw);
}
