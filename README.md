# Project 60

Spring WebFlux & Angular, Reactive MongoDB, Clarity, Docker

[https://gitorko.github.io/spring-webflux-angular/](https://gitorko.github.io/spring-webflux-angular/)

### Version

Check version

```bash
$java --version
openjdk 17.0.3 2022-04-19 LTS

node --version
v16.16.0

yarn --version
1.22.18
```

### Mongo DB

```bash
docker run --name my-mongo -e MONGO_INITDB_ROOT_USERNAME=test -e MONGO_INITDB_ROOT_PASSWORD=test@123 -p 27017:27017 -d mongo 
docker ps
```

### Dev

To run the backend in dev mode.

```bash
./gradlew clean build
./gradlew bootRun
```

To Run UI in dev mode

```bash
cd ui
yarn install
yarn build
yarn start
```

Open [http://localhost:4200/](http://localhost:4200/)

### Prod

To run as a single jar, both UI and backend are bundled to single uber jar.

```bash
./gradlew cleanBuild
cd build/libs
java -jar project60-1.0.0.jar
```

Open [http://localhost:8080/](http://localhost:8080/)

### Docker

```bash
./gradlew cleanBuild
docker build -f docker/Dockerfile --force-rm -t project60:1.0.0 .
docker images |grep project60
docker tag project60:1.0.0 gitorko/project60:1.0.0
docker push gitorko/project60:1.0.0
docker-compose -f docker/docker-compose.yml up
```
