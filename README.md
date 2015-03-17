# ERNI Ideas Board
Repository containing both backend and frontend. More details to come!

# How to build the whole application
Make sure you have prerequisites for both client/server fulfilled! (See separate README.md for 
[client](https://github.com/ERNICommunity/erni-ideas-board/blob/master/client/README.md) and [server](https://github.com/ERNICommunity/erni-ideas-board/blob/master/backend/README.MD)) 
           
Then, this is a one time configuration only: 
            
            cd client
            mvn frontend:install-node-and-npm
            npm install bower           
            npm install grunt-cli

If you have done this and you want to build the application type in the `erni-ideas-board` root:
                   
            mvn clean package --DskipTests=true
            
You can then run the application (you must have MongoDB running, don't forget!):
            
            mvn spring-boot:run