package de.hannes.datadogpoc.exceptions;

public class ClaimNotFoundException extends RuntimeException{
    public ClaimNotFoundException(Long id) {
        super("Could not find claim " + id);
    }

}
