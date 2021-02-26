package com.epam.esm.service.dto;


import com.epam.esm.model.Certificate;
import com.epam.esm.model.Order;
import com.epam.esm.model.User;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.time.Instant;

@Validated
public class OrderConditionDto extends EntityDto implements Serializable {
    private double price;
    private Order order;
    private Certificate certificate;

    public OrderConditionDto() {
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderConditionDto)) return false;
        if (!super.equals(o)) return false;

        OrderConditionDto that = (OrderConditionDto) o;

        if (Double.compare(that.price, price) != 0) return false;
        if (order != null ? !order.equals(that.order) : that.order != null) return false;
        return certificate != null ? certificate.equals(that.certificate) : that.certificate == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (order != null ? order.hashCode() : 0);
        result = 31 * result + (certificate != null ? certificate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderConditionDto{");
        sb.append("price=").append(price);
        sb.append(", order=").append(order);
        sb.append(", certificate=").append(certificate);
        sb.append('}');
        return sb.toString();
    }
}
