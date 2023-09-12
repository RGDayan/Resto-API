package com.dayan.restaurant.model.relations;

import com.dayan.restaurant.model.Order;
import com.dayan.restaurant.model.Product;
import com.dayan.restaurant.view.OrderView;
import com.dayan.restaurant.view.ProductView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.Objects;

@Data
@Entity
@Table(name = "order_product")
public class OrderProduct {
    @EmbeddedId
    private OrderProductId id;

    @ManyToOne
    @MapsId("orderId")
    @JsonView(ProductView.Index.class)
    private Order order;

    @ManyToOne
    @MapsId("productId")
    @JsonView(OrderView.Index.class)
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

        OrderProduct that = (OrderProduct) o;
        return Objects.equals(order, that.order) &&
                Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, product);
    }
}
