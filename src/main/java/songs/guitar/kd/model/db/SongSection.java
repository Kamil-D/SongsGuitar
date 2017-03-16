package songs.guitar.kd.model.db;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Kamil on 2017-03-15.
 */
@Entity
@Table
public class SongSection {

    @Id
    @SequenceGenerator(name = "sectionSequence", sequenceName = "sectionSequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sectionSequence")
    @Column
    @Type(type = "int")
    private int id;

    @Column(nullable = false)
    @Type(type = "text")
    private String title;

    @Column(nullable = false)
    @Type(type = "text")
    private String type;

    @Column(nullable = false)
    @Type(type = "text")
    private String learnedLevel;




    @OneToMany(targetEntity = Song.class, mappedBy = "songSection")
    private Set<Song> songHashSet = new HashSet<Song>(0);

    public Set<Song> getSongHashSet() {
        return songHashSet;
    }

    public void setSongHashSet(Set<Song> songHashSet) {
        this.songHashSet = songHashSet;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLearnedLevel() {
        return learnedLevel;
    }

    public void setLearnedLevel(String learnedLevel) {
        this.learnedLevel = learnedLevel;
    }


}
