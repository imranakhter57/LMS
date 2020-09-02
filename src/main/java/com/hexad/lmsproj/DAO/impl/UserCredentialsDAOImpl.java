package com.hexad.lmsproj.DAO.impl;

import com.hexad.lmsproj.DAO.UserCredentialsDAO;
import com.hexad.lmsproj.datamodels.entity.UserCredentials;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserCredentialsDAOImpl implements UserCredentialsDAO {

    private static Logger logger = LogManager.getLogger(UserCredentialsDAOImpl.class);

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public UserCredentials getUserCredsByUsername(String username) {
        Session session = null;
        UserCredentials userCredentials = null;
        try {
            session = sessionFactory.openSession();
            userCredentials = (UserCredentials) session.createCriteria(UserCredentials.class).add(Restrictions.eq("userName",username)).uniqueResult();
        }catch(Exception e) {
            logger.error("(:) Error in  Method (:) ",e);
        }finally {
            if(session != null) {
                session.close();
            }
        }
        return userCredentials;
    }

    @Override
    public UserCredentials saveUserCred(UserCredentials userCredentials) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            session.saveOrUpdate(userCredentials);
            tx.commit();
        }catch(Exception e) {
            logger.error("(:) Error in  Method (:) ",e);
        }finally {
            if(session != null) {
                session.close();
            }
        }
        return userCredentials;
    }

    @Override
    public UserCredentials getUserCredsByUserId(Long userId) {
        Session session = null;
        UserCredentials userCredentials = null;
        try {
            session = sessionFactory.openSession();
            userCredentials = session.get(UserCredentials.class,userId);
        }catch(Exception e) {
            logger.error("(:) Error in  Method (:) ",e);
        }finally {
            if(session != null) {
                session.close();
            }
        }
        return userCredentials;
    }
}
