package com.example.demo.Repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Task;

public interface TaskRepo extends JpaRepository<Task, Integer> {

}
