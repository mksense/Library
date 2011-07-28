/**
 * messages to send
 */
public class Message {
    protected String Address;
    protected int id;
    protected int action;
    protected int value;


    public Message() {
    }


    public void set(String add, int a, int b, int c) {
        Address = add;
        id = a;
        action = b;
        value = c;
    }

    /**
     * reset msg
     */
    public void flashMsg() {
        Address = null;
        id = -1;
        action = -1;
        value = -1;

    }

    public String getAddress() {
        return this.Address;
    }

    public int getID() {
        return this.id;
    }

    public int getAction() {
        return this.action;
    }

    public int getValue() {
        return this.value;
    }

}
