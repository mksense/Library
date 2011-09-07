package peripheral;

import communication.DataSender;
import io.Event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TThresholdSensor extends TemperatureSensor {

    private int threshA, threshB;
    private int time;

    private List<ITThresholdListener> TListeners;

    public TThresholdSensor(SmartObject p, int pin) {
        super(p, pin);
        threshA = -1;
        threshB = -1;
        TListeners = Collections.synchronizedList(new ArrayList<ITThresholdListener>());
    }

    public void askValue() {
        super.askValue();
    }

    private void askTemperatureThresholdSensor() {
        synchronized (parent) {
            DataSender.getInstance().send(parent.getAddress(), parent.getPort(), TEMP_T, ON, threshA, threshB, time);
        }
    }

    /**
     * inform the remote that there are no more listeners  for lightSensor
     */
    private void resetTemperatureThresholdSensor() {
        synchronized (parent) {
            DataSender.getInstance().send(parent.getAddress(), parent.getPort(), TEMP_T, OFF);
        }
    }

    public synchronized void setThresholdsTime(final int a, final int b, final int time) {
        if (((threshA != -1) && (threshB != -1))) {
            return;
        }
        threshA = a;
        threshB = b;
        this.time = time;
    }

    public void HandleTEvent(Event ev) {

        if ((ev.getEvent() == pin) && (ev.getParentAddress().contains(parent.getAddress()))) {
            final TemperatureSensor ls = this;
            final int value = ev.getIValue();
            for (int i = 0; i < Listeners.size(); i++) {
                final ITValueListener l = Listeners.get(i);
                new Thread("Temperature val") {
                    public void run() {
                        l.TemperatureValue(ls, value);
                    }
                }.start();
            }

        }
        if ((ev.getEvent() == 4) && (ev.getParentAddress().contains(parent.getAddress()))) {
            final TThresholdSensor tm = this;
            final double value = (double) ev.getIValue();
            for (int i = 0; i < TListeners.size(); i++) {
                final ITThresholdListener l = TListeners.get(i);
                new Thread("Threshold temp val") {
                    public void run() {
                        l.TempThresholdExceed(tm, value);
                    }
                }.start();
            }

        }

    }


    public synchronized void addListener(ITValueListener who) {
        super.addListener(who);
    }

    /**
     * function to remove listener
     *
     * @param who the listener
     */
    public synchronized void removeListener(ITValueListener who) {
        super.removeListener(who);
    }

    public synchronized void addThresholdListener(ITThresholdListener who) {
        if (!TListeners.contains(who))
            TListeners.add(who);
        if (TListeners.size() == 1) {
            askTemperatureThresholdSensor();
        }
    }

    /**
     * function to remove listener
     *
     * @param who the listener
     */
    public synchronized void removeThresholdListener(ITThresholdListener who) {
        TListeners.remove(who);
        if (TListeners.size() == 0) {
            resetTemperatureThresholdSensor();
        }
    }


}
