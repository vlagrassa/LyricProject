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

    String header;


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

    /**
     * Return an {@code ArrayList} of all sets of coordinates associated
     * with the specified language in the overarching {@code LyricLine}.
     * 
     * @param key The language to get coordinates for.
     * @return A list of all coordinates on that language string.
     */
    public ArrayList<LyricCoords> getListOfCoords(String key) {
        ArrayList<LyricCoords> result = new ArrayList<LyricCoords>();
        for (LyricSlice slice : listOfSlices) {
            result.add(slice.getCoords(key));
        }
        return result;
    }

  // =-=-= Coordinate Start and End =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    /**
     * Get the first coordinate, representing the start of the interval, for the
     * given language.
     * 
     * If coords are discontinuous, returns the earliest start coordinate of all
     * the subsections.
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
     * If coords are discontinuous, returns the last end coordinate of all the
     * subsections.
     * 
     * @param key The language to get the coordinate from.
     * @return An {@code Integer} representing the end coordinate.
     */
    public Integer getEnd(String key) {
        return coords.get(key).getEnd();
    }

    /**
     * Helper method which calls setStartEnd on the given {@code LyricCoords} object
     * and handles updating the reference string with the result.
     * 
     * @param key            The language to change.
     * @param start          The new start coordinate.
     * @param end            The new end coordinate.
     * @param coordsToUpdate The {@code LyricCoords} to run {@code setStartEnd} on.
     * @return The altered {@code LyricCoords} object.
     */
    private LyricCoords callSetCoordsUpdatedOnCoords(String key, Integer start, Integer end, LyricCoords coordsToUpdate) {
        // Make a new mutable Stringbuilder to accumulate changes from setStartEnd
        StringBuilder referenceAccum = new StringBuilder(referenceStrings.get(key));

        // Run setStartEnd on the given LyricCoords, and save the result to newCoords
        LyricCoords newCoords = coordsToUpdate.setCoordsUpdated(start, end, referenceAccum, getListOfCoords(key));

        // Convert the changed StringBuilder back to a String and save it to the map of reference strings
        referenceStrings.put(key, referenceAccum.toString());

        // Return the new LyricCoords object
        return newCoords;
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
    public LyricSlice setCoordsUpdated(String key, Integer start, Integer end) {

        // Run setStartEnd on the LyricCoords at key and save the result back to the HashMap
        coords.put(key, callSetCoordsUpdatedOnCoords(key, start, end, coords.get(key)));

        // Return the current LyricSlice object
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
        return setCoordsUpdated(key, newstart, coords.get(key).getEnd());
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
        return setCoordsUpdated(key, coords.get(key).getStart(), newend);
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
        return setCoordsUpdated(key, coords.get(key).getStart() + startoffset, coords.get(key).getEnd() + endoffset);
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
    public void matchUpdatedReference(String key, Integer index, Integer length) {
        coords.get(key).matchUpdatedReference(index, length, referenceStrings.get(key).length());
    }

    /**
     * Create or extend a discontinuous coordinate set by adding more than one pair of
     * coordinates for a given language. Used to mark two separate sections of text as
     * part of the same Lyric slice.
     * 
     * Analogous to {@code LyricCoords.addCoords()}, but modifies the reference string
     * as well.
     * 
     * @see {@code LyricCoords.addCoords(Integer start, Integer end)}
     * 
     * @param key   The language to change the coordinates of.
     * @param start The start coordinate for the new pair.
     * @param end   The end coordinate for the new pair.
     * @return The {@code LyricSlice} itself.
     */
    public LyricSlice addCoords(String key, Integer start, Integer end) {
        // Run setStartEnd on a new set of LyricCoords and save the result to newCoords
        LyricCoords newCoords = callSetCoordsUpdatedOnCoords(key, start, end, new LyricCoords());

        // Add the new coords to the current set and save the result to the HashMap
        coords.put(key, coords.get(key).addCoords(newCoords));

        // Return the current LyricSlice object
        return this;
    }

    /**
     * Create or extend a discontinuous coordinate set by adding more than one pair of
     * coordinates for a given language. Used to mark two separate sections of text as
     * part of the same Lyric slice.
     * 
     * Note that this is analogous to {@code LyricCoords.addCoordsAsCopy()}, NOT to 
     * {@code LyricCoords.addCoords()}. To add a {@code LyricCoords} object directly
     * without formatting the reference string, consider using
     * {@code LyricSlice.get(key).addCoords(...)} instead.
     * 
     * @see {@code LyricCoords.addCoords(LyricCoords... origList)}
     * @see {@code LyricCoords.addCoordsAsCopy(LyricCoords... origList)}
     * 
     * @param key      The language to change the coordinates of.
     * @param origList A list of coordinates to add to this slice.
     * @return The {@code LyricSlice} itself.
     */
    public LyricSlice addCoords(String key, LyricCoords... origList) {
        for (LyricCoords orig : origList) {
            addCoords(key, orig.getStart(), orig.getEnd());
        }
        return this;
    }

    /**
     * Set the coordinates associated with the given language string to store
     * ({@code null}, {@code null}), effectively removing them from the Line.
     * Note that the whole {@code LyricCoords} object is not set to {@code null},
     * just its component coordinates.
     * 
     * @param key The language to remove the coordinates of.
     * @return The current {@code LyricSlice} object itself.
     */
    public LyricSlice removeCoords(String key) {
        setCoordsUpdated(key, null, null);
        return this;
    }

    /**
     * Remove the passed {@code LyricCoords} object from the slice, if it exists in
     * the {@code HashMap} of coordinates directly or exists as subcoordinates in a
     * {@code LyricCoordsDiscontinuous} object.
     * 
     * @param coordsToRemove The {@code LyricCoords} object to remove from the {@code LyricSlice}.
     * @return The current {@code LyricSlice} object itself.
     */
    public LyricSlice removeCoords(LyricCoords coordsToRemove) {
        // Search for the passed coords in each entry to the set of coords
        for (String key : coords.keySet()) {

            // If the coords are found directly, remove them directly
            if (coords.get(key) == coordsToRemove) {
                removeCoords(key);
            }

            // Otherwise, if the current coords are discontinuous, search for the passed coords inside them
            else if (coords.get(key).isDiscontinuous()) {
                for (LyricCoords c : coords.get(key).getCoordsList()) {

                    // If the coords are found...
                    if (c == coordsToRemove) {

                        // ...remove them from the reference string
                        callSetCoordsUpdatedOnCoords(key, null, null, coordsToRemove);

                        // ...replace the current HashMap entry with a modified version that doesn't have them
                        coords.put(key, coords.get(key).removeCoords(coordsToRemove));
                        
                        // ...break out of the loop to avoid a ConcurrentModification exception
                        break;
                    }
                }
            }
        }
        return this;
    }

    // TODO: Merge method for two LyricSlices -- possibly in LyricLine


  // =-=-= Add or Remove Ranges =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    /**
     * Return {@code true} if the specified ranges and/or sets of coordinates overlap
     * with one another, and {@code false} if they do not.
     * 
     * @see {@link #coordsOverlap(LyricCoords, Integer, Integer)}
     * @see {@link #coordsOverlap(Integer, Integer, LyricCoords)}
     * @see {@link #coordsOverlap(LyricCoords, LyricCoords)}
     * 
     * @param start1 The start coordinate of the first set.
     * @param end1   The end coordinate of the first set.
     * @param start2 The start coordinate of the second set.
     * @param end2   The end coordinate of the second set.
     * 
     * @return Whether the given ranges/coordinates overlap.
     */
    private Boolean coordsOverlap(Integer start1, Integer end1, Integer start2, Integer end2) {
        return !((start1 < start2 && end1 < start2) || (start2 < start1 && end2 < start1));
    }

    /**
     * Return {@code true} if the specified ranges and/or sets of coordinates overlap
     * with one another, and {@code false} if they do not.
     * 
     * @see {@link #coordsOverlap(Integer, Integer, Integer, Integer)}
     * @see {@link #coordsOverlap(Integer, Integer, LyricCoords)}
     * @see {@link #coordsOverlap(LyricCoords, LyricCoords)}
     * 
     * @param coords1 The first set of coordinates.
     * @param start2 The start coordinate of the second set.
     * @param end2   The end coordinate of the second set.
     * 
     * @return Whether the given ranges/coordinates overlap.
     */
    private Boolean coordsOverlap(LyricCoords coords1, Integer start2, Integer end2) {
        return coordsOverlap(coords1.getStart(), coords1.getEnd(), start2, end2);
    }

    /**
     * Return {@code true} if the specified ranges and/or sets of coordinates overlap
     * with one another, and {@code false} if they do not.
     * 
     * @see {@link #coordsOverlap(Integer, Integer, Integer, Integer)}
     * @see {@link #coordsOverlap(LyricCoords, Integer, Integer)}
     * @see {@link #coordsOverlap(LyricCoords, LyricCoords)}
     * 
     * @param start1 The start coordinate of the first set.
     * @param end1   The end coordinate of the first set.
     * @param coords2 The second set of coordinates.
     * 
     * @return Whether the given ranges/coordinates overlap.
     */
    private Boolean coordsOverlap(Integer start1, Integer end1, LyricCoords coords2) {
        return coordsOverlap(start1, end1, coords2.getStart(), coords2.getEnd());
    }

    /**
     * Return {@code true} if the specified ranges and/or sets of coordinates overlap
     * with one another, and {@code false} if they do not.
     * 
     * @see {@link #coordsOverlap(Integer, Integer, Integer, Integer)}
     * @see {@link #coordsOverlap(LyricCoords, Integer, Integer)}
     * @see {@link #coordsOverlap(Integer, Integer, LyricCoords)}
     * 
     * @param coords1 The first set of coordinates.
     * @param coords2 The second set of coordinates.
     * 
     * @return Whether the given ranges/coordinates overlap.
     */
    private Boolean coordsOverlap(LyricCoords coords1, LyricCoords coords2) {
        return coordsOverlap(coords1.getStart(), coords1.getEnd(), coords2.getStart(), coords2.getEnd());
    }

    /**
     * Returns the set of {@code LyricCoords} that overlap with the given range.
     * 
     * @param key   The language to get coordinates from.
     * @param start The start of the overlap range.
     * @param end   The end of the overlap range.
     * @return The set of overlapping {@code LyricCoords}.
     */
    private ArrayList<LyricCoords> getOverlaps(String key, Integer start, Integer end) {
        ArrayList<LyricCoords> overlapSet = new ArrayList<LyricCoords>();
        for (LyricCoords currentCoords : coords.get(key).getCoordsList()) {
            if (!currentCoords.hasNull() && coordsOverlap(currentCoords, start, end)) {
                overlapSet.add(currentCoords);
            }
        }
        Collections.sort(overlapSet);
        return overlapSet;
    }

    /**
     * Returns the set of {@code LyricCoords} that overlap with the given range.
     * 
     * @param key        The language to get coordinates from.
     * @param mainCoords A {@code LyricCoords} object representing the overlap range.
     * @return The set of overlapping {@code LyricCoords}.
     */
    private ArrayList<LyricCoords> getOverlaps(String key, LyricCoords mainCoords) {
        return getOverlaps(key, mainCoords.getStart(), mainCoords.getEnd());
    }

    /**
     * Add the specified range of characters to the specified coordinate set, merging
     * with other coordinate sets (continuous or not) as necessary. For example, adding
     * the range {@code (4, 9)} to the string
     * 
     *      {@code a[bcd]e[fg]hi}
     * 
     * will produce
     * 
     *      {@code a[bcdefg]hi}
     * 
     * as opposed to
     * 
     *      {@code a[bc[d]e[f]g]hi}
     * 
     * @see {@link #removeRange(String, Integer, Integer)}
     * 
     * @param key   The language to add the range to.
     * @param start The start coordinate of the range to add.
     * @param end   The end coordinate of the range to add.
     * @return The current {@code LyricSlice} object itself.
     */
    public LyricSlice addRange(String key, Integer start, Integer end) {
        ArrayList<LyricCoords> overlapSet = getOverlaps(key, start, end);
        if (overlapSet.isEmpty()) {
            addCoords(key, start, end);
        } else {
            Integer newstart = Math.min(start, overlapSet.get(0).getStart());
            Integer newend = Math.max(end, overlapSet.get(overlapSet.size()-1).getEnd());
            addCoords(key, newstart, newend);
            for (int i = 0; i < overlapSet.size(); i++) {
                removeCoords(overlapSet.get(i));
            }
        }
        return this;
    }

    /**
     * Remove the specified range of characters from the specified coordinate set,
     * splitting other coordinate sets (continuous or not) as necessary. For example,
     * removing the range {@code (5, 6)} from the string
     * 
     *      {@code a[bcdefg]hi}
     * 
     * will produce
     * 
     *      {@code a[bcd]e[fg]hi}.
     * 
     * @see {@link #addRange(String, Integer, Integer)}
     * 
     * @param key   The language to remove the range from.
     * @param start The start coordinate of the range to remove.
     * @param end   The end coordinate of the range to remove.
     * @return The current {@code LyricSlice} object itself.
     */
    public LyricSlice removeRange(String key, Integer start, Integer end) {
        ArrayList<LyricCoords> overlapSet = getOverlaps(key, start, end);
        if (!overlapSet.isEmpty()) {
            Integer originalstart = overlapSet.get(0).getStart();
            Integer originalend   = overlapSet.get(overlapSet.size()-1).getEnd();
            if (originalend > end) {
                addCoords(key, end, originalend);
            }
            if (originalstart < start) {
                addCoords(key, originalstart+1, start);
            }
            for (int i = 0; i < overlapSet.size(); i++) {
                removeCoords(overlapSet.get(i));
            }
        }
        return this;
    }


  // =-=-= String Methods =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

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


  // =-=-= Header =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    public void setHeader(String newheader) {
        header = newheader;
    }

    public void setHeaderIfEmpty(String newheader) {
        if (hasHeader(false)) {
            setHeader(newheader);
        }
    }

    public String getHeader() {
        return header;
    }

    public Boolean hasHeader() {
        return header != null;
    }

    public Boolean hasHeader(Boolean val) {
        return hasHeader() == val;
    }

    public ArrayList<LyricInsertion> getInsertionList(String key) {
        ArrayList<LyricInsertion> result = new ArrayList<LyricInsertion>();
        for (LyricCoords currentCoords : getCoords(key).getCoordsList()) {
            if (currentCoords.hasStart()) {
                result.add(new LyricInsertion(getHeader(), currentCoords.getStart(), 0));
            }
        }
        return result;
    }
}

class LyricCoords implements Comparable<LyricCoords> {
  // =-=-= Usage Info =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    // Note that nearly all the logic for this class lives setCoordsBound, which is 
    // called by most of the other functions. This uses setValBound to determine the
    // new values for start and end.

  // =-=-= Instance Variables =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    /**
     * An {@code Integer} instance storing the start coordinate.
     */
    private Integer start;

    /**
     * An {@code Integer} instance storing the end coordinate.
     */
    private Integer end;


  // =-=-= Constructor(s) =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

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
     * Default constructor allocating a new {@code LyricCoords} object
     * where both coordinates are set to {@code null}.
     */
    public LyricCoords() {
        this(null, null);
    }


  // =-=-= Basic Getter Methods =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

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


  // =-=-= Continuous/Discontinuous Methods =-=-=-=-=-=-=-=-=-=-=-=-=

    /**
     * Return an {@code ArrayList} of {@code LyricCoords}. If the coords
     * are continuous, will be a singleton list with just the object
     * itself; if the coords are discontinuous, will be a list of all of
     * the coordinate pairs.
     * 
     * Note that this is not very important for the continuous case; it
     * is included because it has a sensible definition, and so it may be
     * used without having to explicitly cast a {@code LyricCoords} object
     * to a {@code LyricCoordsDiscontinuous} one.
     * 
     * @return List of all coordinate pairs represented by the object.
     */
    public ArrayList<LyricCoords> getCoordsList() {
        ArrayList<LyricCoords> result = new ArrayList<LyricCoords>();
        result.add(this);
        return result;
    }

    /**
     * Return {@code true} if the object is a continuous {@code LyricCoords} object, and
     * {@code false} if it is a discontinuous {@code LyricCoordsDiscontinuous} object.
     * Useful to distinguish between the superclass {@code LyricCoords} and its subclass
     * {@code LyricCoordsDiscontinuous} without explicit casting.
     * 
     * @return Whether this is a {@code LyricCoords} object.
     */
    public Boolean isContinuous() {
        return true;
    }

    /**
     * Return {@code true} if the object is a discontinuous {@code LyricCoordsDiscontinuous}
     * object, and {@code false} if it is a continuous {@code LyricCoords} object. Useful to
     * distinguish between the superclass {@code LyricCoords} and its subclass
     * {@code LyricCoordsDiscontinuous} without explicit casting.
     * 
     * @return Whether this is a {@code LyricCoordsDiscontinuous} object.
     */
    public Boolean isDiscontinuous() {
        return !isContinuous();
    }


  // =-=-= Basic Info Methods =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    /**
     * Get the difference between the start and end coordinates. If the
     * coords are discontinuous, returns the sum of the lengths of all
     * the sections.
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


  // =-=-= Setter Methods =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

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


  // =-=-= Move Methods =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

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


  // =-=-= Null Methods =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    /**
     * Set the start coordinate to null.
     * 
     * @return The original start coordinate.
     */
    public Integer setStartToNull() {
        Integer oldstart = start;
        start = null;
        return oldstart;
    }

    /**
     * Set the end coordinate to null.
     * 
     * @return The original end coordinate.
     */
    public Integer setEndToNull() {
        Integer oldend = end;
        end = null;
        return oldend;
    }

    /**
     * Set both coordinates to null.
     * 
     * @return The original coordinates.
     */
    public LyricCoords setCoordsToNull() {
        LyricCoords oldcoords = new LyricCoords(this);
        setStartToNull();
        setEndToNull();
        return oldcoords;
    }

    /**
     * Return {@code true} if either the start or end coordinate is currently
     * set to {@code null}. If coordinates are discontinuous, returns {@code true}
     * if any start or end coordinate is set to {@code null}.
     * 
     * @return whether any coordinate is null.
     */
    public Boolean hasNull() {
        return start == null || end == null;
    }

    /**
     * Check if either the start or end coordinate is currently set to {@code null}
     * and return whether this matches the passed argument. If the coordinates are
     * discontinuous, checks if any start or end coordinate is set to {@code null}.
     * 
     * Equivalent to {@code hasNull() == val}.
     * 
     * @param val The expected value of hasNull().
     * @return Whether the value matches or not.
     */
    public Boolean hasNull(Boolean val) {
        return hasNull() == val;
    }

    /**
     * Return {@code true} if both the start and end coordinates are currently
     * set to {@code null}. If coordinates are discontinuous, returns {@code true}
     * if all start and end coordinates are set to {@code null}.
     * 
     * @return whether all coordinates are null.
     */
    public Boolean isNull() {
        return start == null && end == null;
    }

    /**
     * Check if both the start and end coordinate are currently set to {@code null}
     * and return whether this matches the passed argument. If the coordinates are
     * discontinuous, checks if all start and end coordinate are set to {@code null}.
     * 
     * Equivalent to {@code isNull() == val}.
     * 
     * @param val The expected value of hasNull().
     * @return Whether the value matches or not.
     */
    public Boolean isNull(Boolean val) {
        return isNull() == val;
    }

    /**
     * Return {@code false} if {@code start} equals {@code null}, and
     * {@code true} otherwise.
     * 
     * @return Whether {@code start} has a numerical value. 
     */
    public Boolean hasStart() {
        return start != null;
    }

    /**
     * Return {@code false} if {@code end} equals {@code null}, and
     * {@code true} otherwise.
     * 
     * @return Whether {@code end} has a numerical value. 
     */
    public Boolean hasEnd() {
        return end != null;
    }


  // =-=-= Adding Methods =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    /**
     * Returns a new {@code LyricCoordsDiscontinuous} object representing a set
     * of discontinuous coordinates -- two or more sections of the text with
     * characters between them.
     * 
     * Note that it is not enough to just run addCoords() on an existing
     * {@code LyricCoords} object; the result must be saved to the variable name
     * itself. This is possible because {@code LyricCoordsDiscontinuous} is a
     * subclass of {@code LyricCoords}.
     * 
     * Note that this must be defined independently from the other overloaded
     * version of addCoords, since both must call their respective analogues
     * in LyricCoordsDiscontinuous to function properly.
     * 
     * @see {@code LyricCoordsDiscontinuous.addCoords(Integer start, Integer end)}
     * 
     * @param start The starting coordinate for the new set.
     * @param end   The ending coordinate for the new set.
     * @return New discontinuous set of Lyric coords.
     */
    public LyricCoordsDiscontinuous addCoords(Integer start, Integer end) {
        LyricCoordsDiscontinuous result = new LyricCoordsDiscontinuous(this);
        result.addCoords(start, end);
        return result;
    }

    /**
     * Returns a new {@code LyricCoordsDiscontinuous} object representing a set
     * of discontinuous coordinates -- two or more sections of the text with
     * characters between them.
     * 
     * Note that it is not enough to just run addCoords() on an existing
     * {@code LyricCoords} object; the result must be saved to the variable name
     * itself. This is possible because {@code LyricCoordsDiscontinuous} is a
     * subclass of {@code LyricCoords}.
     * 
     * Note that this must be defined independently from the other overloaded
     * version of addCoords, since both must call their respective analogues
     * in LyricCoordsDiscontinuous to function properly.
     * 
     * Note also that even though a new object will be returned by this function,
     * the original coordinate set is still used as a subset within the new
     * discontinuous coords. This is very important for removing/replacing sets
     * of coordinates.
     * 
     * @see {@code LyricCoordsDiscontinuous.addCoords(LyricCoords... origList)}
     * 
     * @param origList Some preexisting {@code LyricCoords} to directly add to this object.
     * @return A new discontinuous set of Lyric coords.
     */
    public LyricCoordsDiscontinuous addCoords(LyricCoords... origList) {
        // Initialize a new LyricCoordsDiscontinuous to store the coordinates
        LyricCoordsDiscontinuous result = new LyricCoordsDiscontinuous();

        // Add the current coords object to the new discontinuous one
        result.addCoords(this);

        // Add the provided coords objects to the new discontinuous one
        result.addCoords(origList);

        // Return the new LyricCoordsDiscontinuous object
        return result;
    }

    /**
     * Returns a new {@code LyricCoordsDiscontinuous} object representing a set
     * of discontinuous coordinates -- two or more sections of the text with
     * characters between them.
     * 
     * Note that it is not enough to just run addCoords() on an existing
     * {@code LyricCoords} object; the result must be saved to the variable name
     * itself. This is possible because {@code LyricCoordsDiscontinuous} is a
     * subclass of {@code LyricCoords}.
     * 
     * Note that this must be defined independently from the other overloaded
     * version of addCoords, since both must call their respective analogues
     * in LyricCoordsDiscontinuous to function properly.
     * 
     * Note also that even though a new object will be returned by this function,
     * the original coordinate set is still used as a subset within the new
     * discontinuous coords. This is very important for removing/replacing sets
     * of coordinates.
     * 
     * @see {@code LyricCoordsDiscontinuous.addCoordsAsCopy(LyricCoords... origList)}
     * 
     * @param origList Some {@code LyricCoords} to duplicate and add to this object.
     * @return A new discontinuous set of Lyric coords.
     */
    public LyricCoordsDiscontinuous addCoordsAsCopy(LyricCoords... origList) {
        // Initialize a new LyricCoordsDiscontinuous to store the coordinates
        LyricCoordsDiscontinuous result = new LyricCoordsDiscontinuous();

        // Preserve the current coords object in the new set
        result.addCoords(this);

        // Copy all the coordinates passed as arguments
        result.addCoordsAsCopy(origList);

        // Return the new LyricCoordsDiscontinuous object
        return result;
    }

    /**
     * Return a new {@code LyricCoords} object representing the current coordinates
     * with the specified {@code LyricCoords} object removed. If the {@code LyricCoords}
     * object this is being called on is discontinuous, the passed object will be
     * removed from its list of subcoordinates; if the object is continuous, it will be
     * left unchanged. Note that if this would create a discontinuous set with only one
     * pair of coordinates -- that is, a continuous set stored as a discontinuous set
     *  -- then that single pair of coordinates will be returned instead, effectively
     * "flattening" the structure down to a continuous set.
     * 
     * Included in {@code LyricCoords} so it may be called for {@code LyricCoordsDiscontinuous}
     * without explicit casting.
     * 
     * @param coordsToRemove The {@code LyricCoords} object to be removed.
     * @return The original {@code LyricCoords} object with the specified coordinates removed.
     */
    public LyricCoords removeCoords(LyricCoords coordsToRemove) {
        // TODO: Return null coordinates if this == coordsToRemove?
        return this;
    }


  // =-=-= Update Methods =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    /**
     * Adjust the start and end coordinates to reflect some change to the 
     * string they are meant to represent. The third argument may be omitted if
     * no upper bound is necessary.
     * 
     * @see {@link #matchUpdatedReference(Integer, Integer)}
     * 
     * @param index           The index in the string where the change takes place.
     * @param length          The number of characters inserted or deleted.
     * @param referenceLength The length of the full reference string after the transformation.
     */
    public void matchUpdatedReference(Integer index, Integer length, Integer referenceLength) {
        if (!hasNull()) {
            Integer startOffset = (start >= index) ? length : 0;
            Integer endOffset   = (end   >= index) ? length : 0;
            moveCoordsBound(startOffset, endOffset, 0, referenceLength);
        }
    }

    /**
     * Adjust the start and end coordinates to reflect some change to the 
     * string they are meant to represent. If no reference length is provided,
     * the coordinates will be set using an unbound method, meaning there will
     * be no upper limit.
     * 
     * @see {@link #matchUpdatedReference(Integer, Integer, Integer)}
     * 
     * @param index           The index in the string where the change takes place.
     * @param length          The number of characters inserted or deleted.
     */
    public void matchUpdatedReference(Integer index, Integer length) {
        matchUpdatedReference(index, length, null);
    }

    /**
     * Set the start and end coordinates in terms of a given reference string and list of other
     * coords dependent on it. Removes brackets at the current start and end values and inserts
     * new ones at the new start and end values, updating each set of {@code LyricCoords} in the
     * provided {@code ArrayList} to reflect this change.
     * 
     * If the current coords are discontinuous, this will change them to a continuous set with
     * the provided start and end values.
     * 
     * Note that the changes to the reference string will be accumulated in the {@code newReference}
     * argument.
     * 
     * If both {@code newstart} and {@code newend} are {@code null}, both coordinates will be
     * set to {@code null}, effectively removing them from the string.
     * 
     * // TODO: Allow start or end to be null to not change that coordinate (and potentially cut down on runtime)
     * 
     * @param newstart        The new start value for the coordinates.
     * @param newend          The new end value for the coordinates.
     * @param referenceString A {@code StringBuilder} object to accumulate changes to the reference string.
     * @param listOfCoords    A list of other {@code LyricCoords} objects that need to be updated after these changes.
     * @return The updated {@code LyricCoords} object.
     */
    public LyricCoords setCoordsUpdated(Integer newstart, Integer newend, StringBuilder referenceString, ArrayList<LyricCoords> listOfCoords) {

        // Remove current closing bracket, if it exists
        if (hasEnd()) {
            if (referenceString.charAt(end) == ']') {
                referenceString.deleteCharAt(end);
            } else {
                throw new RuntimeException("Character " + end + " of string \"" + referenceString + " does not match close bracket \"]\" ");
            }
        }

        // Remove current opening bracket, if it exists
        if (hasStart()) {
            if (referenceString.charAt(start) == '[') {
                referenceString.deleteCharAt(start);
            } else {
                throw new RuntimeException("Character " + start + " of string \"" + referenceString + " does not match open bracket \"[\" ");
            }
        }

        // Change other coords to match removal of brackets
        for (LyricCoords coords : listOfCoords) {
            if (coords != this) {
                // No upper bound provided since change is negative anyways
                if (hasStart()) coords.matchUpdatedReference(start, -1);
                if (hasEnd()) coords.matchUpdatedReference(end, -1);
            }
        }

        if (newstart == null && newend == null) {
            setCoordsToNull();
            return this;
        }

        // Correct for start and end being entered in reverse order
        // Note that if newstart has not yet been set, newend must be increased by 1 to adjust for the
        // bracket that will be inserted there
        Integer newstart_ = Math.min(newstart, newend);
        Integer newend_ = Math.max(newstart, newend) + (hasStart() ? 0 : 1);

        // Set new start and new end safely
        setCoordsBound(newstart_, newend_, 0, referenceString.length()+1);

        // Increment end by 1 if both bounds end up at the same point
        if (start == end) {
            // TODO: Add bounds to this call, in case it occurs at the end of the string?
            moveEnd(1);
        }

        // Re-insert brackets into the reference string and save it to the HashMap
        referenceString.insert(start, "[");
        referenceString.insert(end, "]");

        // Change other slices to match reinserted brackets
        for (LyricCoords coords : listOfCoords) {
            if (coords != this) {
                if (hasStart()) coords.matchUpdatedReference(start, 1, referenceString.length());
                if (hasEnd()) coords.matchUpdatedReference(end, 1, referenceString.length());
            }
        }

        return this;
    }


    /**
     * Set the start coordinate in terms of a given reference string and list of other coords
     * dependent on it. Removes brackets at the current start and end values and inserts new
     * ones at the new start and end values, updating each set of {@code LyricCoords} in the
     * provided {@code ArrayList} to reflect this change.
     * 
     * Equivalent to {@code setCoordsUpdated(newstart, this.getEnd(), referenceString, listOfCoords)}
     * 
     * If the current coords are discontinuous, this will change them to a continuous set with
     * the provided start value and the highest end value.
     * 
     * Note that the changes to the reference string will be accumulated in the {@code newReference}
     * argument.
     * 
     * @see {@link #setCoordsUpdated()}
     * 
     * @param newstart        The new start value for the coordinates.
     * @param referenceString A {@code StringBuilder} object to accumulate changes to the reference string.
     * @param listOfCoords    A list of other {@code LyricCoords} objects that need to be updated after these changes.
     * @return The updated {@code LyricCoords} object.
     */
    public LyricCoords setStartUpdated(Integer newstart, StringBuilder referenceString, ArrayList<LyricCoords> listOfCoords) {
        return setCoordsUpdated(newstart, getEnd(), referenceString, listOfCoords);
    }

    /**
     * Set the end coordinate in terms of a given reference string and list of other coords
     * dependent on it. Removes brackets at the current start and end values and inserts new
     * ones at the new start and end values, updating each set of {@code LyricCoords} in the
     * provided {@code ArrayList} to reflect this change.
     * 
     * Equivalent to {@code setCoordsUpdated(this.getStart(), newend, referenceString, listOfCoords)}
     * 
     * If the current coords are discontinuous, this will change them to a continuous set with
     * the provided end value and the lowest start value.
     * 
     * Note that the changes to the reference string will be accumulated in the {@code newReference}
     * argument.
     * 
     * @see {@link #setCoordsUpdated()}
     * 
     * @param newend          The new end value for the coordinates.
     * @param referenceString A {@code StringBuilder} object to accumulate changes to the reference string.
     * @param listOfCoords    A list of other {@code LyricCoords} objects that need to be updated after these changes.
     * @return The updated {@code LyricCoords} object.
     */
    public LyricCoords setEndUpdated(Integer newend, StringBuilder referenceString, ArrayList<LyricCoords> listOfCoords) {
        return setCoordsUpdated(getStart(), newend, referenceString, listOfCoords);
    }

    /**
     * Move the start and end coordinates in terms of a given reference string and list of other
     * coords dependent on it. Removes brackets at the current start and end values and inserts
     * new ones at the new start and end values, updating each set of {@code LyricCoords} in the
     * provided {@code ArrayList} to reflect this change.
     * 
     * If the current coords are discontinuous, each of the subsets will be moved by the same amount.
     * 
     * Note that the changes to the reference string will be accumulated in the {@code newReference}
     * argument.
     * 
     * @param startoffset     The new start value for the coordinates.
     * @param endoffset     The new end value for the coordinates.
     * @param referenceString A {@code StringBuilder} object to accumulate changes to the reference string.
     * @param listOfCoords    A list of other {@code LyricCoords} objects that need to be updated after these changes.
     * @return The updated {@code LyricCoords} object.
     */
    public LyricCoords moveCoordsUpdated(Integer startoffset, Integer endoffset, StringBuilder referenceString, ArrayList<LyricCoords> listOfCoords) {
        return setCoordsUpdated(getStart() + startoffset, getEnd() + endoffset, referenceString, listOfCoords);
    }

    /**
     * Move the start coordinate in terms of a given reference string and list of other coords
     * dependent on it. Removes brackets at the current start and end values and inserts new
     * ones at the new start and end values, updating each set of {@code LyricCoords} in the
     * provided {@code ArrayList} to reflect this change.
     * 
     * Equivalent to {@code moveCoordsUpdated(startoffset, 0, referenceString, listOfCoords)}.
     * 
     * If the current coords are discontinuous, each of the subsets will be moved by the same amount.
     * 
     * Note that the changes to the reference string will be accumulated in the {@code newReference}
     * argument.
     * 
     * @param startoffset     The new start value for the coordinates.
     * @param referenceString A {@code StringBuilder} object to accumulate changes to the reference string.
     * @param listOfCoords    A list of other {@code LyricCoords} objects that need to be updated after these changes.
     * @return The updated {@code LyricCoords} object.
     */
    public LyricCoords moveStartUpdated(Integer startoffset, StringBuilder referenceString, ArrayList<LyricCoords> listOfCoords) {
        return moveCoordsUpdated(startoffset, 0, referenceString, listOfCoords);
    }

    /**
     * Move the end coordinate in terms of a given reference string and list of other coords
     * dependent on it. Removes brackets at the current start and end values and inserts new
     * ones at the new start and end values, updating each set of {@code LyricCoords} in the
     * provided {@code ArrayList} to reflect this change.
     * 
     * Equivalent to {@code moveCoordsUpdated(0, endoffset, referenceString, listOfCoords)}.
     * 
     * If the current coords are discontinuous, each of the subsets will be moved by the same amount.
     * 
     * Note that the changes to the reference string will be accumulated in the {@code newReference}
     * argument.
     * 
     * @param endoffset     The new end value for the coordinates.
     * @param referenceString A {@code StringBuilder} object to accumulate changes to the reference string.
     * @param listOfCoords    A list of other {@code LyricCoords} objects that need to be updated after these changes.
     * @return The updated {@code LyricCoords} object.
     */
    public LyricCoords moveEndUpdated(Integer endoffset, StringBuilder referenceString, ArrayList<LyricCoords> listOfCoords) {
        return moveCoordsUpdated(0, endoffset, referenceString, listOfCoords);
    }


  // =-=-= String Methods =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    public String toString() {
        return "(" + start + ", " + end + ")";
    }

    /**
     * Return the characters in the passed {@code String} that are within
     * the coordinate range of this {@code LyricCoords} object.
     * 
     * If the coords are discontinuous, returns the characters bound by each
     * subset of coordinates, separated by the string {@code (...)}.
     * 
     * @param text The string to take characters from.
     * @return The enclosed characters in the string.
     */
    public String getBoundCharacters(String text) {
        return text.substring(getStart()+1, getEnd());
    }

    public void replaceAtStartEnd(StringBuilder fulltext, String newHeader, String newCloser, Integer currentHeaderLength, Integer currentCloserLength) {
        fulltext.replace(getStart(), getStart() + currentHeaderLength, newHeader);
        fulltext.replace(getEnd() + newHeader.length() - currentHeaderLength, getEnd() + newHeader.length() - currentHeaderLength + currentCloserLength, newCloser);
    }


  // =-=-= Comparison Methods =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    public int compareTo(LyricCoords e2) {
        if (!this.hasStart() && !e2.hasStart())
            return 0;
        if (!this.hasStart())
            return -1;
        if (!e2.hasStart())
            return 1;
        return this.getStart().compareTo(e2.getStart());
    }
}

class LyricCoordsDiscontinuous extends LyricCoords {
  // =-=-= Instance Variables =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    /**
     * The list of {@code LyricCoords} subsets of coordinates that make
     * up this discontinuous coordinate set.
     */
    private ArrayList<LyricCoords> coordsList;


  // =-=-= Constructor(s) =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    /**
     * Instantiates a new {@code LyricCoordsDiscontinuous} object with one
     * set of coordinates storing the given values.
     * 
     * @param start The first start coordinate to store.
     * @param end The first end coordinate to store.
     */
    public LyricCoordsDiscontinuous(Integer start, Integer end) {
        super(null, null);
        coordsList = new ArrayList<LyricCoords>();
        addCoords(start, end);
    }

    /**
     * Instantiates a new {@code LyricCoordsDiscontinuous} object storing copies of the given
     * coordinate values, copied as defined in the method {@link #addCoordsAsCopy(LyricCoords)}.
     * 
     * To add {@code LyricCoords} objects directly rather than as copies, consider 
     * {@link #addCoords(LyricCoords)}.
     * 
     * Note that this will "flatten" any given {@code LyricCoordsDiscontinuous} objects, adding
     * their component parts rather than the full structure.
     * 
     * @param newCoordsList Some number of new {@code LyricCoords} objects to store.
     */
    public LyricCoordsDiscontinuous(LyricCoords... newCoordsList) {
        super(null, null);
        coordsList = new ArrayList<LyricCoords>();
        addCoordsAsCopy(newCoordsList);
    }


  // =-=-= Basic Getter Methods =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    /**
     * Get the first coordinate, representing the start of the interval, for the
     * given language.
     * 
     * If coords are discontinuous, returns the earliest start coordinate of all
     * the subsections.
     * 
     * @return An {@code Integer} representing the start coordinate.
     */
    public Integer getStart() {
        Integer startCoords[] = new Integer[coordsList.size()];
        for (int i = 0; i < coordsList.size(); i++) {
            startCoords[i] = coordsList.get(i).getStart();
        }
        Integer min = startCoords[0];
        for (Integer i : startCoords) {
            min = i < min ? i : min;
        }
        return min;
    }

    /**
     * Get the second coordinate, representing the end of the interval, for the
     * given language.
     * 
     * If coords are discontinuous, returns the last end coordinate of all the
     * subsections.
     * 
     * @return An {@code Integer} representing the end coordinate.
     */
    public Integer getEnd() {
        Integer endCoords[] = new Integer[coordsList.size()];
        for (int i = 0; i < coordsList.size(); i++) {
            endCoords[i] = coordsList.get(i).getEnd();
        }
        Integer max = endCoords[0];
        for (Integer i : endCoords) {
            max = i > max ? i : max;
        }
        return max;
    }


  // =-=-= Continuous/Discontinuous Methods =-=-=-=-=-=-=-=-=-=-=-=-=

    /**
     * Return an {@code ArrayList} of {@code LyricCoords}. If the coords
     * are continuous, will be a singleton list with just the object
     * itself; if the coords are discontinuous, will be a list of all of
     * the coordinate pairs.
     * 
     * Note that this is not very important for the continuous case; it
     * is included because it has a sensible definition, and so it may be
     * used without having to explicitly cast a {@code LyricCoords} object
     * to a {@code LyricCoordsDiscontinuous} one.
     * 
     * @return List of all coordinate pairs represented by the object.
     */
    public ArrayList<LyricCoords> getCoordsList() {
        return coordsList;
    }

    /**
     * Return {@code true} if the object is a continuous {@code LyricCoords} object, and
     * {@code false} if it is a discontinuous {@code LyricCoordsDiscontinuous} object.
     * Useful to distinguish between the superclass {@code LyricCoords} and its subclass
     * {@code LyricCoordsDiscontinuous} without explicit casting.
     * 
     * @return Whether this is a {@code LyricCoords} object.
     */
    public Boolean isContinuous() {
        return false;
    }

    /**
     * Return {@code true} if the object is a discontinuous {@code LyricCoordsDiscontinuous}
     * object, and {@code false} if it is a continuous {@code LyricCoords} object. Useful to
     * distinguish between the superclass {@code LyricCoords} and its subclass
     * {@code LyricCoordsDiscontinuous} without explicit casting.
     * 
     * @return Whether this is a {@code LyricCoordsDiscontinuous} object.
     */
    public Boolean isDiscontinuous() {
        return !isContinuous();
    }


  // =-=-= Basic Info Methods =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    /**
     * Get the difference between the start and end coordinates. If the
     * coords are discontinuous, returns the sum of the lengths of all
     * the sections.
     * 
     * @return The length of the interval.
     */
    public Integer length() {
        Integer result = 0;
        for (LyricCoords coords : coordsList) {
            result += coords.length();
        }
        return result;
    }


  // =-=-= Null Methods =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    /**
     * Set both values of all coordinates to null.
     * 
     * @return The original coordinates.
     */
    public LyricCoords setCoordsToNull() {
        LyricCoords oldcoords = new LyricCoords(this);
        for (LyricCoords coords : coordsList) {
            coords.setCoordsToNull();
        }
        return oldcoords;
    }

    /**
     * Return {@code true} if either the start or end coordinate is currently
     * set to {@code null}. If coordinates are discontinuous, returns {@code true}
     * if any start or end coordinate is set to {@code null}.
     * 
     * @return whether any coordinate is null.
     */
    public Boolean hasNull() {
        for (LyricCoords coords : coordsList) {
            if (coords.hasNull())
                return true;
        }
        return false;
    }

    /**
     * Return {@code true} if both the start and end coordinates are currently
     * set to {@code null}. If coordinates are discontinuous, returns {@code true}
     * if all start and end coordinates are set to {@code null}.
     * 
     * @return whether all coordinates are null.
     */
    public Boolean isNull() {
        for (LyricCoords coords : coordsList) {
            if (!coords.isNull())
                return false;
        }
        return true;
    }


  // =-=-= Adding Methods =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    /**
     * Returns a new {@code LyricCoordsDiscontinuous} object representing a set
     * of discontinuous coordinates -- two or more sections of the text with
     * characters between them.
     * 
     * Note that it is not enough to just run addCoords() on an existing
     * {@code LyricCoords} object; the result must be saved to the variable name
     * itself. This is possible because {@code LyricCoordsDiscontinuous} is a
     * subclass of {@code LyricCoords}.
     * 
     * Note that this must be defined independently from the other overloaded
     * version of addCoords, since both must call their respective analogues
     * in LyricCoordsDiscontinuous to function properly.
     * 
     * @see {@code LyricCoords.addCoords(Integer start, Integer end)}
     * 
     * @param start The starting coordinate for the new set.
     * @param end   The ending coordinate for the new set.
     * @return New discontinuous set of Lyric coords.
     */
    public LyricCoordsDiscontinuous addCoords(Integer start, Integer end) {
        return addCoords(new LyricCoords(start, end));
    }

    /**
     * Returns a new {@code LyricCoordsDiscontinuous} object representing a set
     * of discontinuous coordinates -- two or more sections of the text with
     * characters between them.
     * 
     * Note that it is not enough to just run addCoords() on an existing
     * {@code LyricCoords} object; the result must be saved to the variable name
     * itself. This is possible because {@code LyricCoordsDiscontinuous} is a
     * subclass of {@code LyricCoords}.
     * 
     * Note that this must be defined independently from the other overloaded
     * version of addCoords, since both must call their respective analogues
     * in LyricCoordsDiscontinuous to function properly.
     * 
     * @see {@code LyricCoords.addCoords(LyricCoords... origList)}
     * 
     * @param origList Some preexisting {@code LyricCoords} to directly add to this object.
     * @return A new discontinuous set of Lyric coords.
     */
    public LyricCoordsDiscontinuous addCoords(LyricCoords... origList) {
        for (LyricCoords orig : origList) {
            if (orig.isDiscontinuous()) {
                for (LyricCoords newcoords : orig.getCoordsList()) {
                    addCoords(newcoords);
                }
            } else {
                coordsList.add(orig);
                Collections.sort(coordsList);
            }
        }
        // TODO: List needs to be pruned, but is this the best place to put this?
        // Remove any coordinates that are (null, null)
        for (int i = 0; i < coordsList.size(); i++) {
            if (coordsList.get(i).isNull()) {
                coordsList.remove(i);
                i--;
            }
        }
        return this;
    }

    /**
     * Returns a new {@code LyricCoordsDiscontinuous} object representing a set
     * of discontinuous coordinates -- two or more sections of the text with
     * characters between them.
     * 
     * Note that it is not enough to just run addCoords() on an existing
     * {@code LyricCoords} object; the result must be saved to the variable name
     * itself. This is possible because {@code LyricCoordsDiscontinuous} is a
     * subclass of {@code LyricCoords}.
     * 
     * Note that this must be defined independently from the other overloaded
     * version of addCoords, since both must call their respective analogues
     * in LyricCoordsDiscontinuous to function properly.
     * 
     * @see {@code LyricCoords.addCoordsAsCopy(LyricCoords... origList)}
     * 
     * @param origList Some {@code LyricCoords} to duplicate and add to this object.
     * @return A new discontinuous set of Lyric coords.
     */
    public LyricCoordsDiscontinuous addCoordsAsCopy(LyricCoords... origList) {
        for (LyricCoords orig : origList) {
            if (orig.isDiscontinuous()) {
                for (LyricCoords newcoords : orig.getCoordsList()) {
                    addCoordsAsCopy(newcoords);
                }
            } else {
                addCoords(new LyricCoords(orig));
            }
        }
        return this;
    }

    /**
     * Return a new {@code LyricCoords} object representing the current coordinates
     * with the specified {@code LyricCoords} object removed. If the {@code LyricCoords}
     * object this is being called on is discontinuous, the passed object will be
     * removed from its list of subcoordinates; if the object is continuous, it will be
     * left unchanged. Note that if this would create a discontinuous set with only one
     * pair of coordinates -- that is, a continuous set stored as a discontinuous set
     *  -- then that single pair of coordinates will be returned instead, effectively
     * "flattening" the structure down to a continuous set.
     * 
     * Included in {@code LyricCoords} so it may be called for {@code LyricCoordsDiscontinuous}
     * without explicit casting.
     * 
     * @param coordsToRemove The {@code LyricCoords} object to be removed.
     * @return The original {@code LyricCoords} object with the specified coordinates removed.
     */
    public LyricCoords removeCoords(LyricCoords coordsToRemove) {
        // Remove the specified coords from the list
        coordsList.remove(coordsToRemove);

        // If only one set of coords remains, return just that set to "flatten" the coords
        if (coordsList.size() == 1) {
            return coordsList.get(0);
        }

        // Otherwise, just return the current object
        return this;
    }


  // =-=-= Update Methods =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    /**
     * Adjust the start and end coordinates to reflect some change to the 
     * string they are meant to represent. The third argument may be omitted if
     * no upper bound is necessary.
     * 
     * @see {@link #matchUpdatedReference(Integer, Integer)}
     * 
     * @param index           The index in the string where the change takes place.
     * @param length          The number of characters inserted or deleted.
     * @param referenceLength The length of the full reference string after the transformation.
     */
    public void matchUpdatedReference(Integer index, Integer length, Integer referenceLength) {
        for (LyricCoords coords : coordsList) {
            coords.matchUpdatedReference(index, length, referenceLength);
        }
    }

    /**
     * Adjust the start and end coordinates to reflect some change to the 
     * string they are meant to represent. If no reference length is provided,
     * the coordinates will be set using an unbound method, meaning there will
     * be no upper limit.
     * 
     * @see {@link #matchUpdatedReference(Integer, Integer, Integer)}
     * 
     * @param index           The index in the string where the change takes place.
     * @param length          The number of characters inserted or deleted.
     */
    public void matchUpdatedReference(Integer index, Integer length) {
        matchUpdatedReference(index, length, null);
    }

    /**
     * Set the start and end coordinates in terms of a given reference string and list of other
     * coords dependent on it. Removes brackets at the current start and end values and inserts
     * new ones at the new start and end values, updating each set of {@code LyricCoords} in the
     * provided {@code ArrayList} to reflect this change.
     * 
     * If the current coords are discontinuous, this will change them to a continuous set with
     * the provided start and end values.
     * 
     * Note that the changes to the reference string will be accumulated in the {@code newReference}
     * argument.
     * 
     * If both {@code newstart} and {@code newend} are {@code null}, both coordinates will be
     * set to {@code null}, effectively removing them from the string.
     * 
     * @param newstart        The new start value for the coordinates.
     * @param newend          The new end value for the coordinates.
     * @param referenceString A {@code StringBuilder} object to accumulate changes to the reference string.
     * @param listOfCoords    A list of other {@code LyricCoords} objects that need to be updated after these changes.
     * @return The updated {@code LyricCoords} object.
     */
    public LyricCoords setCoordsUpdated(Integer newstart, Integer newend, StringBuilder referenceString, ArrayList<LyricCoords> listOfCoords) {
        // Initialize a new set of coords to be the new value
        LyricCoords result = new LyricCoords();

        // Remove all of the coordinate subsets from the reference string
        for (LyricCoords coords : coordsList) {
            coords.setCoordsUpdated(null, null, referenceString, listOfCoords);
        }

        // Run setStartEnd with the new coords and return it
        result.setCoordsUpdated(newstart, newend, referenceString, listOfCoords);
        return result;
    }

    /**
     * Move the start and end coordinates in terms of a given reference string and list of other
     * coords dependent on it. Removes brackets at the current start and end values and inserts
     * new ones at the new start and end values, updating each set of {@code LyricCoords} in the
     * provided {@code ArrayList} to reflect this change.
     * 
     * If the current coords are discontinuous, each of the subsets will be moved by the same amount.
     * 
     * Note that the changes to the reference string will be accumulated in the {@code newReference}
     * argument.
     * 
     * @param startoffset     The new start value for the coordinates.
     * @param endoffset     The new end value for the coordinates.
     * @param referenceString A {@code StringBuilder} object to accumulate changes to the reference string.
     * @param listOfCoords    A list of other {@code LyricCoords} objects that need to be updated after these changes.
     * @return The updated {@code LyricCoords} object.
     */
    public LyricCoords moveCoordsUpdated(Integer startoffset, Integer endoffset, StringBuilder referenceString, ArrayList<LyricCoords> listOfCoords) {
        for (LyricCoords coords : coordsList) {
            coords.moveCoordsUpdated(startoffset, endoffset, referenceString, listOfCoords);
        }
        return this;
    }


  // =-=-= String Methods =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    public String toString() {
        String result = "[";
        for (LyricCoords coords : coordsList) {
            result += coords;
            result += ", ";
        }
        if (result.length() > 2) {
            result = result.substring(0, result.length()-2);
        }
        result += "]";
        return result;
    }

    /**
     * Return the characters in the passed {@code String} that are within
     * the coordinate range of this {@code LyricCoords} object.
     * 
     * If the coords are discontinuous, returns the characters bound by each
     * subset of coordinates, separated by the string {@code (...)}.
     * 
     * @param text The string to take characters from.
     * @return The enclosed characters in the string.
     */
    public String getBoundCharacters(String text) {
        String result = "";
        String intercalate = " (...) ";
        for (LyricCoords coords : coordsList) {
            result += coords.getBoundCharacters(text);
            result += intercalate;
        }
        if (result.length() > intercalate.length()) {
            result = result.substring(0, result.length() - intercalate.length());
        }
        return result;
    }
}