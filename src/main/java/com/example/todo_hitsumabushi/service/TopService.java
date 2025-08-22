package com.example.todo_hitsumabushi.service;

import com.example.todo_hitsumabushi.controller.Form.TasksForm;
import com.example.todo_hitsumabushi.repository.Entity.Tasks;
import com.example.todo_hitsumabushi.repository.TopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopService {

    @Autowired
    TopRepository topRepository;

    // tasksテーブルから全レコードを取得
    public List<TasksForm> findAllTasks() {
        List<Tasks> results = topRepository.findAll();
        List<TasksForm> taskList = setTasksForm(results);
        return taskList;
    }
    /*
     * DBから取得したデータをFormに設定
     */
    private List<TasksForm> setTasksForm(List<Tasks> results) {
        List<TasksForm> tasks = new ArrayList<>();

        for (int i = 0; i < results.size(); i++) {
            TasksForm task = new TasksForm();
            Tasks result = results.get(i);
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
