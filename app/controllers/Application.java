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
      return ok(index.render(min, max, false, 0));
    }

    public static Result guess(String g) {
      if (session("random") == null) {
        return redirect("/");
      }

      try {
        int guess = Integer.parseInt(g);
        int r = Integer.parseInt(session("random"));
        int res = -1;
        if (guess > r) res = 1;
        else if (guess == r) {
          res = 0;
          session().clear();
        }

        return ok(index.render(min, max, true, res));
      }
      catch (NumberFormatException e) {
        return redirect("/");
      }
    }
}
