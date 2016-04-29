FROM java:7
MAINTAINER Kai-Siyuan <siyuanliu@ucsd.edu>

# Add scripts into the image
COPY host_data/start_ping_server.sh data/
COPY host_data/start_ping_client.sh data/

COPY host_data/server/PingPongServer.java data/server/
COPY host_data/client/PingPongClient.java data/client/
COPY host_data/common/PingPongInterface.java data/common/
COPY host_data/common/FactoryInterface.java data/common/
COPY host_data/server/PingServerFactory.java data/server/
COPY host_data/rmi.jar data/
