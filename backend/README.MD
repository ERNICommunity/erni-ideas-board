# ERNI IdeasBoard backend

## Preconditions for developers
### For building it you should have:
- JDK 8 (the latest possible)
- Apache Maven 3.x
- Standalone MongoDB installation

### For making changes in the code:
- IntelliJ IDEA or Eclipse (Spring source version should fit better)
- Standalone Apache Tomcat 8.x - this is useful if you want to make use of "Update classes and resources" feature for rapid development (see below)

## How to build it using Maven
`mvn clean package`

## How to build and run it using Maven
Note: You should have MongoDB running already.

`mvn clean package spring-boot:run`

And the you can navigate to `http://localhost:8080` or `http://localhost:8080/hello` for HelloWorld message.
 
## How to deploy it as a war file to standalone Tomcat
First you need to enable the `package-war` mavne profile and run `mvn clean package`. 
Then you need to setup your IDE to deploy built artifact. In IntelliJ IDEA or Eclipse it usually means creating web-facet and set up the server. 
This is highly individual and should be set by the developer (I can give some hints for IDEA users ;). 
