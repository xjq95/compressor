package me.fjnu.compressor.util.lzw;


import me.fjnu.compressor.exception.InvalidInputEncodedDataFileException;
import me.fjnu.compressor.exception.InvalidParamsException;

import java.util.LinkedList;
import java.util.List;

import static me.fjnu.compressor.util.lzw.LZWUtility.indexesFromEncodedDataByteArray;
import static me.fjnu.compressor.util.lzw.LZWUtility.toArray;


public class LZWDecoder {
	
	public byte[] decode(byte[] encodedData) throws InvalidParamsException, InvalidInputEncodedDataFileException {
		
		if (encodedData == null) {
			throw new InvalidParamsException("Encoded data can not be null");
		}
		
		if (encodedData.length == 0) {
			return new byte[0];
		}
		
		//  get indexes
		int[] indexes = indexesFromEncodedDataByteArray(encodedData);
		
		/* lets start decoding **/
		
		LinkedList<List<Byte>> result = new LinkedList<>();
		
		//  fill dict with source data alphabet //
		DecodingDictionary decodingDictionary = new DecodingDictionary();
		
		//  pk := first code of compressed data
		int pk = indexes[0];
		
		//  push symbol related to pk to output
		result.add(decodingDictionary.getWord(pk));
		
		int i = 1;
		while (i < indexes.length) { //  while there are still code words to process
			//  read k code
			int k = indexes[i];
			
			// pc := dict[pk]
			List<Byte> pc = decodingDictionary.getWord(pk);
			assert (pc != null);
			
			//  kword
			List<Byte> kword = decodingDictionary.getWord(k);
			if (kword != null) { // kword is in dict
				
				//  add {pc + kword[0]} word to dict
				List<Byte> temp = new LinkedList<>(pc);
				temp.add(kword.get(0));
				
				decodingDictionary.add(temp);
				
				//  push kword to output
				result.add(kword);
			} else {
				//  add {pc + pc[0]} word to dict
				
				List<Byte> temp = new LinkedList<>(pc);
				temp.add(pc.get(0));
				
				decodingDictionary.add(temp);
				
				//  push {pc + pc[0]} to output
				result.add(temp);
			}
			
			//  pk := k
			pk = k;
			
			++i;
		}
		
		return toArray(result);
	}
	
}
