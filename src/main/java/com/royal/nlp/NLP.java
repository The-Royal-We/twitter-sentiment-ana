package com.royal.nlp;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class NLP {
    static StanfordCoreNLP pipeline;
    private static Logger logger = LogManager.getLogger(NLP.class);

    public static void init() {
        pipeline = new StanfordCoreNLP("nlp.properties");
    }

    /**
     * @param tweet
     * @return A value indicating whether or not there is a positive sentiment. The greater
     * the more positive
     */
    public static int findSentiment(String tweet) {
        int mainSentiment = 0;
        if (tweet != null && tweet.length() > 0) {
            int longest = 0;
            Annotation annotation = pipeline.process(tweet);
            for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
                Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
                int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
                String partText = sentence.toString();
//                logger.info(partText);
                if (partText.length() > longest) {
                    mainSentiment = sentiment;
                    longest = partText.length();
                }
            }
        }
        return mainSentiment;
    }
}
