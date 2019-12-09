package com.ssi.entrega2.model;

import android.util.Log;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Pedido {
    private String nomeCliente;
    private String telefone;
    private String dataPedido;
    private List<Produto> produtosPedido;
    private double valorPedido;
    private int _id;


    public Pedido() {}

    public Pedido(String nomeCliente, String telefone, String dataPedido, List<Produto> produtosPedido, double valorPedido) {
        this.nomeCliente = nomeCliente;
        this.telefone = telefone;
        this.dataPedido = dataPedido;
        this.produtosPedido = produtosPedido;
        this.valorPedido = valorPedido;
    }

    public Pedido(String nomeCliente, String telefone, String dataPedido, double valorPedido, int _id) {
        this.nomeCliente = nomeCliente;
        this.telefone = telefone;
        this.dataPedido = dataPedido;
        this.valorPedido = valorPedido;
        this._id = _id;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(String dataPedido) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Calendar cal = Calendar.getInstance();
        Date data = new Date();
        cal.setTime(data);
        Date data_atual = cal.getTime();
        String dataString = dateFormat.format(data_atual);
        Log.i("data_atual", data_atual.toString());
        this.dataPedido = dataString;
    }

    public List<Produto> getProdutosPedido() {
        return produtosPedido;
    }

    public void setProdutosPedido(List<Produto> produtosPedido) {
        this.produtosPedido = produtosPedido;
    }

    public double getValorPedido() {
        return valorPedido;
    }

    public void setValorPedido(double valorPedido) {
        this.valorPedido = valorPedido;
    }
}
