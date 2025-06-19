package com.trungtmnguyen.vnd2text;

import java.util.HashMap;
import java.util.Map;

public class Constants {

  private Constants() {}

  public static Map<String, String> NUMBER_WORDS= new HashMap<String, String>() {
    {
      put("0", "không");
      put("1", "một");
      put("2", "hai");
      put("3", "ba");
      put("4", "bốn");
      put("5", "năm");
      put("6", "sáu");
      put("7", "bảy");
      put("8", "tám");
      put("9", "chín");
    }
  };

  public static Map<String, String> UNIT_WORDS = new HashMap<String, String>() {
    {
      put("1", "");
      put("2", "nghìn");
      put("3", "triệu");
      put("4", "tỷ");
    }
  };

}
