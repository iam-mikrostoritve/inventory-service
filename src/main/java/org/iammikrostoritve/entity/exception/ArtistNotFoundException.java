package org.iammikrostoritve.entity.exception;

public class ArtistNotFoundException extends ItemNotFoundException {
    public ArtistNotFoundException(String id) {
        super("Artist with id " + id + "Not found");
    }
}
