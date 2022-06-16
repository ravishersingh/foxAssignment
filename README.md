# Fox Assignment - Framework for API Testing

## Highlights
1. Uses Jackson libraries for POJO architecture to get/set values on api json.
2. There is a touch of direct json value update using JsonPath
3. Generic, expandable framework for all types of APIs methods, and variations.
4. Request bodies are maintained seperately in json files.
5. Configuration is maintained in separate file.

## Execution
### Pre-Requisites
1. JRE/JDK v11
2. Maven
3. Git

### Setup
Clone the repo in your local environment
by running the following command.

`git clone https://github.com/ravishersingh/foxAssignment.git`

Or go to - [https://github.com/ravishersingh/foxAssignment](https://github.com/ravishersingh/foxAssignment.git)

### Execution
While being in the project directory, run the Maven command: 

`mvn clean test -Dsurefire.suiteXmlFiles=src/main/resources/test.xml`

### Results
Results will be published on the console itself.
