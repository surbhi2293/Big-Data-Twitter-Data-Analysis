/* 
 * Pranjal Patni (pxp142030)
 * Surbhi Mathur (sxm150231)
 * Silpy Jain (sxj152930)
 * Vaidehi Jariwala (vxj150030)
 * @author Pranjal Patni, Surbhi Mathur, Silpy Jain, Vaidehi Jariwala
 * @email-id pxp1420330@utdallas.edu, sxm150231@utdallas.edu, sxj152930@utdallas.edu, vxj150030@utdallas.edu 
 * @version 1.0
 * 
 * This project focuses on analyzing twitter feeds and predicting the winner of US Elections 2016, utilizing a big data solution
 */

package com.utd.bigdata.electionus;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

/*
 * NLP class makes use of Stanford core NLP library for doing the sentiment analysis of the tweets
 * @variable: pipeline is an instance of class StanfordCoreNLP, which is used to find the sentiment of the tweet
 */
public class NLP {
	static StanfordCoreNLP pipeline;
	
	/*
	 * init method initializes the pipeline variable by passing the location of the properties file in the constructor of StanfordCoreNLP object
	 * @return: void
	 */
	public static void init() {
		pipeline = new StanfordCoreNLP("nlp/MyPropFile.properties"); //setting the properties for the tokenizer
	}
	
	/*
	 * findSentiment method accepts a String object and finds the sentiment of the context in that String
	 * @param : tweet is a String that contains the tweet of the user
	 * @return: int; the value of sentiment ranging from 0 to 4, where 0 = very negative, 1 = negative, 2 = neutral, 3 = positive, 4 = very positive
	 */
	public static int findSentiment(String tweet) {

		int mainSentiment = 0;
		if (tweet != null && tweet.length() > 0) {
			int longest = 0;
			Annotation annotation = pipeline.process(tweet);
			for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
				Tree tree = sentence.get(SentimentCoreAnnotations.AnnotatedTree.class);
				int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
				String partText = sentence.toString();
				if (partText.length() > longest) {
					mainSentiment = sentiment;
					longest = partText.length();
				}
			}
		}
		return mainSentiment;
	}
}
