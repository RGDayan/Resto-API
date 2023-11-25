package com.dayan.restaurant.model.relations;

import com.dayan.restaurant.model.Command;
import com.dayan.restaurant.model.Product;
import com.dayan.restaurant.view.CommandView;
import com.dayan.restaurant.view.ProductView;
import com.dayan.restaurant.view.ServiceView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Entity
@Table(name = "command_product")
public class CommandProduct {

    @EmbeddedId
    public CommandProductId id;

    @ManyToOne
    @MapsId("commandId")
    @PrimaryKeyJoinColumn(name = "command_id")
    @JsonView({ProductView.Index.class})
    public Command command;

    @ManyToOne
    @MapsId("productId")
    @PrimaryKeyJoinColumn(name = "product_id")
    @JsonView({CommandView.Index.class, ServiceView.ShowCurrent.class})
    public Product product;

    @Column(nullable = false)
    @JsonView({CommandView.Index.class, ServiceView.ShowCurrent.class, ProductView.Index.class})
    public Integer quantity = 1;

    @Column(nullable = false)
    @JsonView({CommandView.Index.class, ServiceView.ShowCurrent.class, ProductView.Index.class})
    public String status = "ordered";

    @Column(name = "ordered_hour", nullable = false, columnDefinition = "DATETIME")
    @JsonView({CommandView.Index.class, ServiceView.ShowCurrent.class, ProductView.Index.class})
    public LocalDateTime orderedHour;

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
