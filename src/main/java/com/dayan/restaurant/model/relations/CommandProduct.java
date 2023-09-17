package com.dayan.restaurant.model.relations;

import com.dayan.restaurant.model.Command;
import com.dayan.restaurant.model.Product;
import com.dayan.restaurant.view.CommandView;
import com.dayan.restaurant.view.ProductView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.Objects;

@Data
@Entity
@Table(name = "command_product")
public class CommandProduct {
    @EmbeddedId
    private CommandProductId id;

    @ManyToOne
    @MapsId("commandId")
    @JsonView(ProductView.Index.class)
    private Command command;

    @ManyToOne
    @MapsId("productId")
    @JsonView(CommandView.Index.class)
    private Product product;

    @Column(nullable = false)
    private Integer quantity = 1;

    @Column(nullable = false)
    private String status = "ordered";

    @Column(name = "ordered_hour", nullable = false, columnDefinition = "DATETIME")
    private Date orderedHour;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        CommandProduct that = (CommandProduct) o;
        return Objects.equals(command, that.command) &&
                Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(command, product);
    }
}
