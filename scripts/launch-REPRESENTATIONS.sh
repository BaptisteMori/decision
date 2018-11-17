#!bin/sh


if sh compile-all.sh
then
  cd ../
java representations.Main 
fi
