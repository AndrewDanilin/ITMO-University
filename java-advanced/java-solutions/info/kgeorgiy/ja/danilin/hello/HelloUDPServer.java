package info.kgeorgiy.ja.danilin.hello;

import info.kgeorgiy.java.advanced.hello.HelloServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class HelloUDPServer implements HelloServer {
    private static final String DEFAULT_RESPONSE_MESSAGE = "Hello, ";
    private static final int SERVICE_TIMEOUT = 200;

    private ExecutorService receiversService;
    private DatagramSocket datagramSocket;

    @Override
    public void start(final int port, final int threads) {
        receiversService = Executors.newFixedThreadPool(threads);

        try {
            datagramSocket = new DatagramSocket(port);
            for (int i = 0; i < threads; i++) {
                receiversService.submit(() -> receive(datagramSocket));
            }

        } catch (final SocketException e) {
            System.err.println("Failed to open datagram socket: " + e.getMessage());
        }
    }

    private static void receive(final DatagramSocket datagramSocket) {
        try {
            final DatagramPacket packetToReceive = Utils.createPacketForReceive(datagramSocket.getReceiveBufferSize());

            while (!datagramSocket.isClosed()) {
                try {
                    datagramSocket.receive(packetToReceive);
                } catch (final IOException e) {
                    System.err.println("Failed to receive packet: " + e.getMessage());
                    continue;
                }

                final String responseMessage = String.format(
                        "%s%s",
                        DEFAULT_RESPONSE_MESSAGE,
                        Utils.extractPacket(packetToReceive)
                );

                // :NOTE: Новый
                final DatagramPacket packetToSend = Utils.createPacketForSend(
                        responseMessage,
                        packetToReceive.getAddress(),
                        packetToReceive.getPort()
                );

                try {
                    datagramSocket.send(packetToSend);
                } catch (final IOException ignored) {
                    // :NOTE: Вывод
                }
            }
        } catch (final SocketException e) {
            System.err.println("Something went wrong: " + e.getMessage());
        }
    }

    @Override
    public void close() {
        datagramSocket.close();
        receiversService.shutdownNow();
        try {
            // :NOTE: Очень мало
            if (!receiversService.awaitTermination(SERVICE_TIMEOUT, TimeUnit.MILLISECONDS)) {
                System.out.println("Failed to shutdown threads");
            }
        } catch (final InterruptedException e) {
            System.err.println("Interrupted: " + e.getMessage());
        }
    }

    public static void main(final String[] args) {
        if (!argsAreValid(args)) {
            System.out.println(String.join(
                    System.lineSeparator(),
                    "Arguments aren't valid",
                    "Usage:",
                    "1. number of port, which requests accept on",
                    "2. number of parallel threads, which handle requests"
            ));
            new HelloUDPServer().start(
                    Integer.parseInt(args[0]),
                    Integer.parseInt(args[1])
            );
        }
    }

    private static boolean argsAreValid(final String[] args) {
        if (args == null || args.length != 2) {
            return false;
        }

        for (final String arg : args) {
            if (arg == null) {
                return false;
            }
        }

        return true;
    }
}
