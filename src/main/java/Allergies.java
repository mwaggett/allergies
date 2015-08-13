import java.util.Random;
import java.util.HashMap;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import static spark.Spark.*;
import spark.ModelAndView;
//import spark.template.velocity.VelocityTemplateEngine;
import java.util.Map;

public class Allergies {

  public static void main(String[] args){}

  public static Boolean checkInput(String scoreStr) {

    try {
      Integer score = Integer.parseInt(scoreStr);
      if (score > 255 || score < 0) {
        return false;
      }
    } catch(NumberFormatException e) {
        return false;
      }
    return true;
  }

  public static String allergyArray(Integer score) {

    ArrayList<String> allergies = new ArrayList<String>();

    if (score >= 128) {
      allergies.add("cat");
      score -= 128;
    }

    if (score >= 64) {
      allergies.add("pollen");
      score -= 64;
    }

    if (score >= 32) {
      allergies.add("chocolate");
      score -= 32;
    }

    if (score >= 16) {
      allergies.add("tomatoes");
      score -= 16;
    }

    if (score >= 8) {
      allergies.add("strawberries");
      score -= 8;
    }

    if (score >= 4) {
      allergies.add("shellfish");
      score -= 4;
    }

    if (score >= 2) {
      allergies.add("peanuts");
      score -= 2;
    }

    if (score >= 1) {
      allergies.add("eggs");
      score -= 1;
    }

//    HashMap<Integer, String> allergies = new HashMap<Integer,String>();
//    allergies.put(128, "cats");
//    allergies.put(64, "pollen");



    String allergiesStr = allergies.toString();
    return allergiesStr;

  }

}
