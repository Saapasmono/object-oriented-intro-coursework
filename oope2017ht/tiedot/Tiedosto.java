package oope2017ht.tiedot;

/**
 * Created by Joel Sallinen (sallinen.joel.i@student.uta.fi)
 */
public class Tiedosto extends Tieto{
   private int fileSize;

   /**
    * Rakentaja tiedostolle. Saa parametrina nimen ja tiedostokoon.
    * @param newName Haluttu nimi tiedostolle
    * @param fileSize Haluttu tiedostokoko
    * @throws IllegalArgumentException
    */
    public Tiedosto(StringBuilder newName, int fileSize) throws IllegalArgumentException {
       super(newName);
       koko(fileSize);
    }

   /**
    * Kopiorakentaja. Saa pelkän tiedosto olion syötteenä. Tämmä lähetetään yliluokalle.
    * @param name Haluttavan kopioitavan tiedoston nimi
    */
   public Tiedosto(Tiedosto name){
       super(name);
       koko(name.koko());
    }

   /**
    * Tiedostokoon asettaja. Tarkistetaan että koko on suurempi kuin yksi.
    * @param s
    * @throws IllegalArgumentException
    */
   public void koko(int s) throws IllegalArgumentException{
      if(s<1){
         throw new IllegalArgumentException();
      }
      else
         fileSize = s;
   }

   public int koko(){
      return fileSize;
   }

   /**
    * ToString-metodin ylikirjoitus
    * @return
    */
   @Override
   public String toString() {
      return super.toString() + " " + Integer.toString(fileSize);
   }
}
