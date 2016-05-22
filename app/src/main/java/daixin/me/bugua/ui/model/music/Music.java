package daixin.me.bugua.ui.model.music;

/**
 * Created by shidai on 2016/5/12.
 * 播放音乐Model
 */
public class Music {
    private int id;
    private String title;
    private String uri;
    private int length;
    private String image;
    private String artist;

    public Music() {
    }

    public Music(int id, String title, String uri, int length, String image, String artist) {
        this.id = id;
        this.title = title;
        this.uri = uri;
        this.length = length;
        this.image = image;
        this.artist = artist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "Music{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", uri='" + uri + '\'' +
                ", length=" + length +
                ", image='" + image + '\'' +
                ", artist='" + artist + '\'' +
                '}';
    }
}
