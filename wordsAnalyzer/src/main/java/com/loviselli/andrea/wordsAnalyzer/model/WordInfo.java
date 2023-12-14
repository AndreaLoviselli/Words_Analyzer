package com.loviselli.andrea.wordsAnalyzer.model;

import java.util.HashMap;

public class WordInfo {
    private String word;
    private boolean isPalindrome;
    private int charactersNumber;
    private int vowelsNumber;
    private int consonantsNumber;
    private HashMap<Character,Integer> charactersOccurrences;

    public WordInfo(String word) {
        this.word = word;
        this.charactersNumber = word.length();
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public boolean isPalindrome() {
        return isPalindrome;
    }

    public void setPalindrome(boolean palindrome) {
        isPalindrome = palindrome;
    }

    public int getCharactersNumber() {
        return charactersNumber;
    }

    public void setCharactersNumber(int charactersNumber) {
        this.charactersNumber = charactersNumber;
    }

    public int getVowelsNumber() {
        return vowelsNumber;
    }

    public void setVowelsNumber(int vowelsNumber) {
        this.vowelsNumber = vowelsNumber;
    }

    public int getConsonantsNumber() {
        return consonantsNumber;
    }

    public void setConsonantsNumber(int consonantsNumber) {
        this.consonantsNumber = consonantsNumber;
    }

    public HashMap<Character, Integer> getCharactersOccurrences() {
        return charactersOccurrences;
    }

    public void setCharactersOccurrences(HashMap<Character, Integer> charactersOccurrences) {
        this.charactersOccurrences = charactersOccurrences;
    }
}
