package songs.guitar.kd.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import songs.guitar.kd.dao.AbstractDao;
import songs.guitar.kd.dao.SongSectionDao;
import songs.guitar.kd.model.db.SongSection;

import java.util.List;

/**
 * Created by Kamil on 2017-03-16.
 */
public class SongSectionDaoImpl extends AbstractDao<Integer, SongSection> implements SongSectionDao {

    @Override
    public void saveSection(SongSection songSection) {
        persist(songSection);
    }

    @Override
    public void deleteSection(SongSection songSection) {
        deleteEntity(songSection);
    }

    @Override
    public void updateSection(SongSection songSection) {
        editEntity(songSection);
    }

    @Override
    public List<SongSection> getAllSectionsBySong(int songId) {

        List<SongSection> songSectionList;

        startSessionAndTransaction();
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("song.id", songId));
        songSectionList = criteria.list();
        endTransactionSession();

        return songSectionList;
    }
}
