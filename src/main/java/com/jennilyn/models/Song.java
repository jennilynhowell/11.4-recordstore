package com.jennilyn.models;

import javax.persistence.*;

@Entity
@Table(name = "song")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

    public Song() {}

    public Song(String title, Album album) {
        this.title = title;
        this.album = album;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
