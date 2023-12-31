package com.dayan.restaurant.model;

import com.dayan.restaurant.model.relations.CommandProduct;
import com.dayan.restaurant.model.relations.CommandProductId;
import com.dayan.restaurant.view.CardView;
import com.dayan.restaurant.view.CommandView;
import com.dayan.restaurant.view.ProductView;
import com.dayan.restaurant.view.ServiceView;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.Pair;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "command")
public class Command {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({CommandView.Index.class, ProductView.Index.class, ServiceView.Index.class, ServiceView.ShowCurrent.class, CardView.Index.class})
    public Long id;

    @JsonView({CommandView.Index.class, ProductView.Index.class, ServiceView.Index.class, ServiceView.ShowCurrent.class, CardView.Index.class})
    public Double priceHT = 0d;

    @JsonView({CommandView.Index.class, ProductView.Index.class, ServiceView.Index.class, ServiceView.ShowCurrent.class, CardView.Index.class})
    public Double priceTTC = 0d;

    @Column(name = "num_table", nullable = false)
    @JsonView({CommandView.Index.class, ProductView.Index.class, ServiceView.Index.class, ServiceView.ShowCurrent.class, CardView.Index.class})
    public Integer numTable;

    @Column(name = "customer_count", nullable = false)
    @JsonView({CommandView.Index.class, ProductView.Index.class, ServiceView.Index.class, ServiceView.ShowCurrent.class, CardView.Index.class})
    public Integer customerCount;

    @Column(columnDefinition = "BOOLEAN", nullable = false)
    @JsonView({CommandView.Index.class, ProductView.Index.class, ServiceView.Index.class, ServiceView.ShowCurrent.class, CardView.Index.class})
    public Boolean status = false;

    @Column(name = "closing_reason")
    @JsonView({CommandView.Index.class, ProductView.Index.class, ServiceView.Index.class, ServiceView.ShowCurrent.class, CardView.Index.class})
    public String closingReason;

    @Column(name = "created_at", columnDefinition = "DATETIME", nullable = false)
    @JsonView({CommandView.Index.class, ServiceView.ShowCurrent.class, CardView.Index.class})
    public LocalDateTime createdAt;

    @ManyToOne(optional = false)
    @JsonIgnoreProperties(value = "commands", allowSetters = true)
    @JsonView(CommandView.Index.class)
    public Service service;

    @OneToMany(mappedBy = "command", fetch = FetchType.EAGER)
    @JsonView({CommandView.Index.class, ServiceView.ShowCurrent.class})
    public List<CommandProduct> commandProducts = new ArrayList<>();

    public CommandProduct addProduct(Product product){
        CommandProduct commandProduct = null;
        for (CommandProduct cp : commandProducts) {
            if (product.equals(cp.product)) {
                commandProduct = cp;
                break;
            }
        }

        if (commandProduct == null){
            commandProduct = new CommandProduct();
            commandProduct.product = product;
            commandProduct.command = this;
            CommandProductId commandProductId = new CommandProductId();
            commandProductId.productId = product.id;
            commandProductId.commandId = this.id;

            commandProduct.status = "ordered";
            commandProduct.orderedHour = LocalDateTime.now();
            commandProduct.quantity = 1;
            commandProduct.computePrices();

            commandProduct.id = commandProductId;
            this.commandProducts.add(commandProduct);
        } else {
            commandProduct.quantity += 1;
            commandProduct.computePrices();
        }

        return commandProduct;
    }

    public Pair<CommandProduct, String> reduceProduct(Product product) {
        CommandProduct commandProduct = null;
        for (CommandProduct cp : commandProducts) {
            if (product.equals(cp.product)) {
                commandProduct = cp;
                break;
            }
        }

        assert commandProduct != null;
        Pair<CommandProduct, String> pair;
        if (commandProduct.quantity - 1 == 0)
            pair = new Pair<>(commandProduct, "remove");
        else {
            commandProduct.quantity -= 1;
            commandProduct.computePrices();
            pair = new Pair<>(commandProduct, "reduce");
        }

        return pair;
    }

    public void removeProduct(Product product){
        this.commandProducts.removeIf(x -> x.product.id.equals(product.id));
    }

    public void computeAmounts() {
        this.priceHT = (double) 0;
        this.priceTTC = (double) 0;
        for (CommandProduct commandProduct: commandProducts) {
            this.priceHT += commandProduct.product.priceHT * commandProduct.quantity;
            this.priceTTC += commandProduct.product.priceTTC * commandProduct.quantity;
        }
        this.priceHT = Math.ceil(priceHT * 100) / 100;
        this.priceTTC = Math.ceil(priceTTC * 100) / 100;
    }
}
