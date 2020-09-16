package com.murillo.maciel.store.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.murillo.maciel.store.domain.enums.PaymentStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Payment implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    private Integer paymentStatus;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "order_id")
    @MapsId
    private Order order;

    public Payment()
    {

    }

    public Payment(Integer id, PaymentStatus paymentStatus, Order order)
    {
        this.id = id;
        this.paymentStatus = ( paymentStatus == null ? null : paymentStatus.getCode() );
        this.order = order;
    }

    public static long getSerialVersionUID()
    {
        return serialVersionUID;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public PaymentStatus getPaymentStatus()
    {
        return PaymentStatus.toEnum(paymentStatus);
    }

    public void setPaymentStatus(PaymentStatus paymentStatus)
    {
        this.paymentStatus = paymentStatus.getCode();
    }

    public Order getOrder()
    {
        return order;
    }

    public void setOrder(Order order)
    {
        this.order = order;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        if (this.id != null && payment.id != null) return (this.id.equals(payment.id));
        return (this.id == null && payment.id == null);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }
}
