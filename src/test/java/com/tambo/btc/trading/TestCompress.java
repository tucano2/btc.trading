package com.tambo.btc.trading;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.junit.Test;

import com.google.common.primitives.Bytes;

public class TestCompress {

	@Test
	public void testCompressIt() throws Exception {
		String string = "THIS IS A LOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOONG STRIIIIIIIIIIIIIIIIIIIIIIIIIIIIING"
				+ "THIS IS A LOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOONG STRIIIIIIIIIIIIIIIIIIIIIIIIIIIIING"
				+ "THIS IS A LOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOONG STRIIIIIIIIIIIIIIIIIIIIIIIIIIIIING"
				+ "THIS IS A LOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOONG STRIIIIIIIIIIIIIIIIIIIIIIIIIIIIING"
				+ "THIS IS A LOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOONG STRIIIIIIIIIIIIIIIIIIIIIIIIIIIIING"
				+ "THIS IS A LOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOONG STRIIIIIIIIIIIIIIIIIIIIIIIIIIIIING"
				+ "THIS IS A LOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOONG STRIIIIIIIIIIIIIIIIIIIIIIIIIIIIING"
				+ "THIS IS A LOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOONG STRIIIIIIIIIIIIIIIIIIIIIIIIIIIIING"
				+ "THIS IS A LOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOONG STRIIIIIIIIIIIIIIIIIIIIIIIIIIIIING"
				+ "THIS IS A LOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOONG STRIIIIIIIIIIIIIIIIIIIIIIIIIIIIING"
				+ "THIS IS A LOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOONG STRIIIIIIIIIIIIIIIIIIIIIIIIIIIIING";

		//compress string
		byte[] compressed = compress(string);

		//roll byte[] into a Byte List
		List<Byte> compressedList = Bytes.asList(compressed);

		//unroll Byte list back into byte array
		byte[] unwrappedBytes = Bytes.toArray(compressedList);

		//decompress bytes
		System.out.println(decompress(unwrappedBytes));

	}

	public static byte[] compress(String str) throws Exception {
		if (str == null || str.length() == 0) {
			return new byte[] {};
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		GZIPOutputStream gzip = new GZIPOutputStream(baos);
		gzip.write(str.getBytes("UTF-8"));
		gzip.close();
		return baos.toByteArray();
	}

	public static String decompress(byte[] bytes) throws Exception {

		System.out.println("Input String length : " + bytes.length);
		GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(bytes));
		BufferedReader bf = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
		String outStr = "";
		String line;
		while ((line = bf.readLine()) != null) {
			outStr += line;
		}
		System.out.println("Output String lenght : " + outStr.length());
		return outStr;
	}
}
