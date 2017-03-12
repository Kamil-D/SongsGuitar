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
    private Session session;

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
        startTransaction();
        session.persist(entity);
        endSession();
    }

    protected void deleteEntity(T entity) {
        startTransaction();
        session.delete(entity);
        endSession();
    }

    protected void editEntity(T entity) {
        startTransaction();
        session.update(entity);
        endSession();
    }

    protected Criteria createEntityCriteria() {
        return getSession().createCriteria(persistentClass);
    }

    protected void startTransaction() {
        session = getSession();
        session.beginTransaction();
    }

    protected void endSession() {
        session.getTransaction().commit();
        session.close();
    }
}
