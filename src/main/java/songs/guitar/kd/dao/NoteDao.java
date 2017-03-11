package songs.guitar.kd.dao;

import songs.guitar.kd.model.db.Note;

import java.util.List;

/**
 * Created by Kamil on 2017-03-10.
 */
public interface NoteDao {

    void saveNote(Note note);

    void deleteNote(Note note);

    void updateNote(Note note);

    List<Note> getAllNotes();

    Note getNoteById(int id);
}
