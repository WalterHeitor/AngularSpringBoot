package com.softwalter.api.cliente.domain;

import com.softwalter.api.cliente.domain.exceptions.UuidException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

public class MinhaValidacaoUUIDValidation implements ConstraintValidator<MinhaValidacaoUUID, Object> {

    private final Log logger = LogFactory.getLog(this.getClass());
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) throws UuidException {
        try {
            final UUID uuid = UUID.fromString(String.valueOf(value.toString()));
            logger.info("sucesso UUID : "+uuid);
            return true;
        } catch (RuntimeException e){
            throw  new UuidException("UUID :"+value.toString()+"informado nao e valido");
        }
    }
}
