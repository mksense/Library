package peripheral;

import eu.mksense.XBeeRadio;
import io.Event;
import io.IMsgSListener;
import io.PacketTypes;
import io.Worker;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class Switch implements IMsgSListener, PacketTypes {

    private int Switcher;
    private SmartObject parent = null;
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

        if ((Switcher == event.getEvent()) && (event.getParentAddress().equals(parent.getAddress()))) {
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
            try {
                XBeeRadio.getInstance().send(parent.getAddress(), parent.getPort(), new int[]{SWITCH, ON, S});
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (S == 2) {
            try {
                XBeeRadio.getInstance().send(parent.getAddress(), parent.getPort(), new int[]{SWITCH, ON, S});
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (S == 3) {
            try {
                XBeeRadio.getInstance().send(parent.getAddress(), parent.getPort(), new int[]{SWITCH, ON, S});
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (S == 4) {
            try {
                XBeeRadio.getInstance().send(parent.getAddress(), parent.getPort(), new int[]{SWITCH, ON, S});
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (S == 5) {
            try {
                XBeeRadio.getInstance().send(parent.getAddress(), parent.getPort(), new int[]{SWITCH, ON, S});
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (S == 6) {
            try {
                XBeeRadio.getInstance().send(parent.getAddress(), parent.getPort(), new int[]{SWITCH, ON, S});
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (S == 7) {
            try {
                XBeeRadio.getInstance().send(parent.getAddress(), parent.getPort(), new int[]{SWITCH, ON, S});
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (S == 8) {
            try {
                XBeeRadio.getInstance().send(parent.getAddress(), parent.getPort(), new int[]{SWITCH, ON, S});
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (S == 9) {
            try {
                XBeeRadio.getInstance().send(parent.getAddress(), parent.getPort(), new int[]{SWITCH, ON, S});
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (S == 10) {
            try {
                XBeeRadio.getInstance().send(parent.getAddress(), parent.getPort(), new int[]{SWITCH, ON, S});
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (S == 11) {
            try {
                XBeeRadio.getInstance().send(parent.getAddress(), parent.getPort(), new int[]{SWITCH, ON, S});
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (S == 12) {
            try {
                XBeeRadio.getInstance().send(parent.getAddress(), parent.getPort(), new int[]{SWITCH, ON, S});
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (S == 13) {
            try {
                XBeeRadio.getInstance().send(parent.getAddress(), parent.getPort(), new int[]{SWITCH, ON, S});
            } catch (Exception e) {
                e.printStackTrace();
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
            try {
                XBeeRadio.getInstance().send(parent.getAddress(), parent.getPort(), new int[]{SWITCH, OFF, S});
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (S == 2) {
            try {
                XBeeRadio.getInstance().send(parent.getAddress(), parent.getPort(), new int[]{SWITCH, OFF, S});
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (S == 3) {
            try {
                XBeeRadio.getInstance().send(parent.getAddress(), parent.getPort(), new int[]{SWITCH, OFF, S});
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (S == 4) {
            try {
                XBeeRadio.getInstance().send(parent.getAddress(), parent.getPort(), new int[]{SWITCH, OFF, S});
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (S == 5) {
            try {
                XBeeRadio.getInstance().send(parent.getAddress(), parent.getPort(), new int[]{SWITCH, OFF, S});
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (S == 6) {
            try {
                XBeeRadio.getInstance().send(parent.getAddress(), parent.getPort(), new int[]{SWITCH, OFF, S});
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (S == 7) {
            try {
                XBeeRadio.getInstance().send(parent.getAddress(), parent.getPort(), new int[]{SWITCH, OFF, S});
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (S == 8) {
            try {
                XBeeRadio.getInstance().send(parent.getAddress(), parent.getPort(), new int[]{SWITCH, OFF, S});
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (S == 9) {
            try {
                XBeeRadio.getInstance().send(parent.getAddress(), parent.getPort(), new int[]{SWITCH, OFF, S});
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (S == 10) {
            try {
                XBeeRadio.getInstance().send(parent.getAddress(), parent.getPort(), new int[]{SWITCH, OFF, S});
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (S == 11) {
            try {
                XBeeRadio.getInstance().send(parent.getAddress(), parent.getPort(), new int[]{SWITCH, OFF, S});
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (S == 12) {
            try {
                XBeeRadio.getInstance().send(parent.getAddress(), parent.getPort(), new int[]{SWITCH, OFF, S});
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (S == 13) {
            try {
                XBeeRadio.getInstance().send(parent.getAddress(), parent.getPort(), new int[]{SWITCH, OFF, S});
            } catch (Exception e) {
                e.printStackTrace();
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
