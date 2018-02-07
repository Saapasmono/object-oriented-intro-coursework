package oope2017ht;

import apulaiset.In;

/**
 * Created by Joel Sallinen (sallinen.joel.i@student.uta.fi)
 */
public class Kayttoliittyma {
   final String QUIT = "exit";
   final String MKDIR = "md";
   final String MKFILE = "mf";
   final String RENAME = "mv";
   final String LIST = "ls";
   final String COPY = "cp";
   final String FIND = "find";
   final String DEL = "rm";
   final String CHANGEDIR = "cd";

   boolean ok = true;
   public Tulkki tulkkiohjelma = new Tulkki();

   // Aloitetaan ajoluokka
   public void aloita() {
      while (ok) {
         System.out.print(tulkkiohjelma.definePath() + ">");
         String komento = In.readString();
         boolean ok2 = true;
         int dots = 0;
         int parts;

         for (int i = 0; i < komento.length(); i++) {
            char compare = komento.charAt(i);
            if (compare == ' '){
               if (i == komento.length()-1 || komento.charAt(i+1) == ' '){
                  ok2 = false;
               }
            }
            if (compare=='.'){
               dots += 1;
               if (dots > 2){
                  ok2 = false;
               }
            }
            if(Character.isLetter(compare) || Character.isDigit(compare) ||
                    compare == '_' || compare== ' ' || compare=='.'){
               ok2 = true;
            }
            else {
               ok2 = false;
            }

         }

         if(ok2){
            String[] numberOfParts = komento.split(" ");
            parts = numberOfParts.length;

            if (parts == 1){
               if(komento.equals(FIND)){
                  tulkkiohjelma.listRekursive();
               }
               else if(komento.equals(LIST)){
                  tulkkiohjelma.list();
               }
               else if(komento.equals(CHANGEDIR)){
                  tulkkiohjelma.changeDir();
               }
               else if(komento.equals(QUIT)){
                  ok = false;
                  System.out.println("Shell terminated.");
               }
               else
                  System.out.println("Error!");
            }
            else if (parts == 2){
               if(komento.startsWith(MKDIR)){
                  tulkkiohjelma.mkDir(numberOfParts[1]);
               }
               else if(komento.startsWith(CHANGEDIR)){
                  boolean moveOk = tulkkiohjelma.changeDir(numberOfParts[1]);
                  if(!moveOk){
                     System.out.println("Error!");
                  }
               }
               else if(komento.startsWith(DEL)){
                  boolean deleteOk = tulkkiohjelma.del(numberOfParts[1]);
                  if(!deleteOk){
                     System.out.println("Error!");
                  }
               }
               else if(komento.startsWith(LIST)){
                  boolean listOk = tulkkiohjelma.list(numberOfParts[1]);
                  if (!listOk){
                     System.out.println("Error!");
                  }
               }
               else
                  System.out.println("Error!");
            }
            else if(parts==3){
               if(komento.startsWith(MKFILE)){
                  try{
                     int fileSize = Integer.parseInt(numberOfParts[2]);
                     boolean makeOk = tulkkiohjelma.mkFile(numberOfParts[1], fileSize);
                     if(!makeOk){
                        System.out.println("Error!");
                     }
                  }
                  catch (NumberFormatException e){
                     System.out.println("Error!");
                  }
               }
               else if(komento.startsWith(COPY)) {
                  boolean ok3 = tulkkiohjelma.copy(numberOfParts[1], numberOfParts[2]);
                  if(!ok3){
                     System.out.println("Error!");
                  }
               }
               else if (komento.startsWith(RENAME)){
                  boolean nameOk = tulkkiohjelma.rename(numberOfParts[1], numberOfParts[2]);
                  if(!nameOk){
                     System.out.println("Error!");
                  }
               }
               else
                  System.out.println("Error!");
            }
            else if(parts>3){
               System.out.println("Error!");
            }
         }
         else
            System.out.println("Error!");

      }
   }
}
