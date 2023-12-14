package com.loviselli.andrea.wordsAnalyzer.controller;

import com.loviselli.andrea.wordsAnalyzer.model.WordInfo;
import com.loviselli.andrea.wordsAnalyzer.model.WordsSimilarityScore;
import com.loviselli.andrea.wordsAnalyzer.service.WordInfoService;
import com.loviselli.andrea.wordsAnalyzer.service.WordsSimilarityScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller contenente due endpoint. "generateWordInfo" riceve una parola e ne restituisce le informazioni,
 * "generateSimilarityScore" riceve due parole e restituisce uno score di similarità.
 * Vi è poi un metodo "handleIllegalArgumentException" che cattura  e gestisce le eccezioni lanciate dai due endpoint
 */
@RestController
@RequestMapping("/api/words")
public class WordsAnalyzerController {

    private final WordInfoService wordInfoService;
    private final WordsSimilarityScoreService wordsSimilarityScoreService;

    @Autowired
    public WordsAnalyzerController(WordInfoService wordInfoService, WordsSimilarityScoreService wordsSimilarityScoreService) {
        this.wordInfoService = wordInfoService;
        this.wordsSimilarityScoreService = wordsSimilarityScoreService;
    }

    @GetMapping("/{word}")
    public WordInfo generateWordInfo(@PathVariable("word") String word) {

        if (word == null || word.isEmpty() || word.isBlank()) {
            throw new IllegalArgumentException("ERROR: la parola non può essere vuota!");
        }
        if (!word.matches("[a-z]+")) {
            throw new IllegalArgumentException("ERROR: "+ word +" può contenere solo caratteri alfabetici minuscoli!");
        }

        return this.wordInfoService.generateWordInfo(word);
    }

    @PostMapping("/{wordOne}/{wordTwo}")
    public WordsSimilarityScore generateSimilarityScore(@PathVariable("wordOne") String wordOne,
                                                        @PathVariable("wordTwo") String wordTwo) {

        if(wordOne == null || wordOne.isEmpty() || wordOne.isBlank()) {
            throw new IllegalArgumentException("ERROR: la prima parola è vuota!");
        } else if (wordTwo == null || wordTwo.isEmpty() || wordTwo.isBlank()) {
            throw new IllegalArgumentException("ERROR: la seconda parola è vuota!");
        }
        if (!wordOne.matches("[a-z]+")) {
            throw new IllegalArgumentException("ERROR:"+ wordOne +" può contenere solo caratteri alfabetici minuscoli!");
        } else if (!wordTwo.matches("[A-z]+")) {
            throw new IllegalArgumentException("ERROR:"+ wordTwo +" può contenere solo caratteri alfabetici minuscoli!");
        }

        return this.wordsSimilarityScoreService.generateSimilarityScore(wordOne, wordTwo);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException e) {
        String errorMessage = e.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

}
