package songs.guitar.kd.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import java.util.ArrayList;
import java.util.List;


public class Competitor {

    String name;
    int rank;
    int amountCompetitions;
    int score;

    List<Competition> competitions = new ArrayList<>();


    public Competitor(String name) {

        this.name = name;
    }



    @XmlElements(@XmlElement(name = "competition"))
    public List<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(List<Competition> tournaments) {
        this.competitions = tournaments;
    }

    public void addCompetition(Competition competition) {
        this.competitions.add(competition);
        updateScore();
    }

    public void updateScore() {
        this.amountCompetitions = competitions.size();
        score = 0;
        for(Competition competition : competitions){
            score += competition.getPoints();
        }
    }


    @XmlAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute
    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @XmlAttribute
    public int getAmountCompetitions() {
        return amountCompetitions;
    }

    public void setAmountCompetitions(int amountCompetitions) {
        this.amountCompetitions = amountCompetitions;
    }

    @XmlAttribute
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


}
