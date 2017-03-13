package songs.guitar.kd.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import songs.guitar.kd.dao.AbstractDao;
import songs.guitar.kd.dao.SongDao;
import songs.guitar.kd.model.db.Artist;
import songs.guitar.kd.model.db.Song;
import songs.guitar.kd.util.Util;

import java.util.ArrayList;
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
    public void saveSongExistingArtist(Song song, String artistName) {

        startSessionAndTransaction();

        Criteria criteria = session.createCriteria(Artist.class);
        criteria.add(Restrictions.eq("artistName", artistName));

        song.setArtist((Artist) criteria.uniqueResult());

        session.persist(song);

        endTransactionSession();
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
        List<Song> songList;

        startSessionAndTransaction();
        Criteria criteria = createEntityCriteria();
        songList = criteria.list();
        endTransactionSession();
        return songList;
    }

    @Override
    public List<Song> getAllArtistSongs(int artistId) {
        List<Song> songList;

        startSessionAndTransaction();
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("artist.id", artistId));
        songList = criteria.list();
        endTransactionSession();
        return songList;
    }

    @Override
    public List<Song> getNotLearnedSongs() {
        List<Song> songList;

        startSessionAndTransaction();
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.not(Restrictions.ilike("learnedLevel", Util.learnedLevelStrings[9])));
        criteria.add(Restrictions.not(Restrictions.ilike("learnedLevel", Util.learnedLevelStrings[10])));

        songList = criteria.list();
        endTransactionSession();
        return songList;
    }

    @Override
    public List<Song> getLearnedSongs() {
        List<Song> songList;

        startSessionAndTransaction();
        Criteria criteria = createEntityCriteria();

        criteria.add(Restrictions.disjunction()
                .add(Restrictions.ilike("learnedLevel", Util.learnedLevelStrings[9]))
                .add(Restrictions.ilike("learnedLevel", Util.learnedLevelStrings[10]))
        );

        songList = criteria.list();
        endTransactionSession();
        return songList;
    }

}
