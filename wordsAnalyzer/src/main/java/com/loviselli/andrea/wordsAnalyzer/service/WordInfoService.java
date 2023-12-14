package com.loviselli.andrea.wordsAnalyzer.service;

import com.loviselli.andrea.wordsAnalyzer.model.WordInfo;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Service contentente la logica per generare e restituire un'istanza di "WordsInfo".
 * Una volta generata viene passata tra i metodi e ne vengono compilati i campi.
 */
@Service
public class WordInfoService {

    /**
     * Genera un'istanza di "WordInfo". Nel suo costruttore passa "word", calcolando già così due campi:
     * this.word = word && this.charactersNumber = word.length().
     * @param word la parola da cui raccogliere informazioni.
     * @return calculateWordInfo(wordInfo).
     */
    public WordInfo generateWordInfo(String word) {
        WordInfo wordInfo = new WordInfo(word);
        return calculateWordInfo(wordInfo);
    }

    /**
     * @param wordInfo l'istanza di "WordInfo" da passare nei tre metodi interni per calcolarne i restanti campi.
     * @return l'istanza di "WordInfo" calcolata.
     */
    private WordInfo calculateWordInfo(WordInfo wordInfo) {

        calculateAndSetIfPalindrome(wordInfo);
        calculateAndSetVowelsAndConsonants(wordInfo);
        calculateAndSetCharactersOccurrences(wordInfo);

        return wordInfo;
    }

    /**
     * "wordToCheck" = Recupera la parola da analizzare dall'istanza di "WordInfo".
     * "reversedWord" = la inverte con StringBuilder.reverse();
     * Invoca il metodo setter wordInfo.setPalindrome() e lo setta true || false in base al confronto.
     * @param wordInfo da cui recuperare la parola e di cui inizializzare l'attributo isPalindrome.
     */
    private void calculateAndSetIfPalindrome(WordInfo wordInfo) {
        String wordToCheck = wordInfo.getWord();
        StringBuilder reversedWord = new StringBuilder(wordToCheck).reverse();

        wordInfo.setPalindrome(wordToCheck.contentEquals(reversedWord));
    }

    /**
     * Calcola tramite un ciclo for-each quante vocali e quante consonanti ha la parola presa da "wordInfo".
     * Usa i setter di "wordInfo" per inizializzare gli attributi vowelsNumber && consonantNumber.
     * @param wordInfo da cui recuperare la parola e di cui inizializzare gli attributi vowelsNumber e consonantNumber.
     */
    private void calculateAndSetVowelsAndConsonants(WordInfo wordInfo) {
        int consonantCounter = 0;
        int vowelsCounter = 0;

        for(char c : wordInfo.getWord().toCharArray()) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') vowelsCounter++;
            else consonantCounter++;
        }

        wordInfo.setVowelsNumber(vowelsCounter);
        wordInfo.setConsonantsNumber(consonantCounter);
    }

    /**
     * Calcola treamite un ciclo for-each le occorrenze di ogni carattere nella parola da analizzare e le aggiunge alla
     * HashMap "charactersOccurrences".
     * Usa il metodo setter "setCharactersOccurrences()" di "wordInfo" e passa come parametro "characterOccurrences"
     * @param wordInfo
     */
    private void calculateAndSetCharactersOccurrences(WordInfo wordInfo) {
        HashMap<Character, Integer> charactersOccurrences = new HashMap<>();

        for(char c : wordInfo.getWord().toCharArray()) {
            if (charactersOccurrences.containsKey(c)) {
                charactersOccurrences.put(c, charactersOccurrences.get(c) + 1);
            } else {
                charactersOccurrences.put(c, 1);
            }
        }
        wordInfo.setCharactersOccurrences(charactersOccurrences);
    }
}
