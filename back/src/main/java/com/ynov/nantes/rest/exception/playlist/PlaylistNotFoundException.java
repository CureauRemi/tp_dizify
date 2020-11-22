package com.ynov.nantes.rest.exception.playlist;

public class PlaylistNotFoundException extends RuntimeException{
    public PlaylistNotFoundException() {
        super("Playlist Not Found");
    }
}
