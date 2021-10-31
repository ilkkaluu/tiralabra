# Toteutus  

## Rakenne  
Ohjelmaa ajetaan main.java-luokasta käsin. Ohjelmaan syötetään käytettävä algoritmi ja labyrintin pinta-ala.  

Algoritmit sijaitsevat kansiossa algo.  

Labyrintit muodostetaan util-kansion apuluokkia. PPiste-luokka liittyy satunnaistetun Primin algoritmin avulla muodostettavaan labyrinttiin ja KPiste taas satunnaistetun Kruskalin avulla muodostettavaan labyrinttiin.  

## Suorituskyky- ja O-analyysivertailu  
Suorituskykyä mitataan millisekuntiajastimella. Kruskalin suorituskyky heikentyy suuressa labyrintissa huomattavasti optimointiongelmien takia, kun taas Primin suorituskyky säilyy koosta riippumatta O(n^2).  
  
## Puutteet ja parannusehdotukset  
* Kruskalin algoritmi tarvitsisi optimointia, sillä tällä hetkellä se on huomattavasti hitaampi kuin Primin algoritmi.  
* UI on tällä hetkellä vain ASCII-pohjainen. 