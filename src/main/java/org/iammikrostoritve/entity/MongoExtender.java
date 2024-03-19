package org.iammikrostoritve.entity;

import org.bson.types.ObjectId;

public interface MongoExtender {
    ObjectId getId();
    void setId(String id);
    void setId(ObjectId id);
}
