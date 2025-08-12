use thesnackspot;

INSERT INTO Utente VALUES
/* email, password, nome, cognome, via, città, cellulare, cap, nc, tipo, id guest*/
("silvanacafaro256@gmail.com", "Zzilv1234!!", "Silvana", "Cafaro", "Via Tora", "Gioia Sannitica", 3913622669, 81010, "nc", 1, null),
("isabellasessa2901@gmail.com", "AmoIGattini12()", "Isabella Maria", "Sessa", "Via Manzoni", "Succivo", 3342251379, 81030, "22", 1, null),
("GennaroFoglia@gmail.com", "123Minecraft321&", "Gennaro", "Foglia", "Via Campo", "Giffoni", 3384389003, 84095, "13", 1, null),
("roccasalva.maria@gmail.com", "Maria12345!", "Maria", "Roccasalva", "Via Res", "Parete", 3922669517, 84013, "53", 0, null),
("Gigiotelli@gmail.com", "nonLOso5!", "Luigi", "Matrone", "Via Matteini", "Bosco Reale", 3473738319, 80041, "37", 0, null),
("Natalios@gmail.com", "Scimmia00?", "Natalia", "Abbruzzese", "Via Calab", "Reggio Calabria", 3477435629, 89135, "54", 0, null),
("GiacomoFavale33@gmail.com", "Password00$!", "Giacomo", "Favale", "Via Garibaldi", "Torella Dei Lombardi", 3482726075, 83057, "12A", 0, null),
("GiovanniNigro@gmail.com", "CinaLove9!?", "Giovanni", "Nigro", "Via Asia", "Sarno", 3883298435, 84087, "75", 0, null),
("AlbertoGinolfi@gmail.com", "Ciliegie&Antonio00", "Andrea", "Gisolfi", "Via Non lo so", "Bracigliano", 3895557878, 84082, "49", 0, null),
("AntonioGym@gmail.com", "ILoveGym098!!", "Antonio", "Merola", "Via capaccio", "Paestum", 3517041618, 84047, "19", 0, null),
("TresySorrentino@gmail.com", "Ariana&Ballo11", "Teresa", "Sorrentino", "Via Vrenzola", "Napoli", 3772913431, 80013, "66", 0, null),
("SimonaVigorito@gmail.com", "Matthew44!", "Simona", "Vigorito", "Via pesci", "Salerno", 3455887357, 84121, "1A", 0, null);

INSERT INTO Categorie VALUES
("Bibita"),
("Dolce"),
("Salato");

