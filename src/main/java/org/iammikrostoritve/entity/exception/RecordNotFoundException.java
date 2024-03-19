package org.iammikrostoritve.entity.exception;

public class RecordNotFoundException extends ItemNotFoundException {
    public RecordNotFoundException(String id) {
        super("Record with id " + id + "Not found");
    }
}
