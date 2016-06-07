package com.excilys.computerdatabase.persistence;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.computerdatabase.entity.User;

@Component("userDAO")
public class UserDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDAO.class);

    @Autowired
    protected SessionFactory sf;

    /**
     * UserDAO main constructor.
     */
    public UserDAO() {
    }

    protected final Session getSession() {
        try {
            Session session = sf.getCurrentSession();
            return session;
        } catch (HibernateException e) {
            Session session = sf.openSession();
            return session;
        }
    }

    public void setSessionFactory(SessionFactory sf) {
        this.sf = sf;
    }

    /**
     * getting user from login.
     *
     * @param login
     *            user login
     * @return user from db
     */
    public User findByLogin(String login) {
        LOGGER.debug("getting User by login");
        String hql = "SELECT i FROM User as i WHERE login = :name";
        Session s = getSession();
        Query query = s.createQuery(hql);
        query.setParameter("name", login);
        User u = (User) query.uniqueResult();
        return u;
    }

    /**
     * adding user to db.
     *
     * @param u
     *            user to add
     * @return fresh user from db
     */
    public User add(User u) {
        LOGGER.debug("adding user to db");
        getSession().save(u);
        return u;
    }

    /**
     * delete user from db.
     *
     * @param userId
     *            user id to delete
     * @return 1 if success 0 else
     */
    public int delete(Long userId) {
        LOGGER.debug("delete a User");
        Session s = getSession();
        Query query = s.createQuery("delete User where id = :ID");
        query.setParameter("ID", userId);
        int result = query.executeUpdate();
        return result;
    }
}
