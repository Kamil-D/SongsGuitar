package songs.guitar.kd.dao;

import songs.guitar.kd.model.db.SongSection;

import java.util.List;

/**
 * Created by Kamil on 2017-03-16.
 */
public interface SongSectionDao {

    void saveSection(SongSection songSection);

    void deleteSection(SongSection songSection);

    void updateSection(SongSection songSection);

    List<SongSection> getAllSectionsBySong(int songId);

}
