package org.iammikrostoritve.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.iammikrostoritve.InsertRecordProto;
import org.iammikrostoritve.InsertRecordProto;
import org.iammikrostoritve.entity.Artist;
import org.iammikrostoritve.entity.Label;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class InsertRecord {
    private String title;
    private String artistId;
    private String genre;
    private String style;
    private String releaseDate;
    private String labelId;
    private String country;
    private double price;
    private int quantity;

    public InsertRecord fromProto(InsertRecordProto recordInsertProto) {
        this.title = recordInsertProto.getTitle();
        this.artistId = recordInsertProto.getArtistId();
        this.genre = recordInsertProto.getGenre();
        this.style = recordInsertProto.getStyle();
        this.releaseDate = recordInsertProto.getReleaseDate();
        this.labelId = recordInsertProto.getLabelId();
        this.country = recordInsertProto.getCountry();
        this.price = recordInsertProto.getPrice();
        this.quantity = recordInsertProto.getQuantity();
        return this;
    }

    public InsertRecordProto toProto() {
        return InsertRecordProto.newBuilder()
                .setTitle(this.title)
                .setArtistId(this.artistId)
                .setGenre(this.genre)
                .setStyle(this.style)
                .setReleaseDate(this.releaseDate)
                .setLabelId(this.labelId)
                .setCountry(this.country)
                .setPrice(this.price)
                .setQuantity(this.quantity)
                .build();
    }
}
