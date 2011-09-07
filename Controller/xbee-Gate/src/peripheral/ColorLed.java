package peripheral;

import eu.mksense.XBeeRadio;
import io.PacketTypes;

public class ColorLed implements IColorLed, PacketTypes {

    private boolean mode;
    private SmartObject parent = null;

    public ColorLed(SmartObject o) {
        this.mode = false;
        parent = o;
    }

    /**
     *
     * @param ledA led to turn on
     * @param clr  color
     */
    public synchronized void setON(int ledA, LColor clr) {
        setON(true);
        try {
            XBeeRadio.getInstance().send(parent.getAddress(), parent.getPort(),new int[]{LED,SET_ON_O,ledA, clr.red(), clr.green(), clr.blue()});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param led to turn on
     */
    public synchronized void setON(int led) {
        setON(true);
        try {
            XBeeRadio.getInstance().send(parent.getAddress(), parent.getPort(),new int[]{LED,SET_ON_O,led});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * turn many led on
     *
     * @param ledA start led
     * @param ledB last led
     */

    public synchronized void setON(int ledA, int ledB, LColor clr) {
        setON(true);
        try {
            XBeeRadio.getInstance().send(parent.getAddress(), parent.getPort(),new int[]{LED,SET_ON_M,ledA , ledB, clr.red(), clr.green(), clr.blue()});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void setON(int ledA, int ledB) {
        setON(true);
        try {
            XBeeRadio.getInstance().send(parent.getAddress(), parent.getPort(),new int[]{LED,SET_ON_M,ledA , ledB});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * turn one led off
     *
     * @param ledA which led to turn off
     */
    public synchronized void setOFF(int ledA) {
        setON(false);
        try {
            XBeeRadio.getInstance().send(parent.getAddress(), parent.getPort(),new int[]{LED,SET_OFF_O,ledA});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * turn many led off
     *
     * @param ledA start led
     * @param ledB stop led
     */
    public synchronized void setOFF(int ledA, int ledB) {
        setON(false);
        try {
            XBeeRadio.getInstance().send(parent.getAddress(), parent.getPort(),new int[]{LED,SET_OFF_M,ledA , ledB});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * define the state of the led
     *
     * @param on which is the state
     */

    private void setON(boolean on) {
        mode = on;
    }

    /**
     *
     * @return the state/mode of the LEDs
     */
    public synchronized boolean getMode() {
        return mode;
    }

}
