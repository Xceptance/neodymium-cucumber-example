# Introduction
This repository demonstrates how to use our [Neodymium library](https://github.com/Xceptance/neodymium-library) to set up a maintainable and well structured test automation project. Furthermore we use the [Wiki](https://github.com/Xceptance/neodymium-library/wiki) to show and explain how to use the features from the Neodymium library. We also give insight how we think a test automation project should be structured and what results you should get out of it.

## Cucumber
Within this example project we demonstrate how to set up and implement a Cucumber BDD test automation project. You can find information about Cucumber within our [Wiki](https://github.com/Xceptance/neodymium-library/wiki/Cucumber).

## How to try it out
This will be a short introduction how to get it running. 

### Prerequisites
You will need the following technologies available to try it out:
* Git
* Maven 3+
* JDK 8
* IDE of your choice 
* Web browser's of your choice and their respective [WebDrivers](https://github.com/Xceptance/neodymium-library/wiki/How-to-set-up-a-WebDriver)

### Manage Posters
Posters is a web shop for demo purposes build by [Xceptance](https://www.xceptance.com/en/). We use it throughout all our software products to demonstrate their capabilities within a simple and stable environment. 

##### Get Posters
1. Download latest XLT [here](https://www.xceptance.com/en/xlt/download.html).
2. Extract the zip file in the preferred folder.

##### Run Posters
1. Open a console goto [extractXltPath]/samples/app-server/bin/
2. Start Posters with: ./start.sh for Linux or \start.cmd for Windows.
3. Goto [https://localhost:8443/posters/](https://localhost:8443/posters/) to check if it is running.

##### Stop Posters
Stopping Posters just press Ctrl+C and cancel the batch process with "Y".

##### Reset database
1. Stop the server.
2. Delete ./samples/app-server/data
3. Start the server again.
4. DB will be recreated with default values.

##### Delete log files
The posters store create log files which are located in ./samples/app-server/logs folder. You can delete them while the server is stopped to free disk space.

### Get yourself a free copy
Simply clone or fork this project from [here](https://github.com/Xceptance/neodymium-cucumber-example).

### IDE way of doing
1. Import the project as Maven project
2. Set up your [WebDrivers](https://github.com/Xceptance/neodymium-library/wiki/How-to-set-up-a-WebDriver)
3. Goto `posters.cucumber.tests`
4. Run `RunAllFeaturesTest.java` as JUnit test.

### Get into the Console
1. Open a console of your choice
2. Goto the project folder
3. Run `mvn clean test`

### Test out Allure reports
Please check our [Wiki](https://github.com/Xceptance/neodymium-library/wiki/Allure-reports) to get all info you need.

# Template
If you like our ideas and you would like to start a test automation project using the Neodymium library. We prepared a template that can be used as "Hello World" tutorial and is a good starting point for your own project.

Check out [Neodymium Template](https://github.com/Xceptance/neodymium-template).

## License
MIT