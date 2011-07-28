package io;

import com.rapplogic.xbee.api.wpan.RxResponse16;
import eu.mksense.MessageListener;
import eu.mksense.XBeeRadio;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Worker extends Thread implements MessageListener, PacketTypes {
    /*Queue to hold msg*/
    private BlockingQueue<RxResponse16> queueTo;
    /*List for listeners*/
    final private static List<IMsgSListener> listenerSList = Collections.synchronizedList(new LinkedList<IMsgSListener>());
    final private static List<IMsgLListener> listenerLList = Collections.synchronizedList(new LinkedList<IMsgLListener>());
    final private static List<IMsgTListener> listenerTList = Collections.synchronizedList(new LinkedList<IMsgTListener>());

    /**
     * constructor
     */
    public Worker() {
        this.queueTo = new LinkedBlockingQueue<RxResponse16>();
        XBeeRadio.getInstance().addMessageListener(37, this);

    }

    /**
     * callback
     * @param rxResponse16 incoming response
     */
    public void receive(RxResponse16 rxResponse16) {
        try {
            queueTo.put(rxResponse16);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * analyze msg and inform listeners
     */
    public void run() {

        while (true) {
            RxResponse16 MSG = null;
            if (!(queueTo.isEmpty())) {
                try {
                    MSG = queueTo.take();
                    int[] data = null;
                    int event = 0;
                    int value = 0;
                    if (MSG.getLength().getLength() > 0) {
                        data = MSG.getData();
                        if (data[0] == SWITCH) {
                            event = data[1];
                            if (data[5] != 0) {
                                notifySListeners(new Event(this, event, MSG.getRemoteAddress()));
                            }
                        } else if (data[0] == LIGHT_T) {
                            event = data[1];
                            value = takeValue(data[2], data[3], data[4], data[5]);
                            notifyLListeners(new Event(this, event, value, MSG.getRemoteAddress()));
                        } else if (data[0] == LIGHT_V) {
                            event = data[1];
                            value = takeValue(data[2], data[3], data[4], data[5]);
                            notifyLListeners(new Event(this, event, value, MSG.getRemoteAddress()));
                        } else if (data[0] == TEMP_V) {
                            event = data[1];
                            value = takeValue(data[2], data[3], data[4], data[5]);
                            notifyTListeners(new Event(this, event, value, MSG.getRemoteAddress()));
                        }else if(data[0]==TEMP_T){
                            event=data[1];
                            value=takeValue(data[2],data[3],data[4],data[5]);
                            notifyTListeners(new Event(this,event,value,MSG.getRemoteAddress()));
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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

    /**
     * function to construct int
     * @param a first most significant byte
     * @param b second most significant byte
     * @param c third most significant byte
     * @param d fourth most significant byte
     * @return   constructed integer
     */
    private int takeValue(int a, int b, int c, int d) {

        int v = a;
        v = v << 8;
        v += b;
        v = v << 8;
        v += c;
        v = v << 8;
        v += d;
        return v;
    }


}
