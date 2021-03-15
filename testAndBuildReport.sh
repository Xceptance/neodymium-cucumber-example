#!/bin/sh

# If a windows user wants to try this script and has git installed, goto windows explorer, 
# right click the project directory, click "Git Bash Here", execute this script.

# check prerequistes
if ! which mvn > /dev/null 2>&1; then
  echo "There is no maven installed. Apache Maven 3 is required to run."
  exit 1
fi
if ! mvn --version | grep 'Apache Maven 3'; then
  echo "Apache Maven 3 is required to run. Recheck following:"
  mvn --version
  exit 1
fi

# run test and create allure report files 
mvn test allure:report

# create allure report
mvn allure:aggregate

# show allure report
echo "Copy .\target\site\allure-maven-plugin\history to .\target\allure-results\history"
mv ./target/site/allure-maven-plugin/history ./target/allure-results/history
mvn allure:serve
