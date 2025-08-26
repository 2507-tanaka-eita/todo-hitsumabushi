package com.example.todo_hitsumabushi.controller;

import com.example.todo_hitsumabushi.controller.form.TaskForm;
import com.example.todo_hitsumabushi.service.EditService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EditController {

    @Autowired
    EditService editService;

    @GetMapping("/edit")
    public String editNoId(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorCode", "不正なパラメータです");
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    //URLに送られてくる情報は最初は文字列のため、Integerにすると数字以外が来た時にエラーになる
    public ModelAndView editWithId(@PathVariable String id, RedirectAttributes redirectAttributes) {
        //idが数字以外ならエラー
        if (!id.matches("\\d+")) {
            redirectAttributes.addFlashAttribute("errorCode", "不正なパラメータです");
            return new ModelAndView("redirect:/");
        }
        Integer taskId = Integer.valueOf(id);
        //タスクIdが存在しない場合エラーに
        if (editService.editTask(taskId) == null) {
            redirectAttributes.addFlashAttribute("errorCode", "不正なパラメータです");
            return new ModelAndView("redirect:/");
        }
        TaskForm tf = editService.editTask(taskId);
        return new ModelAndView("edit").addObject("taskForm", tf);
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
