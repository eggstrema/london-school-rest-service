package de.egga;

import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.post;

public class Server {

  private Javalin app;

  public Server() {
    app = Javalin.create();
    UserController controller = new UserController();

    app.routes(() -> {
      get("/users", controller::getAll);
      post("/users", controller::create);
    });
  }

  public Javalin javalinApp() {
    return app;
  }
}
