STARWARS API (SWAPI)

Overview
API is the acronym for Application Programming Interface, which is a software intermediary that allows two applications to talk to each other. This API framework is developed using REST Assured and Cucumber.

REST Assured is a Java library that provides a domain-specific language (DSL) for writing powerful, maintainable tests for RESTful APIs. Cucumber is an open-source library that supports behavior-driven development (BDD). To be more precise, Cucumber can be defined as a testing framework, driven by plain English text. It serves as documentation, automated tests, and a development aid – all in one.

For demo purposes, all the test cases are executed against the SWAPI documentation at https://swapi.dev/documentation#root.

Running Tests:Open the command prompt and navigate to the folder containing the pom.xml file. Run the following Maven command:
mvn clean test

Report Location: target/report
Log Location: target/logs