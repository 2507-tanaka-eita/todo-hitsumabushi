package com.example.todo_hitsumabushi.mapper;

import com.example.todo_hitsumabushi.repository.entity.Task;
import com.example.todo_hitsumabushi.service.dto.FilterDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TaskMapper {
    List<Task> selectFilterTasks(FilterDto filterDto);
    Task selectById(Integer id);
    int insertTask(Task task);
    int updateTask(Task task);
    int deleteById(Integer id);
    int insertTaskStatus(Task task);
    int updateTaskStatus(Task task);
}
