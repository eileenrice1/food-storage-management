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

Containerization Solution: 

In our project, the user will input data including : product name, quantity, quantity unit,
expiration date, and optionally the barcode number. This data will be sent to the API in the form of a
JSON object. This project should only need two containers. One to run the RESTful API and
handle user input and output. The requests from the front end (user level) will be handled using
a kubernetes service. Once the requests are routed, our API will parse through the data and
requests. We are also hoping to create a sorting platform where users can filter expired or soon
to be expired food using where requests are sent to the RESTful API to handle.

# Usage
From the directory in which the top level foodStorage directory is in run the following.
## Compile Command:
`mvn package`
## Run Command:
`java -cp target/foodStorage-1.0-SNAPSHOT-jar-with-dependencies.jar edu.csu.cs370.foodStorage.RestfulServer`
## Path to use
Make a get request with path "/D2"
# Docker
If haven't already, run: `docker pull maven`  
Then the following:  
`docker build --tag foodstorage .`  
`docker run --publish 8080:8080 --detach --name fs foodstorage`  
  
Stop docker container with `docker rm --force fs`
