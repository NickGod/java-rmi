Cat Server
================================================================================
By Siyuan Liu (A53099540)

How to run a demo:
    $ ./start.sh <server_file_to_read> <client_file_to_read> <port_num>

    Then the shell will display the building of containers.
    Check out the instructions in 'start.sh' for more details.

More testing instruction:
    My demo integrates the whole thing in a single run. Someone may want to
    verify more things, such as COPYing new files into the image and running
    server/client with different files. Default, there is only one file
    'string.txt' there for data volume container which you can see in directory
    'host_data/string.txt'. If you'd like to COPY other files, please follow the
    steps:
        1. copy files into host directory 'docker_data/host_data/'
            e.g. copy a file 'string.txt' into the directory (already exists).
        2. run the demo with the file names (files for catclient and catserver
           can be different).
            e.g. ./start.sh string.txt string.txt 8886
            
