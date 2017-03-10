package songs.guitar.kd.model.db;


import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Created by Kamil on 2017-03-09.
 */
@Entity
@Table
public class Song {

    @Id
    @GeneratedValue
    @Column
    @Type(type = "int")
    private int id;

    @Column(nullable = false)
    @Type(type = "text")
    private String title;

    @Column(nullable = false)
    @Type(type = "text")
    private String difficultyLevel;

    @Column(nullable = false)
    @Type(type = "text")
    private String learnedLevel;



    //(cascade = CascadeType.ALL)
    //@JoinColumn(name="artistReference", referencedColumnName="id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    //@JoinColumn(name="artistReference", referencedColumnName="id")
    private Artist artist;

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }


    @OneToOne
    //@JoinColumn(name = "noteReference", nullable = false)
    private Note note;

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public String getLearnedLevel() {
        return learnedLevel;
    }

    public void setLearnedLevel(String learnedLevel) {
        this.learnedLevel = learnedLevel;
    }

}
