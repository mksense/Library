import java.util.Observable;


public class Between extends Observable {
    private int[] message;

    private static Between myInstance = null;


    public Between() {

        message = new int[6];


        (new SoundCreator()).start();

    }

    public synchronized static Between getInstance() {
        if (myInstance == null) {
            myInstance = new Between();
        }
        return myInstance;

    }

    public void setMessage(final int[] i) {
        message = i;

    }

    public void updateStatus() {
        this.setChanged();
        notifyObservers(message);
    }


}
