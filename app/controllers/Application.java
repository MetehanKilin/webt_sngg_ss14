package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

import java.util.Random;
import java.lang.Math;

public class Application extends Controller {

    private static int max = 200;
    private static int min = 0;
    private static Random rand = new Random();

    public static Result index() {

      int r = Math.abs(rand.nextInt()%max);
      session("random", ""+r);
      return ok(index.render(min, max));
    }

    public static Result guess(String g) {
      if (session("random") == null) {
        return ok("Press New to play again!"); //redirect("/");
      }

      try {
        int guess = Integer.parseInt(g);
        int r = Integer.parseInt(session("random"));
        String res = "< secret number!";
        if (guess > r) res = "> secret number!";
        else if (guess == r) {
          res = "correct!";
          session().clear();
        }

        return ok(res);
      }
      catch (NumberFormatException e) {
        return redirect("/");
      }
    }
}
