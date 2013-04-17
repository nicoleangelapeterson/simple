import java.io.*;
import java.util.*;

public class PersonalityTest {
   
   public static final int ARRSIZE = 4;
   public static void main(String[] args) throws FileNotFoundException {
      
      int[] arrA = new int[ARRSIZE];
      int[] arrB = new int[ARRSIZE];
      int[] arrP = new int[ARRSIZE];
      char[] arrT = new char[ARRSIZE];
      
      // Ask for the input & output file name and gives an introduction prompt
      System.out.println("This is the personality test reader program!");
      Scanner consoleIn = new Scanner(System.in);
      String inputFile = getValidInputFileName(consoleIn);
      
      System.out.print("Output file name: ");
      String outputFile = consoleIn.nextLine();
      
      // figures out where to output to
      PrintStream output;
      if (outputFile.length() > 0) {
         output = new PrintStream(new File(outputFile));
      } else {
         output = System.out;
      }
            
      // Pulls in names and letters while there is a next line
      Scanner input = new Scanner(new File(inputFile));
      while (input.hasNext()) {
         String currentName = input.nextLine();
         String answers = input.nextLine();
         arrA = collectData(answers, 'a');
         arrB = collectData(answers, 'b');
         arrP = percentage(arrA, arrB);
               
         // figures out what type of personality
         arrT[0] = returnType(arrP[0],'E','I');
         arrT[1] = returnType(arrP[1],'S','N');
         arrT[2] = returnType(arrP[2],'T','F');
         arrT[3] = returnType(arrP[3],'J','P');
               
         //does the output for the person...
         writeOutput(output, currentName,arrA,arrB,arrP,arrT);
      }
   }
         
   // returns back a valid input file name
   public static String getValidInputFileName(Scanner consoleIn) {
      
      System.out.print("Input file name: ");
      String name = consoleIn.nextLine();
      File test = new File(name);
      
      while (!test.exists()) {
         System.out.print("Input file name: ");
         name = consoleIn.nextLine();
         test = new File(name);
      }
      
      return name;
   }
            
            
   // Keeps track of amount of A's and B's
   public static int[] collectData(String answers, char z) {
      
      // create array and elements are initialized to 0 by default
      int[] arr = new int[ARRSIZE];
               
      for (int i = 0; i < 70; i++) {
         
         if (answers.toLowerCase().charAt(i) == z) {
            
            if (i % 7 == 0) {
               arr[0]++;
            } else if (i % 7 == 1 || i % 7 == 2) {
               arr[1]++;
            } else if (i % 7 == 3 || i % 7 == 4) {
               arr[2]++;
            } else if (i % 7 == 5 || i % 7 == 6) {
               arr[3]++;
            }
         }
      }
   
      return arr;
   }
   
   // calculates the B percentage array
   public static int[] percentage(int[] arrA, int[] arrB) {
      
      int[] arr = new int[ARRSIZE];
      
      for(int i = 0; i < ARRSIZE; i++) {
         // calculate the percentage of "B" answers
         arr[i] =  (int) Math.round((((double)arrB[i] / (double)(arrA[i] + arrB[i])) * 100));
      }
      
      return arr;
   }
            
   // returns back either A or B if P is < 50 or >= 50, or x if equal to 50
   public static char returnType(int P, char A, char B) {
      
      if (P < 50) {
         return A;
      } else if (P > 50) {
         return B;
      } else {
         return 'X';
      }
   }
                              
   // writes the output for the given person
   public static void writeOutput(PrintStream out, String name, int[] A, int[] B, int[] P, char[] T) {
      
      out.println(name);
      out.println("Answers: [" + A[0] + "A-" + B[0] + "B, " + A[1] + "A-" + B[1] + "B, " + A[2] + "A-" + B[2] + "B, " + A[3] + "A-" + B[3] + "B]");
      out.println("Percent B: [" + P[0] + ", " + P[1] + ", " + P[2] + ", " + P[3] + "]");
      out.print("Type: ");
      
      for(int i = 0; i < ARRSIZE; i++) {
         out.print(T[i]);
      }
      
      out.println();
      out.println();
   }
}

