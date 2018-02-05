import java.util.*;
import java.io.*;
/*
  All -1 values in html file means undefined/wrong argument
*/

public class HailstoneSequence {
  private int steps;

  //Constructor
  public HailstoneSequence() {
    steps = 0;
  }

  //Cleans method makeHtml()
  public String makeNice(int input, int step, int large) {
    return
      "<div><table style='width:25%'>" +
        "<tr><td>The input was: </td><td>"
          + input
      + "</td></tr>"
      + "<tr><td>The number of steps was: </td><td>"
          + step
      + "</td></tr>"
      + "<tr><td>The second largest number was: </td><td>"
          + large
      + "</td></tr>"
      + "</table></div>";
  }

  //Takes the result parameters and makes them in a HTML file
  public void makeHtml(int input, int step, int large) throws IOException {
    String html = this.makeNice(input, step, large);
    File f = new File(".\\results\\result.html");
    try {
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        bw.write(html);
        bw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /*
  The funcionality method which keeps track of how many steps are there
  and makes a list of all values then sorts the list so it can find
  the second largest number of all values
  */
  public int[] count(int x) {
    ArrayList<Integer> values = new ArrayList<>();
    int[] array = new int[3];
    array[0] = x;
    while (x != 1) {
      values.add(x);
      x = iteration(x);
      steps++;
    }
    Collections.sort(values);
    array[1] = steps;
    if (values.size() > 1) {
      array[2] = values.get(values.size()-2);
    } else {
      array[2] = 1;
    }
    return array;
  }
   //Iteration used in count()
   public int iteration(int x) {
     if (x % 2 == 0) {
       return x/2;
     } else {
       return 3*x + 1;
     }
   }



  public static void main(String[] args) {
    HailstoneSequence temp = new HailstoneSequence();
    System.out.println("Welcome to HailstoneSequence calculator!");
    Scanner reader = new Scanner(System.in);
    int x = Integer.parseInt(reader.next());
    if (x <= 0) {
      try {
        temp.makeHtml(-1, -1, -1);
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else if (x == 1){
      int[] results = new int[3];
      results[0] = 1;
      results[1] = 0;
      results[2] = -1;

      try {
        temp.makeHtml(results[0], results[1], results[2]);
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {

      int[] results = temp.count(x);

      try {
        temp.makeHtml(results[0], results[1], results[2]);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }
}
