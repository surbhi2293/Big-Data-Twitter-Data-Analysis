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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import com.google.common.io.Files;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

/*
 * TwitterListener class implements StatusListener class, and provides the functionality of contiuously streaming live twitter feeds
 * @variable: countFile is used to keep count of the new File, which is created after every 100 MB of data download
 * @variable: tweetNumber is used to keep track of the number tweets being downloaded 
 */
public class TwitterListener implements StatusListener {
	public static long countFile = 1;
	public static BigInteger tweetNumber = BigInteger.ONE;
	
	/*
	 * onException method handles any excpetion that may arise while downloading the twitter feeds
	 * @return: void
	 */
	public void onException(Exception ex) {
		ex.printStackTrace();
	}
	
	/*
	 * onStatus method receives the data from twitter in JSON format and parses it according to the format that is needed. It also creates a file and keeps on appending all the data to it
	 * @parameter: status; It is an instance of Status class, which is basically the data sent by twitter in JSON format
	 * @return: void
	 */
	public void onStatus(Status status) {
		if (status.getUser().getLang().equalsIgnoreCase("en") || status.getUser().getLang().equalsIgnoreCase("en_US")) {
			tweetNumber = tweetNumber.add(BigInteger.ONE);
			String modifyTweet = status.getText().replaceAll("\\s+"," ");
			int sentiment = NLP.findSentiment(modifyTweet);
			String twitterStream = tweetNumber + "\t" + status.getUser().getScreenName() + "\t" + modifyTweet
					+ "\t" + status.getCreatedAt()+ "\t" + status.getWithheldInCountries() + "\t" + status.getPlace() + "\t" + status.getGeoLocation() + "\t" + sentiment;

			try {
				File file = new File("tweets.txt");
				if (!file.exists()) {
					file.createNewFile();
				}
				double bytes = file.length();
				double kilobytes = (bytes / 1024);
				double megabytes = (kilobytes / 1024);
				if (megabytes > 100 * countFile) {
					File newFile = new File("tweets" + countFile + ".txt");
					Files.copy(file, newFile);
					countFile++;
				}
				FileWriter fileWritter = new FileWriter(file.getName(), true);
				BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
				bufferWritter.write(twitterStream + "\n");
				bufferWritter.close();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * onDeletionNotice method is not being used here. It is only required to be overridden since the TwitterListener class implements StatusListener interface
	 * @return: void
	 */
	public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
	}
	
	/*
	 * onTrackLimitationNotice method is not being used here. It is only required to be overridden since the TwitterListener class implements StatusListener interface
	 * @return: void
	 */
	public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
	}

	/*
	 * onScrubGeo method is not being used here. It is only required to be overridden since the TwitterListener class implements StatusListener interface
	 * @return: void
	 */
	public void onScrubGeo(long userId, long upToStatusId) {
	}

	/*
	 * onStallWarning method is not being used here. It is only required to be overridden since the TwitterListener class implements StatusListener interface
	 * @return: void
	 */
	public void onStallWarning(StallWarning warning) {
	}
}
