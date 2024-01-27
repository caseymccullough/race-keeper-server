package com.mccullough.dao;


import com.mccullough.exception.DaoException;
import com.mccullough.model.RaceRunnerResult;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcSingleRaceResultsDao implements SingleRaceResultsDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcSingleRaceResultsDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<RaceRunnerResult> getResultsByYear(int raceYear) {
        List<RaceRunnerResult> runnerResults = new ArrayList<>();
        String sql = "SELECT r.first_name, r.last_name, r.gender_code, r.city, r.state_code,\n" +
                "\tEXTRACT (hour FROM rr.run_time) AS hours,\n" +
                "\tEXTRACT (minute FROM rr.run_time) AS minutes,\n" +
                "\tEXTRACT (second FROM rr.run_time) AS seconds\n" +
                "FROM runner r\n" +
                "JOIN runner_race rr ON r.runner_id = rr.runner_id\n" +
                "JOIN race ON rr.race_year = race.race_year\n" +
                "WHERE rr.race_year = ?\n" +
                "ORDER BY rr.run_time ASC\n" +
                "LIMIT 10;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, raceYear);
            while (results.next()) {
                RaceRunnerResult raceRunnerResult = mapToRaceRunnerResult(results);
                runnerResults.add(raceRunnerResult);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to server or database");
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return runnerResults;
    }

//    @Override
//    public List<RaceRunnerResult> getRunnersByTime(int raceId, char genderCode, int limit) {
//        List<RaceRunnerResult> runnerResults = new ArrayList<>();
//        String sql = "SELECT runner.first_name, runner.last_name, runner.gender_code, runner.city, runner.state_code, runner.birthday, \n" +
//                "DATE_PART ('YEAR', AGE(current_date, runner.birthday)) AS current_age, \n" +
//                "rr.run_time,\n" +
//                "extract (epoch from rr.run_time) AS race_time_seconds\n" +
//                "FROM runner_race rr\n" +
//                "JOIN runner ON runner.runner_id = rr.runner_id\n" +
//                "WHERE race_id = ? AND runner.gender_code = ?\n" +
//                "ORDER BY rr.run_time ASC\n" +
//                "limit ?;";
//
//        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, raceId, genderCode, limit);
//        System.out.println("limit: " + limit);
//        while (results.next()){
//            RaceRunnerResult raceRunnerResult = mapToRaceRunnerResult(results);
//            runnerResults.add(raceRunnerResult);
//        }
//
//        return runnerResults;
//    }

    private RaceRunnerResult mapToRaceRunnerResult(SqlRowSet results) {
        RaceRunnerResult raceRunnerResult = new RaceRunnerResult();
        raceRunnerResult.setFirst_name(results.getString("first_name"));
        raceRunnerResult.setLast_name(results.getString("last_name"));
        raceRunnerResult.setGender_code(results.getString("gender_code").charAt(0));
        raceRunnerResult.setCity(results.getString("city"));
        raceRunnerResult.setState_code(results.getString("state_code"));
//        raceRunnerResult.setBirthday(results.getDate("birthday").toLocalDate());
        raceRunnerResult.setRaceTimeHours((int) results.getDouble("hours"));
        raceRunnerResult.setRaceTimeMinutes((int) results.getDouble("minutes"));
        raceRunnerResult.setRaceTimeSeconds(results.getDouble("seconds"));

        return raceRunnerResult;
    }
}
