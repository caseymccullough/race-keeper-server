package com.mccullough.controller;

import com.mccullough.dao.SingleRaceResultsDao;
import com.mccullough.model.RaceRunnerResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/results")
public class RaceRunnerResultController {

    private final SingleRaceResultsDao resultsDao;

    public RaceRunnerResultController(SingleRaceResultsDao singleRaceResultsDao) {
        this.resultsDao = singleRaceResultsDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<RaceRunnerResult> listByTime(@RequestParam(value = "id") int raceId,
                                             @RequestParam(value = "limit") int limit,
                                             @RequestParam(value = "gender") char gender) {
        return resultsDao.getRunnersByTime(raceId, gender, limit);

    }

}
