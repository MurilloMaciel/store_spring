package com.murillo.maciel.store.services;

import com.murillo.maciel.store.domain.BankSlipPayment;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BankSlipService
{
    public void fillPayment(BankSlipPayment payment, Date orderInstant)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(orderInstant);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        payment.setDueDate(calendar.getTime());
    }
}
