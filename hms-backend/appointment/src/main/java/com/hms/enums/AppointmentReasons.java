package com.hms.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum AppointmentReasons {
    GENERAL_CONSULTATION("General Consultation"),
    FEVER_AND_COLD("Fever & Cold"),
    HEADACHE("Headache"),
    BODY_PAIN("Body Pain"),
    STOMACH_PAIN("Stomach Pain"),
    SKIN_ALLERGY("Skin Allergy"),
    RASHES_AND_ITCHING("Rashes & Itching"),
    DIABETES_CHECKUP("Diabetes Checkup"),
    BLOOD_PRESSURE_CHECK("Blood Pressure Check"),
    HEART_PROBLEM("Heart Problem"),
    CHEST_PAIN("Chest Pain"),
    BREATHING_DIFFICULTY("Breathing Difficulty"),
    EYE_CHECKUP("Eye Checkup"),
    DENTAL_CHECKUP("Dental Checkup"),
    EAR_PAIN("Ear Pain"),
    THROAT_INFECTION("Throat Infection"),
    BACK_PAIN("Back Pain"),
    JOINT_PAIN("Joint Pain"),
    PREGNANCY_CONSULTATION("Pregnancy Consultation"),
    GYNECOLOGY_CONSULTATION("Gynecology Consultation"),
    CHILD_SPECIALIST_CONSULTATION("Child Specialist Consultation"),
    VACCINATION("Vaccination"),
    FOLLOW_UP_VISIT("Follow-up Visit"),
    LAB_TEST_REVIEW("Lab Test Review"),
    PRESCRIPTION_REFILL("Prescription Refill"),
    MENTAL_HEALTH_CONSULTATION("Mental Health Consultation"),
    STRESS_AND_ANXIETY("Stress & Anxiety"),
    PHYSIOTHERAPY("Physiotherapy"),
    NUTRITION_CONSULTATION("Nutrition Consultation"),
    WEIGHT_LOSS_CONSULTATION("Weight Loss Consultation"),
    ANNUAL_HEALTH_CHECKUP("Annual Health Checkup"),
    INJURY_TREATMENT("Injury Treatment"),
    MINOR_SURGERY_CONSULTATION("Minor Surgery Consultation"),
    POST_SURGERY_FOLLOW_UP("Post Surgery Follow-up"),
    URINE_INFECTION("Urine Infection"),
    HAIR_FALL_TREATMENT("Hair Fall Treatment"),
    ACNE_TREATMENT("Acne Treatment"),
    THYROID_CHECKUP("Thyroid Checkup"),
    OTHER("Other");

    private final String value;

    AppointmentReasons(String value) {
        this.value = value;
    }

    public static List<String> getValues(){
        return Arrays.stream(AppointmentReasons.values()).map(AppointmentReasons::getValue).toList();
    }


}
