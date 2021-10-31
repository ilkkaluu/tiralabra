# Käyttöohje
## Ohjelman kloonaus  
Komentorivikomennolla seuraavilla menetelmillä:  

HTTPS:  
>~$ git clone https://github.com/ilkkaluu/tiralabra.git     

SSH:  
>~$ git@github.com:ilkkaluu/tiralabra.git  

GitHub CLI:  
>gh repo clone ilkkaluu/tiralabra  

## Ohjelman asennus  
Ladattu ohjelma voidaan ajaa avaamalla ohjelma esimerkiksi NetBeansissa.  

## Ohjelman käyttö  
Ohjelma toimii tekstikäyttöliittymässä. Ennen ohjelman käynnistymistä käyttäjä voi säätää labyrintin kokoja. Lataushetkellä ohjelmassa on valmiiksi syötettynä 50x50 labyrintin arvot.      
>RPrim.suorita(100, 100);  

Esimerkiksi tällä ohjelma tulostaa Primin algoritmillä tuotetun labyrintin, jonka mitat ovat 100x100. 

>RKruskal kruskalLabyrintti = new RKruskal(100);  

Ja tällä ohjelma tulostaa Kruskalin algoritmillä tuotetun labyrintin, jonka mitat ovat 100x100.   
    
Suorituksen jälkeen ohjelma muodostaa algoritmin mukaisen labyrintin, joka tulostetaan sekä ilmoittaa kuluneen ajan millisekunneissa.  
