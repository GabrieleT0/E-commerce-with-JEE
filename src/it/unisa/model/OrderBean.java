package it.unisa.model;

import java.io.Serializable;

public class OrderBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	String numeroOrdine;
	String dataOrdine;
	String importoOrdine;
	String email;
	String tipoPagamento;


public OrderBean() {
	numeroOrdine="";
	dataOrdine="";
	importoOrdine="";
	email="";
	tipoPagamento="";
}


public String getNumeroOrdine() {
	return numeroOrdine;
}


public void setNumeroOrdine(String numeroOrdine) {
	this.numeroOrdine = numeroOrdine;
}


public String getDataOrdine() {
	return dataOrdine;
}


public void setDataOrdine(String dataOrdine) {
	this.dataOrdine = dataOrdine;
}


public String getImportoOrdine() {
	return importoOrdine;
}


public void setImportoOrdine(String importoOrdine) {
	this.importoOrdine = importoOrdine;
}


public String getEmail() {
	return email;
}


public void setEmail(String email) {
	this.email = email;
}


public String geTipoPagamento() {
	return tipoPagamento;
}


public void setTipoPagamento(String tipoPagamento) {
	this.tipoPagamento = tipoPagamento;
}

public boolean isEmpty() {
	return numeroOrdine == "";
}




}
