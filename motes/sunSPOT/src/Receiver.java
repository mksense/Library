import com.sun.spot.util.Queue;

import javax.microedition.io.Connector;
import javax.microedition.io.Datagram;
import javax.microedition.io.DatagramConnection;
import java.io.IOException;

public class Receiver extends Thread {

    private Queue myQueue;


    /*constructor of the receiver*/

    public Receiver(Queue queue) {
        this.myQueue = queue;
    }


    public void run() {
        //Setting up the Datagram Connection
        DatagramConnection dgConnection = null;
        try {
            //Open Datagram Connection on port 37
            dgConnection = (DatagramConnection) Connector.open("radiogram://:37");

        } catch (IOException e) {
            System.out.println("Could not open radiogram receiver connection");
            e.printStackTrace();
            return;
        }

        while (true) {
            try {
                Datagram dg = dgConnection.newDatagram(dgConnection.getMaximumLength());
                //Ensures that the next read or write operation will read/write from the start of the radiogram
                dg.reset();
                //Receive a Datagram
                dgConnection.receive(dg);
                //put incoming datagram in queue
                myQueue.put(dg);
            } catch (IOException e) {
                System.out.println("Nothing received");
            }
        }
    }


}
