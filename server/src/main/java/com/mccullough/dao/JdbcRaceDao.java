package com.mccullough.dao;


import com.mccullough.exception.DaoException;
import com.mccullough.model.Race;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcRaceDao implements RaceDao {
    private final JdbcTemplate jdbcTemplate;

    private final String RACE_BASE_SQL = "SELECT race_year, race_date " +
            "FROM race ";

    public JdbcRaceDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<Race> getRaces() {
        List<Race> races = new ArrayList<>();

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(RACE_BASE_SQL + "ORDER BY race_year DESC");
            while (results.next()) {
                Race race = mapRowToRace(results);
                races.add(race);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to server or database");
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return races;
    }

    @Override
    public Race getRaceByYear(int year) {
        Race race = null;
        String sql = RACE_BASE_SQL +
                "WHERE race_year = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, year);
            if (results.next()) {
                race = mapRowToRace(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to server or database");
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return race;
    }


    private Race mapRowToRace(SqlRowSet results) {
        Race race = new Race();

        race.setYear(results.getInt("race_year"));
        race.setDate(LocalDate.parse(results.getString("race_date")));

        return race;
    }
}
