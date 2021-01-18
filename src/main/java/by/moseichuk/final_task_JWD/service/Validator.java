package by.moseichuk.final_task_JWD.service;

import by.moseichuk.final_task_JWD.bean.Entity;
import by.moseichuk.final_task_JWD.service.exception.ValidationException;

import java.util.Map;

public interface Validator<Type extends Entity> {

    Map<String, String> validate(Type type);

}
