package by.moseichuk.adlinker.service;

import by.moseichuk.adlinker.bean.Entity;

import java.util.HashMap;
import java.util.Map;

public interface Validator<Type extends Entity> {
    Map<String, String> errorMap = new HashMap<>();

    Map<String, String> validate(Type type);

}
