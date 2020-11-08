package com.murillo.maciel.store.services.validation;

import com.murillo.maciel.store.domain.Client;
import com.murillo.maciel.store.domain.enums.ClientType;
import com.murillo.maciel.store.dto.ClientDTO;
import com.murillo.maciel.store.dto.ClientNewDTO;
import com.murillo.maciel.store.repositories.ClientRepository;
import com.murillo.maciel.store.resources.exceptions.FieldMessage;
import com.murillo.maciel.store.services.validation.utils.Br;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClientDTO>
{
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ClientRepository repository;

    @Override
    public void initialize(ClientUpdate ann)
    {
    }

    @Override
    public boolean isValid(ClientDTO objDto, ConstraintValidatorContext context)
    {
        List<FieldMessage> list = new ArrayList<>();

        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        Client tempClient = repository.findByEmail(objDto.getEmail());

        if (tempClient != null && !tempClient.getId().equals(uriId))
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