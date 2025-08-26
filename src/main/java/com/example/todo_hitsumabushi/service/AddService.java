package com.example.todo_hitsumabushi.service;

import com.example.todo_hitsumabushi.controller.form.TaskForm;
import com.example.todo_hitsumabushi.repository.TaskRepository;
import com.example.todo_hitsumabushi.repository.entity.Task;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddService {
    @Autowired
    TaskRepository taskRepository;

    //タスク追加
    @Transactional
    public void saveTask(TaskForm reqTask){
        Task entity = toTaskEntity(reqTask);
        Task save = taskRepository.save(entity);
    }

    //Entityへ詰める
    private Task toTaskEntity(TaskForm taskForm){
        Task task = new Task();
        task.setContent(taskForm.getContent());
        task.setLimitDate(taskForm.getLimitDate().atStartOfDay());
        task.setStatus(1);
        return task;
    }

    //Entityの情報をFormへ詰め替える
    private List<TaskForm> setTaskForm(List<Task> results){
        List<TaskForm> tasks = new ArrayList<>();
        //DBから取り出したCommentのリストを一件ずつ取り出して処理する
        for (Task result : results) {
            //ここでcommentを使うのは、全投稿を表すものとしてcommentsを使い、一件ずつ読み取る処理の中ではcommentを使いたいから
            TaskForm task = new TaskForm();
            //ここでEntityの全情報をFormに詰め替える
            task.setId(result.getId());
            task.setContent(result.getContent());
            task.setLimitDate(result.getLimitDate().toLocalDate());
            task.setCreatedDate(result.getCreatedDate());
            task.setUpdatedDate(result.getUpdatedDate());
            tasks.add(task);
        }
        return tasks;
    }

    //データの削除
    @Transactional
    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
    }

    public Task findTaskEntityById(Integer id) {
        return taskRepository.findById(id).orElse(null);
    }

    public void saveTaskStatus(Task task) {
        task.setStatus(task.getStatus());
        taskRepository.save(task);
    }
}
