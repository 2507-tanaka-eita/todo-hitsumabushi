package com.example.todo_hitsumabushi.service;

import com.example.todo_hitsumabushi.controller.form.TaskForm;
import com.example.todo_hitsumabushi.mapper.TaskMapper;
import com.example.todo_hitsumabushi.repository.TaskRepository;
import com.example.todo_hitsumabushi.repository.entity.Task;
import com.example.todo_hitsumabushi.service.dto.FilterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopService {

//    @Autowired
//    TaskRepository taskRepository;
    @Autowired
    TaskMapper taskMapper;

    // tasksテーブルから全レコードを取得
//    public List<TaskForm> findAllTasks(FilterDto filterDto) {
//        LocalDateTime start = filterDto.getStartDateTime();
//        LocalDateTime end = filterDto.getEndDateTime();
//        Integer status = filterDto.getStatus();
//        String searchword = filterDto.getSearchword();
//        Pageable pageable = PageRequest.of(0, 1000);
//
//        List<Task> results = taskRepository.findFilterTasks(start, end, status, searchword, pageable);
//        List<TaskForm> taskList = setTasksForm(results);
//        return taskList;
//    }

    public List<TaskForm> findAllTasks(FilterDto filterDto) {
        List<Task> results = taskMapper.selectFilterTasks(filterDto);
        return setTasksForm(results);
    }
    // DBから取得したデータをFormに設定
    private List<TaskForm> setTasksForm(List<Task> results) {
        List<TaskForm> tasks = new ArrayList<>();

        for (int i = 0; i < results.size(); i++) {
            TaskForm task = new TaskForm();
            Task result = results.get(i);
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
