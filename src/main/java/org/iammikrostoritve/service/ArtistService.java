package org.iammikrostoritve.service;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import org.iammikrostoritve.*;
import org.iammikrostoritve.entity.Artist;
import org.iammikrostoritve.entity.exception.ArtistNotFoundException;
import org.iammikrostoritve.repository.ArtistRepository;
import org.jboss.logging.Logger;


@GrpcService
public class ArtistService implements ArtistGrpcService {
    private static final Logger log = Logger.getLogger(ArtistService.class);

    @Inject
    ArtistRepository artistRepository;


    @Override
    public Uni<ArtistProto> getById(ArtistIdProto request) {
        log.info("Received request to get artist by id: " + request.getId());
        try {
            Artist artist = artistRepository.getById(request.getId());
            return GrpcResponse.single(artist.toProto());
        } catch (ArtistNotFoundException e) {
            log.info(e.getMessage());
            throw new StatusRuntimeException(Status.NOT_FOUND.withDescription(e.getMessage()));
        }
    }

    @Override
    public Uni<Empty> insert(InsertArtistProto request) {
        log.info("Received request to insert artist: " + request.getArtistName());
        Artist artist = new Artist().fromProto(request);
        artistRepository.insert(artist);
        return GrpcResponse.empty();
    }

    @Override
    public Uni<Empty> delete(ArtistIdProto request) {
        log.info("Received request to delete artist by id: " + request.getId());
        try {
            artistRepository.delete(request.getId());
            return GrpcResponse.empty();
        } catch (ArtistNotFoundException e) {
            log.info(e.getMessage());
            throw new StatusRuntimeException(Status.NOT_FOUND.withDescription(e.getMessage()));
        }
    }

    @Override
    public Multi<ArtistProto> getAll(Empty request) {
        log.info("Received request to get all artists");
        return GrpcResponse.stream(artistRepository.getAll().stream()
                .map(Artist::toProto));
    }
}
