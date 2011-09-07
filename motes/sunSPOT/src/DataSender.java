import javax.microedition.io.Connector;
import javax.microedition.io.Datagram;
import javax.microedition.io.DatagramConnection;
import java.io.IOException;


public class DataSender {

    /**
     * static instance(ourInstance) initialized as null.
     */
    private static DataSender ourInstance = null;

    /**
     * Private constructor suppresses generation of a (public) default constructor.
     */
    private DataSender() {
        // Does nothing
    }


    public static DataSender getInstance() {
        synchronized (DataSender.class) {
            if (ourInstance == null) {
                ourInstance = new DataSender();
            }
        }
        return ourInstance;
    }


    public void send(final String targetAddress, final int id, final int action, final int s) {

        try {
            final  DatagramConnection dgConnection = (DatagramConnection) Connector.open("radiogram://" + targetAddress + ":37");

            final Datagram dg = dgConnection.newDatagram(dgConnection.getMaximumLength());

            // We send the message (UTF encoded)
            dg.reset();

            dg.write(id);
            dg.write(action);
            dg.writeInt(s);

            //Send Datagram
            dgConnection.send(dg);

            //Close the connection
            dgConnection.close();

        } catch (IOException ex) {
            System.out.println("Could not open radiogram connection");
		ex.printStackTrace();
        }

    }

}
