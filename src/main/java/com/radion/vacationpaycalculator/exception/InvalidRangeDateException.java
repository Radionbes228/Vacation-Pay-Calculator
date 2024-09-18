package com.radion.vacationpaycalculator.exception;

public class InvalidRangeDateException extends IllegalArgumentException{
    public InvalidRangeDateException(String message) {
        super(message);
    }
}
