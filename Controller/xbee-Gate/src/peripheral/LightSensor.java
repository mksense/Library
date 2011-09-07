package peripheral;

import eu.mksense.XBeeRadio;
import io.Event;
import io.IMsgLListener;
import io.PacketTypes;
import io.Worker;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * class that represents light sensor
 */
public class LightSensor implements IMsgLListener, PacketTypes {
    protected SmartObject parent;
    protected int pin;
    protected List<ILValueListener> Listeners;


    public LightSensor(SmartObject p, int pin) {
        parent = p;
        this.pin = pin;
        Listeners = Collections.synchronizedList(new LinkedList<ILValueListener>());
        Worker.addMsgLListener(this);
    }

    /**
     * ask from the smartObject the corresponding value
     */
    public synchronized void askValue() {
        try {
            XBeeRadio.getInstance().send(parent.getAddress(), parent.getPort(), new int[]{LIGHT_V, pin});
        } catch (Exception e) {
            e.printStackTrace();
        }
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

        }


    }

    public synchronized void addListener(ILValueListener who) {
        if (!Listeners.contains(who))
            Listeners.add(who);
    }

    public synchronized void removeListener(ILValueListener who) {
        Listeners.remove(who);
    }

}