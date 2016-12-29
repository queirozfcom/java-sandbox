package com.queirozf;

import sun.misc.Regexp;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;

/*
Find the first word in a stream which is not repeated in the rest of the stream.
Please note that you are being provided a stream as a source for the characters.
The stream is guaranteed to eventually terminate (i.e. return false from a call to the hasNext() method), though it could be very long.
You will access this stream through the provided interface methods.
A call to hasNext() will return whether the stream contains any more characters to process.
A call to next() will return the next character to be processed in the stream.
It is not possible to restart the stream.

You can write any methods or classes you would like. However, all new classes must be written in this
file and you cannot change the FirstWordInStream class name, the Stream interface contract or the firstWord() method signature.

Example:
--------
	Input: "The angry dog was red. And the cat was also angry."
	Output: "dog"
In this example, the word "dog" is the first word in the stream which is not repeated in the rest of the stream.


*/
public class FirstWordInStream {

    // hashset has fast "contains" operation
    private PriorityQueue<String> uniqueWordsSoFar;

    public FirstWordInStream() {
        this.uniqueWordsSoFar = new PriorityQueue<String>();
    }

    public interface Stream {
        char next();

        boolean hasNext();
    }

    public String firstWord(final Stream input, char[] boundaries) {

        // maybe i should use stringbuffer here?
        String currentWord = "";
        char currentChar;

        boolean previousCharWasBoundary = true;
        boolean currentCharIsBoundary = false;

        while (input.hasNext()) {

            currentChar = input.next();

            for (char boundary : boundaries) {
                if (currentChar == boundary) {

                    currentCharIsBoundary = true;

                    if (uniqueWordsSoFar.contains(currentWord)) {
                        uniqueWordsSoFar.remove(currentWord);
                    } else {
                        uniqueWordsSoFar.add(currentWord);
                        currentWord = "";
                    }
                }
            }
            // we didn't skip a loop so none of the provided boundaries match
            // so current char it not a boundary and we move on
            if(!currentCharIsBoundary) {
                previousCharWasBoundary = false;
                currentWord = currentWord + currentChar;
            }
        }

        // is empty is faster than size() == 0
        if (uniqueWordsSoFar.isEmpty()) {
            return null;
        } else {
            return uniqueWordsSoFar.element();
        }
    }
}

class MyStream implements FirstWordInStream.Stream {

    private char[] chars;
    private int size;

    // don't think there's need to use thread safe AtomicInteger here
    private int currentIndex;

    public MyStream(String str) {
        this.currentIndex = 0;
        this.chars = str.toCharArray();
        this.size = this.chars.length;
    }

    public char next() {
        // getAndIncrement rather than incrementAndGet so we don't miss the first element
        return this.chars[currentIndex++];
    }

    public boolean hasNext() {
        // strictly less than
        return (currentIndex < (this.size - 1));
    }
}