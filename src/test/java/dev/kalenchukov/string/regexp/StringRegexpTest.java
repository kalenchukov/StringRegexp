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

package dev.kalenchukov.string.regexp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс проверки методов класса {@link StringRegexp}.
 *
 * @author Алексей Каленчуков
 */
public class StringRegexpTest
{
	/**
	 * Проверка метода {@link StringRegexp#isLocalization(String)}.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"ru-RU"
	})
	public void isLocalization(String value)
	{
		assertTrue(StringRegexp.isLocalization(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isLocalization(String)} с некорректными значениями.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"", " ",
		"RU-RU", "RU-ru", "ru-ru",
		"ru", "RU"
	})
	public void isLocalizationNotCorrect(String value)
	{
		assertFalse(StringRegexp.isLocalization(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isInet4Address(String)}.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"192.168.1.1", "1.1.1.1", "0.0.0.0"
	})
	public void isInet4Address(String value)
	{
		assertTrue(StringRegexp.isInet4Address(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isInet4Address(String)} с некорректными значениями.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"", " ",
		"1.1.1.1.", ".6.6.6.6",
		"2.2.2.2.2", "3", "4.", "5.5",
		"257.168.1.1", "192.257.1.1", "192.168.257.1", "192.168.1.256",
	})
	public void isInet4AddressNotCorrect(String value)
	{
		assertFalse(StringRegexp.isInet4Address(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isInet6Address(String)}.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"2001:0DB8:11A3:09D7:1F34:8A2E:07A0:765D"
	})
	public void isInet6Address(String value)
	{
		assertTrue(StringRegexp.isInet6Address(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isInet6Address(String)} с некорректными значениями.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"2001:0db8:11a3:09d7:1F34:8A2E:07A0:765D"
	})
	public void isInet6AddressNotCorrect(String value)
	{
		assertFalse(StringRegexp.isInet6Address(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isInet6AddressIgnoreCase(String)}.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"2001:0db8:11a3:09d7:1F34:8A2E:07A0:765D",
		"2001:0db8:0000:0000:0000:0000:ae21:AD12", "0000:0000:0000:0000:0000:0000:ae21:AD12",
		"2001:0DB8:0000:0000:0000:0000:ae21:1", "2001:0DB8:0000:1:0000:0000:ae21:AD12",
		"1:0000:0000:0000:0000:0000:ae21:AD12",
		"::0DB8:11A3:09D7:1F34:8A2E:07a0:765D", "0DB8:11A3:09D7:1F34::07a0:765D",
		"2001:0DB8:11A3:09D7:1F34:07a0:07A0::",
		"::ae21:AD12", "AD12::ae21::",
		"2001::", "::2001",
		"::",
		"::1", "1::",
		"EF98:3:0:0:0:0:2F3B:7654", "EF98:3::2f3b:7654", "2001:DB8::ae21:AD12",
	})
	public void isInet6AddressIgnoreCase(String value)
	{
		assertTrue(StringRegexp.isInet6AddressIgnoreCase(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isInet6AddressIgnoreCase(String)} с некорректными значениями.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"", " ",
		"1000", "1000:", ":1000",
		":", ":::", "::::",
		"2001:0db8:11a3:09d7:1F34:8A2E:07A0:765D:765D",
		":0db8:11a3:09d7:1f34:8A2E:07A0:765D", "2001:0db8:11a3:09d7:1F34:8A2E:07A0:",
		":::0db8:11a3:09d7:1f34:8A2E:07A0:765D", "2001:0db8:11a3:09d7:1F34:8A2E:07A0:::",
		"::::0db8:11a3:09d7:1F34:8A2E:07A0:765D", "0db8:11a3:09d7::::1f34:8A2E:07A0:765D",
		"2001:0db8:11a3:09d7:1F34:8A2E:07A0::::",
		":0db8:11a3:09d7:1f34:8A2E:07A0:765D:1000", "1000:2001:0db8:11a3:09D7:1F34:8A2E:07A0:",
		":0db8:11a3:09d7:1f34:8A2E:07A0:765D:1", "1:2001:0db8:11a3:09d7:1f34:8A2E:07A0:",
		"::0db8:11a3:09d7:1f34:8A2E:07A0:765D:1", "1:2001:0db8:11a3:09d7:1f34:8A2E:07A0::",
		":::0db8:11a3:09d7:1f34:8a2e:07A0:765D:1", "1:2001:0db8:11a3:09d7:1F34:8A2E:07A0:::",
		"0db8:11a3::::8A2E:07A0:765D", "0db8:11a3::::::8A2E:07A0:765D"
	})
	public void isInet6AddressIgnoreCaseNotCorrect(String value)
	{
		assertFalse(StringRegexp.isInet6AddressIgnoreCase(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isEmailAddress(String)}.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"aleksey.kalenchukov@yandex.ru", "aleksey.kalenchukov_@yandex.ru",
		"_aleksey.kalenchukov_@yandex.ru", "-aleksey.kalenchukov-@yandex.ru",
		"aleksey.kalenchukov@mail.yandex.ru", "alekseykalenchukov@yandex.ru",
		"aleksey.kalenchukov@yandex.com", "aleksey-kalenchukov@yandex.ru",
		"aleksey_kalenchukov@yandex.ru", "AlekseyKalenchukov@yandex.ru",
		"АлексейКаленчуков@яндекс.ру", "a.k@yandex.ru",
		"k@yandex.ru", "kalenchukov@yandex.ru",
		"kalenchukov@y.ru", "aleksey.kalenchukovkalenchukovkalenchukovkalenchukovkalenchukov@yandex.ru",
		"aleksey123.kalenchukov123@123yandex.ru", "aleksey.kalenchukov@123.yandex.ru",
		"123aleksey.kalenchukov@123.yandex.ru", "123.456@123.ru", "123456@123.ru"
	})
	public void isEmailAddress(String value)
	{
		assertTrue(StringRegexp.isEmailAddress(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isEmailAddress(String)} с некорректными значениями.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"", " ",
		".aleksey.kalenchukov@yandex.ru", "aleksey.kalenchukov.@yandex.ru", "aleksey..kalenchukov@yandex.ru",
		"aleksey.kalenchukov@yandex", "aleksey.kalenchukov@yandex..ru", "aleksey.kalenchukov.yandex.ru",
		"aleksey.kalenchukov@yandex.r", "aleksey.kalenchukov@-yandex.ru",
		"aleksey.kalenchukovkalenchukovkalenchukovkalenchukovkalenchukovkalenchukov@yandex.ru",
	})
	public void isEmailAddressNotCorrect(String value)
	{
		assertFalse(StringRegexp.isEmailAddress(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isDomain(String)}.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"kalenchukov.dev", "regexp.string.kalenchukov.dev",
		"aleksey.123.kalenchukov.ru", "123.aleksey.kalenchukov.ru"
	})
	public void isDomain(String value)
	{
		assertTrue(StringRegexp.isDomain(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isDomain(String)} с некорректными значениями.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"", " ",
		"kalenchukov,dev", "regexp.string.kalenchukov.d"
	})
	public void isDomainNotCorrect(String value)
	{
		assertFalse(StringRegexp.isDomain(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isUrlHttp(String)}.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"http://kalenchukov.dev/hello/world/?java=18&hello=world123#anchor",
		"https://kalenchukov.dev/hello/world/?java=18&hello=world123#anchor",
		"http://www.kalenchukov.dev/hello/world?java=18&hello=world123#anchor",
		"http://www.kalenchukov.dev/hello/world/?java=18&hello=world123#anchor",
		"http://www.kalenchukov.dev/hello/world/?java=18&hello=world123#",
		"http://www.kalenchukov.dev/hello/world/?java=18&hello=world123",
		"http://www.kalenchukov.dev/hello/world/?java=18&hello=",
		"http://www.kalenchukov.dev/hello/world/?java=18&",
		"http://www.kalenchukov.dev/hello/world/?java=18",
		"http://www.kalenchukov.dev/hello/world/?",
		"http://www.kalenchukov.dev/hello/world/",
		"http://www.kalenchukov.dev/hello/world",
		"http://www.kalenchukov.dev/",
		"http://www.kalenchukov.dev",
		"http://www.kalenchukov.dev/hello/world",
		"http://www.kalenchukov.dev/?java=18&hello=world123#anchor",
		"http://www.kalenchukov.dev/#anchor"
	})
	public void isUrlHttp(String value)
	{
		assertTrue(StringRegexp.isUrlHttp(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isRgbNumeric(String)}.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"215,200,166", "0,0,0",
		"215, 200, 166", "0, 0, 0",
		"255,155, 55", "1,1,1"
	})
	public void isRgbNumeric(String value)
	{
		assertTrue(StringRegexp.isRgbNumeric(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isRgbNumeric(String)} с некорректными значениями.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"", " ",
		"0,0", "0",
		"215,  200, 166", "215, 200,  166",
		"-1, 0, 0", "0, -1, 0", "0, 0, -1",
		"215,200,", "1,1,1,",
		"256,155,55", "255,256,55", "255,155,256"
	})
	public void isRgbNumericNotCorrect(String value)
	{
		assertFalse(StringRegexp.isRgbNumeric(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isCountryCodeAlpha2(String)}.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"RU", "BY"
	})
	public void isCountryCodeAlpha2(String value)
	{
		assertTrue(StringRegexp.isCountryCodeAlpha2(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isCountryCodeAlpha2(String)} с некорректными значениями.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"", " ", "ru", "rub", "#RU", "0RU"
	})
	public void isCountryCodeAlpha2NotCorrect(String value)
	{
		assertFalse(StringRegexp.isCountryCodeAlpha2(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isCountryCodeAlpha3(String)}.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"RUS", "BLR"
	})
	public void isCountryCodeAlpha3(String value)
	{
		assertTrue(StringRegexp.isCountryCodeAlpha3(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isCountryCodeAlpha3(String)} с некорректными значениями.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"", " ", "rus", "rub", "#RUS", "0RUS"
	})
	public void isCountryCodeAlpha3NotCorrect(String value)
	{
		assertFalse(StringRegexp.isCountryCodeAlpha3(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isCountryCodeNumeric3(String)}.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"052", "112", "643"
	})
	public void isCountryCodeNumeric3(String value)
	{
		assertTrue(StringRegexp.isCountryCodeNumeric3(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isCountryCodeNumeric3(String)} с некорректными значениями.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"", " ", "52", "0052", "#643"
	})
	public void isCountryCodeNumeric3NotCorrect(String value)
	{
		assertFalse(StringRegexp.isCountryCodeNumeric3(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isMd5(String)}.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
			"1BC29B36F623BA82AAF6724FD3B16718",
			"D41D8CD98F00B204E9800998ECF8427E"
	})
	public void isMd5(String value)
	{
		assertTrue(StringRegexp.isMd5(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isMd5(String)} с некорректными значениями.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
			"", " ",
			"1BC29B36F623BA82AAF6724FD3B1671",
			"1bc29b36f623ba82aaf6724fd3b16718", "1bc29B36F623BA82AAF6724FD3B16718"
	})
	public void isMd5NotCorrect(String value)
	{
		assertFalse(StringRegexp.isMd5(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isMd5IgnoreCase(String)}.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
			"1BC29B36F623BA82AAF6724FD3B16718",
			"1bc29b36f623ba82aaf6724fd3b16718",
			"d41d8cd98f00b204E9800998ECF8427E"
	})
	public void isMd5IgnoreCase(String value)
	{
		assertTrue(StringRegexp.isMd5IgnoreCase(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isMd5IgnoreCase(String)} с некорректными значениями.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
			"", " ",
			"1BC29B36F623BA82AAF6724FD3B1671", "1bc29b36f623ba82aaf6724fd3b1671",
			"1bc29b36f623ba82af6724fd3b16718"
	})
	public void isMd5IgnoreCaseNotCorrect(String value)
	{
		assertFalse(StringRegexp.isMd5IgnoreCase(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isRgbHex(String)}.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"#FFFFFF", "#ABCDEF",
		"#000000", "#123456"
	})
	public void isRgbHex(String value)
	{
		assertTrue(StringRegexp.isRgbHex(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isRgbHex(String)} с некорректными значениями.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"", " ",
		"FFFFFF",
		"#23394W", "#ARDBZG"
	})
	public void isRgbHexNotCorrect(String value)
	{
		assertFalse(StringRegexp.isRgbHex(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isRgbHexIgnoreCase(String)}.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"#fffFFF", "#abcDEF",
		"#000000", "#123456"
	})
	public void isRgbHexIgnoreCase(String value)
	{
		assertTrue(StringRegexp.isRgbHexIgnoreCase(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isRgbHexIgnoreCase(String)} с некорректными значениями.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"", " ",
		"fffFFF",
		"#23394w", "#ardBZG"
	})
	public void isRgbHexIgnoreCaseNotCorrect(String value)
	{
		assertFalse(StringRegexp.isRgbHexIgnoreCase(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isMacAddress(String)}.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"00-EF-CD-EF-11-22", "00:EF:CD:EF:11:22"
	})
	public void isMacAddress(String value)
	{
		assertTrue(StringRegexp.isMacAddress(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isMacAddress(String)} с некорректными значениями.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"", " ",
		"-00-EF-CD-EF-11-22", "-00-EF-CD-EF-11-22-", "00-EF-CD-EF-11-22-",
		":00:EF:CD:EF:11:22", ":00:EF:CD:EF:11:22:", "00:EF:CD:EF:11:22:",
		"00:EF:CD:EF:11:2", "00:EW:CD:EF:11:22", "00:ef:CD:EF:11:22", "00:EF:CD::EF:11:22", "00:EF::EF:11:22",
	})
	public void isMacAddressNotCorrect(String value)
	{
		assertFalse(StringRegexp.isMacAddress(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isMacAddressIgnoreCase(String)}.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"00-EF-cd-EF-11-22", "00:ef:CD:EF:11:22"
	})
	public void isMacAddressIgnoreCase(String value)
	{
		assertTrue(StringRegexp.isMacAddressIgnoreCase(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isMacAddressIgnoreCase(String)} с некорректными значениями.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"", " ",
		"00:EF:CD:EF:11:2", "00:ew:CD:EF:11:22",
		"00:EF:CD::ef:11:22", "00:EF::ef:11:22"
	})
	public void isMacAddressIgnoreCaseNotCorrect(String value)
	{
		assertFalse(StringRegexp.isMacAddressIgnoreCase(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isTelegram(String)}.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"@kalenchukov", "@Kalenchukov", "@KALENCHUKOV",
		"@kalen_chukov", "@kalenchukov_", "@_kalenchukov",
	})
	public void isTelegram(String value)
	{
		assertTrue(StringRegexp.isTelegram(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isTelegram(String)} с некорректными значениями.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"", " ",
		"kalenchukov",
		"@kalenchukov.", "@kalenchukov-", "@kalenchukov+",
		"@kalen-chukov",
	})
	public void isTelegramNotCorrect(String value)
	{
		assertFalse(StringRegexp.isTelegram(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isTag(String)}.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"#tag", "#Tag", "#TAG",
		"#ta_g",
		"#123tag", "#tag123", "#tag_123",
		"#тег", "#тег123", "#тег_123",
	})
	public void isTag(String value)
	{
		assertTrue(StringRegexp.isTag(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isTag(String)} с некорректными значениями.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"", " ",
		"tag",
		"#t", "#ta",
		"#tag.", "#tag-", "#ta-g", "#tag+tag",
		"#_tag", "#tag_",
		"#_", "#__", "#___",
		"#123", "#123_", "#_123",
	})
	public void isTagNotCorrect(String value)
	{
		assertFalse(StringRegexp.isTag(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isDigitBinary(String)}.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"0", "1", "01010101", "1010101"
	})
	public void isDigitBinary(String value)
	{
		assertTrue(StringRegexp.isDigitBinary(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isDigitBinary(String)} с некорректными значениями.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"", " ",
		"1.0", "012", "1a0", "0a"
	})
	public void isDigitBinaryNotCorrect(String value)
	{
		assertFalse(StringRegexp.isDigitBinary(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isDigitTernary(String)}.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"0", "1", "2", "0101201012", "120102101"
	})
	public void isDigitTernary(String value)
	{
		assertTrue(StringRegexp.isDigitTernary(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isDigitTernary(String)} с некорректными значениями.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"", " ",
		"1.0", "1a0", "0a"
	})
	public void isDigitTernaryNotCorrect(String value)
	{
		assertFalse(StringRegexp.isDigitTernary(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isDigitOctal(String)}.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"01234567", "12014302101"
	})
	public void isDigitOctal(String value)
	{
		assertTrue(StringRegexp.isDigitOctal(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isDigitOctal(String)} с некорректными значениями.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"", " "
	})
	public void isDigitOctalNotCorrect(String value)
	{
		assertFalse(StringRegexp.isDigitOctal(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isDigitDecimal(String)}.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"0", "0123", "6516166848498494"
	})
	public void isDigitDecimal(String value)
	{
		assertTrue(StringRegexp.isDigitDecimal(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isDigitDecimal(String)} с некорректными значениями.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"", " ",
		"1.1", "1.1", "1a1", "1a"
	})
	public void isDigitDecimalNotCorrect(String value)
	{
		assertFalse(StringRegexp.isDigitDecimal(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isDigitDuodecimal(String)}.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"0123456789AB", "0123456789ab", "120143023649aB101"
	})
	public void isDigitDuodecimal(String value)
	{
		assertTrue(StringRegexp.isDigitDuodecimal(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isDigitDuodecimal(String)} с некорректными значениями.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"", " "
	})
	public void isDigitDuodecimalNotCorrect(String value)
	{
		assertFalse(StringRegexp.isDigitDuodecimal(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isDigitHexadecimal(String)}.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"0123456789ABCDEF", "0123456789abbcdef", "120ef143023649aB10cd1"
	})
	public void isDigitHexadecimal(String value)
	{
		assertTrue(StringRegexp.isDigitHexadecimal(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isDigitHexadecimal(String)} с некорректными значениями.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"", " "
	})
	public void isDigitHexadecimalNotCorrect(String value)
	{
		assertFalse(StringRegexp.isDigitHexadecimal(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isNumber(String)}.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"0", "100",
		"0.1", "0,1",
		"1.100", "1,100",
		"1,100,123", "1.100.123",
	})
	public void isNumber(String value)
	{
		assertTrue(StringRegexp.isNumber(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isNumber(String)} с некорректными значениями.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"", " ",
		".", ",",
		".1", ",2",
		"3,", "4.",
		"1. 1", "1, 1",
		"1. 100", "1, 100",
		"1 .100", "1 ,100",
		"1 . 100", "1 , 100",
		"1,100, 123", "1.100. 123"
	})
	public void isNumberNotCorrect(String value)
	{
		assertFalse(StringRegexp.isNumber(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isWord(String)}.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"привет", "ПРИВЕТ",
		"Hello", "WoRlD",
		"Hello-World"

	})
	public void isWord(String value)
	{
		assertTrue(StringRegexp.isWord(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isWord(String)} с некорректными значениями.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"", " ",
		"-привет", "привет-",
		" привет", "привет ",
		"Hello- World", "Hello -World", "Hello - World"
	})
	public void isWordNotCorrect(String value)
	{
		assertFalse(StringRegexp.isWord(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isLetter(String)}.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"А", "Ж", "W", "Z"
	})
	public void isLetter(String value)
	{
		assertTrue(StringRegexp.isLetter(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#isLetter(String)} с некорректными значениями.
	 */
	@ParameterizedTest
	@ValueSource(strings = {
		"", " ",
		"-", "0", "?"
	})
	public void isLetterNotCorrect(String value)
	{
		assertFalse(StringRegexp.isLetter(value));
	}

