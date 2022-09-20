package de.egga.services;

import de.egga.model.User;
import de.egga.repositories.UserRepository;

import java.util.List;

public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> getAllUsers() {
    return userRepository.getAllUsers();
  }

  public void persist(User user) {
    userRepository.save(user);
  }
}
