package by.moseichuk.adlinker.service;

import by.moseichuk.adlinker.bean.Entity;
import by.moseichuk.adlinker.service.validator.ApplicationValidator;
import by.moseichuk.adlinker.service.validator.UserValidator;
import by.moseichuk.adlinker.service.validator.ValidatorEnum;

public class ValidatorFactory {

    public static <Type extends Entity> Validator<Type> getValidator(ValidatorEnum validatorEnum) {
        switch (validatorEnum) {
            case USER:
                return (Validator<Type>) new UserValidator();
            case APPLICATION:
                return (Validator<Type>) new ApplicationValidator();
        }
        return null;
    }

}
