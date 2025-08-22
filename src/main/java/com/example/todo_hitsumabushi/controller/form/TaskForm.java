package com.example.todo_hitsumabushi.controller.form;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskForm {
    private Integer id;
    @NotBlank(message = "タスクを入力してください")
    @Size(max = 140, message = "タスクは140文字以内で入力してください")
    private String content;
    private Integer status;
    @NotNull(message = "期限を設定してください")
    @FutureOrPresent(message = "無効な日付です")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime limitDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime updatedDate;

}
