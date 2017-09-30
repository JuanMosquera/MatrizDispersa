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
public class MatrizForma2 {
    private NodoDoble mat;

    public MatrizForma2(int m,int n) {
        Tripleta tx,t = new Tripleta(m,n,null);
        mat = new NodoDoble(t);
        tx = new Tripleta();
        NodoDoble x = new NodoDoble(tx);
        x.setLi(x);
        x.setLd(x);
        mat.setLd(x);
    }
    
    public NodoDoble nodoCabeza(){
        return mat.getLd();
    }
    
    public NodoDoble primerNodo(){
        return mat;
    }
    
    public boolean esVacia(){
        NodoDoble p = mat.getLd();
        return (p.getLi()== p && p.getLd()== p);
    }
    
    public boolean finDeRecorrido(NodoDoble p){
        return p==this.nodoCabeza();
    }
    
    public void conectarPorFilas(NodoDoble x){
        NodoDoble p,q,anterior;
        Tripleta tq,tx;
        int i;
        tx = (Tripleta)x.getDato();
        p = this.nodoCabeza();
        anterior = p;
        q = p.getLd();
        tq = (Tripleta)q.getDato();
        while (q!=p && tq.getFila()< tx.getFila()) {
            anterior=q;
            q = q.getLd();
            tq = (Tripleta)q.getDato();
        }
        while (q!=p && tq.getFila()== tx.getFila()&& tq.getColumna()<tx.getColumna()) {
            anterior = q;
            q = q.getLd();
            tq = (Tripleta)q.getDato();
        }
        anterior.setLd(x);
        x.setLd(q);
        
    }
    
    public void conectarPorColumnas(NodoDoble x){
        NodoDoble p,q,anterior;
        Tripleta tq,tx;
        int i;
        tx = (Tripleta)x.getDato();
        p = this.nodoCabeza();
        anterior = p;
        q = p.getLi();
        tq = (Tripleta)q.getDato();
        while (q!=p && tq.getColumna()<tx.getColumna()) {
            anterior = q;
            q = q.getLi();
            tq = (Tripleta)q.getDato();
        }
        while (q!=p && tq.getColumna()==tx.getColumna() && tq.getFila()<tx.getFila()) {
            anterior = q;
            q = q.getLi();
            tq = (Tripleta)q.getDato();
        }
        anterior.setLi(x);
        x.setLi(q);
        
    }
    
    public void muestraMatriz(){
        int qf,qc,qv;
        NodoDoble q;
        Tripleta tq;
        q = this.nodoCabeza().getLd();
        while (!this.finDeRecorrido(q)) {
            tq = (Tripleta)q.getDato();
            qf = tq.getFila();
            qc = tq.getColumna();
            qv = (int)tq.getValor();
            System.out.println(qf+" "+qc+" "+qv);
            q = q.getLd();
        }
    }
    
    public int getFila(){
        Tripleta t = (Tripleta)this.primerNodo().getDato();
        return t.getFila();
    }
    
    public int getColumna(){
         Tripleta t = (Tripleta)this.primerNodo().getDato();
         return t.getColumna();
    }
    
