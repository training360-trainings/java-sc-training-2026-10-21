# Biztonságos programozás

## Adatbázis

```shell
docker run -d -e POSTGRES_DB=employees -e POSTGRES_USER=employees -e POSTGRES_PASSWORD=employees -p 5432:5432 --name employees-postgres postgres
```

## Keycloak

```shell
docker run -d -p 8090:8080 -e KC_BOOTSTRAP_ADMIN_USERNAME=admin -e KC_BOOTSTRAP_ADMIN_PASSWORD=admin --name keycloak quay.io/keycloak/keycloak start-dev
```

## SonarQube

```shell
docker run --name sonarqube -d -p 9000:9000 sonarqube:lts
```

A következőt fel kell venni a `pom.xml` fájlba:

```xml
<pluginManagement>
    <plugins>
        <plugin>
            <groupId>org.sonarsource.scanner.maven</groupId>
            <artifactId>sonar-maven-plugin</artifactId>
            <version>5.8.0.7211</version>
        </plugin>
    </plugins>
</pluginManagement>
```

Le kell generálni egy tokent: My Account / Security / Tokens

Majd környezeti változóban beállítani:

```shell
set SONAR_TOKEN=[token]
```

Majd a parancs:

```shell
mvnw sonar:sonar -Dsonar.host.url=http://localhost:9000
```