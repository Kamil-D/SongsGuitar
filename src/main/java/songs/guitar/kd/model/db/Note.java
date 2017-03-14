package songs.guitar.kd.model.db;

import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Created by Kamil on 2017-03-09.
 */
@Entity
@Table
public class Note {

    @Id
    @SequenceGenerator(name = "noteSequence", sequenceName = "noteSequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "noteSequence")
    @Column
    @Type(type = "int")
    private int id;

    @Column(nullable = false)
    @Type(type = "text")
    private String noteText;




    @OneToOne(targetEntity = Song.class, mappedBy = "note")
    private Song song;

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }
}
