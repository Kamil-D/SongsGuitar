package songs.guitar.kd.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import songs.guitar.kd.dao.AbstractDao;
import songs.guitar.kd.dao.ArtistDao;
import songs.guitar.kd.model.db.Artist;

import java.util.List;

/**
 * Created by Kamil on 2017-03-10.
 */
public class ArtistDaoImpl extends AbstractDao<Integer, Artist> implements ArtistDao {

    @Override
    public void saveArtist(Artist artist) {
        persist(artist);
    }

    @Override
    public void deleteArtist(Artist artist) {
        deleteEntity(artist);
    }

    @Override
    public void updateArtist(Artist artist) {
        editEntity(artist);
    }

    @Override
    public List<Artist> getAllArtist() {
        Criteria criteria = createEntityCriteria();
        return (List<Artist>) criteria.list();
    }

    @Override
    public Artist getArtistById(int id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("id", id));
        return (Artist) criteria.uniqueResult();
    }
}
