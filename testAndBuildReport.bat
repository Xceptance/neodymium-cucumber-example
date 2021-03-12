call mvn test allure:report
call mvn allure:aggregate
xcopy .\target\site\allure-maven-plugin\history .\target\allure-results\history /Y /Q /I /S
call mvn allure:serve
