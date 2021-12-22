#!/bin/bash
files=$(ls x*)
#echo $files

i=0
for file in $files; do
    sed 's/^\[//' $file | sed 's/\]$//' > result$i
    ((i++))
done

