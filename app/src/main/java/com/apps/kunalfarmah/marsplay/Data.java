package com.apps.kunalfarmah.marsplay;

import java.util.ArrayList;

public class Data {
    String id;
    String essin;
    String date;
    String type;
    String title;
    String score;

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    String journal;
    ArrayList<String> authors,Abstract;

    public Data(String essin, String date, String type, String title, String score, ArrayList<String> authors, ArrayList<String> anAbstract) {
        this.essin = essin;
        this.date = date;
        this.type = type;
        this.title = title;
        this.score = score;
        this.authors = authors;
        Abstract = anAbstract;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEssin() {
        return essin;
    }

    public void setEssin(String essin) {
        this.essin = essin;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }

    public ArrayList<String> getAbstract() {
        return Abstract;
    }

    public void setAbstract(ArrayList<String> anAbstract) {
        Abstract = anAbstract;
    }
}
