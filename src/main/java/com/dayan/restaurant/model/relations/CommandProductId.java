package com.dayan.restaurant.model.relations;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
public class CommandProductId implements Serializable {
    @Column(name = "command_id")
    public Long commandId;

    @Column(name = "product_id")
    public Long productId;

    @Override
    public boolean equals(Object o){
        if (o instanceof CommandProductId pk) {
            return Objects.equals(this.commandId, pk.commandId) && Objects.equals(this.productId, pk.productId);
        }
        return false;
    }

    @Override
    public int hashCode(){
        return Objects.hash(commandId, productId);
    }
}
