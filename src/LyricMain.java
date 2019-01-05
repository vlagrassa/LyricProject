import java.util.*;

public class LyricMain {
  
  public static void main(String[] args) {

// =-= Initializing test LyricLine =-=
    String[] languages = {"English", "Japanese"};
    LyricLine testLine = new LyricLine(languages);
    // TODO: Allow variable number of String inputs as language, instead/also
    testLine.setBracketedText("Japanese", "honbun tesuto");
    testLine.setBracketedText("English", "test text");
    System.out.println(testLine.getAsPlaintext("Japanese"));
    System.out.println(testLine.getAsPlaintext("English"));


// =-= Initializing test LyricSlice =-=
    LyricSlice testSlice = testLine.createSlice(1);
    testSlice.setStartEnd("Japanese", -5, 6);
    testSlice.setStartEnd("English", 5, 18);
    testSlice.setStartEnd("English", 5, 18);
    System.out.println("Test Slice:\n" + testSlice);
    System.out.println("Test Line:\n" + testLine);


// =-= Adding more slices, with categories =-=
    testLine.createSlice(2).setStartEnd("English", 8, 9);
    testLine.createSlice(1).setStartEnd("English", 0, 4).setStartEnd("Japanese", 9, 17);
    /*
    testLine.createSlice(3).setStartEnd("English", 0, 2).setStartEnd("Japanese", 7, 9);
    testLine.createSlice(3).setStartEnd("English", 2, 3).setStartEnd("Japanese", 9, 11);
    testLine.createSlice(3).setStartEnd("English", 3, 4).setStartEnd("Japanese", 11, 13);
    */
    testLine.createSlice(3).setStartEnd("Japanese", 3, 4).addCoords("Japanese", 8, 9);


// =-= Filtering slices by category =-=
    System.out.println("\nFiltering slices by category:");
    System.out.println("No filter:");
    System.out.println(testLine.getSlices());
    System.out.println("Filtered by category 1:");
    System.out.println(testLine.getSlices(1));


// =-= Modifying the text =-=
    // System.out.println(testLine.getAsHTML());
    // System.out.println(testLine.getSlices());
    // testLine.deleteFromPlainText("English", 1, 4);
    // testLine.addToPlainText("English", "___", 8);

    // testLine.modifyPlainText("English", " thing", 4);
    // System.out.println(testLine.getAsHTML());
    // System.out.println(testLine.getSlices());
    // System.out.println(testLine.getAsBracketed("English"));


// =-= Test Discontinuous Lyric Coords =-=
    LyricCoords              testCoordsOrig = new LyricCoords             (1, 3);
    LyricCoords              testCoordsCast = new LyricCoordsDiscontinuous(1, 3);
    LyricCoordsDiscontinuous testCoordsDisc = new LyricCoordsDiscontinuous(1, 3);
    String testText = "abcdefghij";

    // Plain Coordinates
    System.out.println("\nPlain Coordinates:");
    System.out.println("Discontinuous: " + testCoordsOrig.isDiscontinuous());
    System.out.print(testCoordsOrig + " -> ");
    System.out.println(testCoordsOrig.getBoundCharacters(testText));
    testCoordsOrig = testCoordsOrig.addCoords(5, 7);
    System.out.println("Discontinuous: " + testCoordsOrig.isDiscontinuous());
    System.out.print(testCoordsOrig + " -> ");
    System.out.println(testCoordsOrig.getBoundCharacters(testText));

    // Polymorphic Discontinuous Coordinates
    System.out.println("\nPolymorphic Discontinuous Coordinates:");
    System.out.println("Discontinuous: " + testCoordsCast.isDiscontinuous());
    System.out.print(testCoordsCast + " -> ");
    System.out.println(testCoordsCast.getBoundCharacters(testText));
    testCoordsCast = testCoordsCast.addCoords(5, 7);
    System.out.println("Discontinuous: " + testCoordsCast.isDiscontinuous());
    System.out.print(testCoordsCast + " -> ");
    System.out.println(testCoordsCast.getBoundCharacters(testText));

    // Plain Discontinuous Coordinates
    System.out.println("\nPlain Discontinuous Coordinates:");
    System.out.println("Discontinuous: " + testCoordsDisc.isDiscontinuous());
    System.out.print(testCoordsDisc + " -> ");
    System.out.println(testCoordsDisc.getBoundCharacters(testText));
    testCoordsDisc = testCoordsDisc.addCoords(5, 7);
    System.out.println("Discontinuous: " + testCoordsDisc.isDiscontinuous());
    System.out.print(testCoordsDisc + " -> ");
    System.out.println(testCoordsDisc.getBoundCharacters(testText));

    // Using Discontinuous Coordinates in a LyricSlice
    System.out.println("\nDiscontinuous Coordinates in a LyricSlice:");
    LyricSlice testSliceDisc = testLine.createSlice();
    LyricCoordsDiscontinuous testCoordsDisc_ = new LyricCoordsDiscontinuous(0, 5);
    testCoordsDisc_.addCoords(10, 12);
    testCoordsDisc_.addCoords(7, 14);
    testSliceDisc.getCoordsMap().put("English", testCoordsDisc_);
    System.out.println(testSliceDisc.getReferenceString("English"));
    System.out.println(testSliceDisc);


// =-= Test removing non-bracket characers =-=
    System.out.println("\nRemove non-bracket characters:");
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 6-i; j++) {
        System.out.println("Remove " + j + " at index " + i + ": " + testLine.removeUnlessBrackets("a[b[]c[d]]e", j, i));
      }
    }

