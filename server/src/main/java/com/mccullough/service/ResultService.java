package com.mccullough.service;

import com.mccullough.dao.RaceDao;
import com.mccullough.dao.RunnerDao;
import com.mccullough.dao.SingleRaceResultsDao;
import com.mccullough.model.Race;
import com.mccullough.model.RaceRunnerResult;
import org.springframework.stereotype.Component;

import javax.xml.transform.Result;
import java.util.List;

@Component
public class ResultService {

    private RaceDao raceDao;
    private RunnerDao runnerDao;
    private SingleRaceResultsDao singleRaceResultsDao;

    public ResultService (RaceDao raceDao, RunnerDao runnerDao, SingleRaceResultsDao singleRaceResultsDao){
        this.raceDao = raceDao;
        this.runnerDao = runnerDao;
        this.singleRaceResultsDao = singleRaceResultsDao;
    }

    public List<RaceRunnerResult> getRaceResults(int year) {

        // Get the race info
        // Race race = raceDao.getRaceByYear(year);

         //List<RaceRunnerResult> results = singleRaceResultsDao.getRunnersByTime(year, 'M', 30);

        List<RaceRunnerResult> results = singleRaceResultsDao.getResultsByYear(year);
         return results;
    }


}
