package com.example.todo_hitsumabushi.controller;

import com.example.todo_hitsumabushi.controller.form.TaskForm;
import com.example.todo_hitsumabushi.repository.entity.Task;
import com.example.todo_hitsumabushi.service.AddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Text;

import java.io.Serial;
import java.time.LocalDate;

@Controller
public class AddController {
    @Autowired
    AddService addService;

    //タスク追加
    @PostMapping("/add")
    public ModelAndView addTask(@ModelAttribute("taskForm") @Validated TaskForm taskform, BindingResult result){
        if(result.hasErrors()){
            ModelAndView mav = new ModelAndView("/top");
            mav.addObject("taskList", addService.findAllcontent());
            return mav;
        }
        addService.saveTask(taskform);
        return new ModelAndView("redirect:/");
    }

    //タスク削除
    @DeleteMapping("/delete/{id}")
    public ModelAndView deleteTask(@PathVariable Integer id){
        addService.deleteTask(id);
        return new ModelAndView("redirect:/");
    }
}
