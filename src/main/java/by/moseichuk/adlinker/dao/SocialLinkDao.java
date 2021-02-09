package by.moseichuk.adlinker.dao;

import by.moseichuk.adlinker.bean.SocialLink;
import by.moseichuk.adlinker.dao.exception.DaoException;

import java.util.List;

public interface SocialLinkDao extends Dao<SocialLink> {

    List<SocialLink> readByUserId(Integer userId) throws DaoException;

}
