# Java RMI

_Impplement a Java RMI from scratch._

By Siyuan Liu and Kai Zhou

## Run
__Run conformance test__

```
$ cd project1
$ make test
```
Then you can see the automatic checking information.

__Run Docker PingPong test__

```
$ ./start.sh 8886
```
`8886` is the port number you want to use in this test. You can change it into
whatever you want.

Then you will see all the docker building information. After building completed,
you will see the notification and then the test result which shows the logs in
`PingPongClient` and `PingPongServer`.


## Log
`04/21`: finished basic framework and passed all the tests in conformace.

`04/25`: finished docker test.
