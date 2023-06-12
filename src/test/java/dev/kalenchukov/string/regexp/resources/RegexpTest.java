/*
 * Copyright © 2022-2023 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package dev.kalenchukov.string.regexp.resources;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс проверки констант и методов перечисления {@link Regexp}.
 *
 * @author Алексей Каленчуков
 */
public class RegexpTest
{
	/**
	 * Проверка метода {@link Regexp#getGroup()}.
	 */
	@Test
	public void getGroup()
	{
		Regexp regexp = Regexp.LOCALIZATION;

		String actualGroup = regexp.getGroup();

		assertEquals("localization", actualGroup);
	}

	/**
	 * Проверка метода {@link Regexp#getPattern()}.
	 */
	@Test
	public void getPattern()
	{
		Regexp regexp = Regexp.LOCALIZATION;

		String actualPattern = regexp.getPattern();

		assertFalse(actualPattern.isEmpty());
	}

	/**
	 * Класс проверки регулярного выражения констант перечисления {@link Regexp}.
	 *
	 * @author Алексей Каленчуков
	 */
	@Nested
	public class PatternTest
	{
		/**
		 * Проверка регулярного выражения константы {@link Regexp#LOCALIZATION}.
		 */
		@Test
		public void localization()
		{
			String value = "ru-RU";
			Pattern pattern = Pattern.compile(Regexp.LOCALIZATION.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);
			assertTrue(matcher.matches());

			String actualGroup1 = matcher.group("localization");
			String actualGroup2 = matcher.group("language");
			String actualGroup3 = matcher.group("country");

			assertEquals("ru-RU", actualGroup1);
			assertEquals("ru", actualGroup2);
			assertEquals("RU", actualGroup3);
		}

		/**
		 * Проверка константы {@link Regexp#LOCALIZATION} с некорректным значением.
		 */
		@Test
		public void localizationNotCorrect()
		{
			String value = "ru/RU";
			Pattern pattern = Pattern.compile(Regexp.LOCALIZATION.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);

			boolean actual = matcher.matches();

			assertFalse(actual);
		}

		/**
		 * Проверка регулярного выражения константы {@link Regexp#INET_4_ADDRESS}.
		 */
		@Test
		public void inet4Address()
		{
			String value = "192.168.1.100";
			Pattern pattern = Pattern.compile(Regexp.INET_4_ADDRESS.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);
			assertTrue(matcher.matches());

			String actualGroup1 = matcher.group("inet4Address");
			String actualGroup2 = matcher.group("part1");
			String actualGroup3 = matcher.group("part2");
			String actualGroup4 = matcher.group("part3");
			String actualGroup5 = matcher.group("part4");

			assertEquals("192.168.1.100", actualGroup1);
			assertEquals("192", actualGroup2);
			assertEquals("168", actualGroup3);
			assertEquals("1", actualGroup4);
			assertEquals("100", actualGroup5);
		}

		/**
		 * Проверка константы {@link Regexp#INET_4_ADDRESS} с некорректным значением.
		 */
		@Test
		public void inet4AddressNotCorrect()
		{
			String value = "f645654y45t34vtfd4";
			Pattern pattern = Pattern.compile(Regexp.INET_4_ADDRESS.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);

			boolean actual = matcher.matches();

			assertFalse(actual);
		}

		/**
		 * Проверка регулярного выражения константы {@link Regexp#INET_6_ADDRESS}.
		 */
		@Test
		public void inet6Address()
		{
			String value = "2001:0DB8:11A3:09D7:1F34:8A2E:07A0:765D";
			Pattern pattern = Pattern.compile(
				Regexp.INET_6_ADDRESS.getPattern(),
				Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
			);
			Matcher matcher = pattern.matcher(value);
			assertTrue(matcher.matches());

			String actualGroup = matcher.group("inet6Address");

			assertEquals("2001:0DB8:11A3:09D7:1F34:8A2E:07A0:765D", actualGroup);
		}

		/**
		 * Проверка константы {@link Regexp#INET_6_ADDRESS} с некорректным значением.
		 */
		@Test
		public void inet6AddressNotCorrect()
		{
			String value = "vy45yg45tyf45t4f5";
			Pattern pattern = Pattern.compile(Regexp.INET_6_ADDRESS.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);

			boolean actual = matcher.matches();

			assertFalse(actual);
		}

