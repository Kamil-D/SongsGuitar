package songs.guitar.kd.dao.impl;

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
        return null;
    }

    @Override
    public List<Song> getSongByArtist(Artist artist) {
        return null;
    }

}