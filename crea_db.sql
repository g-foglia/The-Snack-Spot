DROP DATABASE IF EXISTS thesnackspot;
CREATE DATABASE thesnackspot;
USE thesnackspot;

CREATE TABLE Guest(
	id_guest varchar(50) primary key not null
);

CREATE TABLE Utente(
    email varchar(30) primary key,
    password_ varchar(20) not null,
	nome varchar(20) not null,
    cognome varchar(20) not null,
    via varchar(20) not null,
    città varchar(20) not null,
    cellulare varchar(10) not null,
    cap int not null,
    nc varchar(10) not null,
    tipo boolean not null,
    id_guest varchar(50),
    FOREIGN KEY(id_guest) REFERENCES Guest(id_guest)
);
    
    CREATE TABLE categorie(
	nome_cat varchar(30) primary key
);
    
    
CREATE TABLE Prodotto(
	id_prodotto varchar(5) primary key not null,
    nome_prodotto varchar(40) not null,
    prezzo double not null,
    produttore varchar(20) not null,
    descrizione varchar(50) not null,
    qt_deposito int not null
);
    
 CREATE TABLE Allergeni(
	nome_all varchar(20) primary key
);   
    
CREATE TABLE ass_all(
	nome_all varchar(20) not null,
    id_prodotto varchar(5),
    PRIMARY KEY(nome_all, id_prodotto),
    FOREIGN KEY(nome_all) REFERENCES allergeni(nome_all),
    FOREIGN KEY(id_prodotto) REFERENCES prodotto(id_prodotto)
);   
    
CREATE TABLE Carrello(
	id_carrello int primary key auto_increment,
	sub_totale double not null,
	id_guest varchar(50),
    email varchar(30),
    FOREIGN KEY(id_guest) REFERENCES Guest(id_guest),
    FOREIGN KEY(email) REFERENCES Utente(email)
);

CREATE TABLE Composizione(
	id_carrello int not null,
    id_prodotto varchar(5) not null,
    quantità int not null,
    PRIMARY KEY(id_carrello, id_prodotto),
    FOREIGN KEY(id_carrello) REFERENCES Carrello(id_carrello),
    FOREIGN KEY(id_prodotto) REFERENCES prodotto(id_prodotto)
);

CREATE TABLE Pagamento(
	n_conto varchar(30) primary key,
	intestatario varchar(20) not null,
    metodo varchar(20) not null,
    scadenza varchar(12) not null,
    cvv int
);

CREATE TABLE Ordine(
	n_ordine int primary key auto_increment,
    stato varchar(20) not null,
    totale float not null,
    data_ordine varchar(12) not null,
    data_consegna varchar(12) not null,
    n_conto varchar(30) not null,
    id_carrello int not null,
    FOREIGN KEY(n_conto) REFERENCES pagamento(n_conto),
    FOREIGN KEY(id_carrello) REFERENCES carrello(id_carrello)
    );

CREATE TABLE effettua(
	n_conto varchar(30) not null,
    email varchar(30) not null,
    PRIMARY KEY (n_conto, email),
    FOREIGN KEY(n_conto) REFERENCES pagamento(n_conto),
    FOREIGN KEY(email) REFERENCES Utente(email)
    );
    
CREATE TABLE Aggiunta(
    nome_cat varchar(30) not null,
    id_prodotto varchar(5) not null,
    PRIMARY KEY (nome_cat, id_prodotto),
    FOREIGN KEY(nome_cat) REFERENCES categorie(nome_cat),
    FOREIGN KEY(id_prodotto) REFERENCES prodotto(id_prodotto)    
    );

