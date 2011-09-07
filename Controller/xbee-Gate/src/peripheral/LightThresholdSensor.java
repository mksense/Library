package peripheral;

import eu.mksense.XBeeRadio;
import io.Event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class LightThresholdSensor extends LightSensor {
    private int threshA, threshB, time;

    private List<ILThresholdListener> TListeners;


    public LightThresholdSensor(SmartObject p, int pin) {
        super(p, pin);
        threshA = -1;
        threshB = -1;
        TListeners = Collections.synchronizedList(new ArrayList<ILThresholdListener>());
    }


    public synchronized void askValue() {
        super.askValue();
    }


    private void askLightThresholdSensor() {
        try {
            XBeeRadio.getInstance().send(parent.getAddress(), parent.getPort(), new int[]{LIGHT_T, ON, (0xff & (threshA >> 24)), (0xff & (threshA >> 16)), (0xff & (threshA >> 8)), (0xff & threshA), (0xff & (threshB >> 24)), (0xff & (threshB >> 16)), (0xff & (threshB >> 8)), (0xff & threshB), (0xff & (time >> 24)), (0xff & (time >> 16)), (0xff & (time >> 8)), (0xff & time)});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * inform the remote that there are no more listeners  for lightSensor
     */
    private void resetLightThresholdSensor() {
        try {
            XBeeRadio.getInstance().send(parent.getAddress(), parent.getPort(), new int[]{LIGHT_T, OFF});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void setThresholdsTime(final int a, final int b, final int interval) {
        if (((threshA != -1) && (threshB != -1))) {
            return;
        }
        threshA = a;
        threshB = b;
        time = interval;
    }


    public void HandleLEvent(Event ev) {
        if ((ev.getEvent() == pin) && (ev.getParentAddress().equals(parent.getAddress()))) {
            final LightSensor ls = this;
            final int value = ev.getIValue();
            for (int i = 0; i < Listeners.size(); i++) {
                final ILValueListener l = Listeners.get(i);
                new Thread("Light val") {
                    public void run() {
                        l.lightSensorValue(ls, value);
                    }
                }.start();
            }

        } else if ((ev.getEvent() == 2) && (ev.getParentAddress().equals(parent.getAddress()))) {
            final LightThresholdSensor ls = this;
            final int value = ev.getIValue();
            for (int i = 0; i < TListeners.size(); i++) {
                final ILThresholdListener l = TListeners.get(i);
                new Thread("Threshold Light val") {
                    public void run() {
                        l.thresholdExceed(ls, value);
                    }
                }.start();
            }

        }


    }

    public synchronized void addListener(ILValueListener who) {
        super.addListener(who);
    }

    public synchronized void removeListener(ILValueListener who) {
        super.removeListener(who);
    }

    public synchronized void addThresholdListener(ILThresholdListener who) {
        if (!TListeners.contains(who))
            TListeners.add(who);
        if (TListeners.size() == 1) {
            askLightThresholdSensor();
        }
    }

    /**
     * function to remove listener
     *
     * @param who the listener
     */
    public synchronized void removeThresholdListener(ILThresholdListener who) {
        TListeners.remove(who);
        if (TListeners.size() == 0) {
            resetLightThresholdSensor();
        }
    }

}
