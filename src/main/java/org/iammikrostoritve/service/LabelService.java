package org.iammikrostoritve.service;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import org.iammikrostoritve.*;
import org.iammikrostoritve.repository.LabelRepository;
import org.jboss.logging.Logger;
import org.iammikrostoritve.entity.Label;

@GrpcService
public class LabelService implements LabelGrpcService {
    private static final Logger log = Logger.getLogger(LabelService.class);

    @Inject
    LabelRepository labelRepository;

    @Override
    public Uni<LabelProto> getById(LabelIdProto request) {
        log.info("Received request to get label by id: " + request.getId());
        try {
            Label label = labelRepository.getById(request.getId());
            return GrpcResponse.single(label.toProto());
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new StatusRuntimeException(Status.NOT_FOUND.withDescription(e.getMessage()));
        }
    }

    @Override
    public Uni<Empty> insert(InsertLabelProto request) {
        log.info("Received request to insert label: " + request.getName());
        Label label = new Label().fromProto(request);
        labelRepository.insert(label);
        return GrpcResponse.empty();
    }

    @Override
    public Uni<Empty> delete(LabelIdProto request) {
        log.info("Received request to delete label by id: " + request.getId());
        try {
            labelRepository.delete(request.getId());
            return GrpcResponse.empty();
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new StatusRuntimeException(Status.NOT_FOUND.withDescription(e.getMessage()));
        }
    }

    @Override
    public Multi<LabelProto> getAll(Empty request) {
        log.info("Received request to get all labels");
        return GrpcResponse.stream(labelRepository.getAll().stream()
                .map(Label::toProto));
    }
}
