/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrizdispersa;

/**
 *
 * @author Juan
 */
public class MatrizDispersa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        matrizEnTripletas matri;
        Tripleta tri = new Tripleta(5,5,null);
        matri = new matrizEnTripletas(tri);
        matri.asignaNumeroTripletas(0);
        tri = new Tripleta(1,1,3);
        matri.insertaTripleta(tri);
        tri = new Tripleta(1,3,6);
        matri.insertaTripleta(tri);
        tri = new Tripleta(2,2,9);
        matri.insertaTripleta(tri);
        tri = new Tripleta(3,4,2);
        matri.insertaTripleta(tri);
        tri = new Tripleta(4,3,1);
        matri.insertaTripleta(tri);
        tri = new Tripleta(5,5,7);
        matri.insertaTripleta(tri);
        matri.mostrarComoMatriz();
        System.out.println(" ");
        matrizEnTripletas matri2;
        Tripleta tri2 = new Tripleta(5,5,null);
        matri2 = new matrizEnTripletas(tri2);
        matri2.asignaNumeroTripletas(0);
        tri2 = new Tripleta(1,1,1);
        matri2.insertaTripleta(tri2);
        tri2 = new Tripleta(1,3,2);
        matri2.insertaTripleta(tri2);
        tri2 = new Tripleta(2,2,3);
        matri2.insertaTripleta(tri2);
        tri2 = new Tripleta(3,4,4);
        matri2.insertaTripleta(tri2);
        tri2 = new Tripleta(4,3,5);
        matri2.insertaTripleta(tri2);
        tri2 = new Tripleta(5,5,6);
        matri2.insertaTripleta(tri2);
        matri2.mostrarComoMatriz();
        System.out.println(" el resultado");
       matrizEnTripletas result = matri.multiplica(matri2);
       result.mostrarComoMatriz();
        
        
        
        
//        Matriz a, p= new Matriz(5,5);
//        p.setDato(1, 2, 12);
//        p.setDato(2, 4, 25);
//        p.setDato(3, 4, 5);
//        p.mostrarMatriz();
//        
        
//        matrizEnTripletas m = p.construyeMatrizEnTripletas(p.getFila(), p.getColumna());
//        m.muestraMatrizEnTripletas();
//        m.mostrarComoMatriz();
//        System.out.println("matriz dos");
//        a = m.construyeMatriz();
//        a.mostrarMatriz();
//        System.out.println("transpuesta");
//        matrizEnTripletas b = m.traspuesta();
//        b.mostrarComoMatriz();
//        
//        
//        MatrizForma1 ma = new MatrizForma1(5,5);
//        ma.construyeNodosCabeza();
//        Tripleta tr = new Tripleta(2,2,4);
//        NodoDoble x = new NodoDoble(tr);
//        ma.conectarPorFilas(x);
//        ma.conectarPorColumnas(x);
//        tr = new Tripleta(3,4,6);
//        x = new NodoDoble(tr);
//        ma.conectarPorFilas(x);
//        ma.conectarPorColumnas(x);
//        ma.muestraMatriz();
    }
    
}
