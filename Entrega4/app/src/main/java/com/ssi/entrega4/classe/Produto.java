package com.ssi.entrega4.classe;

import java.util.ArrayList;
import java.util.List;

public class Produto {
    private String id;
    private String nome;
    //private CorStatic cor;
    private String cor;
    private double precoCusto;
    private double precoVenda;
    private double lucro;
    private int _id;
    private List<Pedido> pedidosProdutos;
    private double valorProdutos;

    public Produto() {}

    public Produto(String id, String nome, String cor, double precoCusto, double precoVenda, double lucro) {
        this.id = id;
        this.nome = nome;
        this.cor = cor;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.lucro = lucro;
    }

    public Produto(String nome, String cor, double precoCusto, double precoVenda) {
        this.nome = nome;
        this.cor = cor;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                "cor='" + cor + '\''+
                ", precoCusto=" + precoCusto +
                ", precoVenda=" + precoVenda +
                ", lucro=" + lucro +
                ", id=" + id +
                '}';
    }

    /*public List<Pedido> getProdutosPedido() {
        return pedidosProdutos;
    }

    public void setProdutosPedido(List<Produto> produtosPedido) {
        this.pedidosProdutos = pedidosProdutos;
    }

    public double getValorProdutos() {
        return getValorProdutos();
    }

    public void setValorProdutos(double valorProdutos) {
        this.valorProdutos = valorProdutos;
    }*/



}
