package org.iammikrostoritve.repository;

import org.bson.types.ObjectId;
import org.iammikrostoritve.entity.Label;

import jakarta.enterprise.context.ApplicationScoped;
import org.iammikrostoritve.entity.exception.LabelNotFoundException;

import java.util.List;

@ApplicationScoped
public class LabelRepository {

    public List<Label> getAll() {
        return Label.listAll();
    }

    public Label getById(String id) throws LabelNotFoundException {
        Label label = Label.findById(new ObjectId(id));
        if (label == null) {
            throw new LabelNotFoundException(id);
        }
        return label;
    }

    public void insert(Label label) {
        label.persist();
    }

    public void delete(String id) throws LabelNotFoundException {
        Label label = Label.findById(new ObjectId(id));
        if (label == null) {
            throw new LabelNotFoundException(id);
        }
        label.delete();
    }
}