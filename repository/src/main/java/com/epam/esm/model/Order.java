package com.epam.esm.model;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = {"user", "orderConditions"})
@ToString(callSuper = true, exclude = {"user", "orderConditions"})
@Inheritance(strategy = InheritanceType.JOINED)
public class Order extends com.epam.esm.model.Entity<Long> implements Serializable {
    private static final long serialVersionUID = -519531229783607916L;

    @Column(name = "date_of_issue", columnDefinition = "TIMESTAMP")
    private Instant dateOfIssue;
    @Column(name = "quantity")
    private Float quantity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    private Set<OrderCondition> orderConditions;

    public Order(Instant dateOfIssue, float quantity, User user) {
        this.dateOfIssue = dateOfIssue;
        this.quantity = quantity;
        this.user = user;
    }

    public Order(Instant dateOfIssue, User linkingUser, Object user) {
    }

    public Instant getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Instant dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<OrderCondition> getOrderConditions() {
        return orderConditions;
    }

    public void setOrderConditions(Set<OrderCondition> orderConditions) {
        this.orderConditions = orderConditions;
    }


}

