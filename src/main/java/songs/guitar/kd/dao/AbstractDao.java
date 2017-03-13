package songs.guitar.kd.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import songs.guitar.kd.util.HibernateUtil;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public abstract class AbstractDao<PK extends Serializable, T> {

    private final Class<T> persistentClass;
    private SessionFactory sessionFactory;
    protected Session session;

    public AbstractDao() {
        this.persistentClass = (Class<T>) ((ParameterizedType)
                this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];

        sessionFactory = HibernateUtil.getSessionFactory();
    }

    protected Session getSession() {
        return sessionFactory.openSession();
    }

    public T getByKey(PK key) {
        return (T) getSession().get(persistentClass, key);
    }

    protected void persist(T entity) {
        startSessionAndTransaction();
        session.persist(entity);
        endTransactionSession();
    }

    protected void deleteEntity(T entity) {
        startSessionAndTransaction();
        session.delete(entity);
        endTransactionSession();
    }

    protected void editEntity(T entity) {
        startSessionAndTransaction();
        session.update(entity);
        endTransactionSession();
    }

    protected Criteria createEntityCriteria() {
        return session.createCriteria(persistentClass);
    }

    protected void startSessionAndTransaction() {
        session = getSession();
        session.beginTransaction();
    }

    protected void endTransactionSession() {
        session.getTransaction().commit();
        session.close();
    }
}
