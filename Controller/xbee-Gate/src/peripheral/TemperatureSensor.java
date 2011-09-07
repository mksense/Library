package peripheral;

import eu.mksense.XBeeRadio;
import io.Event;
import io.IMsgTListener;
import io.PacketTypes;
import io.Worker;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * basic class that represents temperature sensor
 */
public class TemperatureSensor implements IMsgTListener, PacketTypes {
    protected SmartObject parent;
    protected int pin;
    protected List<ITValueListener> Listeners;


    public TemperatureSensor(SmartObject p, int pin) {
        parent = p;
        this.pin = pin;
        Listeners = Collections.synchronizedList(new LinkedList<ITValueListener>());
        Worker.addMsgTListener(this);

    }

    /**
     * ask from the smartObject to send the corresponding value
     */
    public synchronized void askValue() {
        try {
            XBeeRadio.getInstance().send(parent.getAddress(), parent.getPort(), new int[]{TEMP_V, pin});
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    }

    public synchronized void addListener(ITValueListener who) {
        if (!Listeners.contains(who))
            Listeners.add(who);
    }

    public synchronized void removeListener(ITValueListener who) {
        Listeners.remove(who);
    }

}
