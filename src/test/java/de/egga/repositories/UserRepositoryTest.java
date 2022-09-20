package de.egga.repositories;

import de.egga.model.User;
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
  void givenAnEmptyRepository__whenAddingAUser__theUserIsListed() {
    UserRepository repository = new UserRepository();
    User anyUser = new User(1337, "Trude", "Berlin", 12353);
    repository.save(anyUser);
    List<User> allUsers = repository.getAllUsers();
    assertThat(allUsers).containsExactly(anyUser);
  }

  @Test
  void givenAnyUser__whenFindById__theUserIsFound() {
    UserRepository repository = new UserRepository();
    User anyUser = new User(1337, "Trude", "Berlin", 12353);
    repository.save(anyUser);
    User found = repository.findById(anyUser.getId());
    assertThat(found).isEqualTo(anyUser);
  }
}
