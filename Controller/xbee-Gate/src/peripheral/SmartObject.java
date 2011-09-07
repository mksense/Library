package peripheral;

import com.rapplogic.xbee.api.XBeeAddress16;

public class SmartObject {
    public XBeeAddress16 address;
    public int port;


    public SmartObject(XBeeAddress16 add, int p) {
        address = add;
        port = p;
    }


    public XBeeAddress16 getAddress() {
        return address;

    }

    public int getPort() {

        return port;
    }

}
