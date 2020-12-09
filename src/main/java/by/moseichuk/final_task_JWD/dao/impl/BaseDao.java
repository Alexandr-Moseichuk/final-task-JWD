package by.moseichuk.final_task_JWD.dao.impl;

import java.sql.Connection;

public abstract class BaseDao {
    protected Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
