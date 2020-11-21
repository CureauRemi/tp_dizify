package com.ynov.nantes.rest.exception.artist;

public class ArtistErrorException extends RuntimeException {
    public ArtistErrorException(String errorMessage) {
        super("There was an error on Object Artist : " + errorMessage);
    }
}