INSERT INTO Prodotto VALUES
/* Id, nome prezzo, produttore, descrizione, qt*/
("1A", "Doritos original", 1.50, "Frito-Lay", "Patatine gusto tortillas tex mex", 10),
("1B", "Hot Chip Challenge", 9.90, "Frito-Lay", "Patatina piccante", 7),
("1C", "Cheetos Ketchup", 2.99, "Clarence Crane", "Cheetos al ketchup", 15),
("1D", "Cheetos Pizzerini", 2.99, "Clarence Crane", "Cheetos al gusto pizza", 12),
("1E", "Herr's Pizza Curls", 2.40, "James Herr", "Patatine di mais al gusto pizza", 6),
("1F", "Pringles Churrasco", 5.90, "Kellogg's", "Pringles al gusto barbecue", 20),
("1G", "Johnny's Spicy Rings", 1, "Frito-lay", "Anelli di mais alla paprika", 18),
("1H", "Chipoys Original", 2.50, "James Herr", "Tortilla di mais al lime e sale", 8),
("1I", "Iberica Chips Trufa", 2.50, "Iberica Spirit", "patatine al tartufo nero", 13),
("1L", "Iberica Chips Jamon", 2.50, "Iberica Spirit", "patatine al prosciutto", 21),
("1M", "Pringles Ranch", 5.49, "Kellogg's", "Pringles alla salsa ranch", 25),
("1N", "Herr's Jalapeno", 3.50, "James Herr", "Patatine al formaggio piccante", 20),
("1O", "Corn Nuts BBQ", 2, "Clarence Crane", "Chicchi di mais al barbecue", 4),
("1P", "Pop Chips Sea Salt", 1.30, "Frito-Lay", "Patatine con sale marino", 15),
("1Q", "Takis Blue Heat", 4.90, "Clarence Crane", "Patatine di mais piccanti", 25),
("1R", "Reese's Big Cup", 1, "Hersey", "Coppa cioccolato e burro d'arachidi", 30),
("1S", "Reese's Nutrageous", 2, "Hersey", "Barretta cioccolato e arachidi", 25),
("1T", "Cherryhead", 0.50, "Kraft", "caramella alla ciliegia", 10),
("1U", "Slaps Lollipops Mix", 4, "Nabisco", "Camarelle mix frutta", 15),
("1V", "Fluff Marshmallow", 5.30, "Durkee-Mowe", "crema al marshmallow", 35),
("1Z", "Airheads Cherry", 0.80, "Nabisco", "Caramelle alla ciliegia", 23),
("2A", "Ferrero Hanuta", 6, "Ferrero candy", "Wafer con crema alla nocciola", 24),
("2B", "Nikkoh jelly straws", 2.15, "Kraft", "Gelatine alla frutta", 12),
("2C", "Crayon Strawberry", 1.50, "Nabisco", "Caramella alla fragola", 34),
("2D", "Lucky Charms", 11, "Ferrera Candy", "Cereali al marshmallow", 32),
("2E", "Cow Tales Oreo", 0.99, "Kraft", "caramelle con crema oreo", 14),
("2F", "Golden Grahams", 11, "Nabisco", "Cereali mais e grano", 3),
("2G", "Gummi Eyez", 8.70, "Hersey Company", "Caramelle alla frutta", 16),
("2H", "Laffy Taffy Cherry", 0.99, "Durkee-Mowe", "Caramella alla ciliegia", 23),
("2I", "My Cousin", 6.70, "Nabisco", "Caramella alla ciliegia", 15),
("2L", "Jell-o Grape Raisin", 3.24, "Ferrera Candy", "Gelatina all'uva", 17),
("2M", "Fanta Berry ", 2, "Coca-Cola", "Fanta ai mirtilli", 12),
("2N", "Fanta Peach", 2, "Coca-cola", "Fanta alla pesca", 14),
("2O", "Fanta Grape", 2, "Coca-cola", "Fanta all'uva", 17),
("2P", "Fanta Pineapple", 2, "Coca-cola", "Fanta all'ananas", 12),
("2Q", "Fanta Lemon", 2, "Coca-cola", "Fanta al limone", 21),
("2R", "Dr Pepper", 2, "Coca-cola", "Bevanda americana", 30),
("2S", "Monster Assault", 4.90, "Monster Beverage", "Monster al ginseng", 20),
("2T", "Monster Blue", 4.90, "Monster Beverage", "Monster al lampone", 23),
("2U", "Monster Khaotic", 4.90, "Monster Beverage", "Monster all'arancia", 18),
("2V", "Monster Monarch", 4.90, "Monster Beverage", "Monster alla pesca", 27),
("2Z", "Monster Ripper", 4.90, "Monster Beverage", "Monster agli agrumi", 29),
("3A", "7 Up Mojito", 2.30, "krafte", "7 up al mojito", 7),
("3B", "Kool Aid Grape", 0.99, "Monster Beverage", "Kraft", 14),
("3C", "Mountain Dew", 3.40, "Kraft", "Bustina all'uva", 11);

INSERT INTO Allergeni VALUES
("Glutine"),
("Frutta a guscio"),
("Lattosio"),
("Uovo");

INSERT INTO Ass_all VALUES
/* nome all, id prodotto*/
("Glutine", "2S"),
("Glutine", "2T"),
("Glutine", "2U"),
("Glutine", "2V"),
("Glutine", "2Z"),
("Lattosio", "1R"),
("Lattosio", "1S"),
("Frutta a guscio", "1S"),
("Lattosio", "2A"),
("Glutine", "2A"),
("Frutta a guscio", "2A"),
("Glutine", "1A"),
("Glutine", "1B"),
("Glutine", "1C"),
("Glutine", "1D"),
("Glutine", "1E"),
("Glutine", "1F"),
("Glutine", "1L"),
("Glutine", "1M"),
("Glutine", "1N"),
("Glutine", "1O"),
("Glutine", "1P"),
("Glutine", "1Q");

