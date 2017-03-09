package songs.guitar.kd.model;


import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Created by Kamil on 2017-03-09.
 */
@Table(name = "notes")
public class Song {

    @Id
    @GeneratedValue
    @Column(name = "id")
    @Type(type = "int")
    private int id;

    @Column(name = "title", nullable = false)
    @Type(type = "text")
    private String title;

}
