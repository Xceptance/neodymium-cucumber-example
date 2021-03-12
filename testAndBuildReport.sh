#!/bin/sh

mvn test allure:report
mvn allure:aggregate
mv ./target/site/allure-maven-plugin/history ./target/allure-results/history
mvn allure:serve
