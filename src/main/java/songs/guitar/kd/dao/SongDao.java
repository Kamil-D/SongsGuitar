package songs.guitar.kd.dao;

import songs.guitar.kd.model.db.Artist;
import songs.guitar.kd.model.db.Song;

import java.util.List;

/**
 * Created by Kamil on 2017-03-10.
 */
public interface SongDao {

    void saveSong(Song song);

    void saveSongExistingArtist(Song song, String artistName);

    void deleteSong(Song song);

    void updateSong(Song song);

    List<Song> getAllSongs();

    List<Song> getAllArtistSongs(int artistId);

    List<Song> getNotLearnedSongs();

    List<Song> getLearnedSongs();
}