		/**
		 * Проверка регулярного выражения константы {@link Regexp#EMAIL_ADDRESS}.
		 */
		@Test
		public void emailAddress()
		{
			String value = "aleksey.kalenchukov@yandex.ru";
			Pattern pattern = Pattern.compile(
				Regexp.EMAIL_ADDRESS.getPattern(),
				Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
			);
			Matcher matcher = pattern.matcher(value);
			assertTrue(matcher.matches());

			String actualGroup1 = matcher.group("emailAddress");
			String actualGroup2 = matcher.group("local");
			String actualGroup3 = matcher.group("domain");
			String actualGroup4 = matcher.group("tld");

			assertEquals("aleksey.kalenchukov@yandex.ru", actualGroup1);
			assertEquals("aleksey.kalenchukov", actualGroup2);
			assertEquals("yandex.ru", actualGroup3);
			assertEquals("ru", actualGroup4);
		}

		/**
		 * Проверка константы {@link Regexp#EMAIL_ADDRESS} с некорректным значением.
		 */
		@Test
		public void emailAddressNotCorrect()
		{
			String value = "45f645g6y45hy45g5";
			Pattern pattern = Pattern.compile(Regexp.EMAIL_ADDRESS.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);

			boolean actual = matcher.matches();

			assertFalse(actual);
		}

		/**
		 * Проверка регулярного выражения константы {@link Regexp#MD5}.
		 */
		@Test
		public void md5()
		{
			String value = "1BC29B36F623BA82AAF6724FD3B16718";
			Pattern pattern = Pattern.compile(
				Regexp.MD5.getPattern(),
				Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
			);
			Matcher matcher = pattern.matcher(value);
			assertTrue(matcher.matches());

			String actualGroup = matcher.group("md5");

			assertEquals("1BC29B36F623BA82AAF6724FD3B16718", actualGroup);
		}

		/**
		 * Проверка константы {@link Regexp#MD5} с некорректным значением.
		 */
		@Test
		public void md5NotCorrect()
		{
			String value = "34546457546nhgewrxs";
			Pattern pattern = Pattern.compile(Regexp.MD5.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);

			boolean actual = matcher.matches();

			assertFalse(actual);
		}

		/**
		 * Проверка регулярного выражения константы {@link Regexp#DOMAIN}.
		 */
		@Test
		public void domain()
		{
			String value = "kalenchukov.dev";
			Pattern pattern = Pattern.compile(
				Regexp.DOMAIN.getPattern(),
				Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
			);
			Matcher matcher = pattern.matcher(value);
			assertTrue(matcher.matches());

			String actualGroup1 = matcher.group("domain");
			String actualGroup2 = matcher.group("tld");

			assertEquals("kalenchukov.dev", actualGroup1);
			assertEquals("dev", actualGroup2);
		}

		/**
		 * Проверка константы {@link Regexp#DOMAIN} с некорректным значением.
		 */
		@Test
		public void domainNotCorrect()
		{
			String value = "54gy657h65u76";
			Pattern pattern = Pattern.compile(Regexp.DOMAIN.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);

			boolean actual = matcher.matches();

			assertFalse(actual);
		}

		/**
		 * Проверка регулярного выражения константы {@link Regexp#URL_HTTP}.
		 */
		@Test
		public void urlHttp()
		{
			String value = "http://www.kalenchukov.dev/hello/world/?java=18&hello=world123#anchor";
			Pattern pattern = Pattern.compile(
				Regexp.URL_HTTP.getPattern(),
				Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
			);
			Matcher matcher = pattern.matcher(value);
			assertTrue(matcher.matches());

			String actualGroup1 = matcher.group("url");
			String actualGroup2 = matcher.group("protocol");
			String actualGroup3 = matcher.group("domain");
			String actualGroup4 = matcher.group("tld");
			String actualGroup5 = matcher.group("path");
			String actualGroup6 = matcher.group("param");
			String actualGroup7 = matcher.group("anchor");

			assertEquals("http://www.kalenchukov.dev/hello/world/?java=18&hello=world123#anchor", actualGroup1);
			assertEquals("http", actualGroup2);
			assertEquals("kalenchukov.dev", actualGroup3);
			assertEquals("dev", actualGroup4);
			assertEquals("hello/world/", actualGroup5);
			assertEquals("java=18&hello=world123", actualGroup6);
			assertEquals("anchor", actualGroup7);
		}

