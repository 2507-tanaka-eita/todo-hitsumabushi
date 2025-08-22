package com.example.todo_hitsumabushi.service;

import com.example.todo_hitsumabushi.controller.form.TaskForm;
import com.example.todo_hitsumabushi.repository.AddRepository;
import com.example.todo_hitsumabushi.repository.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AddService {
    @Autowired
    AddRepository addRepository;

    //タスク追加
    public void saveTask(TaskForm reqTask){
        Task saveTask = setTaskEntity(reqTask);
        addRepository.save(saveTask);
    }

    private Task setTaskEntity(TaskForm form){
        Task task = new Task();
        task.setContent(form.getContent());
        task.setLimitDate(form.getLimitDate());
        task.setStatus(1);
        task.setCreatedDate(LocalDateTime.now());
        task.setUpdatedDate(LocalDateTime.now());
        return task;
    }

    public List<TaskForm> findAllcontent() {
        return addRepository.findAllByOrderByUpdatedDateDesc();
    }

    public void deleteTask(Integer id) {
        addRepository.deleteById(id);
    }
}
