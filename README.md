# Super Peach project readme

Email dei componenti:

- maurizio.capuano2@studio.unibo.it
- patrick.sbrighi@studio.unibo.it
- miriam.sonaglia@studio.unibo.it
- eraldo.tabaku@studio.unibo.it

Funzionalità necessarie (tutte completate):

- Schermata di avvio e menù principale di gioco
- Implementazione della mappa preposta a giocare il livello base
- Sviluppo di 1 livello inedito con finale sconfitta del boss
- Implementazione delle funzionalità di movimento di base
- Implementazione dei classici tre power-ups caratteristici e del classico blocco "punto interrogativo"
- Inserimento di due tipologie di nemici di ostacolo alla vittoria
- Sistema di tracciamento del punteggio e delle vite durante lo svolgimento della partita

Funzionalità opzionali aggiuntive:

- Ampliamento del menù di gioco per permettere l'interfacciamento con le seguenti funzionalità
- Possibilità di giocare diversi personaggi e conseguente aggiunta di abilità caratteristiche per ognuno di loro
- Ampliamento del bacino di power-ups disponibili
- Sistema di leaderboard e corrispondente salvataggio su file
- Ampliamento del bacino di nemici (Fatto)
- Inserimento di altri blocchi interattivi in aggiunta al blocco di power-up
- Inserimento di una seconda modalità di gioco che, come unica differenza con la prima, prevede la generazione automatizzata della mappa di gioco

Principali sfide da affrontare:

- Coordinazione nella partecipazione concorrente alla repository remota (corretto utilizzo del DVCS)
- Gestione delle collisioni tra personaggi, oggetti e ambiente
- Implentazione di una interfaccia grafica reattiva tramite l'utilizzo delle librerie di GUI e della classe Thread
- Gestione delle entità attive del gioco e corretta gestione degli input istantanei

Suddivisione di massima del lavoro:

- Capuano: Codifica e implementazione della mappa di gioco con generazione e comportamento dei blocchi e gestione dell'evento di fine livello
- Sbrighi: Implementazione del personaggio principale con relativa grafica, gestione dell'input del suo movimento e collisioni
- Sonaglia: Implementazione dei power-ups, della schemata di avvio e del menù principale
- Tabaku: Implementazione dei nemici con relativa interfaccia grafica, movimenti e collisioni con l'ambiente