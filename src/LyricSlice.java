public class LyricSlice implements Comparable<LyricSlice> {
    LyricCoords coords;
    String reference;
    Integer category;

    public LyricSlice(Integer s, Integer e, String ref, Integer c) {
        coords = new LyricCoords(s, e);
        reference = ref;
        category = c;
    }

    public String getReferenceString() {
        return reference;
    }

    public Integer getCategory() {
        return getCategory();
    }

    public String getCategoryStr() {
        return Integer.toString(category);
    }

    public Integer setCategory(Integer newcat) {
        Integer oldcat = category;
        category = newcat;
        return oldcat;
    }

    public Boolean isCategory(Integer matchcat) {
        return category == matchcat;
    }

    public LyricCoords getCoords() {
        return coords;
    }

    public Integer getStart() {
        return coords.start;
    }

    public Integer getEnd() {
        return coords.end;
    }

    public LyricCoords setCoords(LyricCoords newcoords) {
        LyricCoords oldcoords = coords;
        coords = newcoords;
        return oldcoords;
    }

    public Integer setStart(Integer newstart) {
        Integer oldstart = coords.getStart();
        if (newstart < 0) {
            return coords.setStart(0);
        }
        else if (newstart > coords.getEnd()) {
            Integer oldend = coords.getEnd();
            coords.setEnd(newstart);
            return coords.setStart(oldend);
        }
        else {
            return coords.setStart(newstart);
        }
    }

    public Integer setEnd(Integer newend) {
        Integer oldend = coords.getEnd();
        if (newend > reference.length()) {
            return coords.setEnd(reference.length());
        }
        else if (newend < coords.getStart()) {
            Integer oldstart = coords.getStart();
            coords.setStart(newend);
            return coords.setEnd(oldstart);
        } else {
            return coords.setEnd(newend);
        }
    }

    public Integer moveStart(Integer offset) {
        return setStart(coords.getStart() + offset);
    }

    public Integer moveEnd(Integer offset) {
        return setEnd(coords.getEnd() + offset);
    }

    // TODO: Double check that this logic works!
    public int compareTo(LyricSlice b) {
        // If the slices have different starting points, order by those
        if (getStart() != b.getStart()) {
            return getStart.compareTo(b.getStart());
        }

        // If they have the same starting point, order by the end points
        else if (getEnd() != b.getEnd()) {
            return getEnd.compareTo(b.getEnd())
        }

        // If both are the same, order by category
        else {
            return category.compareTo(b.getCategory());
        }
    }
}

class LyricCoords {
    private Integer start;
    private Integer end;

    public LyricCoords(Integer s, Integer e) {
        start = s;
        end = e;
    }

    public Integer getStart() {
        return start;
    }

    public Integer getEnd() {
        return end;
    }

    public Integer setStart(Integer newstart) {
        Integer oldstart = start;
        start = newstart
        return oldstart;
    }

    public Integer setEnd(Integer newend) {
        Integer oldend = end;
        end = newend;
        return oldend;
    }

    public void setCoords(Integer s, Integer e) {
        setStart(s);
        setEnd(e);
    }

    public void moveCoords(Integer offsetS, Integer offsetE) {
        setCoords(start + offsetS, end + offsetE);
    }
}