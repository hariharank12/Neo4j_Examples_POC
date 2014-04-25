package org.custom.iterator;

import java.util.*;
import java.util.Iterator;


/**
 * Created by user1 on 4/25/14.
 */
public class FamilyIterator<E> implements Iterator<E>, Iterable<String> {

    //String articleNames[] = new
    //String[] myStringArray = new String[3];
    //String[] myStringArray = {"a","b","c"};
    //private String[] familyNames1 = new String[]{"alamelu","kumar","sailakshmi","hariharan"};
    //private E[] familyNames
    private List<E> familyNames = null;
    protected Integer pos = 0;

    public FamilyIterator() {
    // use this constructor only for enhanced for loop demo ie iterable interface
        //this.familyNames = new ArrayList<String>(Arrays.asList("lakshmi", "hari"));
    }

    public FamilyIterator(List<E> familyNames) {
        this.familyNames = familyNames;
    }

    @Override
    public boolean hasNext() {
        // in case of string array -> return pos < familyNames.length;
        return pos < familyNames.size();
    }

    @Override
    public E next() {
        // in case of array -> return familyNames[pos];
        int currPos = pos;
        pos++;
        return familyNames.get(currPos);
    }

    @Override
    public void remove() {
        // empty right now
    }

    // Even more for the generic implementation of Iterable interface ie using Iterable<E> refer link, http://tutorials.jenkov.com/java-generics/implementing-iterable.html
    // We cannot use Iterable<E> as we cannot specify generics outside and return an concrete implementation from inside
    @Override
    public Iterator<String> iterator() {
        //List<String> familyNames = new ArrayList<String>(Arrays.asList("lakshmi", "hari"));
        return new FamilyIterator<String>(new ArrayList<String>(Arrays.asList("lakshmi", "hari")));
        //return new FamilyIterator<E>()
    }
}