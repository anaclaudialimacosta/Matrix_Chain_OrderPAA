/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pd_cadeiadematrizes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;

/**
 *
 * @author inaci
 */
public class PD_CadeiaDeMatrizes {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    //Definição de um infinito inteiro
    static Integer infinito = 999999999;
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        //Entrada de Arquivos no formato: 
        //1° linha: vetor p separado por \t
        
        //Leitura da Entrada
         BufferedReader in = new BufferedReader(new FileReader("/C:\\Users\\inaci\\OneDrive\\Documentos\\NetBeansProjects\\PD_CadeiaDeMatrizes\\src/entrada1.txt"));
         
         String str;
         str = in.readLine();
         String[] aux = str.split("\t");
         Integer[] p = new Integer[aux.length]; 
         for(int i=0;i<p.length;i++){
             p[i] = parseInt(aux[i]);
         }
         verVetor(p);
         //System.out.println("LEU A TABELA\n ");
         int n = p.length-1;
         ArrayList<Integer[][]> solucao = Matrix_Chain_Order(p);
         //System.out.println("RETORNOU");
         Integer[][] s = solucao.get(1);
         print_optimal_parents(s,0,n);
         
 
    }
    
    public static void print_optimal_parents(Integer[][] s, int i, int j){
        if(i==j){
            System.out.print("A");
            
        } else {
            
            System.out.print("(");
            print_optimal_parents(s,i,s[i][j]);
            print_optimal_parents(s,s[i][j]+1,j);
            System.out.print(")");
            
        }
    }//print da solução
    public static ArrayList Matrix_Chain_Order(Integer[] p){
        //System.out.println("Entrou aqui");
        int n = p.length-1;
        Integer[][] m = new Integer[n][n];
        Integer[][] s = new Integer[n][n];
        for(int i =0;i<n;i++){
            //System.out.println("Ta Executando aqui");
            m[i][i] = 0;
        }
        for(int l=2;l<n;l++){
            //System.out.println("AQUIIII");
            for(int i= 1;i<(n-l+1);i++){
                int j=i+1-1;
                m[i][j] = infinito;
                //System.out.println("PASSEI PELO INFINITO");
                for(int k = i;k < j-1;k++ ){
                   // System.out.println("Entra aqui");
                    Integer q = m[i][k] + m[k+1][j] +(p[i-1]*p[k]*p[j]);
                    if(q<m[i][j]){
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
            
        }
        
        ArrayList<Integer[][]> retorno = new ArrayList();
        retorno.add(m);
        retorno.add(s);
        //System.out.println("HERE!");
        return retorno;
    }//método que determina a sequência
 /* public static void verVetor(Integer[] p){
      int j = 0;
      while(j<p.length) {
          System.out.println(p[j]);
          j++;
      }*/
          
  }  
}
