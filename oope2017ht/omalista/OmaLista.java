package oope2017ht.omalista;

import apulaiset.Ooperoiva;
import fi.uta.csjola.oope.lista.LinkitettyLista;

/**
 * Created by Joel Sallinen (sallinen.joel.i@student.uta.fi)
 */
public class OmaLista extends LinkitettyLista implements Ooperoiva {

   /**
    * Haku. Koitetaan löytää haluttu olio
    * @param haettava Haettava tieto
    * @return Palautta joko null, tai löydetyn.
    */
   @Override
   public Object hae(Object haettava) {
      Object temp = null;

      //Etsitään listasta mahdollisesti olemassa olevaa alkiota
      if (koko() != 0 && haettava != null) {
         for (int i = 0; i < koko(); i++) {
            if (alkio(i).equals(haettava))
               temp = alkio(i);
         }
      }
      return temp;
   }


   /**
    * Lisäys. Tarkastetaan ettei ole tyhjä ja tämän jälkeen haetaan oikea paikka listassa.
    * @param uusi viite olioon, jonka luokan tai luokan esivanhemman oletetaan
    * toteuttaneen Comparable-rajapinnan.
    * @return palautetaan tieto onnistumisesta
    */
   @SuppressWarnings("unchecked")
   @Override
   public boolean lisaa(Object uusi) {
      //apubooleanit
      boolean add = false;
      boolean sameName = false;

      //Tarkastetaan onko tyhjä
      if (uusi != null) {
         if (koko() == 0) {
            lisaaAlkuun(uusi);
            add = true;
         }

         // Jos ei, vertaillaan onko saman nimisiä
         else {
            for (int i = 0; i < koko(); i++) {
               Comparable vertaa = (Comparable)uusi;
               if (vertaa.equals(alkio(i)))
                  sameName = true;
            }
            // Jos ei, haetaan oikea paikka.
            if (!sameName) {
               boolean positionOK = false;
               int count = 0;
               while (!positionOK) {
                  Comparable vertaa = (Comparable)uusi;
                  int alphabetOrder = vertaa.compareTo(alkio(count));

                  if (alphabetOrder < 0) {
                     add = this.lisaa(count, uusi);
                     positionOK = true;
                  }
                  else if (count == koko() - 1 && !positionOK) {
                     this.lisaaLoppuun(uusi);
                     positionOK = true;
                     add = true;
                  }
                  else
                     count++;

               }
            }
         }
      }
      return add;
   }


   /**
    * Listalta poisto .
    * @param poistettava Poistettava
    * @return palautetaan poistettu tai tyhjä
    */
   @Override
   public Object poista(Object poistettava) {
      Object apu = null;

      // Verrataan mahdollisesti olemassa oleviin alkoihin jonka poistaa
      if (koko() != 0 && poistettava != null) {
         for (int i = 0; i < koko(); i++) {
            if (alkio(i).equals(poistettava)) {
               apu = alkio(i);
               poista(i);
               return apu;
            }
         }
      }
      return apu;
   }
}
