package com.sirdave.get_ahead;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FlattenedIterator implements Iterator<Integer> {
    private final Queue<Iterator<Integer>> mIteratorQueue;

    public FlattenedIterator(List<Iterator<Integer>> iterators){
        mIteratorQueue = new LinkedList<>();

        for (Iterator<Integer> iterator: iterators){
            if (iterator.hasNext()){
                mIteratorQueue.add(iterator);
            }
        }

    }

    @Override
    public boolean hasNext() {
        if (mIteratorQueue.isEmpty()){
            return false;
        }

        if (!mIteratorQueue.peek().hasNext()){
            throw new IllegalStateException("Iterator unexpectedly empty");
        }
        return true;
    }

    @Override
    public Integer next() {
        if (hasNext()){
            Iterator<Integer> iterator = mIteratorQueue.remove();
            Integer value = iterator.next();
            if (iterator.hasNext()){
                mIteratorQueue.add(iterator);
            }
            return value;
        }
        else{
            return null;
        }
    }

    public static void main(String[] main){
        List<Integer> arr1 = List.of(1,2,3);
        List<Integer> arr2 = List.of(4,5);
        List<Integer> arr3 = List.of(6,7,8);

        //FlattenedIterator = [1, 4, 6, 2, 5, 7, 3, 8]
        FlattenedIterator flattenedIterator = new FlattenedIterator(
                List.of(arr1.iterator(),
                        arr2.iterator(),
                        arr3.iterator()));

        System.out.println(flattenedIterator.next());
        System.out.println(flattenedIterator.next());
        System.out.println(flattenedIterator.next());
        System.out.println(flattenedIterator.next());
        System.out.println(flattenedIterator.next());
        System.out.println(flattenedIterator.next());
        System.out.println(flattenedIterator.next());
        System.out.println(flattenedIterator.next());

    }
}
