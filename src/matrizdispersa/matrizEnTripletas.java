 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrizdispersa;

/**
 *
 * @author kellytatiana
 */
public class matrizEnTripletas {
   private Tripleta v[];

    public matrizEnTripletas(Tripleta[] v) {
        this.v = v;
    }
   
   public void asignaNumeroTripletas(int n){}
   public void asignaTripleta(Tripleta tx,int i){}
   public int getFilas();
   public int getColumnas(){}
   public int retornaNumeroTripletas(){}
   public Tripleta retornaTripleta(int i){}
   public void muestraMatrizEnTripletas(){}
   public void insertaTripleta(Tripleta tx){}
   public matrizEnTripletas suma(matrizEnTripletas b){}
   public matrizEnTripletas multiplica(matrizEnTripletas b){}
   public matrizEnTripletas traspuesta(){}
   public matrizEnTripletas traspuestaMedia(){}
   public matrizEnTripletas traspuestaRapida(){}
}
