package de.egga.controllers;

import io.javalin.plugin.json.JavalinJackson;
import okhttp3.ResponseBody;
import org.json.JSONException;

import java.io.IOException;

import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.skyscreamer.jsonassert.JSONCompareMode.STRICT;

public class JsonHelper {

  public static void assertJson(Object expected, ResponseBody response) {
    try {
      String actual = response.string();
      String expectedString = new JavalinJackson().toJsonString(expected);
      assertEquals(expectedString, actual, STRICT);

    } catch (IOException | JSONException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }
}
