package de.egga.services;

import de.egga.model.User;
import de.egga.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
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
  void whenGetAllUsers_thenRepositoryIsCalled() {
    service.getAllUsers();

    verify(repository).getAllUsers();
  }

  @Test
  void whenGetUserById_thenRepositoryIsCalled() {
    String id = "1337";

    service.getUser(id);

    verify(repository).findById(parseInt(id));
  }

  @Test
  void whenGetUserById_thenDataFromRepositoryIsReturnedUnaltered() {
    when(repository.findById(1337)).thenReturn(anyUser);

    User users = service.getUser("1337");

    assertThat(users).isEqualTo(anyUser);
  }

  @Test
  void whenGetAllUsers_thenDataFromRepositoryIsReturnedUnaltered() {
    when(repository.getAllUsers()).thenReturn(asList(anyUser));

    List<User> users = service.getAllUsers();

    assertThat(users).containsExactly(anyUser);
  }

  @Test
  void whenSaveUser_thenRepositoryIsCalled() {
    service.persist(anyUser);

    verify(repository).save(anyUser);
  }
}
