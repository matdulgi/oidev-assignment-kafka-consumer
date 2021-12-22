#!/bin/bash
files=$(ls x*)
#echo $files

for file in $files; do
    sed 's/\[//' $file > $file
    sed 's/\]//' $file > $file
done
