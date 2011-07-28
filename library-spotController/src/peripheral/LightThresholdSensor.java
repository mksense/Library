package peripheral;

import communication.DataSender;
import io.Event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * class represents the light with thresholds
 */
public class LightThresholdSensor extends LightSensor {
    private int threshA, threshB, time;

    private List<ILThresholdListener> TListeners;


    public LightThresholdSensor(SmartObject p, int pin) {
        super(p, pin);
        threshA = -1;
        threshB = -1;
        TListeners = Collections.synchronizedList(new ArrayList<ILThresholdListener>());
    }


    public void askValue() {
        super.askValue();
    }


    private void askLightThresholdSensor() {
        synchronized (parent) {
            DataSender.getInstance().send(parent.getAddress(), parent.getPort(), LIGHT_T, ON, threshA, threshB, time);
        }
    }

    /**
     * inform the remote that there are no more listeners  for lightSensor
     */
    private void resetLightThresholdSensor() {
        synchronized (parent) {
            DataSender.getInstance().send(parent.getAddress(), parent.getPort(), LIGHT_T, OFF);
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
        if ((ev.getEvent() == pin) && (ev.getParentAddress().contains(parent.getAddress()))) {
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

        } else if ((ev.getEvent() == 2) && (ev.getParentAddress().contains(parent.getAddress()))) {
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

    public synchronized void removeThresholdListener(ILThresholdListener who) {
        TListeners.remove(who);
        if (TListeners.size() == 0) {
            resetLightThresholdSensor();
        }
    }

}
