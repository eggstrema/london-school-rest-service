package de.egga.model;

public class User {

  private final Integer id;
  private final String name;
  private final String city;
  private final Integer zipCode;

  public User(Integer id, String name, String city, Integer zipCode) {
    this.id = id;
    this.name = name;
    this.city = city;
    this.zipCode = zipCode;
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getCity() {
    return city;
  }

  public Integer getZipCode() {
    return zipCode;
  }
}
