package by.moseichuk.final_task_JWD.service;

import by.moseichuk.final_task_JWD.bean.Entity;

import java.util.HashMap;
import java.util.Map;

public interface Validator<Type extends Entity> {
    Map<String, String> errorMap = new HashMap<>();

    Map<String, String> validate(Type type);

}
