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
    testLine.createSlice(1).setStartEnd("English", 0, 4).setStartEnd("Japanese", 11, 17);
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
    // System.out.println("\n" + testLine.getAsHTML());
  }
}