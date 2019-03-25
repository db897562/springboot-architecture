package com.architecture.controller;


import com.architecture.entity.CharacterSets;
import com.architecture.service.CharacterSetsService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Wrapper;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dirk
 * @since 2019-03-24
 */
@Slf4j
@RestController
@RequestMapping("/characterSets2")
@Api("swaggerDemoController相关的api")
public class CharacterSetsController2 {
    @Autowired
    private CharacterSetsService characterSetsService;

    @ApiOperation(value = "根据id查询学生信息", notes = "查询数据库中某个的学生信息")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<CharacterSets> getStudent() {
        log.info("开始查询某个学生信息");
        EntityWrapper<CharacterSets> ew = new EntityWrapper<CharacterSets>();
        return characterSetsService.selectList(ew);
    }
}

