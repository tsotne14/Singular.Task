# Test Automation Framework
This is a Test Automation Framework designed using Java, RestAssured, Cucumber, and Maven. The framework follows a Behavior Driven Development (BDD) methodology to automate API tests.
## Technology Stack
Programming Language: Java
API Testing Framework: RestAssured
BDD Framework: Cucumber
Build Tool: Maven
### Prerequisites
Java 20.0 or later
An IDE (like IntelliJ IDEA)
## Running Tests
You can run the tests from within your IDE.
#### From within the IDE:
Navigate to "src/test/runner/TestRunner"
Change "tags" parameter to the relevant one
Run the file
The project follows a standard Maven structure and Cucumber BDD structure:
src/main/java: This contains the main code for the framework.
src/test/java: This contains the test code, Page Objects, and Test Runner.
src/test/resources: This contains feature file where the BDD scenarios are written.
Reporting
Upon completion of the test run, a report is automatically generated under the target folder. The report provides a comprehensive view of the executed tests with their status (passed/failed).
