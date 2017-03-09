package songs.guitar.kd.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class CompetitionTableRow {

    SimpleStringProperty name;
    SimpleIntegerProperty points;


    public CompetitionTableRow(Competition competitionObject) {
        this.name = new SimpleStringProperty(competitionObject.getName());
        this.points = new SimpleIntegerProperty(competitionObject.getPoints());
    }


    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public SimpleIntegerProperty pointsProperty() {
        return points;
    }

    public int getPoints() {
        return points.getValue();
    }

    public void setPoints(SimpleIntegerProperty points) {
        this.points = points;
    }

}
