package com.mccullough.controller;

import com.mccullough.model.Runner;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Runner get (@PathVariable int id){
        return runnerDao.getRunnerById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Runner add (@RequestBody Runner runner) {
        return runnerDao.createRunner(runner);
    }







}
