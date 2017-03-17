package songs.guitar.kd.model.table;

import javafx.beans.property.SimpleStringProperty;
import songs.guitar.kd.model.db.SongSection;

/**
 * Created by Kamil on 2017-03-16.
 */
public class SectionTableRow {


    private SimpleStringProperty sectionType;
    private SimpleStringProperty sectionTitle;
    private SimpleStringProperty sectionLearnedLevel;

    private SongSection songSection;

    public SectionTableRow(SongSection songSection) {

        this.songSection = songSection;

        setSimpleProperties(songSection);
    }

    private void setSimpleProperties(SongSection songSection) {
        sectionType = new SimpleStringProperty(songSection.getType());
        sectionTitle = new SimpleStringProperty(songSection.getTitle());
        sectionLearnedLevel = new SimpleStringProperty(songSection.getLearnedLevel());
    }


    public String getSectionType() {
        return sectionType.get();
    }

    public SimpleStringProperty sectionTypeProperty() {
        return sectionType;
    }

    public void setSectionType(String sectionType) {
        this.sectionType.set(sectionType);
    }

    public String getSectionTitle() {
        return sectionTitle.get();
    }

    public SimpleStringProperty sectionTitleProperty() {
        return sectionTitle;
    }

    public void setSectionTitle(String sectionTitle) {
        this.sectionTitle.set(sectionTitle);
    }

    public String getSectionLearnedLevel() {
        return sectionLearnedLevel.get();
    }

    public SimpleStringProperty sectionLearnedLevelProperty() {
        return sectionLearnedLevel;
    }

    public void setSectionLearnedLevel(String sectionLearnedLevel) {
        this.sectionLearnedLevel.set(sectionLearnedLevel);
    }

    public SongSection getSongSection() {
        return songSection;
    }

    public void setSongSection(SongSection songSection) {
        this.songSection = songSection;
    }
}