	/**
	 * Проверка метода {@link StringRegexp#findEmailAddress(String)}.
	 */
	@Test
	public void findEmailAddress()
	{
		String[] emailAddress = {
			"aleksey.kalenchukov@yandex.ru",
			"alekseyKalenchukov@yandex.ru",
			"aleksey123kalenchukov@yandex.ru",
			"123alekseykalenchukov@yandex.ru"
		};

		String value = """
			И я должен прийти к девяти, на работу свою,
			Но сейчас уже без десяти, а я только встаю aleksey.kalenchukov@yandex.ru
			На столе моем завтрак стоит, от него не уйти
			И наверное, я к девяти не смогу подойти
			Ещё только без десяти alekseyKalenchukov@yandex.ru, девять часов
			Ещё только без десяти, девять часов
			В объяснительной я напишу, что был у врача,
			А еще напишу, что часов, на пути не встречал
			(aleksey123kalenchukov@yandex.ru) И пускай все ругают меня, на работе моей
			И пускай все позорят меня, на работе моей
			Ещё только без десяти, девять часов
			Ещё только без десяти, девять часов
			Ещё только без десяти, девять часов
			Ещё только без десяти, девять часов
			Эх, 123alekseykalenchukov@yandex.ru ещё только без десяти, девять часов
			""";

		assertArrayEquals(emailAddress, StringRegexp.findEmailAddress(value).toArray());
	}

