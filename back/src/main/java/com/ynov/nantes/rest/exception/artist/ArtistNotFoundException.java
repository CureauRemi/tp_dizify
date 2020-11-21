package com.ynov.nantes.rest.exception.artist;

public class ArtistNotFoundException extends RuntimeException{
    public ArtistNotFoundException() {
        super("Artist Not Found");
    }
}
