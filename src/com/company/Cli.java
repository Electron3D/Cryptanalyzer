package com.company;

import com.company.coders.BruteForceDecoder;
import com.company.coders.Coder;
import com.company.managers.AlphabetManager;
import com.company.managers.IOManager;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static com.company.LegalOperations.legalOperationsToString;

public class Cli {
    public static void start(String[] args) {
        checkArgs(args);
        process(args);
    }
    private static void checkArgs(String[] args) {
        if (args.length == 3) {
            String operation = args[0];
            String srcPathString = args[1];
            String keyOrPathToReference = args[2];
            if (!isOperationLegal(operation)) {
                System.out.printf("Wrong operation.%n" +
                        "List of legal operations: %s%n" +
                        "Arguments pattern: \"operation filePath key/filePathForStatisticAnalysis\".%n" +
                        "Order of arguments is important!%n", legalOperationsToString());
                throw new IllegalArgumentException();
            }
            if (isPathWrong(srcPathString)) {
                System.out.println("File path is wrong or input file doesn't exist.");
                throw new IllegalArgumentException();
            }
            if (LegalOperations.BRUTE_FORCE.getOperation().equals(operation)) {
                if (isPathWrong(keyOrPathToReference)) {
                    System.out.println("File path for statistic analysis is wrong or file doesn't exist.");
                    throw new IllegalArgumentException();
                }
            } else {
                if (!isKeyLegal(keyOrPathToReference)) {
                    System.out.println("This key isn't allowed. \n Key should be integer number.");
                    throw new IllegalArgumentException();
                }
            }
        } else {
            System.out.printf("Incorrect number of arguments.%nNeeded: 3 Found: %s%n" +
                    "Arguments pattern: \"operation filePath key(OR filePathForStatisticAnalysis for bruteForce)\".%n" +
                    "Order of arguments is important!%n", args.length);
            throw new IllegalArgumentException();
        }
    }

    public static void process(String[] args) {
        String operation = args[0];
        String srcPathString = args[1];
        String keyOrPathToReference = args[2];
        Path srcPath = Path.of(srcPathString);
        IOManager ioManager = new IOManager();
        AlphabetManager alphabetManager = AlphabetManager.getInstance();
        List<String> srcText = ioManager.read(srcPath);
        List<String> modText;
        int key;
        if (LegalOperations.BRUTE_FORCE.getOperation().equals(operation)) {
            List<String> referenceText = ioManager.read(Path.of(keyOrPathToReference));
            modText = BruteForceDecoder.forceDecode(srcText, referenceText, alphabetManager);
            key = BruteForceDecoder.getKey();
        } else {
            key = Integer.parseInt(keyOrPathToReference);
            if (LegalOperations.ENCODE.getOperation().equals(operation)) {
                modText = Coder.encode(srcText, key);
            } else {
                modText = Coder.decode(srcText, key);
            }
        }
        ioManager.write(modText, srcPath, operation, key);
    }

    private static boolean isOperationLegal(String arg) {
        return LegalOperations.getOperations().contains(arg);
    }

    //todo: check is directory first
    private static boolean isPathWrong(String pathString) {
        if (pathString.substring(pathString.lastIndexOf(".")).equals(".txt")) {
            Path path = Path.of(pathString);
            return !Files.exists(path);
        } else {
            return true;
        }
    }

    private static boolean isKeyLegal(String arg) {
        try {
            Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}