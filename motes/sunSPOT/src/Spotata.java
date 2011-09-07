import com.sun.spot.peripheral.radio.LowPan;
import com.sun.spot.peripheral.radio.shrp.SingleHopManager;
import com.sun.spot.util.Queue;

import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

/**
 * main class
 */
public class Spotata extends MIDlet {
    /**
     * queue for incoming datagram
     */
    private Queue messageQueue = new Queue();

    protected void destroyApp(final boolean b) throws MIDletStateChangeException {
    }

    protected void pauseApp() {
    }

    protected void startApp() throws MIDletStateChangeException {
        LowPan.getInstance().setRoutingManager(new SingleHopManager());

        /*receiver*/
        (new Receiver(messageQueue)).start();
        /*handler/worker*/
        (new Worker(messageQueue)).start();
    }
}


