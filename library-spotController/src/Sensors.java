/*this will be the top singleton class through which we will use each sensor{sunSPOT, arduino}*/

import com.sun.spot.peripheral.radio.LowPan;
import com.sun.spot.peripheral.radio.shrp.SingleHopManager;
import communication.Receiver;
import io.Worker;
import peripheral.Arduino;
import peripheral.SunSPOT;

import javax.microedition.io.Datagram;
import java.util.Hashtable;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Sensors {

    //holds the only instance of this class
    private static Sensors myInstance = null;
    //queue that holds incoming messages for sunSPOT
    private BlockingQueue<Datagram> incomingMSG;

    //holds the sunSPOTS
    private static Hashtable<String, SunSPOT> sunSPOT;

    private static Hashtable<String, Arduino> arduino;

    static {
        sunSPOT = new Hashtable<String, SunSPOT>(10, 10);

        arduino = new Hashtable<String, Arduino>(10, 10);
    }


    /**
     * constructor
     */
    private Sensors() {
        LowPan.getInstance().setRoutingManager(new SingleHopManager());

        incomingMSG = new LinkedBlockingQueue<Datagram>();

        (new Receiver(incomingMSG)).start();

        (new Worker(incomingMSG)).start();


    }

    /**
     * function to get the instance
     *
     * @return the one and only instance of this class
     */
    public synchronized static Sensors getInstance() {
        if (myInstance == null) {
            myInstance = new Sensors();
        }
        return myInstance;
    }

    /**
     * adds sunSPOT
     *
     * @param address the address of the remote sunSPOT
     * @param port    the port in which the server will listen
     */
    public synchronized void addSPOT(String address, int port) {
        if (sunSPOT.containsKey(address)) {
            System.out.println("The sunSPOT already exists\n");
            return;
        }
        sunSPOT.put(address, new SunSPOT(address, port));

    }

    public synchronized void addArduino(String address, int port) {
        if (arduino.containsKey(address)) {
            System.out.println("The Arduino board exists in your list\n");
            return;
        }
        arduino.put(address, new Arduino(address, port));
    }

    /**
     * get desired sunSPOT
     *
     * @param address the address of the remote
     * @return the required sunSPOT
     */
    public synchronized SunSPOT getSPOT(String address) {
        return sunSPOT.get(address);

    }

    public synchronized Arduino getArduino(String address) {
        return arduino.get(address);
    }

}
