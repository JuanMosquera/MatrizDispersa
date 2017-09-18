/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrizdispersa;

/**
 *
 * @author jfwc1
 */
public class Matriz {
    private int matriz[][];
    private int fila,columna;

    public Matriz(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        matriz = new int[fila][columna];
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                matriz[i][j]=0;
            }
        }
    }

    public int[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(int[][] matriz) {
        this.matriz = matriz;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }
    
    public void setDato(int f, int c, int v){
        matriz[f-1][c-1]=v;
    }
    
    public int getDato(int i, int j){
        return matriz[i][j];
    }
    
    public void mostrarMatriz(){
        for (int i = 0; i < this.getFila(); i++) {
            for (int j = 0; j < this.getColumna(); j++) {
                System.out.print("  "+matriz[i][j]);
            }
            System.out.println("");
        }
    }
    
    // este es el metodo para convertir una matriz normal a tripletas
   public matrizEnTripletas construyeMatrizEnTripletas(int m,int n){
       matrizEnTripletas a;
       int c,d;
       Tripleta t = new Tripleta(m,n,null);
       a = new matrizEnTripletas(t);
       c = 0;
       for (int i = 0; i < m; i++) {
           for (int j = 0; j < n; j++) {
               d = getDato(i,j);
               if (d!=0) {
                   c = c + 1;
                   t = new Tripleta(i+1,j+1,d);
                   a.asignaTripleta(t,c);   
               }   
           }  
       }
       a.asignaNumeroTripletas(c);
       return a;
   }
    
}
