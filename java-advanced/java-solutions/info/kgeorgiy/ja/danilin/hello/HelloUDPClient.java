package info.kgeorgiy.ja.danilin.hello;

import info.kgeorgiy.java.advanced.hello.HelloClient;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class HelloUDPClient implements HelloClient {
    private static final int UDP_SOCKET_TIMEOUT = 1000;
    private static final int REQUEST_TIMEOUT = 1000;
    private static final String DEFAULT_REQUEST_FORMAT = "%s%d_%d";

    @Override
    public void run(final String host, final int port, final String prefix, final int threads, final int requests) {
        final ExecutorService sendersService = Executors.newFixedThreadPool(threads);

        try {
            final InetAddress inetAddress = InetAddress.getByName(host);
            for (int indexOfThread = 0; indexOfThread < threads; indexOfThread++) {
                sendersService.submit(senderAction(inetAddress, port, prefix, indexOfThread, requests));
            }
            terminate(sendersService, threads, requests);
        } catch (final UnknownHostException e) {
            System.err.println("Failed to get internet address by host: " + e.getMessage());
        }
    }

    private void terminate(final ExecutorService executorService, final int threads, final int requests) {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(threads * requests * REQUEST_TIMEOUT, TimeUnit.SECONDS)) {
                System.out.println("Failed to shutdown threads");
            }
        } catch (final InterruptedException e) {
            System.out.println("Interrupted: " + e.getMessage());
        }
    }

    private Runnable senderAction(
            final InetAddress inetAddress,
            final int port,
            final String prefix,
            final int indexOfThread,
            final int requests
    ) {
        return () -> {
          try (final DatagramSocket datagramSocket = new DatagramSocket()) {
              datagramSocket.setSoTimeout(UDP_SOCKET_TIMEOUT);
              for (int indexOfRequest = 0; indexOfRequest < requests; indexOfRequest++) {

                  final String requestMessage = String.format(DEFAULT_REQUEST_FORMAT, prefix, indexOfThread, indexOfRequest);

                  // :NOTE: Новые
                  final DatagramPacket packetToSend = Utils.createPacketForSend(requestMessage, inetAddress, port);
                  final DatagramPacket packetToReceive = Utils.createPacketForReceive(datagramSocket.getReceiveBufferSize());

                  System.out.println("Sending request: " + requestMessage);
                  sendAndReceive(requestMessage, datagramSocket, packetToSend, packetToReceive);
              }
          } catch (final SocketException e) {
              System.err.println("Failed to open datagram socket: " + e.getMessage());
          }
        };
    }

    private void sendAndReceive(
            final String requestInfo,
            final DatagramSocket datagramSocket,
            final DatagramPacket packetToSend,
            final DatagramPacket packetToReceive
    ) {
        while (true) {
            try {
                datagramSocket.send(packetToSend);
            } catch (final IOException e) {
                System.err.println("Failed to send packet: " + e.getMessage());
                continue;
            }

            try {
                datagramSocket.receive(packetToReceive);
            } catch (final IOException e) {
                System.err.println("Failed to receive packet: " + e.getMessage());
                continue;
            }

            final String responseMessage = Utils.extractPacket(packetToReceive);

            if (responseMessage.contains(requestInfo)) {
                System.out.println("Response " + responseMessage + " for " + requestInfo + " received");
                break;
            }
        }
    }

    public static void main(final String[] args) {
        if (!argsAreValid(args)) {
            System.out.println(String.join(
                    System.lineSeparator(),
                    "Argument aren't valid",
                    "Usage:",
                    "1. name or ip-address of server",
                    "2. number of port, which request to",
                    "3. prefix of requests",
                    "4. number of parallel threads, which send requests",
                    "5. number of requests in one thread"
            ));
        }
        new HelloUDPClient().run(
                args[0],
                Integer.parseInt(args[1]),
                args[2],
                Integer.parseInt(args[3]),
                Integer.parseInt(args[4])
        );
    }

    private static boolean argsAreValid(final String[] args) {
        if (args == null || args.length != 5) {
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
