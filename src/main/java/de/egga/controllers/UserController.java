package de.egga.controllers;

import de.egga.model.User;
import de.egga.services.UserService;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;

public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  public void create(Context context) {
    String username = context.queryParam("username");
    if (username == null || username.length() < 5) {
      throw new BadRequestResponse();
    } else {
      userService.persist(new User(1337, username, "Berlin", 12353));
      context.status(201);
    }
  }

  public void getAll(Context context) {
    context.json(userService.getAllUsers());
  }

  public void getUser(Context context) {
    String id = context.pathParam("id");
    User user = userService.getUser(id);
    context.json(user);
  }
}
