import java.util.*;

public class LyricSlice {
  // =-=-= Instance Variables =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    /**
     * A map from a {@code String} representing a given language to a set of
     * {@code LyricCoords} storing the position of the slice for that language.
     */
    HashMap<String,LyricCoords> coords;

    /**
     * A map from a {@code String} representing a given language to the text
     * associated with the overarching {@code LyricLine} in that language.
     */
    HashMap<String,String> referenceStrings;

    /**
     * An {@code Integer} storing the category of the slice -- generally part of
     * speech, type of phrase, etc. This is important for organizing slices in the
     * final product, and determines what color to use to highlight it.
     */
    Integer category;

    /**
     * A {@code String} storing information about the text contained in the slice,
     * generally relating to how it is translated or used.
     */
    String annotation;

    /**
     * The {@code ArrayList} stored by a {@code LyricLine} to which this {@code LyricSlice}
     * belongs. Used to update other slices when the bounds of this slice are changed.
     */
    ArrayList<LyricSlice> listOfSlices;


  // =-=-= Constructor(s) =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    /**
     * Constructs a newly allocated {@Code LyricSlice} object.
     * 
     * The {@code reference} and {@code slices} arguments are necessary, and will be
     * saved directly to the fields {@code referenceStrings} and {@code listOfSlices},
     * respectively.
     * 
     * If not provided, the argument {@code category} will default to {@code null},
     * and the argument {@code annotation} will default to the empty string {@code ""}.
     * 
     * The field {@code coords} will be constructed as a newly allocated {@code HashMap},
     * and a newly allocated, default {@code LyricCoords} object will be added to it for
     * each language string in {@code reference}. Each {@code LyricCoords} will start out
     * storing {@code null} for its {@code start} and {@code end} values.
     * 
     * @param reference  HashMap of languages to reference strings.
     * @param slices     Reference to list of slices to which this slice belongs.
     * @param category   The category of this slice.
     * @param annotation The annotation associated with this slice.
     */
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

    /**
     * Constructs a newly allocated {@Code LyricSlice} object.
     * 
     * The {@code reference} and {@code slices} arguments are necessary, and will be
     * saved directly to the fields {@code referenceStrings} and {@code listOfSlices},
     * respectively.
     * 
     * If not provided, the argument {@code category} will default to {@code null},
     * and the argument {@code annotation} will default to the empty string {@code ""}.
     * 
     * The field {@code coords} will be constructed as a newly allocated {@code HashMap},
     * and a newly allocated, default {@code LyricCoords} object will be added to it for
     * each language string in {@code reference}. Each {@code LyricCoords} will start out
     * storing {@code null} for its {@code start} and {@code end} values.
     * 
     * @param reference  HashMap of languages to reference strings.
     * @param slices     Reference to list of slices to which this slice belongs.
     * @param category   The category of this slice.
     */
    public LyricSlice(HashMap<String,String> reference, ArrayList<LyricSlice> slices, Integer category) {
        this(reference, slices, category, "");
    }

    /**
     * Constructs a newly allocated {@Code LyricSlice} object.
     * 
     * The {@code reference} and {@code slices} arguments are necessary, and will be
     * saved directly to the fields {@code referenceStrings} and {@code listOfSlices},
     * respectively.
     * 
     * If not provided, the argument {@code category} will default to {@code null},
     * and the argument {@code annotation} will default to the empty string {@code ""}.
     * 
     * The field {@code coords} will be constructed as a newly allocated {@code HashMap},
     * and a newly allocated, default {@code LyricCoords} object will be added to it for
     * each language string in {@code reference}. Each {@code LyricCoords} will start out
     * storing {@code null} for its {@code start} and {@code end} values.
     * 
     * @param reference  HashMap of languages to reference strings.
     * @param slices     Reference to list of slices to which this slice belongs.
     * @param annotation The annotation associated with this slice.
     */
    public LyricSlice(HashMap<String,String> reference, ArrayList<LyricSlice> slices, String annotation) {
        this(reference, slices, null, annotation);
    }

