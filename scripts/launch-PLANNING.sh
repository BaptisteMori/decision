#!bin/sh


if sh compile-all.sh
then
  cd ../
java planning.Main
sleep 10m
fi
