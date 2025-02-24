package Config;

import org.hibernate.Session;

import Model.User;

public class HibernateDBUser {

    private final Session session;

    public HibernateDBUser() {
        session = HibernateDB.getSession(User.class); 
    }

    public User findUserByEmail(String email) {
        User user = null;
        try {
            session.beginTransaction();
            user = session.createQuery("FROM users WHERE email = :email", User.class)
                    .setParameter("email", email)
                    .uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e; 
        }
        return user;
    }

    public void saveUser(User user) throws Exception {
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e; 
        }
    }
}
