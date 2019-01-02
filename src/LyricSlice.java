import java.util.*;

public class LyricSlice /*implements Comparable<LyricSlice>*/ {

// =-=-= Instance Variables =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    HashMap<String,LyricCoords> coords;
    HashMap<String,String> referenceStrings;
    Integer category;
    String annotation;
    ArrayList<LyricSlice> listOfSlices;


// =-=-= Constructor(s) =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    public LyricSlice(HashMap<String,String> reference, ArrayList<LyricSlice> slices, Integer category, String annotation) {
        coords = new HashMap<String,LyricCoords>();
        for (String lang : reference.keySet()) {
            coords.put(lang, new LyricCoords(null, null));
        }
        referenceStrings = reference;
        listOfSlices = slices;
        this.category = category;
        this.annotation = annotation;
    }

    public LyricSlice(HashMap<String,String> reference, ArrayList<LyricSlice> slices, Integer category) {
        this(reference, slices, category, "");
    }

    public LyricSlice(HashMap<String,String> reference, ArrayList<LyricSlice> slices, String annotation) {
        this(reference, slices, null, annotation);
    }

    public LyricSlice(HashMap<String,String> reference, ArrayList<LyricSlice> slices) {
        this(reference, slices, null, "");
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
        return category;
    }

    public String getCategoryStr() {
        if (category == null) {
            return "";
        }
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
        StringBuilder newReference = new StringBuilder(referenceStrings.get(key));
        Integer newstart = Math.min(start, end);
        Integer newend = Math.max(start, end) + 1;

        // Remove current closing bracket, if it exists
        if (coordSet.getEnd() != null) {
            if (newReference.charAt(coordSet.getEnd()) == ']') {
                newReference.deleteCharAt(coordSet.getEnd());
            } else {
                System.out.println("Character " + coordSet.getEnd() + " of string \"" + newReference + " does not match close bracket \"]\" ");
            }
        }

        // Remove current opening bracket, if it exists
        if (coordSet.getStart() != null) {
            if (newReference.charAt(coordSet.getStart()) == '[') {
                newReference.deleteCharAt(coordSet.getStart());
            } else {
                System.out.println("Character " + coordSet.getStart() + " of string \"" + newReference + " does not match open bracket \"[\" ");
            }
        }

        // Change other slices to match removal of brackets
        for (LyricSlice slice : listOfSlices) {
            if (slice != this) {
                if (coordSet.getStart() != null) slice.updateReference(key, coordSet.getStart(), -1);
                if (coordSet.getEnd() != null) slice.updateReference(key, coordSet.getEnd(), -1);
            }
        }

        // Set new start and new end safely
        coordSet.setCoordsBound(newstart, newend, newReference.length()+1);

        // Re-insert brackets into the reference string and save it to the HashMap
        newReference.insert(coordSet.getStart(), "[");
        newReference.insert(coordSet.getEnd(), "]");
        referenceStrings.put(key, newReference.toString());

        // Change other slices to match reinserted brackets
        for (LyricSlice slice : listOfSlices) {
            if (slice != this) {
                if (coordSet.getStart() != null) slice.updateReference(key, coordSet.getStart(), 1);
                if (coordSet.getEnd() != null) slice.updateReference(key, coordSet.getEnd(), 1);
            }
        }

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
            result += lang + " " + coords.get(lang) + ": \"" + getEnclosedPlaintext(lang) + "\"\n";
        }
        return result;
    }

    public String getEnclosedPlaintext(String key) {
        return getEnclosedBracketedText(key).replace("[", "").replace("]", "");
    }

    public String getEnclosedBracketedText(String key) {
        if (coords.get(key).hasNull()) {
            return "";
        }
        return referenceStrings.get(key).substring(coords.get(key).getStart()+1, coords.get(key).getEnd());
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

    public LyricCoords(Integer start, Integer end) {
        this.start = start;
        this.end = end;
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
        return setStartBound(newstart, null);
    }

    public Integer setStartBound(Integer newstart, Integer maxlength) {
        Integer oldstart = start;
        if (maxlength == null)
            start = newstart;
        else if (newstart < 0)
            start = 0;
        else if (newstart > maxlength)
            start = maxlength;
        else
            start = newstart;
        return oldstart;
    }

    public Integer setEnd(Integer newend) {
        return setEndBound(newend, null);
    }

    public Integer setEndBound(Integer newend, Integer maxlength) {
        Integer oldend = end;
        if (maxlength == null)
            end = newend;
        else if (newend < 0)
            end = 0;
        else if (newend > maxlength)
            end = maxlength;
        else
            end = newend;
        return oldend;
    }

    public void setCoords(Integer newstart, Integer newend) {
        setCoordsBound(newstart, newend, null);
    }

    public void setCoordsBound(Integer newstart, Integer newend, Integer maxlength) {
        setStartBound(newstart, maxlength);
        setEndBound(newend, maxlength);
    }

    public void moveCoords(Integer startoffset, Integer endoffset) {
        moveCoordsBound(startoffset, endoffset, null);
    }

    public void moveCoordsBound(Integer startoffset, Integer endoffset, Integer maxlength) {
        setCoordsBound(start + startoffset, end + endoffset, maxlength);
    }

    public void updateReference(Integer index, Integer length, Integer referenceLength) {
        if (!hasNull()) {
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

    public Boolean hasNull() {
        return start == null || end == null;
    }

    public Boolean isNull() {
        return start == null && end == null;
    }

    public String toString() {
        return "(" + start + ", " + end + ")";
    }
}