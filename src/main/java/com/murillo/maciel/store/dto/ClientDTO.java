package com.murillo.maciel.store.dto;

import com.murillo.maciel.store.domain.Client;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class ClientDTO implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 3, max = 120, message = "Length needs to be between 3 and 120")
    private String name;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Email(message = "Email inválido")
    private String email;

    public ClientDTO()
    {

    }

    public ClientDTO(Client client)
    {
        this.id = client.getId();
        this.name = client.getName();
        this.email = client.getEmail();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
