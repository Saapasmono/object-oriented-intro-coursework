package oope2017ht.tiedot;

import apulaiset.*;
import fi.uta.csjola.oope.lista.LinkitettyLista;
import oope2017ht.omalista.OmaLista;

/**
 * Created by Joel Sallinen (sallinen.joel.i@student.uta.fi)
 */
public class Hakemisto extends Tieto implements Komennettava<Tieto>{
   private Hakemisto parentDir;
   private OmaLista content;

   public Hakemisto (){
      super();
      parentDir(null);
      content = new OmaLista();
   }

   /**
    * Hakemiston luominen, passataan ylikansion nimi ja OmaListan-sisältämät tiedot
    * @param h Hakemistoksi nimettävä nimi
    * @param main osoitta ylikansion
    * @throws IllegalArgumentException
    */
   public Hakemisto(StringBuilder h, Hakemisto main) throws IllegalArgumentException{
      // Haetaan hakemisto
      super(h);

      //Luodaan hakemisto omallalistalla
      content = new OmaLista();

      //Annetaan hakemistolle tiedoksi ylihakemiston tieto
      setParentDir(main);
   }


   //Korvataan LinkitettyListan metodit
   @Override
   public LinkitettyLista sisalto() {
      return content;
   }


   /**
    * Haku sisällöstä.
    * @param name Haettava nimi
    * @return Palauttaa viitteen olioon
    */
   @Override
   public Tieto hae(String name) {
      Object ref = content.hae(name);
      return (Tieto)ref;
   }


   /**
    * OmaListalle lisätään luotu kansio. Tarkastetaan ettei ole duplikaatteja.
    * Mahdollista tarkastella tyhjääkin.
    * @param toAdd Lisättävä parametri. Tieto-tyyppinen
    * @return Onnistuiko
    */
   @Override
   public boolean lisaa(Tieto toAdd) {
      boolean sameNode = false;
      boolean addOK = false;
      boolean omaListaAddOK;

      if(toAdd!=null){
         for (int i = 0; i < content.koko(); i++) {
            if (toAdd.equals(content.alkio(i)))
               sameNode = true;
         }
         if(!sameNode){
            omaListaAddOK = content.lisaa(toAdd);
            if (omaListaAddOK)
               addOK=true;
         }
      }return addOK;
   }


   /**
    * Poistetaan tieto listalta. Palauttaa poistetun tiedon tai nullin.
    * @param name Poistettava tiedosto tai hakemisto
    * @return Palauttaa nullin tai poistetun tiedon
    */
   @Override
   public Tieto poista(String name) {
      if(name != null){
         StringBuilder temp = new StringBuilder(name);
         Hakemisto deleted = new Hakemisto(temp, null);
         return (Tieto)content.poista(temp);
      }
      else
         return null;
   }


   //Aksessorit
   public Hakemisto parentDir(){
      return parentDir;
   }

   public void parentDir(Hakemisto mainDirectory) {
      parentDir = mainDirectory;
   }

   public void content(Tieto r){
      content.lisaa(r);
   }

   //toString

   @Override
   public String toString() {
      return super.toString()+"/ "+content.koko();
   }

   private void setParentDir(Hakemisto parentdir){
      this.parentDir(parentdir);
   }
}
