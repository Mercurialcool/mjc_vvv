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
        private float quantity;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;

        @ManyToMany
        @JoinTable(name = "orders_certificates",
                joinColumns = @JoinColumn(name = "order_id"),
                inverseJoinColumns = @JoinColumn(name = "gift_certificate_id"))
        private Set<Certificate> certificates;

    public Order() {
    }

    public Instant getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Instant dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(Set<Certificate> certificates) {
        this.certificates = certificates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        if (!super.equals(o)) return false;

        Order order = (Order) o;

        if (Float.compare(order.quantity, quantity) != 0) return false;
        if (dateOfIssue != null ? !dateOfIssue.equals(order.dateOfIssue) : order.dateOfIssue != null) return false;
        if (user != null ? !user.equals(order.user) : order.user != null) return false;
        return certificates != null ? certificates.equals(order.certificates) : order.certificates == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (dateOfIssue != null ? dateOfIssue.hashCode() : 0);
        result = 31 * result + (quantity != +0.0f ? Float.floatToIntBits(quantity) : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (certificates != null ? certificates.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("dateOfIssue=").append(dateOfIssue);
        sb.append(", quantity=").append(quantity);
        sb.append(", user=").append(user);
        sb.append(", certificates=").append(certificates);
        sb.append('}');
        return sb.toString();
    }
}

