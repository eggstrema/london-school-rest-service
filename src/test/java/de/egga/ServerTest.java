package de.egga;

import io.javalin.Javalin;
import io.javalin.plugin.json.JavalinJackson;
import io.javalin.testtools.JavalinTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ServerTest {

  Javalin app = new Server().javalinApp();
  String usersJson = new JavalinJackson().toJsonString(new UserController().users);

  @Test
  public void GET_to_fetch_users_returns_list_of_users() {
    JavalinTest.test(app, (server, client) -> {
      assertThat(client.get("/users").code()).isEqualTo(200);
      assertThat(client.get("/users").body().string()).isEqualTo(usersJson);
    });
  }
}
