import java.util.*;
import java.awt.Color;

public class LyricCategory {
    private String displayName;
    private Color displayColor;
    private String extraInfo;
    private Integer index;

    public LyricCategory(Integer index, String displayName) {
        this.index = index;
        this.displayName = displayName;
    }

    public LyricCategory(Integer index) {
        this(index, "");
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String newname) {
        displayName = newname;
    }

    public Color getDisplayColor() {
        return displayColor;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public Integer getIndex() {
        return index;
    }

    public String getIndexStr() {
        return Integer.toString(index);
    }

    public String toString() {
        return "Category " + getIndexStr() + ": " + getDisplayName();
    }
}