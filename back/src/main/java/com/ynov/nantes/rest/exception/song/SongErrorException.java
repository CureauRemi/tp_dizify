package com.ynov.nantes.rest.exception.song;

public class SongErrorException extends RuntimeException{
    public SongErrorException(String message) {
        super("There was an error on Object Song : " + message);
    }
}
