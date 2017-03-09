package songs.guitar.kd.model;


public class Competition {

    String name;
    int points;



    public Competition() {
    }

    public Competition(String name, int points) {
        this.name = name;
        this.points = points;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
