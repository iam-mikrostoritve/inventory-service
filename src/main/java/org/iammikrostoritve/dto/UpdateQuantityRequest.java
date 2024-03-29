package org.iammikrostoritve.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.iammikrostoritve.UpdateQuantityRequestProto;
import org.iammikrostoritve.entity.GrpcEntity;

@Data
@AllArgsConstructor
public class UpdateQuantityRequest implements GrpcEntity<UpdateQuantityRequest, UpdateQuantityRequestProto> {
    private String id;
    private int quantity;

    public UpdateQuantityRequest fromProto(UpdateQuantityRequestProto updateQuantityRequestProto) {
        this.id = updateQuantityRequestProto.getId();
        this.quantity = updateQuantityRequestProto.getQuantity();
        return this;
    }

    public UpdateQuantityRequestProto toProto() {
        return UpdateQuantityRequestProto.newBuilder()
                .setId(this.id)
                .setQuantity(this.quantity)
                .build();
    }
}
