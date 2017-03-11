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
public class Artist {

    @Id
    @GeneratedValue
    @Column
    @Type(type = "int")
    private int id;

    @Column(nullable = false)
    @Type(type = "text")
    private String artistName;



    @OneToMany(targetEntity = Song.class, mappedBy = "artist")
//    @JoinColumn(name="songReference", referencedColumnName="id")
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

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

}
