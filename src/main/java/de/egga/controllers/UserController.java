package de.egga.controllers;

import de.egga.services.UserService;
import de.egga.services.model.User;
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
      userService.persist(new User(username));
      context.status(201);
    }
  }

  public void getAll(Context context) {
    context.json(userService.getAllUsers());
  }
}
