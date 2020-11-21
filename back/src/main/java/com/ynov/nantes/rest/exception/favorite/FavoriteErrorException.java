package com.ynov.nantes.rest.exception.favorite;

public class FavoriteErrorException extends RuntimeException {
    public FavoriteErrorException(String errorMessage) {
        super("There was an error on Object Favorite : " + errorMessage);
    }
}
