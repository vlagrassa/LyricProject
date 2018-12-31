public class LyricMain {
  
  public static void main(String[] args)
  {
    String[] languages = {"English", "Japanese"};
    LyricLine testLine = new LyricLine(languages);
    testLine.setPlainText("Japanese", "honbun tesuto");
    testLine.setPlainText("English", "test text");
    System.out.println(testLine.getPlainText("Japanese"));
    System.out.println(testLine.getPlainText("English"));

    LyricSlice testSlice = testLine.createSlice(1);
    testSlice.setStartEnd("Japanese", -5, 6);
    testSlice.setStartEnd("English", 5, 18);
    System.out.println("Test Slice:\n" + testSlice);
    System.out.println("Test Line:\n" + testLine);

    testLine.createSlice(2).setStartEnd("English", 7, 8);
    testLine.createSlice(1).setStartEnd("English", 0, 4).setStartEnd("Japanese", 7, 13);
    /*
    testLine.createSlice(3).setStartEnd("English", 0, 2).setStartEnd("Japanese", 7, 9);
    testLine.createSlice(3).setStartEnd("English", 2, 3).setStartEnd("Japanese", 9, 11);
    testLine.createSlice(3).setStartEnd("English", 3, 4).setStartEnd("Japanese", 11, 13);
    */

    System.out.println("\nNo filter:");
    System.out.println(testLine.getSlices());
    System.out.println("\nFiltered by category 1:");
    System.out.println(testLine.getSlices(1));

    System.out.println(testLine.getAsHTML());

    // System.out.println(testLine.getSlices());
    // testLine.deleteFromPlainText("English", 8, 0);
    testLine.addToPlainText("English", "___", 8);

    // testLine.modifyPlainText("English", " thing", 4);
    System.out.println(testLine.getAsHTML());
  }
}