package io;

import com.rapplogic.xbee.api.XBeeAddress16;

import java.util.EventObject;

/**
 * class to pass events to observers
 */

public class Event extends EventObject {

    private int event;
    private int iValue;
    private XBeeAddress16 parentAddress;

    public Event(Object source, int ev, XBeeAddress16 add) {
        super(source);
        event = ev;
        parentAddress = add;

    }

    public Event(Object source, int ev, int val, XBeeAddress16 add) {
        super(source);
        event = ev;
        iValue = val;
        parentAddress = add;
    }


    public int getEvent() {
        return event;
    }

    public int getIValue() {
        return iValue;
    }

    public XBeeAddress16 getParentAddress() {
        return parentAddress;
    }

}
