package org.iammikrostoritve.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.iammikrostoritve.RecordProto;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MongoEntity(collection = "records")
public class Record extends PanacheMongoEntity implements GrpcEntity<Record, RecordProto> {
    private String title;
    private Artist artist;
    private String genre;
    private String style;
    private String releaseDate;
    private Label label;
    private String country;
    private double price;
    private int quantity;

    public Record(String title, double price, int quantity) {
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Record{" +
                "title='" + this.title + '\'' +
                ", artist=" + this.artist.getArtistName() +
                ", genre='" + this.genre + '\'' +
                ", style='" + this.style + '\'' +
                ", releaseDate='" + this.releaseDate + '\'' +
                ", label=" + this.label.getName() +
                ", country='" + this.country + '\'' +
                ", price=" + this.price +
                ", quantity=" + this.quantity +
                '}';
    }

    public Record fromProto(RecordProto recordProto) {
        this.title = recordProto.getTitle();
        this.artist = new Artist().fromProto(recordProto.getArtist());
        this.genre = recordProto.getGenre();
        this.style = recordProto.getStyle();
        this.releaseDate = recordProto.getReleaseDate();
        this.label = new Label().fromProto(recordProto.getLabel());
        this.country = recordProto.getCountry();
        this.price = recordProto.getPrice();
        this.quantity = recordProto.getQuantity();
        return this;
    }

    public RecordProto toProto() {
        return RecordProto.newBuilder()
                .setId(this.id.toString())
                .setTitle(this.title)
                .setArtist(this.artist.toProto())
                .setGenre(this.genre)
                .setStyle(this.style)
                .setReleaseDate(this.releaseDate)
                .setLabel(this.label.toProto())
                .setCountry(this.country)
                .setPrice(this.price)
                .setQuantity(this.quantity)
                .build();
    }
}