	/**
	 * Проверка метода {@link StringRegexp#findDomain(String)}.
	 */
	@Test
	public void findDomain()
	{
		String[] domain = {
			"kalenchukov.dev",
			"regexp.string.kalenchukov.dev",
			"aleksey.123.kalenchukov.ru",
			"123.aleksey.kalenchukov.ru"
		};

		String value = """
			Зерна упали в землю, зерна просят дождя.
			Им нужен дождь.
			Разрежь мою грудь, kalenchukov.dev посмотри мне внутрь,
			Ты увидишь, там все горит огнем.
			Через день будет поздно, через час будет поздно,
			Через миг будет уже не встать.
			Если к дверям не подходят ключи,regexp.string.kalenchukov.dev вышиби двери плечом.
	
			Мама, мы все тяжело больны...
			Мама, я знаю, мы все сошли с ума...

			Сталь между пальцев, сжатый кулак.
			Удар выше кисти, терзающий плоть,
			Но вместо крови в жилах застыл яд, медленный яд.
			Разрушенный мир, разбитые лбы, разломанный надвое хлеб.
			И вот кто-то плачет, а кто-то молчит,
			А кто-то так рад, кто-то так рад...
			aleksey.123.kalenchukov.ru
			Мама, мы все тяжело больны...
			Мама, я знаю, мы все сошли с ума...

			Ты должен быть сильным, ты должен уметь сказать:
			Руки прочь, прочь от меня!
			Ты должен быть сильным, иначе зачем тебе быть.
			Что будет стоить тысячи слов,
			Когда важна будет крепость руки?
			И вот ты стоишь на берегу и думаешь: "Плыть или не плыть?"

			Мама, мы все тяжело (123.aleksey.kalenchukov.ru) больны...
			Мама, я знаю, мы все сошли с ума...
			Мама, мы все тяжело больны...
			Мама, я знаю, мы все сошли с ума...
			""";

		assertArrayEquals(domain, StringRegexp.findDomain(value).toArray());
	}

