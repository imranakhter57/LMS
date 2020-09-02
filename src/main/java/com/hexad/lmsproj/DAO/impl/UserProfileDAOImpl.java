package com.hexad.lmsproj.DAO.impl;

import com.hexad.lmsproj.DAO.UserProfileDAO;
import com.hexad.lmsproj.datamodels.entity.UserCredentials;
import com.hexad.lmsproj.datamodels.entity.UserProfile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static org.springframework.orm.hibernate4.SessionFactoryUtils.closeSession;

@Repository
public class UserProfileDAOImpl implements UserProfileDAO {

    private static Logger logger = LogManager.getLogger(UserProfile.class);

    @Autowired
    SessionFactory sessionFactory;


    @Override
    public UserProfile getUserProfileByUserId(Long userId) {
        Session session = null;
        UserProfile userProfile = null;
        try {
            session = sessionFactory.openSession();
            userProfile = session.get(UserProfile.class,userId);
        }catch(Exception e) {
            logger.error("(:) Error in  Method (:) ",e);
        }finally {
            if(session != null) {
                session.close();
            }
        }
        return userProfile;
    }
}
