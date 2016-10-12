package hello;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;

@RestController
public class HelloController {

    Utils util = new Utils();

    MusicDao musicDao = new MusicDao();
    @CrossOrigin
    @RequestMapping("/getGenres/")
    public String index() {
        util.truncateGenreTable();
        util.createGenreList();
        LinkedList<Genre> genres = musicDao.getGenres();
        util.truncateGenresAssocTable();
        util.createGenresAssocList(genres);
        System.out.println("Genres: " + genres.toString());

        return "sukces";
    }

}