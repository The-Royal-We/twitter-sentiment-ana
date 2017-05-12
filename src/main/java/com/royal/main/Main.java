package com.royal.main;

import com.royal.nlp.NLP;
import com.royal.twitter.TweetManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Application START");

        System.out.println("Enter in a topic to pull in: ");
        Scanner scanner = new Scanner(System.in);

        String topic = null;

        try {
           topic = scanner.next();
        } catch (Exception e) {
            logger.error("Error in retrieving topic from IO", e);
        } finally {
            scanner.close();
        }
        if(topic == null) {
            System.exit(-1);
        }

        logger.info("Retrieving tweets for given topic");
        List<String> tweets = TweetManager.getTweets(topic);
        if (tweets == null) {
            System.out.println("Error in retrieving your tweets. Exiting....");
            System.exit(-1);
        } else {
            if (!tweets.isEmpty()) {
                logger.info("Processing tweets...");
                processTweets(tweets);
            }
        }
    }

    private static void processTweets(List<String> tweets) {

        NLP.init();

        int index = 0;
        for(String tweet : tweets) {
            int sentiment = NLP.findSentiment(tweet);
            System.out.println("[" + index + "]:" + sentiment);
            index++;
        }
    }
}