    public boolean esSimetrica(){
         Tripleta t1,t2;
         
        if (this.getColumna()!=this.getFila()) {
            return false;
        }

        NodoDoble nod2, nod = this.nodoCabeza().getLd();
        nod2 = this.nodoCabeza().getLi();
        t1= (Tripleta)nod.getDato();
        t2 = (Tripleta)nod2.getDato();
        
        while (!this.finDeRecorrido(nod)) {   
            System.out.println(t2.getValor());
            if (t1.getColumna()!=t2.getFila()||t1.getFila()!=t2.getColumna()) {
                return false;
            }
            if ((int)t1.getValor()!=(int)t2.getValor()) {
                return false;
            }
            nod = nod.getLd();
            nod2 = nod2.getLi();
            t1= (Tripleta)nod.getDato();
            t2 = (Tripleta)nod2.getDato();
        }
        return true;
    }
    
    
    public MatrizForma2 suma(MatrizForma2 b){
        int ma,na,mb,nb,ss;
        NodoDoble p,q,x;
        Tripleta tp,tq,tx;
        p= this.primerNodo();
        q= b.primerNodo();
        tp = (Tripleta) p.getDato();
        tq = (Tripleta) q.getDato();
        ma = tp.getFila();
        na = tp.getColumna();
        mb = tq.getFila();
        nb = tq.getColumna();
        if ((ma!=mb)||(na!=nb)) {
            System.out.println("Matrices de diferente dimensiones no se puede sumar");
            return null;
        }
        MatrizForma2 c = new MatrizForma2(ma,na);
        p = this.nodoCabeza().getLd();
        q = b.nodoCabeza().getLd();
        while (!this.finDeRecorrido(p) && !b.finDeRecorrido(q)) {
            tp = (Tripleta) p.getDato();
            tq = (Tripleta) q.getDato();
            switch (compare(tp.getFila(),tq.getFila())) {
                case -1:
                    x= new NodoDoble(tp);
                    c.conectarPorFilas(x);
                    c.conectarPorColumnas(x);
                    p = p.getLd();
                    break;
                case 1:
                    x = new NodoDoble(tq);
                    c.conectarPorFilas(x);
                    c.conectarPorColumnas(x);
                    q = q.getLd();
                    break;
                case 0:
                    switch (compare(tp.getColumna(), tq.getColumna())) {
                        case -1:
                            x = new NodoDoble(tp);
                            c.conectarPorFilas(x);
                            c.conectarPorColumnas(x);
                            p = p.getLd();
                            break;
                        case 1:
                            x = new NodoDoble(tq);
                            c.conectarPorFilas(x);
                            c.conectarPorColumnas(x);
                            q = q.getLd();
                            break;
                        case 0:
                            ss = (int)tp.getValor()+(int)tq.getValor();
                            if (ss!=0) {
                                tx = new Tripleta(tp.getFila(),tp.getColumna(),ss);
                                x = new NodoDoble(tx);
                                c.conectarPorFilas(x);
                                c.conectarPorColumnas(x);
                                
                            }
                            p = p.getLd();
                            q = q.getLd();
                            break;
                       
                    }
            }
        }
        while (!this.finDeRecorrido(p)) {
            tp = (Tripleta) p.getDato();
            x = new NodoDoble(tp);
            c.conectarPorFilas(x);
            c.conectarPorColumnas(x);
            p = p.getLd();
        }
        while (!b.finDeRecorrido(q)) {
            tq = (Tripleta) q.getDato();
            x = new NodoDoble(tq);
            c.conectarPorFilas(x);
            c.conectarPorColumnas(x);
            q = q.getLd();
        }
        return c;
    }
    
   public int compare(int d1, int d2){
       if (d1<d2) {
           return -1;
       }
       if (d1==d2) {
           return 0 ;
       }
       return 1;
   }
    
    public MatrizForma2 multiplicar(MatrizForma2 b){
        NodoDoble p,q,x,ip;
        Tripleta tp,tq,tx;
        int s, filaActual, columnaActual;
        filaActual = 0;
        columnaActual = 0;
        p = this.primerNodo();
        q = b.primerNodo();
        tp = (Tripleta) p.getDato();
        tq = (Tripleta) q.getDato();
        if (tp.getColumna()!= tq.getFila()) {
            System.out.println("Matrices no se pueden multiplicar");
            return null;
        }
        MatrizForma2 c = new MatrizForma2(tp.getFila(),tq.getColumna());
        p=this.nodoCabeza().getLd();
        while (!this.finDeRecorrido(p)) {
            tp = (Tripleta) p.getDato();
            filaActual = tp.getFila();
            ip = p;
            q = b.nodoCabeza().getLd();
            while (!b.finDeRecorrido(q)) {
                tq = (Tripleta) q.getDato();
                columnaActual = tq.getColumna();
                s = 0;
                while (tp.getFila()==filaActual && tq.getColumna()==columnaActual) {
                    if (tp.getColumna()<tq.getFila()) {
                        p = p.getLd();
                        tp = (Tripleta) p.getDato();
                        continue;
                    }
                    if (tp.getColumna()>tq.getFila()) {
                        q = q.getLi();
                        tq = (Tripleta) q.getDato();
                        continue;
                    }
                    s = s + (int)tp.getValor()*(int)tq.getValor();
                    p=p.getLd();
                    tp = (Tripleta) p.getDato();
                    q = q.getLi();
                    tq = (Tripleta) q.getDato();
                }
                if (s!=0) {
                    tx = new Tripleta(filaActual,columnaActual,s);
                    x = new NodoDoble(tx);
                    c.conectarPorFilas(x);
                    c.conectarPorColumnas(x);
                }
                while (tq.getColumna()==columnaActual) {
                    q = q.getLi();
                    tq = (Tripleta) q.getDato();
                }
                p = ip;
                tp = (Tripleta) p.getDato();
            }
            while (tp.getFila()==filaActual) {
                p = p.getLd();
                tp = (Tripleta) p.getDato();
            }
        }
        return c;
    }
   
