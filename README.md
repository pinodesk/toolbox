![GitHub Release](https://img.shields.io/github/v/release/pinodesk/toolbox)
![CI](https://github.com/pinodesk/toolbox/actions/workflows/ci.yml/badge.svg)
![codecov](https://codecov.io/gh/pinodesk/toolbox/branch/main/graph/badge.svg)
[![Quality gate status](https://sonarcloud.io/api/project_badges/measure?project=pinodesk_toolbox&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=pinodesk_toolbox)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=pinodesk_toolbox&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=pinodesk_toolbox)
![License](https://img.shields.io/github/license/pinodesk/toolbox)

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
