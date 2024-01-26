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
    public List<Runner> list (@RequestParam (value = "name_like", defaultValue = "") String name) {

        System.out.println("call to list()");
        if (!name.isEmpty()){
            System.out.println("name parameter: " + name);
            return runnerDao.getRunnersByName(name, true);
        }
        else {
            return runnerDao.getRunners();
        }

    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Runner get (@PathVariable int id){
        return runnerDao.getRunnerById(id);
    }

    @RequestMapping(path = "/city/{city_name}", method = RequestMethod.GET)
    public List<Runner> listByCity(@PathVariable String city_name) { return runnerDao.getRunnersByCity(city_name);}

    @RequestMapping(method = RequestMethod.POST)
    public Runner add (@RequestBody Runner runner) {
        return runnerDao.createRunner(runner);
    }







}
