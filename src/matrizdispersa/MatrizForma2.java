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
    
//    public MatrizForma2 suma(MatrizForma2 b){}
//    public MatrizForma2 multiplicar(MatrizForma2 b){}
//    public MatrizForma2 transpuesta(){}
//    public void puntoDeSilla(){}
    //essimetrica
    //construir tripletas
    //construir forma1
    //muestra como matriz
    
}
