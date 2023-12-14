package com.loviselli.andrea.wordsAnalyzer.service;


import com.loviselli.andrea.wordsAnalyzer.model.WordsSimilarityScore;
import org.springframework.stereotype.Service;

/**
 * Service contentente la logica per calcolare, generare e restituire un'istanza di WordsSimilarityScore rispetto alle
 * due parole ricevute. Calcola prima la distanza di Levenshtein e poi la trasforma in punteggio da 0 a 1.
 */
@Service
public class WordsSimilarityScoreService {

    /**
     * Calcola i parametri del costruttore dell'istanza di "WordsSimilarityScore" per poterla generare e restituire.
     * prima la LevenshteinDistance invocando "calculateLevenshteinDistance", poi trasformata in punteggio con
     * "calculateScoreFromLevenshteinDistance". Successivamente implementa una descrizione della procedura invocando
     * "writeDescription".
     * @param wordOne la prima parola del confronto.
     * @param wordTwo la seconda parola del confronto.
     * @return istanza di "WordsSimilarityScore" calcolata.
     */
    public WordsSimilarityScore generateSimilarityScore(String wordOne, String wordTwo) {
        int levenshteinDistance = calculateLevenshteinDistance(wordOne, wordTwo);
        double similarityScore = calculateScoreFromLevenshteinDistance(levenshteinDistance, wordOne, wordTwo);
        String description = writeDescription(similarityScore);

        return new WordsSimilarityScore(wordOne, wordTwo, similarityScore, description);
    }


    /**
     * Calcola la distanza di Levenshtein tra due parole, ovvero l'indice di dissimilarità.
     * La distanza di Levenshtein rappresenta il numero minimo di passaggi (inserimenti,
     * eliminazioni o sostituzioni) necessari per rendere due parole uguali.
     *
     * Utilizza l'approccio di programmazione dinamica (Dynamic Programming) per evitare
     * il ricalcolo delle sottostringhe già analizzate. I risultati vengono memorizzati
     * nella tabella "tableDP" per riutilizzarli durante il calcolo successivo.
     *
     * @param wordOne la prima parola del confronto.
     * @param wordTwo la seconda parola del confronto.
     * @return l'ultima "box" della tabella -> la levenshteinDistance che verrà poi tramutata in punteggio.
     */
    private int calculateLevenshteinDistance(String wordOne, String wordTwo) {
        final int m = wordOne.length();
        final int n = wordTwo.length();

        int[][] tableDP = new int[m + 1][n + 1];
        //compilazione dei valori già calcolabili della tabella
        for (int i = 0; i <= m; i++) {
            tableDP[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            tableDP[0][j] = j;
        }

        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (wordOne.charAt(i - 1) == wordTwo.charAt(j - 1)) {  //se le due subString sono uguali
                    tableDP[i][j] = tableDP[i - 1][j - 1];
                } else {                                               //se sono diverse uso i valori già calcolati
                    int topLeftOfTheBox = tableDP[i - 1][j - 1];
                    int topOfTheBox = tableDP[i - 1][j];
                    int leftOfTheBox = tableDP[i][j - 1];
                    tableDP[i][j] = Math.min(topLeftOfTheBox, Math.min(topOfTheBox, leftOfTheBox)) + 1;
                }
            }
        }
        return tableDP[m][n];
    }

    /**
     * Calcola il punteggio basato sulla distanza di Levenshtein tra due parole.
     * Maggiore sarà il punteggio, maggiore sarà la somiglianza tra le parole.
     * Un punteggio di 1.0 indica parole identiche.
     *
     * @param levenshteinDistance l'indice di dissimilarità
     * @param wordOne la prima parola del confronto
     * @param wordTwo la seconda parola del confronto
     * @return il punteggio normalizzato da 0 a 1 sulla base della distanza di Levenshtein
     */
    private double calculateScoreFromLevenshteinDistance(int levenshteinDistance, String wordOne, String wordTwo) {
        int maxLength = Math.max(wordOne.length(), wordTwo.length());
        double normalizedDistance = (double) levenshteinDistance / maxLength;

        return 1 - normalizedDistance;
    }

    private String writeDescription(double similarityScore) {
        return "SCORE SIMILARITÀ = " + String.format("%.3f", similarityScore) + " / 1.0";
    }
}
