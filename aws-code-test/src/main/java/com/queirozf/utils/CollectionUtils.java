package com.queirozf.utils;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by felipe on 28/12/16.
 */
public class CollectionUtils {
    private static CollectionUtils ourInstance = new CollectionUtils();

    public static CollectionUtils getInstance() {
        return ourInstance;
    }

    public ArrayList<String> sort(ArrayList<String> input ) {

        // we don't want to mutate what's been passed to us
        ArrayList<String> clone = new ArrayList<String>(input.size());

        for (String item : input) clone.add(item);

        Collections.sort(clone);

        return clone;
    }

    private CollectionUtils() {
    }


}
