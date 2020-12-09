package by.moseichuk.final_task_JWD.dao;

import by.moseichuk.final_task_JWD.bean.Administrator;

import java.util.List;

public interface AdministratorDao extends Dao<Administrator> {
    List<Administrator> readAll();
}
