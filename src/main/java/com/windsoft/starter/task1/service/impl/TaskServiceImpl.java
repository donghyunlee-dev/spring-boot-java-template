package com.windsoft.starter.task1.service.impl;

import com.windsoft.starter.task1.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {

    @Override
    public String getName(String id) {
        return "";
    }
}
