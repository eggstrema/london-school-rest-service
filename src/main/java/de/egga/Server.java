package de.egga;

import de.egga.controllers.UserController;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.post;

public class Server {

  private final Javalin app;

  public Server(UserController controller) {
    this.app = Javalin.create();

    app.routes(() -> {
      get("/users", controller::getAll);
      post("/users", controller::create);
    });
  }

  public Javalin app() {
    return app;
  }
}
