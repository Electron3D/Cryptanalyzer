package com.company.managers;

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
}
