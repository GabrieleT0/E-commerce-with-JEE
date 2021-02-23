DROP DATABASE IF EXISTS ecommerce;
CREATE DATABASE ecommerce; 
DROP USER IF EXISTS 'gabriele'@'localhost';
CREATE USER 'gabriele'@'localhost'IDENTIFIED BY '123456';
GRANT ALL ON ecommerce.*TO'gabriele'@'localhost';
USE ecommerce;

CREATE TABLE Cliente(
	email CHAR(100) NOT NULL,
    psw CHAR(64),
    nome CHAR(50) NOT NULL,
    cognome CHAR(50) NOT NULL,21
    città CHAR(50) NOT NULL,
    provincia CHAR(5) NOT NULL,
    via CHAR(150) NOT NULL,
	numeroCivico INT,
    cap INT,
    PRIMARY KEY(email)
);
CREATE TABLE Ordine(
numeroOrdine CHAR(15) NOT NULL,
dataOrdine TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
importoOrdine FLOAT(10),
email CHAR(100) ,
statoDiElaborazione CHAR(30),
FOREIGN KEY(email) REFERENCES Cliente(email),
PRIMARY KEY(numeroOrdine)
);
 CREATE TABLE Pagamento(
	IDpagamento CHAR(5),
    valuta CHAR(10),
    importo FLOAT(10),
    dataPagamento TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    numeroOrdine CHAR (15),
    FOREIGN KEY (numeroOrdine) REFERENCES Ordine(numeroOrdine),
    PRIMARY KEY(IDpagamento),
    UNIQUE(numeroOrdine)
    );
CREATE TABLE Contrassegno(
	città CHAR(100) NOT NULL,
    provincia CHAR(5) NOT NULL,
    CAP INT NOT NULL,
    via CHAR(150) NOT NULL,
    numeroCivico INT,
    IDpagamento CHAR(5),
    FOREIGN KEY(IDpagamento) REFERENCES Pagamento(IDpagamento)
    );

CREATE TABLE CartaDiCredito(
	numeroCarta CHAR(16) NOT NULL,
    intestatarioCarta CHAR(100) NOT NULL,
    dataScadenza CHAR(5) NOT NULL,
    IDpagamento CHAR(5),
    FOREIGN KEY(IDpagamento) REFERENCES Pagamento(IDpagamento)
    );
CREATE TABLE PayPal(
	email CHAR(150),
    psw CHAR(150),
	IDpagamento CHAR(5),
	FOREIGN KEY(IDpagamento) REFERENCES Pagamento(IDpagamento),
    UNIQUE (IDpagamento)
);
CREATE TABLE AziendaCostruttrice(
IDazienda CHAR(5),
PRIMARY KEY(IDazienda)
);
CREATE TABLE Prodotto(
	codice CHAR(20) NOT NULL,
	nome TEXT(500) NOT NULL,
	descrizione TEXT(500) NOT NULL,
	categoria CHAR(20),
	prezzo  FLOAT(10),
    PRIMARY KEY(codice)
);
CREATE TABLE Gestore(
IDgestore INT AUTO_INCREMENT NOT NULL,
codiceFiscale CHAR(16),
ragioneSociale CHAR(50),
IDazienda CHAR(5),
FOREIGN KEY(IDazienda) REFERENCES AziendaCostruttrice(IDazienda),
PRIMARY KEY(IDgestore),
UNIQUE(IDazienda)
);
CREATE TABLE Magazzino(
IDmagazzino INT,
PRIMARY KEY(IDmagazzino)
);
CREATE TABLE risiede(
disponibilità INT,
codice CHAR(20),
IDmagazzino INT,
FOREIGN KEY(codice) REFERENCES Prodotto(codice) ON DELETE CASCADE,
FOREIGN KEY(IDmagazzino) REFERENCES Magazzino(IDmagazzino)
);
CREATE TABLE composto(
quantità INT,
codice CHAR(20),
numeroOrdine CHAR(15),
FOREIGN KEY(numeroOrdine) REFERENCES Ordine(numeroOrdine),
FOREIGN KEY(codice) REFERENCES Prodotto(codice) ON DELETE CASCADE
);
CREATE TABLE costruito(
codice CHAR(20),
IDazienda CHAR(5),
FOREIGN KEY(codice) REFERENCES Prodotto(codice) ON DELETE CASCADE,
FOREIGN KEY(IDazienda) REFERENCES aziendaCostruttrice(IDazienda)
);







