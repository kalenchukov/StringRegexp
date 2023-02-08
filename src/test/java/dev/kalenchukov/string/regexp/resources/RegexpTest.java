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

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс проверки констант и методов перечисления {@link Regexp}.
 */
public class RegexpTest
{
	/**
	 * Проверка метода {@link Regexp#getGroup()}.
	 */
	@Test
	public void getGroup()
	{
		assertEquals("localization", Regexp.LOCALIZATION.getGroup());
	}

	/**
	 * Проверка метода {@link Regexp#getPattern()}.
	 */
	@Test
	public void getPattern()
	{
		assertTrue( Regexp.LOCALIZATION.getPattern().length() > 0);
	}

	/**
	 * Проверка групп константы {@link Regexp#LOCALIZATION}.
	 */
	@Test
	public void testGroupLocalization()
	{
		String value = "ru-RU";

		Pattern pattern = Pattern.compile(Regexp.LOCALIZATION.getPattern());
		Matcher matcher = pattern.matcher(value);

		assertTrue(matcher.find());

		assertEquals("ru-RU", matcher.group(Regexp.LOCALIZATION.getGroup()));
		assertEquals("ru", matcher.group("language"));
		assertEquals("RU", matcher.group("country"));
	}

	/**
	 * Проверка групп константы {@link Regexp#INET_4_ADDRESS}.
	 */
	@Test
	public void testGroupInet4Address()
	{
		String value = "192.168.1.100";

		Pattern pattern = Pattern.compile(Regexp.INET_4_ADDRESS.getPattern());
		Matcher matcher = pattern.matcher(value);

		assertTrue(matcher.find());

		assertEquals("192.168.1.100", matcher.group(Regexp.INET_4_ADDRESS.getGroup()));
		assertEquals("192", matcher.group("part1"));
		assertEquals("168", matcher.group("part2"));
		assertEquals("1", matcher.group("part3"));
		assertEquals("100", matcher.group("part4"));
	}

