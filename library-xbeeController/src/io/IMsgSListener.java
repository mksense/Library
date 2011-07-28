package io;

import java.util.EventListener;
/**
 * interface to Listen for switch incoming events
 */
public interface IMsgSListener extends EventListener {

    public void HandleSEvent(Event ev);
}