    public MatrizForma2 transpuesta(){
//        int m,n;
        NodoDoble p,x;
        Tripleta tp,tx;
        p=this.primerNodo();
        tp = (Tripleta)p.getDato();
        MatrizForma2 a = new MatrizForma2(tp.getColumna(),tp.getFila());
        p = this.primerNodo();
        tp =(Tripleta)p.getDato();
        while (!this.finDeRecorrido(p)) {
            tx = new Tripleta(tp.getColumna(),tp.getFila(),tp.getValor());
            x = new NodoDoble(tx);
            a.conectarPorFilas(x);
            a.conectarPorColumnas(x);
            p = p.getLd();
            tp = (Tripleta)p.getDato();
        }
        return a;
    }
    
    public void puntoDeSilla(){
        int k,psi,psj,numeroFilas,numeroColumnas,filaActual;
        Tripleta tp,ti;
        NodoDoble p,q,vpi[];
        p = this.primerNodo();
        tp = (Tripleta)p.getDato();
        numeroFilas = tp.getFila();
        numeroColumnas = tp.getColumna();
        vpi = new NodoDoble[numeroColumnas + 2];
        q = this.nodoCabeza().getLd();
        
        ti=(Tripleta) q.getDato();
        while (ti.getColumna()!=numeroColumnas) {
            ti=(Tripleta) q.getDato();
            vpi[ti.getColumna()]=q;
            q=q.getLd();
        }
        
        p= this.nodoCabeza().getLd();
        psj=0;
        while (p!=q) {
            k=0;
            tp =(Tripleta)p.getDato();
            filaActual = tp.getFila();
            psi = filaMenorDato(p,numeroColumnas);
            System.out.println("menor fila"+psi);
            if (psi!=0) {
                q =vpi[psi];
                psj = ColumnaMayorDato(q,numeroFilas);
                if (psj==tp.getFila()) {
                    System.out.println("El punto de siila es"+tp.getFila()+" "+psi);
                    return;
                }
            }
            while (tp.getFila()==filaActual) {
                p =p.getLd();
                tp = (Tripleta)p.getDato();
            }
        }
        System.out.println("No hay punto de silla");
    }
    
    public int filaMenorDato(NodoDoble pp, int nc){
        int menor, j, columna, k, p, filaActual;
        NodoDoble qq;
        Tripleta tx;
        menor = 10000;
        qq = pp;
        columna = 0;
        j = 0;
        tx = (Tripleta)pp.getDato();
        filaActual = tx.getFila();
        while (tx.getFila()==filaActual) {
            j = j+1;
            if ((int)tx.getValor()<menor) {
                menor=(int)tx.getValor();
                columna = tx.getColumna();
            }
            pp = pp.getLd();
            tx = (Tripleta)pp.getDato();
        }
        k = 0;
        pp = qq;
        tx = (Tripleta)pp.getDato();
        while (tx.getFila()==filaActual) {
            if ((int)tx.getValor()==menor) {
                k=k+1;
            }
            pp = pp.getLd();
            tx = (Tripleta)pp.getDato();
        }
        switch (nc-j) {
            case 0:
                if (k==1) {
                    return columna;
                }
            break;
            case 1:
                if (menor<0 && k==1) {
                    return columna;
                }
                pp=qq;
                tx = (Tripleta)pp.getDato();
                p=1;
                while (tx.getColumna()==p) {
                    pp=pp.getLd();
                    tx=(Tripleta)pp.getDato();
                    p = p+1;
                }
                return p;
            default:
                if (menor<0 && k==1) {
                    return columna;
                }
        }
        return 0;
    }
    
