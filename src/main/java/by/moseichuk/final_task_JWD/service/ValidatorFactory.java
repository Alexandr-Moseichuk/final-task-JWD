package by.moseichuk.final_task_JWD.service;

import by.moseichuk.final_task_JWD.bean.Entity;
import by.moseichuk.final_task_JWD.service.validator.UserValidator;
import by.moseichuk.final_task_JWD.service.validator.ValidatorEnum;

public class ValidatorFactory {

    public static <Type extends Entity> Validator<Type> getValidator(ValidatorEnum validatorEnum) {
        switch (validatorEnum) {
            case USER:
                return (Validator<Type>) new UserValidator();
        }
        return null;
    }

}
