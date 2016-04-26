#!/bin/sh

# Check if there are at least three parameters from shell.
if [[ $# -lt 1 ]] ; then
    echo "Usage: ./start.sh <port_num>"
    echo "Example: ./start.sh 8886"
    exit 0
fi

# Compile java files and create jar for rmi package
#javac docker/docker_data/host_data/PingPongClient.java
#javac docker/docker_data/host_data/PingPongServer.java
#jar -cvf docker/docker_data/host_data/rmi.jar rmi/*

# Start a Docker Daemon
docker-machine rm -f kai-siyuan-proj1
docker-machine create -d virtualbox kai-siyuan-proj1
docker-machine ls
eval "$(docker-machine env kai-siyuan-proj1)"

# Build images for data volume container and client/server containers
docker build -t tomatosliu/ubuntu_data:0.1 docker/docker_data
docker build -t tomatosliu/ubuntu_sc:0.1 docker/docker_sc

# Run data volume container and catclient/catserver containers
docker run -itd -v /data --name data tomatosliu/ubuntu_data:0.1 bash
docker run -itd --volumes-from data --name server tomatosliu/ubuntu_sc:0.1 java -cp data/rmi.jar: data/server/PingServerFactory $1
docker run -itd --volumes-from data --link server --name client tomatosliu/ubuntu_sc:0.1 java -cp data/rmi.jar: data/client/PingPongClient $1

# Some sort of test, print the log and see the result and verification
echo "=============================build completed============================="
echo ""
echo 'Running the communication between client and server... (about 1 sec)'
sleep 1s
echo "============================Running completed============================"
echo ""
echo "$ docker logs client"
docker logs client
echo ""
echo "$ docker logs server"
docker logs server
