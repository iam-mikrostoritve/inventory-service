package org.iammikrostoritve.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.iammikrostoritve.ArtistProto;
import org.iammikrostoritve.InsertArtistProto;

import java.util.Locale;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MongoEntity(collection = "artists")
public class Artist extends PanacheMongoEntity implements GrpcEntity<Artist, ArtistProto>, MongoExtender {
    private String artistName;
    private String country;
    private String realName;

    public Artist(String artistName) {
        this.artistName = artistName;
    }

    @Override
    public ObjectId getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = new ObjectId(id);
    }

    @Override
    public void setId(ObjectId id) {
        this.id = id;
    }

    public Artist fromProto(ArtistProto artistProto) {
        this.artistName = artistProto.getArtistName();
        this.country = artistProto.getCountry();
        this.realName = artistProto.getRealName();
        return this;
    }

    public Artist fromProto(InsertArtistProto artistProto) {
        this.artistName = artistProto.getArtistName();
        this.country = artistProto.getCountry();
        this.realName = artistProto.getRealName();
        return this;
    }

    public ArtistProto toProto() {
        return ArtistProto.newBuilder()
                .setId(this.id.toString())
                .setArtistName(this.artistName)
                .setCountry(this.country)
                .setRealName(this.realName)
                .build();
    }
}
