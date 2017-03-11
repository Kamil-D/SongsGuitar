package songs.guitar.kd.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import songs.guitar.kd.dao.AbstractDao;
import songs.guitar.kd.dao.SongDao;
import songs.guitar.kd.model.db.Artist;
import songs.guitar.kd.model.db.Song;

import java.util.List;

/**
 * Created by Kamil on 2017-03-10.
 */
public class SongDaoImpl extends AbstractDao<Integer, Song> implements SongDao {

    @Override
    public void saveSong(Song song) {
        persist(song);
    }

    @Override
    public void deleteSong(Song song) {
        deleteEntity(song);
    }

    @Override
    public void updateSong(Song song) {
        editEntity(song);
    }

    @Override
    public List<Song> getAllSongs() {
        Criteria criteria = createEntityCriteria();
        return (List<Song>) criteria.list();
    }

    @Override
    public List<Song> getAllArtistSongs(int artistId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("artist.id", artistId));
        return criteria.list();
    }

}
