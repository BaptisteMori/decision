#!bin/sh


if sh compile-all.sh
then
  cd ../
java examples.Main
sleep 10m
fi
