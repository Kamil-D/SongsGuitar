package songs.guitar.kd.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import songs.guitar.kd.dao.AbstractDao;
import songs.guitar.kd.dao.ArtistDao;
import songs.guitar.kd.dao.NoteDao;
import songs.guitar.kd.model.db.Note;
import songs.guitar.kd.model.db.Song;

import java.util.List;

/**
 * Created by Kamil on 2017-03-10.
 */
public class NoteDaoImpl extends AbstractDao<Integer, Note> implements NoteDao {


    @Override
    public void saveNote(Note note) {
        persist(note);
    }

    @Override
    public void deleteNote(Note note) {
        deleteEntity(note);
    }

    @Override
    public void updateNote(Note note) {
        editEntity(note);
    }

    @Override
    public List<Note> getAllNotes() {
        Criteria criteria = createEntityCriteria();
        return (List<Note>) criteria.list();
    }

    @Override
    public Note getNoteById(int id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("id", id));
        return (Note) criteria.uniqueResult();    }

}