		/**
		 * Проверка константы {@link Regexp#URL_HTTP} с некорректным значением.
		 */
		@Test
		public void urlHttpNotCorrect()
		{
			String value = "://www.kalenchukov.dev/h";
			Pattern pattern = Pattern.compile(Regexp.URL_HTTP.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);

			boolean actual = matcher.matches();

			assertFalse(actual);
		}

		/**
		 * Проверка регулярного выражения константы {@link Regexp#RGB_NUMERIC}.
		 */
		@Test
		public void rgbNumeric()
		{
			String value = "215,200,166";
			Pattern pattern = Pattern.compile(Regexp.RGB_NUMERIC.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);
			assertTrue(matcher.matches());

			String actualGroup1 = matcher.group("rgb");
			String actualGroup2 = matcher.group("red");
			String actualGroup3 = matcher.group("green");
			String actualGroup4 = matcher.group("blue");

			assertEquals("215,200,166", actualGroup1);
			assertEquals("215", actualGroup2);
			assertEquals("200", actualGroup3);
			assertEquals("166", actualGroup4);
		}

		/**
		 * Проверка константы {@link Regexp#RGB_NUMERIC} с некорректным значением.
		 */
		@Test
		public void rgbNumericNotCorrect()
		{
			String value = "45g657h65uj";
			Pattern pattern = Pattern.compile(Regexp.RGB_NUMERIC.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);

			boolean actual = matcher.matches();

			assertFalse(actual);
		}

		/**
		 * Проверка регулярного выражения константы {@link Regexp#RGB_HEX}.
		 */
		@Test
		public void rgbHex()
		{
			String value = "#ABCDEF";
			Pattern pattern = Pattern.compile(
				Regexp.RGB_HEX.getPattern(),
				Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
			);
			Matcher matcher = pattern.matcher(value);
			assertTrue(matcher.matches());

			String actualGroup1 = matcher.group("rgb");
			String actualGroup2 = matcher.group("red");
			String actualGroup3 = matcher.group("green");
			String actualGroup4 = matcher.group("blue");

			assertEquals("#ABCDEF", actualGroup1);
			assertEquals("AB", actualGroup2);
			assertEquals("CD", actualGroup3);
			assertEquals("EF", actualGroup4);
		}

		/**
		 * Проверка константы {@link Regexp#RGB_HEX} с некорректным значением.
		 */
		@Test
		public void rgbHexNotCorrect()
		{
			String value = "45y65y65";
			Pattern pattern = Pattern.compile(Regexp.RGB_HEX.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);

			boolean actual = matcher.matches();

			assertFalse(actual);
		}

		/**
		 * Проверка регулярного выражения константы {@link Regexp#MAC_ADDRESS}.
		 */
		@Test
		public void macAddress()
		{
			String value = "00-eF-cd-Ef-11-22";
			Pattern pattern = Pattern.compile(
				Regexp.MAC_ADDRESS.getPattern(),
				Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
			);
			Matcher matcher = pattern.matcher(value);
			assertTrue(matcher.matches());

			String actualGroup1 = matcher.group("macAddress");
			String actualGroup2 = matcher.group("separator");
			String actualGroup3 = matcher.group("part1");
			String actualGroup4 = matcher.group("part2");
			String actualGroup5 = matcher.group("part3");
			String actualGroup6 = matcher.group("part4");
			String actualGroup7 = matcher.group("part5");
			String actualGroup8 = matcher.group("part6");

			assertEquals("00-eF-cd-Ef-11-22", actualGroup1);
			assertEquals("-", actualGroup2);
			assertEquals("00", actualGroup3);
			assertEquals("eF", actualGroup4);
			assertEquals("cd", actualGroup5);
			assertEquals("Ef", actualGroup6);
			assertEquals("11", actualGroup7);
			assertEquals("22", actualGroup8);
		}

		/**
		 * Проверка константы {@link Regexp#MAC_ADDRESS} с некорректным значением.
		 */
		@Test
		public void macAddressNotCorrect()
		{
			String value = "00-eF-cdEf1122";
			Pattern pattern = Pattern.compile(Regexp.MAC_ADDRESS.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);

			boolean actual = matcher.matches();

			assertFalse(actual);
		}

		/**
		 * Проверка регулярного выражения константы {@link Regexp#TELEGRAM}.
		 */
		@Test
		public void telegram()
		{
			String value = "@kalenchukov";
			Pattern pattern = Pattern.compile(
				Regexp.TELEGRAM.getPattern(),
				Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
			);
			Matcher matcher = pattern.matcher(value);
			assertTrue(matcher.matches());

			String actualGroup1 = matcher.group("telegram");
			String actualGroup2 = matcher.group("name");

			assertEquals("@kalenchukov", actualGroup1);
			assertEquals("kalenchukov", actualGroup2);
		}

