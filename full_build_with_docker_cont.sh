#!/bin/bash

echo "Build of SampleProject"

echo "Cleaning containers"
docker rm sampleprojectcont
docker rmi sampleprojectimage

echo "Refreshing directory for data from container"
rm -r data
mkdir data

echo "PreBuild build"
docker build --build-arg SERVER_USERNAME=$SERVER_USERNAME --build-arg SERVER_PASSWORD=$SERVER_PASSWORD -f Dockerfile-pre -t prebuild .

echo "MVN Build"
docker run -e TESTCONTAINERS_HOST_OVERRIDE=docker.for.mac.host.internal -v /var/run/docker.sock:/var/run/docker.sock --name sampleprojectcont -i prebuild mvn -f /home/app/pom.xml clean package

echo "Starting sampleprojectcont"
docker start -d --restart=always sampleprojectcont

echo "Copy jar back to Tmp"
docker cp sampleprojectcont:/home/app/target data/target
docker cp sampleprojectcont:/home/app/docker data/docker

echo "Removing previous image"
docker rmi sampleprojectimage

echo "Build main image"
docker build -f Dockerfile -t sampleprojectimage .

echo "Cleaning workspace"
rm -r data

echo "Starting docker containers"
docker compose -f "docker-compose.yml" up -d --build