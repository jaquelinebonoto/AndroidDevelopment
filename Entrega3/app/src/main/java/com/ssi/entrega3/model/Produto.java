package com.ssi.entrega3.model;

import java.util.ArrayList;
import java.util.List;

public class Produto {
        private String nome;
        //private CorStatic cor;
        private String cor;
        private double precoCusto;
        private double precoVenda;
        private double lucro;
        private int _id;


        public Produto() {}

        public Produto(String nome, String cor, double precoCusto, double precoVenda, double lucro, int _id) {
            this.nome = nome;
            this.cor = cor;
            this.precoCusto = precoCusto;
            this.precoVenda = precoVenda;
            this.lucro = lucro;
            this._id = _id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getCor() {
            return cor;
        }

        public void setCor(String cor) {
            this.cor = cor;
        }

        public double getPrecoCusto() {
            return precoCusto;
        }

        public void setPrecoCusto(double precoCusto) {
            this.precoCusto = precoCusto;
        }

        public double getPrecoVenda() {
            return precoVenda;
        }

        public void setPrecoVenda(double precoVenda) {
            this.precoVenda = precoVenda;
        }

        public double getLucro() {
            return lucro;
        }

        public void setLucro(double lucro) {
            calculaLucro(this.getPrecoVenda(), this.getPrecoCusto());
        }

        public double calculaLucro(double precoVenda, double precoCusto){
            lucro = (this.precoVenda-this.precoCusto);
            return lucro;
        }

        public int get_id() {
            return _id;
        }

        public void set_id(int _id){
            this._id = _id;
        }

        @Override
        public String toString() {
            return "Produto{" +
                    "nome='" + nome + '\'' +
                    "cor='" + cor + '\''+
                    ", precoCusto=" + precoCusto +
                    ", precoVenda=" + precoVenda +
                    ", lucro=" + lucro +
                    ", _id=" + _id +
                    '}';
        }


    public static List<Produto> inicializaLista(){
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto("Calcinha", "Azul", 2.5, 2.6, 3.4, 1));
        produtos.add(new Produto("Sutiã", "Azul", 2.5, 2.6, 3.4, 1));
        return produtos;
    }

}
