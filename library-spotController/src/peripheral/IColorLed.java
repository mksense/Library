package peripheral;/*interface which provide some functions to handle the led*/

public interface IColorLed {
    /*this reference type will be used to set some variables that will be send to the remote SunSPOT*/

    /**
     * turn the led on
     *
     * @param led which led to turn on and later with which color
     * @param clr color
     */
    void setON(int led, LColor clr);

    void setON(int led);

    /**
     * turn the led ON
     *
     * @param ledA start led
     * @param ledB last led
     * @param clr  color
     */
    void setON(int ledA, int ledB, LColor clr);

    void setON(int ledA, int ledB);

    /**
     * turn the led off
     *
     * @param led which led to turn off
     */
    void setOFF(int led);

    /**
     * turn led off
     *
     * @param ledA start led
     * @param ledB stop led
     */
    void setOFF(int ledA, int ledB);

    /*return the state of the led true or false*/

    public boolean getMode();


}