	/**
	 * Проверка метода {@link StringRegexp#findUrlHttp(String)}.
	 */
	@Test
	public void findUrlHttp()
	{
		String[] domain = {
			"http://www.kalenchukov.dev/string/regexp/?java=18&isUrlHttp=test123#readme",
			"https://kalenchukov.dev/#readme",
			"https://www.kalenchukov.dev/string/regexp/",
			"http://kalenchukov.dev/?isUrlHttp=test123"
		};

		String value = """
			Тот, кто в пятнадцать лет убежал из дома
			Вряд ли поймёт http://www.kalenchukov.dev/string/regexp/?java=18&isUrlHttp=test123#readme того, кто учился в спецшколе
			Тот, у кого есть хороший жизненный план
			Вряд ли будет https://kalenchukov.dev/#readme думать о чём-то другом
			
			Мы пьём чай в старых квартирах
			Ждём лета в старых квартирах
			В старых квартирах, где есть свет
			Газ, телефон, горячая вода
			Радиоточка, пол, паркет
			Санузел раздельный, дом кирпичный
			Одна семья, две семьи, три семьи
			Много подсобных помещений
			Первый и последний не предлагать
			Рядом с метро, центр
			https://www.kalenchukov.dev/string/regexp/
			Все говорят, что мы вместе
			Все говорят, но немногие знают в каком,
			А из наших труб идёт необычный дым
			Стой, опасная зона, работа мозга
			
			Бошетунмай, бошетунмай
			Бошетунмай,http://kalenchukov.dev/?isUrlHttp=test123 бошетунмай
			Бошетунмай, бошетунмай
			Бошетунмай, бошетунмай
			Бошетунмай, бошетунмай
			""";

		assertArrayEquals(domain, StringRegexp.findUrlHttp(value).toArray());
	}

	/**
	 * Проверка метода {@link StringRegexp#findCountryCodeAlpha2(String)}.
	 */
	@Test
	public void findCountryCodeAlpha2()
	{
		String[] countryCodeAlpha2 = {
			"RU",
			"BY"
		};

		String value = """
			О-o, это странное место Камчатка
			О-o, это сладкое слово «Камчатка»
			Но на этой земле RU я не вижу тебя
			Я не вижу твоих кораблей
			Я не вижу реки, я не вижу моста
			Ну и пусть...
			О-o, это странное место Камчатка
			О-o, это сладкое слово «Камчатка»
			Я нашел здесь руду, я нашел здесь любовь
			Я пытаюсь BY забыть, забываю и вновь
			Вспоминаю собаку, она, как звезда
			Ну и пусть...
			О-o, это странное место Камчатка
			О-o, это сладкое слово «Камчатка»
			Я не вижу здесь их, я не вижу здесь нас
			Я искал здесь вино, а нашел третий глаз
			Мои руки из дуба, голова из свинца
			Ну и пусть...
			""";

		assertArrayEquals(countryCodeAlpha2, StringRegexp.findCountryCodeAlpha2(value).toArray());
	}

	/**
	 * Проверка метода {@link StringRegexp#findCountryCodeAlpha3(String)}.
	 */
	@Test
	public void findCountryCodeAlpha3()
	{
		String[] countryCodeAlpha3 = {
			"RUS",
			"BLR"
		};

		String value = """
			Каждый день ты приходишь домой, когда темно.
			Каждый день RUS долго едешь в метро, когда темно.
			А она живет в центре всех городов,
			И ты хочешь быть рядом,BLR
			Но надо ехать домой, уже темно.
			Проснись, это любовь,
			Смотри, это любовь,
			Проснись, это любовь...
			Твои родители давно уже спят, уже темно.
			Ты не спишь, ты ждешь, а вдруг зазвонит телефон.
			И ты готов отдать все за этот звонок,
			Но она давно уже спит там,
			В центре всех городов.
			Проснись, это любовь,
			Смотри, это любовь,
			Проснись, это любовь...
			""";

		assertArrayEquals(countryCodeAlpha3, StringRegexp.findCountryCodeAlpha3(value).toArray());
	}

	/**
	 * Проверка метода {@link StringRegexp#findCountryCodeNumeric3(String)}.
	 */
	@Test
	public void findCountryCodeNumeric3()
	{
		String[] countryCodeNumeric3 = {
			"052",
			"643",
			"112"
		};

		String value = """
			Стань птицей, живущей в моём небе
			Помни, что нет 052 тюрьмы страшнее, чем в голове
			Стань птицей, не думай о хлебе
			Я стану дорогой 643
			112 Я помню прозрачность воды моря
			Я вижу прозрачность горящего газа
			Стань сердцем, бейся в моём теле
			Я стану кровью
			Я буду делать всё, как умею
			Стань книгой, ложись в мои руки
			Стань песней, живи на моих губах
			Я стану словами
			""";

		assertArrayEquals(countryCodeNumeric3, StringRegexp.findCountryCodeNumeric3(value).toArray());
	}

