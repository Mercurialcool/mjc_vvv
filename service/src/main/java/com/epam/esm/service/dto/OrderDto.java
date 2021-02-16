package com.epam.esm.service.dto;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.io.Serializable;
import java.time.Instant;

@JsonRootName(value = "order")
public class OrderDto extends EntityDto implements Serializable {

    private Instant dateOfIssue;
    private float quantity;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDto)) return false;
        if (!super.equals(o)) return false;

        OrderDto orderDto = (OrderDto) o;

        if (Float.compare(orderDto.quantity, quantity) != 0) return false;
        return dateOfIssue != null ? dateOfIssue.equals(orderDto.dateOfIssue) : orderDto.dateOfIssue == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (dateOfIssue != null ? dateOfIssue.hashCode() : 0);
        result = 31 * result + (quantity != +0.0f ? Float.floatToIntBits(quantity) : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderDto{");
        sb.append("dateOfIssue=").append(dateOfIssue);
        sb.append(", quantity=").append(quantity);
        sb.append('}');
        return sb.toString();
    }
}
