package com.ynov.nantes.rest.exception.song;

public class SongNotFoundException extends RuntimeException {
    public SongNotFoundException() {
        super("Song Not Found");
    }
}
