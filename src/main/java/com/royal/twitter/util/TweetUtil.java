package com.royal.twitter.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TweetUtil {
    private static String URL_REGEX = "((https?|ftp|gopher|telnet|file|Unsure|http)" +
            ":((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";

    private static Pattern URL_PATTERN = Pattern.compile(URL_REGEX, Pattern.CASE_INSENSITIVE);
    public static String sanitizeTweets(String tweet) {
        Matcher m = URL_PATTERN.matcher(tweet);
        int index = 0;
        while(m.find()) {
            tweet = tweet.replace(m.group(index), "").trim();
            index++;
        }
        return tweet;
    }

}
