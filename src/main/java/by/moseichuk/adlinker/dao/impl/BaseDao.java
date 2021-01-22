package by.moseichuk.adlinker.dao.impl;

import java.sql.Connection;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class BaseDao {
    protected Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    protected Calendar parseDate(Timestamp timestamp) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(timestamp.getTime());
        return calendar;
    }
}