    /**
     * Constructs a newly allocated {@Code LyricSlice} object.
     * 
     * The {@code reference} and {@code slices} arguments are necessary, and will be
     * saved directly to the fields {@code referenceStrings} and {@code listOfSlices},
     * respectively.
     * 
     * If not provided, the argument {@code category} will default to {@code null},
     * and the argument {@code annotation} will default to the empty string {@code ""}.
     * 
     * The field {@code coords} will be constructed as a newly allocated {@code HashMap},
     * and a newly allocated, default {@code LyricCoords} object will be added to it for
     * each language string in {@code reference}. Each {@code LyricCoords} will start out
     * storing {@code null} for its {@code start} and {@code end} values.
     * 
     * @param reference  HashMap of languages to reference strings.
     * @param slices     Reference to list of slices to which this slice belongs.
     */
    public LyricSlice(HashMap<String,String> reference, ArrayList<LyricSlice> slices) {
        this(reference, slices, null, "");
    }


  // =-=-= Reference String Map =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    /**
     * Get the {@code HashMap} of reference strings directly. This is a
     * map from a {@code String} representing a given language to the text
     * associated with the overarching {@code LyricLine} in that language.
     * 
     * @return The map of reference strings.
     */
    public HashMap<String,String> getReferenceStrings() {
        return referenceStrings;
    }

    /**
     * Get the reference string for the given language -- the text
     * associated with the overarching {@code LyricLine} in that language.
     * 
     * @param key The language of the reference string
     * @return The reference string associated with that language.
     */
    public String getReferenceString(String key) {
        return referenceStrings.get(key);
    }


  // =-=-= Category =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    /**
     * Get the category of the slice. Note that this may have the default
     * value {@code null}.
     * 
     * @return The category of the slice.
     */
    public Integer getCategory() {
        return category;
    }

    /**
     * Get the category of the slice as a String. Note that if the category
     * has the default value {@code null}, this will return the empty string
     * {@code ""}.
     * 
     * @return The category as a {@code String}.
     */
    public String getCategoryStr() {
        if (category == null) {
            return "";
        }
        return Integer.toString(category);
    }

    /**
     * Change the category of the slice to the given value.
     * 
     * @param newcat The new category of the slice.
     * @return The original category of the slice.
     */
    public Integer setCategory(Integer newcat) {
        Integer oldcat = category;
        category = newcat;
        return oldcat;
    }

    /**
     * Return {@code true} if the category of the slice matches the
     * given category, and false otherwise. Note that this can be
     * used to check if the category is {@code null} by passing
     * {@code null} as the argument.
     * 
     * @param matchcat Value to compare category with.
     * @return Whether the slice's category matches the passed category.
     */
    public Boolean isCategory(Integer matchcat) {
        if (matchcat == null)
            return category == null;
        if (category == null)
            return false;
        return category == matchcat;
    }


  // =-=-= Annotation =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    /**
     * Get the annotation associated with this slice.
     * 
     * @return The annotation.
     */
    public String getAnnotation() {
        return annotation;
    }

    /**
     * Change the annotation associated with this slice.
     * 
     * @param newAnnotation The new annotation.
     * @return The original annotation.
     */
    public String setAnnotation(String newAnnotation) {
        String oldAnnotation = annotation;
        annotation = newAnnotation;
        return oldAnnotation;
    }


  // =-=-= Full Coordinates =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    /**
     * Get the full map from a {@code String} representing a given language to a set of
     * {@code LyricCoords} storing the position of the slice for that language.
     * 
     * @return HashMap from language to slice coordinates.
     */
    public HashMap<String,LyricCoords> getCoordsMap() {
        return coords;
    }

    /**
     * Get the {@code LyricCoords} storing the position of the slice for the given
     * language.
     * 
     * @param key The language to get coordinates for.
     * @return The coordinates for that language.
     */
    public LyricCoords getCoords(String key) {
        return coords.get(key);
    }

    /**
     * Replace the full map from a {@code String} representing a given language to a set of
     * {@code LyricCoords} storing the position of the slice for that language. Note that
     * this will not correct the bounds of the coordinates if they are outside the range of
     * the current reference string.
     * 
     * @param newcoords The new coordinate map.
     * @return The original coordinate map.
     */
    public HashMap<String,LyricCoords> setCoordsMap(HashMap<String,LyricCoords> newcoords) {
        HashMap<String,LyricCoords> oldcoords = coords;
        coords = newcoords;
        return oldcoords;
    }

