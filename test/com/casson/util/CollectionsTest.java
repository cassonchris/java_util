package com.casson.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import org.junit.Test;
import static org.junit.Assert.*;

public class CollectionsTest {

    /**
     * Test of getSubCollections method, of class Collections.
     */
    @Test
    public void testGetSubCollections() {
        /////////////////////////////////
        // TEST 1 - Integer Collection //
        /////////////////////////////////
        Collection<Integer> startListInts = new LinkedList<>();
        startListInts.add(1);
        startListInts.add(2);
        
        Collection<Collection<Integer>> expectedIntegerSublists = new LinkedList<>();
        expectedIntegerSublists.add(new LinkedList<>());
        expectedIntegerSublists.add(new LinkedList<>(Arrays.asList(1)));
        expectedIntegerSublists.add(new LinkedList<>(Arrays.asList(2)));
        expectedIntegerSublists.add(new LinkedList<>(Arrays.asList(1,2)));
        
        Collection<Collection<Integer>> actualIntegerSublists = Collections.getSubCollections(startListInts);
        
        assertEquals(expectedIntegerSublists, actualIntegerSublists);
                
        /////////////////////////////////
        // TEST 2 - String Collection  //
        /////////////////////////////////
        Collection<String> startListStrings = new LinkedList<>();
        startListStrings.add("a");
        startListStrings.add("b");
        startListStrings.add("c");
        
        Collection<Collection<String>> expectedStringSublists = new LinkedList<>();
        expectedStringSublists.add(new LinkedList<>());
        expectedStringSublists.add(new LinkedList<>(Arrays.asList("a")));
        expectedStringSublists.add(new LinkedList<>(Arrays.asList("b")));
        expectedStringSublists.add(new LinkedList<>(Arrays.asList("a","b")));
        expectedStringSublists.add(new LinkedList<>(Arrays.asList("c")));
        expectedStringSublists.add(new LinkedList<>(Arrays.asList("a","c")));
        expectedStringSublists.add(new LinkedList<>(Arrays.asList("b","c")));
        expectedStringSublists.add(new LinkedList<>(Arrays.asList("a","b","c")));
        
        Collection<Collection<String>> actualStringSublists = Collections.getSubCollections(startListStrings);
        
        assertEquals(expectedStringSublists, actualStringSublists);
        
        /////////////////////////////////
        // TEST 3 - empty Collection   //
        /////////////////////////////////
        Collection<Object> emptyCollection = new LinkedList<>();
        
        Collection<Collection<Object>> expectedEmptySublists = new LinkedList<>();
        expectedEmptySublists.add(new LinkedList<>());
        
        Collection<Collection<Object>> actualEmptySublists = Collections.getSubCollections(emptyCollection);
        
        assertEquals(expectedEmptySublists, actualEmptySublists);
    }
    
    /**
     * Test of getSubCollectionsRecursive method, of class Collections.
     */
    @Test
    public void getSubCollectionsRecursive() {
        /////////////////////////////////
        // TEST 1 - empty Collection   //
        /////////////////////////////////
        Collection<Object> emptyCollection = new LinkedList<>();
        
        Collection<Collection<Object>> expectedEmptyCollection = new LinkedList<>();
        expectedEmptyCollection.add(new LinkedList<>());
        
        Collection<Collection<Object>> actualEmptyCollection = Collections.getSubCollectionsRecursive(emptyCollection);
        
        assertEquals(expectedEmptyCollection, actualEmptyCollection);
        
        /////////////////////////////////
        // TEST 2 - Integer Collection //
        /////////////////////////////////
        Collection<Integer> startListInts = new LinkedList<>();
        startListInts.add(1);
        startListInts.add(2);
        
        Collection<Collection<Integer>> expectedIntegerSublists = new LinkedList<>();
        expectedIntegerSublists.add(new LinkedList<>());
        expectedIntegerSublists.add(new LinkedList<>(Arrays.asList(2)));
        expectedIntegerSublists.add(new LinkedList<>(Arrays.asList(1)));
        expectedIntegerSublists.add(new LinkedList<>(Arrays.asList(1,2)));
        
        Collection<Collection<Integer>> actualIntegerSublists = Collections.getSubCollectionsRecursive(startListInts);
        
        assertEquals(expectedIntegerSublists, actualIntegerSublists);
                
        /////////////////////////////////
        // TEST 3 - String Collection  //
        /////////////////////////////////
        Collection<String> startListStrings = new LinkedList<>();
        startListStrings.add("a");
        startListStrings.add("b");
        startListStrings.add("c");
        
        Collection<Collection<String>> expectedStringSublists = new LinkedList<>();
        expectedStringSublists.add(new LinkedList<>());
        expectedStringSublists.add(new LinkedList<>(Arrays.asList("c")));
        expectedStringSublists.add(new LinkedList<>(Arrays.asList("b")));
        expectedStringSublists.add(new LinkedList<>(Arrays.asList("b","c")));
        expectedStringSublists.add(new LinkedList<>(Arrays.asList("a")));
        expectedStringSublists.add(new LinkedList<>(Arrays.asList("a","c")));
        expectedStringSublists.add(new LinkedList<>(Arrays.asList("a","b")));
        expectedStringSublists.add(new LinkedList<>(Arrays.asList("a","b","c")));
        
        Collection<Collection<String>> actualStringSublists = Collections.getSubCollectionsRecursive(startListStrings);
        
        assertEquals(expectedStringSublists, actualStringSublists);
    }
}
