package com.example.todo_hitsumabushi.controller.form;

import com.example.todo_hitsumabushi.validation.CharacterLimit;
import com.example.todo_hitsumabushi.validation.NotOnlyWhitespace;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class TaskForm {
    private Integer id;

    @NotOnlyWhitespace(message = "タスクを入力してください")
    @CharacterLimit(message = "タスクは140文字以内で入力してください")
    private String content;

    private Integer status;

    @NotNull(message = "期限を設定してください")
    @FutureOrPresent(message = "無効な日付です")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate limitDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime updatedDate;
}
