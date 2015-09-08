import java.util.Random;
import java.util.HashMap;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import static spark.Spark.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import java.util.Map;
import spark.QueryParamsMap;

public class Allergies {

  public static void main(String[] args){
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap model = new HashMap();
      model.put("template", "templates/home.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/results", (request, response) -> {
      HashMap model = new HashMap();
      model.put("template", "templates/results.vtl");

      String scoreStr = request.queryParams("score");
      Boolean goodInput = checkInput(scoreStr);
      if (goodInput) {
        Integer score = Integer.parseInt(scoreStr);
        model.put("score", score);
        String allergies = allergyArray(score);
        model.put("allergies", allergies);
      } else {
        String allergies = "That is not valid input.";
        model.put("allergies", allergies);
      }

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  //   get("/getscore", (request, response) -> {
  //     HashMap model = new HashMap();
  //     model.put("template", "templates/getscore.vtl");
  //     System.out.println(request.queryMap("allergies").getClass());
  //
  //     QueryParamsMap myMap = request.queryMap("allergies");
  //     System.out.println(myMap.hasKeys());
  //     // String[] values = myMap.values();
  //     // System.out.println(values[0]);
  //     String allergies = request.queryParams("allergies");
  //     model.put("allergies", allergies);
  //
  //     return new ModelAndView(model, layout);
  //   }, new VelocityTemplateEngine());
  
  }

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

    //String allergiesStr = allergies.toString();

    String allergiesStr = "You are allergic to the following: ";

    for (String allergy : allergies) {
      allergiesStr = allergiesStr + allergy;
      if (allergy.equals(allergies.get(allergies.size()-1))) {
        allergiesStr = allergiesStr + ".";
      } else {
        allergiesStr = allergiesStr + ", ";
      }
    }
    return allergiesStr;

  }
}
