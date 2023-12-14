# WordsAnalyzer

## Applicazione Spring Boot che riceve una parola e la analizza o più parole e ne calcola lo score di similarità

L'applicazione è costruita attorno al pattern MVC ed è divisa in tre directories: controller, model e service.


### Controller
È uno per due endpoint. Invocheranno ciascuno un service associato dividendosi così i due scopi dell'applicazione. Oltre ai due endpoint è poi presente un metodo "handleIllegalArgumentException" che raccoglie le eccezioni lanciate dai controlli presenti.
### Model
Composto da due classi "WordInfo" e "WordsSimilarityScore". Entrambe con un costruttore definito per essere generate, compilate e  restituite nel service.
### Service
Vi sono infine due service "WordInfoService" e "WordsSimilarityScore" service. Entrambi genereranno un'istanza dell'oggetto richiesto, la compileranno a partira dalla/e parola/e e la restituiranno.

## L' ALGORITMO DI LEVENSHTEIN
È l'algoritmo scelto per calcolare lo score di similarità tra due parole. Sebbene calcoli la distanza tra due parole intesa come numero di passaggi necessari per renderle uguali, risulta uno dei più adatti data la mole di informazioni sul web e di casi di utilizzo. Semplicemente è necessario anche un metodo che trasformi in punteggio da 0 a 1 quello che sarebbe altrimenti una distanza da n passaggi a 0. 

## Framework e Librerie utilizzate
* SpringBoot 3.2.0    
* Gradle - Groovy   
* Spring Web          
* JDK 17    

## TESTAMI FACILMENTE
Per semplificare il testing ho condiviso qui direttamente la libreria **Postman** che ho utilizzato. clicca [qui!](https://progettosbloviselliandrea.postman.co/workspace/New-Team-Workspace~b2d02203-2a9a-47ed-867e-79b9a8e2c608/collection/27730869-0b39419f-0fb8-4286-98e5-d2141fad1096?action=share&creator=27730869)

**P.S** -> per fare chiamate da broswer ad un local host con post man serve scaricare il desktop agent!



