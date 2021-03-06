package com.murillo.maciel.store.dto;

import com.murillo.maciel.store.services.validation.ClientInsert;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ClientInsert
public class ClientNewDTO implements Serializable
{
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 3, max = 120, message = "Length needs to be between 3 and 120")
    private String name;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String cpfOrCnpj;
    private Integer clientTypeInt;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String street;
    @NotEmpty(message = "Preenchimento obrigatório")
    private String number;
    private String complement;
    private String neighborhood;
    @NotEmpty(message = "Preenchimento obrigatório")
        private String zipCode;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String phone1;
    private String phone2;
    private String phone3;

    private Integer cityId;

    public ClientNewDTO()
    {

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

    public Integer getClientTypeInt()
    {
        return clientTypeInt;
    }

    public void setClientTypeInt(Integer clientTypeInt)
    {
        this.clientTypeInt = clientTypeInt;
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

    public String getPhone1()
    {
        return phone1;
    }

    public void setPhone1(String phone1)
    {
        this.phone1 = phone1;
    }

    public String getPhone2()
    {
        return phone2;
    }

    public void setPhone2(String phone2)
    {
        this.phone2 = phone2;
    }

    public String getPhone3()
    {
        return phone3;
    }

    public void setPhone3(String phone3)
    {
        this.phone3 = phone3;
    }

    public Integer getCityId()
    {
        return cityId;
    }

    public void setCityId(Integer cityId)
    {
        this.cityId = cityId;
    }
}
