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
        coordSet.setCoordsBound(newstart, newend, 0, newReference.length()+1);

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

    /**
     * An {@code Integer} instance storing the start coordinate.
     */
    private Integer start;

    /**
     * An {@code Integer} instance storing the end coordinate.
     */
    private Integer end;

    /**
     * Constructs a newly allocated {@code LyricCoords} object that
     * stores the two specified coordinates.
     * 
     * @param start The first coordinate in the pair, representing
     *              the starting index.
     * @param end   The second coordinate in the pair, representing
     *              the ending index.
     */
    public LyricCoords(Integer start, Integer end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Copy constructor allocating a new {@code LyricCoords} object
     * that stores the same coordinates as the passed {@code LyricCoords}.
     * 
     * @param orig The original {@code LyricCoords} object to copy.
     */
    public LyricCoords(LyricCoords orig) {
        this(orig.getStart(), orig.getEnd());
    }

    /**
     * Get the first coordinate, representing the start.
     * 
     * @return An {@code Integer} representing the start coordinate.
     */
    public Integer getStart() {
        return start;
    }

    /**
     * Get the second coordinate, representing the end.
     * 
     * @return An {@code Integer} representing the end coordinate.
     */
    public Integer getEnd() {
        return end;
    }

    /**
     * Directly set the start coordinate to the passed argument, with no
     * checks or adjustments made.
     * 
     * @param newstart The new start coordinate.
     * @return The original start coordinate.
     */
    public Integer setStart(Integer newstart) {
        return setStartBound(newstart, null, null);
    }

    /**
     * Safely set the start coordinate by capping it at 0 and the given
     * maxlength. That is, if the new coordinate is less than 0 or greater
     * than maxlength, start will instead be set to that value; otherwise,
     * it will be set to the passed value. If maxlength is null, there will
     * be no bounds, and start will be set to newstart regardless of its
     * value.
     * 
     * @param newstart  The new start coordinate.
     * @param maxlength The maximum allowed value for the new starting coordinate.
     * @return The original start coordinate.
     */
    public Integer setStartBound(Integer newstart, Integer minval, Integer maxval) {
        Integer oldstart = start;
        if (maxval != null && newstart > maxval)
            start = maxval;
        else if (minval != null && newstart < minval)
            start = minval;
        else
            start = newstart;
        return oldstart;
    }

    /**
     * Directly set the end coordinate to the passed argument, with no
     * checks or adjustments made.
     * 
     * @param newend The new end coordinate.
     * @return The original end coordinate.
     */
    public Integer setEnd(Integer newend) {
        return setEndBound(newend, null, null);
    }

    /**
     * Safely set the end coordinate by capping it at 0 and the given
     * maxlength. That is, if the new coordinate is less than 0 or greater
     * than maxlength, end will instead be set to that value; otherwise,
     * it will be set to the passed value. If maxlength is null, there will
     * be no bounds, and end will be set to newend regardless of its value.
     * 
     * @param newend  The new end coordinate.
     * @param maxlength The maximum allowed value for the new ending coordinate.
     * @return The original end coordinate.
     */
    public Integer setEndBound(Integer newend, Integer minval, Integer maxval) {
        Integer oldend = end;
        if (maxval != null && newend > maxval)
            end = maxval;
        else if (minval != null && newend < minval)
            end = minval;
        else
            end = newend;
        return oldend;
    }

    /**
     * Directly set the start and end coordinates to the passed arguments,
     * with no checks or adjustments made.
     * 
     * @param newstart The new start coordinate.
     * @param newend   The new end coordinate.
     */
    public void setCoords(Integer newstart, Integer newend) {
        setCoordsBound(newstart, newend, null, null);
    }

    /**
     * Safely set both coordinates by capping them at 0 and the given
     * maxlength. That is, if the new coordinate is less than 0 or greater
     * than maxlength, it will instead be set to that value; otherwise,
     * it will be set to the passed value. If maxlength is null, there will
     * be no bounds, and the coordinates will be set directly regardless of
     * the new values.
     * 
     * @param newstart The new start coordinate.
     * @param newend   The new end coordinate.
     * @param maxlength The maximum allowed value for the new coordinates.
     */
    public void setCoordsBound(Integer newstart, Integer minstartval, Integer maxstartval, Integer newend, Integer minendval, Integer maxendval) {
        setStartBound(newstart, minstartval, maxstartval);
        setEndBound(newend, minendval, maxendval);
    }

    public void setCoordsBound(Integer newstart, Integer newend, Integer minval, Integer maxval) {
        setCoordsBound(newstart, minval, maxval, newend, minval, maxval);
    }

    /**
     * Directly shift the start and end coordinates by  the passed values,
     * with no checks or adjustments made. The new coordinates will be
     * start + startoffset and end + endoffset.
     * 
     * @param startoffset The amount to change the start coordinate by.
     * @param endoffset   The amount to change the end coordinate by.
     */
    public void moveCoords(Integer startoffset, Integer endoffset) {
        moveCoordsBound(startoffset, endoffset, null, null);
    }

    /**
     * Safely shift both coordinates by capping them at 0 and the given
     * maxlength. That is, if the new coordinate is less than 0 or greater
     * than maxlength, it will instead be set to that value; otherwise, it
     * will be adjusted by the passed value. If maxlength is null, there will
     * be no bounds, and the coordinates will be set directly regardless of
     * the new values.
     * 
     * @param startoffset The amount to change the start coordinate by.
     * @param endoffset   The amount to change the end coordinate by.
     * @param maxlength The maximum allowed value for the new coordinates.
     */
    public void moveCoordsBound(Integer startoffset, Integer minstartval, Integer maxstartval, Integer endoffset, Integer minendval, Integer maxendval) {
        setCoordsBound(start + startoffset, minstartval, maxstartval, end + endoffset, minendval, maxendval);
    }

    public void moveCoordsBound(Integer startoffset, Integer endoffset, Integer minval, Integer maxval) {
        moveCoordsBound(startoffset, minval, maxval, endoffset, minval, maxval);
    }

    /**
     * Adjust the start and end coordinates to reflect some change to the 
     * string they are meant to represent.
     * 
     * @param index           The index in the string where the change takes place.
     * @param length          The number of characters inserted or deleted.
     * @param referenceLength The length of the full reference string after the transformation.
     */
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
            moveCoordsBound(startOffset, endOffset, 0, referenceLength);
        }
    }

    /**
     * Return true if either the start or end coordinate is currently
     * set to null.
     * 
     * @return whether one coordinate is null.
     */
    public Boolean hasNull() {
        return start == null || end == null;
    }

    /**
     * Return true if both the start and end coordinates are currently
     * set to null.
     * 
     * @return whether both coordinates are null.
     */
    public Boolean isNull() {
        return start == null && end == null;
    }

    public String toString() {
        return "(" + start + ", " + end + ")";
    }
}