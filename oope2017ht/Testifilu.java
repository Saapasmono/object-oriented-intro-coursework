package oope2017ht;


import fi.uta.csjola.oope.lista.LinkitettyLista;

import oope2017ht.tiedot.Hakemisto;
import oope2017ht.tiedot.Tiedosto;


/**
 * Created by Joel Sallinen (sallinen.joel.i@student.uta.fi)
 */
public class Testifilu {

   private static void tulosta(LinkitettyLista lista2) {
      if (lista2 != null) {
         System.out.print("[ ");
         for (int i = 0; i < lista2.koko(); i++) {
            System.out.print(lista2.alkio(i));
            if (i < lista2.koko() - 1)
               System.out.print(", ");
         }
         System.out.println(" ]");
      }
   }

   public static void main(String[] args) {


      Hakemisto hakemisto = new Hakemisto(new StringBuilder("cats"), null);
      Hakemisto alihakemisto1 = new Hakemisto(new StringBuilder("perse"), hakemisto);
      Hakemisto alihakemisto2 = new Hakemisto(new StringBuilder("dogs"), hakemisto);
      Hakemisto alihakemisto3 = new Hakemisto(new StringBuilder("hajotus"), alihakemisto1);
      Hakemisto alihakemisto4 = new Hakemisto(new StringBuilder("saatana"), alihakemisto2);

      Tiedosto tiedosto1 = new Tiedosto(new StringBuilder("grumpy_cat.jpeg"), 335932);
      Tiedosto tiedosto2 = new Tiedosto(new StringBuilder("cannot_be_unseen.jpeg"), 29614);
      Tiedosto tiedosto3 = new Tiedosto(new StringBuilder("ceiling_cat.gif"), 3677);
      Tiedosto tiedosto4 = new Tiedosto(new StringBuilder("dangerous_kitten.jpg"), 13432);
      Tiedosto tiedosto5 = new Tiedosto(new StringBuilder("uikurssionparas.jpeg"), 335932);
      Tiedosto tiedosto6 = new Tiedosto(new StringBuilder("entieda.jpeg"), 29614);
      Tiedosto tiedosto7 = new Tiedosto(new StringBuilder("setamies.gif"), 3677);
      Tiedosto tiedosto8 = new Tiedosto(new StringBuilder("shieeeet.jpg"), 13432);
      Tiedosto tiedosto9 = new Tiedosto(new StringBuilder("plooo"), 335932);
      Tiedosto tiedosto10 = new Tiedosto(new StringBuilder("tilt.jpeg"), 29614);



      hakemisto.lisaa(tiedosto1);
      hakemisto.lisaa(tiedosto2);
      hakemisto.lisaa(tiedosto3);
      alihakemisto1.lisaa(tiedosto4);
      alihakemisto1.lisaa(tiedosto5);
      alihakemisto2.lisaa(tiedosto6);
      alihakemisto2.lisaa(tiedosto7);
      alihakemisto3.lisaa(tiedosto8);
      alihakemisto3.lisaa(tiedosto9);
      alihakemisto4.lisaa(tiedosto10);

      tulosta(hakemisto.sisalto());
      tulosta(alihakemisto1.sisalto());
      tulosta(alihakemisto2.sisalto());
      tulosta(alihakemisto3.sisalto());
      tulosta(alihakemisto4.sisalto());
   }



}
