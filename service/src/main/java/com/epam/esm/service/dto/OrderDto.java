package com.epam.esm.service.dto;

import com.epam.esm.model.Certificate;
import com.epam.esm.model.OrderCondition;
import com.epam.esm.model.User;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@Validated
@JsonRootName(value = "order")
public class OrderDto extends EntityDto<Long, OrderDto> implements Serializable {

    private Instant dateOfIssue;
    private float quantity;

    @NotNull
    private UserDto user;

    @NotNull
    private Set<CertificateDto> certificates;

    private Set<OrderCondition> orderConditions;

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


    public OrderDto() {
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public Set<CertificateDto> getCertificates() {
        return certificates;
    }

    public void setCertificates(Set<CertificateDto> certificates) {
        this.certificates = certificates;
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
        if (!(o instanceof OrderDto)) return false;
        if (!super.equals(o)) return false;

        OrderDto orderDto = (OrderDto) o;

        if (Float.compare(orderDto.quantity, quantity) != 0) return false;
        if (dateOfIssue != null ? !dateOfIssue.equals(orderDto.dateOfIssue) : orderDto.dateOfIssue != null)
            return false;
        if (user != null ? !user.equals(orderDto.user) : orderDto.user != null) return false;
        if (certificates != null ? !certificates.equals(orderDto.certificates) : orderDto.certificates != null)
            return false;
        return orderConditions != null ? orderConditions.equals(orderDto.orderConditions) : orderDto.orderConditions == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (dateOfIssue != null ? dateOfIssue.hashCode() : 0);
        result = 31 * result + (quantity != +0.0f ? Float.floatToIntBits(quantity) : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (certificates != null ? certificates.hashCode() : 0);
        result = 31 * result + (orderConditions != null ? orderConditions.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderDto{");
        sb.append("dateOfIssue=").append(dateOfIssue);
        sb.append(", quantity=").append(quantity);
        sb.append(", user=").append(user);
        sb.append(", certificates=").append(certificates);
        sb.append(", orderConditions=").append(orderConditions);
        sb.append('}');
        return sb.toString();
    }
}
