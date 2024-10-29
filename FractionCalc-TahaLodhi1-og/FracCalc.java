// Taha Lodhi
// Period 2
// Fraction Calculator Project

import java.util.*;

// TODO: Description of what this program does goes here.
public class FracCalc {

  // It is best if we have only one console object for input
  public static Scanner console = new Scanner(System.in);

  // This main method will loop through user input and then call the
  // correct method to execute the user's request for help, test, or
  // the mathematical operation on fractions. or, quit.
  // DO NOT CHANGE THIS METHOD!!
  public static void main(String[] args) {

    // initialize to false so that we start our loop
    boolean done = false;

    // When the user types in "quit", we are done.
    while (!done) {
      // prompt the user for input
      System.out.println("Enter your operation");
      String input = getInput();

    

      // special case the "quit" command
      if (input.equalsIgnoreCase("quit")) {
        done = true;
      } else if (!UnitTestRunner.processCommand(input, FracCalc::processCommand)) {
        // We allowed the UnitTestRunner to handle the command first.
        // If the UnitTestRunner didn't handled the command, process normally.
        String result = processCommand(input);


        System.out.println(result);
      }
    }

    System.out.println("Goodbye!");
    console.close();
  }

  // Prompt the user with a simple, "Enter: " and get the line of input.
  // Return the full line that the user typed in.
  public static String getInput() {
    String example = console.nextLine();

    // TODO: Implement this method
    return example;

  }

  // processCommand will process every user command except for "quit".
  // It will return the String that should be printed to the console.
  // This method won't print anything.
  // DO NOT CHANGE THIS METHOD!!!
  public static String processCommand(String input) {

    if (input.equalsIgnoreCase("help")) {
      return provideHelp();
    }

    // if the command is not "help", it should be an expression.
    // Of course, this is only if the user is being nice.
    return processExpression(input);
    
  }

  // Lots work for this project is handled in here.
  // Of course, this method will call LOTS of helper methods
  // so that this method can be shorter.
  // This will calculate the expression and RETURN the answer.
  // This will NOT print anything!
  // Input: an expression to be evaluated
  // Examples:
  // 1/2 + 1/2
  // 2_1/4 - 0_1/8
  // 1_1/8 * 2
  // Return: the fully reduced mathematical result of the expression
  // Value is returned as a String. Results using above examples:
  // 1
  // 2 1/8
  // 2 1/4
  public static String processExpression(String input) {

    
    return parseExpression(input);    
   
  }

  // Returns a string that is helpful to the user about how
  // to use the program. These are instructions to the user.
  public static String provideHelp() {
    // TODO: Update this help text!

    String help = "enter to numbers and an operator in between \n";
    help += "you can also do fractions";

    return help;
  }
  private static String parseExpression(String line) {

    //return checkPoint1(line);
    //return checkPoint2(line);
    return checkPoint3and4(line);
  }
  public static String checkPoint1(String line) {
    return line;
  }
  


