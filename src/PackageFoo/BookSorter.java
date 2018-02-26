package PackageFoo;

import java.util.Comparator;

public class BookSorter implements Comparator<Book> {

    public int compare(Book one, Book another){
        return one.compareTo(another);
    }
}
