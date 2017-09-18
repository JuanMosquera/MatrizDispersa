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
public class MatrizForma1 {
    private NodoDoble mat;

    public MatrizForma1(int m, int n) {
        Tripleta t = new Tripleta(m,n,null);
        mat = new NodoDoble(t);
        t.setValor(mat);
        mat.setDato(t);
    }
    
    public NodoDoble nodoCabeza(){
        return mat;
    }
    
    public NodoDoble primerNodo(){
        Tripleta tp = (Tripleta)mat.getDato();
        NodoDoble p = (NodoDoble)tp.getValor();
        return p;
    }
    
    public boolean esVacia(){
        NodoDoble p = this.primerNodo();
        return p==mat;
    }
    
    public boolean finDeRecorrido(NodoDoble p){
        return p==mat;
    }
    
    public void construyeNodosCabeza(){
        int mayor,i,m,n;
        NodoDoble x,ultimo;
        Tripleta t;
        ultimo = nodoCabeza();
        t = (Tripleta)ultimo.getDato();
        m = t.getFila();
        n = t.getColumna();
        mayor = m;
        if (n>m) {
            mayor = n;
        }
        for (i = 1; i <= mayor; i++) {//ojo 
            t = new Tripleta(i,i,this.nodoCabeza());
            x = new NodoDoble(t);
            x.setLd(x);
            x.setLi(x);
            t = (Tripleta)ultimo.getDato();
            t.setValor(x);
            ultimo.setDato(t);
            ultimo = x;
        }
    }
    
    
    public void conectarPorFilas(NodoDoble x){
        NodoDoble p,q,anterior;
        Tripleta tp,tq,tx;
        int i;
        tx = (Tripleta)x.getDato();
        p = this.primerNodo();
        for (i = 1; i <tx.getFila() ; i++) {
            tp = (Tripleta)p.getDato();
            p = (NodoDoble)tp.getValor();
        }
        anterior = p;
        q = p.getLd(); 
        tq = (Tripleta)q.getDato();
        while ((q!=p)&&(tq.getColumna()<tx.getColumna())) {
            anterior = q;
            q = q.getLd();
            tq = (Tripleta)q.getDato();
        }
        anterior.setLd(x);
        x.setLd(q);
    }
    
    public void conectarPorColumnas(NodoDoble x){
        NodoDoble p,q,anterior;
        Tripleta tp,tq,tx;
        int i;
        tx = (Tripleta)x.getDato();
        p = this.primerNodo();
        for (i = 1; i < tx.getColumna(); i++) {
            tp = (Tripleta)p.getDato();
            p = (NodoDoble)tp.getValor();
        }
        anterior = p;
        q = p.getLi();
        tq = (Tripleta)q.getDato();
        while ((q!=p)&&(tq.getFila()<tx.getFila())) {
            anterior = q;
            q = q.getLi();
            tq = (Tripleta)q.getDato();
        }
        anterior.setLi(x);
        x.setLi(q);
    }
    
