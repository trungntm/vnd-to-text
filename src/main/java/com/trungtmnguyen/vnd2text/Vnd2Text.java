package com.trungtmnguyen.vnd2text;

import static com.trungtmnguyen.vnd2text.Constants.NUMBER_WORDS;
import static com.trungtmnguyen.vnd2text.Constants.UNIT_WORDS;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Vnd2Text {

  private boolean canSpeak(int index, List<String> currency) {
    return Long.parseLong(currency.get(index)) != 0;
  }

  public String convert(String currency) {
    if (currency == null || currency.isEmpty() || Long.parseLong(currency) == 0) {
      return "Không đồng";
    }

    String rs = "";
    List<String> moneyList = toList(currency);
    List<String> text = new ArrayList<>();

    for (int i = 0; i < moneyList.size(); i++) {
      if (!canSpeak(i, moneyList)) {
        continue;
      }
      
      if (moneyList.get(i).length() % 3 == 1) {
        text.add(NUMBER_WORDS.get(String.valueOf(Long.parseLong(moneyList.get(i)))) + " " + UNIT_WORDS.get(String.valueOf(i + 1)));
      }

      if (moneyList.get(i).length() % 3 ==2) {
        String temp = "";
        String first = moneyList.get(i).substring(0, 1);
        if (first.equalsIgnoreCase("1")) {
          temp += "mười ";
        } else {
          temp += (NUMBER_WORDS.get(first) + " mươi ");
        }

        String second = moneyList.get(i).substring(1);
        if (!second.equalsIgnoreCase("0")) {
          if (second.equalsIgnoreCase("1") && !first.equals("1")) {
            temp += "mốt ";
          } else {
            temp += NUMBER_WORDS.get(second) + " ";
          }
        }

        temp += UNIT_WORDS.get(String.valueOf(i + 1));

        text.add(temp);
      }

      if (moneyList.get(i).length() % 3 == 0) {
        String temp = "";
        String first = moneyList.get(i).substring(0, 1);
        String second = moneyList.get(i).substring(1, 2);
        String third = moneyList.get(i).substring(2, 3);

        if (!first.equalsIgnoreCase("0")) {
          temp += (NUMBER_WORDS.get(first) + " trăm ");
        } else {
          if (!second.equalsIgnoreCase("0") || !third.equalsIgnoreCase("0")) {
            temp += "không trăm ";

          }
        }
        if (!second.equalsIgnoreCase("0")) {
          if (second.equalsIgnoreCase("1")) {
            temp += "mười ";
          } else {
            temp += NUMBER_WORDS.get(second) + " mươi ";
          }
        } else {
          if (!third.equalsIgnoreCase("0")) {
            temp += "lẻ ";
          }
        }

        if (!third.equalsIgnoreCase("0")) {
          if (third.equalsIgnoreCase("1") && !second.equals("0") && !second.equals("1")) {
            temp += "mốt ";
          } else {
            if (third.equalsIgnoreCase("5") && !second.equalsIgnoreCase("0")) {
              temp += "lăm ";
            } else {
              temp += NUMBER_WORDS.get(third) + " ";
            }
          }
        }

        temp += UNIT_WORDS.get(String.valueOf(i + 1));
        text.add(temp);
      }

    }

    Collections.reverse(text);
    rs = String.join(" ", text);

    if (!rs.endsWith("đồng")) {
      rs = rs + " đồng";
    }

    String firstWordUpperCase = rs.substring(0,1).toUpperCase();
    rs = firstWordUpperCase + rs.substring(1);

    return rs;
  }



  private List<String> toList(String money) {
    String temp = money;
    int length = temp.length();
    List<String> array = new ArrayList<>();
    int startIndex = money.length();
    boolean flag = true;

    while (flag) {
      String unit = temp.substring(startIndex - 3, startIndex);
      array.add(unit);
      startIndex -= 3;
      length = length - 3;

      if (length < 3) {
        flag = false;
        if (!temp.substring(0, startIndex).isEmpty()) {
          array.add(temp.substring(0, startIndex));
        }
      }
    }

    return array;
  }

  public String format(double amount, boolean withExtension) {
    Locale locale = new Locale("vi", "VN");
    NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
    if (withExtension) {
      return numberFormat.format(amount);
    }

    return numberFormat.format(amount).replace(" đ", "");
  }


}
