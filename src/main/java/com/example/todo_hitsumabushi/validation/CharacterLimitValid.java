package com.example.todo_hitsumabushi.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CharacterLimitValid implements ConstraintValidator<CharacterLimit, String> {

    // 改行含め140文字以内をチェックするバリデーション
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String characterCountCheck = value.replaceAll("\\r?\\n", "");
        return characterCountCheck.length() <= 140;
    }
}
