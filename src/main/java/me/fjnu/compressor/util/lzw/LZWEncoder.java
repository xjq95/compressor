package me.fjnu.compressor.util.lzw;


import me.fjnu.compressor.exception.InvalidParamsException;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static me.fjnu.compressor.util.lzw.LZWUtility.indexesToByteArray;

public final class LZWEncoder {

    //  package visibility for test purposes
    private LinkedList<Integer> indexes;

    private EncodingDictionary dictionary;

    public byte[] encode(final byte[] data) throws InvalidParamsException {
        if(data == null) {
            throw new InvalidParamsException("Data to encode can not be null");
        }

        if(data.length == 0) {
            throw new InvalidParamsException("Data to encode can not be of 0 byte length");
        }

        /** lets start encoding    **/

        indexes = new LinkedList<>();

        //  fill dict with source data alphabet
        dictionary = new EncodingDictionary();

        //  read first symbol
        LinkedList<Byte> c = new LinkedList<>();
        c.add(data[0]);

        int i = 1;
        while (i < data.length) {   //  while data in input

            //  read s symbol
            byte s = data[i];

            if(dictionary.contains(c, s)) {
                //   c := c + s
                c.add(s);
            } else {
                //  push code(index) of c to output
                indexes.add(dictionary.getIndexOf(c));

                //  add c + s to dictionary
                dictionary.add(c, s);

                //  c := s
                c.clear();
                c.add(s);
            }
            ++i;
        }

        //  in the end, push code(index) of c to output
        indexes.add(dictionary.getIndexOf(c));

//        printAvgBitLength(indexes);
    
        return indexesToByteArray(indexes);
    }

    public EncodingDictionary getDictionary() {
        return dictionary;
    }

    public LinkedList<Integer> getIndexes() {
        return indexes;
    }

    public static void printAvgBitLength(List<Integer> indexes, Map<Integer, List<Byte>> dictionary) {

    }

}
