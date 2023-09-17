package com.dayan.restaurant.model;

import com.dayan.restaurant.view.CommandView;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "service")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(CommandView.Index.class)
    private Long id;

    @Column(name = "opened_date", nullable = false, columnDefinition = "DATETIME")
    @JsonView(CommandView.Index.class)
    private Date openedDate = new Date(System.currentTimeMillis());

    @Column(name = "closed_date", columnDefinition = "DATETIME")
    @JsonView(CommandView.Index.class)
    private Date closedDate;

    @Column(name = "total_amount")
    @JsonView(CommandView.Index.class)
    private Double totalAmount;

    @Column(columnDefinition = "BOOLEAN", nullable = false)
    @JsonView(CommandView.Index.class)
    private Boolean status = true;

    @ManyToOne(optional = false)
    @JsonIgnoreProperties("services")
    private Card card;

    @OneToMany(mappedBy = "service")
    @JsonIgnoreProperties("service")
    private List<Command> commands = new ArrayList<>();
}
