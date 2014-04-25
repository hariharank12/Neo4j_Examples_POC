package org.custom.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by user1 on 4/25/14.
 */
// Examples taken from site, http://tutorials.jenkov.com/java-generics/implementing-iterable.html
// this is the way more or less java has implemented its iterable & iterator interface. for more details refer AbstractList.java . ArrayList.java extends AbstractList.java


public class MyCollection<E> implements Iterable<E> {

/*
    static List<String> familyNames = new ArrayList<String>() {{
        add("alamelu");
        add("kumar");
        add("sailakshmi");
        add("hariharan");
    }};
*/
    private List<E> familyNames = null;

    public MyCollection(List<E> familyNames) {
        this.familyNames = familyNames;
    }

    class MyIterator implements Iterator<E> {

        //private List<E> familyNames = null;
        protected Integer pos = 0;

/*
        public MyIterator() {
            //this(staticFamilyNames);
        }

        public MyIterator(List<E> familyNames) {
            this.familyNames = familyNames;
        }
*/

        @Override
        public boolean hasNext() {
            return pos < familyNames.size();
            //implement...
        }

        @Override
        public E next() {
            int currPos = pos;
            pos++;
            return familyNames.get(currPos);
            //implement...;
        }

        @Override
        public void remove() {
            //implement... if supported.
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    public static void main(String[] args) {

        List<String> familyNames = new ArrayList<String>() {{
            add("alamelu");
            add("kumar");
            add("sailakshmi");
            add("hariharan");
        }};

        MyCollection<String> stringCollection = new MyCollection<String>(familyNames);

        for(String name: stringCollection){
            System.out.println(name);

        }
    }

}
