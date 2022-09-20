package de.egga.repositories;

import de.egga.services.model.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UserRepositoryTest {

  @Test
  void givenAnEmptyRepository__whenGetAllUsers__anEmptyListIsReturned() {
    UserRepository repository = new UserRepository();
    List<User> allUsers = repository.getAllUsers();
    assertThat(allUsers).isEmpty();
  }

  @Test
  void givenAnEmptyRepository__whenAddingAUser__anEmptyListIsReturned() {
    UserRepository repository = new UserRepository();
    User anyUser = new User("Trude");
    repository.save(anyUser);
    List<User> allUsers = repository.getAllUsers();
    assertThat(allUsers).containsExactly(anyUser);
  }
}