		/**
		 * Проверка константы {@link Regexp#TELEGRAM} с некорректным значением.
		 */
		@Test
		public void telegramNotCorrect()
		{
			String value = "45f645yg65y";
			Pattern pattern = Pattern.compile(Regexp.TELEGRAM.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);

			boolean actual = matcher.matches();

			assertFalse(actual);
		}

		/**
		 * Проверка регулярного выражения константы {@link Regexp#TAG}.
		 */
		@Test
		public void tag()
		{
			String value = "#tag";
			Pattern pattern = Pattern.compile(
				Regexp.TAG.getPattern(),
				Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
			);
			Matcher matcher = pattern.matcher(value);
			assertTrue(matcher.matches());

			String actualGroup1 = matcher.group("tag");
			String actualGroup2 = matcher.group("name");

			assertEquals("#tag", actualGroup1);
			assertEquals("tag", actualGroup2);
		}

		/**
		 * Проверка константы {@link Regexp#TAG} с некорректным значением.
		 */
		@Test
		public void tagNotCorrect()
		{
			String value = "4v5y4vt";
			Pattern pattern = Pattern.compile(Regexp.TAG.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);

			boolean actual = matcher.matches();

			assertFalse(actual);
		}

		/**
		 * Проверка регулярного выражения константы {@link Regexp#DIGIT_BINARY}.
		 */
		@Test
		public void digitBinary()
		{
			String value = "0101010101";
			Pattern pattern = Pattern.compile(Regexp.DIGIT_BINARY.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);
			assertTrue(matcher.matches());

			String actualGroup = matcher.group("digit");

			assertEquals("0101010101", actualGroup);
		}

		/**
		 * Проверка константы {@link Regexp#DIGIT_BINARY} с некорректным значением.
		 */
		@Test
		public void digitBinaryNotCorrect()
		{
			String value = "4f34tf45t45";
			Pattern pattern = Pattern.compile(Regexp.DIGIT_BINARY.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);

			boolean actual = matcher.matches();

			assertFalse(actual);
		}

		/**
		 * Проверка регулярного выражения константы {@link Regexp#DIGIT_TERNARY}.
		 */
		@Test
		public void digitTernary()
		{
			String value = "210210210210";
			Pattern pattern = Pattern.compile(Regexp.DIGIT_TERNARY.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);
			assertTrue(matcher.matches());

			String actualGroup = matcher.group("digit");

			assertEquals("210210210210", actualGroup);
		}

		/**
		 * Проверка константы {@link Regexp#DIGIT_TERNARY} с некорректным значением.
		 */
		@Test
		public void digitTernaryNotCorrect()
		{
			String value = "4f6456f45y45dgf";
			Pattern pattern = Pattern.compile(Regexp.DIGIT_TERNARY.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);

			boolean actual = matcher.matches();

			assertFalse(actual);
		}

		/**
		 * Проверка регулярного выражения константы {@link Regexp#DIGIT_OCTAL}.
		 */
		@Test
		public void digitOctal()
		{
			String value = "65016716501710651467106";
			Pattern pattern = Pattern.compile(Regexp.DIGIT_OCTAL.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);
			assertTrue(matcher.matches());

			String actualGroup = matcher.group("digit");

			assertEquals("65016716501710651467106", actualGroup);
		}

		/**
		 * Проверка константы {@link Regexp#DIGIT_OCTAL} с некорректным значением.
		 */
		@Test
		public void digitOctalNotCorrect()
		{
			String value = "56vy56y46y545";
			Pattern pattern = Pattern.compile(Regexp.DIGIT_OCTAL.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);

			boolean actual = matcher.matches();

			assertFalse(actual);
		}

		/**
		 * Проверка регулярного выражения константы {@link Regexp#DIGIT_DECIMAL}.
		 */
		@Test
		public void digitDecimal()
		{
			String value = "0123";
			Pattern pattern = Pattern.compile(Regexp.DIGIT_DECIMAL.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);
			assertTrue(matcher.matches());

			String actualGroup = matcher.group("digit");

			assertEquals("0123", actualGroup);
		}

		/**
		 * Проверка константы {@link Regexp#DIGIT_DECIMAL} с некорректным значением.
		 */
		@Test
		public void digitDecimalNotCorrect()
		{
			String value = "45f65434d34ffd";
			Pattern pattern = Pattern.compile(Regexp.DIGIT_DECIMAL.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);

			boolean actual = matcher.matches();

			assertFalse(actual);
		}

