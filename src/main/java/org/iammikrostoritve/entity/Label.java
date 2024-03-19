package org.iammikrostoritve.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.iammikrostoritve.InsertLabelProto;
import org.iammikrostoritve.LabelProto;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MongoEntity(collection = "labels")
public class Label extends PanacheMongoEntity implements GrpcEntity<Label, LabelProto>, MongoExtender {
    private String name;
    private String country;

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

    public Label fromProto(LabelProto labelProto) {
        this.name = labelProto.getName();
        this.country = labelProto.getCountry();
        return this;
    }

    public Label fromProto(InsertLabelProto labelProto) {
        this.name = labelProto.getName();
        this.country = labelProto.getCountry();
        return this;
    }

    public LabelProto toProto() {
        return LabelProto.newBuilder()
                .setId(this.id.toString())
                .setName(this.name)
                .setCountry(this.country)
                .build();
    }
}
