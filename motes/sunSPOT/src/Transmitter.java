import com.sun.spot.util.Queue;

/**
 * transmits msg to controller/host
 */
public class Transmitter extends Thread {
    private Queue messageQueue;
    private Queue fMessageQueue;

    public Transmitter(Queue q, Queue fq) {
        this.messageQueue = q;
        fMessageQueue = fq;
    }


    public void run() {

        while (true) {
            if (!(messageQueue.isEmpty())) {
                Message msg = (Message) messageQueue.get();
                DataSender.getInstance().send(msg.getAddress(), msg.getID(), msg.getAction(), msg.getValue());
                msg.flashMsg();
                fMessageQueue.put(msg);
            }
        }
    }
}
