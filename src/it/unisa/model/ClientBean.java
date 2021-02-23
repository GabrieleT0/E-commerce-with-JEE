package it.unisa.model;
import java.io.Serializable;

import Jbcrtypt.BCrypt;



public class ClientBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	String email;
	String password;
	String nome;
	String cognome;
	String city;
	String provincia;
	String via;
	String numeroCivico;
	String CAP;
	String numeroTelefono;
	
	public ClientBean() {
		email="";
		password="";
		nome="";
		cognome="";
		city="";
		provincia="";
		via="";
		numeroCivico="";
		CAP="";
		numeroTelefono="";
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getCitta() {
		return city;
	}
	public void setCitta(String città) {
		this.city = città;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getVia() {
		return via;
	}
	public void setVia(String via) {
		this.via = via;
	}
	public String getNumerocivico() {
		return numeroCivico;
	}
	public void setNumerocivico(String numeroCivico) {
		this.numeroCivico = numeroCivico;
	}
	public String getcap() {
		return CAP;
	}
	public void setcap(String CAP) {
		this.CAP = CAP;
	}
	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono=numeroTelefono;
	}
	public String getNumeroTelefono() {
		return numeroTelefono;
	}
	
	public boolean isEmpty() {
		return email=="";
	}
	
	public String hashPassword(String plainTextPassword){
		return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
	}
	
	public boolean checkPass(String plainPassword, String hashedPassword) {
		if (BCrypt.checkpw(plainPassword, hashedPassword))
			return true;
		else
			return false;
	}

	
	public boolean equals(Object obj) {
	      if (obj == this) {
	         return true;
	      }
	      if (!(obj instanceof ProductBean)) {
	         return false;
	      }
	      ClientBean bean = (ClientBean) obj;
	      return email.equals(bean.getEmail()) && password.equals(bean.getPassword());
	   }
	
	 public String toString() {
	      return email + " " ;
	   }

}



