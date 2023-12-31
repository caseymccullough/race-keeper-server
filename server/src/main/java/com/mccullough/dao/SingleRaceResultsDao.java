package com.mccullough.dao;


import com.mccullough.model.RaceRunnerResult;

import java.util.List;

public interface SingleRaceResultsDao {
    List<RaceRunnerResult> getRunnersByTime(int raceId, int raceYear, int limit);
}