	/**
	 * Проверка метода {@link StringRegexp#findRgbHex(String)}.
	 */
	@Test
	public void findRgbHex()
	{
		String[] rgbHex = {
			"#FFFFFF",
			"#000000",
			"#D0E9F8",
			"#1A2B3C"
		};

		String value = """
			Крыши домов дрожат под тяжестью дней
			Небесный пастух пасёт #FFFFFF облака
			Город стреляет в ночь дробью огней
			Но ночь сильней, её власть велика
			
			Тем, кто ложится спать
			Спокойного сна, спокойная ночь
			Тем, кто ложится спать, спокойного сна #000000
			Спокойная ночь
			
			Я ждал это время, и вот это время пришло
			Те, кто молчал перестали молчать
			Те, кому нечего ждать, садятся в седло
			Их не догнать, уже не догнать
			
			А тем, кто#D0E9F8 ложится спать
			Спокойного сна, спокойная ночь
			Тем, кто ложится спать, спокойного сна
			Спокойная ночь
			
			Соседи приходят, им слышится стук копыт
			Мешает уснуть, тревожит их сон
			Те, кому нечего ждать, отправляются в путь
			Те, кто спасён,#1A2B3C те, кто спасён
			
			А тем, кто ложится спать
			Спокойного сна, спокойная ночь
			Тем, кто ложится спать, спокойного сна
			Спокойная ночь
			""";

		assertArrayEquals(rgbHex, StringRegexp.findRgbHex(value).toArray());
	}

	/**
	 * Проверка метода {@link StringRegexp#findRgbHexIgnoreCase(String)}.
	 */
	@Test
	public void findRgbHexIgnoreCase()
	{
		String[] rgbHex = {
			"#fffFFF",
			"#000000",
			"#d0e9F8",
			"#1a2B3C"
		};

		String value = """
			Застоялся мой #fffFFF поезд в депо.
			Снова я уезжаю. Пора...
			На пороге ветер заждался меня.
			На пороге осень — моя сестра.#000000
			
			После красно-желтых дней начнется и кончится зима.
			Горе ты мое от ума, не печалься, гляди веселей.
			И я вернусь домой со щитом,#d0e9F8 а, может быть, на щите,
			В серебре, а, может быть, в нищете, но как можно скорей.
			
			Расскажи мне о тех, кто устал
			От безжалостных уличных драм
			И о храме из разбитых сердец
			И о тех, кто идет в этот храм.
			
			После красно-желтых дней начнется и кончится зима.
			Горе ты мое от ума, не печалься, гляди веселей.
			И я вернусь домой со щитом, а, может быть, на щите,
			В серебре, а, может быть, в нищете, но как можно скорей.
			
			А мне приснилось: миром правит любовь,
			А мне приснилось: миром правит мечта.
			И над этим прекрасно горит звезда,
			Я проснулся и понял — беда...#1a2B3C
			
			После красно-желтых дней начнется и кончится зима.
			Горе ты мое от ума, не печалься, гляди веселей.
			И я вернусь домой со щитом, а, может быть, на щите,
			В серебре, а, может быть, в нищете, но как можно скорей.
			""";

		assertArrayEquals(rgbHex, StringRegexp.findRgbHexIgnoreCase(value).toArray());
	}

	/**
	 * Проверка метода {@link StringRegexp#findMacAddress(String)}.
	 */
	@Test
	public void findMacAddress()
	{
		String[] macAddress = {
			"00-EF-CD-EF-11-22",
			"00:EF:CD:EF:11:22",
			"00:EF:CD:DF:11:22",
			"00-EF-CD-CE-11-22"
		};

		String value = """
			В этом мотиве есть какая-то фальшь,
			Но где найти 00-EF-CD-EF-11-22 тех, что услышат ее?
			Подросший ребенок, воспитанный жизнью за шкафом,
			Теперь ты видишь Солнце, возьми - 00:EF:CD:EF:11:22 это твое!
			
			Я объявляю свой дом
			Безъядерной зоной!
			Я объявляю свой двор
			Безъядерной зоной!
			Я объявляю свой город
			Безъядерной зоной!
			яю свой...00:EF:CD:DF:11:22
			
			Как не прочны стены наших квартир,
			Но кто-то один не подставит за всех плечо.
			Я вижу дом, я беру в руки мел,
			Нет замка, но я владею ключом.
			00-EF-CD-CE-11-22
			Я объявляю свой дом
			Безъядерной зоной!
			Я объявляю свой двор
			Безъядерной зоной!
			Я объявляю свой город
			Безъядерной зоной!
			Я объявляю свой...
			""";

		assertArrayEquals(macAddress, StringRegexp.findMacAddress(value).toArray());
	}

	/**
	 * Проверка метода {@link StringRegexp#findMacAddressIgnoreCase(String)}.
	 */
	@Test
	public void findMacAddressIgnoreCase()
	{
		String[] macAddress = {
			"00-EF-CD-ef-11-22",
			"00:EF:CD:EF:11:22",
			"00:EF:cd:DF:11:22",
			"00-ef-cd-ce-11-22"
		};

		String value = """
			Я вижу, как волны смывают следы на песке
			Я слышу, как ветер 00-EF-CD-ef-11-22 поет свою странную песню
			Я слышу, как струны деревьев играют ее
			Музыку волн 00:EF:CD:EF:11:22, музыку ветра
			
			Здесь трудно сказать, что такое асфальт
			Здесь трудно сказать, что такое машина
			Здесь нужно руками кидать воду вверх
			Музыка волн, музыка ветра
			00:EF:cd:DF:11:22
			Кто из вас вспомнит о тех, кто сбился с дороги?
			Кто из вас вспомнит о тех, кто смеялся и пел?
			Кто из вас вспомнит, чувствуя холод приклада
			Музыку волн, музыку ветра 00-ef-cd-ce-11-22?
			Музыку волн, музыку ветра?
			""";

		assertArrayEquals(macAddress, StringRegexp.findMacAddressIgnoreCase(value).toArray());
	}

