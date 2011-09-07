package io;


import java.util.EventListener;
/**
 * interface to Listen for temperature incoming events
 */
public interface IMsgTListener extends EventListener {

    public void HandleTEvent(Event ev);
}
