package com.mccullough.dao;


import com.mccullough.model.RaceRunnerResult;

import java.util.List;

public interface SingleRaceResultsDao {

    List<RaceRunnerResult> getResultsByYear(int raceYear);

   // List<RaceRunnerResult> getRunnersByTime(int raceYear, char genderCode, int limit);
}
