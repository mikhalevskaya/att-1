package edu.csf.oop.java.durak.baseDescriptions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Denominations {
    public static final String[] ranks = {
            "6",
            "7",
            "8",
            "9",
            "10",
            "Валет",
            "Королева",
            "Король",
            "Туз"
    };
    public static final Map<String, Integer> values;//сопоставление строковых значений номиналов целочисленным
    static {
        Map<String, Integer> valuesMap = new HashMap<>();
        valuesMap.put("6", 6);
        valuesMap.put("7", 7);
        valuesMap.put("8", 8);
        valuesMap.put("9", 9);
        valuesMap.put("10", 10);
        valuesMap.put("Валет", 11);
        valuesMap.put("Королева", 12);
        valuesMap.put("Король", 13);
        valuesMap.put("Туз", 14);
        values = Collections.unmodifiableMap(valuesMap);//перечень карт не модифицируется
    }
}