	/**
	 * Проверка групп константы {@link Regexp#INET_6_ADDRESS}.
	 */
	@Test
	public void testGroupInet6Address()
	{
		String value = "2001:0DB8:11A3:09D7:1F34:8A2E:07A0:765D";

		Pattern pattern = Pattern.compile(
			Regexp.INET_6_ADDRESS.getPattern(),
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
		Matcher matcher = pattern.matcher(value);

		assertTrue(matcher.find());

		assertEquals("2001:0DB8:11A3:09D7:1F34:8A2E:07A0:765D", matcher.group(Regexp.INET_6_ADDRESS.getGroup()));
	}

	/**
	 * Проверка групп константы {@link Regexp#EMAIL_ADDRESS}.
	 */
	@Test
	public void testGroupEmailAddress()
	{
		String value = "aleksey.kalenchukov@yandex.ru";

		Pattern pattern = Pattern.compile(
			Regexp.EMAIL_ADDRESS.getPattern(),
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
		Matcher matcher = pattern.matcher(value);

		assertTrue(matcher.find());

		assertEquals("aleksey.kalenchukov@yandex.ru", matcher.group(Regexp.EMAIL_ADDRESS.getGroup()));
		assertEquals("aleksey.kalenchukov", matcher.group("local"));
		assertEquals("yandex.ru", matcher.group("domain"));
		assertEquals("ru", matcher.group("tld"));
	}

	/**
	 * Проверка групп константы {@link Regexp#DOMAIN}.
	 */
	@Test
	public void testGroupDomain()
	{
		String value = "kalenchukov.dev";

		Pattern pattern = Pattern.compile(
			Regexp.DOMAIN.getPattern(),
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
		Matcher matcher = pattern.matcher(value);

		assertTrue(matcher.find());

		assertEquals("kalenchukov.dev", matcher.group(Regexp.DOMAIN.getGroup()));
		assertEquals("dev", matcher.group("tld"));
	}

	/**
	 * Проверка групп константы {@link Regexp#URL_HTTP}.
	 */
	@Test
	public void testGroupUrlHttp()
	{
		String value = "http://www.kalenchukov.dev/hello/world/?java=18&hello=world123#anchor";

		Pattern pattern = Pattern.compile(
			Regexp.URL_HTTP.getPattern(),
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
		Matcher matcher = pattern.matcher(value);

		assertTrue(matcher.find());

		assertEquals("http://www.kalenchukov.dev/hello/world/?java=18&hello=world123#anchor", matcher.group(Regexp.URL_HTTP.getGroup()));
		assertEquals("http", matcher.group("protocol"));
		assertEquals("kalenchukov.dev", matcher.group("domain"));
		assertEquals("dev", matcher.group("tld"));
		assertEquals("hello/world/", matcher.group("path"));
		assertEquals("java=18&hello=world123", matcher.group("param"));
		assertEquals("anchor", matcher.group("anchor"));
	}

	/**
	 * Проверка групп константы {@link Regexp#RGB_NUMERIC}.
	 */
	@Test
	public void testGroupRgbNumeric()
	{
		String value = "215,200,166";

		Pattern pattern = Pattern.compile(Regexp.RGB_NUMERIC.getPattern());
		Matcher matcher = pattern.matcher(value);

		assertTrue(matcher.find());

		assertEquals("215,200,166", matcher.group(Regexp.RGB_NUMERIC.getGroup()));
		assertEquals("215", matcher.group("red"));
		assertEquals("200", matcher.group("green"));
		assertEquals("166", matcher.group("blue"));
	}

	/**
	 * Проверка групп константы {@link Regexp#RGB_HEX}.
	 */
	@Test
	public void testGroupRgbHex()
	{
		String value = "#ABCDEF";

		Pattern pattern = Pattern.compile(
			Regexp.RGB_HEX.getPattern(),
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
		Matcher matcher = pattern.matcher(value);

		assertTrue(matcher.find());

		assertEquals("#ABCDEF", matcher.group(Regexp.RGB_HEX.getGroup()));
		assertEquals("AB", matcher.group("red"));
		assertEquals("CD", matcher.group("green"));
		assertEquals("EF", matcher.group("blue"));
	}

	/**
	 * Проверка групп константы {@link Regexp#MAC_ADDRESS}.
	 */
	@Test
	public void testGroupMacAddress()
	{
		String value = "00-eF-cd-Ef-11-22";

		Pattern pattern = Pattern.compile(
			Regexp.MAC_ADDRESS.getPattern(),
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
		Matcher matcher = pattern.matcher(value);

		assertTrue(matcher.find());

		assertEquals("00-eF-cd-Ef-11-22", matcher.group(Regexp.MAC_ADDRESS.getGroup()));
		assertEquals("-", matcher.group("separator"));
		assertEquals("00", matcher.group("part1"));
		assertEquals("eF", matcher.group("part2"));
		assertEquals("cd", matcher.group("part3"));
		assertEquals("Ef", matcher.group("part4"));
		assertEquals("11", matcher.group("part5"));
		assertEquals("22", matcher.group("part6"));
	}

	/**
	 * Проверка групп константы {@link Regexp#TELEGRAM}.
	 */
	@Test
	public void testGroupTelegram()
	{
		String value = "@kalenchukov";

		Pattern pattern = Pattern.compile(
			Regexp.TELEGRAM.getPattern(),
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
		Matcher matcher = pattern.matcher(value);

		assertTrue(matcher.find());

		assertEquals("@kalenchukov", matcher.group(Regexp.TELEGRAM.getGroup()));
		assertEquals("kalenchukov", matcher.group("name"));
	}

	/**
	 * Проверка групп константы {@link Regexp#TAG}.
	 */
	@Test
	public void testGroupTag()
	{
		String value = "#tag";

		Pattern pattern = Pattern.compile(
			Regexp.TAG.getPattern(),
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
		Matcher matcher = pattern.matcher(value);

		assertTrue(matcher.find());

		assertEquals("#tag", matcher.group(Regexp.TAG.getGroup()));
		assertEquals("tag", matcher.group("name"));
	}

	/**
	 * Проверка групп константы {@link Regexp#DIGIT_BINARY}.
	 */
	@Test
	public void testGroupDigitBinary()
	{
		String value = "0101010101";

		Pattern pattern = Pattern.compile(Regexp.DIGIT_BINARY.getPattern());
		Matcher matcher = pattern.matcher(value);

		assertTrue(matcher.find());

		assertEquals("0101010101", matcher.group(Regexp.DIGIT_BINARY.getGroup()));
	}

	/**
	 * Проверка групп константы {@link Regexp#DIGIT_TERNARY}.
	 */
	@Test
	public void testGroupDigitTernary()
	{
		String value = "210210210210";

		Pattern pattern = Pattern.compile(Regexp.DIGIT_TERNARY.getPattern());
		Matcher matcher = pattern.matcher(value);

		assertTrue(matcher.find());

		assertEquals("210210210210", matcher.group(Regexp.DIGIT_TERNARY.getGroup()));
	}

	/**
	 * Проверка групп константы {@link Regexp#DIGIT_OCTAL}.
	 */
	@Test
	public void testGroupDigitOctal()
	{
		String value = "65016716501710651467106";

		Pattern pattern = Pattern.compile(Regexp.DIGIT_OCTAL.getPattern());
		Matcher matcher = pattern.matcher(value);

		assertTrue(matcher.find());

		assertEquals("65016716501710651467106", matcher.group(Regexp.DIGIT_OCTAL.getGroup()));
	}

	/**
	 * Проверка групп константы {@link Regexp#DIGIT_DECIMAL}.
	 */
	@Test
	public void testGroupDigitDecimal()
	{
		String value = "0123";

		Pattern pattern = Pattern.compile(Regexp.DIGIT_DECIMAL.getPattern());
		Matcher matcher = pattern.matcher(value);

		assertTrue(matcher.find());

		assertEquals("0123", matcher.group(Regexp.DIGIT_DECIMAL.getGroup()));
	}

	/**
	 * Проверка групп константы {@link Regexp#DIGIT_DUODECIMAL}.
	 */
	@Test
	public void testGroupDigitDuodecimal()
	{
		String value = "8819a861bB14Aab04ab519a2baA3b7a9B";

		Pattern pattern = Pattern.compile(
			Regexp.DIGIT_DUODECIMAL.getPattern(),
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
		Matcher matcher = pattern.matcher(value);

		assertTrue(matcher.find());

		assertEquals("8819a861bB14Aab04ab519a2baA3b7a9B", matcher.group(Regexp.DIGIT_DUODECIMAL.getGroup()));
	}

	/**
	 * Проверка групп константы {@link Regexp#DIGIT_HEXADECIMAL}.
	 */
	@Test
	public void testGroupDigitHexadecimal()
	{
		String value = "0123456789ABCDEF";

		Pattern pattern = Pattern.compile(
			Regexp.DIGIT_HEXADECIMAL.getPattern(),
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
		Matcher matcher = pattern.matcher(value);

		assertTrue(matcher.find());

		assertEquals("0123456789ABCDEF", matcher.group(Regexp.DIGIT_HEXADECIMAL.getGroup()));
	}

	/**
	 * Проверка групп константы {@link Regexp#NUMBER}.
	 */
	@Test
	public void testGroupNumber()
	{
		String value = "1.100";

		Pattern pattern = Pattern.compile(Regexp.NUMBER.getPattern());
		Matcher matcher = pattern.matcher(value);

		assertTrue(matcher.find());

		assertEquals("1.100", matcher.group(Regexp.NUMBER.getGroup()));
	}

	/**
	 * Проверка групп константы {@link Regexp#WORD}.
	 */
	@Test
	public void testGroupWord()
	{
		String value = "Привет";

		Pattern pattern = Pattern.compile(
			Regexp.WORD.getPattern(),
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
		Matcher matcher = pattern.matcher(value);

		assertTrue(matcher.find());

		assertEquals("Привет", matcher.group(Regexp.WORD.getGroup()));
	}

	/**
	 * Проверка групп константы {@link Regexp#LETTER}.
	 */
	@Test
	public void testGroupLetter()
	{
		String value = "Ъ";

		Pattern pattern = Pattern.compile(
			Regexp.LETTER.getPattern(),
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
		Matcher matcher = pattern.matcher(value);

		assertTrue(matcher.find());

		assertEquals("Ъ", matcher.group(Regexp.LETTER.getGroup()));
	}
}
