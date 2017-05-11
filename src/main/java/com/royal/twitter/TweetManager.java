package com.royal.twitter;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import twitter4j.*;

import java.util.ArrayList;
import java.util.List;

public class TweetManager {
    private static Logger logger = LogManager.getLogger(TweetManager.class);

    public static ArrayList<String> getTweets(String topic) {
        Twitter twitter = new TwitterFactory().getInstance();
        ArrayList<String> tweetList = new ArrayList<>();
        try {
            Query query = new Query(topic);
            QueryResult result;
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                    tweetList.add(tweet.getText());
                }
            } while ((query = result.nextQuery()) != null);
        } catch (Exception e) {
            logger.error("Failed to retrieve tweets", e);
            System.out.println("Failed to retrieve tweets");
        }
        return tweetList;
    }
}
