#!bin/sh


if sh compile-all.sh
then
  cd ../
java diagnosis.Main
sleep 10m
fi
