# Description

Problem Description: 

Many families have a food storage to use in times of need, but keeping track of what
they have can be difficult. The first part of the problem to be solved is keeping track of what the
family has available to them, and the second part is when that food will expire. It is very easy to
forget how long certain items have been stored and end up letting it go bad before it can be
used. Since multiple members of the family will be accessing the food storage there needs to
be a way for multiple users to easily update the underlying database. To help use food before it
goes bad there should also be a mechanism to notify users when certain items are going to
expire soon so they can incorporate them into their meal plans.

# Usage
From the directory in which the top level foodStorage directory is in run the following.
## Compile Command:
`mvn package`
## Run Command:
`java -cp target/foodStorage-1.0-SNAPSHOT-jar-with-dependencies.jar edu.csu.cs370.foodStorage.RestfulServer`
# Docker
If haven't already, run: `docker pull maven`  
Then the following:  
`docker build --tag foodstorage .`  
`docker run --publish 8080:8080 --detach -name fs foodstorage`  
  
Stop docker container with `docker rm --force fs`
