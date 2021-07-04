package com.example.grafic;

import java.io.Serializable;
import java.util.Date;

enum Gen {ROMANTIC, SF, COMEDIE}

public class Film implements Serializable {

    private String titlu;
    private int pret;
    private Gen gen;
    private Date releaseDate;
    private String descriere;

    public Film() {
        this.gen=Gen.COMEDIE;
    }

    public Film(String titlu, int pret, Gen gen, Date releaseDate, String descriere) {
        this.titlu = titlu;
        this.pret = pret;
        this.gen = gen;
        this.releaseDate = releaseDate;
        this.descriere = descriere;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    public Gen getGen() {
        return gen;
    }

    public void setGen(Gen gen) {
        this.gen = gen;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    @Override
    public String toString() {
        return "Film{" +
                "titlu='" + titlu + '\'' +
                ", pret=" + pret +
                ", gen='" + gen + '\'' +
                ", releaseDate=" + releaseDate +
                ", descriere='" + descriere + '\'' +
                '}';
    }
}
