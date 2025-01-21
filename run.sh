
# Package with skipTests
# mvn clean package -DskipTests

# Set Maven memory options if required
# export MAVEN_OPTS=-Xmx1024m -XX:MaxPermSize=128M

mvn spring-boot:run

# run Docker image build
# mvn spring-boot:build-image
# mvn spring-boot:build-image -Dspring-boot.build-image.imageName=quay.io/mmartofe/spring-boot-bootstrap-project:0.0.1-SNAPSHOT

# docker push quay.io/mmartofe/spring-boot-bootstrap-project:0.0.1-SNAPSHOT
# docker push quay.io/mmartofe/spring-boot-bootstrap-project:docker-build
# docker push quay.io/mmartofe/spring-boot-bootstrap-project:docker-build-RH
