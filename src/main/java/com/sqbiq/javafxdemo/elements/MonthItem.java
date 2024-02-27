package com.sqbiq.javafxdemo.elements;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

public class MonthItem {
    final private Month value;
    final private String label;

    public MonthItem(Month month, Locale locale) {
        value = month;
        label = month.getDisplayName(TextStyle.FULL, locale);
    }

    public Month getValue() { return value; }

    public String getLabel() { return label; }

    @Override
    public String toString() { return label; }
}
