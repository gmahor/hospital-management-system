package com.hms.profile.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum Specializations {

    GENERAL_PHYSICIAN("General Physician"),
    CARDIOLOGIST("Cardiologist"),
    DERMATOLOGIST("Dermatologist"),
    NEUROLOGIST("Neurologist"),
    ORTHOPEDIC("Orthopedic"),
    PEDIATRICIAN("Pediatrician"),
    PSYCHIATRIST("Psychiatrist"),
    GYNECOLOGIST("Gynecologist"),
    ONCOLOGIST("Oncologist"),
    OPHTHALMOLOGIST("Ophthalmologist"),
    ENT_SPECIALIST("Ent-specialist"),
    ENDOCRINOLOGIST("Endo-crinologist"),
    GASTROENTEROLOGIST("Gastroenterologist"),
    UROLOGIST("Urologist"),
    NEPHROLOGIST("Nephrologist"),
    PULMONOLOGIST("Pulmonologist"),
    RADIOLOGIST("Radiologist"),
    ANESTHESIOLOGIST("Anesthesiologist");

    private final String value;

    public static List<String> getSpecializations() {
        return Arrays.stream(Specializations.values()).map(Specializations::getValue).toList();
    }


}
