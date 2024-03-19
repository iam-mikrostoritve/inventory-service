package org.iammikrostoritve.entity.exception;

public class LabelNotFoundException extends ItemNotFoundException {
    public LabelNotFoundException(String id) {
        super("Label with id " + id + "Not found");
    }
}
