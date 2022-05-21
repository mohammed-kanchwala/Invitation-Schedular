package com.washmen.util;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Unit {
    K('K', "Kilo Meters"),
    M('M', "Meters"),
    NM('N', "Nautical Miles");

    public final char unitChar;
    public final String unitName;
}
