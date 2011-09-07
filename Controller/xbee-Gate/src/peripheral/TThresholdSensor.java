package peripheral;

import eu.mksense.XBeeRadio;
import io.Event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * class that represents temperature sensor with thresholds
 */
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

    public synchronized void askValue() {
        super.askValue();
    }

    private void askTemperatureThresholdSensor() {
        try {
            XBeeRadio.getInstance().send(parent.getAddress(), parent.getPort(), new int[]{TEMP_T, ON,(0xff & (threshA >> 24)), (0xff & (threshA >> 16)), (0xff & (threshA >> 8)), (0xff & threshA), (0xff & (threshB >> 24)), (0xff & (threshB >> 16)), (0xff & (threshB >> 8)), (0xff & threshB), (0xff & (time >> 24)), (0xff & (time >> 16)), (0xff & (time >> 8)), (0xff & time)});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void resetTemperatureThresholdSensor() {
        try {
            XBeeRadio.getInstance().send(parent.getAddress(), parent.getPort(), new int[]{TEMP_T, OFF});
        } catch (Exception e) {
            e.printStackTrace();
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

        if ((ev.getEvent() == pin) && (ev.getParentAddress().equals(parent.getAddress()))) {
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
        if ((ev.getEvent() == 4) && (ev.getParentAddress().equals(parent.getAddress()))) {
            final TThresholdSensor tm = this;
            final int value = ev.getIValue();
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

    public synchronized void removeThresholdListener(ITThresholdListener who) {
        TListeners.remove(who);
        if (TListeners.size() == 0) {
            resetTemperatureThresholdSensor();
        }
    }


}
