package com.example.todo_hitsumabushi.controller;

import com.example.todo_hitsumabushi.controller.form.TaskForm;
import com.example.todo_hitsumabushi.repository.entity.Task;
import com.example.todo_hitsumabushi.service.AddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddController {
    @Autowired
    AddService addService;

    //タスク追加(新規タスクボタン押下後)
    @GetMapping("/new")
    public ModelAndView newTask(){
        ModelAndView mav = new ModelAndView("/new");
        TaskForm taskForm = new TaskForm();
        mav.addObject("taskForm", taskForm);
        return mav;
    }

    //タスク追加(追加ボタン押下後)
    @PostMapping("/add")
    public ModelAndView addTask(@ModelAttribute("taskForm") @Validated TaskForm taskform, BindingResult result){
        if(result.hasErrors()){
            ModelAndView mav = new ModelAndView("/new");
            mav.addObject("taskList", addService.findAlltask());
            return mav;
        }
        addService.saveTask(taskform);
        return new ModelAndView("redirect:/");
    }

    //タスク削除(削除ボタン押下後の処理)
    @DeleteMapping("/delete/{id}")
    public ModelAndView deleteTask(@PathVariable Integer id){
        addService.deleteTask(id);
        return new ModelAndView("redirect:/");
    }

    //ステータス変更処理(変更ボタン押下後の処理)
    @PutMapping("/change/{id}")
    public ModelAndView changeTask(@PathVariable Integer id, @ModelAttribute("taskForm") TaskForm taskForm){
        Task exsitingTask = addService.findTaskEntityById(id);
        exsitingTask.setStatus(taskForm.getStatus());
        addService.saveTaskStatus(exsitingTask);
        return new ModelAndView("redirect:/");
    }
}
