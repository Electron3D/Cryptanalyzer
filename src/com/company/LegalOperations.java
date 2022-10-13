package com.company;

import java.util.ArrayList;
import java.util.List;

public enum LegalOperations {
    ENCODE("encode"),
    DECODE("decode"),
    BRUTE_FORCE("bruteForce");

    private final String OPERATION;

    LegalOperations(String operation) {
        this.OPERATION = operation;
    }

    public String getOperation() {
        return OPERATION;
    }
    public static List<String> getOperations() {
        List<String> result = new ArrayList<>();
        for (LegalOperations operation : LegalOperations.values()) {
            result.add(operation.getOperation());
        }
        return result;
    }
    public static String legalOperationsToString() {
        StringBuilder result = new StringBuilder();
        for (String operation : LegalOperations.getOperations()) {
            result.append(operation).append(", ");
        }
        return result.delete(result.length() - 2, result.length()).append(".").toString();
    }
}
