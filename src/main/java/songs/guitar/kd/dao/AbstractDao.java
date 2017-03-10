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
        Session session = getSession();

        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();

        session.close();
    }

    protected void deleteEntity(T entity) {
        getSession().delete(entity);
    }

    protected void editEntity(T entity) {
        getSession().update(entity);
    }

    protected Criteria createEntityCriteria() {
        return getSession().createCriteria(persistentClass);
    }

}
