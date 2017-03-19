package songs.guitar.kd.util.enums;

/**
 * Created by Kamil on 2017-03-15.
 */
public enum SectionTypeEnum {

    TYPE_1("verse"),
    TYPE_2("chorus"),
    TYPE_3("bridge"),
    TYPE_4("intro"),
    TYPE_5("outro"),
    TYPE_6("solo");


    SectionTypeEnum(String level) {
        this.type = level;
    }

    private String type;

    public String getType() {
        return type;
    }

}
