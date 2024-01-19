package com.mccullough.dao;


import com.mccullough.exception.DaoException;
import com.mccullough.model.Race;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcRaceDao implements RaceDao {
    private final JdbcTemplate jdbcTemplate;

    private final String RACE_BASE_SQL = "SELECT race_id, race_name, race_city, race_state_code, race_distance " +
            "FROM race ";

    public JdbcRaceDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<Race> getRaces() {
        List<Race> races = new ArrayList<>();

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(RACE_BASE_SQL + ";");
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
    public Race getRaceById(int id) {
        Race race = null;
        String sql = RACE_BASE_SQL +
                "WHERE race_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
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

    @Override
    public List<Race> getRacesByName(String name, boolean useWildCard) {
        List<Race> races = new ArrayList<>();
        if (useWildCard) {
            name = "%" + name + "%";
        }
        String sql = RACE_BASE_SQL +
                "WHERE race_name ILIKE ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, name);
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

    private Race mapRowToRace(SqlRowSet results) {
        Race race = new Race();

        race.setId(results.getInt("race_id"));
        race.setName(results.getString("race_name"));
        race.setCity(results.getString("race_city"));
        race.setState_code(results.getString("race_state_code"));
        race.setDistance(results.getDouble("race_distance"));

        return race;
    }
}