	/**
	 * Проверка метода {@link StringRegexp#findInet4Address(String)}.
	 */
	@Test
	public void findInet4Address()
	{
		String[] inet4Address = {
			"192.168.1.1",
			"1.1.1.1",
			"0.0.0.0",
			"10.222.170.80"
		};

		String value = """
			День как день, только ты почему-то грустишь.
			И вокруг все поют, только ты один молчишь.
			Потерял аппетит и не 192.168.1.1 хочешь сходить в кино.
			Ты идешь в магазин, чтобы купить вино.
			
			Солнце светит и растет трава,
			Но тебе она не нужна.
			Все не так и все не то,
			Когда твоя девушка больна 1.1.1.1
			Когда твоя девушка больна
			Когда больна.
			
			Ты идешь в магазин, головою поник,
			Как будто иссяк чистый горный родник.
			Она где-то лежит,0.0.0.0 ест мед и пьет аспирин,
			И вот ты идешь на вечеринку один.
			
			Солнце светит и растет трава,
			Но тебе она не нужна.
			Все не так и все не то,
			Когда твоя девушка больна.
			На вечеринку -10.222.170.80 один.
			Когда твоя девушка больна.
			На вечеринку - один.
			Когда твоя девушка больна.
			
			Когда твоя девушка больна.
			Когда твоя девушка больна.
			""";

		assertArrayEquals(inet4Address, StringRegexp.findInet4Address(value).toArray());
	}

	/**
	 * Проверка метода {@link StringRegexp#findRgbNumeric(String)}.
	 */
	@Test
	public void findRgbNumeric()
	{
		String[] rgb = {
			"255,150,50",
			"0, 0, 0",
			"50, 50,50",
			"113,13,3"
		};

		String value = """
			Наши реки бедны водой
			В наших окнах 255,150,50 не видно дня
			Наше утро похоже на ночь
			Ну, а ночь — для меня
			Глядя в жидкое зеркало луж 0, 0, 0
			На часы, что полвека стоят
			На до дыр зацелованный флаг
			Я полцарства отдам за коня
			
			Играй!50, 50,50
			Невесёлая песня моя
			Играй!
			Играй!
			
			Командиры Армии Лет
			Мы теряли в бою день за днём
			А когда мы разжигали огонь
			Наш огонь тушили дождём
			Мы сидим у разбитых корыт
			И гадаем на розе ветров
			А когда приходит время вставать
			Мы сидим, 113,13,3 мы ждём
			
			Играй!
			Невесёлая песня моя
			Играй!
			Играй!
			Играй!
			Невесёлая песня моя
			Играй!
			Играй!
			""";

		assertArrayEquals(rgb, StringRegexp.findRgbNumeric(value).toArray());
	}

	/**
	 * Проверка метода {@link StringRegexp#findTelegram(String)}.
	 */
	@Test
	public void findTelegram()
	{
		String[] telegram = {
			"@kalenchukov",
			"@Kalenchukov",
			"@KALENCHUKOV",
			"@kalen_CHUKOV"
		};

		String value = """
			Ты часто проходишь мимо, не видя меня
			С кем-то другим, я стою не дыша @kalenchukov
			Я знаю, что ты живешь в соседнем дворе
			Ты идешь не спеша,@Kalenchukov не спеша...
			
			Ооооу, но это не любовь...
			Ооооу, но это не любовь...
			
			А вечером я стою под твоим окном
			Ты поливаешь цветы, поливаешь цветы
			А я дотемна стою и сгораю огнем
			И виной тому ты, только ты...@KALENCHUKOV
			
			Ооооу, но это не любовь...
			Ооооу, но это не любовь...
			
			Научи меня всему тому, что умеешь ты
			Я хочу это знать и уметь
			Сделай так, чтобы сбылись все мои мечты
			Мне нельзя больше ждать, я могу умереть...
			@kalen_CHUKOV
			Ооооу, но это не любовь...
			Ооооу, но это не любовь...
			""";

		assertArrayEquals(telegram, StringRegexp.findTelegram(value).toArray());
	}

	/**
	 * Проверка метода {@link StringRegexp#findInet6Address(String)}.
	 */
	@Test
	public void findInet6Address()
	{
		String[] inet6Address = {
			"2001:0DB8:11A3:09D7:1F34:8A2E:07A0:765D",
			"AD12::AE21::",
			"::AE21:AD12",
			"2001:DB8::AE21:AD12"
		};

		String value = """
			В последнее время я редко был дома,
			Так что даже отвыкли звонить мне друзья.
			В разъездах, разгулах 2001:0DB8:11A3:09D7:1F34:8A2E:07A0:765D конца лета симптомы
			Совсем перестали вдруг мучить меня.
			
			И я подумал что Осень - это тоже не плохо,
			И что Осенью слякоть и сер первый снег, AD12::AE21::
			И что холод ветров я буду чувствовать боком,
			Опьяненный сознаньем того, что я человек.
			
			И этой Осенью много дней чьих-то рождений
			И уж я постараюсь на них побывать,
			::AE21:AD12 А потом, игнорируя лужи и слякоть,
			Я приду домой пьяный и мешком повалюсь на кровать.
			
			И утром рано я встану и отправлюсь учиться,
			И с похмелья я буду смеяться над всем.
			Скоро будет Зима, чтоб в Весне раствориться,
			А потом будет Лето - неизвестно зачем.
			
			И я начал за здравие, а кончу я плохо,
			Написав наш порядковый номер - 600.
			С чьих-то старых столов подбираю я крохи,
			И не в силах сказать,2001:DB8::AE21:AD12 что принес этот год.
			""";

		assertArrayEquals(inet6Address, StringRegexp.findInet6Address(value).toArray());
	}

	/**
	 * Проверка метода {@link StringRegexp#findInet6AddressIgnoreCase(String)}.
	 */
	@Test
	public void findInet6AddressIgnoreCase()
	{
		String[] inet6Address = {
			"2001:0db8:11a3:09d7:1F34:8A2E:07A0:765D",
			"ad12::AE21::",
			"::ae21:AD12",
			"2001:db8::AE21:AD12"
		};

		String value = """
			За окнами солнце, за окнами свет - это день.
			Ну, а я всегда 2001:0db8:11a3:09d7:1F34:8A2E:07A0:765D любил ночь.
			И это мое дело - любить ночь,
			И это мое право - ad12::AE21:: уйти в тень.
			
			Я люблю ночь за то, что в ней меньше машин,
			Я люблю дым и пепел своих папирос,
			Я люблю кухни за то, что они хранят тайны,
			Я люблю свой дом, но вряд ли это всерьез.
			
			И эта ночь и ее электрический свет бьет мне в глаза,
			И эта ночь и ее электрический дождь бьет мне в окно,
			И эта ночь и ее электрический голос манит меня к себе,
			И я не знаю, как мне прожить следующий день.
			
			Я один, но это не значит, что я одинок, ::ae21:AD12
			Мой магнитофон хрипит о радостях дня,
			Я помню, что завтра меня ждет несколько встреч,
			И кофе в известном кафе согреет меня.
			
			И эта ночь и ее электрический свет бьет мне в глаза,
			И эта ночь и ее электрический дождь бьет мне в окно,
			И эта ночь и ее электрический голос манит меня к себе,
			И я не знаю, как мне прожить следующий день.
			
			И эта ночь и ее электрический свет бьет мне в глаза,
			И эта ночь и ее электрический дождь бьет мне в окно,
			И эта ночь и ее электрический голос манит меня к себе,
			И я не знаю,2001:db8::AE21:AD12 как мне прожить следующий день.
			""";

		assertArrayEquals(inet6Address, StringRegexp.findInet6AddressIgnoreCase(value).toArray());
	}

