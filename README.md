# Description
# Usage
From the directory in which the top level foodStorage directory is in run the following.
## Compile Command:
`mvn package`
## Run Command:
`java -cp target/foodStorage-1.0-SNAPSHOT-jar-with-dependencies.jar edu.csu.cs370.foodStorage.RestfulServer`
# Docker
If haven't already, run: `docker pull maven`\n
Then the following:\n
`docker build --tag foodstorage .`\n
`docker run --publish 8080:8080 --detach -name fs foodstorage`\n
\n
Stop docker container with `docker rm --force fs`
