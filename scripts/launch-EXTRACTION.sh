#!bin/sh


if sh compile-all.sh
then
  cd ../
java extraction.Main
sleep 10m
fi
