package com.mccullough.controller;

import com.mccullough.dao.RaceDao;
import com.mccullough.model.Race;
import com.mccullough.model.RaceRunnerResult;
import com.mccullough.service.ResultService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (path = "/races")
public class RaceController {
    private final RaceDao raceDao;
    private final ResultService resultService;

    public RaceController (RaceDao raceDao, ResultService resultService) {
        this.raceDao = raceDao;
        this.resultService = resultService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Race> list() {
        return raceDao.getRaces();
    }

    @RequestMapping(path = "/{year}", method = RequestMethod.GET)
    public Race get (@PathVariable int year) {
        return raceDao.getRaceByYear(year);
    }

    @RequestMapping(path = "/{year}/results", method = RequestMethod.GET)
    public List<RaceRunnerResult> listResults(@RequestParam(value = "race_year") int raceYear){
        return resultService.getRaceResults(raceYear);
    }

//    @RequestMapping(method = RequestMethod.GET)
//    public List<RaceRunnerResult> listByTime(@RequestParam(value = "id") int raceId,
//                                             @RequestParam(value = "limit", defaultValue = "30") int limit,
//                                             @RequestParam(value = "gender", required = false) Character gender) {
//        // return resultsDao.getRunnersByTime(raceId, gender, limit);
//
//        RaceRunnerResult testResult = new RaceRunnerResult()
//
//    }

}
