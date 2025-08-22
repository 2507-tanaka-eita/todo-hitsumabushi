package com.example.todo_hitsumabushi.controller;

import com.example.todo_hitsumabushi.service.TopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.example.todo_hitsumabushi.controller.form.TaskForm;

import java.util.List;

@Controller
public class TopController {

    @Autowired
    TopService topService;

    @GetMapping("/")
    public ModelAndView top() {
        ModelAndView mav = new ModelAndView();

        // tasksテーブルから全レコードを取得
        List<TaskForm> taskList = topService.findAllTasks();

        mav.addObject("tasks", taskList);
        mav.addObject("taskForm", taskList);
        mav.setViewName("/top");
        return mav;
    }
}
