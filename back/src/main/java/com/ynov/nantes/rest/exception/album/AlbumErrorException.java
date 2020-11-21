package com.ynov.nantes.rest.exception.album;

public class AlbumErrorException extends RuntimeException {

    public AlbumErrorException(String message) {
        super("There was an error on Object Album : " + message);
    }

}
