package com.example.todo_hitsumabushi.controller;

import com.example.todo_hitsumabushi.controller.form.FilterForm;
import com.example.todo_hitsumabushi.service.dto.FilterDto;
import com.example.todo_hitsumabushi.service.TopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import com.example.todo_hitsumabushi.controller.form.TaskForm;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class TopController {

    @Autowired
    TopService topService;

    @GetMapping("/")
    public ModelAndView top(@ModelAttribute("filterForm") FilterForm filterForm) {
        ModelAndView mav = new ModelAndView();

        // 絞り込み機能(日付、ステータス、タスク内容)
        LocalDate startDate = filterForm.getStartDate();
        LocalDate endDate = filterForm.getEndDate();
        FilterDto filterDto = new FilterDto();

        if (startDate != null) {
            // startDateに時刻の情報を追加　＊atStartOfDay()：指定日の0時00分のLocalDateTimeを返す。
            filterDto.setStartDateTime(startDate.atStartOfDay());
        } else {
            // 初期値　＊2020-01-01 00:00:00
            filterDto.setStartDateTime(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        }

        if (endDate != null) {
            // endDateに時刻の情報を追加。
            filterDto.setEndDateTime(endDate.atTime(23, 59, 59));
        } else {
            // 初期値　＊2100-12-31 23:59:59
            filterDto.setEndDateTime(LocalDateTime.of(2100, 12, 31, 23, 59, 59));
        }

        filterDto.setSearchword(filterForm.getSearchword());
        filterDto.setStatus(filterForm.getStatus());

        // tasksテーブルからレコードを取得
        List<TaskForm> taskList = topService.findAllTasks(filterDto);

        mav.addObject("tasks", taskList);
        mav.setViewName("/top");
        return mav;
    }
}
