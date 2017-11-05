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
   
   //revisar los algorimo de cambio de filas y columnas
  
   public void intercambiarFilas(int i, int j){
       int mi, ni, mj, nj, p, k, ki, kj, ci, cj, vi, vj, pp, s[];
       Tripleta t;
       matrizEnTripletas aux;
       if (i==j) {
           return;
       }
       if (i>j) {
           k=i;
           i=j;
           j=k;
       }
       s = contruyeVectorDeLimites();
       mi = s[i];
       ni = s[i+1]-s[i];
       
       mj = s[j];
       nj = s[j+1]-s[j];
       //en las lineas anteriores ubico la posicion fila y columna del primer dato 
       //de la primera fila y la segunda fila a cambiar
       ki = 1;
       kj = 1;
       while (ki <= ni && kj <= nj) {
          ci = v[mi].getColumna();
          cj = v[mj].getColumna();
          vi = (int) v[mi].getValor();
           System.out.print("");
          vj = (int) v[mj].getValor();
          v[mi].setColumna(cj);
          v[mi].setValor(vj);
          v[mj].setColumna(ci);
          v[mj].setValor(vi);
          ki = ki + 1;
          kj = kj + 1;
          mi = mi + 1;
          mj = mj + 1;
       }
       if  (kj  <= nj){
            pp=mj-1;
            t = new Tripleta(this.getFilas(),this.getColumnas(), 0);
            aux = new  matrizEnTripletas(t);
            while  (kj <= nj){
                t = new Tripleta(i, v[mj].getColumna(), v[mj].getValor());
                aux.insertaTripleta(t);
                kj = kj + 1;
                mj = mj + 1;
            }
            mj = mj-1;
            while  (pp >= mi){
                v[mj] = v[pp];
                mj=mj-1;
                pp = pp-1;
            }
            for (k=1; k<=aux.retornaNumeroTripletas(); k++){
                v[mi] = aux.getTripleta(k);
                mi = mi + 1;
            }
            return;
        }
                    
        if  (ki <= ni){
            pp = mi;
            t = new Tripleta(this.getFilas(), this.getColumnas(), 0);
            aux = new  matrizEnTripletas(t);
            while  (ki <= ni) {
                t = new Tripleta(j, v[mi].getColumna(), v[mi].getValor());
                aux.insertaTripleta(t);
                ki = ki + 1;
                mi = mi + 1;
            }
            while  (mi < mj){
                v[pp] = v[mi];
                mi = mi + 1;
                pp = pp + 1;
            }
            for (k=1; k<=aux.retornaNumeroTripletas(); k++){
                v[pp] = aux.getTripleta(k);
                pp = pp + 1;
            }
        }
   }
   //devuelbe un vector que indica las posiciones
   //en en la tripleta en la que empieza cada fila
   public int[] contruyeVectorDeLimites(){
       int k,p,n,s[];
       Tripleta tx;
       p = this.retornaNumeroTripletas();
       n = this.getFilas();
       s = new int[n+2];

       for (k = 1; k <= n; k++){
            s[k] = 0;
        }
        for (k=1; k<=p; k++){
            tx = this.getTripleta(k);
            s[tx.getFila()] = s[tx.getFila()] + 1;
        }
        s[n + 1] = p + 1;
        for (k = n; k > 0; k--) {
            s[k]=s[k+1]-s[k];
            
            
        }

       return s;
       
   }
   
   public void intercambiarColumnas(int i, int j){
       int k, s[],n;
       s = this.contruyeVectorDeLimites();
       n = this.getFilas();
       for (k = 1; k <= n; k++) {
           procesaFilaActual(s[k],s[k+1]-1,i,j);
       }
   }
   
   public void procesaFilaActual(int ifa, int tfa, int i, int j){
       int aux, k, pi, pj;
       Tripleta t;
       aux = 0;
       if (i>j) {
           aux=i;
           i=j;
           j=aux;
       }
       k = ifa;
       pi = 0;
       while (k<=tfa) {
           if (v[k].getColumna()==i) {
               pi = k;
               break;
           }
           k = k+1;
       }
       k = ifa;
       pj = 0;
       while (k<=tfa) {
           if (v[k].getColumna()==j) {
               pj = k;
               break;
           }
           k = k+1;
       }
       if (pi==0 && pj==0) {
           return;
       }
       if (pj == 0) {
           k = ifa;
           while (k<=tfa && v[k].getColumna()!=i) {
               k=k+1;
           }
           t=v[k];
           k=k+1;
           while (k<=tfa && v[k].getColumna()<j) {
               v[k-1]=v[k];
               k=k+1;
           }
           t.setColumna(j);
           v[k-1]=t;
           return;
       }
       if (pi==0) {
           k=tfa;
           while (k<=ifa && v[k].getColumna()!=j) {
               k=k-1;
               
           }
           t=v[k];
           k=k-1;
           while (k>=ifa && v[k].getColumna()>i) {
               v[k+1]=v[k];
               k=k-1;
           }
           t.setColumna(i);
           v[k+1]=t;
           return;
       }
       aux= (int) v[pi].getValor();
       v[pi].setValor((int)v[pj].getValor());
       v[pj].setValor(aux);
       
   }
   
   //punto de silla
   public void puntoDeSilla(){
       int k, psi, psj, numeroFilas, numeroColumnas, sa[], sb[];
       Tripleta tx;
       matrizEnTripletas bt;
       numeroFilas = this.getFilas();
       numeroColumnas = this.getColumnas();
       bt = this.traspuestaRapida();
       sa = this.contruyeVectorDeLimites();
       sb = bt.contruyeVectorDeLimites();
       psi = 0;
       k = 1;
       while (k<=numeroFilas) {
           psi = filaMenorDato(sa[k], sa[k+1]-1, numeroColumnas);
           if (psi!=0) {
               psj = bt.columnaMayorDato(sb[psi], sb[psi+1]-1, numeroFilas);
               if (psj==k) {
                   System.out.println("el punto de silla es"+k+" "+psi);
                   return;
               }
           }
           k = k+1;
       }
       System.out.println("no hay punto de silla");     
   }

