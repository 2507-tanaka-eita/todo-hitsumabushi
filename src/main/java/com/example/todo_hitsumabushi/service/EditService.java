package com.example.todo_hitsumabushi.service;

import com.example.todo_hitsumabushi.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo_hitsumabushi.controller.form.TaskForm;
import com.example.todo_hitsumabushi.repository.entity.Task;

import java.util.ArrayList;
import java.util.List;

@Service
public class EditService {

    @Autowired
    TaskRepository taskRepository;

    // タスク編集画面の表示
    public TaskForm editTask(Integer id) {
        List<Task> results = new ArrayList<>();
        results.add((Task) taskRepository.findById(id).orElse(null));
        List<TaskForm> reports = setTaskForm(results);
        return reports.get(0);
    }

    // 取得したレコードをEntity→Formへ詰め替え
    private List<TaskForm> setTaskForm(List<Task> results) {
        List<TaskForm> tasks = new ArrayList<>();

        for (int i = 0; i < results.size(); i++) {
            TaskForm task = new TaskForm();
            Task result = results.get(i);

            // データセット。
            task.setId(result.getId());
            task.setContent(result.getContent());
            task.setStatus(result.getStatus());
            task.setLimitDate(result.getLimitDate());
            task.setCreatedDate(result.getCreatedDate());
            task.setUpdatedDate(result.getUpdatedDate());
            tasks.add(task);
        }
        return tasks;
    }
}
