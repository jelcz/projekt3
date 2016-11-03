package app;

import de.umass.lastfm.PaginatedResult;
import de.umass.lastfm.Tag;
import de.umass.lastfm.Track;
import de.umass.lastfm.User;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
public class ApiController {

    Utils util = new Utils();
    MyLogger log = new MyLogger();

    MusicDao musicDao = new MusicDao();
    MusicService musicService = new MusicService();
    @CrossOrigin
    @RequestMapping("/initData/")
    public JSONObject initData() {
        log.log("initData call");
        util.truncateGenreTable();
        util.createGenreList();
        LinkedList<Genre> genres = musicDao.getGenres();
        util.truncateGenresAssocTable();
        util.createGenresAssocList(genres);

        JSONObject obj = new JSONObject();
        obj.put("Status", "succeed");
        return obj;
    }

    @CrossOrigin
    @RequestMapping("/init/")
    public JSONObject init() {
        log.log("init call");
        musicService.setSongs(musicDao.getSongs());
        musicService.setGenres(musicDao.getGenres());
        musicService.setGenresAssoc(musicDao.getGenresAssoc());
        JSONObject obj = new JSONObject();
        obj.put("Status", "succeed");
        return obj;
    }

    @CrossOrigin
    @RequestMapping("/lastFmData/")
    public JSONObject lastfm() {
        log.log("initLastFm call");

        String key = "66e316cc7a5713729c71cc2083a05eaa";
        String user = "boleem";
        int counter = 0;
        for(int i = 0; i<20 ; i++) {
            PaginatedResult<Track> tracks = User.
                    getRecentTracks(user, i, 1000, key);

            for (Track track : tracks) {
                List<Tag> tagList = new ArrayList(
                        track.getTopTags(track.getArtist().
                                toString(), track.getName(), key));

                String tag0 = "";
                String tag1 = "";
                String tag2 = "";
                String tag3 = "";
                String tag4 = "";
                if (tagList.size() > 0) {
                    tag0 = tagList.get(0).getName();
                    if (tagList.size() > 1) {
                        tag1 = tagList.get(1).getName();
                        if (tagList.size() > 2) {
                            tag2 = tagList.get(2).getName();
                            if (tagList.size() > 3) {
                                tag3 = tagList.get(3).getName();
                                if (tagList.size() > 4) {
                                    tag4 = tagList.get(4).getName();
                                }
                            }
                        }
                    }
                    musicDao.insertIntoTracks(counter++,
                            track.getName(), track.getArtist().toString(),
                            tag0, tag1, tag2, tag3, tag4);
                }
            }
        }


        JSONObject obj = new JSONObject();
        obj.put("Status", "succeed");
        return obj;
    }
    @CrossOrigin
    @RequestMapping("/getGenres/")
    public JSONObject index() {
        log.log("getGenres call");
        LinkedList<Genre> genres = musicDao.getGenres();

        System.out.println("Closest genres to " + genres.get(32).getGenreName() +": " + musicDao.getGenreClosestAssocs(genres.get(32).getGenreName()));

        JSONObject obj = new JSONObject();
        obj.put("Status", "succeed");
        return obj;
    }
    @CrossOrigin
    @RequestMapping("/getSongs/")
    public JSONObject restGetTracks() {
        log.log("getSongs call");
        JSONObject obj = new JSONObject();
        LinkedList<Song> tracks = musicDao.getSongs();
        for(Song song: tracks){
            System.out.println(song);
        }

        obj.put("Songs", tracks);
        return obj;
    }

    @CrossOrigin
    @RequestMapping(value = "/getSongsByTag/{tag}/{quantity}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject restGetSongsByTag(@PathVariable("tag") String tag, @PathVariable("quantity") int quantity) {

        log.log("getSongsByTag call " + tag);

        LinkedList<Song> songs = musicService.getSongsByTag(tag, musicService.getSongs());
        List<Song> songsList = new LinkedList<>();
        if (songs.size() > quantity) {
            songsList = songs.subList(0, quantity);
        } else {
            songsList = songs;
        }

        JSONObject obj = new JSONObject();
        obj.put("songs", songsList);

        return obj;
    }

    @CrossOrigin
    @RequestMapping(value = "/getArtistsByTag/{tag}/{quantity}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject restGetArtistsByTag(@PathVariable("tag") String tag, @PathVariable("quantity") int quantity) {

        log.log("restGetArtistsByTag call " + tag + ", " + quantity);

        LinkedList<Song> songs = musicService.getSongsByTag(tag, musicService.getSongs());
        List<Song> songsList = new LinkedList<>();
        if (songs.size() > quantity) {
            songsList = songs.subList(0, quantity);
        } else {
            songsList = songs;
        }

        JSONObject obj = new JSONObject();
        obj.put("songs", songsList);

        return obj;
    }

    @CrossOrigin
    @RequestMapping(value = "/getSongsByTag/{tag1}/{tag2}/{quantity}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject restGetSongsByTags(@PathVariable("tag1") String tag1,
                                         @PathVariable("tag2") String tag2,
                                         @PathVariable("quantity") int quantity) {

        log.log("getSongsByTag call " + tag1 + ", " + tag2);

        LinkedList<Song> songs = musicService.getSongsByTags(tag1, tag2, musicService.getSongs());
        List<Song> songsList = new LinkedList<>();
        if (songs.size() == 0) {
            LinkedList<Genre> genresExtended = musicDao.getGenreClosestAssocs(tag1);
            genresExtended.addAll(musicDao.getGenreClosestAssocs(tag2));
                for(Genre genre: genresExtended) {
                    songs.addAll(musicService.getSongsByTag(genre.getGenreName(),
                            musicService.getSongs()));
                }
        }
        if (songs.size() > quantity) {
            songsList = songs.subList(0, quantity);
        } else {
            songsList = songs;
        }

        JSONObject obj = new JSONObject();
        obj.put("songs", songsList);

        return obj;
    }
}