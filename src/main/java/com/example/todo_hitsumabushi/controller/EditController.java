package com.example.todo_hitsumabushi.controller;

import com.example.todo_hitsumabushi.service.EditService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.example.todo_hitsumabushi.controller.form.TaskForm;

@Controller
public class EditController {

    @Autowired
    EditService editService;

    // タスク編集画面の表示
    @GetMapping("/edit/{id}")
    // @PathVariable：HTTPリクエストのURLパスの値を引数にバインド
    public ModelAndView editContent(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView();

        // 編集対象の投稿を取得してformに格納
        TaskForm task = editService.editTask(id);

        mav.setViewName("/edit");
        mav.addObject("taskForm", task);
        return mav;
    }

    // タスクの編集処理
    @PutMapping("/update/{id}")
    public ModelAndView updateContent(@PathVariable Integer id, @ModelAttribute("taskForm") @Valid TaskForm task, BindingResult bindingResult){
        // 投稿編集に関するバリデーション。
        if (bindingResult.hasErrors()) {
            // バリデーションエラーがある場合、フォームを再表示。
            ModelAndView mav = new ModelAndView("edit");
            // 入力値を保持して表示。
            mav.addObject("taskForm", task);
            return mav;
        }

        task.setId(id);
        editService.saveTask(task);
        return new ModelAndView("redirect:/");
    }
}
