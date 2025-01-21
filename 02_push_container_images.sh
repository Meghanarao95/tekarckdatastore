# Push your recently built imaget to required registry
# notice: I'm using publicly available service of https://quay.io provided by Red Hat, you can use yours

DESTINATION_REGISTRY=quay.io/mmartofe/spring-boot-bootstrap-project

# Set tag names for all 3 images as follows
TAG_MVN=0.0.1-SNAPSHOT
TAG_DOCKER=docker-build
TAG_DOCKER_RH=docker-build-RH

docker push "$DESTINATION_REGISTRY":"$TAG_MVN"
docker push "$DESTINATION_REGISTRY":"$TAG_DOCKER"
docker push "$DESTINATION_REGISTRY":"$TAG_DOCKER_RH"