    /**
     * Directly set the {@code LyricCoords} storing the position of the slice for the
     * given language. Note that this will not correct the bounds of the coordinates
     * if they are outside the range of the current reference string.
     * 
     * @param key       The language to set the coordinates for.
     * @param newcoords The new set of coordinates.
     * @return The original set of coordinates.
     */
    public LyricCoords setCoords(String key, LyricCoords newcoords) {
        LyricCoords oldcoords = coords.get(key);
        coords.put(key, newcoords);
        return oldcoords;
    }


  // =-=-= Coordinate Start and End =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    /**
     * Get the first coordinate, representing the start of the interval, for the
     * given language.
     * 
     * @param key The language to get the coordinate from.
     * @return An {@code Integer} representing the start coordinate.
     */
    public Integer getStart(String key) {
        return coords.get(key).getStart();
    }

    /**
     * Get the second coordinate, representing the end of the interval, for the
     * given language.
     * 
     * @param key The language to get the coordinate from.
     * @return An {@code Integer} representing the end coordinate.
     */
    public Integer getEnd(String key) {
        return coords.get(key).getEnd();
    }

    /**
     * Move the start and end coordinates of the slice, adjusting the coordinates of the
     * other slices in the line to match the change. Note that the coordinates are bound
     * by 0 and the length of the reference string -- that is, if they are outside this
     * range, they will be adjusted to fit within it. They may also be entered in either
     * order.
     * 
     * @param key   The language to set the coordinates for.
     * @param start The new start coordinate.
     * @param end   The new end coordinate.
     * @return The {@code LyricSlice} itself.
     */
    public LyricSlice setStartEnd(String key, Integer start, Integer end) {
        // TODO: Allow start or end to be null to not change that coordinate (and potentially cut down on runtime)

        // Get the coordinates for the given language
        LyricCoords coordSet = coords.get(key);

        // Initialize the new reference string to be a copy of the current one
        StringBuilder newReference = new StringBuilder(referenceStrings.get(key));

        // Correct for start and end being entered in reverse order
        // Note that if newstart has not yet been set, newend must be increased by 1 to adjust for the
        // bracket that will be inserted there
        Integer newstart = Math.min(start, end);
        Integer newend = Math.max(start, end) + (coordSet.getStart() == null ? 1 : 0);

        // Remove current closing bracket, if it exists
        if (coordSet.getEnd() != null) {
            if (newReference.charAt(coordSet.getEnd()) == ']') {
                newReference.deleteCharAt(coordSet.getEnd());
            } else {
                throw new RuntimeException("Character " + coordSet.getEnd() + " of string \"" + newReference + " does not match close bracket \"]\" ");
            }
        }

        // Remove current opening bracket, if it exists
        if (coordSet.getStart() != null) {
            if (newReference.charAt(coordSet.getStart()) == '[') {
                newReference.deleteCharAt(coordSet.getStart());
            } else {
                throw new RuntimeException("Character " + coordSet.getStart() + " of string \"" + newReference + " does not match open bracket \"[\" ");
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

        // Increment end by 1 if both bounds end up at the same point
        if (coordSet.getStart() == coordSet.getEnd()) {
            coordSet.moveEnd(1);
        }

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

    /**
     * Move the start coordinate of the slice, adjusting the coordinates of the other
     * slices in the line to match the change. The new start coordinate will be bound
     * by 0 and the length of the reference string -- that is, if it is outside this
     * range, it will be adjusted to fit within it.
     * 
     * @param key      The language to set the coordinates for.
     * @param newstart The new start coordinate.
     * @return The {@code LyricSlice} itself.
     */
    public LyricSlice setStart(String key, Integer newstart) {
        return setStartEnd(key, newstart, coords.get(key).getEnd());
    }

    /**
     * Move the end coordinate of the slice, adjusting the coordinates of the other
     * slices in the line to match the change. The new end coordinate will be bound
     * by 0 and the length of the reference string -- that is, if it is outside this
     * range, it will be adjusted to fit within it.
     * 
     * @param key    The language to set the coordinates for.
     * @param newend The new end coordinate.
     * @return The {@code LyricSlice} itself.
     */
    public LyricSlice setEnd(String key, Integer newend) {
        return setStartEnd(key, coords.get(key).getStart(), newend);
    }

    /**
     * Shift the start and end coordinates of the slice with reference to their current
     * values, adjusting the coordinates of the other slices in the line to match the
     * change. Note that the coordinates are bound by 0 and the length of the reference
     * string -- that is, if they are outside this range, they will be adjusted to fit
     * within it.
     * 
     * @param key         The language to set the coordinates for.
     * @param startoffset The amount to change the start coordinate by.
     * @param endoffset   The amount to change the end coordinate by.
     * @return The {@code LyricSlice} itself.
     */
    public LyricSlice moveStartEnd(String key, Integer startoffset, Integer endoffset) {
        return setStartEnd(key, coords.get(key).getStart() + startoffset, coords.get(key).getEnd() + endoffset);
    }

    /**
     * Shift the start coordinate of the slice with reference to its current value,
     * adjusting the coordinates of the other slices in the line to match the change.
     * The new start coordinate will be bound by 0 and the length of the reference
     * string -- that is, if it is outside this range, it will be adjusted to fit
     * within it.
     * 
     * @param key         The language to set the coordinates for.
     * @param startoffset The amount to change the start coordinate by.
     * @return The {@code LyricSlice} itself.
     */
    public LyricSlice moveStart(String key, Integer startoffset) {
        return setStart(key, coords.get(key).getStart() + startoffset);
    }

    /**
     * Shift the end coordinate of the slice with reference to its current value,
     * adjusting the coordinates of the other slices in the line to match the change.
     * The new end coordinate will be bound by 0 and the length of the reference
     * string -- that is, if it is outside this range, it will be adjusted to fit
     * within it.
     * 
     * @param key       The language to set the coordinates for.
     * @param endoffset The amount to change the end coordinate by.
     * @return The {@code LyricSlice} itself.
     */
    public LyricSlice moveEnd(String key, Integer endoffset) {
        return setEnd(key, coords.get(key).getEnd() + endoffset);
    }

    /**
     * Adjust the start and end coordinates for the given language to reflect
     * a change to the reference string.
     * 
     * @param key    The language to change the coordinates of.
     * @param index  The index in the string where the change takes place.
     * @param length The number of characters inserted or deleted.
     */
    public void updateReference(String key, Integer index, Integer length) {
        coords.get(key).updateReference(index, length, referenceStrings.get(key).length());
    }

    public LyricSlice addCoords(String key, Integer start, Integer end) {
        coords.put(key, coords.get(key).addCoords(start, end));
        return this;
    }


  // =-=-= Strings =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    public String toString() {
        String result = "";
        for (String lang : referenceStrings.keySet()) {
            result += lang + " " + coords.get(lang) + ": \"" + getEnclosedPlaintext(lang) + "\"\n";
        }
        return result;
    }

    /**
     * Get the characters inside the slice for the given language, without any brackets
     * from other slices.
     * 
     * @param key The language to take the substring from.
     * @return The substring contained in the slice.
     */
    public String getEnclosedPlaintext(String key) {
        return getEnclosedBracketedText(key).replace("[", "").replace("]", "");
    }

    /**
     * Get the characters inside the slice for the given language, including brackets
     * from other slices.
     * 
     * @param key The language to take the substring from.
     * @return The substring contained in the slice.
     */
    public String getEnclosedBracketedText(String key) {
        if (coords.get(key).hasNull()) {
            return "";
        }
        return coords.get(key).getBoundCharacters(referenceStrings.get(key));
    }
}

class LyricCoords {

    // Note that nearly all the logic for this class lives setCoordsBound, which is 
    // called by most of the other functions. This uses setValBound to determine the
    // new values for start and end.

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
     * Get the first coordinate, representing the start of the interval.
     * 
     * @return An {@code Integer} representing the start coordinate.
     */
    public Integer getStart() {
        return start;
    }

    /**
     * Get the second coordinate, representing the end of the interval.
     * 
     * @return An {@code Integer} representing the end coordinate.
     */
    public Integer getEnd() {
        return end;
    }

    /**
     * Get the difference between the start and end coordinates.
     * 
     * @return The length of the interval.
     */
    public Integer length() {
        return end - start;
    }

    /**
     * Return newval if it falls between minval and maxval; otherwise, return whichever
     * bound it is beyond. If either bound is {@code null}, the new value is not bound
     * in that direction.
     * 
     * @param newval The potential new value.
     * @param minval The minimum allowed value.
     * @param maxval The maximum allowed value.
     */
    private Integer setValBound(Integer newval, Integer minval, Integer maxval) {
        if (maxval != null && newval > maxval)
            return maxval;
        else if (minval != null && newval < minval)
            return minval;
        return newval;
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
     * Safely set the start coordinate by capping it with the given values.
     * That is, if the new coordinate is less than minval or greater than
     * maxval, start will instead be set to the appropriate min or max value,
     * respectively; otherwise, it will be set to the passed value. If either
     * of the bounding values is null, it will be treated as if that bound
     * doesn't exist.
     * 
     * @param newstart The new start coordinate.
     * @param minval   The minimum allowed value for the new starting coordinate.
     * @param maxval   The maximum allowed value for the new starting coordinate.
     * @return The original start coordinate.
     */
    public Integer setStartBound(Integer newstart, Integer minval, Integer maxval) {
        return setCoordsBound(newstart, null, minval, maxval).getStart();
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
     * Safely set the end coordinate by capping it with the given values.
     * That is, if the new coordinate is less than minval or greater than
     * maxval, end will instead be set to the appropriate min or max value,
     * respectively; otherwise, it will be set to the passed value. If either
     * of the bounding values is null, it will be treated as if that bound
     * doesn't exist.
     * 
     * @param newend The new end coordinate.
     * @param minval The minimum allowed value for the new ending coordinate.
     * @param maxval The maximum allowed value for the new ending coordinate.
     * @return The original end coordinate.
     */
    public Integer setEndBound(Integer newend, Integer minval, Integer maxval) {
        return setCoordsBound(null, newend, minval, maxval).getEnd();
    }

    /**
     * Directly set the start and end coordinates to the passed arguments,
     * with no checks or adjustments made.
     * 
     * @param newstart The new start coordinate.
     * @param newend   The new end coordinate.
     * @return The original coordinates.
     */
    public LyricCoords setCoords(Integer newstart, Integer newend) {
        return setCoordsBound(newstart, newend, null, null);
    }

    /**
     * Safely set both coordinates by capping them with the given values.
     * That is, if the new coordinate is less than the associated minval or
     * greater than the associated maxval, the coordinate will instead be
     * set to the appropriate min or max value, respectively; otherwise, it
     * will be set to the passed value. If either of the bounding values is
     * null, it will be treated as if that bound doesn't exist.
     * 
     * @param newstart    The new start coordinate.
     * @param minstartval The minimum allowed value for the new starting coordinate.
     * @param maxstartval The maximum allowed value for the new starting coordinate.
     * @param newend      The new end coordinate.
     * @param minendval   The minimum allowed value for the new ending coordinate.
     * @param maxendval   The maximum allowed value for the new ending coordinate.
     * @return The original coordinates.
     */
    public LyricCoords setCoordsBound(Integer newstart, Integer minstartval, Integer maxstartval, Integer newend, Integer minendval, Integer maxendval) {
        LyricCoords oldcoords = new LyricCoords(this);
        if (newstart != null)
            start = setValBound(newstart, minstartval, maxstartval);
        if (newend != null)
            end = setValBound(newend, minendval, maxendval);
        return oldcoords;
    }

    /**
     * Safely set both coordinates by capping them with the given values.
     * That is, if the new coordinate is less than the given minval or
     * greater than the given maxval, the coordinate will instead be set to
     * the appropriate min or max value, respectively; otherwise, it will
     * be set to the passed value. If either of the bounding values is null,
     * it will be treated as if that bound doesn't exist.
     * 
     * @param newstart The new start coordinate.
     * @param newend   The new end coordinate.
     * @param minval   The minimum allowed value for the new coordinates.
     * @param maxval   The maximum allowed value for the new coordinates.
     * @return The original coordinates.
     */
    public LyricCoords setCoordsBound(Integer newstart, Integer newend, Integer minval, Integer maxval) {
        return setCoordsBound(newstart, minval, maxval, newend, minval, maxval);
    }

    /**
     * Directly shift the start coordinate by the passed, with no
     * checks or adjustments made.
     * 
     * @param startoffset The amount to change the start coordinate by.
     * @return The original start coordinate.
     */
    public Integer moveStart(Integer startoffset) {
        return moveStartBound(startoffset, null, null);
    }

    /**
     * Safely shift the start coordinate by capping it with the given values.
     * That is, if the new coordinate is less than minval or greater than
     * maxval, start will instead be set to the appropriate min or max value,
     * respectively; otherwise, it will be set to the passed value. If either
     * of the bounding values is null, it will be treated as if that bound
     * doesn't exist.
     * 
     * @param startoffset The amount to change the start coordinate by.
     * @param minval      The minimum allowed value for the new starting coordinate.
     * @param maxval      The maximum allowed value for the new starting coordinate.
     * @return The original start coordinate.
     */
    public Integer moveStartBound(Integer startoffset, Integer minval, Integer maxval) {
        return moveCoordsBound(startoffset, 0, minval, maxval).getStart();
    }

    /**
     * Directly shift the end coordinate by the passed value, with no
     * checks or adjustments made.
     * 
     * @param endoffset The amount to change the end coordinate by.
     * @return The original end coordinate.
     */
    public Integer moveEnd(Integer endoffset) {
        return moveEndBound(endoffset, null, null);
    }

    /**
     * Safely shift the end coordinate by capping it with the given values.
     * That is, if the new coordinate is less than minval or greater than
     * maxval, end will instead be set to the appropriate min or max value,
     * respectively; otherwise, it will be set to the passed value. If either
     * of the bounding values is null, it will be treated as if that bound
     * doesn't exist.
     * 
     * @param endoffset The amount to change the end coordinate by.
     * @param minval    The minimum allowed value for the new end coordinate.
     * @param maxval    The maximum allowed value for the new end coordinate.
     * @return The original end coordinate.
     */
    public Integer moveEndBound(Integer endoffset, Integer minval, Integer maxval) {
        return moveCoordsBound(0, endoffset, minval, maxval).getEnd();
    }

    /**
     * Directly shift the start and end coordinates by  the passed values,
     * with no checks or adjustments made. The new coordinates will be
     * start + startoffset and end + endoffset.
     * 
     * @param startoffset The amount to change the start coordinate by.
     * @param endoffset   The amount to change the end coordinate by.
     * @return The original coordinates.
     */
    public LyricCoords moveCoords(Integer startoffset, Integer endoffset) {
        return moveCoordsBound(startoffset, endoffset, null, null);
    }

    /**
     * Safely shift both coordinates by capping them with the given values.
     * That is, if the new coordinate is less than the associated minval or
     * greater than the associated maxval, the coordinate will instead be
     * set to the appropriate min or max value, respectively; otherwise, it
     * will be set to the passed value. If either of the bounding values is
     * null, it will be treated as if that bound doesn't exist.
     * 
     * @param startoffset The amount to change the start coordinate by.
     * @param minstartval The minimum allowed value for the new starting coordinate.
     * @param maxstartval The maximum allowed value for the new starting coordinate.
     * @param endoffset   The amount to change the end coordinate by.
     * @param minendval   The minimum allowed value for the new ending coordinate.
     * @param maxendval   The maximum allowed value for the new ending coordinate.
     * @return The original coordinates.
     */
    public LyricCoords moveCoordsBound(Integer startoffset, Integer minstartval, Integer maxstartval, Integer endoffset, Integer minendval, Integer maxendval) {
        return setCoordsBound(start + startoffset, minstartval, maxstartval, end + endoffset, minendval, maxendval);
    }

    /**
     * Safely shift both coordinates by capping them with the given values.
     * That is, if the new coordinate is less than the given minval or
     * greater than the given maxval, the coordinate will instead be set to
     * the appropriate min or max value, respectively; otherwise, it will
     * be set to the passed value. If either of the bounding values is null,
     * it will be treated as if that bound doesn't exist.
     * 
     * @param startoffset The amount to change the start coordinate by.
     * @param endoffset   The amount to change the end coordinate by.
     * @param minval   The minimum allowed value for the new coordinates.
     * @param maxval   The maximum allowed value for the new coordinates.
     * @return The original coordinates.
     */
    public LyricCoords moveCoordsBound(Integer startoffset, Integer endoffset, Integer minval, Integer maxval) {
        return moveCoordsBound(startoffset, minval, maxval, endoffset, minval, maxval);
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

    public LyricCoordsDiscontinuous addCoords(Integer start, Integer end) {
        LyricCoordsDiscontinuous result = new LyricCoordsDiscontinuous(this);
        result.addCoords(start, end);
        return result;
    }

    public LyricSlice setStartEnd(String key, Integer start, Integer end) {
        // TODO: Allow start or end to be null to not change that coordinate (and potentially cut down on runtime)

        // Get the coordinates for the given language
        LyricCoords coordSet = coords.get(key);

        // Initialize the new reference string to be a copy of the current one
        StringBuilder newReference = new StringBuilder(referenceStrings.get(key));

        // Correct for start and end being entered in reverse order
        // Note that if newstart has not yet been set, newend must be increased by 1 to adjust for the
        // bracket that will be inserted there
        Integer newstart = Math.min(start, end);
        Integer newend = Math.max(start, end) + (coordSet.getStart() == null ? 1 : 0);

        // Remove current closing bracket, if it exists
        if (coordSet.getEnd() != null) {
            if (newReference.charAt(coordSet.getEnd()) == ']') {
                newReference.deleteCharAt(coordSet.getEnd());
            } else {
                throw new RuntimeException("Character " + coordSet.getEnd() + " of string \"" + newReference + " does not match close bracket \"]\" ");
            }
        }

        // Remove current opening bracket, if it exists
        if (coordSet.getStart() != null) {
            if (newReference.charAt(coordSet.getStart()) == '[') {
                newReference.deleteCharAt(coordSet.getStart());
            } else {
                throw new RuntimeException("Character " + coordSet.getStart() + " of string \"" + newReference + " does not match open bracket \"[\" ");
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

        // Increment end by 1 if both bounds end up at the same point
        if (coordSet.getStart() == coordSet.getEnd()) {
            coordSet.moveEnd(1);
        }

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

    public Boolean isDiscontinuous() {
        return false;
    }

    public String toString() {
        return "(" + start + ", " + end + ")";
    }

    public String getBoundCharacters(String text) {
        return text.substring(getStart()+1, getEnd());
    }
}

class LyricCoordsDiscontinuous extends LyricCoords {
    private ArrayList<LyricCoords> coordsList;

    public LyricCoordsDiscontinuous(Integer start, Integer end) {
        super(null, null);
        coordsList = new ArrayList<LyricCoords>();
        coordsList.add(new LyricCoords(start, end));
    }

    public LyricCoordsDiscontinuous(LyricCoords orig) {
        this(orig.getStart(), orig.getEnd());
    }

    // TODO: Constructor taking variable number of LyricCoords
    // public LyricCoordsDiscontinuous(LyricCoords... coordsList) {
    //     super(null, null);
    //     coordsList = new ArrayList<LyricCoords>();
    //     for (LyricCoords coords : coordsList) {
    //         this.coordsList.add(new LyricCoords(coordsList));
    //     }
    // }

    public void updateReference(Integer index, Integer length, Integer referenceLength) {
        for (LyricCoords coords : coordsList) {
            coords.updateReference(index, length, referenceLength);
        }
    }

    public LyricCoordsDiscontinuous addCoords(Integer start, Integer end) {
        coordsList.add(new LyricCoords(start, end));

        return this;
    }

    public Boolean hasNull() {
        for (LyricCoords coords : coordsList) {
            if (coords.hasNull())
                return true;
        }
        return false;
    }

    public Boolean isNull() {
        for (LyricCoords coords : coordsList) {
            if (!coords.isNull())
                return false;
        }
        return true;
    }

    public Boolean isDiscontinuous() {
        return true;
    }

    public String toString() {
        String result = "[";
        for (LyricCoords coords : coordsList) {
            result += coords;
            result += ", ";
        }
        result = result.substring(0, result.length()-2);
        result += "]";
        return result;
    }

    public String getBoundCharacters(String text) {
        String result = "";
        String intercalate = " (...) ";
        for (LyricCoords coords : coordsList) {
            result += coords.getBoundCharacters(text);
            result += intercalate;
        }
        result = result.substring(0, result.length() - intercalate.length());
        return result;
    }
}