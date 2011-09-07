import com.sun.spot.sensorboard.EDemoBoard;
import com.sun.spot.sensorboard.peripheral.*;
import com.sun.spot.util.Queue;
import com.sun.spot.util.Utils;

import javax.microedition.io.Datagram;
import java.io.IOException;

public class Worker extends Thread implements ISwitchListener, ILightSensorThresholdListener, ITemperatureInputThresholdListener, PacketTypes {
    /*variable to hold the passed Queue*/
    private Queue queueProcess;
    private ITriColorLED[] leds;
    private ISwitch sw1, sw2;
    private ILightSensor lightSensor;
    private ITemperatureInput tempSensor;
    private String add;
    private int LThreshA, LThreshB;
    private double TThreshA, TThreshB;
    private int LTime, TTime;
    private int lightLevel;
    private int tempValue;
    private Queue msgQueue;
    private Queue freeMsgQueue;

    public Worker(Queue queue) {
        this.queueProcess = queue;
        leds = EDemoBoard.getInstance().getLEDs();
        sw1 = EDemoBoard.getInstance().getSwitches()[EDemoBoard.SW1];
        sw2 = EDemoBoard.getInstance().getSwitches()[EDemoBoard.SW2];
        lightSensor = EDemoBoard.getInstance().getLightSensor();
        tempSensor = EDemoBoard.getInstance().getADCTemperature();
        init();
    }

    /**
     * initialize queues and some default free messages
     */
    private void init() {
        msgQueue = new Queue();
        freeMsgQueue = new Queue();

        for (int i = 0; i < 100; i++) {
            freeMsgQueue.put(new Message());
        }

    }

