package org.iammikrostoritve.repository;

import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.iammikrostoritve.dto.InsertRecord;
import org.iammikrostoritve.entity.Artist;
import org.iammikrostoritve.entity.Label;
import org.iammikrostoritve.entity.Record;

import jakarta.enterprise.context.ApplicationScoped;
import org.iammikrostoritve.entity.exception.ArtistNotFoundException;
import org.iammikrostoritve.entity.exception.LabelNotFoundException;
import org.iammikrostoritve.entity.exception.RecordNotFoundException;

import java.util.List;

@ApplicationScoped
public class RecordRepository {

    @Inject
    ArtistRepository artistRepository;
    @Inject
    LabelRepository labelRepository;

    public List<Record> getAll() {
        return Record.listAll();
    }

    public Record getById(String id) throws RecordNotFoundException {
        Record record = Record.findById(new ObjectId(id));
        if (record == null) {
            throw new RecordNotFoundException(id);
        }
        return record;
    }

    public void insert(InsertRecord insertRecord) throws ArtistNotFoundException, LabelNotFoundException {
        Artist artist;
        if (insertRecord.getArtistId() != null) {
            artist = artistRepository.getById(insertRecord.getArtistId());
        } else {
            artist = null;
        }

        Label label;
        if (insertRecord.getLabelId() != null) {
            label = labelRepository.getById(insertRecord.getLabelId());
        } else {
            label = null;
        }

        Record record = new Record(
                insertRecord.getTitle(),
                artist,
                insertRecord.getGenre(),
                insertRecord.getStyle(),
                insertRecord.getReleaseDate(),
                label,
                insertRecord.getCountry(),
                insertRecord.getPrice(),
                insertRecord.getQuantity()
        );

        record.persist();
    }

    public void delete(String id) throws RecordNotFoundException {
        Record record = Record.findById(new ObjectId(id));
        if (record == null) {
            throw new RecordNotFoundException(id);
        }
        record.delete();
    }

    public void updateQuantity(int quantity, String id) throws RecordNotFoundException {
        Record record = Record.findById(new ObjectId(id));
        if (record == null) {
            throw new RecordNotFoundException(id);
        }
        record.setQuantity(quantity);
        record.persist();
    }

    public void updatePrice(double price, String id) throws RecordNotFoundException {
        Record record = Record.findById(new ObjectId(id));
        if (record == null) {
            throw new RecordNotFoundException(id);
        }
        record.setPrice(price);
        record.persist();
    }

}