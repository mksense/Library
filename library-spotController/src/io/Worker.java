package io;

import javax.microedition.io.Datagram;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * class to handle incoming msg and inform listeners implements PacketTypes
 */
public class Worker extends Thread implements PacketTypes {
    /*Queue to hold msg*/
    private BlockingQueue<Datagram> queueTo;
    /*List for listeners*/
    final private static List<IMsgSListener> listenerSList = Collections.synchronizedList(new LinkedList<IMsgSListener>());
    final private static List<IMsgLListener> listenerLList = Collections.synchronizedList(new LinkedList<IMsgLListener>());
    final private static List<IMsgTListener> listenerTList = Collections.synchronizedList(new LinkedList<IMsgTListener>());

    /**
     * constructor
     *
     * @param queue queue that holds incoming msg
     */
    public Worker(BlockingQueue<Datagram> queue) {
        this.queueTo = queue;
    }

    /**
     * analyze msg and inform listeners
     */
    public void run() {

        while (true) {
            Datagram MSG = null;
            try {
                MSG = queueTo.take();
                int id = 0;
                int event = 0;
                int value = 0;
                if (MSG.getLength() > 0) {
                    try {
                        id = MSG.readUnsignedByte();
                        if (id == SWITCH) {
                            event = MSG.readUnsignedByte();
                            if (MSG.readInt() != 0) {
                                notifySListeners(new Event(this, event, MSG.getAddress()));
                            }
                        } else if (id == LIGHT_T) {
                            event = MSG.readUnsignedByte();
                            value = MSG.readInt();
                            notifyLListeners(new Event(this, event, value, MSG.getAddress()));
                        } else if (id == LIGHT_V) {
                            event = MSG.readUnsignedByte();
                            value = MSG.readInt();
                            notifyLListeners(new Event(this, event, value, MSG.getAddress()));
                        } else if (id == TEMP_V) {
                            event=MSG.readUnsignedByte();
                            value=MSG.readInt();
                            notifyTListeners(new Event(this,event,value, MSG.getAddress()));
                        } else if(id==TEMP_T) {
                              event=MSG.readUnsignedByte();
                             value=MSG.readInt();
                            notifyTListeners(new Event(this,event,value,MSG.getAddress()));
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * method to add listeners for msg
     *
     * @param swL listener
     */
    public static synchronized void addMsgSListener(IMsgSListener swL) {
        if (!listenerSList.contains(swL))
            listenerSList.add(swL);
    }

    public static synchronized void addMsgLListener(IMsgLListener swL) {
        if (!listenerLList.contains(swL))
            listenerLList.add(swL);
    }

    public static synchronized void addMsgTListener(IMsgTListener swL) {
        if (!listenerTList.contains(swL))
            listenerTList.add(swL);
    }


    /**
     * function to inform the listeners
     *
     * @param ev event/msg that happened
     */
    public static void notifySListeners(final Event ev) {
        new Thread() {
            public void run() {
                Object[] copy;
                synchronized (listenerSList) {
                    copy = listenerSList.toArray();
                }
                for (int i = 0; i < copy.length; i++) {
                    ((IMsgSListener) copy[i]).HandleSEvent(ev);
                }
            }
        }.start();
    }

    public static void notifyLListeners(final Event ev) {
        new Thread() {
            public void run() {
                Object[] copy;
                synchronized (listenerLList) {
                    copy = listenerLList.toArray();
                }
                for (int i = 0; i < copy.length; i++) {
                    ((IMsgLListener) copy[i]).HandleLEvent(ev);
                }
            }
        }.start();
    }

    public static void notifyTListeners(final Event ev) {
        new Thread() {
            public void run() {
                Object[] copy;
                synchronized (listenerTList) {
                    copy = listenerTList.toArray();
                }
                for (int i = 0; i < copy.length; i++) {
                    ((IMsgTListener) copy[i]).HandleTEvent(ev);
                }
            }
        }.start();
    }
}