    public void muestraMatriz(){
        int qf, qc, qv;
        NodoDoble p,q;
        Tripleta tq,tp;
        p = primerNodo();
        while (!this.finDeRecorrido(p)) {
            q = p.getLd();
            while (q!=p) {
                tq = (Tripleta)q.getDato();
                qf = tq.getFila();
                qc = tq.getColumna();
                qv = (int)tq.getValor();
                System.out.println(qf+" "+qc+" "+qv);
                q = q.getLd();
            }
            tp = (Tripleta)p.getDato();
            p = (NodoDoble)tp.getValor();
        }
    }
    

    
    public MatrizForma1 suma(MatrizForma1 b){
        int ma, na, mb, nb, ss;
        NodoDoble p, q, r, s, x;
        Tripleta tp, tq, tr, ts, tx;
        MatrizForma1 c;
        p = this.nodoCabeza();
        q = b.nodoCabeza();
        tp = (Tripleta)p.getDato();
        tq = (Tripleta)q.getDato();
        ma = tp.getFila();
        na = tp.getColumna();
        mb = tq.getFila();
        nb = tq.getColumna();
        if (ma!=mb || na!=nb) {
            System.out.println("Matrices de diferentes dimensiones no se puede sumar");
            return null;
        }
        c = new MatrizForma1(ma,na);
        c.construyeNodosCabeza();
        p = this.primerNodo();
        q = b.primerNodo();
        while (!this.finDeRecorrido(p)) {
            r = p.getLd();
            s = q.getLd();
            while ((r!=p)&&(s!=q)) {
                tr = (Tripleta)r.getDato();
                ts = (Tripleta)s.getDato();
                switch (compare(tr.getFila(),ts.getFila())) {
                    case -1:
                        x = new NodoDoble(tr);
                        c.conectarPorFilas(x);
                        c.conectarPorColumnas(x);
                        r = r.getLd();
                        break;
                    case 1:
                        x = new NodoDoble(ts);
                        c.conectarPorFilas(x);
                        c.conectarPorColumnas(x);
                        s = s.getLd();
                        break;
                    case 0:
                        switch (compare(tr.getColumna(),ts.getColumna())) {
                            case -1:
                                x = new NodoDoble(tr);
                                c.conectarPorFilas(x);
                                c.conectarPorColumnas(x);
                                r = r.getLd();
                                break;
                            case 1:
                                x = new NodoDoble(ts);
                                c.conectarPorFilas(x);
                                c.conectarPorColumnas(x);
                                s = s.getLd();
                                break;
                            case 0:
                               ss = (int)tr.getValor() + (int)ts.getValor();
                                if (ss!=0) {
                                    tx = new Tripleta(tr.getFila(),tr.getColumna(),ss);
                                    x = new NodoDoble(tx);
                                    c.conectarPorFilas(x);
                                    c.conectarPorColumnas(x);
                                }
                                r = r.getLd();
                                s = s.getLd();
                                break;
                        }
                }
            }
            while (r!=p) {
                tr = (Tripleta)r.getDato();
                x = new NodoDoble(tr);
                c.conectarPorFilas(x);
                c.conectarPorColumnas(x);
                r = r.getLd();
            }
            while (s != q) {
                ts = (Tripleta)s.getDato();
                x = new NodoDoble(ts);
                c.conectarPorFilas(x);
                c.conectarPorColumnas(x);
                s = s.getLd();
            }
            tp = (Tripleta)p.getDato();
            p = (NodoDoble)tp.getValor();
            tq = (Tripleta)q.getDato();
            q = (NodoDoble)tq.getValor();
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
    
    public MatrizForma1 multiplicar(MatrizForma1 b){
        NodoDoble p,q,r,t,x;
        Tripleta tp, tq, tr, tt, tx;
        int s, fila, columna;
        MatrizForma1 c;
        p = this.nodoCabeza();
        q = b.nodoCabeza();
        tp = (Tripleta)p.getDato();
        tq = (Tripleta)q.getDato();
        if (tp.getColumna()!=tq.getFila()) {
            System.out.println("las matrices no se pueden multiplicar");
            return null;
        }
        c = new MatrizForma1(tp.getFila(),tq.getColumna());
        c.construyeNodosCabeza();
        p = this.primerNodo();
        while (!this.finDeRecorrido(p)) {
            q = b.primerNodo();
            while (!b.finDeRecorrido(q)) {
                s = 0;
                r = p.getLd();
                t = q.getLi();
                tr = (Tripleta)r.getDato();
                tt = (Tripleta)t.getDato();
                fila = tr.getFila();
                columna = tt.getColumna();
                while (r!=p && t!=q) {
                    tr = (Tripleta)r.getDato();
                    tt = (Tripleta)t.getDato();
                    switch (compare(tr.getColumna(),tt.getFila())) {
                        case -1:
                            r=t.getLd();
                            break;
                        case 1:
                            t=t.getLi();
                            break;
                        case 0:
                            s = s+(int)tr.getValor()*(int)tt.getValor();
                            r = r.getLd();
                            t = t.getLi();
                            break;
                            
                    }
                }
                if (s!=0) {
                    tx = new Tripleta(fila,columna,s);
                    x = new NodoDoble(tx);
                    c.conectarPorFilas(x);
                    c.conectarPorColumnas(x);
                }
                tq = (Tripleta)q.getDato();
                q = (NodoDoble)tq.getValor();
                
            }
            tp = (Tripleta)p.getDato();
            p = (NodoDoble)tp.getValor();
        }
        return c;
    }
     
     
    public MatrizForma1 transpuesta(){
        NodoDoble p,q,x;
        Tripleta tp,tq,tx;
        p = this.nodoCabeza();
        tp = (Tripleta)p.getDato();
        MatrizForma1 a = new MatrizForma1(tp.getColumna(),tp.getFila());
        a.construyeNodosCabeza();
        p = this.primerNodo();
        while (!this.finDeRecorrido(p)) {
            q = p.getLd();
            tq = (Tripleta)q.getDato();
            while (q!=p) {
                tx = new Tripleta(tq.getColumna(),tq.getFila(),tq.getValor());
                x = new NodoDoble(tx);
                a.conectarPorFilas(x);
                a.conectarPorColumnas(x);
                q = q.getLd();
                tq = (Tripleta)q.getDato();
            }
            tp =(Tripleta)p.getDato();
            p = (NodoDoble)tp.getValor();
        }
        return a;
    }
    
    public void puntoDeSilla(){
       int k,psi,psj,numeroFilas,numeroColumnas;
       Tripleta tx,tq,tp;
       NodoDoble cab,p,q;
       cab = this.nodoCabeza();
       tx = (Tripleta)cab.getDato();
       numeroFilas = tx.getFila();
       numeroColumnas = tx.getColumna();
       psi = 0;
       p = this.primerNodo();
        while (!this.finDeRecorrido(p)) {
            tp = (Tripleta)p.getDato();
            psi = filaMenorDato(p,numeroColumnas);
            if (psi!=0) {
                q = this.primerNodo();
                for (k = 1; k < psi; k++) {
                    tq = (Tripleta)q.getDato();
                    q = (NodoDoble)tq.getValor();
                }
                psj = columnaMayorDato(q,numeroFilas);
                if (psj==tp.getFila()) {
                    System.out.println("El punto de silla es "+tp.getFila()+" "+psi);
                    return;
                }
            }
            tp = (Tripleta)p.getDato();
            p = (NodoDoble)tp.getValor();
        }
        System.out.println("No hay punto de silla");
    }
    
    public int filaMenorDato(NodoDoble pp, int nc){
        int menor,j,columna,k,p;
        NodoDoble qq;
        Tripleta tx;
        menor = 10000;
        columna = 0;
        j=0;
        qq = pp.getLd();
        while (qq != pp) {
            j= j+1;
            tx = (Tripleta)qq.getDato();
            if ((int)tx.getValor()< menor) {
                menor = (int)tx.getValor();
                columna = tx.getColumna();
            }
            qq = qq.getLd();
        }
        k = 0;
        qq = pp.getLd();
        while (qq != pp) {
            tx = (Tripleta)qq.getDato();
            if ((int)tx.getValor()==menor) {
                k=k+1;
            }
            qq = qq.getLd();
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
                if (menor<0) {
                    return 0;
                }
                qq = pp.getLd();
                tx = (Tripleta)qq.getDato();
                p = 1;
                while (tx.getColumna()==p) {
                    qq = qq.getLd();
                    tx = (Tripleta)qq.getDato();
                    p = p+1;
                }
                return p ;
            default:
                if (menor<0 && k==1) {
                    return columna;
                }
        }
        return 0;
    }
    
    public int columnaMayorDato(NodoDoble pp, int nc){
        int j, mayor,fila, k,p;
        NodoDoble qq;
        Tripleta tx;
        mayor = -1000;
        fila = 0;
        j = 0;
        qq = pp.getLi();
        while (qq != pp) {
            j=j+1;
            tx = (Tripleta)qq.getDato();
            if ((int)tx.getValor()>mayor) {
                mayor = (int)tx.getValor();
                fila = tx.getFila();
            }
            qq = qq.getLi();
        }
        k=0;
        qq = pp.getLi();
        while (qq != pp) {
            tx = (Tripleta)qq.getDato();
            if ((int)tx.getValor()==mayor) {
                k=k+1;
            }
            qq = qq.getLi();
        }
        switch (nc-j) {
            case 0:
                if (k==1) {
                    return fila;
                }
            case 1:
                if (mayor > 0 && k==1) {
                    return fila;
                }
                if (mayor > 0) {
                    return 0;
                }
                qq = pp.getLi();
                tx = (Tripleta)qq.getDato();
                p=1;
                while (tx.getFila()==p) {
                    qq = qq.getLi();
                    tx = (Tripleta)qq.getDato();
                    p = p+1;
                }
                return p;
            default:
                if (mayor > 0 && k==1) {
                    return fila;
                }
        }
        return 0;
    }
    //essimetrica
    //construir matriz entripletas
    //construir forma2
    //muestracomo matriz
    
}
