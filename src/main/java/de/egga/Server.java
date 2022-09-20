package de.egga;

import de.egga.controllers.UserController;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Server {

  private final Javalin app;

  public Server(UserController controller) {
    this.app = Javalin.create();

    app.routes(() -> {
      path("users", () -> {
        get(controller::getAll);
        post(controller::create);
        path("data", () -> {
          get(controller::getData);
        });
      });
    });
  }

  public Javalin app() {
    return app;
  }
}
