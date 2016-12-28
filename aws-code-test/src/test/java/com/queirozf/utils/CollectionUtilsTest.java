package com.queirozf.utils;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by felipe on 28/12/16.
 */
public class CollectionUtilsTest {

    CollectionUtils collectionUtils;

    @Before
    public void setUp() {
        collectionUtils = CollectionUtils.getInstance();
    }

    @Test
    public void testSort() throws Exception {

        ArrayList<String> input = new ArrayList<String>();
        input.add("foo");
        input.add("bar");
        input.add("baz");

        ArrayList<String> output = collectionUtils.sort(input);

        ArrayList<String> expected = new ArrayList<String>();
        expected.add("bar");
        expected.add("baz");
        expected.add("foo");

        assertEquals(output,expected);

    }

}