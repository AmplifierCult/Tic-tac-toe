package com.company;

public interface Validator {
    boolean validate(String input);
    String getErrorMessage(String input);
}
