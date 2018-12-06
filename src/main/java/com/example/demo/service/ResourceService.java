package com.example.demo.service;

import com.example.demo.dao.SysResourceMapper;
import com.example.demo.entity.SysResource;
import com.example.demo.entity.SysResourceExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ResourceService {

    @Autowired
    private SysResourceMapper resourceMapper;

    public List<SysResource> getUserResources(Map<String, Object> map) {
        return resourceMapper.getUserResources(map);
    }

    public List<SysResource> findAll() {
        return resourceMapper.selectByExample(new SysResourceExample());
    }
}
