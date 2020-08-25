package com.murillo.maciel.store.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "order_table")
public class Order implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private Date instant;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
    private Payment payment;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "delivery_address_id")
    private Address deliveryAddress;

    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> itens = new HashSet<>();


    public Order()
    {

    }

    public Order(Integer id, Date instant, Client client, Address deliveryAddress)
    {
        this.id = id;
        this.instant = instant;
        this.client = client;
        this.deliveryAddress = deliveryAddress;
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

    public Date getInstant()
    {
        return instant;
    }

    public void setInstant(Date instant)
    {
        this.instant = instant;
    }

    public Payment getPayment()
    {
        return payment;
    }

    public void setPayment(Payment payment)
    {
        this.payment = payment;
    }

    public Client getClient()
    {
        return client;
    }

    public void setClient(Client client)
    {
        this.client = client;
    }

    public Address getDeliveryAddress()
    {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress)
    {
        this.deliveryAddress = deliveryAddress;
    }

    public Set<OrderItem> getItens()
    {
        return itens;
    }

    public void setItens(Set<OrderItem> itens)
    {
        this.itens = itens;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        if (this.id != null && order.id != null) return (this.id.equals(order.id));
        return (this.id == null && order.id == null);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }
}
