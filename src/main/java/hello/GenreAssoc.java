package hello;

/**
 * Created by jelcz on 2016-10-12.
 */
public class GenreAssoc {
    private int genreAssocIdFrom;
    private int genreAssocIdTo;

    public int getGenreAssocIdFrom() {
        return genreAssocIdFrom;
    }
    public GenreAssoc(int genreAssocIdFrom, int genreAssocIdTo){
        this.genreAssocIdFrom = genreAssocIdFrom;
        this.genreAssocIdTo = genreAssocIdTo;
    }

    public void setGenreAssocIdFrom(int genreAssocIdFrom) {
        this.genreAssocIdFrom = genreAssocIdFrom;
    }


    public int getGenreAssocIdTo() {
        return genreAssocIdTo;
    }

    public void setGenreAssocIdTo(int genreAssocIdTo) {
        this.genreAssocIdTo = genreAssocIdTo;
    }


    @Override
    public String toString() {
        return "GenreAssoc{" +
                "genreAssocIdFrom=" + genreAssocIdFrom +
                ", genreAssocIdTo=" + genreAssocIdTo +
                '}';
    }
}
