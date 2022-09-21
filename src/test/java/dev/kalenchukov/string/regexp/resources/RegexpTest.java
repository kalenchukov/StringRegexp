/*
 * Copyright © 2022 Алексей Каленчуков
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

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class RegexpTest
{
	/**
	 * Проверка групп.
	 */
	@Test
	public void testRegexpLocalization()
	{
		String string = "ru-RU";

		Pattern pattern = Pattern.compile(Regexp.LOCALIZATION.getPattern());
		Matcher matcher = pattern.matcher(string);

		assertTrue(matcher.find());

		assertEquals("ru-RU", matcher.group(Regexp.LOCALIZATION.getGroup()));
		assertEquals("ru", matcher.group("language"));
		assertEquals("RU", matcher.group("country"));
	}

	/**
	 * Проверка групп.
	 */
	@Test
	public void testRegexpInetAddressVersion4()
	{
		String string = "192.168.1.100";

		Pattern pattern = Pattern.compile(Regexp.INET_4_ADDRESS.getPattern());
		Matcher matcher = pattern.matcher(string);

		assertTrue(matcher.find());

		assertEquals("192.168.1.100", matcher.group(Regexp.INET_4_ADDRESS.getGroup()));
		assertEquals("192", matcher.group("part1"));
		assertEquals("168", matcher.group("part2"));
		assertEquals("1", matcher.group("part3"));
		assertEquals("100", matcher.group("part4"));
	}

	/**
	 * Проверка групп.
	 */
	@Test
	public void testRegexpInetAddressVersion6()
	{
		String string = "2001:0DB8:11A3:09D7:1F34:8A2E:07A0:765D";

		Pattern pattern = Pattern.compile(
			Regexp.INET_6_ADDRESS.getPattern(),
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
		Matcher matcher = pattern.matcher(string);

		assertTrue(matcher.find());

		assertEquals("2001:0DB8:11A3:09D7:1F34:8A2E:07A0:765D", matcher.group(Regexp.INET_6_ADDRESS.getGroup()));
	}

	/**
	 * Проверка групп.
	 */
	@Test
	public void testRegexpEmailAddress()
	{
		String string = "aleksey.kalenchukov@yandex.ru";

		Pattern pattern = Pattern.compile(
			Regexp.EMAIL_ADDRESS.getPattern(),
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
		Matcher matcher = pattern.matcher(string);

		assertTrue(matcher.find());

		assertEquals("aleksey.kalenchukov@yandex.ru", matcher.group(Regexp.EMAIL_ADDRESS.getGroup()));
		assertEquals("aleksey.kalenchukov", matcher.group("local"));
		assertEquals("yandex.ru", matcher.group("domain"));
		assertEquals("ru", matcher.group("tld"));
	}

	/**
	 * Проверка групп.
	 */
	@Test
	public void testRegexpDomain()
	{
		String string = "kalenchukov.dev";

		Pattern pattern = Pattern.compile(
			Regexp.DOMAIN.getPattern(),
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
		Matcher matcher = pattern.matcher(string);

		assertTrue(matcher.find());

		assertEquals("kalenchukov.dev", matcher.group(Regexp.DOMAIN.getGroup()));
		assertEquals("dev", matcher.group("tld"));
	}

	/**
	 * Проверка групп.
	 */
	@Test
	public void testRegexpRgbNumeric()
	{
		String string = "215,200,166";

		Pattern pattern = Pattern.compile(Regexp.RGB_NUMERIC.getPattern());
		Matcher matcher = pattern.matcher(string);

		assertTrue(matcher.find());

		assertEquals("215,200,166", matcher.group(Regexp.RGB_NUMERIC.getGroup()));
		assertEquals("215", matcher.group("red"));
		assertEquals("200", matcher.group("green"));
		assertEquals("166", matcher.group("blue"));
	}

	/**
	 * Проверка групп.
	 */
	@Test
	public void testRegexpRgbHex()
	{
		String string = "#ABCDEF";

		Pattern pattern = Pattern.compile(
			Regexp.RGB_HEX.getPattern(),
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
		Matcher matcher = pattern.matcher(string);

		assertTrue(matcher.find());

		assertEquals("#ABCDEF", matcher.group(Regexp.RGB_HEX.getGroup()));
		assertEquals("AB", matcher.group("red"));
		assertEquals("CD", matcher.group("green"));
		assertEquals("EF", matcher.group("blue"));
	}

	/**
	 * Проверка групп.
	 */
	@Test
	public void testRegexpMacAddress()
	{
		String string = "00-eF-cd-Ef-11-22";

		Pattern pattern = Pattern.compile(
			Regexp.MAC_ADDRESS.getPattern(),
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
		Matcher matcher = pattern.matcher(string);

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
	 * Проверка групп.
	 */
	@Test
	public void testRegexpTelegram()
	{
		String string = "@kalenchukov";

		Pattern pattern = Pattern.compile(
			Regexp.TELEGRAM.getPattern(),
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
		Matcher matcher = pattern.matcher(string);

		assertTrue(matcher.find());

		assertEquals("@kalenchukov", matcher.group(Regexp.TELEGRAM.getGroup()));
		assertEquals("kalenchukov", matcher.group("name"));
	}

	/**
	 * Проверка групп.
	 */
	@Test
	public void testRegexpTag()
	{
		String string = "#tag";

		Pattern pattern = Pattern.compile(
			Regexp.TAG.getPattern(),
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
		Matcher matcher = pattern.matcher(string);

		assertTrue(matcher.find());

		assertEquals("#tag", matcher.group(Regexp.TAG.getGroup()));
		assertEquals("tag", matcher.group("name"));
	}

	/**
	 * Проверка групп.
	 */
	@Test
	public void testRegexpHtmlComment()
	{
		String string = "<!-- Комментарий -->";

		Pattern pattern = Pattern.compile(
			Regexp.HTML_COMMENT.getPattern(),
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
		Matcher matcher = pattern.matcher(string);

		assertTrue(matcher.find());

		assertEquals("<!-- Комментарий -->", matcher.group(Regexp.HTML_COMMENT.getGroup()));
		assertEquals(" Комментарий ", matcher.group("value"));
	}

	/**
	 * Проверка групп.
	 */
	@Test
	public void testRegexpHtmlEntityName()
	{
		String string = "&DownArrowBar;";

		Pattern pattern = Pattern.compile(
			Regexp.HTML_ENTITY_NAME.getPattern(),
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
		Matcher matcher = pattern.matcher(string);

		assertTrue(matcher.find());

		assertEquals("&DownArrowBar;", matcher.group(Regexp.HTML_ENTITY_NAME.getGroup()));
		assertEquals("DownArrowBar", matcher.group("name"));
	}

	/**
	 * Проверка групп.
	 */
	@Test
	public void testRegexpHtmlEntityNumeric()
	{
		String string = "&#0010590;";

		Pattern pattern = Pattern.compile(
			Regexp.HTML_ENTITY_NUMERIC.getPattern(),
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
		Matcher matcher = pattern.matcher(string);

		assertTrue(matcher.find());

		assertEquals("&#0010590;", matcher.group(Regexp.HTML_ENTITY_NUMERIC.getGroup()));
		assertEquals("0010590", matcher.group("numeric"));
		assertEquals("10590", matcher.group("numericLeast"));
	}

	/**
	 * Проверка групп.
	 */
	@Test
	public void testRegexpHtmlEntityUnicode()
	{
		String string = "&#X000154;";

		Pattern pattern = Pattern.compile(
			Regexp.HTML_ENTITY_UNICODE.getPattern(),
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
		Matcher matcher = pattern.matcher(string);

		assertTrue(matcher.find());

		assertEquals("&#X000154;", matcher.group(Regexp.HTML_ENTITY_UNICODE.getGroup()));
		assertEquals("000154", matcher.group("unicode"));
		assertEquals("154", matcher.group("unicodeLeast"));
	}

	/**
	 * Проверка групп.
	 */
	@Test
	public void testRegexpHtmlDoctype()
	{
		String string = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">";

		Pattern pattern = Pattern.compile(
			Regexp.HTML_DOCTYPE.getPattern(),
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
		Matcher matcher = pattern.matcher(string);

		assertTrue(matcher.find());

		assertEquals(
			"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">",
			matcher.group(Regexp.HTML_DOCTYPE.getGroup())
		);
		assertEquals("HTML", matcher.group("rootElement"));
		assertEquals("PUBLIC", matcher.group("public"));
		assertEquals("-//W3C//DTD HTML 4.01//EN", matcher.group("dtd"));
		assertEquals("-", matcher.group("registration"));
		assertEquals("W3C", matcher.group("organization"));
		assertEquals("DTD HTML 4.01", matcher.group("documentType"));
		assertEquals("EN", matcher.group("language"));
		assertEquals("http://www.w3.org/TR/html4/strict.dtd", matcher.group("url"));
	}

	/**
	 * Проверка групп.
	 */
	@Test
	public void testRegexpHtmlEndTag()
	{
		String string = "</form >";

		Pattern pattern = Pattern.compile(
			Regexp.HTML_END_TAG.getPattern(),
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
		Matcher matcher = pattern.matcher(string);

		assertTrue(matcher.find());

		assertEquals("</form >", matcher.group(Regexp.HTML_END_TAG.getGroup()));
		assertEquals("form", matcher.group("name"));
	}

	/**
	 * Проверка групп.
	 */
	@Test
	public void testRegexpHtmlSelfClosingTag()
	{
		String string = "<input name=viewport content=\"width=device-width, initial-scale=1, user-scalable=0\"/>";

		Pattern pattern = Pattern.compile(
			Regexp.HTML_SELF_CLOSING_TAG.getPattern(),
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
		Matcher matcher = pattern.matcher(string);

		assertTrue(matcher.find());

		assertEquals(
			"<input name=viewport content=\"width=device-width, initial-scale=1, user-scalable=0\"/>",
			matcher.group(Regexp.HTML_SELF_CLOSING_TAG.getGroup())
		);
		assertEquals("input", matcher.group("name"));
		assertEquals(
			"content=\"width=device-width, initial-scale=1, user-scalable=0\"",
			matcher.group("param")
		);
		assertEquals("content", matcher.group("paramName"));
		assertEquals(
			"\"width=device-width, initial-scale=1, user-scalable=0\"",
			matcher.group("paramValue")
		);
	}

	/**
	 * Проверка групп.
	 */
	@Test
	public void testRegexpHtmlStartTag()
	{
		String string = "<meta name=viewport content=\"width=device-width, initial-scale=1, user-scalable=0\">";

		Pattern pattern = Pattern.compile(
			Regexp.HTML_START_TAG.getPattern(),
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
		Matcher matcher = pattern.matcher(string);

		assertTrue(matcher.find());

		assertEquals(
			"<meta name=viewport content=\"width=device-width, initial-scale=1, user-scalable=0\">",
			matcher.group(Regexp.HTML_START_TAG.getGroup())
		);
		assertEquals("meta", matcher.group("name"));
		assertEquals("content", matcher.group("paramName"));
		assertEquals("\"width=device-width, initial-scale=1, user-scalable=0\"", matcher.group("paramValue"));
	}

	/**
	 * Проверка групп.
	 */
	@Test
	public void testRegexpCData()
	{
		String string = "<![CDATA[ Текст ]]>";

		Pattern pattern = Pattern.compile(
			Regexp.CDATA.getPattern(),
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
		Matcher matcher = pattern.matcher(string);

		assertTrue(matcher.find());

		assertEquals("<![CDATA[ Текст ]]>", matcher.group(Regexp.CDATA.getGroup()));
		assertEquals(" Текст ", matcher.group("value"));
	}

	/**
	 * Проверка групп.
	 */
	@Test
	public void testRegexpDigitDecimal()
	{
		String string = "0123";

		Pattern pattern = Pattern.compile(Regexp.DIGIT_DECIMAL.getPattern());
		Matcher matcher = pattern.matcher(string);

		assertTrue(matcher.find());

		assertEquals("0123", matcher.group(Regexp.DIGIT_DECIMAL.getGroup()));
	}

	/**
	 * Проверка групп.
	 */
	@Test
	public void testRegexpNumber()
	{
		String string = "1.100";

		Pattern pattern = Pattern.compile(Regexp.NUMBER.getPattern());
		Matcher matcher = pattern.matcher(string);

		assertTrue(matcher.find());

		assertEquals("1.100", matcher.group(Regexp.NUMBER.getGroup()));
	}

	/**
	 * Проверка групп.
	 */
	@Test
	public void testRegexpWord()
	{
		String string = "Привет";

		Pattern pattern = Pattern.compile(
			Regexp.WORD.getPattern(),
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
		Matcher matcher = pattern.matcher(string);

		assertTrue(matcher.find());

		assertEquals("Привет", matcher.group(Regexp.WORD.getGroup()));
	}

	/**
	 * Проверка групп.
	 */
	@Test
	public void testRegexpLetter()
	{
		String string = "Ъ";

		Pattern pattern = Pattern.compile(
			Regexp.LETTER.getPattern(),
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
		Matcher matcher = pattern.matcher(string);

		assertTrue(matcher.find());

		assertEquals("Ъ", matcher.group(Regexp.LETTER.getGroup()));
	}
}