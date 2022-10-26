package info.kgeorgiy.ja.danilin.hello;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class Utils {
    public static DatagramPacket createPacketForSend(String requestMessage, InetAddress inetAddress, int port) {
        byte[] bytes = requestMessage.getBytes(StandardCharsets.UTF_8);
        return new DatagramPacket(bytes, bytes.length, inetAddress, port);
    }

    public static DatagramPacket createPacketForReceive(int length) {
        return new DatagramPacket(new byte[length], length);
    }

    public static String extractPacket(DatagramPacket datagramPacket) {
        return new String(
                datagramPacket.getData(),
                datagramPacket.getOffset(),
                datagramPacket.getLength(),
                StandardCharsets.UTF_8
        );
    }
}
