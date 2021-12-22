#!/bin/bash
lst=$(ls x*)
i=0
for file in $lst; do
    mv $file sampledata_$i.json
    ((i++))
done
