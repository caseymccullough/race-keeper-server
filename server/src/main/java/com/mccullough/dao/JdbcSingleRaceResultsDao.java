package com.mccullough.dao;


import com.mccullough.model.RaceRunnerResult;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcSingleRaceResultsDao implements SingleRaceResultsDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcSingleRaceResultsDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<RaceRunnerResult> getRunnersByTime(int raceId, char genderCode, int limit) {
        List<RaceRunnerResult> runnerResults = new ArrayList<>();
        String sql = "SELECT runner.first_name, runner.last_name, runner.gender_code, runner.city, runner.state_code, runner.birthday, \n" +
                "DATE_PART ('YEAR', AGE(current_date, runner.birthday)) AS current_age, \n" +
                "rr.run_time,\n" +
                "extract (epoch from rr.run_time) AS race_time_seconds\n" +
                "FROM runner_race rr\n" +
                "JOIN runner ON runner.runner_id = rr.runner_id\n" +
                "WHERE race_id = ? AND runner.gender_code = ?\n" +
                "ORDER BY rr.run_time ASC\n" +
                "limit ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, raceId, genderCode, limit);
        System.out.println("limit: " + limit);
        while (results.next()){
            RaceRunnerResult raceRunnerResult = mapToRaceRunnerResult(results);
            runnerResults.add(raceRunnerResult);
        }

        return runnerResults;
    }

    private RaceRunnerResult mapToRaceRunnerResult(SqlRowSet results) {
        RaceRunnerResult raceRunnerResult = new RaceRunnerResult();
        raceRunnerResult.setFirst_name(results.getString("first_name"));
        raceRunnerResult.setLast_name(results.getString("last_name"));
        raceRunnerResult.setGender_code(results.getString("gender_code").charAt(0));
        raceRunnerResult.setCity(results.getString("city"));
        raceRunnerResult.setState_code(results.getString("state_code"));
        raceRunnerResult.setBirthday(results.getDate("birthday").toLocalDate());
        raceRunnerResult.setRaceTimeInSeconds(results.getDouble("race_time_seconds"));

        return raceRunnerResult;
    }
}
