package com.epam.esm.service.dto;


import com.epam.esm.model.Certificate;
import com.epam.esm.model.Order;
import com.epam.esm.model.User;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Validated
public class OrderConditionDto extends EntityDto<Long,  OrderConditionDto> implements Serializable {

    @Null(message = "Users cannot enter any order condition")
    private BigDecimal price;

    private Order order;

    @NotNull(message = "Certificate's id has to be entered")
    private Certificate certificate;

    public OrderConditionDto() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
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
}
