package peripheral;

import communication.DataSender;
import io.PacketTypes;

/**
 * class that represents  LEDs and functionality of it
 */
public class ColorLed implements IColorLed, PacketTypes {

    private boolean mode;
    private final SmartObject parent;

    public ColorLed(SmartObject o) {
        this.mode = false;
        parent = o;
    }

    /**
     * @param ledA led to turn on
     * @param clr  color
     */
    public void setON(int ledA, LColor clr) {
        synchronized (parent) {
            setON(true);
            DataSender.getInstance().send(parent.getAddress(), parent.getPort(), LED, SET_ON_O, ledA, clr.red(), clr.green(), clr.blue());
        }
    }

    /**
     * @param led to turn on
     */
    public void setON(int led) {
        synchronized (parent) {
            setON(true);
            DataSender.getInstance().send(parent.getAddress(), parent.getPort(), LED, SET_ON_O, led);
        }
    }

    /**
     * turn led on
     *
     * @param ledA start led
     * @param ledB last led
     */

    public void setON(int ledA, int ledB, LColor clr) {
        synchronized (parent) {
            setON(true);
            DataSender.getInstance().send(parent.getAddress(), parent.getPort(), LED, SET_ON_M, ledA, ledB, clr.red(), clr.green(), clr.blue());
        }
    }

    public void setON(int ledA, int ledB) {
        synchronized (parent) {
            setON(true);
            DataSender.getInstance().send(parent.getAddress(), parent.getPort(), LED, SET_ON_M, ledA, ledB);
        }
    }


    /**
     * turn led off
     *
     * @param ledA which led to turn off
     */
    public void setOFF(int ledA) {
        synchronized (parent) {
            setON(false);
            DataSender.getInstance().send(parent.getAddress(), parent.getPort(), LED, SET_OFF_O, ledA);
        }
    }

    /**
     * turn led off
     *
     * @param ledA start led
     * @param ledB stop led
     */
    public void setOFF(int ledA, int ledB) {
        synchronized (parent) {
            setON(false);
            DataSender.getInstance().send(parent.getAddress(), parent.getPort(), LED, SET_OFF_M, ledA, ledB);
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
     * @return the state/mode of the LEDs
     */
    public boolean getMode() {
        return mode;
    }

}
