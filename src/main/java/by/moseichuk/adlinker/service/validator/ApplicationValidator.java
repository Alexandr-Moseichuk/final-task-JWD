package by.moseichuk.adlinker.service.validator;

import by.moseichuk.adlinker.bean.Application;
import by.moseichuk.adlinker.service.Validator;

import java.util.Map;

public class ApplicationValidator implements Validator<Application> {

    @Override
    public Map<String, String> validate(Application application) {
        String comment = application.getComment();
        if (comment == null || comment.isEmpty() || comment.length() < 100) {
            errorMap.put("commentError", "commentError");
        }
        return errorMap;
    }
}
