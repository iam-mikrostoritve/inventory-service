package org.iammikrostoritve.repository;

import org.bson.types.ObjectId;
import org.iammikrostoritve.entity.Artist;
import jakarta.enterprise.context.ApplicationScoped;
import org.iammikrostoritve.entity.exception.ArtistNotFoundException;

import java.util.List;

@ApplicationScoped
public class ArtistRepository {

    public List<Artist> getAll() {
        return Artist.listAll();
    }

    public Artist getById(String id) throws ArtistNotFoundException {
        Artist artist = Artist.findById(new ObjectId(id));
        if (artist == null)
            throw new ArtistNotFoundException(id);
        return artist;
    }

    public void insert(Artist artist) {
        artist.persist();
    }

    public void delete(String id) throws ArtistNotFoundException {
        Artist artist = Artist.findById(new ObjectId(id));
        if (artist == null) {
            throw new ArtistNotFoundException(id);
        }
        artist.delete();
    }
}
