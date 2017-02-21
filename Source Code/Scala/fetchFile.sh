#!/bin/bash
hdfs dfs -get merge
cat merge/* > merge.txt
sed -e 's/(//' -e 's/))//' -e 's/CompactBuffer(//' -e 's/ //'< merge.txt > output.txt
