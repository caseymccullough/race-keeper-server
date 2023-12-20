package com.mccullough.dao;

import com.mccullough.model.Race;
import java.util.List;

public interface RaceDao {

    /**
     * Get a list of all races from the datastore.
     * The list is never null. It is empty if there are no races in the datastore.
     *
     * @return all races as a list of Race objects
     */
    List<Race> getRaces();

    /**
     * Get a race from the datastore that has the given id.
     * If the id is not found, return null.
     *
     * @param id the race id to get from the datastore
     * @return a fully populated Race object
     */
    Race getRaceById(int id);

    /**
     * Get a list of all the races from the datastore that match the name.
     * The list is never null. It is empty if no matching races were found.
     *
     * @param name the race name to get from the datastore
     * @param useWildCard wraps name with wild card characters if true
     * @return all matching races as a list of Race objects
     */
    List<Race> getRacesByName(String name, boolean useWildCard);
}
