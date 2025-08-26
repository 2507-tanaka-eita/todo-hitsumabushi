package com.example.todo_hitsumabushi.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 改行含め140文字以内をチェックするバリデーション
@Target({ ElementType.FIELD })
@Constraint(validatedBy = CharacterLimitValid.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface CharacterLimit {
    String message() default "タスクは140文字以内で入力してください";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}