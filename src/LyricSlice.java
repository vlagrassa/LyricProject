import java.util.*;

public class LyricSlice /*implements Comparable<LyricSlice>*/ {

// =-=-= Instance Variables =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    HashMap<String,LyricCoords> coords;
    HashMap<String,String> referenceStrings;
    Integer category;
    String annotation;


// =-=-= Constructor(s) =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    public LyricSlice(HashMap<String,String> reference, Integer category, String annotation) {
        coords = new HashMap<String,LyricCoords>();
        for (String lang : reference.keySet()) {
            coords.put(lang, new LyricCoords(0,0));
        }
        referenceStrings = reference;
        this.category = category;
        this.annotation = annotation;
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


// =-=-= Reference String Map =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    public HashMap<String,String> getReferenceStrings() {
        return referenceStrings;
    }

    public String getReferenceString(String key) {
        return referenceStrings.get(key);
    }


// =-=-= Category =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

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


// =-=-= Annotation =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    public String getAnnotation() {
        return annotation;
    }

    public String setAnnotation(String newAnnotation) {
        String oldAnnotation = annotation;
        annotation = newAnnotation;
        return oldAnnotation;
    }


// =-=-= Full Coordinates =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    public HashMap<String,LyricCoords> getCoordsMap() {
        return coords;
    }

    public LyricCoords getCoords(String key) {
        return coords.get(key);
    }

    public HashMap<String,LyricCoords> setCoordsMap(HashMap<String,LyricCoords> newcoords) {
        HashMap<String,LyricCoords> oldcoords = coords;
        coords = newcoords;
        return oldcoords;
    }

    public LyricCoords setCoords(String key, LyricCoords newcoord) {
        LyricCoords oldcoord = coords.get(key);
        coords.put(key, newcoord);
        return oldcoord;
    }


// =-=-= Coordinate Start and End =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    public Integer getStart(String key) {
        return coords.get(key).getStart();
    }

    public Integer getEnd(String key) {
        return coords.get(key).getEnd();
    }

    public LyricSlice setStartEnd(String key, Integer start, Integer end) {
        LyricCoords coordSet = coords.get(key);
        Integer oldstart = coordSet.getStart();
        Integer oldend = coordSet.getEnd();
        Integer newstart = Math.min(start, end);
        Integer newend = Math.max(start, end);

        if (newstart < 0) {
            coordSet.setStart(0);
        } else {
            coordSet.setStart(newstart);
        }

        if (newend > referenceStrings.get(key).length()) {
            coordSet.setEnd(referenceStrings.get(key).length());
        } else {
            coordSet.setEnd(newend);
        }

        // return (new LyricCoords(oldstart, oldend));
        return this;
    }

    public LyricSlice setStart(String key, Integer newstart) {
        return setStartEnd(key, newstart, coords.get(key).getEnd());
    }

    public LyricSlice setEnd(String key, Integer newend) {
        return setStartEnd(key, coords.get(key).getStart(), newend);
    }

    public LyricSlice moveStart(String key, Integer offset) {
        return setStart(key, coords.get(key).getStart() + offset);
    }

    public LyricSlice moveEnd(String key, Integer offset) {
        return setEnd(key, coords.get(key).getEnd() + offset);
    }

    public void updateReference(String key, Integer index, Integer length) {
        coords.get(key).updateReference(index, length, referenceStrings.get(key).length());
    }


// =-=-= Strings =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    public String toString() {
        String result = "";
        for (String lang : referenceStrings.keySet()) {
            result += lang + " " + coords.get(lang) + ": \"" + referenceStrings.get(lang).substring(coords.get(lang).getStart(), coords.get(lang).getEnd()) + "\"\n";
        }
        return result;
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

    public LyricCoords(LyricCoords orig) {
        this(orig.getStart(), orig.getEnd());
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

    public Integer setStartBound(Integer newstart, Integer maxlength) {
        Integer oldstart = start;
        if (newstart < 0)
            start = 0;
        else if (newstart > maxlength)
            start = maxlength;
        else
            start = newstart;
        return oldstart;
    }

    public Integer setEnd(Integer newend) {
        Integer oldend = end;
        end = newend;
        return oldend;
    }

    public Integer setEndBound(Integer newend, Integer maxlength) {
        Integer oldend = end;
        if (newend < 0)
            end = 0;
        else if (newend > maxlength)
            end = maxlength;
        else
            end = newend;
        return oldend;
    }

    public void setCoords(Integer s, Integer e) {
        setStart(s);
        setEnd(e);
    }

    public void setCoordsBound(Integer s, Integer e, Integer maxlength) {
        setStartBound(s, maxlength);
        setEndBound(e, maxlength);
    }

    public void moveCoords(Integer offsetS, Integer offsetE) {
        setCoords(start + offsetS, end + offsetE);
    }

    public void moveCoordsBound(Integer offsetS, Integer offsetE, Integer maxlength) {
        setCoordsBound(start + offsetS, end + offsetE, maxlength);
    }

    public void updateReference(Integer index, Integer length, Integer referenceLength) {
        if (!(start == 0 && end == 0)) {
            Integer startOffset = 0, endOffset = 0;
            if (length > 0) {
                if (start >  index) startOffset = length;
                if (end   >= index) endOffset   = length;
            }
            else if (length < 0) {
                if (start >= index) startOffset = length;
                if (end   >  index) endOffset   = length;
            }
            moveCoordsBound(startOffset, endOffset, referenceLength);
        }
    }

    public String toString() {
        return "(" + start + ", " + end + ")";
    }
}