package de.egga.services;

import de.egga.repositories.UserRepository;
import de.egga.services.model.User;

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
