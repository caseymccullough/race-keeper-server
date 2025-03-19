package com.mccullough.controller;

import com.mccullough.exception.DaoException;
import com.mccullough.model.Runner;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.mccullough.dao.RunnerDao;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
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

    @RequestMapping(path = "/state/{state_code}", method = RequestMethod.GET)
    public List<Runner> listByState(@PathVariable String state_code) {
        return runnerDao.getRunnersByState(state_code);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Runner add (@RequestBody Runner runner) {
        return runnerDao.createRunner(runner);
    }

    /* PUT is for update/ replace */
    /*
        @Valid decoration in Spring
        checks constraints established for Runner class
        if validation fails responds with HTTP 400 Bad Request
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public Runner updateRunner (@Valid @RequestBody Runner changedRunner, @PathVariable int id){
        // the id on the URL takes precedence over the one in the payload, if any
        changedRunner.setId(id);

        try{
            return runnerDao.updateRunner(changedRunner);
        } catch(DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO Error - " + e.getMessage());
        }
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeRunner(@PathVariable int id){
        try {
            runnerDao.deleteRunner(id);
        }
        catch  (DaoException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "DAO error - " + e.getMessage());

        }

    }


}
