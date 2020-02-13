package practica1tc;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {
    char opc = ' ';
    String a1[], a2[], w1, w2; 
    String menu[] = {"a) Insertar el alfabeto 1"
                   , "b) Insertar el alfabeto 2"
                   , "c) Insertar W1"
                   , "d) Insertar W2"
                   , "e) Generar (w1w2)^n"
                   , "f) Obtener |W1|x"
                   , "g) Es prefijo o sufijo"
                   , "h) Palíndromo"
                   , "i) "
                   , "j) 3 palabras aleatorias"  
                   , "z) Salir"};
    Scanner scan;

    public Menu(){
        scan = new Scanner(System.in);
    }
    
    public void startMenu(){
        do {
            for(int i=0 ; i<menu.length ; i++){
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
                    powChain(w1, w2);
                    break;
                case 'f':
                    countLetter(w1);
                    break;
                case 'g':
                    prefixSufix(w1, w2);
                    break;
                case 'h':
                    isPalindrome();
                    break;
                case 'i':
                    askPow();
                    break;
                case 'j':
                    randomWords();
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
            System.out.println("Inserte la cadena W" + cNum +" palabra de 𝝨1");
            chain = scan.nextLine();
        }while(!isOfAlphabet(alphabet, chain));
        
        return chain;
    }
    
    public boolean isOfAlphabet(String[] alphabet, String chain){
        for(Object o: alphabet)
            chain = chain.replace(o.toString(), "");
        
        return chain.isEmpty();
    }
    
    public boolean isPartOf(String[] alphabet, String x){
        List<String> list = Arrays.asList(alphabet);
        return list.contains(x);
    }
    
    public void powChain(String w1, String w2){
        String total = "";
        System.out.println("Inserta n:");
        int n = Integer.parseInt(scan.nextLine());
        
        String chain = w1+w2;
        if(n<0){
            String temp = "";
            for(int i=chain.length()-1; i>=0; i--)
                temp+=chain.charAt(i);
            n = n*(-1);
            for(int i=0; i<n ; i++)
                total += temp;
        }else{
            if(n>0){
                for(int i = 0; i<n ; i++ )
                    total += chain;
            }
            else{
                total = "λ";
            }
        }    
        System.out.println("\n"+total+"\n");
        
    }
    
    public void countLetter(String w1){
        String x = "";
        do{
            System.out.println("Dame x elemento de 𝝨1");
            x = scan.nextLine();
        }while(!isPartOf(a1, x));
        
        System.out.println((w1.split(x,-1).length)-1);
    }
    
    public void prefixSufix(String w1, String w2){
        String msg = "";
        if(w1.equals(w2))
            msg+= "\nPrefijo impropio"
                    + "\nSufijo impropio";
        else{
            if(w2.startsWith(w1))
                msg+= "\nPrefijo propio";
            if(w2.endsWith(w1))
                msg+= "\nSufijo propio";
        }  
    }
    
    public void isPalindrome(){
        String w3 = readChain(a1, 3);
        
        int n = w3.length()/2;
        boolean flag = true;
        
        for(int i=0; i<n; i++) {
            if(w3.charAt(i)!=w3.charAt(w3.length()-i-1))
                flag=false;
        }
        
        System.out.println(flag ? "Es palindromo" : "No es palindromo");
    }
    
    public void askPow(){
        System.out.println("Inserte n: ");
        String number = scan.nextLine();
        int n = Integer.parseInt(number);
        
        String copy[] = new String[a1.length];
        for(int i=0; i<a1.length; i++)
            copy[i] = a1[i];
        
        if(n!=0)
            powAlphabet(copy, n-1, 2);
        else
            System.out.println("λ");
        
        
    }
    
    public void powAlphabet(String[] a, int pot, int counter){
        if(pot>0){
            String array[];
            int tamano=(int)Math.pow(a1.length,counter);
            System.out.println(tamano);
            array=new String[tamano];
            int k=0;
            for(int i=0;i<a.length;i++){
                for(int j=0;j<a1.length;j++){
                    array[k]=a[i]+a1[j];
                    k++;
                }
            }
            counter++;
            pot--;
            powAlphabet(array,pot,counter);
        }
        else
            printAlphabet(a, counter);
    }
    
    public void printAlphabet(String a[], int pow){
        for (int i = 0; i<a.length; i++) {
            System.out.print(i%Math.pow(3, pow-2)==0 ? "\n"+a[i]+", " : a[i]+", ");
        }
        System.out.println("");
    }
    
    public void randomWords(){
        for(int i=0; i<3; i++) {
            String word = "";
            int length = (int) (Math.random()*20);
            for(int j=0; j<length; j++){
                int nLetter = (int) (Math.random()*(a1.length));
                word += a1[nLetter];
            }
            System.out.println("A"+(i+1)+": " +word);
        }
    }
}
