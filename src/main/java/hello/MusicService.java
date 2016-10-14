package hello;

import java.util.LinkedList;

/**
 * Created by jelcz on 2016-10-14.
 */
public class MusicService {
    LinkedList<Genre> genres;
    LinkedList<Song> songs;
    MyLogger log = new MyLogger();


    LinkedList<Song> getSongsByTag(String tag, LinkedList<Song> tracks) {


        log.log("getSongsByTag: " + tag);

        LinkedList<Song> songsToReturn = new LinkedList<>();
        for (Song song : tracks) {
            if (song.hasTag(tag)) {
                songsToReturn.add(song);
            }
        }
        return songsToReturn;
    }
    public LinkedList<Genre> getGenres() {
        return genres;
    }

    public void setGenres(LinkedList<Genre> genres) {
        this.genres = genres;
    }

    public LinkedList<Song> getSongs() {
        return songs;
    }

    public void setSongs(LinkedList<Song> songs) {
        this.songs = songs;
    }
}
