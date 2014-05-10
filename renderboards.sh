#!/bin/bash

mkdir -p board_renders

for board in boards/*.pb; do
    java -classpath ".:antlr.jar:commons-io-2.4.jar:physics.jar:bin" util.BoardRenderer $board board_renders
done;
