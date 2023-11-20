package com.dayan.restaurant.model;

import com.dayan.restaurant.view.CardView;
import com.dayan.restaurant.view.CommandView;
import com.dayan.restaurant.view.ServiceView;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "service")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ServiceView.Index.class, CardView.Index.class, CommandView.Index.class, ServiceView.ShowCurrent.class})
    public Long id;

    @Column(name = "opened_date", nullable = false, columnDefinition = "DATETIME")
    @JsonView({ ServiceView.Index.class, CardView.Index.class, CommandView.Index.class, ServiceView.ShowCurrent.class})
    public LocalDateTime openedDate = LocalDateTime.now();

    @Column(name = "closed_date", columnDefinition = "DATETIME")
    @JsonView({ServiceView.Index.class, CardView.Index.class, CommandView.Index.class, ServiceView.ShowCurrent.class})
    public LocalDateTime closedDate;

    @Column(name = "planned_close_date", nullable = false, columnDefinition = "DATETIME")
    @JsonView({ServiceView.Index.class, CardView.Index.class, CommandView.Index.class, ServiceView.ShowCurrent.class})
    public LocalDateTime plannedClosedDate;

    @Column(columnDefinition = "BOOLEAN", nullable = false)
    @JsonView({ServiceView.Index.class, CardView.Index.class, CommandView.Index.class, ServiceView.ShowCurrent.class})
    public Boolean status = true;

    @ManyToOne(optional = false)
    @JsonIgnoreProperties("services")
    @JsonView({ServiceView.Index.class, ServiceView.ShowCurrent.class})
    public Card card;

    @OneToMany(mappedBy = "service")
    @JsonIgnoreProperties("service")
    @JsonView({ServiceView.Index.class, ServiceView.ShowCurrent.class, CardView.Index.class})
    public List<Command> commands = new ArrayList<>();
}
