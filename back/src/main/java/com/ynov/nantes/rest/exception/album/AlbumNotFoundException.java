package com.ynov.nantes.rest.exception.album;

public class AlbumNotFoundException extends RuntimeException {

    public AlbumNotFoundException(int id) {
        super("Album #" + id + "Not found");
    }

}
