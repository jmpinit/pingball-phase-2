#!/bin/bash

PIPE=/tmp/pingballpipe

mkfifo $PIPE

tail -f $PIPE | java -classpath ".:antlr.jar:physics.jar:bin" server.PingballServer &
SERVER=$!

sleep 1

java -classpath ".:antlr.jar:physics.jar:bin" client.PingballGUI --port 10987 --host localhost --file boards/nw.pb &
CLIENT1=$!

java -classpath ".:antlr.jar:physics.jar:bin" client.PingballGUI --port 10987 --host localhost --file boards/ne.pb &
CLIENT2=$!

sleep 1

echo h nw ne > $PIPE

read

kill $SERVER
kill $(jobs -p)
