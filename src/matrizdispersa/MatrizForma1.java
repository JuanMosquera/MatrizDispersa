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
        for (i = 1; i < mayor; i++) {
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
    
//    public MatrizForma1 suma(MatrizForma1 b){}
//    public MatrizForma1 multiplicar(MatrizForma1 b){}
//    public MatrizForma1 transpuesta(){}
//    public void puntoDeSilla(){}
}
