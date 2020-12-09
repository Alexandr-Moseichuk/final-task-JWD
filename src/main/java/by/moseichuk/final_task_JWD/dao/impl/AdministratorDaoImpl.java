package by.moseichuk.final_task_JWD.dao.impl;

import by.moseichuk.final_task_JWD.bean.Administrator;
import by.moseichuk.final_task_JWD.dao.AdministratorDao;

import java.util.List;

public class AdministratorDaoImpl implements AdministratorDao {
    //TODO
    private static final String READ_ALL = "SELECT `login`, `password`, `mail`, `role`, `approved` FROM `user`";
    private static final String CREATE = "INSERT INTO `user` (`login`, `password`, `mail`, `role`, `approved`) VALUES(?, ?, ?, ?, ?)";
    private static final String READ = "SELECT `login`, `password`, `mail`, `role`, `approved` FROM `user` WHERE `id` = ?";
    private static final String UPDATE = "UPDATE `user` SET `login` = ?, `password` = ?, `mail` = ?, `role` = ?, `approved` = ? WHERE `id` = ?";
    private static final String DELETE = "DELETE FROM `user` WHERE `id` = ?";

    @Override
    public List<Administrator> readAll() {
        return null;
    }

    @Override
    public Integer create(Administrator entity) {
        return null;
    }

    @Override
    public Administrator read(Integer id) {
        return null;
    }

    @Override
    public void update(Administrator entity) {

    }

    @Override
    public void delete(Integer id) {

    }
}
