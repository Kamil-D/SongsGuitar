package songs.guitar.kd.util.enumclass;

/**
 * Created by Kamil on 2017-03-15.
 */
public enum LearnedLevelEnum {

    LEVEL_0("0/10"),
    LEVEL_1("1/10"),
    LEVEL_2("2/10"),
    LEVEL_3("3/10"),
    LEVEL_4("4/10"),
    LEVEL_5("5/10"),
    LEVEL_6("6/10"),
    LEVEL_7("7/10"),
    LEVEL_8("8/10"),
    LEVEL_9("9/10"),
    LEVEL_10("10/10");

    LearnedLevelEnum(String level) {
        this.level = level;
    }

    private String level;

    public String getLevel() {
        return level;
    }

}
