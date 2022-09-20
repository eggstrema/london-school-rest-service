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

  public User findById(Integer id) {

    for (User user : users) {
      if (user.getId().equals(id)) {
        return user;
      }
    }

    return null;
  }
}
