package com.murillo.maciel.store.dto;

import com.murillo.maciel.store.domain.Category;

import java.io.Serializable;

public class CategoryDTO implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;

    CategoryDTO()
    {

    }

    public CategoryDTO(Integer id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public CategoryDTO(Category category)
    {
        this.id = category.getId();
        this.name = category.getName();
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
}
