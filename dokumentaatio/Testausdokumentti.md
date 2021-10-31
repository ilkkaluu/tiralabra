# Testaus  

## Ohjelman testaus  
Varsinaista ohjelman testausta ei tällä hetkellä valitettavasti ole.  

## Mitä on testattu, miten, ja minkälaisilla syötteillä?  
### Suorituskyky   
* Tehokkutta testataan ajastimella, joka mittaa kuluneen ajan millisekunneissa. Tulos näytetään ohjelman suorituksen päätyttyä.  
* Tässä muutamia suoritusaikoja eri kokoisilla labyrinteillä. Tulokset ovat suuntaa antavia, sillä uskon, että optimointia voisi vielä tehdä.  
##### 10x10  
Prim: 4ms  
Kruskal: 3ms  

##### 25x25  
Prim: 30ms      
Kruskal: 21ms       

##### 50x50
Prim: 127ms    
Kruskal: 198ms     

##### 75x75  
Prim: 150ms  
Kruskal: 1800ms  

##### 100x100  
Prim: 250ms    
Kruskal: 15408ms  

* Voidaan todeta, että satunnaistettu Kruskal on nopeampi pienissä labyrinteissä, kun taas satunnaistettu Prim on nopeampi isoissa. Osa jälkimmäisistä tuloksista voidaan tosin selittää optimoinnilla, sillä uskon, Kruskalia saisi vielä huomattavasti nopeammaksi.  
