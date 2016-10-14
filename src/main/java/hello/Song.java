package hello;

/**
 * Created by jelcz on 2016-10-12.
 */
public class Song {

    private int songId;
    private String songName;
    private String songArtistName;
    private String songTag1;
    private String songTag2;
    private String songTag3;
    private String songTag4;
    private String songTag5;

    public Song(int songId, String songName, String songArtistName, String songTag1, String songTag2, String songTag3, String songTag4, String songTag5) {
        this.songId = songId;
        this.songName = songName;
        this.songArtistName = songArtistName;
        this.songTag1 = songTag1;
        this.songTag2 = songTag2;
        this.songTag3 = songTag3;
        this.songTag4 = songTag4;
        this.songTag5 = songTag5;
    }


    public boolean hasTag(String tag){
        if(this.songTag1.equals(tag) || this.songTag2.equals(tag) ||
                this.songTag3.equals(tag) || this.songTag4.equals(tag) ||
                this.songTag5.equals(tag)) {
            return true;
        }
        return false;
    }

    public boolean hasTags(String tag1, String tag2){
        if(this.songTag1.equals(tag1) || this.songTag2.equals(tag1) ||
                this.songTag3.equals(tag1) || this.songTag4.equals(tag1) ||
                this.songTag5.equals(tag1)) {
            if(this.songTag1.equals(tag2) || this.songTag2.equals(tag2) ||
                    this.songTag3.equals(tag2) || this.songTag4.equals(tag2) ||
                    this.songTag5.equals(tag2)) {
                return true;
            }
        }
        return false;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongArtistName() {
        return songArtistName;
    }

    public void setSongArtistName(String songArtistName) {
        this.songArtistName = songArtistName;
    }

    public String getSongTag1() {
        return songTag1;
    }

    public void setSongTag1(String songTag1) {
        this.songTag1 = songTag1;
    }

    public String getSongTag2() {
        return songTag2;
    }

    public void setSongTag2(String songTag2) {
        this.songTag2 = songTag2;
    }

    public String getSongTag3() {
        return songTag3;
    }

    public void setSongTag3(String songTag3) {
        this.songTag3 = songTag3;
    }

    public String getSongTag4() {
        return songTag4;
    }

    public void setSongTag4(String songTag4) {
        this.songTag4 = songTag4;
    }

    public String getSongTag5() {
        return songTag5;
    }

    public void setSongTag5(String songTag5) {
        this.songTag5 = songTag5;
    }
}