	/**
	 * Проверка метода {@link StringRegexp#findTag(String)}.
	 */
	@Test
	public void findTag()
	{
		String[] tag = {
			"#tag",
			"#Tag",
			"#TAG",
			"#ta_g"
		};

		String value = """
			Гуляю. Я один гуляю.
			Что дальше делать, #tag я не знаю.
			Нет дома. Никого нет дома #Tag.
			Я лишний, словно куча лома, у-у.

			Припев:
			Я бездельник, о-о, мама, мама, я бездельник, у-у...
			Я бездельник, о-о, мама, мама.

			В толпе я как иголка в сене.
			Я снова человек без цели.#TAG
			Болтаюсь, целый день гуляю.
			Не знаю, я ничего не знаю, у-у.

			Припев:#ta_g
			Я бездельник, о-о, мама, мама, я бездельник, у-у...
			Я бездельник, о-о, мама, мама.
			   
			у-у... я бездельник, о-о, мама, мама.
			Я бездельник, у-у...
			Я бездельник, о-о, мама, мама, я бездельник, у-у...
			Я бездельник, у-у...
			""";

		assertArrayEquals(tag, StringRegexp.findTag(value).toArray());
	}

	/**
	 * Проверка метода {@link StringRegexp#findDigitBinary(String)}.
	 */
	@Test
	public void findDigitBinary()
	{
		String[] digitBinary = {
			"010101",
			"1101",
			"10101",
			"01010101"
		};

		String value = """
			Мы хотим видеть 010101 дальше, чем окна дома напротив
			Мы хотим жить, мы живучи, как кошки
			И вот мы пришли заявить о своих правах,1101 да
			Слышишь шелест плащей? Это мы
			
			Дальше действовать будем мы
			Дальше действовать будем мы
			Дальше действовать будем мы
			Дальше действовать будем мы
			10101
			Мы родились в тесных квартирах новых районов
			Мы потеряли невинность в боях за любовь
			Нам уже стали тесны одежды,
			Сшитые вами для нас одежды
			И вот мы пришли сказать вам о том, что дальше...
			
			Дальше действовать будем мы
			Дальше действовать будем мы
			Дальше действовать будем мы 01010101
			Дальше действовать будем мы
			
			Дальше действовать будем мы
			Дальше действовать будем мы
			Дальше действовать будем мы
			Дальше действовать будем мы
			""";

		assertArrayEquals(digitBinary, StringRegexp.findDigitBinary(value).toArray());
	}

	/**
	 * Проверка метода {@link StringRegexp#findDigitTernary(String)}.
	 */
	@Test
	public void findDigitTernary()
	{
		String[] digitTernary = {
			"0101201",
			"11021",
			"102101",
			"010210101"
		};

		String value = """
			Струн провода, ток по рукам,
			Телефон на все 0101201 голоса говорит: «Пока!» Пора...
			И пальто на гвозде, шарф в рукаве
			И перчатки в карманах шепчут:
			«Подожди до утра!» До утра...11021
			
			Но странный стук зовёт: «В дорогу!»
			Может сердца, а может стук в дверь.
			И, когда я обернусь на пороге,
			Я скажу одно лишь слово:102101 «Верь!»
			
			И опять на вокзал, и опять к поездам,
			И опять проводник выдаст бельё и чай,
			И опять не усну, и опять сквозь грохот колёс
			Мне послышится слово: «Прощай!»
			
			Но странный стук зовёт: «В дорогу!»
			Может сердца, а может стук в дверь.
			И,010210101 когда я обернусь на пороге,
			Я скажу одно лишь слово: «Верь!»
			
			Но странный стук зовёт: «В дорогу!»
			Может сердца, а может стук в дверь.
			И, когда я обернусь на пороге,
			Я скажу одно лишь слово: «Верь!»
			""";

		assertArrayEquals(digitTernary, StringRegexp.findDigitTernary(value).toArray());
	}

	/**
	 * Проверка метода {@link StringRegexp#findDigitOctal(String)}.
	 */
	@Test
	public void findDigitOctal()
	{
		String[] digitOctal = {
			"010317201",
			"1160215",
			"170241031",
			"01021034567101"
		};

		String value = """
			Над землёй мороз, что ни тронь - всё лёд.
			Лишь во сне моём,010317201 поёт капель.
			А снег идёт стеной, а снег идёт весь день,
			А за той стеной - стоит Апрель.1160215
			
			А он придёт и приведёт за собой Весну,
			И рассеет серых туч войска,
			А когда мы все посмотрим в глаза его,
			На нас из глаз его посмотрит тоска.
			И откроются двери домов,
			Да ты садись, а то в ногах правды нет.
			И когда мы все посмотрим в глаза его,
			То увидим в тех глазах солнца свет.
			170241031
			На теле ран не счесть, не легки шаги,
			Лишь в груди горит звезда.
			И умрёт Апрель, и родится вновь,
			И придёт уже навсегда.
			
			А он придёт и приведёт за собой Весну,
			И рассеет серых туч войска.01021034567101
			А когда мы все посмотрим в глаза его,
			На нас из глаз его посмотрит тоска.
			И откроются двери домов,
			Да ты садись, а то в ногах правды нет,
			И когда мы все посмотрим в глаза его,
			То увидим в тех глазах солнца свет.
			""";

		assertArrayEquals(digitOctal, StringRegexp.findDigitOctal(value).toArray());
	}

	/**
	 * Проверка метода {@link StringRegexp#findDigitDuodecimal(String)}.
	 */
	@Test
	public void findDigitDuodecimal()
	{
		String[] digitDuodecimal = {
			"01031A9b7201",
			"11602B15",
			"17024A1031",
			"B010A210394567101"
		};

		String value = """
			Крыши домов дрожат под тяжестью дней
			Небесный пастух пасёт облака 01031A9b7201
			Город стреляет в ночь дробью огней
			Но ночь сильней,11602B15 её власть велика
			
			Тем, кто ложится спать
			Спокойного сна, спокойная ночь
			Тем, кто ложится спать, спокойного сна
			Спокойная ночь
			17024A1031
			Я ждал это время, и вот это время пришло
			Те, кто молчал перестали молчать
			Те, кому нечего ждать, садятся в седло
			Их не догнать, уже не догнать
			
			А тем, кто ложится спать
			Спокойного сна, спокойная ночь
			Тем, кто ложится спать, спокойного сна
			Спокойная ночь
			
			Соседи приходят,B010A210394567101 им слышится стук копыт
			Мешает уснуть, тревожит их сон
			Те, кому нечего ждать, отправляются в путь
			Те, кто спасён, те, кто спасён
			
			А тем, кто ложится спать
			Спокойного сна, спокойная ночь
			Тем, кто ложится спать, спокойного сна
			Спокойная ночь
			""";

		assertArrayEquals(digitDuodecimal, StringRegexp.findDigitDuodecimal(value).toArray());
	}