    /**
     * process messages from host
     */
    public void run() {

        (new Transmitter(msgQueue, freeMsgQueue)).start();

        while (true) {
            Datagram MSG = (Datagram) queueProcess.get();
            try {
                int id = MSG.readUnsignedByte();
                if (id == LED) {
                    int action = MSG.readUnsignedByte();
                    switch (action) {
                        case SET_ON_O: {
                            onOne(MSG.readUnsignedByte(), MSG.readUnsignedByte(), MSG.readUnsignedByte(), MSG.readUnsignedByte());
                            break;
                        }
                        case SET_ON_M: {
                            onMany(MSG.readUnsignedByte(), MSG.readUnsignedByte(), MSG.readUnsignedByte(), MSG.readUnsignedByte(), MSG.readUnsignedByte());
                            break;
                        }
                        case SET_OFF_O: {
                            offOne(MSG.readUnsignedByte());
                            break;
                        }
                        case SET_OFF_M: {
                            offMany(MSG.readUnsignedByte(), MSG.readUnsignedByte());
                            break;
                        }
                    }

                } else if (id == SWITCH) {
                    int action = MSG.readUnsignedByte();
                    switch (action) {
                        case ON: {
                            int sw = MSG.readUnsignedByte();
                            switch (sw) {
                                case 1: {
                                    add = MSG.getAddress();
                                    sw1.addISwitchListener(this);
                                    break;
                                }
                                case 2: {
                                    add = MSG.getAddress();
                                    sw2.addISwitchListener(this);
                                    break;
                                }
                            }
                            break;
                        }
                        case OFF: {
                            int sw = MSG.readUnsignedByte();
                            switch (sw) {
                                case 1: {
                                    sw1.removeISwitchListener(this);
                                    break;
                                }

                                case 2: {
                                    sw2.removeISwitchListener(this);
                                    break;
                                }
                            }
                            break;
                        }
                    }
                } else if (id == LIGHT_T) {
                    int action = MSG.readUnsignedByte();
                    switch (action) {
                        case ON: {
                            add = MSG.getAddress();
                            LThreshA = MSG.readInt();
                            LThreshB = MSG.readInt();
                            LTime = MSG.readInt();
                            lightSensorRun(LThreshA, LThreshB, this);
                            break;
                        }
                        case OFF: {
                            lightSensorReset(this);
                            break;
                        }
                    }
                } else if (id == LIGHT_V) {
                    try {
                        add = MSG.getAddress();
                        lightLevel = lightSensor.getValue();
                        Message msg = getMessage();
                        msg.set(add, LIGHT_V, 1, lightLevel);
                        msgQueue.put(msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (id == TEMP_V) {
                    add = MSG.getAddress();
                    tempValue = tempSensor.getValue();
                    DataSender.getInstance().send(add, TEMP_V, 3, tempValue);
                } else if (id == TEMP_T) {
                    int action = MSG.readUnsignedByte();
                    switch (action) {
                        case ON: {
                            add = MSG.getAddress();
                            TThreshA = (double) MSG.readInt();
                            TThreshB = (double) MSG.readInt();
                            TTime = MSG.readInt();
                            tempSensorRun(TThreshA, TThreshB, this);
                            break;
                        }
                        case OFF: {
                            tempSensorReset(this);
                            break;
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private void onOne(final int led, final int red, final int green, final int blue) {
        leds[led].setRGB(red, green, blue);
        leds[led].setOn();
    }


    private void onMany(final int ledA, final int ledB, final int red, final int green, final int blue) {
        for (int i = ledA; i <= ledB; i++) {
            leds[i].setRGB(red, green, blue);
            leds[i].setOn();
        }
    }

    private void offOne(final int led) {
        leds[led].setOff();
    }

    private void offMany(final int ledA, final int ledB) {
        for (int i = ledA; i <= ledB; i++) {

            leds[i].setOff();
        }
    }

    private void lightSensorRun(final int threshA, final int threshB, final Worker w) throws IOException {
        lightSensor.addILightSensorThresholdListener(w);
        lightSensor.setThresholds(threshA, threshB);
        lightSensor.enableThresholdEvents(true);

    }

    private void tempSensorRun(final double threshA, final double threshB, final Worker w) {
        tempSensor.addITemperatureInputThresholdListener(w);
        tempSensor.setThresholds(threshA, threshB, true);
        tempSensor.enableThresholdEvents(true);
    }

    private void lightSensorReset(final Worker w) {
        lightSensor.removeILightSensorThresholdListener(w);
        lightSensor.enableThresholdEvents(false);
    }

    private void tempSensorReset(final Worker w) {
        tempSensor.removeITemperatureInputThresholdListener(w);
        tempSensor.enableThresholdEvents(false);
    }

    public void switchPressed(ISwitch iSwitch) {
        int switchNum = (iSwitch == sw1) ? 1 : 2;
        Message msg = getMessage();
        msg.set(add, SWITCH, switchNum, 1);
        msgQueue.put(msg);
    }

    public void switchReleased(ISwitch sw) {
        //ignore
    }

    public void thresholdExceeded(ILightSensor iLightSensor, int i) {
        Message msg = null;
        if (i >= LThreshB) {
            msg = getMessage();
            msg.set(add, LIGHT_T, 2, i);
            msgQueue.put(msg);
            Utils.sleep(LTime);
            lightSensor.enableThresholdEvents(true);
        } else if (i <= LThreshA) {
            msg = getMessage();
            msg.set(add, LIGHT_T, 2, i);
            msgQueue.put(msg);
            Utils.sleep(LTime);
            lightSensor.enableThresholdEvents(true);
        } else {
            lightSensor.enableThresholdEvents(true);
        }
    }

    public void thresholdChanged(ILightSensor iLightSensor, int i, int i1) {
        //ignore
    }

    public void thresholdExceeded(ITemperatureInput iTemperatureInput, double v, boolean b) {
        Message msg = null;
        if (v >= TThreshB) {
            msg = getMessage();
            msg.set(add, TEMP_T, 4, (int) v);
            msgQueue.put(msg);
            Utils.sleep(TTime);
            tempSensor.enableThresholdEvents(true);
        } else if (v <= TThreshA) {
            msg = getMessage();
            msg.set(add, TEMP_T, 4, (int) v);
            msgQueue.put(msg);
            Utils.sleep(TTime);
            tempSensor.enableThresholdEvents(true);
        } else {
            tempSensor.enableThresholdEvents(true);
        }

    }

    public void thresholdChanged(ITemperatureInput iTemperatureInput, double v, double v1, boolean b) {
        //ignore
    }


    private Message getMessage() {
        Message msg = null;
        if (freeMsgQueue.isEmpty()) {
            msg = new Message();
        } else {
            msg = (Message) freeMsgQueue.get();
        }
        return msg;
    }
}
