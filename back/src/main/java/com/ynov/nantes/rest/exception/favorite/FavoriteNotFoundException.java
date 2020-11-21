package com.ynov.nantes.rest.exception.favorite;

public class FavoriteNotFoundException extends  RuntimeException{
    public FavoriteNotFoundException(String errorMessage) {
        super("Favorite Not Found");
    }
}