public int filaMenorDato(int i, int n, int nc){
    int menor, j , columna, k, p, ne;
    Tripleta tx;
    menor = 10000;
    columna = 0;
    ne = n-i+1;
    for (j=i; j<=n ; j++) {
        tx = this.getTripleta(j);
        if ((int)tx.getValor()<menor) {
            menor = (int)tx.getValor();
            columna = tx.getColumna();
        }
    }
    k=0;
    for (j=i; j <= n; j++) {
        tx = this.getTripleta(j);
        if ((int)tx.getValor()==menor) {
            k=k+1;
        }
    }
    switch (nc-ne) {
        case 0:
            if (k==1) {
                return columna;
            }
            break;
        case 1:
            if (menor<0 && k==1) {
                return columna;
            }
            if (menor<0) {
                return 0;
            }
            j=i;
            tx =this.getTripleta(j);
            p=1;
            while (tx.getColumna()==p) {
                j=j+1;
                tx=this.getTripleta(j);
                p=p+1;
            }
            return p;
        default:
            if (menor<0 && k==1) {
                return columna;
            }
    }
    return 0;
}

public int columnaMayorDato(int i, int n, int nc){
    int j, mayor, fila, k, p, ne;
    Tripleta tx;
    mayor = -10000;
    fila =0;
    ne = n-i+1;
    for (j = i;  j<= n; j++) {
        tx = this.getTripleta(j);
        if ((int)tx.getValor()>mayor) {
            mayor = (int)tx.getValor();
            fila = tx.getColumna();
        }
    }
    k=0;
    for (j = i;  j<=n; j++) {
        tx = this.getTripleta(j);
        if ((int)tx.getValor()==mayor) {
            k=k+1;
        }
    }
    switch (nc-ne) {
        case 0:
            if (k==1) {
                return fila;
            }
            break;
        case 1:
            if (mayor>0 && k==1) {
                return fila;
            }
            if (mayor > 0 ) {
                return 0;
            }
            j=i;
            tx = this.getTripleta(j);
            p=1;
            while (tx.getColumna()==p) {
                j=j+1;
                tx = this.getTripleta(j);
                p = p+1;
            }
            return p;
        default:
            if (mayor>0 && k==1) {
                return fila;
            }
    }
    return 0;
}


   
   //simetrica
