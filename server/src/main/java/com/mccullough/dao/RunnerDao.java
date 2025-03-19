package com.mccullough.dao;

import com.mccullough.model.Runner;

import java.util.List;

public interface RunnerDao {

    /**
     * Get a list of all runners from the datastore.
     * The list is never null. It is empty if there are no runners in the datastore.
     *
     * @return all runners as a list of Runner objects
     */
    List<Runner> getRunners();

    /**
     * Get a runner from the datastore that has the given id.
     * If the id is not found, return null.
     *
     * @param id the runner id to get from the datastore
     * @return a fully populated Runner object
     */
    Runner getRunnerById(int id);

    /**
     * Get a list of all runners from a given city.
     *
     * @param city the city of runners to get from the datastore
     * @return all runners from the city as a list of Runner objects
     */
    public List<Runner> getRunnersByCity(String city);

    /**
     * Get a list of all runners from a given state.
     *
     * @param state_code the city of runners to get from the datastore
     * @return all runners from the city as a list of Runner objects
     */
    public List<Runner> getRunnersByState(String state_code);

    /**
     * Get a list of all the runners from the datastore that match the name.
     * The list is never null. It is empty if no matching runners were found.
     *
     * @param name the runner name to get from the datastore
     * @param useWildCard wraps name with wild card characters if true
     * @return all matching runners as a list of Runner objects
     */
    List<Runner> getRunnersByName(String name, boolean useWildCard);

    /**
     *
     * @param newRunner the Runner object to be added to the datastore
     * @return the updated Runner object
     */
    Runner createRunner (Runner newRunner);

    /**
     * Update a customer to the datastore. Only called on customers that
     * are already in the datastore.
     *
     * @param updatedRunner The Runner object to update.
     * @return The updated Runner object.
     */
    Runner updateRunner (Runner updatedRunner);

    /**
     *
     * @param id
     * @return the number of rows deleted
     */
    int deleteRunner (int id);

}
