package peripheral;

public class SmartObject {
    public String address;
    public int port;


    public SmartObject(String add, int p) {
        address = add;
        port = p;
    }


    public String getAddress() {
        return address;

    }
    /*method to get the private field PORT*/

    public int getPort() {

        return port;
    }

}
