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
import org.springframework.web.bind.annotation.RequestMapping;

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
}