    public int ColumnaMayorDato(NodoDoble pp, int nc){
        int j,mayor,fila,k,p,columnaActual;
        NodoDoble qq;
        Tripleta tx;
        mayor = -1000;
        fila = 0;
        j =0;
        qq = pp;
        tx = (Tripleta)qq.getDato();
        columnaActual = tx.getColumna();
        while (columnaActual==tx.getColumna()) {
            j = j+1;
            if ((int)tx.getValor()>mayor) {
                mayor=(int)tx.getValor();
                fila = tx.getFila();
            }
            qq =qq.getLi();
            tx = (Tripleta)qq.getDato();
        }
        k=0;
        qq=pp;
        tx=(Tripleta)qq.getDato();
        while (columnaActual==tx.getColumna()) {
            if ((int)tx.getValor()==mayor) {
                k=k+1;
            }
            qq =qq.getLi();
            tx = (Tripleta)qq.getDato();
        }
        switch (nc-j) {
            case 0:
                if (k==1) {
                    return fila;
                }
                break;
            case 1:
                if (mayor>0 && k==1) {
                    return fila;
                }
                qq=pp;
                tx=(Tripleta)qq.getDato();
                p=1;
                while (tx.getFila()==p) {
                    qq = qq.getLi();
                    tx = (Tripleta)qq.getDato();
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
    
    //construir 
    public matrizEnTripletas construyeMatrizEnTripletas(){
        matrizEnTripletas a;
        NodoDoble p;
       int c;
       Tripleta tp,tx,t = new Tripleta(this.getFila(),this.getColumna(),0);
       a = new matrizEnTripletas(t);
       c=0;
       
        p = this.nodoCabeza().getLd();
        tp =(Tripleta)p.getDato();
       
        while (!this.finDeRecorrido(p)) {
            tx = new Tripleta(tp.getFila(),tp.getColumna(),tp.getValor());//intercambie filas por columnas?
            a.insertaTripleta(tx);
            c=c+1;
            p = p.getLd();
            tp =(Tripleta)p.getDato();
        }

       a.asignaNumeroTripletas(c);
       return a;
    }
    
    //construir forma1
     public MatrizForma1 construyeMatrizForma1(){
        
         MatrizForma1 a;
         NodoDoble p,x;
         Tripleta tx, tp;
         a = new MatrizForma1(this.getFila(),this.getColumna());
         a.construyeNodosCabeza();
       
         p = this.nodoCabeza().getLd();
         tp =(Tripleta)p.getDato();
       
        while (!this.finDeRecorrido(p)) {
            tx = new Tripleta(tp.getFila(),tp.getColumna(),tp.getValor());
            x=new NodoDoble(tx);
            a.conectarPorFilas(x);
            a.conectarPorColumnas(x);
            p = p.getLd();
            tp =(Tripleta)p.getDato();
        }

       return a;
    }
    //muestra como matriz
    //intercambiar filas y columnas
     public MatrizForma2 cambiarFilas(int m, int n){
        
         MatrizForma2 a;
         NodoDoble p,x;
         Tripleta tx, tp;
         a = new MatrizForma2(this.getFila(),this.getColumna());
       
         p = this.nodoCabeza().getLd();
         tp =(Tripleta)p.getDato();
       
        while (!this.finDeRecorrido(p)) {
            if (tp.getFila()==m) {
                tx = new Tripleta(n,tp.getColumna(),tp.getValor());
            } else if (tp.getColumna()==n) {
                tx = new Tripleta(m,tp.getColumna(),tp.getValor());
            } else {
                tx = new Tripleta(tp.getFila(),tp.getColumna(),tp.getValor());
            }
            
            x=new NodoDoble(tx);
            a.conectarPorFilas(x);
            a.conectarPorColumnas(x);
            p = p.getLd();
            tp =(Tripleta)p.getDato();
        }

       return a;
    }
     
     public MatrizForma2 cambiarColumnas(int m, int n){
        
         MatrizForma2 a;
         NodoDoble p,x;
         Tripleta tx, tp;
         a = new MatrizForma2(this.getFila(),this.getColumna());
         
       
         p = this.nodoCabeza().getLi();
         tp =(Tripleta)p.getDato();
       
        while (!this.finDeRecorrido(p)) {
            if (tp.getColumna()==m) {
                tx = new Tripleta(tp.getFila(),n,tp.getValor());
            } else if (tp.getColumna()==n) {
                tx = new Tripleta(tp.getFila(),m,tp.getValor());
            } else {
                tx = new Tripleta(tp.getFila(),tp.getColumna(),tp.getValor());
            }
            x=new NodoDoble(tx);
            a.conectarPorFilas(x);
            a.conectarPorColumnas(x);
            p = p.getLi();
            tp =(Tripleta)p.getDato();
        }

       return a;
    }
}
