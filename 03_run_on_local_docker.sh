
# Spefify repositiry and tag for Spring Boot API image location (comes from script 02 usually)
DESTINATION_REGISTRY=quay.io/mmartofe/spring-boot-bootstrap-project
# TAG=0.0.1-SNAPSHOT
# TAG=docker-build
TAG=docker-build-RH

# Create new network to communicate containers
docker network create spring-network

# Remove old containers if they exist
docker rm -f spring-boot-bootstrap-project
docker rm -f spring-postgres

# Run Postgres database container
docker run \
    -p 5432:5432 \
    --net=spring-network \
    --name spring-postgres \
    -e POSTGRES_USER=demo \
    -e POSTGRES_PASSWORD=demo \
    -e POSTGRES_DB=demo \
    -d postgres

# Use following command to check your database
#
# docker exec -it spring-postgres psql -U demo
# 

# Run Spring Boot API container connecting to above database in profile docker
# notice: spring.profiles.active=docker
# is set to manage active profiles for Spring Boot
docker run \
    -p 8080:8080 \
    --net=spring-network \
    --name=spring-boot-bootstrap-project \
    -e "JAVA_OPTS=-Dspring.profiles.active=docker -Xmx128m" \
    -d "$DESTINATION_REGISTRY":"$TAG"
