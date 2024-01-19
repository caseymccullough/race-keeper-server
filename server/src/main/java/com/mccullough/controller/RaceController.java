package com.mccullough.controller;

import com.mccullough.dao.RaceDao;
import com.mccullough.model.Race;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping (path = "/races")
public class RaceController {
    private final RaceDao raceDao;

    public RaceController (RaceDao raceDao) {
        this.raceDao = raceDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Race> list() {
        return raceDao.getRaces();
    }

    @RequestMapping(path = "/{year}", method = RequestMethod.GET)
    public Race get (@PathVariable int year) {
        return raceDao.getRaceByYear(year);
    }

}
