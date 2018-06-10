# war-deploy-docker-demo
Demo application that demonstrates the deployment of a simple Helloworld webapp in a Docker container for integration testing.

## Requirements
* You need to have docker + docker compose installed
* you need to have a running jenkins instance
* create Jenkins pipeline and enter `jenkins/Jenkinksfile` as pipeline script

## Running tests locally
* you need to have apache maven, docker + docker-compose
```
docker-compose -f containers/docker-compose.yml up -d
mvn install
```