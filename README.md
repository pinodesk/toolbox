![CI](https://github.com/pinodesk/toolbox/actions/workflows/ci.yml/badge.svg)
![codecov](https://codecov.io/gh/pinodesk/toolbox/branch/main/graph/badge.svg)
![License](https://img.shields.io/github/license/pinodesk/toolbox)
![GitHub Release](https://img.shields.io/github/v/release/pinodesk/toolbox)

# Toolbox

General-purpose utilities for Java projects

## How to Use

1. Add GitHub Packages as a Maven repository in your `pom.xml`:

```xml
<repositories>
    <repository>
        <id>github</id>
        <url>https://maven.pkg.github.com/pinodesk/toolbox</url>
    </repository>
</repositories>
```

2. Add this project as a Maven dependency:

```xml
<dependency>
    <groupId>com.pinodesk</groupId>
    <artifactId>toolbox</artifactId>
    <version>0.3.0-SNAPSHOT</version>
</dependency>
```

## Badges

This project uses the following free services for open source projects:

- **GitHub Actions**: CI/CD pipeline status
- **Codecov**: Code coverage reporting
- **GitHub License**: License badge (MIT)

### Optional Badge Services

You can also add these badges by configuring the respective services:

- **SonarCloud**: Code quality and security analysis
  ```markdown
  ![SonarCloud](https://sonarcloud.io/api/project_badges/measure?project=pinodesk_toolbox&metric=alert_status)
  ```
- **Maven Central**: If you publish to Maven Central
  ```markdown
  ![Maven Central](https://img.shields.io/maven-central/v/com.pinodesk/toolbox)
  ```
- **GitHub Release**: Latest release version
  ```markdown
  ![GitHub Release](https://img.shields.io/github/v/release/pinodesk/toolbox)
  ```