// =-= Add and remove from bracketed text =-=
    System.out.println("\nAdd to and remove from bracketed text:");
    System.out.println(testLine.getBracketedText("English"));
    testLine.addToBracketedText("English", "abcde", 1);
    System.out.println(testLine.getBracketedText("English"));
    testLine.deleteFromBracketedText("English", 5, 0);
    System.out.println(testLine.getBracketedText("English"));
    System.out.println(testLine.getAsPlaintext("English"));


// =-= Test removing non-bracket characters
    System.out.println("\nRemove non-bracket characters from sample string:");
    String thing = "a[b[]c[d]]e";
    System.out.println(testLine.removeUnlessBrackets(thing, 3, 1));
    System.out.println("Test thing: " + thing);
    System.out.println("\n" + testLine.getSlices());
    System.out.println("\n" + testLine.getAsHTML());
    // testSlice.moveStart("English", 1);
    // testSlice.moveEnd("English", -6);


// =-= Moving end to same position as start =-=
    System.out.println("\nMove end position to same as start position:");
    System.out.println(testLine.getBracketedText("English"));
    testSlice.moveEnd("English", -7);
    System.out.println(testLine.getBracketedText("English"));

// =-= Incrementing end toward start with -1, then moving past with -2 =-=
    // System.out.println(testLine.getBracketedText("English"));
    // for (int i = 0; i < 15; i++) {
    //   testSlice.moveEnd("English", -1);
    //   System.out.println(testLine.getBracketedText("English"));
    // }
    // testSlice.moveEnd("English", -2);
    // System.out.println(testLine.getBracketedText("English"));
    // testSlice.moveEnd("English", -2);
    // System.out.println(testLine.getBracketedText("English"));
    // testSlice.moveStart("English", 2);
    // System.out.println(testLine.getBracketedText("English"));
    // testSlice.moveStart("English", 2);
    // System.out.println(testLine.getBracketedText("English"));

    System.out.println("\n" + testLine.getAsHTML());
    System.out.println(testLine.getBracketedText("English"));
    System.out.println(testLine.getSlices());
    testSlice.setStartEnd("English", null, null);
    System.out.println("\n" + testLine.getAsHTML());
    System.out.println(testLine.getBracketedText("English"));
    System.out.println(testLine.getSlices());

// =-= Test variable argument Constructor for discontinuous Lyric coords =-=
    System.out.println("\nLyricCoordsDiscontinuous Constructor:");
    LyricCoordsDiscontinuous test0 = new LyricCoordsDiscontinuous();
    LyricCoordsDiscontinuous test1 = new LyricCoordsDiscontinuous(new LyricCoords(1, 2));
    LyricCoordsDiscontinuous test2 = new LyricCoordsDiscontinuous(new LyricCoords(1, 2), new LyricCoords(3, 4));
    LyricCoordsDiscontinuous test3a = new LyricCoordsDiscontinuous(new LyricCoords(5, 6), test2);
    LyricCoordsDiscontinuous test3b = new LyricCoordsDiscontinuous(new LyricCoords(5, 6), new LyricCoords(test2));
    LyricCoordsDiscontinuous test3c = new LyricCoordsDiscontinuous(new LyricCoords(5, 6), new LyricCoordsDiscontinuous(test2));
    LyricCoordsDiscontinuous test3d = new LyricCoordsDiscontinuous(new LyricCoords(5, 6));
    test3d.addCoords(test2);

    System.out.println("Test 0: " + test0);
    System.out.println("Test 1: " + test1);
    System.out.println("Test 2: " + test2);
    System.out.println("Test 3a: " + test3a);
    System.out.println("Test 3b: " + test3b);
    System.out.println("Test 3c: " + test3c);
    System.out.println("Test 3d: " + test3d);
    System.out.println("Changing second test coords...");
    test2.getCoordsList().get(0).moveCoords(5, 7);
    System.out.println("Test 2: " + test2);
    System.out.println("Test 3a: " + test3a);
    System.out.println("Test 3b: " + test3b);
    System.out.println("Test 3c: " + test3c);
    System.out.println("Test 3d: " + test3d);

// =-= Editing inside a HashMap =-=
    System.out.println("\nEditing inside a HashMap:");
    HashMap<Integer, LyricCoords> testMap = new HashMap<Integer, LyricCoords>();
    LyricCoords test4 = new LyricCoords(1, 2);
    testMap.put(0, test4);
    System.out.println(test4);
    System.out.println(testMap.get(0));
    System.out.println(testMap.get(0).setEnd(4));
    System.out.println(test4);
    System.out.println(testMap.get(0));

    System.out.println("\nUsing setStartEnd to make discontinuous into continuous");
    LyricLine testLine3 = new LyricLine(languages);
    testLine3.setBracketedText("English", "abcdefghijklmnopqrst");
    System.out.println(testLine3.getAsPlaintext("English"));
    LyricSlice testSlice5 = testLine3.createSlice().setStartEnd("English", 0, 2);
    System.out.println(testLine3.getAsBracketed("English"));
    System.out.println(testSlice5);
    testSlice5.addCoords("English", 5, 7);
    System.out.println(testLine3.getAsBracketed("English"));
    System.out.println(testSlice5);
    testSlice5.setStartEnd("English", 10, 12);
    // test5 = test5.setStartEnd(10, 12, new StringBuilder("[a]bc[d]efghijklmnopqrst"), );
    System.out.println(testLine3.getAsBracketed("English"));
    System.out.println(testSlice5);

  }
}