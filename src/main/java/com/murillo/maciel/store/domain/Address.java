package com.murillo.maciel.store.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Address implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @JsonBackReference // protege de serialização ciclica, sem serializar o client
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Address()
    {

    }

    public Address(Integer id, String street, String number, String complement, String neighborhood, String zipCode, Client client, City city)
    {
        this.id = id;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.zipCode = zipCode;
        this.client = client;
        this.city = city;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public String getComplement()
    {
        return complement;
    }

    public void setComplement(String complement)
    {
        this.complement = complement;
    }

    public String getNeighborhood()
    {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood)
    {
        this.neighborhood = neighborhood;
    }

    public String getZipCode()
    {
        return zipCode;
    }

    public void setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }

    public Client getClient()
    {
        return client;
    }

    public void setClient(Client client)
    {
        this.client = client;
    }

    public City getCity()
    {
        return city;
    }

    public void setCity(City city)
    {
        this.city = city;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id.equals(address.id);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }
}
