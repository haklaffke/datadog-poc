package de.hannes.datadogpoc.exceptions;

public class DamageNotFoundException extends RuntimeException{
    public DamageNotFoundException(Long id) {
        super("Could not find damage " + id);
    }

}
