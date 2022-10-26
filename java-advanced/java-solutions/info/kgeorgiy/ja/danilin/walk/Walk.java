package info.kgeorgiy.ja.danilin.walk;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Walk {
    public static void main(String[] args) {
        if (args == null || args.length != 2 || args[0] == null || args[1] == null) {
            System.err.println("Expected two arguments: First - input file, second - output file");
        } else {
            runWalk(args[0], args[1]);
        }
    }

    public static void runWalk(String inputFilename, String outputFilename) {
        Path inputFilePath;
        try {
            inputFilePath = Path.of(inputFilename);
        } catch (InvalidPathException e) {
            System.err.println("Wrong input filename: " + inputFilename);
            return;
        }

        Path outputFilePath;
        try {
            outputFilePath = Path.of(outputFilename);
        } catch (InvalidPathException e) {
            System.err.println("Wrong output filename: " + outputFilename);
            return;
        }

        try {
            if (outputFilePath.getParent() != null) {
                Files.createDirectories(outputFilePath.getParent());
            }
        } catch (IOException e) {
            System.err.println("Failed to create file directory");
            return;
        }

        try {
            try (BufferedReader reader = Files.newBufferedReader(inputFilePath)) {
                try (BufferedWriter writer = Files.newBufferedWriter(outputFilePath)) {
                    String currentFilename;
                    while ((currentFilename = reader.readLine()) != null) {
                        String hash = calculateHashSum(currentFilename);
                        writer.write(hash + " " + currentFilename);
                        writer.newLine();
                    }
                } catch (IOException e) {
                    System.err.println("Error while writing to output file: " + e.getMessage());
                }
            } catch (IOException e) {
                System.err.println("Error while reading input file: " + e.getMessage());
            }
        } catch (SecurityException | NoSuchAlgorithmException e) {
            System.err.println("Something went wrong: " + e.getMessage());
        }

    }

    public static String calculateHashSum(String filename) throws NoSuchAlgorithmException {
        try {
            Path currentPath = Path.of(filename);
            InputStream inputStream = Files.newInputStream(currentPath);
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            // :NOTE: buffer size to a const
            byte[] bytes = new byte[1024];
            int size;
            while ((size = inputStream.read(bytes)) >= 0) {
                messageDigest.update(bytes, 0, size);
            }
            return bytesToHexString(messageDigest.digest());
        } catch (InvalidPathException | IOException e) {
            // :NOTE: move to a const
            return String.format("%040x", 0);
        }
    }

    public static String bytesToHexString(byte[] bytes) {
        return String.format("%040x", new BigInteger(1, bytes)).toLowerCase();
    }
}
