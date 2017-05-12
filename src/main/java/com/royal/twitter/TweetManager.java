package com.royal.twitter;

import com.royal.twitter.util.TweetUtil;
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
            query.setCount(100);
            QueryResult result;
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                    if(!tweet.isRetweet() && tweet.getLang().equals("en")) {
                        String text = tweet.getText();
                        TweetUtil.sanitizeTweets(text);
                        tweetList.add(text);
                    }

                }
            } while ((query = result.nextQuery()) != null);
        } catch (Exception e) {
            logger.error("Failed to retrieve tweets", e);
            System.out.println("Failed to retrieve tweets");
        }
        return tweetList;
    }
}
