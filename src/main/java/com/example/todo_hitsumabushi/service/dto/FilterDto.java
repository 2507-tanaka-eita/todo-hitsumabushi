package com.example.todo_hitsumabushi.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

// FilterFormで受け取った日付情報をLocalDate→LocalDateTime型に変換するのに使用。
@Getter
@Setter
public class FilterDto {
    private String searchword;
    private Integer status;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
}
