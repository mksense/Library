package io;

import java.util.EventListener;

/**
 * interface implemented by classes interested in temperature incoming events
 */
public interface IMsgTListener extends EventListener {

    public void HandleTEvent(Event ev);
}
