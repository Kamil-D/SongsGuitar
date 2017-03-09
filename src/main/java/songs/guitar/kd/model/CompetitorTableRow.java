package songs.guitar.kd.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class CompetitorTableRow {

    Competitor competitorObject;

    SimpleStringProperty name;
    SimpleIntegerProperty rank;
    SimpleIntegerProperty amountCompetitions;
    SimpleIntegerProperty score;



    public CompetitorTableRow(Competitor competitorObject) {
        this.competitorObject = competitorObject;

        this.name = new SimpleStringProperty(this.competitorObject.getName());
        this.rank = new SimpleIntegerProperty(this.competitorObject.getRank());
        this.amountCompetitions = new SimpleIntegerProperty(this.competitorObject.getCompetitions().size());
        this.score = new SimpleIntegerProperty(this.competitorObject.getScore());
    }



    public Competitor getCompetitorObject() {
        return competitorObject;
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

    public SimpleIntegerProperty rankProperty() {
        return rank;
    }

    public int getRank() {
        return rank.get();
    }

    public void setRank(int rank) {
        this.rank.set(rank);
    }

    public SimpleIntegerProperty amountCompetitionsProperty() {
        return amountCompetitions;
    }

    public int getAmountCompetitions() {
        return amountCompetitions.get();
    }

    public void setAmountCompetitions(int amountCompetitions) {
        this.amountCompetitions.set(amountCompetitions);
    }

    public SimpleIntegerProperty scoreProperty() {
        return score;
    }

    public int getScore() {
        return score.get();
    }

    public void setScore(int score) {
        this.score.set(score);
    }

}
