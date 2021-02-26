package com.epam.esm.model;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "orders")
@Inheritance(strategy = InheritanceType.JOINED)
public class Order extends com.epam.esm.model.Entity implements Serializable {
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

    public Order() {
    }

    public Order(Instant dateOfIssue, float quantity, User user) {
        this.dateOfIssue = dateOfIssue;
        this.quantity = quantity;
        this.user = user;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        if (!super.equals(o)) return false;

        Order order = (Order) o;

        if (dateOfIssue != null ? !dateOfIssue.equals(order.dateOfIssue) : order.dateOfIssue != null) return false;
        if (quantity != null ? !quantity.equals(order.quantity) : order.quantity != null) return false;
        if (user != null ? !user.equals(order.user) : order.user != null) return false;
        return orderConditions != null ? orderConditions.equals(order.orderConditions) : order.orderConditions == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (dateOfIssue != null ? dateOfIssue.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (orderConditions != null ? orderConditions.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("dateOfIssue=").append(dateOfIssue);
        sb.append(", quantity=").append(quantity);
        sb.append(", user=").append(user);
        sb.append(", orderConditions=").append(orderConditions);
        sb.append('}');
        return sb.toString();
    }
}

