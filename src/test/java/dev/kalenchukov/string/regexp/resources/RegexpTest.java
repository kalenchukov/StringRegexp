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

		Pattern pattern = Pattern.compile(Regexp.INET_6_ADDRESS.getPattern());
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

		Pattern pattern = Pattern.compile(Regexp.EMAIL_ADDRESS.getPattern());
		Matcher matcher = pattern.matcher(string);

		assertTrue(matcher.find());

		assertEquals("aleksey.kalenchukov@yandex.ru", matcher.group(Regexp.EMAIL_ADDRESS.getGroup()));
		assertEquals("aleksey.kalenchukov", matcher.group("local"));
		assertEquals("yandex.ru", matcher.group("domain"));
		assertEquals("ru", matcher.group("tld"));
	}
}