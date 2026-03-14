package com.hms.profile.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum BloodGroups {
    A_POSITIVE("A+"),
    A_NEGATIVE("A-"),
    B_POSITIVE("B+"),
    B_NEGATIVE("B-"),
    AB_POSITIVE("AB+"),
    AB_NEGATIVE("AB-"),
    O_POSITIVE("O+"),
    O_NEGATIVE("O-");

    private final String value;

    BloodGroups(String value) {
        this.value = value;
    }

    public static BloodGroups fromValue(String value) {
        for (BloodGroups bg : values()) {
            if (bg.value.equals(value)) {
                return bg;
            }
        }
        throw new IllegalArgumentException("Unknown blood group: " + value);
    }

    public static List<String> getValues(){
         return Arrays.stream(BloodGroups.values()).map(BloodGroups::getValue).toList();
    }

}
