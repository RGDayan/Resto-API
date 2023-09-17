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
    private Long commandId;

    @Column(name = "product_id")
    private Long productId;

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;

        CommandProductId that = (CommandProductId) o;
        return Objects.equals(commandId, that.commandId) &&
                Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode(){
        return Objects.hash(commandId, productId);
    }
}
