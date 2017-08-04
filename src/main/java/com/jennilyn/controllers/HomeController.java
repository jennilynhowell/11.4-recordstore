package com.jennilyn.controllers;

import com.jennilyn.models.Album;
import com.jennilyn.models.Artist;
import com.jennilyn.models.Song;
import com.jennilyn.repositories.AlbumRepository;
import com.jennilyn.repositories.ArtistRepository;
import com.jennilyn.repositories.SongRepository;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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

//    ------Album/Artist Controllers------
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

    @RequestMapping("/album/{albumId}/editAlbum")
    public String editAlbum(@PathVariable("albumId") long id, Model model){

        Album album = albumRepo.findOne(id);
        Iterable<Artist> artists = artistRepo.findAll();
        model.addAttribute("artists", artists);
        model.addAttribute("album", album);
        return "editAlbum";
    }

    @RequestMapping(value = "/album/{albumId}/editAlbum", method = RequestMethod.POST)
    public String editAlbum(@PathVariable("albumId") long id,
                            @RequestParam("title") String title,
                            @RequestParam("artist-id") long artistId,
                            @RequestParam("genre") String genre,
                            @RequestParam("release-date") String release, Model model){
        Artist artist = artistRepo.findOne(artistId);
        Album album = albumRepo.findOne(id);
        album.setArtist(artist);
        album.setAlbumGenre(genre);
        album.setReleaseDate(release);
        album.setTitle(title);
        albumRepo.save(album);

        return "redirect:/album/" + id;
    }

    @RequestMapping(value = "/album/{albumId}/deleteAlbum", method = RequestMethod.POST)
    public String deleteAlbum(@PathVariable("albumId") long id){
        Album album = albumRepo.findOne(id);
        albumRepo.delete(album);
        return "redirect:/";
    }

    @RequestMapping(value = "/album/search", method = RequestMethod.POST)
    public String searchAlbum(@RequestParam("searchAlbumName") String searchAlbumName, Model model) {
        List<Album> albums = albumRepo.findByTitle(searchAlbumName);
        model.addAttribute("albums", albums);
        return "searchResults";
    }

    @RequestMapping(value = "/artist/search", method = RequestMethod.POST)
    public String searchArtist(@RequestParam("searchArtistName") String searchArtistName, Model model) {
        List<Artist> artists = artistRepo.findByName(searchArtistName);
        model.addAttribute("artists", artists);
        return "searchResultsArtist";
    }




//    ------Song Controllers------
    @RequestMapping("/song/{songId}")
    public String editSong(@PathVariable("songId") long id, Model model){
        Song song = songRepo.findOne(id);
        model.addAttribute(song);

        return "editSong";
    }

    @RequestMapping(value = "/song/{songId}/editSong", method = RequestMethod.POST)
    public String editSong(@PathVariable("songId") long id,
                           @RequestParam("title") String title,
                           Model model){
        Song song = songRepo.findOne(id);
        song.setTitle(title);
        songRepo.save(song);

        model.addAttribute(song);
        return "redirect:/song/" + id;
    }

    @RequestMapping(value = "/song/{songId}/deleteSong", method = RequestMethod.POST)
    public String deleteSong(@PathVariable("songId") long id){
        Song song = songRepo.findOne(id);
        long albumId = song.getAlbum().getId();
        songRepo.delete(id);
        return "redirect:/album/" + albumId;
    }

}
