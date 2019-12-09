package com.ssi.entrega4.classe;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Pedido {
    private String id;
    private String nomeCliente;
    private String telefone;
    private String dataPedido;
    private List<Produto> produtosPedido;
    private double valorPedido;




    public Pedido() {}

    public Pedido(String nomeCliente, String telefone, String dataPedido, List<Produto> produtosPedido, double valorPedido) {
        this.nomeCliente = nomeCliente;
        this.telefone = telefone;
        this.dataPedido = dataPedido;
        this.produtosPedido = produtosPedido;
        this.valorPedido = valorPedido;
    }

    public Pedido(String id, String nomeCliente, String telefone, String dataPedido, double valorPedido) {
        this.nomeCliente = nomeCliente;
        this.telefone = telefone;
        this.dataPedido = dataPedido;
        this.valorPedido = valorPedido;
        this.id = id;
    }


    public Pedido(String nomeCliente, String telefone, String dataPedido, double valorPedido) {
        this.nomeCliente = nomeCliente;
        this.telefone = telefone;
        this.dataPedido = dataPedido;
        this.valorPedido = valorPedido;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

   /* public static List<Pedido> inicializaLista(){
        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(new Pedido("x123", "Cláudia", "995054050", "12/12/12", 3.4));
        pedidos.add(new Pedido("a123", "João", "96448442", "06/04/1988", 2.6));
        return pedidos;
    }*/
}
