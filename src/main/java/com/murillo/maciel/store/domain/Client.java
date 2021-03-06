package com.murillo.maciel.store.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.murillo.maciel.store.domain.enums.ClientType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Client implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @Column(unique = true)
    private String email;
    private String cpfOrCnpj;
    private Integer clientTypeInt;

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Order> orders = new ArrayList<>();

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "client")
    private List<Address> adresses = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "PHONE")
    private Set<String> phones = new HashSet<>();

    public Client()
    {

    }

    public Client(Integer id, String name, String email, String cpfOrCnpj, ClientType clientType)
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpfOrCnpj = cpfOrCnpj;
        this.clientTypeInt = ( clientType == null ? null : clientType.getCode() );
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getCpfOrCnpj()
    {
        return cpfOrCnpj;
    }

    public void setCpfOrCnpj(String cpfOrCnpj)
    {
        this.cpfOrCnpj = cpfOrCnpj;
    }

    public ClientType getClientType()
    {
        return ClientType.toEnum(this.clientTypeInt);
    }

    public void setClientType(ClientType clientType)
    {
        this.clientTypeInt = clientType.getCode();
    }

    public List<Address> getAdresses()
    {
        return adresses;
    }

    public void setAdresses(List<Address> adresses)
    {
        this.adresses = adresses;
    }

    public Set<String> getPhones()
    {
        return phones;
    }

    public void setPhones(Set<String> phones)
    {
        this.phones = phones;
    }

    public List<Order> getOrders()
    {
        return orders;
    }

    public void setOrders(List<Order> orders)
    {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        if (this.id != null && client.id != null) return (this.id.equals(client.id));
        return (this.id == null && client.id == null);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }
}