public boolean esSimetrica(){
    int p, f, c,vi, n, s[],k;
    Tripleta t;
    s = this.contruyeVectorDeLimites();
    p = this.retornaNumeroTripletas();
    for (k = 1 ; k <= p/2+1; k++) {
        f = v[k].getFila();
        c = v[k].getColumna();
        vi = (int)v[k].getValor();
        t = new Tripleta(c,f,vi);
        if (!existeTripleta(t,s[c],s[c+1])) {
            return false;
        }
    }
    return true;
}

public boolean existeTripleta(Tripleta t, int inicio, int fin){
    int i,f,c,vi;
    i = inicio;
    while (i<fin) {
        f= t.getFila();
        c = t.getColumna();
        vi = (int)t.getValor();
        if (f==v[i].getFila() && c == v[i].getColumna() && vi==(int)v[i].getValor()) {
            return true;
        }
        i = i+1;
    }
    return false;
}

   //construir forma2
    public MatrizForma2 construyeMatrizForma2(){
         int m,n,p;
           Tripleta t;
           MatrizForma2 a;
           NodoDoble c;
           m = getFilas();
           n = getColumnas();
           p = retornaNumeroTripletas();
           a = new MatrizForma2(m,n);
           
           for (int i = 1; i <= p; i++) {
               t = getTripleta(i);
               c = new NodoDoble(t);
               a.conectarPorFilas(c);
               a.conectarPorColumnas(c);
           }
           return a;
    }
    
//    public MatrizForma2 crear(){
//        MatrizForma2 a;
//        a = new MatrizForma2(m,n);
//        Tripleta t = new Tripleta(fila,columna,valor);
//        NodoDoble n= new NodoDoble(t);
//        a.conectarPorFilas(n);
//        a.conectarPorColumnas(n);
//    }
    
    
   //construir forma1
    public MatrizForma1 construyeMatrizForma1(){
           int m,n,p;
           Tripleta t;
           MatrizForma1 a;
           NodoDoble c;
           m = getFilas();
           n = getColumnas();
           p = retornaNumeroTripletas();
           a = new MatrizForma1(m,n);
           a.construyeNodosCabeza();
           for (int i = 1; i <= p; i++) {
               t = getTripleta(i);
               c = new NodoDoble(t);
               a.conectarPorFilas(c);
               a.conectarPorColumnas(c);
           }
           return a;
           
    
           
    }

    public void interColumna(int m, int n){
               
               Tripleta v2[],tx,t=v[0];
               int p,nc,nf,nv,i=1, tamaño = this.retornaNumeroTripletas();
             
                nf = this.getFilas();
                nc = this.getColumnas();
                p =(nf*nc)+2;
                v2 = new Tripleta[p];
                v2[0] = t;
                for ( i = 1; i < p; i++) {
                    v2[i] = null;
                }
               
               i=1;
               while (i<=tamaño) {
                   t=v[i];
                   nf=t.getFila();
                   nc=t.getColumna();
                   nv=(int) t.getValor();
                   if (m==nc) {
                       tx=new Tripleta(nf,n,nv);
                       v2[i]=tx;
                   } else if (n==nc) {
                       tx=new Tripleta(nf,m,nv);
                       v2[i]=tx;
                   } else {
                       tx=new Tripleta(nf,nc,nv);
                       v2[i]=tx;
                   }
                   i=i+1;
               }
               v=v2;
       }

   


}
