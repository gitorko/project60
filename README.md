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

## Commands

```bash
ng new ui
cd ui
yarn add @cds/core @clr/icons @clr/angular @clr/ui
```

proxy.config.json redirects the client calls

```json
{
  "/api/*": {
    "target": "http://localhost:8080/",
    "secure": false,
    "logLevel": "debug"
  }
}
```

Modify package.json file, change the start & build command to

```bash
"start": "ng serve --proxy-config proxy.config.json --open",
"build": "ng build --prod",
```

Update the routing.The useHash:true will be useful when we deploy the application in a single uber jar later. 
If we dont use this then the back button on the application will run into errors. It uses a hash based routing instead of the default location based routing.

If you run into the error

```bash
Error: initial exceeded maximum budget.
```

Update the budget in angular.json file

```
"maximumWarning": "4mb",
"maximumError": "5mb"
```
