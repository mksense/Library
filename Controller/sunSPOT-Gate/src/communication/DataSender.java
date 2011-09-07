package communication;


import com.sun.spot.io.j2me.radiogram.RadiogramConnection;

import javax.microedition.io.Connector;
import javax.microedition.io.Datagram;
import java.io.IOException;

/**
 * class establishing the communication part
 */
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

    /**
     * DataSender is loaded on the first execution of DataSender.getInstance()
     * or the first access to DataSender.ourInstance, not before.
     *
     * @return ourInstance
     */
    public static DataSender getInstance() {
        synchronized (DataSender.class) {
            if (ourInstance == null) {
                ourInstance = new DataSender();
            }
        }
        return ourInstance;
    }

    public void send(final String targetAddress, final int PORT, final int id, final int action, final int led, final int red, final int green, final int blue) {

        try {
            final RadiogramConnection dgConnection = (RadiogramConnection) Connector.open("radiogram://" + targetAddress + ":" + PORT);

            final Datagram dg = dgConnection.newDatagram(dgConnection.getMaximumLength());

            dg.reset();

            //Write Data to Datagram
            dg.write(id);
            dg.write(action);
            dg.write(led);
            dg.write(red);
            dg.write(green);
            dg.write(blue);


            //Send Datagram
            dgConnection.send(dg);

            //Close the connection
            dgConnection.close();

        } catch (IOException ex) {
            System.out.println("Could not open radiogram connection");
        } finally {
            return;
        }

    }

    public void send(final String targetAddress, final int PORT, final int id, final int action, final int ledA, final int ledB, final int red, final int green, final int blue) {

        try {
            final RadiogramConnection dgConnection = (RadiogramConnection) Connector.open("radiogram://" + targetAddress + ":" + PORT);

            final Datagram dg = dgConnection.newDatagram(dgConnection.getMaximumLength());

            dg.reset();

            //Write Data to Datagram
            dg.write(id);
            dg.write(action);
            dg.write(ledA);
            dg.write(ledB);
            dg.write(red);
            dg.write(green);
            dg.write(blue);


            //Send Datagram
            dgConnection.send(dg);

            //Close the connection
            dgConnection.close();

        } catch (IOException ex) {
            System.out.println("Could not open radiogram connection");
        }
    }

    public void send(final String targetAddress, final int PORT, final int id, final int led) {

        try {
            final RadiogramConnection dgConnection = (RadiogramConnection) Connector.open("radiogram://" + targetAddress + ":" + PORT);

            final Datagram dg = dgConnection.newDatagram(dgConnection.getMaximumLength());

            dg.reset();

            //Write Data to Datagram
            dg.write(id);
            dg.write(led);

            //Send Datagram
            dgConnection.send(dg);

            //Close the connection
            dgConnection.close();

        } catch (IOException ex) {
            System.out.println("Could not open radiogram connection");
        }

    }

    public void send(final String targetAddress, final int PORT, final int id, final int ledA, final int ledB) {

        try {
            final RadiogramConnection dgConnection = (RadiogramConnection) Connector.open("radiogram://" + targetAddress + ":" + PORT);

            final Datagram dg = dgConnection.newDatagram(dgConnection.getMaximumLength());

            dg.reset();

            //Write Data to Datagram
            dg.write(id);
            dg.write(ledA);
            dg.write(ledB);


            //Send Datagram
            dgConnection.send(dg);

            //Close the connection
            dgConnection.close();

        } catch (IOException ex) {
            System.out.println("Could not open radiogram connection");
        }

    }


    public void send(final String targetAddress, final int PORT, final int id, final int a, final int b, final int c) {

        try {
            final RadiogramConnection dgConnection = (RadiogramConnection) Connector.open("radiogram://" + targetAddress + ":" + PORT);

            final Datagram dg = dgConnection.newDatagram(dgConnection.getMaximumLength());

            dg.reset();

            dg.write(id);
            dg.write(a);
            dg.write(b);
            dg.write(c);

            dgConnection.send(dg);

            //Close the connection
            dgConnection.close();

        } catch (IOException ex) {
            System.out.println("Could not open radiogram connection");
        }

    }

    public void send(final String targetAddress, final int PORT, final int id, final int action, final int b, final int c, final int time) {

        try {
            final RadiogramConnection dgConnection = (RadiogramConnection) Connector.open("radiogram://" + targetAddress + ":" + PORT);

            final Datagram dg = dgConnection.newDatagram(dgConnection.getMaximumLength());

            dg.reset();

            dg.write(id);
            dg.write(action);
            dg.writeInt(b);
            dg.writeInt(c);
            dg.writeInt(time);
            //Send Datagram
            dgConnection.send(dg);

            //Close the connection
            dgConnection.close();

        } catch (IOException ex) {
            System.out.println("Could not open radiogram connection");
        }

    }

}