	/**
	 * Проверка метода {@link StringRegexp#findDigitHexadecimal(String)}.
	 */
	@Test
	public void findDigitHexadecimal()
	{
		String[] digitHexadecimal = {
			"0103f1A9b720d1",
			"1160de2B15",
			"17024cA103d1",
			"B010A210cde3945f67101"
		};

		String value = """
			На холодной земле стоит город большой.
			Там горят фонари, и машины гудят.
			А над городом ночь, 0103f1A9b720d1 а над ночью луна,
			И сегодня луна каплей крови красна.1160de2B15
			
			Дом стоит, свет горит, из окна видна даль.
			Так откуда взялась печаль?
			И вроде, жив и здоров, и вроде жить, не тужить.
			Так откуда взялась печаль?
			
			А вокруг благодать - 17024cA103d1 ни черта не видать,
			А вокруг красота - не видать ни черта.
			И все кричат: "Ура!" и все бегут вперед,
			И над этим всем, новый день встает.
			
			Дом стоит, B010A210cde3945f67101 свет горит, из окна видна даль.
			Так откуда взялась, печаль?
			И вроде, жив и здоров, и вроде жить, не тужить.
			Так откуда взялась, печаль?
			
			Дом стоит, свет горит, из окна видна даль.
			Так откуда взялась, печаль?
			И вроде, жив и здоров, и вроде жить, не тужить.
			Так откуда взялась, печаль?
			""";

		assertArrayEquals(digitHexadecimal, StringRegexp.findDigitHexadecimal(value).toArray());
	}

	/**
	 * Проверка метода {@link StringRegexp#findLocalization(String)}.
	 */
	@Test
	public void findLocalization()
	{
		String[] localization = {
			"ru-RU",
			"en-EN",
			"by-BY",
			"it-IT"
		};

		String value = """
			Застоялся мой поезд в депо.
			Снова я уезжаю. Пора...
			На пороге ветер заждался меня.
			На пороге осень — моя сестра.ru-RU
			
			После красно-желтых дней начнется и кончится зима.
			Горе ты мое от ума, не печалься, гляди веселей.
			И я вернусь домой со щитом, а, может быть, на щите,
			В серебре, а, может быть, в нищете, но как можно скорей.
			
			Расскажи мне о тех, кто устал
			От безжалостных уличных драм
			И о храме из разбитых сердец
			И о тех, кто идет в этот храм.
			
			После красно-желтых дней начнется и кончится зима.
			Горе ты мое от ума,en-EN не печалься, гляди веселей.
			И я вернусь домой со щитом, а, может быть, на щите,
			В серебре, а, by-BY может быть, в нищете, но как можно скорей.
			
			А мне приснилось: миром правит любовь,
			А мне приснилось:it-IT миром правит мечта.
			И над этим прекрасно горит звезда,
			Я проснулся и понял — беда...
			
			После красно-желтых дней начнется и кончится зима.
			Горе ты мое от ума, не печалься, гляди веселей.
			И я вернусь домой со щитом, а, может быть, на щите,
			В серебре, а, может быть, в нищете, но как можно скорей.
			""";

		assertArrayEquals(localization, StringRegexp.findLocalization(value).toArray());
	}

	/**
	 * Проверка метода {@link StringRegexp#findDigitDecimal(String)}.
	 */
	@Test
	public void findDigitDecimal()
	{
		String[] digitDecimal = {
			"0",
			"12",
			"123",
			"0123456789"
		};

		String value = """
			Ты часто проходишь 0 мимо, не видя меня,
			С кем-то другим, я стою не дыша.
			Я знаю, что ты живешь в соседнем дворе,12
			Ты идешь не спеша, не спеша...
			
			О, но это не любовь...
			
			А вечером я стою под твоим окном,
			Ты поливаешь цветы, поливаешь цветы.
			А я дотемна стою и сгораю огнем,
			123И виной тому ты, только ты...
			
			О, но это не любовь...
			
			Научи меня всему тому, что умеешь ты,
			Я хочу это знать и уметь.
			Сделай так, чтобы сбылись все мои мечты,
			Мне нельзя больше ждать, я могу умереть...
			
			О, но это не любовь...0123456789
			""";

		assertArrayEquals(digitDecimal, StringRegexp.findDigitDecimal(value).toArray());
	}

	/**
	 * Проверка метода {@link StringRegexp#findNumber(String)}.
	 */
	@Test
	public void findNumber()
	{
		String[] number = {
			"1.100",
			"0,1",
			"1,000,00",
			"1.222,123"
		};

		String value = """
			Ночь коротка, цель далека;
			Ночью так часто 1.100 хочется пить,
			Ты выходишь на кухню, но вода здесь горька;
			Ты не можешь здесь спать, 0,1 ты не хочешь здесь жить.
			
			Доброе утро, последний герой!
			Доброе утро, — тебе, и таким, как ты!
			Доброе утро, последний герой!
			Здравствуй, последний герой!
			
			Ты хотел быть один — это быстро прошло,
			Ты хотел быть один, но не смог быть один.
			Твоя ноша легка, но немеет рука;
			И ты встречаешь рассвет за игрой в «Дурака».
			
			Доброе утро, последний герой!
			Доброе утро, —1,000,00 тебе, и таким, как ты!
			Доброе утро, последний герой!
			Здравствуй, последний герой!
			
			Утром ты стремишься скорее уйти,
			Телефонный звонок, как команда «Вперёд!»
			Ты уходишь туда, куда не хочешь идти;
			Ты уходишь туда,1.222,123 но тебя там никто не ждёт!
			
			Доброе утро, последний герой!
			Доброе утро, — тебе, и таким, как ты!
			Доброе утро, последний герой!
			Здравствуй, последний герой!
			""";

		assertArrayEquals(number, StringRegexp.findNumber(value).toArray());
	}

	/**
	 * Проверка метода {@link StringRegexp#findWord(String)}.
	 */
	@Test
	public void findWord()
	{
		String[] word = {
			"Красно-жёлтые",
			"дни",
			"Застоялся",
			"мой",
			"поезд",
			"в",
			"депо",
			"Снова",
			"я",
			"уезжаю",
			"Пора",
			"На",
			"пороге",
			"ветер",
			"заждался",
			"меня",
			"На",
			"пороге",
			"осень",
			"моя",
			"сестра"
		};

		String value = """
			Красно-жёлтые дни.
						
			Застоялся мой поезд в депо.
			Снова я уезжаю. Пора...
			На пороге ветер заждался меня.
			На пороге осень — моя сестра.
			""";

		assertArrayEquals(word, StringRegexp.findWord(value).toArray());
	}

	/**
	 * Проверка метода {@link StringRegexp#findLetter(String)}.
	 */
	@Test
	public void findLetter()
	{
		String[] letter = {
			"М",
			"а",
			"л",
			"ы",
			"ш"
		};

		String value = """
			Малыш
			""";

		assertArrayEquals(letter, StringRegexp.findLetter(value).toArray());
	}
}