package it.unisa.model;
import java.io.Serializable;


public class ProductBean implements Serializable {
	private static final long serialVersionUID = 1L;
	String codice;
	String nome;
	String descrizione;
	Double prezzo;
	
	public ProductBean() {
		codice="";
		nome="";
		descrizione="";
		prezzo=0.00;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		 this.prezzo=prezzo;
	}

	public boolean isEmpty() {
		return codice == "";
	}
	
	
	@Override
	public String toString() {
		return nome +" [" +codice+ "] " +prezzo+" ," +descrizione;  
	}
	
	@Override
	 public boolean equals(Object obj) {
	      if (obj == this) {
	         return true;
	      }
	      if (!(obj instanceof ProductBean)) {
	         return false;
	      }
	      ProductBean bean = (ProductBean) obj;
	      return codice.equals(bean.getCodice());
	   }
	}
