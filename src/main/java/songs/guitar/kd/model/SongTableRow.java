package songs.guitar.kd.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import songs.guitar.kd.model.db.Artist;
import songs.guitar.kd.model.db.Note;
import songs.guitar.kd.model.db.Song;

/**
 * Created by Kamil on 2017-03-09.
 */
public class SongTableRow {

    SimpleStringProperty songTitleName;
    SimpleStringProperty artistName;
    SimpleStringProperty difficultyLevel;
    SimpleStringProperty learnedLevel;
    SimpleStringProperty noteText;

    private Song song;
    private Artist artist;
    private Note note;

    public SongTableRow(Song song, Artist artist, Note note) {

        this.song = song;
        this.artist = artist;
        this.note = note;

        this.songTitleName = new SimpleStringProperty(song.getTitle());
        this.artistName = new SimpleStringProperty(artist.getArtistName());
        this.difficultyLevel = new SimpleStringProperty(song.getDifficultyLevel());
        this.learnedLevel = new SimpleStringProperty(song.getLearnedLevel());
        this.noteText = new SimpleStringProperty(note.getNoteText());
    }

    public SimpleStringProperty songTitleNameProperty() {
        return songTitleName;
    }

    public SimpleStringProperty artistNameProperty() {
        return artistName;
    }

    public SimpleStringProperty difficultyLevelProperty() {
        return difficultyLevel;
    }

    public SimpleStringProperty learnedLevelProperty() {
        return learnedLevel;
    }

    public SimpleStringProperty noteProperty() {
        return noteText;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }
}
