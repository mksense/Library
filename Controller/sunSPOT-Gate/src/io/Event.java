package io;

import java.util.EventObject;


//class to transfer messages to observers
public class Event extends EventObject {

    private int event;
    private int iValue;
    private String parentAddress;

    public Event(Object source, int ev, String add) {
        super(source);
        event = ev;
        parentAddress = add;

    }

    public Event(Object source, int ev, int val, String add) {
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

    public String getParentAddress() {
        return parentAddress;
    }

}
