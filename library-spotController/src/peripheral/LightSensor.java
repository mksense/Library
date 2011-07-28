package peripheral;

import communication.DataSender;
import io.Event;
import io.IMsgLListener;
import io.PacketTypes;
import io.Worker;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * class represents the LightSensor
 */
public class LightSensor implements IMsgLListener, PacketTypes {
    protected final SmartObject parent;
    protected final int pin;
    protected List<ILValueListener> Listeners;


    public LightSensor(SmartObject p, int pin) {
        parent = p;
        this.pin = pin;
        Listeners = Collections.synchronizedList(new LinkedList<ILValueListener>());
        Worker.addMsgLListener(this);
    }


    public void askValue() {
        synchronized (parent) {
            DataSender.getInstance().send(parent.getAddress(), parent.getPort(), LIGHT_V, pin);
        }
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

        }


    }

    public synchronized void addListener(ILValueListener who) {
        if (!Listeners.contains(who))
            Listeners.add(who);
    }

    /**
     * function to remove listener
     *
     * @param who the listener
     */
    public synchronized void removeListener(ILValueListener who) {
        Listeners.remove(who);
    }

}