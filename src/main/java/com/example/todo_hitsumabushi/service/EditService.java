package com.example.todo_hitsumabushi.service;

import com.example.todo_hitsumabushi.controller.form.TaskForm;
import com.example.todo_hitsumabushi.mapper.TaskMapper;
import com.example.todo_hitsumabushi.repository.TaskRepository;
import com.example.todo_hitsumabushi.repository.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EditService {

//    @Autowired
//    TaskRepository taskRepository;
    @Autowired
    TaskMapper taskMapper;

    // タスク編集画面の表示
    public TaskForm editTask(Integer id) {
//        results.add((Task) taskRepository.findById(id).orElse(null));
        Task entity = taskMapper.selectById(id); // Optionalは不要
        if (entity == null) {
            return null;
        }
        List<Task> results = new ArrayList<>();
        results.add(entity);
        List<TaskForm> reports = setTaskForm(results);
        return reports.get(0);
    }

    // 編集したタスクの更新
    public void saveTask(TaskForm reqTask) {
        Task saveReport = setReportEntity(reqTask);
//        taskRepository.save(saveReport);
        if (saveReport.getId() == null) {
            // 新規
            taskMapper.insertTask(saveReport);
        } else {
            // 更新
            taskMapper.updateTask(saveReport);
        }
    }

    // Form→Entityの詰め替え作業
    private Task setReportEntity(TaskForm reqTask) {
        Task task = new Task();

        // データセット。
        task.setId(reqTask.getId());
        task.setContent(reqTask.getContent());
        task.setStatus(reqTask.getStatus());
        task.setLimitDate(reqTask.getLimitDate().atStartOfDay());
        task.setCreatedDate(reqTask.getCreatedDate());
        task.setUpdatedDate(reqTask.getUpdatedDate());
        return task;
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
            task.setLimitDate(result.getLimitDate().toLocalDate());
            task.setCreatedDate(result.getCreatedDate());
            task.setUpdatedDate(result.getUpdatedDate());
            tasks.add(task);
        }
        return tasks;
    }
}
