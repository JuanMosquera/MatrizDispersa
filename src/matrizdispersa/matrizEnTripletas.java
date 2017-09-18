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

    public matrizEnTripletas(Tripleta t) {
        int m = t.getFila();
        int n = t.getColumna();
        int p = (m*n)+2;
        int i;
        v = new Tripleta[p];
        v[0] = t;
        for (i = 1; i < p; i++) {
            v[i] = null;
        }
    }
   /*pensar sobre la construccion en tripletas si con un tamaño igual 
    al numero de elementos reales o con las dimensiones de la matriz original*/
   public void asignaNumeroTripletas(int n){
       Tripleta t = v[0];
       t.setValor(n);
       v[0] = t;
   }
   
   public void asignaTripleta(Tripleta tx,int i){
       v[i] = tx;
   }
   
   public int getFilas(){
       Tripleta t = v[0];
       return t.getFila();
   }
   
   public int getColumnas(){
       Tripleta t = v[0];
       return t.getColumna();
   }
   
   public int retornaNumeroTripletas(){
       Tripleta t = v[0];
       return (int)t.getValor();
       //hacer casting o retornar objeto?
   }
   
   public Tripleta getTripleta(int i){
       return v[i];
   }
   
   public void muestraMatrizEnTripletas(){
       int i = 1;
       Tripleta t = getTripleta(0);
       int datos = (int)t.getValor();
       while (i <= datos) {
           System.out.print(" "+v[i].getFila());
           System.out.print(" "+v[i].getColumna());
           System.out.print(" "+(int)v[i].getValor());
           System.out.println("");
           i = i + 1;
       }
   }
   
      public void mostrarComoMatriz(){
       int i = 1;
       Tripleta t = getTripleta(0);
       int fila = t.getFila();
       int columna = t.getColumna();
       int datos = (int)t.getValor();
       for (int j = 1; j <= fila; j++) {
           for (int k = 1; k <= columna; k++) {
               if (v[i].getFila() == j && v[i].getColumna() == k ) {
                   System.out.print(" "+(int)v[i].getValor());
                   if (i!=datos) {
                    i = i + 1;   
                   }
               } else {
                   System.out.print(" "+0);
               }
               if (k==columna) {
                   System.out.println("");
               }
           }
       }
   }
   
   public void insertaTripleta(Tripleta ti){
       int i,j,datos;
       Tripleta t, tx;
       tx = getTripleta(0);
       datos = (int)tx.getValor();
       i = 1;
       t = getTripleta(i);
       while (i<=datos && t.getFila()< ti.getFila()) {
           i = i+1;
           t = getTripleta(i);
       }
       while (i<=datos && t.getFila()== ti.getFila() && t.getColumna() < ti.getColumna()) {
           i = i+1;
           t = getTripleta(i);
       }
       datos = datos + 1;
       j = datos - 1;
       while (j>=i) {
           v[j+1]= v[j];
           j = j-1;
       }
       v[i] = ti;
       asignaNumeroTripletas(datos);
   }
   
   public matrizEnTripletas suma(matrizEnTripletas b){
       int ma,na,mb,nb,p,q,i,j,k,ss,fi,fj,ci,cj,vi,vj;
       Tripleta ti,tj,tx;
       ma = getFilas();
       na = getColumnas();
       mb = b.getFilas();
       nb = b.getColumnas();
       p = retornaNumeroTripletas();
       q = b.retornaNumeroTripletas();
       if ((ma!=mb)||(na!=nb)) {
           System.out.println("matrices de diferentes dimensiones no se puede sumar");
           return null;
       }
       ti = new Tripleta(ma,na,0);
       matrizEnTripletas c = new matrizEnTripletas(ti);
       i = 1;
       j = 1;
       k = 0;
       while ((i<=p)&&(j<=q)) {
           ti = getTripleta(i);
           tj = b.getTripleta(j);
           fi = ti.getFila();
           fj = tj.getFila();
           k = k + 1;
           switch (comparar(fi,fj)) {
               case -1:
                   c.asignaTripleta(ti, k);
                   i = i+1;
                   break;
               case 1:
                   c.asignaTripleta(tj, k);
                   j = j+1;
                   break;
               case 0:
                   ci = ti.getColumna();
                   cj = tj.getColumna();
                   switch (comparar(ci,cj)) {
                       case -1:
                           c.asignaTripleta(ti, k);
                           i = i+1;
                           break;
                       case 1:
                           c.asignaTripleta(tj, k);
                           j = j+1;
                           break;
                       case 0:
                           vi = (int)ti.getValor();
                           vj = (int)tj.getValor();
                           ss = vi+vj;
                           if (ss != 0) {
                               tx = new Tripleta(fi,ci,ss);
                               c.asignaTripleta(tx, k);
                           } else {
                               k = k-1;
                           }
                           i = i+1;
                           j = j+1;
                           break;       
                   }   
           }
       }
       while (i <= p) {
           ti = getTripleta(i);
           k = k+1;
           c.asignaTripleta(ti, k);
           i = i+1;
       }
       while (j <= q) {
           tj = b.getTripleta(j);
           k = k+1;
           c.asignaTripleta(tj, k);
           j = j+1;
       }
       c.asignaNumeroTripletas(k);
       return c;
   }
   
   public int comparar(int d1, int d2){
       if (d1<d2) {
           return -1;
       }
       if (d1==d2) {
           return 0 ;
       }
       return 1;
   }
   
   //public matrizEnTripletas multiplica(matrizEnTripletas b){}
  
   public matrizEnTripletas   multiplica(matrizEnTripletas  b){
        int  m, n, na, p, nb, i, j, k, filaActual, columnaActual, inicioFilaActual, suma;
        Tripleta ti, tj, tx;
        matrizEnTripletas bt, c;
        m = this.getFilas();
        n = this.getColumnas();
        na = this.retornaNumeroTripletas();
        if (n != b.getFilas()) {
            System.out.println("no se pueden multiplicar las matrices");
            return null;
        }

        p = b.getColumnas();
        nb = b.retornaNumeroTripletas();
        tx = new Tripleta(m+1, 0, 0);
        this.asignaTripleta(tx, na+1);
        tx = new Tripleta(m, p, null);
        c = new  matrizEnTripletas(tx);
        bt = b.traspuestaRapida();
        tx = new Tripleta(p+1, 0, 0);
        bt.asignaTripleta(tx, nb+1);
        i = 1;
        ti = this.getTripleta(i);
        filaActual = ti.getFila();
        inicioFilaActual = i;
        k = 0;
        suma = 0;        
        while(i <= na){  
                j = 1;
                tj = bt.getTripleta(j);
                columnaActual = tj.getColumna();
                while  (j <= nb+1){
                        tj = bt.getTripleta(j);
                        if  (ti.getFila() != filaActual){ 
                            if  (suma != 0){  
                                    k = k + 1;
                                    tx = new  Tripleta(filaActual, columnaActual, suma);
                                    c.asignaTripleta(tx, k);
                                    suma = 0;
                            }
                            while  (tj.getFila() == columnaActual){ 
                                    j = j + 1;
                                    tj = bt.getTripleta(j);
                            }
                            columnaActual = tj.getFila();
                            i = inicioFilaActual;
                            ti = getTripleta(i);
                            continue;
                        }		
                        if  (tj.getFila() != columnaActual){
                            if  (suma != 0) {
                                k = k + 1;
                                tx = new Tripleta(filaActual, columnaActual, suma);
                                c.asignaTripleta(tx, k);
                                suma = 0;
                            }
                            columnaActual = tj.getFila();
                            i = inicioFilaActual;
                            ti = getTripleta(i);
                            continue;
                        }
                        if  (ti.getColumna() < tj.getColumna()){
                            i = i + 1;
                            ti = getTripleta(i);
                            continue;
                        }
                        if  (ti.getColumna() == tj.getColumna()){  
                            int sti = (int)ti.getValor();
                            int stj = (int)tj.getValor();
                            suma = suma + sti * stj ;
                            i = i + 1;
                            j = j + 1;
                            ti = getTripleta(i);
                            tj = bt.getTripleta(j);
                            continue;
                        }
                    j = j + 1;
                }
                while (ti.getFila() == filaActual){
                    i = i + 1;
                    ti = this.getTripleta(i);
                }
                inicioFilaActual = i;
                filaActual = ti.getFila();
            }
        c.asignaNumeroTripletas(k);
        return  c;
	}
   
   public matrizEnTripletas traspuesta(){
     int i,p,f,c,v;
     Tripleta ti;
     p = retornaNumeroTripletas();
     ti = new Tripleta(this.getColumnas(),this.getFilas(),0);
     matrizEnTripletas b = new matrizEnTripletas(ti);
     i = 1;
       while (i <= p) {
           ti = this.getTripleta(i);
           f = ti.getColumna();
           c = ti.getFila();
           v = (int)ti.getValor();
           ti = new Tripleta(f,c,v);
           b.insertaTripleta(ti);
           i = i+1;
       }
       return b;
   }
   
   public matrizEnTripletas traspuestaMedia(){
       int i,j,k,m,n,p,f,c,v;
       Tripleta tj, tx;
       m = this.getFilas();
       n = this.getColumnas();
       p = this.retornaNumeroTripletas();
       tx = new Tripleta(n,m,p);
       matrizEnTripletas b = new matrizEnTripletas(tx);
       k = 0;
       for (i = 1; i <= n; i++) {
           for (j = 1; j <= p; j++) {
               tj = this.getTripleta(j);
               f = tj.getFila();
               c = tj.getColumna();
               v = (int)tj.getValor();
               if (c == i) {
                   k = k+1;
                   tx = new Tripleta(c,f,v);
                   b.asignaTripleta(tx, k);
               }
           }
       }
       return b;
   }
   
   public matrizEnTripletas traspuestaRapida(){
       int m,n,p,i,j,s[],t[];
       Tripleta ti , tx;
       m = this.getFilas();
       n = this.getColumnas();
       p = this.retornaNumeroTripletas();
       ti = new Tripleta(n,m,p);
       matrizEnTripletas b = new matrizEnTripletas(ti);
       s = new int[n+1];
       t = new int[n+1];
       for (i = 1; i <= n; i++) {
           s[i]=0;
       }
       for (i = 1; i <= p; i++) {
           ti = this.getTripleta(i);
           s[ti.getColumna()] = s[ti.getColumna()]+1;
       }
       t[1]=1;
       for (i = 2; i <= n; i++) {
           t[i] = t[i-1] + s[i-1];
       }
       for (i = 1; i <= p; i++) {
           ti = this.getTripleta(i);
           j = ti.getColumna();
           tx = new Tripleta(j,ti.getFila(),ti.getValor());
           b.asignaTripleta(tx, t[j]);
           t[j] = t[j]+1;
       }
       return b;
   }
   
   //metodo que crea una matriz normal a partir de una tripleta
   public Matriz construyeMatriz(){
       int m,n,p;
       Tripleta t;
       Matriz a;
       m = getFilas();
       n = getColumnas();
       p = retornaNumeroTripletas();
       a = new Matriz(m,n);
       for (int i = 1; i <= p; i++) {
           t = getTripleta(i);
           a.setDato(t.getFila(),t.getColumna(),(int)t.getValor());
       }
       
       return a;
   }
}
