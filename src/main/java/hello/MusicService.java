package hello;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by jelcz on 2016-10-14.
 */
public class MusicService {
    LinkedList<Genre> genres;
    LinkedList<Song> songs;
    MyLogger log = new MyLogger();


    LinkedList<Song> getSongsByTag(String tag, LinkedList<Song> tracks) {
        tag = tag.toLowerCase();
        log.log("getSongsByTag: " + tag);

        LinkedList<Song> songsToReturn = new LinkedList<>();
        for (Song song : tracks) {
            if (song.hasTag(tag)) {
                songsToReturn.add(song);
            }
        }
        long seed = System.nanoTime();
        Collections.shuffle(songsToReturn, new Random(seed));
        return songsToReturn;
    }

    LinkedList<Song> getSongsByTags(String tag1, String tag2, LinkedList<Song> tracks) {
        tag1 = tag1.toLowerCase();
        tag2 = tag2.toLowerCase();
        log.log("getSongsByTag: " + tag1 + ", " + tag2);

        LinkedList<Song> songsToReturn = new LinkedList<>();
        for (Song song : tracks) {
            if (song.hasTags(tag1, tag2)) {
                songsToReturn.add(song);
            }
        }
        long seed = System.nanoTime();
        Collections.shuffle(songsToReturn, new Random(seed));
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
