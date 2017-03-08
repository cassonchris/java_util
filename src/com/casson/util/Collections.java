package com.casson.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public final class Collections {
    
    private Collections() { }

    /**
     * This method takes a collection as input and returns all possible 
     * collections that can be made with the given collection elements.
     * 
     * @param <T>
     * @param collection
     * @return a collection of collections in no particular order
     */
    public static <T> Collection<Collection<T>> getSubCollections(Collection<T> collection) {
        // the Collection needs to be converted to a List in order to have positional access to elements
        List<T> startList = new ArrayList<>(collection);
        
        // create the collection of sublists
        Collection<Collection<T>> sublists = new LinkedList<>();

        // the number of sublists equals 2^listsize
        long sublistsSize = (long) Math.pow(2, startList.size());

        // Each number from 0 to sublistsSize-1 represents a sublist's bit pattern
        // where each bit represents an element in the original list.
        //
        // ex.
        //      startList: [ 'a' , 'b' , 'c' ]
        //
        //      bit patterns:
        //          000 -> []
        //          001 -> [ 'a' ]
        //          010 -> [ 'b' ]
        //          011 -> [ 'a' , 'b' ]
        //          100 -> [ 'c' ]
        //          101 -> [ 'a' , 'c' ]
        //          110 -> [ 'b' , 'c' ]
        //          111 -> [ 'a' , 'b' , 'c' ]
        //
        // for each bit pattern, it checks bit by bit and if it equals one
        // it adds the corresponding element to the list
        for (int i = 0; i < sublistsSize; i++) {
            Collection<T> sublist = new LinkedList<>();
            for (int j = 0; j < startList.size(); j++) {
                if (((i >> j) & 1) == 1) {
                    sublist.add(startList.get(j));
                }
            }
            sublists.add(sublist);
        }

        // return the collection of sublists
        return sublists;
    }

    /**
     * This method takes a collection as input and returns all possible
     * collections that can be made with the given collection elements.
     * 
     * @param <T>
     * @param collection
     * @return a collection of collections in no particular order
     */
    public static <T> Collection<Collection<T>> getSubCollectionsRecursive(Collection<T> collection) {
        return Collections.getSubCollectionsRecursive(collection, new LinkedList<>());
    }
    
    /**
     * Example recursion tree:
     * 
     *      Starting list = [1,2]
     *      c = collection
     *      s = sublist
     * 
     *                     c = [ 1 , 2 ]; s = [ ];
     *                      /                   \  
     *                     /                     \
     *                    /                       \
     *                   /                         \
     *                  /                           \
     *           c=[2]; s=[];                    c=[2]; s=[1];
     *          /           \                    /           \
     *         /             \                  /             \
     *        /               \                /               \
     *    c=[]; s=[];     c=[]; s=[2];    c=[]; s=[1];     c=[]; s=[1,2];
     * 
     *      The leaves sublists are the collections that get returned.
     *      [ [], [2], [1], [1,2] ]
     * 
     * @param <T>
     * @param collection
     * @param sublist
     * @return 
     */
    private static <T> Collection<Collection<T>> getSubCollectionsRecursive(Collection<T> collection, Collection<T> sublist) {
        // create the collection of collections that will be returned
        Collection<Collection<T>> allSublists = new LinkedList<>();
        
        // create copies of the starting list and the sublist to pass down the recursion tree
        Collection<T> startList = new LinkedList<>(collection);
        Collection<T> sublistCopy = new LinkedList<>(sublist);
        
        // base case, all elements have been removed from the starting list
        if (startList.isEmpty()) {
            // add the sublist that was created to the allSublists collection
            allSublists.add(sublistCopy);
            return allSublists;
        } else {
            // remove an item from the collection
            Iterator<T> iterator = startList.iterator();
            T item = null;
            if (iterator.hasNext()) {
                item = iterator.next();
                iterator.remove();
            }

            // "left" branch of recursion tree
            allSublists.addAll(Collections.getSubCollectionsRecursive(startList, sublistCopy));
            
            // "right" branch of recursion tree
            sublistCopy.add(item);
            allSublists.addAll(Collections.getSubCollectionsRecursive(startList, sublistCopy));

            return allSublists;
        }
    }
}
