package org.custom.iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by user1 on 4/25/14.
 */
public class CustomIteratorDemo {

    public static void main(String[] args) {
        //new ArrayList<String>(Arrays.asList("alamelu","kumar","sailakshmi","hariharan"));
        List<String> familyNames = new ArrayList<String>() {{
            add("alamelu");
            add("kumar");
            add("sailakshmi");
            add("hariharan");
        }};
        Iterator<String> myIterator = new FamilyIterator<String>(familyNames);
        while(myIterator.hasNext()) System.out.println(myIterator.next());
        //for(String name: familyNames) {
        for(String name: new FamilyIterator<String>()) {
            System.out.println(name);
        }
    }

}