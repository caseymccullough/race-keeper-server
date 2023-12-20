package com.mccullough.controller;

import com.mccullough.model.Runner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.mccullough.dao.RunnerDao;

import java.util.List;

@RestController
@RequestMapping (path = "/runners")
public class RunnerController {

    private final RunnerDao runnerDao;

    public RunnerController(RunnerDao dao) {
        this.runnerDao = dao;
    }
    @RequestMapping(method = RequestMethod.GET)
    public List<Runner> list (){
        return runnerDao.getRunners();
    }





}
