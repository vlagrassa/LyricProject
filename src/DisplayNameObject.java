public abstract class DisplayNameObject {

    /**
     * A display name to identify the object by.
     */
    private String displayName;

    /**
     * A subclass-specific method to generate a default name for the object
     * based on some input string. Note that the key does not have to be
     * used to create the name, but is provided for cases where it may help.
     * 
     * @param key Some input information to help generate the default name.
     * 
     * @return A default display name for the object.
     */
    public abstract String getDefaultName(String key);

    /**
     * Get the object's display name, if it has one. If it doesn't, returns
     * the empty string {@code ""}.
     * 
     * @return The display name.
     */
    public String getName() {
        return hasName() ? displayName : "";
    }

    /**
     * Get a name to display for the object. If it already has a name,
     * that name will be returned; if it does not, a temporary name will
     * be filled in based on the passed {@code String} and the subclass's
     * specific implementation of {@link #getDefaultName(String)}.
     * 
     * @see {@link #getDefaultName(String)}
     * 
     * @param key Information to fall back on if no name exists.
     * 
     * @return A non-empty name to display.
     */
    public String createDisplayName(String key) {
        return hasName() ? displayName : getDefaultName(key);
    }

    /**
     * Get a name to display for the object, and cut it off to fit within
     * the given length if necessary. If the name is cut off, an ellipsis
     * (Unicode value {@code \u2026}) will be added to the end.
     * 
     * If the object already has a name, that name will be used; if it does
     * not, a temporary name will be filled in using the plaintext
     * associated with the provided language string.
     * 
     * @see {@link #createDisplayName(String)}
     * @see {@link #getDefaultName(String)}
     * 
     * @param key Information to fall back on if no name exists.
     * @param maxlength The maximum length of the name.
     * 
     * @return A non-empty name to display.
     */
    public String createDisplayNameClipped(String key, Integer maxlength) {
        String result = createDisplayName(key);
        if (result.length() > maxlength) {
            return result.substring(0, maxlength - 1).trim() + "\u2026";
        } else {
            return result;
        }
    }

    /**
     * Return {@code true} if the object has a display name, and {@code false}
     * otherwise. May also be called with a boolean argument to check whether
     * the result matches that value.
     * 
     * @return Whether the line has a name value.
     */
    public Boolean hasName() {
        return displayName != null;
    }

    /**
     * Check whether the object currently has a display name, and return
     * {@code true} if this value matches the passed value.
     * 
     * @param val The expected value of {@code hasName()}.
     * @return Whether the value matches or not.
     */
    public Boolean hasName(Boolean val) {
        return hasName() == val;
    }

    /**
     * Set the display name of the object.
     * 
     * @param newname The new display name for the line.
     */
    public void setName(String newname) {
        displayName = newname;
    }

    /**
     * Set the display name of the object if it does not currently have a
     * value; otherwise, leave it unchanged. Returns {@code true} if the
     * name is changed, and false if it is not.
     * 
     * @param newname The new display name for the object.
     * @return Whether the name is changed.
     */
    public Boolean setNameIfEmpty(String newname) {
        if (hasName(false)) {
            setName(newname);
            return true;
        }
        return false;
    }
}