  public static String checkPoint2(String line) {

    Scanner parser = new Scanner(line);
    String num1 = parser.next();
    String op = parser.next();
    String num2 = parser.next();
    if (!num2.contains("/")) {
      num1 = num2;
    } else if (num2.contains("/") && !num2.contains("_")) {
      num1 = "0";
    } else if (num2.contains("_")) {
      int indexofu = num2.indexOf("_");
      num1 = num2.substring(0, indexofu);
      System.out.println("look at this:" + num1);
    }
    String numerator = " ";
    String denominator = " ";
    if (!num2.contains("/")) {
      numerator = "0";
      denominator = "1";

    } else if (num2.contains("/") && num2.contains("_")) {
      numerator = num2.substring(num2.indexOf("_") + 1, num2.indexOf("/"));
      System.out.println("look at this:" + numerator);
      denominator = num2.substring(num2.indexOf("/") + 1);
    } else {
      numerator = num2.substring(0, num2.indexOf("/"));
      System.out.println("look at this:" + numerator);
      denominator = num2.substring(num2.indexOf("/") + 1);

    }
    int numerator2 = Integer.valueOf(numerator);
    int denominator2 = Integer.valueOf(denominator);
    if (numerator2 < 0 && denominator2 < 0) {
      numerator2 = numerator2 * -1;
      denominator2 = denominator2 * -1;
      numerator = String.valueOf(numerator2);
      denominator = String.valueOf(denominator2);
    } else {
      numerator = String.valueOf(numerator2);
      denominator = String.valueOf(denominator2);

    }
    numerator2 = Integer.valueOf(numerator);
    denominator2 = Integer.valueOf(denominator);
    if (denominator2 < 0 && numerator2 > 0) {
      numerator2 = numerator2 * -1;
      denominator2 = denominator2 * -1;
      numerator = String.valueOf(numerator2);
      denominator = String.valueOf(denominator2);
    } else {
      numerator = String.valueOf(numerator2);
      denominator = String.valueOf(denominator2);

    }

    String result = "Op:" + op + " Whole:" + num1 + " Num:" + numerator
        + " Den:" + denominator;

    return result;
  }
  // if this string contains a mixed number indicated by an _ and
  // fraction by / parse and convert into a fraction string to return
  // we won't reduce the fraction at this point and instead do it after
  // performing op on both operands on the result.
    private static Fraction parseMixed(String input) {
      String wholeNumString = "";
      String numeratorStr = "";
      String denomStr = "";
      int wholeNum = 0;
      int num, denom;
      Fraction numFrac = new Fraction(0,0);

      // if we have a simple whole number only (no fraction)
      if(!input.contains("_") && !input.contains("/")){ 
        numFrac.updateFraction(Integer.parseInt(input), 1);
      }

      

      else { // process mixed numbers (fraction only or both whole and fraction)
        if(input.contains("_")){ // if we have a whole number
        wholeNumString = input.substring(0, input.indexOf("_"));
        }

        if (wholeNumString != "") { // add whole number if there was any
          wholeNum = Integer.parseInt(wholeNumString);
        }

        if(input.contains("/")){

          if(wholeNumString == "")
            numeratorStr = input.substring(0, input.indexOf("/"));

          else
            // length + 1 below to skip _
            numeratorStr = input.substring(wholeNumString.length()+1, input.indexOf("/"));

          num = Integer.parseInt(numeratorStr);
          denomStr = input.substring(input.indexOf("/")+1);
          denom = Integer.parseInt(denomStr);

          if(wholeNum !=0) {
            boolean isWNegative = false;

            // handle the case of negative whole number 
            // e.g. -1_1/6 (keep sign as a whole and add absolute values)
            if(wholeNum < 0) {
              wholeNum = -wholeNum;
              isWNegative = true;
            }

            // convert whole number into an improper fraction
            num = wholeNum * denom + num;

            if(isWNegative)
              num = -num;
          }

          numFrac.updateFraction(num, denom);
        }
      }

      return numFrac;
    }
  // Returns the final string in desired format
  // Only adds denominator if its > 1 and separates out whole
  // number from numerator. Adds space etc.
  private static String format2Print(Fraction resF) {

    String result = "";
    boolean isNumNegative = false;
    boolean isDenomNegative = false;
    int num = resF.getNumerator();
    int denom = resF.getDenominator();

    // if either has a negative sign, track it and convert to positive
    if(denom < 0) {
      denom = -denom;
      isDenomNegative = true;
    }

    if(num < 0){
      num = -num;
      isNumNegative = true;
    }
    if(denom == 0) {
      result = "ERROR: Cannot divide by 0";
      return result;
    }

    // Append negative sign if one of num or denom was negative
    // Also exclude case of both being negative resulting in positive
    if((isDenomNegative && !isNumNegative) ||
      (!isDenomNegative && isNumNegative))
      result += "-";

    // leaving out denom if its 1 and reduce the fraction to lowest form
    if (denom != 1 && num > denom) {

      int whole = num/denom;
      num = num%denom;
      result += String.valueOf(whole) + " ";
    }

    result += String.valueOf(num);

    // Only add denominator if its > 1
    if(denom > 1)
      result += "/" + String.valueOf(denom);

    return result;
  }


 
  
