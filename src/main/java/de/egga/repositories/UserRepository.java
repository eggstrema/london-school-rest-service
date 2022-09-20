package de.egga.repositories;

import de.egga.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

  private final ArrayList<User> users = new ArrayList<>();

  public List<User> getAllUsers() {
    return users;
  }

  public void save(User user) {
    users.add(user);
  }
}
