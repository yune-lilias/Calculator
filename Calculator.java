import java.util.Scanner;
import java.util.*; 
import java.io.*; 

public class Calculator {
  
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // read input
        String expression = sc.nextLine();
        
        // print the evaluated result
        System.out.println(eval(expression));
    }
    
    public static int eval(String expression) {
      Stack<Character> stack = new Stack<>();
      int s = 0;
          char[] ex = expression.toCharArray();
          while(true)
          if(expression.indexOf(')')>=0)        
          for(int i = 0; i<ex.length;i++){
            if(ex[i]!=')')
            stack.push(ex[i]);
            else{
            String temp = "";
            while(stack.peek()!='(') {
            String temp1 = "";
            if(stack.peek()>='0'&&stack.peek()<='9') {
            while(stack.peek()>='0'&&stack.peek()<='9')
            	temp1 = stack.pop() +""+ temp1;
            temp+=temp1;}
            else {
            	temp+=stack.pop();
               }
            }
            s = calc(temp);
            String stemp = s + "";
            stack.pop();
            for(int sind = 0; sind<stemp.length();sind++)
            	stack.push(stemp.charAt(sind));
            String zs = "("+temp+")";
            int index = expression.indexOf(")");
            expression = expression.substring(0,index-zs.length()+1)+s+expression.substring(index+1);
            }
          }
          else {
        	  s = calc(expression);
          return s;}
    }
    
    public static int calc(String s){
      String z = findfirstnum(s);
      String[] z0 = new String[2];
      int sk = 0;
      z0 = z.split(" ");
      if(z0[1].equals("$"))
        return Integer.parseInt(z0[0]);
      else
        switch(z0[1].charAt(0)){
          case '+':
             return Integer.parseInt(z0[0]) + calc(z0[1].substring(1));
          case '-':
             return Integer.parseInt(z0[0]) - calc(z0[1].substring(1));
          case '*':
             String[] z1 = findfirstnum(z0[1].substring(1)).split(" ");
             if(z1[1].equals("$"))
             return Integer.parseInt(z0[0])*Integer.parseInt(z1[0]);
             else if(z1[1].charAt(0)=='+'){
                 sk = Integer.parseInt(z0[0])*Integer.parseInt(z1[0])+ calc(z1[1].substring(1));}
             else
            	 sk = Integer.parseInt(z0[0])*Integer.parseInt(z1[0])* calc(z1[1].substring(1)); 
             return sk;
             }
        return 0;
    }
    
    public static String findfirstnum(String s){
      String k = "";
      int i = 0;
      try {
      while(s.charAt(i)>='0'&&s.charAt(i)<='9'){
        k += s.charAt(i);
        i++;
      }}catch(Exception e) {return k+" "+"$";}
      try{
      return k+" "+s.substring(i);}
      catch(Exception e){return k+" "+"$";}
    }
}