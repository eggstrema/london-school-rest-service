package de.egga.services;

import de.egga.exceptions.UserNotFoundException;
import de.egga.model.User;
import de.egga.repositories.UserRepository;
import io.javalin.http.BadRequestResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.lang.Integer.parseInt;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  @Mock
  UserRepository repository;

  @InjectMocks
  UserService service;

  User anyUser = new User(1337, "Gerda", "Berlin", 12353);

  @Test
  void givenValidId__whenGetUserById__thenRepositoryIsCalled() {
    String id = "1337";
    when(repository.findById(1337)).thenReturn(anyUser);

    service.getUser(id);

    verify(repository).findById(parseInt(id));
  }

  @Test
  void givenInvalidId__whenGetUserById__thenExceptionIsThrown() {
    String invalidId = "abc";

    assertThatThrownBy(() -> {
      service.getUser(invalidId);

    }).isInstanceOf(BadRequestResponse.class);
  }

  @Test
  void givenValidId__whenGetUserById__thenDataFromRepositoryIsReturnedUnaltered() {
    when(repository.findById(1337)).thenReturn(anyUser);

    User user = service.getUser("1337");

    assertThat(user).isEqualTo(anyUser);
  }

  @Test
  void givenUnknownId__whenGetUserById__thenExceptionIsThrown() {
    when(repository.findById(any())).thenReturn(null);

    assertThatThrownBy(() -> {
      service.getUser("1337");

    }).isInstanceOf(UserNotFoundException.class);
  }

  @Test
  void givenValidUser__whenPersistUser__thenRepositoryIsCalled() {
    service.persist(anyUser);

    verify(repository).save(anyUser);
  }
}
