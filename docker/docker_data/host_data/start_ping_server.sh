#!bin/bash

javac -cp data/rmi.jar data/*/*.java
java -cp data/rmi.jar: data/server/PingServerFactory $1
