package by.moseichuk.final_task_JWD.service;

import by.moseichuk.final_task_JWD.bean.User;
import by.moseichuk.final_task_JWD.dao.UserDao;
import by.moseichuk.final_task_JWD.dao.exception.DaoException;
import by.moseichuk.final_task_JWD.dao.exception.TransactionException;
import by.moseichuk.final_task_JWD.service.exception.ServiceException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class UserServiceImpl extends BaseService implements UserService {
    @Override
    public User login(String mail, String password) throws ServiceException {
        try {
            UserDao userDao = transaction.getDao("UserDao");
            return userDao.login(mail, md5(password));
        } catch (TransactionException e) {
            throw new ServiceException(e);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String md5(String password) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("md5");
            digest.reset();
            digest.update(password.getBytes());
            byte hash[] = digest.digest();
            Formatter formatter = new Formatter();
            for(int i = 0; i < hash.length; i++) {
                formatter.format("%02X", hash[i]);
            }
            String md5summ = formatter.toString();
            formatter.close();
            return md5summ;
        } catch(NoSuchAlgorithmException e) {
            return null;
        }
    }
}
