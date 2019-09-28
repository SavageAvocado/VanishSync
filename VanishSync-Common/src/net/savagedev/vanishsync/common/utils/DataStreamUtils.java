package net.savagedev.vanishsync.common.utils;

import java.io.*;
import java.util.function.Consumer;

public class DataStreamUtils {
    private DataStreamUtils() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    public static byte[] toByteArray(Consumer<DataOutput> consumer) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DataOutput output = new DataOutputStream(outputStream);
        consumer.accept(output);
        return outputStream.toByteArray();
    }

    public static DataInput newDataInput(byte[] message) {
        return new DataInputStream(new ByteArrayInputStream(message));
    }
}
