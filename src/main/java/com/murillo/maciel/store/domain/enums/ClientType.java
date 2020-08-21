package com.murillo.maciel.store.domain.enums;

public enum ClientType
{
    INDIVIDUAL_ENTITY(1, "Individual Entity"),
    LEGAL_ENTITY(2, "Legal Entity");

    private int code;
    private String description;

    private ClientType(int code, String description)
    {
        this.code = code;
        this.description = description;
    }

    public int getCode()
    {
        return code;
    }

    public String getDescription()
    {
        return description;
    }

    public static ClientType toEnum(Integer code)
    {
        if (code == null)
        {
            return null;
        }
        for (ClientType clientType : ClientType.values())
        {
            if (code == clientType.getCode())
            {
                return clientType;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + code);
    }
}
