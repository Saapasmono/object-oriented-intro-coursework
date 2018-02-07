package oope2017ht;

import fi.uta.csjola.oope.lista.LinkitettyLista;
import oope2017ht.tiedot.Hakemisto;
import oope2017ht.tiedot.Tiedosto;
import oope2017ht.tiedot.Tieto;

/**
 * Created by Joel Sallinen (sallinen.joel.i@student.uta.fi)
 */
public class Tulkki {
   //Luodaan Hakemisto-atribuutti nykyisen ja root-kansion seurantaan
   private Hakemisto current;
   private Hakemisto root;

   /**
    * Tulkin rakentaja. Nykyisesksi hakemistoksi määritetään root.
    */
   public Tulkki() {
      root = new Hakemisto();
      current = root;
   }

   /**
    * Passataan kutsu polun määritettlylle definePath-operaatioon. Parametrina nykyinen kansio
    * @return palauttaa String-muotoisen polun
    */
   public String definePath(){return definePath(current);}

   /**
    * Määritetään polku. Parametrina nykyinen Hakemisto. Tehdään tyhjä apumuuttuja, tarkistetaan
    * onko Hakemisto tyhjä. Jos ei, rakennetaan hakemistopolku
    * @param cur Nykyinen hakemisto
    * @return Palautetaan string-muotoisena uusi hakemistopolku
    */
   public String definePath(Hakemisto cur){
      String path = "";
      while (cur != null){
         path = cur.name()+ "/" + path;
         cur = cur.parentDir();
      }
      return path;
   }

   /**
    * Nimetään uudelleen. Tarkastetaan ettei ole saman nimisiä tai löytyykö uusi nimi jo.
    * Nimetään tieto uudella nimellä
    * @param oldName Vanha nimi joka muutetaan
    * @param newName Uusi haluttu nimi
    * @return Palautetaan tieto siitä, että onnistuiko uudelleen nimeäminen.
    */
   public boolean rename(String oldName, String newName) {

      if(oldName.equals(newName)){
         return false;
      }

      Tieto dir = current.hae(newName);

      if (dir != null){
         return false;
      }

      try{
         StringBuilder temp = new StringBuilder(newName);
         Tieto fetchFile = current.hae(oldName);
         fetchFile.name(temp);
         Tieto deleted = current.poista(newName);
         current.lisaa(fetchFile);
      }
      catch (Exception e){
         return false;
      }
      return true;

   }

   /**
    * Selvittää nykyisen hakemiston ja passaa sen toimintoon.
    */
   public void listRekursive() {
      listRekursive(current);
   }

   /**
    * Listataan rekursiivisesti kaikkien alihakemistojen ja tietojen listaus. Luodaan
    * väliaikainen lista, josta käydään alki kerrallaan läpi. Tulostetaan polku.
    * @param dir Parametrittoman listRekursiven lähettämä nykyinen kansio.
    */
   public void listRekursive(Hakemisto dir){
      LinkitettyLista rekursiveList = dir.sisalto();

      for (int i = 0; i < rekursiveList.koko(); i++) {
         System.out.print(definePath(dir));
         System.out.println(rekursiveList.alkio(i).toString());
         if (rekursiveList.alkio(i) instanceof Hakemisto){
            listRekursive((Hakemisto)rekursiveList.alkio(i));
         }
      }
   }

   //Aksessorit nykyhakemistolle.
   public void current(Hakemisto c){
      c.name();
   }

   public Hakemisto current(){
      return current;
   }

   /**
    * Poistaminen. Palauttaa tietona onnistuiko poistaminen
    * @param komento Haluttava poistettava tieto
    * @return Onnistuiko poistaminen
    */
   public boolean del(String komento) {
      Tieto deleted = current.poista(komento);

      if(deleted == null){
         return false;
      }
      else
         return true;

   }

   public void changeDir() {current = root;}

   /**
    * Siirrytään hakemistossa. Kahdella pisteellä siirrytään ylihakemistoon, muuten mennään eteenpäin.
    * @param dir Antaa parametrina käyttäjän komennon
    * @return Palauttaa onnistuiko siirtyminen
    */

   public boolean changeDir(String dir){
      if(dir.equals("..")){
         if(current==root){
            return false;
         }
         else {
            current = current.parentDir();
         }
      }
      else {
         Tieto move = current.hae(dir);
         if (move == null){
            return false;
         }
         else if(move instanceof Hakemisto){
            current = (Hakemisto)move;
         }
      }return true;
   }

   /**
    * Luodaan kansio nykyiseen kansioon. Annetaan nimi ja viite ylihakemistoon, eli nykyiseen.
    * @param komento Antaa parametrina käyttäjän komennon
    */
   public void mkDir(String komento) {

      try{
         StringBuilder k = new StringBuilder();
         k.append(komento);
         Hakemisto newDir = new Hakemisto(k, current);

         boolean ok = current.lisaa(newDir);
      }
      catch (IllegalArgumentException e){
         System.out.println("Error!");
      }
   }

   /**
    * Tiedoston luominen. Parametrinaan saa tiedoston nimen ja koon. Tarkastetaan onko jo olemassa.
    * Jos on, palautetaan false, muuten luodaan Tiedosto-olio ja lisätään Hakemistoon.
    * @param name Haluttu nimi tiedostolle
    * @param fileSize Kokonaisluku tiedostokoolle
    * @return Palautetaan tieto, onnistuiko luominen.
    */
   public boolean mkFile(String name, int fileSize) {
      boolean addOk = true;
      try{
         StringBuilder newName = new StringBuilder(name);
         Tieto folder = current.hae(name);

         if (folder !=null){
            return false;
         }

         Tiedosto file = new Tiedosto(newName, fileSize);
         addOk = current.lisaa(file);
         return addOk;
      }
      catch (IllegalArgumentException e){
         System.out.println("Error!");
         return addOk;
      }
   }

   /**
    * Kopioidaan tiedosto tai kansio. Tarkistetaan onko kansio vai tiedosto kyseessä.
    * @param forCopy Parametri kopioitavasta tiedostosta
    * @param copy Parametri kopioitavaksi tiedostoksi
    * @return Palautetaan tieto onnistumisesta
    */

   public boolean copy(String forCopy, String copy) {
      boolean added = false;
      Tieto copyThis = current.hae(forCopy);
      if(copyThis == null){
         return added;
      }
      else if(copyThis instanceof Tiedosto){
         Tiedosto new1 = new Tiedosto((Tiedosto)copyThis);
         StringBuilder newName= new StringBuilder(copy);
         new1.name(newName);

         added = current.lisaa(new1);
      }
      return added;
   }


   /**
    * Komento ls tulostaa nykyisen kansion sisällön. Tarkastetaan ettei kansio ole tyhjä ja tämän
    * jälkeen tulostetaan.
    */
   public void list() {
      LinkitettyLista temp = current.sisalto();

      if(temp != null){
         for (int i = 0; i < temp.koko(); i++) {
            System.out.println(temp.alkio(i).toString());
         }
      }
   }

   /**
    * Listaus tietyn tiedon tulostamiselle. Kyseessä joko syötteenä annettu tiedosto tai hakemisto.
    * @param komento Haluttu olio tulostukseen
    * @return Onnistuiko
    */
   public boolean list(String komento){
      Tieto toPrint = current.hae(komento);
      if(toPrint == null){
         return false;
      }
      else {
         System.out.println(toPrint.toString());
         return true;
      }
   }
}
