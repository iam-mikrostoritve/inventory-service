package org.iammikrostoritve.service;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import org.iammikrostoritve.*;
import org.iammikrostoritve.dto.InsertRecord;
import org.iammikrostoritve.entity.exception.ArtistNotFoundException;
import org.iammikrostoritve.entity.exception.LabelNotFoundException;
import org.iammikrostoritve.repository.RecordRepository;
import org.jboss.logging.Logger;
import org.iammikrostoritve.entity.Record;

@GrpcService
public class RecordService implements RecordGrpcService {
    private static final Logger log = Logger.getLogger(RecordService.class);

    @Inject
    RecordRepository recordRepository;

    @Override
    public Uni<RecordProto> getById(RecordIdProto request) {
        log.info("Received request to get record by id: " + request.getId());
        try {
            Record record = recordRepository.getById(request.getId());
            return GrpcResponse.single(record.toProto());
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new StatusRuntimeException(Status.NOT_FOUND.withDescription(e.getMessage()));
        }
    }

    @Override
    public Uni<Empty> insert(InsertRecordProto request) {
        log.info("Received request to insert record: " + request.getTitle());
        InsertRecord insertRecord = new InsertRecord().fromProto(request);
        try {
            recordRepository.insert(insertRecord);
            return GrpcResponse.empty();
        } catch (ArtistNotFoundException | LabelNotFoundException e) {
            log.info(e.getMessage());
            throw new StatusRuntimeException(Status.NOT_FOUND.withDescription(e.getMessage()));
        }
    }

    @Override
    public Uni<Empty> updateQuantity(UpdateQuantityRequestProto request) {
        log.info("Received request to update quantity: " + request.getId());
        try {
            recordRepository.updateQuantity(request.getQuantity(), request.getId());
            return GrpcResponse.empty();
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new StatusRuntimeException(Status.NOT_FOUND.withDescription(e.getMessage()));
        }
    }

    @Override
    public Uni<Empty> updatePrice(UpdatePriceRequestProto request) {
        log.info("Received request to update price: " + request.getId());
        try {
            recordRepository.updatePrice(request.getPrice(), request.getId());
            return GrpcResponse.empty();
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new StatusRuntimeException(Status.NOT_FOUND.withDescription(e.getMessage()));
        }
    }

    @Override
    public Uni<Empty> delete(RecordIdProto request) {
        log.info("Received request to delete record by id: " + request.getId());
        try {
            recordRepository.delete(request.getId());
            return GrpcResponse.empty();
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new StatusRuntimeException(Status.NOT_FOUND.withDescription(e.getMessage()));
        }
    }

    @Override
    public Multi<RecordProto> getAll(Empty request) {
        log.info("Received request to get all records");
        return GrpcResponse.stream(recordRepository.getAll().stream()
                .map(Record::toProto));
    }
}
