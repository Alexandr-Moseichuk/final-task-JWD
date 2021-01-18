package by.moseichuk.final_task_JWD.service.validator;

import by.moseichuk.final_task_JWD.bean.Application;
import by.moseichuk.final_task_JWD.service.Validator;

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
