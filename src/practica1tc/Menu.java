package practica1tc;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Menu {
    char opc = ' ';
    String a1[], a2[], w1, w2; 
    String menu[] = {"a) Insertar el alfabeto 1"
                   , "b) Insertar el alfabeto 2"
                   , "c) Insertar W1"
                   , "d) Insertar W2"
                   , "e) Combinar alfabeto 1 y alfabeto 2"
                   , "f) Obtener magnitud de W1"
                   , "g)"
                   , "h)"
                   , "i) "
                   , "z) Salir"};
    Scanner scan;

    public Menu(){
        scan = new Scanner(System.in);
    }
    
    public void startMenu(){
        do {
            for(int i=0 ; i<10 ; i++){
                System.out.println(menu[i]);
            }
            opc = scan.nextLine().charAt(0);
            
            switch(opc){
                case 'a':
                    a1 = readAlphabet(1);
                    break;
                case 'b':
                    a2 = readAlphabet(2);
                    break;
                case 'c':
                    w1 = readChain(a1, 1);
                    break;
                case 'd':
                    w2 = readChain(a1, 2);
                    break;
                case 'e':
                    break;
                case 'f':
                    break;
                case 'g':
                    break;
                case 'h':
                    break;
                case 'i':
                    break;
                case 'z':
                    break;
                default: System.out.println("\n Esa opción no existe \n");
                    break;
            }
        }while(opc != 'z');
    }
    
    public String[] readAlphabet(int a){
        String chain[] = {""};
        ArrayList<String> arrchain = new ArrayList<>();
        
        char option = 'Y';
        int min = 0;
        
        System.out.println("Inserte los elementos del alfabeto " + a);
        
        while(!(option == 'N' || option == 'n')){
            System.out.println("Elemento: ");
            arrchain.add(scan.nextLine());
            
            if(min > 1){
                System.out.println("¿Agregar más elementos? Y/N");
                option = scan.nextLine().charAt(0);
            }
            min++;
        }
        
        return sortAlphabet(arrchain.toArray(new String[arrchain.size()]));
    }
    
    
    public String[] sortAlphabet(String[] s){ 
        for (int i=1 ;i<s.length; i++){ 
            String temp = s[i]; 

            int j = i - 1; 
            while (j >= 0 && temp.length() > s[j].length()){ 
                s[j+1] = s[j]; 
                j--;
            } 
            s[j+1] = temp; 
        }
        
        return s;
    }
    
    public String readChain(String[] alphabet, int cNum){
        String chain = "";
        
        do{
            System.out.println("Inserte la cadena W" + cNum +" elemento del primer alfabeto");
            chain = scan.nextLine();
        }while(!isOfAlphabet(alphabet, chain));
        
        return chain;
    }
    
    public boolean isOfAlphabet(String[] alphabet, String chain){
        for(Object o: alphabet)
            chain = chain.replace(o.toString(), "");
        
        return chain.isEmpty();
    } 
}
