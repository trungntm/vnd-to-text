package com.trungtmnguyen.vnd2text;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Vnd2TextTest {

  @Test
  void testConvert() {
    Vnd2Text vnd2Text = new Vnd2Text();
    assertEquals("Một triệu đồng", vnd2Text.convert("1000000"));
    assertEquals("Một tỷ đồng", vnd2Text.convert("1000000000"));
//    assert "một tỷ một triệu đồng".equals(vnd2Text.convert("1001000000"));
  }
}
