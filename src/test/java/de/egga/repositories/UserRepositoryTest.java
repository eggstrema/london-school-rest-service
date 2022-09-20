package de.egga.repositories;

import de.egga.model.User;
import org.junit.jupiter.api.Test;

import static de.egga.model.UserFactory.anyUser;
import static org.assertj.core.api.Assertions.assertThat;

class UserRepositoryTest {

  UserRepository repository = new UserRepository();

  @Test
  void givenAnyPersistedUser__whenFindById__theUserIsFound() {
    User anyUser = anyUser();
    repository.save(anyUser);

    User found = repository.findById(anyUser.getId());

    assertThat(found).isEqualTo(anyUser);
  }

  @Test
  void givenAnInvalidId__whenFindById__nullIsReturned() {
    int unknownId = 98765;

    User found = repository.findById(unknownId);

    assertThat(found).isNull();
  }
}
