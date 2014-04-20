package controllers;

import play.*;
import play.mvc.*;
import play.libs.F.Callback;
import play.libs.F.Callback0;

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

  public static WebSocket<String> guess() {
    WebSocket<String> ws = null;
    try {
      final int r = Integer.parseInt(session("random"));
      ws = new WebSocket<String>() {

      public void onReady(WebSocket.In<String> in, final WebSocket.Out<String> out) {

        in.onMessage(new Callback<String>() {
          public void invoke(String g) {
            try {
              int guess = Integer.parseInt(g);
              String res = "< secret number!";
              if (guess > r) {
                res = "> secret number!";
              } else if (guess == r) {
                res = "correct!";
              }
              out.write(res);
            } catch (NumberFormatException e) {
              System.err.println("Guess: NumberFormatException");
            }
          }
        });

        in.onClose(new Callback0() {
          public void invoke() {
            System.out.println("Disconnected!");
          }
        });

      }
    };

    } catch (Exception e) {
      System.err.println("Exception while creating WebSocket: "+ e.getMessage());
    }

    return ws;
  }

}

