package com.murillo.maciel.store.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class OrderItem implements Serializable
{
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();

    private Double discount;
    private Integer count;
    private Double price;

    public OrderItem()
    {

    }

    public OrderItem(Order order, Product product, Double discount, Integer count, Double price)
    {
        super();
        this.id.setOrder(order);
        this.id.setProduct(product);
        this.discount = discount;
        this.count = count;
        this.price = price;
    }

    public OrderItemPK getId()
    {
        return id;
    }

    public void setId(OrderItemPK id)
    {
        this.id = id;
    }

    public Double getDiscount()
    {
        return discount;
    }

    public void setDiscount(Double discount)
    {
        this.discount = discount;
    }

    public Integer getCount()
    {
        return count;
    }

    public void setCount(Integer count)
    {
        this.count = count;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }

    @JsonIgnore
    public Order getOrder()
    {
        return this.id.getOrder();
    }

    public Product getProduct()
    {
        return this.id.getProduct();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        if (this.id != null && orderItem.id != null) return (this.id.equals(orderItem.id));
        return (this.id == null && orderItem.id == null);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }
}
