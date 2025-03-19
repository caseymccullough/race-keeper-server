package com.mccullough.dao;


import com.mccullough.exception.DaoException;
import com.mccullough.model.Runner;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcRunnerDao implements RunnerDao {

    private final JdbcTemplate jdbcTemplate;

    private final String RUNNER_BASE_SQL = "SELECT runner_id, first_name, last_name, street, city, state_code, gender_code, birthday FROM runner ";


    public JdbcRunnerDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Runner> getRunners() {
        List<Runner> runners = new ArrayList<>();

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(RUNNER_BASE_SQL + ";");

            while (results.next()) {
                Runner runner = mapRowToRunner(results);
                runners.add(runner);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to server or database");
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return runners;
    }

    @Override
    public Runner getRunnerById(int id) {
        Runner runner = null;
        String sql = RUNNER_BASE_SQL +
                "WHERE runner_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                runner = mapRowToRunner(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to server or database");
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return runner;
    }

    @Override
    public List<Runner> getRunnersByCity(String city) {
        List<Runner> runners = new ArrayList<>();
        String sql = RUNNER_BASE_SQL +
                "WHERE city = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, city);
            while (results.next()) {
                Runner runner = mapRowToRunner(results);
                runners.add(runner);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to server or database");
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return runners;
    }

    @Override
    public List<Runner> getRunnersByState(String state_code) {
        List<Runner> runners = new ArrayList<>();
        String sql = RUNNER_BASE_SQL +
                "WHERE state_code = ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, state_code);
            while (results.next()) {
                Runner runner = mapRowToRunner(results);
                runners.add(runner);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to server or database");
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return runners;
    }

    @Override
    public List<Runner> getRunnersByName(String name, boolean useWildCard) {
        List<Runner> runners = new ArrayList<>();
        if (useWildCard) {
            name = "%" + name + "%";
        }
        String sql = RUNNER_BASE_SQL +
                "WHERE CONCAT(first_name, last_name) ILIKE ?;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, name);
            while (results.next()) {
                Runner runner = mapRowToRunner(results);
                runners.add(runner);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to server or database");
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return runners;

    }

    @Override
    public Runner createRunner(Runner newRunner) {
        Runner runner = null;
        String sql = "INSERT INTO runner (first_name, last_name, street, city, state_code, gender_code, birthday) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING runner_id;";
        try {
            int newId = jdbcTemplate.queryForObject(sql, int.class, newRunner.getFirst_name(), newRunner.getLast_name(), newRunner.getStreet(),
                    newRunner.getCity(), newRunner.getState_code(), newRunner.getGender_code(), newRunner.getBirthday());
            runner = getRunnerById(newId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return runner;
    }

    @Override
    public Runner updateRunner(Runner updatedRunner) {
        Runner runner = null;
        String sql = "UPDATE runner SET first_name=?, last_name=?, street=?, city=?, state_code=?, gender_code=?, birthday=? " +
                "WHERE runner_id = ?;";
        try {
            int rowsUpdated = jdbcTemplate.update(sql, updatedRunner.getFirst_name(), updatedRunner.getLast_name(),
                    updatedRunner.getStreet(), updatedRunner.getCity(), updatedRunner.getState_code(), updatedRunner.getGender_code(),
                    updatedRunner.getBirthday(), updatedRunner.getId());
            if (rowsUpdated == 0) {
                throw new DaoException("Zero rows affected");
            } else {
                runner = getRunnerById(updatedRunner.getId());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return runner;
    }

    @Override
    public int deleteRunner(int runnerId) {
        int numberOfRows = 0;
        String sql = "DELETE FROM runner " +
                "WHERE runner_id = ?;";
        try {
            numberOfRows = jdbcTemplate.update(sql, runnerId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return numberOfRows;
    }


    private Runner mapRowToRunner(SqlRowSet results) {
        Runner runner = new Runner();
        runner.setId(results.getInt("runner_id"));
        runner.setFirst_name(results.getString("first_name"));
        runner.setLast_name(results.getString("last_name"));
        runner.setStreet(results.getString("street"));
        runner.setCity(results.getString("city"));
        runner.setState_code(results.getString("state_code"));
        runner.setGender_code(results.getString("gender_code").charAt(0));
        if (results.getDate("birthday") != null) {
            runner.setBirthday(results.getDate("birthday").toLocalDate());
        }

        return runner;
    }
}