		/**
		 * Проверка регулярного выражения константы {@link Regexp#DIGIT_DUODECIMAL}.
		 */
		@Test
		public void digitDuodecimal()
		{
			String value = "8819a861bB14Aab04ab519a2baA3b7a9B";
			Pattern pattern = Pattern.compile(
				Regexp.DIGIT_DUODECIMAL.getPattern(),
				Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
			);
			Matcher matcher = pattern.matcher(value);
			assertTrue(matcher.matches());

			String actualGroup = matcher.group("digit");

			assertEquals("8819a861bB14Aab04ab519a2baA3b7a9B", actualGroup);
		}

		/**
		 * Проверка константы {@link Regexp#DIGIT_DUODECIMAL} с некорректным значением.
		 */
		@Test
		public void digitDuodecimalNotCorrect()
		{
			String value = "457N678H6734X3ZS2";
			Pattern pattern = Pattern.compile(Regexp.DIGIT_DUODECIMAL.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);

			boolean actual = matcher.matches();

			assertFalse(actual);
		}

		/**
		 * Проверка регулярного выражения константы {@link Regexp#DIGIT_HEXADECIMAL}.
		 */
		@Test
		public void digitHexadecimal()
		{
			String value = "0123456789ABCDEF";
			Pattern pattern = Pattern.compile(
				Regexp.DIGIT_HEXADECIMAL.getPattern(),
				Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
			);
			Matcher matcher = pattern.matcher(value);
			assertTrue(matcher.matches());

			String actualGroup = matcher.group("digit");

			assertEquals("0123456789ABCDEF", actualGroup);
		}

		/**
		 * Проверка константы {@link Regexp#DIGIT_HEXADECIMAL} с некорректным значением.
		 */
		@Test
		public void digitHexadecimalNotCorrect()
		{
			String value = "345F45Y56G5";
			Pattern pattern = Pattern.compile(Regexp.DIGIT_HEXADECIMAL.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);

			boolean actual = matcher.matches();

			assertFalse(actual);
		}

		/**
		 * Проверка регулярного выражения константы {@link Regexp#NUMBER}.
		 */
		@Test
		public void number()
		{
			String value = "1.100";
			Pattern pattern = Pattern.compile(Regexp.NUMBER.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);
			assertTrue(matcher.matches());

			String actualGroup = matcher.group("number");

			assertEquals("1.100", actualGroup);
		}

		/**
		 * Проверка константы {@link Regexp#NUMBER} с некорректным значением.
		 */
		@Test
		public void numberNotCorrect()
		{
			String value = "asd";
			Pattern pattern = Pattern.compile(Regexp.NUMBER.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);

			boolean actual = matcher.matches();

			assertFalse(actual);
		}

		/**
		 * Проверка регулярного выражения константы {@link Regexp#WORD}.
		 */
		@Test
		public void word()
		{
			String value = "Привет";
			Pattern pattern = Pattern.compile(
				Regexp.WORD.getPattern(),
				Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
			);
			Matcher matcher = pattern.matcher(value);
			assertTrue(matcher.matches());

			String actualGroup = matcher.group("word");

			assertEquals("Привет", actualGroup);
		}

		/**
		 * Проверка константы {@link Regexp#WORD} с некорректным значением.
		 */
		@Test
		public void wordNotCorrect()
		{
			String value = "34546";
			Pattern pattern = Pattern.compile(Regexp.WORD.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);

			boolean actual = matcher.matches();

			assertFalse(actual);
		}

		/**
		 * Проверка регулярного выражения константы {@link Regexp#LETTER}.
		 */
		@Test
		public void letter()
		{
			String value = "Ъ";
			Pattern pattern = Pattern.compile(
				Regexp.LETTER.getPattern(),
				Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
			);
			Matcher matcher = pattern.matcher(value);
			assertTrue(matcher.matches());

			String actualGroup = matcher.group("letter");

			assertEquals("Ъ", actualGroup);
		}

		/**
		 * Проверка константы {@link Regexp#LETTER} с некорректным значением.
		 */
		@Test
		public void letterNotCorrect()
		{
			String value = "0";
			Pattern pattern = Pattern.compile(Regexp.LETTER.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);

			boolean actual = matcher.matches();

			assertFalse(actual);
		}
	}
}
