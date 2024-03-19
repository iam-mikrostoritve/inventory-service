package org.iammikrostoritve.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.iammikrostoritve.UpdatePriceRequestProto;
import org.iammikrostoritve.entity.GrpcEntity;

@Data
@AllArgsConstructor
public class UpdatePriceRequest implements GrpcEntity<UpdatePriceRequest, UpdatePriceRequestProto> {
    private String id;
    private double price;

    public UpdatePriceRequest fromProto(UpdatePriceRequestProto updatePriceRequestProto) {
        this.id = updatePriceRequestProto.getId();
        this.price = updatePriceRequestProto.getPrice();
        return this;
    }

    public UpdatePriceRequestProto toProto() {
        return UpdatePriceRequestProto.newBuilder()
                .setId(this.id)
                .setPrice(this.price)
                .build();
    }
}
