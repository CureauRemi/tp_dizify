package com.ynov.nantes.rest.exception.playlist;

public class PlaylistErrorException extends RuntimeException {
    public PlaylistErrorException(String errorMessage) {
        super("There was an error on Object Playlist : " + errorMessage);
    }
}
