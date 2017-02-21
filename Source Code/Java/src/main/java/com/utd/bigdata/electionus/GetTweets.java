/* Surbhi Mathur (sxm150231)
 * @version 1.0
 * This project focuses on analyzing twitter feeds and predicting the winner of US Elections 2016, utilizing a big data solution
 */
package com.utd.bigdata.electionus;

import java.util.ArrayList;
import java.util.Arrays;
import twitter4j.FilterQuery;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

/*
 * GetTweets class uses the Twitter credentials of the user to query for feeds based on the names of the politicians who are running in the race for contention of being the next US president
 */

public class GetTweets {
	
	/*
	 * getTweet method uses the twitter credentials of the user to do a query on the topics - US Elections 2016 and the individual candidates
	 * @return: void
	 */
	public void getTweet(){
		ConfigurationBuilder configBuilder = new ConfigurationBuilder();
		configBuilder.setOAuthConsumerKey("<consumerKey>");
		configBuilder.setOAuthConsumerSecret("<consumerSecret>");
		configBuilder.setOAuthAccessToken("<accessToken>");
		configBuilder.setOAuthAccessTokenSecret("<accessTokenSecret>");
	
		TwitterListener twitterListener = new TwitterListener();
		NLP.init();
		TwitterStream streamTwitter
		        = new TwitterStreamFactory(configBuilder.build()).getInstance();

		// Registering the Listener
		streamTwitter.addListener(twitterListener);

		FilterQuery query = new FilterQuery();
		ArrayList<String> searchFor = new ArrayList<String>();
		String[] topics = {"#Elections2016","#DonaldTrump", "Donald Trump", "Hillary Clinton", "Bernie Sanders", "#HillaryClinton","#BernieSanders", "Ted Cruz", "John Kasich", "Gary Johnson", "Jill Stein", " Jesse Ventura"};	//Searching tweets based on the names of the politicians
		searchFor.addAll(Arrays.asList(topics));
		query.track(searchFor.toArray(new String[searchFor.size()]));

		streamTwitter.filter(query);
	}
	
	/*
	 * main method creates an instance of class GetTweets and launches the application
	 * @return: void
	 */
	public static void main(String[] args){
		GetTweets getTweets = new GetTweets();
		getTweets.getTweet();
	}
}
