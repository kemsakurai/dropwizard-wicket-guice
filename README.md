# dropwizard-wicket-guice
=========================
Module to run wicket web applications with guice in dropwizard 

## QuickStart
Git Clone
```
git clone https://github.com/kemsakurai/dropwizard-wicket-guice.git
```

Move Project Directory
```
cd dropwizard-wicket-guice
```

Excute gradle shadowjar task
```
./gradlew shadowjar
```

Excute fatJar
```
java -jar ./build/libs/dropwizard-wicket-guice-0.1.0-all.jar server develop.yml
```
Access the following URL.
```
http://120.0.0.1:8080
```
