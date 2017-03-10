package songs.guitar.kd.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Kamil on 2017-03-09.
 */
public class SongTableRow {

    SimpleStringProperty songTitleName;
    SimpleStringProperty artistName;
    SimpleStringProperty difficultyLevel;
    SimpleStringProperty learnedLevel;
    SimpleStringProperty note;


    public SongTableRow(String songTitleName, String artistName, String difficultyLevel,
                        String learnedLevel, String note) {
        this.songTitleName = new SimpleStringProperty(songTitleName);
        this.artistName = new SimpleStringProperty(artistName);
        this.difficultyLevel = new SimpleStringProperty(difficultyLevel);
        this.learnedLevel = new SimpleStringProperty(learnedLevel);
        this.note = new SimpleStringProperty(note);
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
        return note;
    }
}
