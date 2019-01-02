public class LyricMain {
  
  public static void main(String[] args)
  {
    String[] languages = {"English", "Japanese"};
    LyricLine testLine = new LyricLine(languages);
    testLine.setBracketedText("Japanese", "honbun tesuto");
    testLine.setBracketedText("English", "test text");
    System.out.println(testLine.getAsPlaintext("Japanese"));
    System.out.println(testLine.getAsPlaintext("English"));

    LyricSlice testSlice = testLine.createSlice(1);
    testSlice.setStartEnd("Japanese", -5, 6);
    testSlice.setStartEnd("English", 5, 18);
    testSlice.setStartEnd("English", 5, 18);
    System.out.println("Test Slice:\n" + testSlice);
    System.out.println("Test Line:\n" + testLine);

    testLine.createSlice(2).setStartEnd("English", 8, 9);
    testLine.createSlice(1).setStartEnd("English", 0, 4).setStartEnd("Japanese", 9, 17);
    /*
    testLine.createSlice(3).setStartEnd("English", 0, 2).setStartEnd("Japanese", 7, 9);
    testLine.createSlice(3).setStartEnd("English", 2, 3).setStartEnd("Japanese", 9, 11);
    testLine.createSlice(3).setStartEnd("English", 3, 4).setStartEnd("Japanese", 11, 13);
    */

    /*
    System.out.println("\nNo filter:");
    System.out.println(testLine.getSlices());
    System.out.println("\nFiltered by category 1:");
    System.out.println(testLine.getSlices(1));
    */

    // System.out.println(testLine.getAsHTML());

    // System.out.println(testLine.getSlices());
    // testLine.deleteFromPlainText("English", 1, 4);
    // testLine.addToPlainText("English", "___", 8);

    // testLine.modifyPlainText("English", " thing", 4);
    // System.out.println(testLine.getAsHTML());
    System.out.println(testLine.getSlices());
    // System.out.println(testLine.getAsBracketed("English"));

// =-= Test Discontinuous Lyric Coords with
//       - plain coordinates (testCoordsOrig)
//       - polymorphic discontinuous coordinates (testCoordsCast)
//       - discontinuous coordinates (testCoordsDisc)
    LyricCoords              testCoordsOrig = new LyricCoords             (1, 3);
    LyricCoords              testCoordsCast = new LyricCoordsDiscontinuous(1, 3);
    LyricCoordsDiscontinuous testCoordsDisc = new LyricCoordsDiscontinuous(1, 3);
    String testText = "abcdefghij";

    // Plain Coordinates
    System.out.println("\nPlain Coordinates:");
    System.out.print(testCoordsOrig + " -> ");
    System.out.println(testCoordsOrig.getBoundCharacters(testText));
    testCoordsOrig = testCoordsOrig.addCoords(5, 7);
    System.out.print(testCoordsOrig + " -> ");
    System.out.println(testCoordsOrig.getBoundCharacters(testText));

    // Polymorphic Discontinuous Coordinates
    System.out.println("\nPolymorphic Discontinuous Coordinates:");
    System.out.print(testCoordsCast + " -> ");
    System.out.println(testCoordsCast.getBoundCharacters(testText));
    testCoordsCast = testCoordsCast.addCoords(5, 7);
    System.out.print(testCoordsCast + " -> ");
    System.out.println(testCoordsCast.getBoundCharacters(testText));

    // Plain Discontinuous Coordinates
    System.out.println("\nPlain Discontinuous Coordinates:");
    System.out.print(testCoordsDisc + " -> ");
    System.out.println(testCoordsDisc.getBoundCharacters(testText));
    testCoordsDisc = testCoordsDisc.addCoords(5, 7);
    System.out.print(testCoordsDisc + " -> ");
    System.out.println(testCoordsDisc.getBoundCharacters(testText));

    // Using Discontinuous Coordinates in a LyricSlice
    System.out.println("\nDiscontinuous Coordinates in a LyricSlice:");
    LyricSlice testSliceDisc = testLine.createSlice();
    LyricCoordsDiscontinuous testCoordsDisc_ = new LyricCoordsDiscontinuous(0, 5);
    testCoordsDisc_.addCoords(7, 14);
    testCoordsDisc_.addCoords(10, 12);
    testSliceDisc.getCoordsMap().put("English", testCoordsDisc_);
    System.out.println(testSliceDisc.getReferenceString("English"));
    System.out.println(testSliceDisc);


    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 6-i; j++) {
        System.out.println("Remove " + j + " at index " + i + ": " + testLine.removeUnlessBrackets("a[b[]c[d]]e", j, i));
      }
    }

    System.out.println(testLine.getBracketedText("English"));
    testLine.addToBracketedText("English", "abcde", 1);
    System.out.println(testLine.getBracketedText("English"));
    testLine.deleteFromBracketedText("English", 5, 0);
    System.out.println(testLine.getBracketedText("English"));
    System.out.println(testLine.getAsPlaintext("English"));

    String thing = "a[b[]c[d]]e";
    System.out.println(testLine.removeUnlessBrackets(thing, 3, 1));
    System.out.println("Test thing: " + thing);
    System.out.println("\n" + testLine.getSlices());
    System.out.println("\n" + testLine.getAsHTML());
    // testSlice.moveStart("English", 1);
    // testSlice.moveEnd("English", -6);

// =-= Moving end to same position as start =-=
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
  }
}