package com.loviselli.andrea.wordsAnalyzer.model;

public class WordsSimilarityScore {
    private String wordOne;
    private String wordTwo;
    private double similarityScore;
    private String description;

    public WordsSimilarityScore(String wordOne, String wordTwo, double similarityScore, String description) {
        this.wordOne = wordOne;
        this.wordTwo = wordTwo;
        this.similarityScore = similarityScore;
        this.description = description;
    }

    public String getWordOne() {
        return wordOne;
    }

    public void setWordOne(String wordOne) {
        this.wordOne = wordOne;
    }

    public String getWordTwo() {
        return wordTwo;
    }

    public void setWordTwo(String wordTwo) {
        this.wordTwo = wordTwo;
    }

    public double getSimilarityScore() {
        return similarityScore;
    }

    public void setSimilarityScore(double similarityScore) {
        this.similarityScore = similarityScore;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
