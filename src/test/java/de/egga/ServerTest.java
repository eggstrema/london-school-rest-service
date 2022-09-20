package de.egga;

import de.egga.controllers.UserController;
import de.egga.model.User;
import de.egga.services.UserService;
import io.javalin.testtools.JavalinTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ServerTest {

  UserService userService = mock(UserService.class);
  UserController userController = new UserController(userService);
  Server server = new Server(userController);

  @Test
  public void GET_to_fetch_users_gives_200() {
    JavalinTest.test(server.app(), (server, client) -> {
      assertThat(client.get("/users").code()).isEqualTo(200);
    });
  }

  @Test
  public void GET_to_fetch_personal_data_gives_200() {
    when(userService.getUser(any())).thenReturn(new User(1337, "Laura", "Berlin", 12353));
    JavalinTest.test(server.app(), (server, client) -> {
      assertThat(client.get("/users/1337").code()).isEqualTo(200);
    });
  }

  @Test
  public void POST_to_create_users_gives_201_for_valid_username() {
    JavalinTest.test(server.app(), (server, client) -> {
      assertThat(client.post("/users?username=horst")
        .code()).isEqualTo(201);
    });
  }

  @Test
  public void POST_to_create_users_throws_for_invalid_username() {
    JavalinTest.test(server.app(), (server, client) -> {
      assertThat(client.post("/users")
        .code()).isEqualTo(400);
    });
  }
}
