import java.util.*;

public class LyricHead {

  // =-=-= Instance Variables =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    private ArrayList<String> listOfLanguages;
    private ArrayList<LyricCategory> listOfCategories;


  // =-=-= Constructor(s) =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    public LyricHead(ArrayList<String> languages, ArrayList<LyricCategory> categories) {
        listOfLanguages = languages;
        listOfCategories = categories;
    }


  // =-=-= Language List =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    public ArrayList<String> getLanguages() {
        return listOfLanguages;
    }

    //TODO: Adding, removing, reordering languages


  // =-=-= Category List =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    public ArrayList<LyricCategory> getCategories() {
        return listOfCategories;
    }

    public void setCategoryList(ArrayList<LyricCategory> newCategoryList) {
        listOfCategories = newCategoryList;
    }

    public void addCategory(LyricCategory newCategory) {
        if (!listOfCategories.contains(newCategory)) {
            listOfCategories.add(newCategory);
        }
    }

    public void addCategories(LyricCategory... newCategories) {
        for (LyricCategory category : newCategories) {
            addCategory(category);
        }
    }

    public void removeCategory(LyricCategory category) {
        listOfCategories.remove(category);
    }

    public void removeCategories(LyricCategory... categories) {
        for (LyricCategory category : categories) {
            removeCategory(category);
        }
    }

    //TODO: Reordering categories
}