INSERT INTO Carrello VALUES
/*id_carrello, sub totale, id guest, email */
(null, 0.99, null, "roccasalva.maria@gmail.com"),
(null, 16.4, null, "Gigiotelli@gmail.com"),
(null, 22, null, "TresySorrentino@gmail.com"),
(null, 24.5, null, "Natalios@gmail.com"),
(null, 3, null, "GiacomoFavale33@gmail.com"),
(null, 16.99, null, "GiovanniNigro@gmail.com"),
(null, 0, null, "AlbertoGinolfi@gmail.com"),
(null, 24.50, null, "AntonioGym@gmail.com"),
(null, 0, null, "SimonaVigorito@gmail.com");

INSERT INTO Composizione VALUES
/*Id carrelo id prodotto qt*/
("1", "3B", 1),
("2", "3C", 1),
("2", "1N", 1),
("2", "1L", 1),
("2", "2A", 1),
("2", "1R", 1),
("3", "2D", 2),
("4", "1T", 3),
("4", "1V", 1),
("4", "2I", 1),
("4", "2D", 1),
("5", "2N", 1),
("5", "1R", 1),
("6", "1C", 1),
("6", "1V", 1),
("6", "2G", 1),
("8", "2T", 5);

INSERT INTO Pagamento VALUES
/* n conto, intestatariom metodo, scadenzam, cvv nykl*/
("1235 9384 3948 4720", "Maria Roccasalva", "mastercard", "05/2025", 567),
("2948 2948 3820 8203", "Antonio Merola", "visa", "12/2024", 322),
("1234 1345 2321 4345", "Giovanni Nigro", "visa", "09/2024", 153),
("8430 2742 1382 4820", "Teresa Sorrentino", "mastercard", "10/2027", 543),
("0391 4810 3820 3802", "Giacomo Favale", "visa", "01/2026", 642),
("1284 2719 1391 4628", "Luigi Matrone", "mastercard", "07/2025", 472);

INSERT INTO Ordine VALUES
/*(n ordine, stato, totale, data ordine, data consegna, nc onto, id carrello);*/
(null, "Consegnato", 0.99, "01/04/2020", "14/04/2020", "1235 9384 3948 4720", "1"),
(null, "Consegnato", 16.4, "17/05/2020", "4/06/2020", "1284 2719 1391 4628", "2"),
(null, "Consegnato", 24.50, "23/08/2021", "06/09/2021", "2948 2948 3820 8203","8"),
(null, "Consegnato", 3, "10/11/2022", "24/11/2022", "0391 4810 3820 3802", "5"),
(null, "In Consegna", 22, "08/07/2023", "24/07/2023", "8430 2742 1382 4820", "3"),
(null, "In Consegna", 16.99, "10/07/2023", "26/07/2023", "1234 1345 2321 4345", "6");

INSERT INTO Effettua VALUES 
/* n conto email */
("1235 9384 3948 4720", "roccasalva.maria@gmail.com"),
("1284 2719 1391 4628", "Gigiotelli@gmail.com"),
("2948 2948 3820 8203", "AntonioGym@gmail.com"),
("0391 4810 3820 3802", "GiacomoFavale33@gmail.com"),
("8430 2742 1382 4820", "TresySorrentino@gmail.com"),
("1234 1345 2321 4345", "GiovanniNigro@gmail.com");

INSERT INTO Aggiunta VALUES
/*nome cat id prodotto*/
("Salato", "1A"),
("Salato", "1B"),
("Salato", "1C"),
("Salato", "1D"),
("Salato", "1E"),
("Salato", "1F"),
("Salato", "1G"),
("Salato", "1H"),
("Salato", "1I"),
("Salato", "1L"),
("Salato", "1M"),
("Salato", "1N"),
("Salato", "1O"),
("Salato", "1P"),
("Salato", "1Q"),
("Dolce", "1R"),
("Dolce", "1S"),
("Dolce", "1T"),
("Dolce", "1U"),
("Dolce", "1V"),
("Dolce", "1Z"),
("Dolce", "2A"),
("Dolce", "2B"),
("Dolce", "2C"),
("Dolce", "2D"),
("Dolce", "2E"),
("Dolce", "2F"),
("Dolce", "2G"),
("Dolce", "2H"),
("Dolce", "2I"),
("Dolce", "2L"),
("Bibita", "2M"),
("Bibita", "2N"),
("Bibita", "2O"),
("Bibita", "2P"),
("Bibita", "2Q"),
("Bibita", "2R"),
("Bibita", "2S"),
("Bibita", "2T"),
("Bibita", "2U"),
("Bibita", "2V"),
("Bibita", "2Z"),
("Bibita", "3A"),
("Bibita", "3B"),
("Bibita", "3C");
