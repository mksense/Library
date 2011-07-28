import com.rapplogic.xbee.api.XBeeAddress16;
import eu.mksense.XBeeRadio;
import io.Worker;
import peripheral.Arduino;
import peripheral.SunSPOT;

import java.util.Hashtable;


public class Sensors {

    //holds the only instance of this class
    private static Sensors myInstance = null;

    //holds the sunSPOTS
    private static Hashtable<String, SunSPOT> sunSPOT;

    private static Hashtable<String, Arduino> arduino;

    static{
        sunSPOT = new Hashtable<String, SunSPOT>(10, 10);

        arduino = new Hashtable<String, Arduino>(10, 10);
    }

    /**
     * constructor
     */
    private Sensors() {
        try {
            XBeeRadio.getInstance().open("/dev/ttyUSB0", 9600);
        } catch (Exception e) {
            System.out.println("Could not open connection with XBEE\n");
        }
        (new Worker()).start();


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
    public synchronized void addSPOT(XBeeAddress16 address, int port) {
        String add = Integer.toHexString(address.get16BitValue());
        add = add.toUpperCase();
        if (sunSPOT.containsKey(add)) {
            System.out.println("The sunSPOT already exists\n");
            return;
        }
        sunSPOT.put(add, new SunSPOT(address, port));
    }

    public synchronized void addArduino(XBeeAddress16 address, int port) {

        String add = Integer.toHexString(address.get16BitValue());
        add = add.toUpperCase();
        if (arduino.containsKey(add)) {
            System.out.println("The Arduino board exists in your list\n");
            return;
        }
        arduino.put(add, new Arduino(address, port));
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
