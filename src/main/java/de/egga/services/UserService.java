package de.egga.services;

import de.egga.exceptions.BadRequestException;
import de.egga.exceptions.UserNotFoundException;
import de.egga.model.User;
import de.egga.repositories.UserRepository;

public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void persist(User user) {
    userRepository.save(user);
  }

  public User getUser(String inputParam) {
    int id = parseUserId(inputParam);

    User user = userRepository.findById(id);

    if (user == null) {
      throw new UserNotFoundException();
    }
    return user;
  }

  private int parseUserId(String input) {
    try {
      return Integer.parseInt(input);
    } catch (NumberFormatException exception) {
      throw new BadRequestException();
    }
  }
}
