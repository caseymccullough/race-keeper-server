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
     * If the year is not found, return null.
     *
     * @param year the race id to get from the datastore
     * @return a fully populated Race object
     */
    Race getRaceByYear(int year);

}