    /*
    Update your processExpression method to return a single String in the proper format. It
    will no longer return the parts of the second operand. Instead, it will compute the
    addition of the two fractions. It will parse the input, add the two fractions, and return an
    answer that is fully normalized and reduced. Here are details on what that means:
    1. Fractions must be reduced. Output 1/2 not 2/4.
    2. Mixed fractions will output to the screen with a space, not an “_”.
    3. Denominators will never have a negative value.
    4. If the answer can be expressed as a whole number without a fraction, then don’t
    output the fraction.
    5. If the answer is only a fraction, then don’t output the whole number.
    Here is sample output:
    -1_1/2 + -1/-2   --- returns -1
    1_3/7 + 1_1/7    --- returns 2 4/7
    6/26 + 0         --- returns 3/13
     */
    public static String checkPoint3and4(String line){

      Scanner parser = new Scanner(line);
      String num1="";
      String op = "";
      String num2 = "";
      
      if (parser.hasNext())
        num1 = parser.next();
      if (parser.hasNext())
          op = parser.next();
      if (parser.hasNext())
        num2 = parser.next();

      // if there not two operands and an operator then return
      if (!num1.equals("") && !num2.equals("") && !op.equals("")) {
        
        Fraction num1F = parseMixed(num1);
        Fraction num2F = parseMixed(num2);
        Fraction resultF = new Fraction(0,1); // Fraction object for final result
      
      
      int res_num = 0;
      int res_denom = 1;

      // following are only needed for + and - to multiple with LCM
      int left_multiplier = 1; // for a differnt denominator, this is multiplied by numerator
      int right_multiplier = 1;

      if (num1F.getDenominator() == num2F.getDenominator())
        res_denom = num1F.getDenominator();
      else {
        int g_temp = gcdFind(num1F.getDenominator(), num2F.getDenominator());
        res_denom = lcmFind(num1F.getDenominator(), num2F.getDenominator(), g_temp);
        
        if ((num1F.getDenominator() != 0) && (num2F.getDenominator() != 0)) {
          // multipliers are only needed for + and - operations
          left_multiplier = res_denom / num1F.getDenominator();
          right_multiplier = res_denom / num2F.getDenominator();
        }
      }

      if(op.equals("+"))
        res_num = num1F.getNumerator()*left_multiplier + 
              num2F.getNumerator()*right_multiplier;

      else if(op.equals("-"))
        res_num = num1F.getNumerator()*left_multiplier - 
              num2F.getNumerator()*right_multiplier;

      // multipliers aren't needed for * and /
      else if(op.equals("*"))
        res_num = num1F.getNumerator() * num2F.getNumerator();

      // for division, swap the numertor with denominator and use *
      else if(op.equals("/")) {
        res_num = num1F.getNumerator() * num2F.getDenominator();
        res_denom = num1F.getDenominator() * num2F.getNumerator();
      }

      if (res_denom > 1){ // denominator would be 1 for non mixed number
        // Simplify the fraction using GCD
        int gcd = gcdFind(res_num, res_denom);

        if(gcd!=1){
          res_num = res_num/gcd;
          res_denom = res_denom/gcd;
        }
      }

      parser.close();

      resultF.updateFraction(res_num, res_denom);

      return format2Print(resultF);
      } else {
        parser.close();
        String out = "ERROR: Invalid input";
        return out;
        
      }   
    }
    // Returns a string that is helpful to the user about how
    // to use the program. These are instructions to the user.
    

    // find lcm of two numbers given GCD
    // Product of 2 numbers = GCD * LCM
    private static int lcmFind(int a, int b, int gcd) {    
      return a*b / gcd;
    }
    private static int gcdFind(int a, int b) {
      int tmp;

     // System.out.print("GCD of " + a + " and " + b + " is:");

      while (b != 0) {
        tmp = b;
        b   = a % b;
        a   = tmp;
      }

      //System.out.println(a);
      return a;
    }  

  }




    
    
  

