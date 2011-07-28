package peripheral;

/**
 * interface for listening the Switches
 */
public interface ISListener {
    /**
     * @param sw the switch that was pressed
     */
    public void SwitchPressed(Switch sw);
}
