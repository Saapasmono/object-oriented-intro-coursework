package oope2017ht.tiedot;

/**
 * Created by Joel Sallinen (sallinen.joel.i@student.uta.fi)
 */
public abstract class Tieto implements Comparable<Tieto> {
   private StringBuilder name;

   /**
    * Nimen parametrillinen aksessori. Stringbuilderilla nimiehdotus jkoa tarkastetaan.
    * @param n Syötteenä saatu nimiehdotus
    * @throws IllegalArgumentException jos ei kelpaa
    */
   public void name(StringBuilder n) throws IllegalArgumentException {
      int dotCount = 0;

      if (n.length() < 1) {
         throw new IllegalArgumentException();
      }
      else {
         for (int i = 0; i < n.length(); i++) {
            char character = n.charAt(i);

            if(character >='a'&&character<='z'||character>='A'&&character<='z'||
                    Character.isDigit(character) ||
                    character=='_' || character==' '){}

            else if (character == '.') {
               dotCount++;

               if (dotCount > 1)
                  throw new IllegalArgumentException();
            }

            else {
               throw new IllegalArgumentException();
            }
         }
         name = n;
      }
   }
   public StringBuilder name(){
      return name;
   }

   /**
    * Tiedon rakentaja. Antaa oliolle nimen
    * @param n Nimiehdotus, joka testataan
    * @throws IllegalArgumentException epäkelvossa palautta
    */
   public Tieto(StringBuilder n) throws IllegalArgumentException{
      if(n.length()<1){
         throw new IllegalArgumentException();
      }
      else{
         name(n);
      }
   }

   public Tieto(){
      name = new StringBuilder();
   }

   public Tieto(Tieto n){
      if(n instanceof Tieto){
         StringBuilder copyName = new StringBuilder(n.name());
         name(copyName);
      }
   }

   /**
    * Korvataan equals. Vertaillaan onko kyseessä tieto. Jos on, siitä tehdään apuoli.
    * @param compare Vertailtava syöte
    * @return palauttaa osuman tai virheen.
    */
   public boolean equals(Object compare){
      try{
         if(compare instanceof Tieto){
            Tieto first = (Tieto)compare;

            String firstName = name.toString();
            String secondName = first.name().toString();

            return firstName.equals(secondName);
         }
         else
            return name().toString().equals(compare.toString());
      }
      catch (Exception e){
         return false;
      }
   }

   //toString-metodi
   @Override
   public String toString() {
      return name.toString();
   }

   //compareTo-metodi
   @Override
   public int compareTo(Tieto o) {
      return name.toString().compareTo(o.name.toString());
   }
}

