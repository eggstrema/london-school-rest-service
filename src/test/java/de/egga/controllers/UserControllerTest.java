package de.egga.controllers;

import de.egga.Server;
import de.egga.model.User;
import de.egga.services.UserService;
import io.javalin.plugin.json.JavalinJackson;
import io.javalin.testtools.JavalinTest;
import org.json.JSONException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.skyscreamer.jsonassert.JSONCompareMode.STRICT;

class UserControllerTest {

  UserService userService = mock(UserService.class);
  UserController userController = new UserController(userService);
  Server server = new Server(userController);

  @Test
  public void GET_to_fetch_users_returns_list_from_service() {
    List<User> users = asList(new User(1337, "Paula", "Berlin", 12353), new User(1337, "Karla", "Berlin", 12353));
    when(userService.getAllUsers()).thenReturn(users);

    JavalinTest.test(server.app(), (server, client) -> {
      String actual = client.get("/users").body().string();

      assertJson(users, actual);
    });
  }

  @Test
  public void GET_to_fetch_user_data_returns_data_from_service() {
    User user = new User(1337, "Laura", "Berlin", 12353);
    String id = String.valueOf(user.getId());
    when(userService.getUser(id)).thenReturn(user);

    JavalinTest.test(server.app(), (server, client) -> {
      String actual = client.get("/users/1337").body().string();

      assertJson(user, actual);
    });
  }

  @Test
  public void POST_to_create_user_is_passed_on_to_service() {
    JavalinTest.test(server.app(), (server, client) -> {
      client.post("/users?username=horst");
    });

    verify(userService).persist(any());
  }

  private void assertJson(Object expected, String actual) {
    String expectedString = new JavalinJackson().toJsonString(expected);
    try {
      assertEquals(expectedString, actual, STRICT);
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }
}
