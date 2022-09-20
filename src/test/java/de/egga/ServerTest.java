package de.egga;

import de.egga.controllers.UserController;
import de.egga.exceptions.UserNotFoundException;
import de.egga.services.UserService;
import io.javalin.http.BadRequestResponse;
import io.javalin.testtools.JavalinTest;
import org.junit.jupiter.api.Test;

import static de.egga.model.UserFactory.anyUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ServerTest {

  UserService userService = mock(UserService.class);
  UserController userController = new UserController(userService);
  Server server = new Server(userController);

  @Test
  public void givenValidId__whenGetUserData_thenStatusCode() {
    when(userService.getUser(any())).thenReturn(anyUser());
    JavalinTest.test(server.app(), (server, client) -> {
      assertThat(client.get("/users/1337").code()).isEqualTo(200);
    });
  }

  @Test
  public void givenUnknownId__whenGetUserData_thenStatusCode() {
    when(userService.getUser(any())).thenThrow(new UserNotFoundException());
    JavalinTest.test(server.app(), (server, client) -> {
      assertThat(client.get("/users/1337").code()).isEqualTo(404);
    });
  }

  @Test
  public void givenUnknownId__whenGetUserData_thenErrorMessageInBody() {
    when(userService.getUser(any())).thenThrow(new UserNotFoundException());
    JavalinTest.test(server.app(), (server, client) -> {
      assertThat(client.get("/users/1337").body().string()).isEqualTo("User not found: 1337");
    });
  }

  @Test
  public void givenInvalidId__whenGetUserData_thenStatusCode() {
    when(userService.getUser(any())).thenThrow(new BadRequestResponse());
    JavalinTest.test(server.app(), (server, client) -> {
      assertThat(client.get("/users/mona").code()).isEqualTo(400);
    });
  }

  @Test
  public void givenInvalidId__whenGetUserData__thenErrorMessageInBody() {
    when(userService.getUser(any())).thenThrow(new BadRequestResponse("Not a valid ID: mona"));
    JavalinTest.test(server.app(), (server, client) -> {
      assertThat(client.get("/users/mona").body().string()).isEqualTo("Not a valid ID: mona");
    });
  }

  @Test
  public void givenValidName__whenCreateUser__thenStatusCode() {
    JavalinTest.test(server.app(), (server, client) -> {
      assertThat(client.post("/users?username=horst")
        .code()).isEqualTo(201);
    });
  }

  @Test
  public void givenInvalidName__whenCreateUser__thenStatusCode() {
    JavalinTest.test(server.app(), (server, client) -> {
      assertThat(client.post("/users")
        .code()).isEqualTo(400);
    });
  }
}
