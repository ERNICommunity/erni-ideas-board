# ERNI IdeasBoard backend

## Preconditions for developers

### For building it using command line
> For a detailed instructions see the list at the end

- Git for your OS
- JDK 8 (the latest possible)
- Apache Maven 3.x
- Standalone MongoDB installation

### For making changes in the code
- IntelliJ IDEA or Eclipse ([Spring's version might be suitable](https://spring.io/tools/sts))
- Standalone Apache Tomcat 8.x - this is useful if you want to make use of "Update classes and resources" feature for rapid development (see below)

## How to build it using Maven
`mvn clean package`

## How to build and run it using Maven
Note: You should have MongoDB running already.

`mvn clean package spring-boot:run`

And the you can navigate to `http://localhost:8080`. There already is one super-simple REST endpoint: `http://localhost:8080/ideas` which supports CRUD operations. Explore it ;).
UPDATE: You will need to use the Authentication mechanism described in "OAuth Authentication". The user database is the "Community login" one which was used for ERNI Moods app.
 
## How to deploy it as a war file to standalone Tomcat
First you need to enable the `package-war` mavne profile and run `mvn clean package`. 
Then you need to setup your IDE to deploy built artifact. In IntelliJ IDEA or Eclipse it usually means creating web-facet and set up the server. 
This is highly individual and should be set by the developer (I can give some hints for IDEA users ;). 

## In-depth explanation of how to run the code
> This is a draft... I'll update it once I get a hold of some vanilla-notebook

Any computer with Windows, Linux or Mac OS can be used for building and running the backend service. Ensure you have admin rights and are able to install the software on your computer.

- You can start by installing [Git for your OS](http://git-scm.com/downloads), you won't need Git GUI client, Git bash is sufficient. The installation is straight forward, you can use the default settings. Ensure afterwards that you can run your git bash and that you can run `git --version` command. I would highly recommend to run all the command line commands inside Git bash from now on, since you have some useful linux commands available. All of the next steps are working with Git bash. 
- Download and install the latest JDK from [Oracle's site](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- Then download the latest [Maven binary](http://maven.apache.org/download.cgi). This project uses Maven for compiling sources and building executable package.
- Ensure that you have environment variables set up properly, e.g. you can run `mvn --version` and `java -version`
    * If not, then set on Windows `JAVA_HOME` variable to you Java installation (e.g. C:\Program Files\Java\jdk1.8.0_40) and `M2_HOME` to your Maven directory (e.g. C:\dev\bin\apache-maven-3.2.5)
    On Windows you can do it via "Environment properties" dialog or for the current command line session using commands
    
            set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_40
            set M2_HOME=C:\dev\bin\apache-maven-3.2.5
            
            echo %JAVA_HOME%
            echo %M2_HOME%
              
    * The crucial part however is setting the `PATH` environment variable correctly (so that `mvn` and `java` commands are visible in the command line). 

            echo %PATH%
            
            set PATH=%PATH%;%M2_HOME%\bin;%JAVA_HOME%\bin;
            
- Now you should be able to run the `mvn` and `java` commands without problem. If you set this via environment variables dialog then these changes should be persistent. If not they are only applied to current command line session.
- Clone the source code to your drive, and try building it
 
            git clone https://github.com/ERNICommunity/erni-ideas-board.git
            cd erni-ideas-board/backend
            mvn clean package
            
- Then, download [MongoDB](https://www.mongodb.org/downloads). Download the one corresponding to your OS. Then navigate to installation directory and run mongo with the path to your database (create a directory if not existing):
            
            mongod --dbpath "C:\dev\db\mongodb-data"
            
- Finally, you can run the `mvn spring-boot:run` (inside the application root) and the server should start up. When you navigate your browser to `http://localhost:8080` you should get some default JSON message as a response. 

### Troubleshooting

When you get trying to build the sources via Maven error message like this:

`[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.1:compile (default-compile) on project backend: Fatal error compiling: invalid target release: 1.8 -> [Help 1]`

It is most likely caused by incorrect JAVA_HOME variable. Ensure that it is set to jdk8 and that when you run `mvn --version` the used java version is 8.

### OAuth Authentication 

(I will explain it more deeply, but for now...)

If you want to perform a registration request, issue this call:

            curl -X POST http://localhost:8080/register -H "Content-type:application/json" -d "{\"username\":\"<<<USERNAME>>>\", \"phone\":\"<<<PHONE>>>\", \"password\": \"<<<PASSWORD>>>\", \"email\": \"<<<EMAIL>>>\"}"
            
If everything is valid, you should get a `HTTP 200` response. To authenticate with this newly created or already existing user perform:

            curl -X POST -vu <<<CLIENT_ID>>>:<<<CLIENT_SECRET>>> http://localhost:8080/oauth/token -H "Accept:application/json" -d "password=<<<PASSWORD>>>&username=<<<USERNAME>>>&grant_type=password&scope=read&client_secret=<<<CLIENT_SECRET>>>&client_id=<<<CLIENT_ID>>>"
            
Of course, replace the `<<<STRINGS>>>` with your values ;). You should then get a OAuth token, which you can use for accessing the endpoints:
            
            curl http://localhost:8080/ideas -H "Authorization: Bearer <<<ACCESS_TOKEN>>>"
            
More explanation to come...