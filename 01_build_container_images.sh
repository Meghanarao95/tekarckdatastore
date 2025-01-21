# Set your destination registry right at the start to don't rename images then 
# notice: I'm using publicly available service of https://quay.io provided by Red Hat, you can use yours

DESTINATION_REGISTRY=quay.io/mmartofe/spring-boot-bootstrap-project

# Set tag names for all 3 images as follows
TAG_MVN=0.0.1-SNAPSHOT
TAG_DOCKER=docker-build
TAG_DOCKER_RH=docker-build-RH

# Build application jar file
# notice: for now I need to skip tests here as Flyway can't resolve database uri once multiple profiles set at application.properties
./mvnw clean package -DskipTests

# Build container image with Paketo buildpacks using Maven plugin
# notice: for now I need to skip tests here as Flyway can't resolve database uri once multiple profiles set at application.properties
mvn spring-boot:build-image \
    -DskipTests \
    -Dspring-boot.build-image.imageName="$DESTINATION_REGISTRY":"$TAG_MVN"

# Build container image with regular Dockerfile (OpenJDK binaries built by Eclipse Temurin, by Adoptium)
docker build \
    -f ./dockerfiles/Dockerfile \
    -t "$DESTINATION_REGISTRY":"$TAG_DOCKER" .

# Build container image with regular Dockerfile (Red Hat certified image)
docker build  \
    -f ./dockerfiles/Dockerfile-RH \
    -t "$DESTINATION_REGISTRY":"$TAG_DOCKER_RH" .
