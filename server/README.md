# Race Keeper Pro Server

This is the Back End to a project in which I will simulate the capture and display of results for an annual marathon.

## Races

The API can return information about races at the following end points

| METHOD | END POINT                                 | ACTION                                          |
|--------|-------------------------------------------|-------------------------------------------------|
| GET    | http://localhost:8080/races| Get all races in the database                   |
| GET    | http://localhost:8080/races/2021| Get one race by year (year serves as unique id) |

## Runners

The API can return information about runners at the following end points

| METHOD | END POINT                                  | ACTION                                                            |
|--------|--------------------------------------------|-------------------------------------------------------------------|
| GET    | http://localhost:8080/runners              | Get all runners in the database                                   |
| GET    | http://localhost:8080/runners?name_like=mc | Search for runners by name (first or last, with partial matching) |
| GET    | http://localhost:8080/runners/1            | Get a single runner by id                                         |
| POST   | http://localhost:8080/runners              | Add a runner to the database                                      |
| GET    |http://localhost:8080/runners/city/Boulder | Get runner by city                                                |

## Results

The API can return information about race results at the following end points

| METHOD | END POINT                                  | ACTION                                                                         |
|--------|--------------------------------------------|--------------------------------------------------------------------------------|
| GET    | http://localhost:8080/races/2022/results              | Get list of runners who ran in given year, ordered by time                     |
| GET    | http://localhost:8080/races/2022/results?gender=M | Get list of runners of a given gender who ran in a given year, ordered by time |
