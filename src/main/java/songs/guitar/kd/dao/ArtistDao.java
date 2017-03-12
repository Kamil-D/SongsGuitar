package songs.guitar.kd.dao;

import songs.guitar.kd.model.db.Artist;
import songs.guitar.kd.model.db.Song;

import java.util.List;

/**
 * Created by Kamil on 2017-03-10.
 */
public interface ArtistDao {

    void saveArtist(Artist artist);

    void deleteArtist(Artist artist);

    void updateArtist(Artist artist);

    List<Artist> getAllArtist();

    Artist getArtistById(int id);

    Artist getArtistByName(String name);

}
