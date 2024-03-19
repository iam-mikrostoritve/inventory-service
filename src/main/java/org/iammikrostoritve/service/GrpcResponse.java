package org.iammikrostoritve.service;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.iammikrostoritve.Empty;
import org.iammikrostoritve.entity.exception.ItemNotFoundException;


import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GrpcResponse {

    public static Uni<Empty> empty() {
        return Uni.createFrom().item(Empty.newBuilder().build());
    }

    public static <T> Uni<T> single(T item) {
        return Uni.createFrom().item(item);
    }

    public static <T> Multi<T> stream(Stream<T> items) {
        return Multi.createFrom().iterable(items.collect(Collectors.toList()));
    }
}