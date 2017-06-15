package com.eservice.utils;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class Encoder {

		private static final int CHARACTER_CODEPOINT_HORIZONTAL_TABULATION = 9;
		private static final String HORIZONTAL_TABULATION = "\t";
		private static final int CHARACTER_CODEPOINT_NEW_LINE = 10;
		private static final String NEW_LINE = "\n";
		private static final int CHARACTER_CODEPOINT_CARRIAGE_RETURN = 13;
		private static final String CARRIAGE_RETURN = "\r";
		private static final String SEMICOLON = ";";
		private static final String AMPERSAND_NUMBER_SIGN = "&#";

		private static final String CHARSET_NAME_ISO = "ISO-8859-1";
		private static final String CHARSET_NAME_UTF = "UTF-8";

		public static String encode(String inputString)
		{
			StringBuffer outBuffer = new StringBuffer();
			int charCodePoint;
			String escapeChars;
			for(int i = 0; i < inputString.length(); i++)
			{
				charCodePoint = Character.codePointAt(inputString, i);
				escapeChars = getEscapeCharString(charCodePoint);
				outBuffer.append(escapeChars);
			}

			return outBuffer.toString();
		}

		private static String getEscapeCharString(int val)
		{
			if(val == CHARACTER_CODEPOINT_HORIZONTAL_TABULATION)
				return HORIZONTAL_TABULATION;
			else if(val == CHARACTER_CODEPOINT_NEW_LINE)
				return NEW_LINE;
			else if(val == CHARACTER_CODEPOINT_CARRIAGE_RETURN)
				return CARRIAGE_RETURN;
			else if(val < 32 || val > 126)
				return AMPERSAND_NUMBER_SIGN + val + SEMICOLON;
			else
				return String.valueOf(Character.toChars(val));
		}

		public static String encodeByCharset(String str)
		{
			CharsetDecoder decoder = Charset.forName(CHARSET_NAME_ISO).newDecoder();
			CharsetEncoder encoder = Charset.forName(CHARSET_NAME_UTF).newEncoder();
			ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
			ByteBuffer outBuffer;
			String output;
			try
			{
				outBuffer = encoder.encode(decoder.decode(buffer));
				encoder.flush(outBuffer);
				output = new String(outBuffer.array());

				return output;
			}
			catch(Exception e)
			{
				return str;
			}

		}
	}
