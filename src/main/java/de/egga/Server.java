package de.egga;

import de.egga.controllers.UserController;
import de.egga.exceptions.UserNotFoundException;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Server {

  private final Javalin app;

  public Server(UserController controller) {
    this.app = Javalin.create();

    app.routes(() -> {
      path("users", () -> {
        post(controller::create);
        path("{id}", () -> {
          get(controller::getUser);
        });
      });
    });

    app.exception(UserNotFoundException.class, (exception, context) -> {
      context.json("User not found: " + context.pathParam("id")).status(404);
    });
  }

  public Javalin app() {
    return app;
  }
}
