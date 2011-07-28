package io;

import java.util.EventListener;

/**
 * interface implements by classes interested in switch incoming events
 */
public interface IMsgSListener extends EventListener {

    public void HandleSEvent(Event ev);
}
