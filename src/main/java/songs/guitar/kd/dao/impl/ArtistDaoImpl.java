package songs.guitar.kd.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
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
        List<Artist> artistList;

        startSessionAndTransaction();
        Criteria criteria = createEntityCriteria();
        artistList = criteria.list();
        endTransactionSession();
        return artistList;
    }

//    @Override
//    public Artist getArtistById(int id) {
//        Artist artist;
//
//        startSessionAndTransaction();
//        Criteria criteria = createEntityCriteria();
//        criteria.add(Restrictions.eq("id", id));
//        artist = (Artist) criteria.uniqueResult();
//        endTransactionSession();
//        return artist;
//    }

    @Override
    public Artist getArtistByName(String name) {
        Artist artist;

        startSessionAndTransaction();
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("artistName", name));
        artist = (Artist) criteria.uniqueResult();
        endTransactionSession();
        return artist;
    }

}
