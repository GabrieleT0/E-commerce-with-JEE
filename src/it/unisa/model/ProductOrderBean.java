package it.unisa.model;

import java.io.Serializable;

public class ProductOrderBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	String codice;
	String numeroOrdine;
	
	public ProductOrderBean(){
		codice="";
		numeroOrdine="";
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getNumeroOrdine() {
		return numeroOrdine;
	}

	public void setNumeroOrdine(String numeroOrdine) {
		this.numeroOrdine = numeroOrdine;
	}
}
