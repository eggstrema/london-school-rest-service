package de.egga.model;

import org.jetbrains.annotations.NotNull;

public class UserFactory {

  @NotNull
  public static User anyUser() {
    return new User(1337, "Trude", "Berlin", 12353);
  }
}
