package com.murillo.maciel.store.services.validation;

import com.murillo.maciel.store.domain.Client;
import com.murillo.maciel.store.domain.enums.ClientType;
import com.murillo.maciel.store.dto.ClientNewDTO;
import com.murillo.maciel.store.repositories.ClientRepository;
import com.murillo.maciel.store.resources.exceptions.FieldMessage;
import com.murillo.maciel.store.services.validation.utils.Br;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO>
{
    @Autowired
    private ClientRepository repository;

    @Override
    public void initialize(ClientInsert ann)
    {
    }

    @Override
    public boolean isValid(ClientNewDTO objDto, ConstraintValidatorContext context)
    {
        List<FieldMessage> list = new ArrayList<>();

        ClientType clientType = ClientType.toEnum(objDto.getClientTypeInt());
        String cpfOrCnpj = objDto.getCpfOrCnpj();

        if (clientType.equals(ClientType.INDIVIDUAL_ENTITY) && !Br.isValidCpf(cpfOrCnpj))
        {
            list.add(new FieldMessage("cpfOrCnpj", "Invalid CPF"));
        }
        else if (clientType.equals(ClientType.LEGAL_ENTITY) && !Br.isValidCpf(cpfOrCnpj))
        {
            list.add(new FieldMessage("cpfOrCnpj", "Invalid CNPJ"));
        }

        Client tempClient = repository.findByEmail(objDto.getEmail());

        if (tempClient != null)
        {
            list.add(new FieldMessage("email", "Client with this email already exists"));
        }

        for (FieldMessage e : list)
        {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }

        return list.isEmpty();
    }
}