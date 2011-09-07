package io;

import java.util.EventListener;

/**
 * interface implements by classes interest to incoming Light events
 */
public interface IMsgLListener extends EventListener {

    public void HandleLEvent(Event ev);
}
