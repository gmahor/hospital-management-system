package com.hms.profile.utils;

import com.hms.profile.enums.BloodGroups;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class BloodGroupConverter implements AttributeConverter<BloodGroups, String> {

    @Override
    public String convertToDatabaseColumn(BloodGroups bloodGroup) {
        return bloodGroup != null ? bloodGroup.getValue() : null;
    }

    @Override
    public BloodGroups convertToEntityAttribute(String dbValue) {
        return dbValue != null ? BloodGroups.fromValue(dbValue) : null;
    }
}