package net.savagedev.vanishsync.common.messenger;

import net.savagedev.vanishsync.common.messenger.receiver.MessageReceiver;

public interface Messenger {
    void close();

    default void send(MessageReceiver receiver, byte[] data) {
        receiver.sendData(data);
    }
}
