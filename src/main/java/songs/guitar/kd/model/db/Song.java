package songs.guitar.kd.model.db;


import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Kamil on 2017-03-09.
 */
@Entity
@Table
public class Song {

    @Id
    @SequenceGenerator(name = "songSequence", sequenceName = "songSequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "songSequence")
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




    //@JoinColumn(name="artistReference", referencedColumnName="id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Artist artist;

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }


    @OneToMany(fetch = FetchType.LAZY, targetEntity = SongSection.class, mappedBy = "song")
    private Set<SongSection> songSectionHashSet = new HashSet<SongSection>(0);

    public Set<SongSection> getSongSectionHashSet() {
        return songSectionHashSet;
    }

    public void setSongSectionHashSet(Set<SongSection> songSectionHashSet) {
        this.songSectionHashSet = songSectionHashSet;
    }



    @OneToOne(orphanRemoval = true, cascade = CascadeType.PERSIST)
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
