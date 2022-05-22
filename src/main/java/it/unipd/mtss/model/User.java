////////////////////////////////////////////////////////////////////
// [FEDERICO] [DE SANCTIS] [2009107]
// [GABRIELE] [SARACCO] [2009997]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

import java.time.LocalDate;

public class User {

    String nickname, Nome, Cognome;
    LocalDate dataNascita;

    public User(String nickname, String Nome, String Cognome, LocalDate dataNascita) {
        if(nickname == null) {
            throw new IllegalArgumentException("Nickname nullo");
        }
        if(Nome == null) {
            throw new IllegalArgumentException("Nome non valido");
        }
        if(Cognome == null) {
            throw new IllegalArgumentException("Cognome non valido");
        }
        if(dataNascita == null) {
            throw new IllegalArgumentException("Data non valida");
        }
        this.nickname = nickname;
        this.Nome = Nome;
        this.Cognome = Cognome;
        this.dataNascita = dataNascita;
    }

    public String getNickname(){
        return nickname;
    }

    public String getName() {
        return Nome;
    }

    public String getSurname() {
        return Cognome;
    }

    public int getAge() {
        return LocalDate.now().getYear()-dataNascita.getYear();
    }
}