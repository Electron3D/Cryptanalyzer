package com.company;

import com.company.coders.BruteForceDecoder;
import com.company.coders.Coder;
import com.company.managers.AlphabetManager;
import com.company.managers.IOManager;
import com.company.managers.LegalOperations;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Cli {
    public static void start(String[] args) {
        init(args);
        process(args);
    }
    private static void init(String[] args) {
        if (args.length == 3) {
            if (!isOperationLegal(args[0])) {
                System.out.printf("Wrong operation.%n" +
                        "List of legal operations: %s%n" +
                        "Arguments pattern: \"operation filePath key/filePathForStatisticAnalysis\".%n" +
                        "Order of arguments is important!%n", legalOperationsToString());
                throw new IllegalArgumentException();
            }
            if (isPathWrong(args[1])) {
                System.out.println("File path is wrong or input file doesn't exist.");
                throw new IllegalArgumentException();
            }
            if (args[0].equals(LegalOperations.BRUTE_FORCE.getOperation())) {
                if (isPathWrong(args[2])) {
                    System.out.println("File path for statistic analysis is wrong or file doesn't exist.");
                    throw new IllegalArgumentException();
                }
            } else {
                if (!isKeyLegal(args[2])) {
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
        Path path = Path.of(args[1]);
        IOManager ioManager = new IOManager();
        AlphabetManager alphabetManager = AlphabetManager.getInstance();
        List<String> text = ioManager.read(path);
        List<String> modText;
        int key;
        if (args[0].equals(LegalOperations.BRUTE_FORCE.getOperation())) {
            List<String> exampleText = ioManager.read(Path.of(args[2]));
            modText = BruteForceDecoder.forceDecode(text, exampleText, alphabetManager);
            key = BruteForceDecoder.getEngKey();
        } else {
            key = Integer.parseInt(args[2]);
            if (args[0].equals(LegalOperations.ENCODE.getOperation())) {
                modText = Coder.encode(text, key);
            } else {
                modText = Coder.decode(text, key);
            }
        }
        ioManager.write(modText, path, args[0], key);
    }

    private static boolean isOperationLegal(String arg) {
        int count = 0;
        for (LegalOperations operation : LegalOperations.values()) {
            if (arg.equals(operation.getOperation())) {
                count++;
            }
        }
        return count == 1;
    }

    private static boolean isPathWrong(String path) {
        if (path.substring(path.lastIndexOf(".")).equals(".txt")) {
            Path path1 = Path.of(path);
            return !Files.exists(path1);
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

    private static String legalOperationsToString() {
        StringBuilder result = new StringBuilder();
        for (LegalOperations operation : LegalOperations.values()) {
            result.append(operation.getOperation()).append(", ");
        }
        return result.delete(result.length() - 2, result.length()).append(".").toString();
    }
}