package org.iammikrostoritve.entity;

import org.bson.types.ObjectId;

public interface GrpcEntity<T, R> {
    T fromProto(R proto);
    R toProto();
}
