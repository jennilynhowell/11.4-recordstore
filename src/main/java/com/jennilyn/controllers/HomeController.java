package com.jennilyn.controllers;

import com.jennilyn.models.Album;
import com.jennilyn.models.Artist;
import com.jennilyn.models.Song;
import com.jennilyn.repositories.AlbumRepository;
import com.jennilyn.repositories.ArtistRepository;
import com.jennilyn.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    ArtistRepository artistRepo;

    @Autowired
    AlbumRepository albumRepo;

    @Autowired
    SongRepository songRepo;

    @RequestMapping("/")
    public String index(Model model){
        Iterable<Album> albums = albumRepo.findAll();

        model.addAttribute("albums", albums);
        return "index";
    }

    @RequestMapping("/addAlbum")
    public String addAlbum(Model model){
        Iterable<Artist> artists = artistRepo.findAll();

        model.addAttribute("artists", artists);
        return "addAlbum";
    }

    @RequestMapping(value = "/addAlbum", method = RequestMethod.POST)
    public String addAlbum(@RequestParam("title") String title,
                           @RequestParam("artist-id") String artist,
                           @RequestParam("genre") String genre,
                           @RequestParam("release-date") String release){

        Album newAlbum = new Album(genre, release, title);

        try {
            long artistId = Long.parseLong(artist);
            Artist albumArtist = artistRepo.findOne(artistId);
            newAlbum.setArtist(albumArtist);
        } catch (Exception ex) {}

        albumRepo.save(newAlbum);

        return "redirect:/";
    }

    @RequestMapping(value = "/addArtist", method = RequestMethod.POST)
    public String addArtist(@RequestParam("addArtist") String addArtist) {
        Artist newArtist = new Artist(addArtist);
        artistRepo.save(newArtist);

        return "redirect:/addAlbum";
    }

    @RequestMapping("/album/{albumId}")
    public String albumDetails(@PathVariable("albumId") long id, Model model) {
        Album album = albumRepo.findOne(id);
        model.addAttribute("album", album);
        return "albumDetails";
    }

    @RequestMapping(value = "/album/{albumId}/addSong", method = RequestMethod.POST)
    public String albumDetails(@PathVariable("albumId") long id,
                               @RequestParam("title") String songTitle) {

        Album album = albumRepo.findOne(id);
        Song song = new Song(songTitle, album);
        songRepo.save(song);

        return "redirect:/album/" + id;
    }

}
