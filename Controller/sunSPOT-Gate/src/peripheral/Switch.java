package peripheral;

import communication.DataSender;
import io.Event;
import io.IMsgSListener;
import io.PacketTypes;
import io.Worker;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * class for onBoard switches implements IMsgListener and PacketTypes interface
 */
public class Switch implements IMsgSListener, PacketTypes {

    private final int Switcher;
    private final SmartObject parent;
    private List<ISListener> listeners;

    /**
     * constructor
     *
     * @param o remote
     * @param i switch id
     */
    public Switch(SmartObject o, int i) {
        Switcher = i;
        parent = o;
        listeners = Collections.synchronizedList(new LinkedList<ISListener>());
        Worker.addMsgSListener(this);
    }

    /**
     * function to handle incoming msg interesting for
     *
     * @param event the kind of msg /event
     */
    public void HandleSEvent(Event event) {

        if ((Switcher == event.getEvent()) && (event.getParentAddress().contains(parent.getAddress()))) {
            final Switch sw = this;
            for (int i = 0; i < listeners.size(); i++) {
                final ISListener l = listeners.get(i);
                new Thread("Switch is Pressed") {
                    public void run() {
                        l.SwitchPressed(sw);
                    }
                }.start();
            }
        }
    }

    /**
     * method to ask for particular switch
     *
     * @param S switches id
     */
    private void askSwitch(int S) {
        if (S == 1) {
            synchronized (parent) {
                DataSender.getInstance().send(parent.getAddress(), parent.getPort(), SWITCH, ON, S);
            }
        } else if (S == 2) {
            synchronized (parent) {
                DataSender.getInstance().send(parent.getAddress(), parent.getPort(), SWITCH, ON, S);
            }
        } else if (S == 3) {
            synchronized (parent) {
                DataSender.getInstance().send(parent.getAddress(), parent.getPort(), SWITCH, ON, S);
            }
        } else if (S == 4) {
            synchronized (parent) {
                DataSender.getInstance().send(parent.getAddress(), parent.getPort(), SWITCH, ON, S);
            }
        } else if (S == 5) {
            synchronized (parent) {
                DataSender.getInstance().send(parent.getAddress(), parent.getPort(), SWITCH, ON, S);
            }
        } else if (S == 6) {
            synchronized (parent) {
                DataSender.getInstance().send(parent.getAddress(), parent.getPort(), SWITCH, ON, S);
            }
        } else if (S == 7) {
            synchronized (parent) {
                DataSender.getInstance().send(parent.getAddress(), parent.getPort(), SWITCH, ON, S);
            }
        } else if (S == 8) {
            synchronized (parent) {
                DataSender.getInstance().send(parent.getAddress(), parent.getPort(), SWITCH, ON, S);
            }
        } else if (S == 9) {
            synchronized (parent) {
                DataSender.getInstance().send(parent.getAddress(), parent.getPort(), SWITCH, ON, S);
            }
        } else if (S == 10) {
            synchronized (parent) {
                DataSender.getInstance().send(parent.getAddress(), parent.getPort(), SWITCH, ON, S);
            }
        } else if (S == 11) {
            synchronized (parent) {
                DataSender.getInstance().send(parent.getAddress(), parent.getPort(), SWITCH, ON, S);
            }
        } else if (S == 12) {
            synchronized (parent) {
                DataSender.getInstance().send(parent.getAddress(), parent.getPort(), SWITCH, ON, S);
            }
        } else if (S == 13) {
            synchronized (parent) {
                DataSender.getInstance().send(parent.getAddress(), parent.getPort(), SWITCH, ON, S);
            }
        }


    }

    /**
     * method to reset msg for particular switch
     *
     * @param S switches id
     */
    private void resetSwitch(int S) {
        if (S == 1) {
            synchronized (parent) {
                DataSender.getInstance().send(parent.getAddress(), parent.getPort(), SWITCH, OFF, S);
            }
        } else if (S == 2) {
            synchronized (parent) {
                DataSender.getInstance().send(parent.getAddress(), parent.getPort(), SWITCH, OFF, S);
            }
        } else if (S == 3) {
            synchronized (parent) {
                DataSender.getInstance().send(parent.getAddress(), parent.getPort(), SWITCH, OFF, S);
            }
        } else if (S == 4) {
            synchronized (parent) {
                DataSender.getInstance().send(parent.getAddress(), parent.getPort(), SWITCH, OFF, S);
            }
        } else if (S == 5) {
            synchronized (parent) {
                DataSender.getInstance().send(parent.getAddress(), parent.getPort(), SWITCH, OFF, S);
            }
        } else if (S == 6) {
            synchronized (parent) {
                DataSender.getInstance().send(parent.getAddress(), parent.getPort(), SWITCH, OFF, S);
            }
        } else if (S == 7) {
            synchronized (parent) {
                DataSender.getInstance().send(parent.getAddress(), parent.getPort(), SWITCH, OFF, S);
            }
        } else if (S == 8) {
            synchronized (parent) {
                DataSender.getInstance().send(parent.getAddress(), parent.getPort(), SWITCH, OFF, S);
            }
        } else if (S == 9) {
            synchronized (parent) {
                DataSender.getInstance().send(parent.getAddress(), parent.getPort(), SWITCH, OFF, S);
            }
        } else if (S == 10) {
            synchronized (parent) {
                DataSender.getInstance().send(parent.getAddress(), parent.getPort(), SWITCH, OFF, S);
            }
        } else if (S == 11) {
            synchronized (parent) {
                DataSender.getInstance().send(parent.getAddress(), parent.getPort(), SWITCH, OFF, S);
            }
        } else if (S == 12) {
            synchronized (parent) {
                DataSender.getInstance().send(parent.getAddress(), parent.getPort(), SWITCH, OFF, S);
            }
        } else if (S == 13) {
            synchronized (parent) {
                DataSender.getInstance().send(parent.getAddress(), parent.getPort(), SWITCH, OFF, S);
            }
        }
    }

    /**
     * method to add listener
     *
     * @param who the listener
     */
    public synchronized void addListener(ISListener who) {
        if (!listeners.contains(who))
            listeners.add(who);
        if (listeners.size() == 1)
            askSwitch(this.Switcher);

    }

    /**
     * method to remove listener
     *
     * @param who the listener
     */
    public synchronized void removeListener(ISListener who) {
        listeners.remove(who);
        if (listeners.size() == 0) {
            resetSwitch(this.Switcher);
        }

    }


}
