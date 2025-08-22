package com.example.todo_hitsumabushi.controller.Form;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TasksForm {
    private int id;
    private String content;
    private short status;
    private Date limitDate;
    private Date createdDate;
    private Date updatedDate;
}
