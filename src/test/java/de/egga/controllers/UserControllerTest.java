package de.egga.controllers;

import de.egga.Server;
import de.egga.model.User;
import de.egga.services.UserService;
import io.javalin.testtools.JavalinTest;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;

import static de.egga.controllers.JsonHelper.assertJson;
import static de.egga.model.UserFactory.anyUser;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserControllerTest {

  UserService userService = mock(UserService.class);
  UserController userController = new UserController(userService);
  Server server = new Server(userController);

  @Test
  public void givenValidId__whenGetUser__thenDataFromServiceIsReturned() {
    User user = anyUser();
    when(userService.getUser("1337")).thenReturn(user);

    JavalinTest.test(server.app(), (server, client) -> {
      ResponseBody body = client.get("/users/1337").body();

      assertJson(user, body);
    });
  }

  @Test
  public void givenValidUsername__whenCreateUser__thenServiceIsCalled() {
    JavalinTest.test(server.app(), (server, client) -> {
      client.post("/users?username=horst");
    });

    verify(userService).persist(any());
  }
}
