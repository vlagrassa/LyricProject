public class LyricSlice implements Comparable<LyricSlice> {
    HashMap<String,LyricCoords> coords;
    HashMap<String,String> referenceStrings;
    Integer category;
    String annotation;

    public LyricSlice(HashMap<String,String> reference, Integer category, String annotation) {
        coords = new HashMap<String,LyricCoords>();
        referenceStrings = reference;
        category = category;
        annotation = annotation;
    }

    public LyricSlice(HashMap<String,String> reference, Integer category) {
        this(reference, category, "");
    }

    public LyricSlice(HashMap<String,String> reference, String annotation) {
        this(reference, -1, annotation);
    }

    public LyricSlice(HashMap<String,String> reference) {
        this(reference, -1, "");
    }

    public HashMap<String,String> getReferenceStrings() {
        return referenceStrings;
    }

    public String getReferenceString(String key) {
        return referenceStrings.get(key);
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

    public String getAnnotation() {
        return annotation;
    }

    public String setAnnotation(String newAnnotation) {
        String oldAnnotation = annotation;
        annotation = newAnnotation;
        return oldAnnotation;
    }

    public HashMap<String,LyricCoords> getCoords() {
        return coords;
    }

    public LyricCoords getCoord(String key) {
        return coords.get(key);
    }

    public Integer getStart(String key) {
        return coords.get(key).start;
    }

    public Integer getEnd(String key) {
        return coords.get(key).end;
    }

    public HashMap<String,LyricCoords> setCoords(HashMap<String,LyricCoords> newcoords) {
        HashMap<String,LyricCoords> oldcoords = coords;
        coords = newcoords;
        return oldcoords;
    }

    public LyricCoords setCoord(String key, LyricCoords newcoord) {
        LyricCoords oldcoord = coords.get(key);
        coords.set(key, newCoord);
        return oldcoord;
    }

    public Integer setStart(String key, Integer newstart) {
        LyricCoords coordSet = coords.get(key);
        Integer oldstart = coordSet.getStart();
        if (newstart < 0) {
            return coordSet.get(key).setStart(0);
        }
        else if (newstart > coordSet.getEnd()) {
            Integer oldend = coordSet.getEnd();
            coordSet.setEnd(newstart);
            return coordSet.setStart(oldend);
        }
        else {
            return coordSet.setStart(newstart);
        }
    }

    public Integer setEnd(String key, Integer newend) {
        LyricCoords coordSet = coords.get(key);
        Integer oldend = coordSet.getEnd();
        if (newend > referenceStrings.get(key).length()) {
            return coordSet.setEnd(referenceStrings.get(key).length());
        }
        else if (newend < coordSet.getStart()) {
            Integer oldstart = coordSet.getStart();
            coordSet.setStart(newend);
            return coordSet.setEnd(oldstart);
        } else {
            return coordSet.setEnd(newend);
        }
    }

    public Integer moveStart(String key, Integer offset) {
        return setStart(key, coords.get(key).getStart() + offset);
    }

    public Integer moveEnd(Integer offset) {
        return setEnd(key, coords.get(key).getEnd() + offset);
    }

    // // TODO: Double check that this logic works!
    // public int compareTo(LyricSlice b) {
    //     // If the slices have different starting points, order by those
    //     if (getStart() != b.getStart()) {
    //         return getStart.compareTo(b.getStart());
    //     }

    //     // If they have the same starting point, order by the end points
    //     else if (getEnd() != b.getEnd()) {
    //         return getEnd.compareTo(b.getEnd())
    //     }

    //     // If both are the same, order by category
    //     else {
    //         return category.compareTo(b.getCategory());
    //     }
    // }
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
        start = newstart;
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