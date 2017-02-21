US Elections 2016
The project predicts the probable chances of winnning of every candidate in the 2016 US Presidential Elections

How to execute -
Run the following command on the terminal - 
java -cp "electionus-jar-with-dependencies.jar;stanford-english-corenlp-2016-01-10-models.jar" com.utd.bigdata.electionus.GetTweets
    
This will create a tweets.txt file and will continuously append to it as the new data arrives. 

Format of tweets.txt File -
"TweetNumber userName Tweet Timestamp countryName placeName geoLocation sentimentValue"

JAVA Code
1. pom.xml can be used to create a Maven project which will download all the necessary dependencies
2. An external jar for NLP needs to be downloaded. It is - stanford-english-corenlp-2016-01-10-models.jar
3. Maven creates a jar called- electionus-jar-with-dependencies.jar

SCALA Code 
runCode.scala contains the scala code. To run it use the following command - 
    spark-shell -i runCode.scala --master local[1]
    
runCode.scala will create a file named "merge" in HDFS. Use the fetchFile.sh, that is the bash script, to get the file to localhost and compile the iutput obtained from "merge" into a single file named as "output.txt". If you want to graphically represent the results using Tableau or any other too, you can directly feed the output.txt file to the software and generate a graph/pie chart. 

./fetchFile.sh

Note- Do make sure you have made the script executable. You can do so by the following command- 
      chmod +x fetchFile.sh OR chmod 755 fetchFile.sh
