package com.murillo.maciel.store.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @JsonBackReference
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
        this.paymentStatus = paymentStatus.getCode();
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
        return id.equals(payment.id);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }
}
