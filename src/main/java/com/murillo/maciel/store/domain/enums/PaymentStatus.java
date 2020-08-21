package com.murillo.maciel.store.domain.enums;

public enum PaymentStatus
{
    PENDING(1, "Pending Payment"),
    CONFIRMED(2, "Payment Confirmed"),
    CANCELLED(3, "Payment Cancelled");

    private int code;
    private String description;

    private PaymentStatus(int code, String description)
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

    public static PaymentStatus toEnum(Integer code)
    {
        if (code == null)
        {
            return null;
        }
        for (PaymentStatus paymentStatus : PaymentStatus.values())
        {
            if (code == paymentStatus.getCode())
            {
                return paymentStatus;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + code);
    }
}
