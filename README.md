# Java RMI

_Impplement a Java RMI from scratch._

By Siyuan Liu (A53099540, sil148@eng.ucsd.edu) and Kai Zhou (A53101200, kaz040@eng.ucsd.edu)

## Run
__Run conformance test__

```
$ cd project1
$ make test
```
Then you can see the automatic checking information.

__Run Docker PingPong test__

```
$ ./start.sh <port_num>
```
`port_num` is the port number you want to use in this test. You can change it into
whatever you want. e.g. `8886`.

Then you will see all the docker building information. After building completed,
you will see the notification and then the test results which shows the logs in
`PingPongClient` and `PingPongServer`.

<!--
## Log
`04/21`: finished basic framework and passed all the tests in conformace.

`04/25`: finished docker test.
-->
