package de.egga;

import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserController {

  List<String> users = new ArrayList<>(Arrays.asList("User1", "User2", "User3"));

  public void create(Context context) {
    String username = context.queryParam("username");
    if (username == null || username.length() < 5) {
      throw new BadRequestResponse();
    } else {
      users.add(username);
      context.status(201);
    }
  }

  public void getAll(Context ctx) {
    ctx.json(users);
  }

}
