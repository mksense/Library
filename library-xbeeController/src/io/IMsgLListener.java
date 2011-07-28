package io;

import java.util.EventListener;

/**
 * interface to Listen for Light incoming events
 */
public interface IMsgLListener extends EventListener {

    public void HandleLEvent(Event ev);
}
