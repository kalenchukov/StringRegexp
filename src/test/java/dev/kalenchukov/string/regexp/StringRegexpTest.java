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

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Класс проверки методов класса {@link StringRegexp}.
 *
 * @author Алексей Каленчуков
 */
public class StringRegexpTest
{
	/**
	 * Класс проверки статических методов.
	 *
	 * @author Алексей Каленчуков
	 */
	@Nested
	public class Static
	{
		/**
		 * Класс проверки метода {@link StringRegexp#isLocalization(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class IsLocalization
		{
			/**
			 * Проверка метода {@link StringRegexp#isLocalization(String)} с корректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"ru-RU"
			})
			public void isLocalizationWithValueValid(String value)
			{
				boolean actual = StringRegexp.isLocalization(value);

				assertThat(actual).isTrue();
			}

			/**
			 * Проверка метода {@link StringRegexp#isLocalization(String)} с некорректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"", " ", "RU-RU", "RU-ru", "ru-ru", "ru", "RU"
			})
			public void isLocalizationWithValueInvalid(String value)
			{
				boolean actual = StringRegexp.isLocalization(value);

				assertThat(actual).isFalse();
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#isInet4Address(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class IsInet4Address
		{
			/**
			 * Проверка метода {@link StringRegexp#isInet4Address(String)} с корректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"192.168.1.1", "1.1.1.1", "0.0.0.0"
			})
			public void isInet4AddressWithValueValid(String value)
			{
				boolean actual = StringRegexp.isInet4Address(value);

				assertThat(actual).isTrue();
			}

			/**
			 * Проверка метода {@link StringRegexp#isInet4Address(String)} с некорректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"",
				" ",
				"1.1.1.1.",
				".6.6.6.6",
				"2.2.2.2.2",
				"3",
				"4.",
				"5.5",
				"257.168.1.1",
				"192.257.1.1",
				"192.168.257.1",
				"192.168.1.256",
			})
			public void isInet4AddressWithValueInvalid(String value)
			{
				boolean actual = StringRegexp.isInet4Address(value);

				assertThat(actual).isFalse();
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#isInet6Address(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class IsInet6Address
		{
			/**
			 * Проверка метода {@link StringRegexp#isInet6Address(String)} с корректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"2001:0DB8:11A3:09D7:1F34:8A2E:07A0:765D"
			})
			public void isInet6AddressWithValueValid(String value)
			{
				boolean actual = StringRegexp.isInet6Address(value);

				assertThat(actual).isTrue();
			}

			/**
			 * Проверка метода {@link StringRegexp#isInet6Address(String)} с некорректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"2001:0db8:11a3:09d7:1F34:8A2E:07A0:765D"
			})
			public void isInet6AddressWithValueInvalid(String value)
			{
				boolean actual = StringRegexp.isInet6Address(value);

				assertThat(actual).isFalse();
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#isInet6AddressIgnoreCase(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class IsInet6AddressIgnoreCase
		{
			/**
			 * Проверка метода {@link StringRegexp#isInet6AddressIgnoreCase(String)} с корректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"2001:0db8:11a3:09d7:1F34:8A2E:07A0:765D",
				"2001:0db8:0000:0000:0000:0000:ae21:AD12",
				"0000:0000:0000:0000:0000:0000:ae21:AD12",
				"2001:0DB8:0000:0000:0000:0000:ae21:1",
				"2001:0DB8:0000:1:0000:0000:ae21:AD12",
				"1:0000:0000:0000:0000:0000:ae21:AD12",
				"::0DB8:11A3:09D7:1F34:8A2E:07a0:765D",
				"0DB8:11A3:09D7:1F34::07a0:765D",
				"2001:0DB8:11A3:09D7:1F34:07a0:07A0::",
				"::ae21:AD12",
				"AD12::ae21::",
				"2001::",
				"::2001",
				"::",
				"::1",
				"1::",
				"EF98:3:0:0:0:0:2F3B:7654",
				"EF98:3::2f3b:7654",
				"2001:DB8::ae21:AD12",
			})
			public void isInet6AddressIgnoreCaseWithValueValid(String value)
			{
				boolean actual = StringRegexp.isInet6AddressIgnoreCase(value);

				assertThat(actual).isTrue();
			}

			/**
			 * Проверка метода {@link StringRegexp#isInet6AddressIgnoreCase(String)} с некорректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"",
				" ",
				"1000",
				"1000:",
				":1000",
				":",
				":::",
				"::::",
				"2001:0db8:11a3:09d7:1F34:8A2E:07A0:765D:765D",
				":0db8:11a3:09d7:1f34:8A2E:07A0:765D",
				"2001:0db8:11a3:09d7:1F34:8A2E:07A0:",
				":::0db8:11a3:09d7:1f34:8A2E:07A0:765D",
				"2001:0db8:11a3:09d7:1F34:8A2E:07A0:::",
				"::::0db8:11a3:09d7:1F34:8A2E:07A0:765D",
				"0db8:11a3:09d7::::1f34:8A2E:07A0:765D",
				"2001:0db8:11a3:09d7:1F34:8A2E:07A0::::",
				":0db8:11a3:09d7:1f34:8A2E:07A0:765D:1000",
				"1000:2001:0db8:11a3:09D7:1F34:8A2E:07A0:",
				":0db8:11a3:09d7:1f34:8A2E:07A0:765D:1",
				"1:2001:0db8:11a3:09d7:1f34:8A2E:07A0:",
				"::0db8:11a3:09d7:1f34:8A2E:07A0:765D:1",
				"1:2001:0db8:11a3:09d7:1f34:8A2E:07A0::",
				":::0db8:11a3:09d7:1f34:8a2e:07A0:765D:1",
				"1:2001:0db8:11a3:09d7:1F34:8A2E:07A0:::",
				"0db8:11a3::::8A2E:07A0:765D",
				"0db8:11a3::::::8A2E:07A0:765D"
			})
			public void isInet6AddressIgnoreCaseWithValueInvalid(String value)
			{
				boolean actual = StringRegexp.isInet6AddressIgnoreCase(value);

				assertThat(actual).isFalse();
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#isEmailAddress(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class IsEmailAddress
		{
			/**
			 * Проверка метода {@link StringRegexp#isEmailAddress(String)} с корректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"aleksey.kalenchukov@yandex.ru",
				"aleksey.kalenchukov_@yandex.ru",
				"_aleksey.kalenchukov_@yandex.ru",
				"-aleksey.kalenchukov-@yandex.ru",
				"aleksey.kalenchukov@mail.yandex.ru",
				"alekseykalenchukov@yandex.ru",
				"aleksey.kalenchukov@yandex.com",
				"aleksey-kalenchukov@yandex.ru",
				"aleksey_kalenchukov@yandex.ru",
				"AlekseyKalenchukov@yandex.ru",
				"АлексейКаленчуков@яндекс.ру",
				"a.k@yandex.ru",
				"k@yandex.ru",
				"kalenchukov@yandex.ru",
				"kalenchukov@y.ru",
				"aleksey.kalenchukovkalenchukovkalenchukovkalenchukovkalenchukov@yandex.ru",
				"aleksey123.kalenchukov123@123yandex.ru",
				"aleksey.kalenchukov@123.yandex.ru",
				"123aleksey.kalenchukov@123.yandex.ru",
				"123.456@123.ru",
				"123456@123.ru"
			})
			public void isEmailAddressWithValueValid(String value)
			{
				boolean actual = StringRegexp.isEmailAddress(value);

				assertThat(actual).isTrue();
			}

			/**
			 * Проверка метода {@link StringRegexp#isEmailAddress(String)} с некорректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"",
				" ",
				".aleksey.kalenchukov@yandex.ru",
				"aleksey.kalenchukov.@yandex.ru",
				"aleksey..kalenchukov@yandex.ru",
				"aleksey.kalenchukov@yandex",
				"aleksey.kalenchukov@yandex..ru",
				"aleksey.kalenchukov.yandex.ru",
				"aleksey.kalenchukov@yandex.r",
				"aleksey.kalenchukov@-yandex.ru",
				"aleksey.kalenchukovkalenchukovkalenchukovkalenchukovkalenchukovkalenchukov@yandex.ru",
			})
			public void isEmailAddressWithValueInvalid(String value)
			{
				boolean actual = StringRegexp.isEmailAddress(value);

				assertThat(actual).isFalse();
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#isDomain(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class IsDomain
		{
			/**
			 * Проверка метода {@link StringRegexp#isDomain(String)} с корректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"kalenchukov.dev",
				"www.kalenchukov.dev",
				"www.mywww.dev",
				"www.my-www.dev",
				"regexp.string.kalenchukov.dev",
				"aleksey.123.kalenchukov.ru",
				"123.aleksey.kalenchukov.ru"
			})
			public void isDomainWithValueValid(String value)
			{
				boolean actual = StringRegexp.isDomain(value);

				assertThat(actual).isTrue();
			}

			/**
			 * Проверка метода {@link StringRegexp#isDomain(String)} с некорректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"", " ", "kalenchukov,dev", "regexp.string.kalenchukov.d",
				"-kalenchukov.dev", ".kalenchukov.dev",
				"kalenchukov.", "kalenchukov",
				"www.kalenchukov"
			})
			public void isDomainWithValueInvalid(String value)
			{
				boolean actual = StringRegexp.isDomain(value);

				assertThat(actual).isFalse();
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#isUrlHttp(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class IsUrlHttp
		{
			/**
			 * Проверка метода {@link StringRegexp#isUrlHttp(String)} с корректными значениями.
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
				"http://www.kalenchukov.www.dev",
				"http://www.kalenchukov-www.dev",
				"http://www.kalenchukov.dev/hello/world",
				"http://www.kalenchukov.dev/?java=18&hello=world123#anchor",
				"http://www.kalenchukov.dev/#anchor"
			})
			public void isUrlHttpWithValueValid(String value)
			{
				boolean actual = StringRegexp.isUrlHttp(value);

				assertThat(actual).isTrue();
			}

			/**
			 * Проверка метода {@link StringRegexp#isUrlHttp(String)} с некорректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"http://kalenchukov.d",
				"https://kalenchukov.",
				"http://www.kalenchukov",
				"://www.kalenchukov.dev",
				"http//www.kalenchukov.dev",
				"http://www..dev",
				"http://www.dev",
				"http:/www.kalenchukov.dev"
			})
			public void isUrlHttpWithValueInvalid(String value)
			{
				boolean actual = StringRegexp.isUrlHttp(value);

				assertThat(actual).isFalse();
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#isRgbNumeric(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class IsRgbNumeric
		{
			/**
			 * Проверка метода {@link StringRegexp#isRgbNumeric(String)} с корректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"215,200,166", "0,0,0", "215, 200, 166", "0, 0, 0", "255,155, 55", "1,1,1"
			})
			public void isRgbNumericWithValueValid(String value)
			{
				boolean actual = StringRegexp.isRgbNumeric(value);

				assertThat(actual).isTrue();
			}

			/**
			 * Проверка метода {@link StringRegexp#isRgbNumeric(String)} с некорректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"",
				" ",
				"0,0",
				"0",
				"215,  200, 166",
				"215, 200,  166",
				"-1, 0, 0",
				"0, -1, 0",
				"0, 0, -1",
				"215,200,",
				"1,1,1,",
				"256,155,55",
				"255,256,55",
				"255,155,256"
			})
			public void isRgbNumericWithValueInvalid(String value)
			{
				boolean actual = StringRegexp.isRgbNumeric(value);

				assertThat(actual).isFalse();
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#isCountryCodeAlpha2(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class IsCountryCodeAlpha2
		{
			/**
			 * Проверка метода {@link StringRegexp#isCountryCodeAlpha2(String)} с корректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"RU", "BY"
			})
			public void isCountryCodeAlpha2WithValueValid(String value)
			{
				boolean actual = StringRegexp.isCountryCodeAlpha2(value);

				assertThat(actual).isTrue();
			}

			/**
			 * Проверка метода {@link StringRegexp#isCountryCodeAlpha2(String)} с некорректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"", " ", "ru", "rub", "#RU", "0RU"
			})
			public void isCountryCodeAlpha2WithValueInvalid(String value)
			{
				boolean actual = StringRegexp.isCountryCodeAlpha2(value);

				assertThat(actual).isFalse();
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#isCountryCodeAlpha3(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class IsCountryCodeAlpha3
		{
			/**
			 * Проверка метода {@link StringRegexp#isCountryCodeAlpha3(String)} с корректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"RUS", "BLR"
			})
			public void isCountryCodeAlpha3WithValueValid(String value)
			{
				boolean actual = StringRegexp.isCountryCodeAlpha3(value);

				assertThat(actual).isTrue();
			}

			/**
			 * Проверка метода {@link StringRegexp#isCountryCodeAlpha3(String)} с некорректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"", " ", "rus", "rub", "#RUS", "0RUS"
			})
			public void isCountryCodeAlpha3WithValueInvalid(String value)
			{
				boolean actual = StringRegexp.isCountryCodeAlpha3(value);

				assertThat(actual).isFalse();
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#isCountryCodeNumeric3(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class IsCountryCodeNumeric3
		{
			/**
			 * Проверка метода {@link StringRegexp#isCountryCodeNumeric3(String)} с корректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"052", "112", "643"
			})
			public void isCountryCodeNumeric3WithValueValid(String value)
			{
				boolean actual = StringRegexp.isCountryCodeNumeric3(value);

				assertThat(actual).isTrue();
			}

			/**
			 * Проверка метода {@link StringRegexp#isCountryCodeNumeric3(String)} с некорректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"", " ", "52", "0052", "#643"
			})
			public void isCountryCodeNumeric3WithValueInvalid(String value)
			{
				boolean actual = StringRegexp.isCountryCodeNumeric3(value);

				assertThat(actual).isFalse();
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#isUuid(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class IsUuid
		{
			/**
			 * Проверка метода {@link StringRegexp#isUuid(String)} с корректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"01234567-89ab-cdef-ABCD-EF0123456789",
				"01234567-89AB-CDEF-ABCD-EF0123456789",
				"01234567-89ab-cdef-abcd-ef0123456789"
			})
			public void isUuidWithValueValid(String value)
			{
				boolean actual = StringRegexp.isUuid(value);

				assertThat(actual).isTrue();
			}

			/**
			 * Проверка метода {@link StringRegexp#isUuid(String)} с некорректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"",
				" ",
				"0123456-89ab-cdef-ABCD-EF0123456789",
				"012345678-89AB-CDEF-ABCD-EF0123456789",
				"0123.567-89ab-cdef-abcd-ef0123456789",
				"01234567_89ab-cdef-ABCD-EF0123456789"
			})
			public void isUuidWithWithValueInvalid(String value)
			{
				boolean actual = StringRegexp.isUuid(value);

				assertThat(actual).isFalse();
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#isMd5(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class IsMd5
		{
			/**
			 * Проверка метода {@link StringRegexp#isMd5(String)} с корректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"1BC29B36F623BA82AAF6724FD3B16718", "D41D8CD98F00B204E9800998ECF8427E"
			})
			public void isMd5WithValueValid(String value)
			{
				boolean actual = StringRegexp.isMd5(value);

				assertThat(actual).isTrue();
			}

			/**
			 * Проверка метода {@link StringRegexp#isMd5(String)} с некорректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"",
				" ",
				"1BC29B36F623BA82AAF6724FD3B1671",
				"1bc29b36f623ba82aaf6724fd3b16718",
				"1bc29B36F623BA82AAF6724FD3B16718"
			})
			public void isMd5WithValueInvalid(String value)
			{
				boolean actual = StringRegexp.isMd5(value);

				assertThat(actual).isFalse();
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#isMd5IgnoreCase(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class IsMd5IgnoreCase
		{
			/**
			 * Проверка метода {@link StringRegexp#isMd5IgnoreCase(String)} с корректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"1BC29B36F623BA82AAF6724FD3B16718",
				"1bc29b36f623ba82aaf6724fd3b16718",
				"d41d8cd98f00b204E9800998ECF8427E"
			})
			public void isMd5IgnoreCaseWithValueValid(String value)
			{
				boolean actual = StringRegexp.isMd5IgnoreCase(value);

				assertThat(actual).isTrue();
			}

			/**
			 * Проверка метода {@link StringRegexp#isMd5IgnoreCase(String)} с некорректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"",
				" ",
				"1BC29B36F623BA82AAF6724FD3B1671",
				"1bc29b36f623ba82aaf6724fd3b1671",
				"1bc29b36f623ba82af6724fd3b16718"
			})
			public void isMd5IgnoreCaseWithValueInvalid(String value)
			{
				boolean actual = StringRegexp.isMd5IgnoreCase(value);

				assertThat(actual).isFalse();
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#isRgbHex(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class IsRgbHex
		{
			/**
			 * Проверка метода {@link StringRegexp#isRgbHex(String)} с корректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"#FFFFFF", "#ABCDEF", "#000000", "#123456"
			})
			public void isRgbHexWithValueValid(String value)
			{
				boolean actual = StringRegexp.isRgbHex(value);

				assertThat(actual).isTrue();
			}

			/**
			 * Проверка метода {@link StringRegexp#isRgbHex(String)} с некорректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"", " ", "FFFFFF", "#23394W", "#ARDBZG"
			})
			public void isRgbHexWithValueInvalid(String value)
			{
				boolean actual = StringRegexp.isRgbHex(value);

				assertThat(actual).isFalse();
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#isRgbHexIgnoreCase(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class IsRgbHexIgnoreCase
		{
			/**
			 * Проверка метода {@link StringRegexp#isRgbHexIgnoreCase(String)} с корректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"#fffFFF", "#abcDEF", "#000000", "#123456"
			})
			public void isRgbHexIgnoreCaseWithValueValid(String value)
			{
				boolean actual = StringRegexp.isRgbHexIgnoreCase(value);

				assertThat(actual).isTrue();
			}

			/**
			 * Проверка метода {@link StringRegexp#isRgbHexIgnoreCase(String)} с некорректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"", " ", "fffFFF", "#23394w", "#ardBZG"
			})
			public void isRgbHexIgnoreCaseWithValueInvalid(String value)
			{
				boolean actual = StringRegexp.isRgbHexIgnoreCase(value);

				assertThat(actual).isFalse();
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#isMacAddress(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class IsMacAddress
		{
			/**
			 * Проверка метода {@link StringRegexp#isMacAddress(String)} с корректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"00-EF-CD-EF-11-22", "00:EF:CD:EF:11:22"
			})
			public void isMacAddressWithValueValid(String value)
			{
				boolean actual = StringRegexp.isMacAddress(value);

				assertThat(actual).isTrue();
			}

			/**
			 * Проверка метода {@link StringRegexp#isMacAddress(String)} с некорректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"",
				" ",
				"-00-EF-CD-EF-11-22",
				"-00-EF-CD-EF-11-22-",
				"00-EF-CD-EF-11-22-",
				":00:EF:CD:EF:11:22",
				":00:EF:CD:EF:11:22:",
				"00:EF:CD:EF:11:22:",
				"00:EF:CD:EF:11:2",
				"00:EW:CD:EF:11:22",
				"00:ef:CD:EF:11:22",
				"00:EF:CD::EF:11:22",
				"00:EF::EF:11:22",
			})
			public void isMacAddressWithValueInvalid(String value)
			{
				boolean actual = StringRegexp.isMacAddress(value);

				assertThat(actual).isFalse();
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#isMacAddressIgnoreCase(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class IsMacAddressIgnoreCase
		{
			/**
			 * Проверка метода {@link StringRegexp#isMacAddressIgnoreCase(String)} с корректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"00-EF-cd-EF-11-22", "00:ef:CD:EF:11:22"
			})
			public void isMacAddressIgnoreCaseWithValueValid(String value)
			{
				boolean actual = StringRegexp.isMacAddressIgnoreCase(value);

				assertThat(actual).isTrue();
			}

			/**
			 * Проверка метода {@link StringRegexp#isMacAddressIgnoreCase(String)} с некорректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"", " ", "00:EF:CD:EF:11:2", "00:ew:CD:EF:11:22", "00:EF:CD::ef:11:22", "00:EF::ef:11:22"
			})
			public void isMacAddressIgnoreCaseWithValueInvalid(String value)
			{
				boolean actual = StringRegexp.isMacAddressIgnoreCase(value);

				assertThat(actual).isFalse();
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#isTelegram(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class IsTelegram
		{
			/**
			 * Проверка метода {@link StringRegexp#isTelegram(String)} с корректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"@kalenchukov", "@Kalenchukov", "@KALENCHUKOV", "@kalen_chukov", "@kalenchukov_", "@_kalenchukov"
			})
			public void isTelegramWithValueValid(String value)
			{
				boolean actual = StringRegexp.isTelegram(value);

				assertThat(actual).isTrue();
			}

			/**
			 * Проверка метода {@link StringRegexp#isTelegram(String)} с некорректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"", " ", "kalenchukov", "@kalenchukov.", "@kalenchukov-", "@kalenchukov+", "@kalen-chukov"
			})
			public void isTelegramWithValueInvalid(String value)
			{
				boolean actual = StringRegexp.isTelegram(value);

				assertThat(actual).isFalse();
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#isTag(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class IsTag
		{
			/**
			 * Проверка метода {@link StringRegexp#isTag(String)} с корректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"#tag", "#Tag", "#TAG", "#ta_g", "#123tag", "#tag123", "#tag_123", "#тег", "#тег123", "#тег_123"
			})
			public void isTagWithValueValid(String value)
			{
				boolean actual = StringRegexp.isTag(value);

				assertThat(actual).isTrue();
			}

			/**
			 * Проверка метода {@link StringRegexp#isTag(String)} с некорректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"",
				" ",
				"tag",
				"#t",
				"#ta",
				"#tag.",
				"#tag-",
				"#ta-g",
				"#tag+tag",
				"#_tag",
				"#tag_",
				"#_",
				"#__",
				"#___",
				"#123",
				"#123_",
				"#_123"
			})
			public void isTagWithValueInvalid(String value)
			{
				boolean actual = StringRegexp.isTag(value);

				assertThat(actual).isFalse();
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#isDigitBinary(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class IsDigitBinary
		{
			/**
			 * Проверка метода {@link StringRegexp#isDigitBinary(String)} с корректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"0", "1", "01010101", "1010101"
			})
			public void isDigitBinaryWithValueValid(String value)
			{
				boolean actual = StringRegexp.isDigitBinary(value);

				assertThat(actual).isTrue();
			}

			/**
			 * Проверка метода {@link StringRegexp#isDigitBinary(String)} с некорректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"", " ", "1.0", "012", "1a0", "0a"
			})
			public void isDigitBinaryWithValueInvalid(String value)
			{
				boolean actual = StringRegexp.isDigitBinary(value);

				assertThat(actual).isFalse();
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#isDigitTernary(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class IsDigitTernary
		{
			/**
			 * Проверка метода {@link StringRegexp#isDigitTernary(String)} с корректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"0", "1", "2", "0101201012", "120102101"
			})
			public void isDigitTernaryWithValueValid(String value)
			{
				boolean actual = StringRegexp.isDigitTernary(value);

				assertThat(actual).isTrue();
			}

			/**
			 * Проверка метода {@link StringRegexp#isDigitTernary(String)} с некорректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"", " ", "1.0", "1a0", "0a"
			})
			public void isDigitTernaryWithValueInvalid(String value)
			{
				boolean actual = StringRegexp.isDigitTernary(value);

				assertThat(actual).isFalse();
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#isDigitOctal(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class IsDigitOctal
		{
			/**
			 * Проверка метода {@link StringRegexp#isDigitOctal(String)} с корректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"01234567", "12014302101"
			})
			public void isDigitOctalWithValueValid(String value)
			{
				boolean actual = StringRegexp.isDigitOctal(value);

				assertThat(actual).isTrue();
			}

			/**
			 * Проверка метода {@link StringRegexp#isDigitOctal(String)} с некорректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"", " "
			})
			public void isDigitOctalWithValueInvalid(String value)
			{
				boolean actual = StringRegexp.isDigitOctal(value);

				assertThat(actual).isFalse();
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#isDigitDecimal(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class IsDigitDecimal
		{
			/**
			 * Проверка метода {@link StringRegexp#isDigitDecimal(String)} с корректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"0", "0123", "6516166848498494"
			})
			public void isDigitDecimalWithValueValid(String value)
			{
				boolean actual = StringRegexp.isDigitDecimal(value);

				assertThat(actual).isTrue();
			}

			/**
			 * Проверка метода {@link StringRegexp#isDigitDecimal(String)} с некорректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"", " ", "1.1", "1.1", "1a1", "1a"
			})
			public void isDigitDecimalWithValueInvalid(String value)
			{
				boolean actual = StringRegexp.isDigitDecimal(value);

				assertThat(actual).isFalse();
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#isDigitDuodecimal(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class IsDigitDuodecimal
		{
			/**
			 * Проверка метода {@link StringRegexp#isDigitDuodecimal(String)} с корректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"0123456789AB", "0123456789ab", "120143023649aB101"
			})
			public void isDigitDuodecimalWithValueValid(String value)
			{
				boolean actual = StringRegexp.isDigitDuodecimal(value);

				assertThat(actual).isTrue();
			}

			/**
			 * Проверка метода {@link StringRegexp#isDigitDuodecimal(String)} с некорректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"", " "
			})
			public void isDigitDuodecimalWithValueInvalid(String value)
			{
				boolean actual = StringRegexp.isDigitDuodecimal(value);

				assertThat(actual).isFalse();
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#isDigitHexadecimal(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class IsDigitHexadecimal
		{
			/**
			 * Проверка метода {@link StringRegexp#isDigitHexadecimal(String)} с корректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"0123456789ABCDEF", "0123456789abbcdef", "120ef143023649aB10cd1"
			})
			public void isDigitHexadecimalWithValueValid(String value)
			{
				boolean actual = StringRegexp.isDigitHexadecimal(value);

				assertThat(actual).isTrue();
			}

			/**
			 * Проверка метода {@link StringRegexp#isDigitHexadecimal(String)} с некорректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"", " "
			})
			public void isDigitHexadecimalWithValueInvalid(String value)
			{
				boolean actual = StringRegexp.isDigitHexadecimal(value);

				assertThat(actual).isFalse();
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#isNumber(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class IsNumber
		{
			/**
			 * Проверка метода {@link StringRegexp#isNumber(String)} с корректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"0", "100", "0.1", "0,1", "1.100", "1,100", "1,100,123", "1.100.123",
			})
			public void isNumberWithValueValid(String value)
			{
				boolean actual = StringRegexp.isNumber(value);

				assertThat(actual).isTrue();
			}

			/**
			 * Проверка метода {@link StringRegexp#isNumber(String)} с некорректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"",
				" ",
				".",
				",",
				".1",
				",2",
				"3,",
				"4.",
				"1. 1",
				"1, 1",
				"1. 100",
				"1, 100",
				"1 .100",
				"1 ,100",
				"1 . 100",
				"1 , 100",
				"1,100, 123",
				"1.100. 123"
			})
			public void isNumberWithValueInvalid(String value)
			{
				boolean actual = StringRegexp.isNumber(value);

				assertThat(actual).isFalse();
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#isWord(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class IsWord
		{
			/**
			 * Проверка метода {@link StringRegexp#isWord(String)} с корректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"привет", "ПРИВЕТ", "Hello", "WoRlD", "Hello-World"

			})
			public void isWordWithValueValid(String value)
			{
				boolean actual = StringRegexp.isWord(value);

				assertThat(actual).isTrue();
			}

			/**
			 * Проверка метода {@link StringRegexp#isWord(String)} с некорректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"", " ", "-привет", "привет-", " привет", "привет ", "Hello- World", "Hello -World", "Hello - World"
			})
			public void isWordWithValueInvalid(String value)
			{
				boolean actual = StringRegexp.isWord(value);

				assertThat(actual).isFalse();
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#isLetter(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class IsLetter
		{
			/**
			 * Проверка метода {@link StringRegexp#isLetter(String)} с корректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"А", "Ж", "W", "Z"
			})
			public void isLetterWithValueValid(String value)
			{
				boolean actual = StringRegexp.isLetter(value);

				assertThat(actual).isTrue();
			}

			/**
			 * Проверка метода {@link StringRegexp#isLetter(String)} с некорректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				"", " ", "-", "0", "?"
			})
			public void isLetterWithValueInvalid(String value)
			{
				boolean actual = StringRegexp.isLetter(value);

				assertThat(actual).isFalse();
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#findEmailAddress(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class FindEmailAddress
		{
			/**
			 * Проверка метода {@link StringRegexp#findEmailAddress(String)} без корректных значений.
			 */
			@Test
			public void findEmailAddressWithZeroValue()
			{
				String value = """
					И я должен прийти к девяти, на работу свою,
					Но сейчас уже без десяти, а я только встаю
					На столе моем завтрак стоит, от него не уйти
					И наверное, я к девяти не смогу подойти
					Ещё только без десяти, девять часов
					Ещё только без десяти, девять часов
					В объяснительной я напишу, что был у врача,
					А еще напишу, что часов, на пути не встречал
					И пускай все ругают меня, на работе моей
					И пускай все позорят меня, на работе моей
					Ещё только без десяти, девять часов
					Ещё только без десяти, девять часов
					Ещё только без десяти, девять часов
					Ещё только без десяти, девять часов
					Эх, ещё только без десяти, девять часов
					""";

				List<String> actual = StringRegexp.findEmailAddress(value);

				assertThat(actual).isEmpty();
			}

			/**
			 * Проверка метода {@link StringRegexp#findEmailAddress(String)} с несколькими корректными значениями.
			 */
			@Test
			public void findEmailAddressWithMultipleValue()
			{
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

				List<String> expected = List.of("aleksey.kalenchukov@yandex.ru", "alekseyKalenchukov@yandex.ru",
													"aleksey123kalenchukov@yandex.ru", "123alekseykalenchukov@yandex.ru"
				);

				List<String> actual = StringRegexp.findEmailAddress(value);

				assertThat(actual).containsExactlyElementsOf(expected);
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#findDomain(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class FindDomain
		{
			/**
			 * Проверка метода {@link StringRegexp#findDomain(String)} без корректных значений.
			 */
			@Test
			public void findDomainWithZeroValue()
			{
				String value = """
					Зерна упали в землю, зерна просят дождя.
					Им нужен дождь.
					Разрежь мою грудь, посмотри мне внутрь,
					Ты увидишь, там все горит огнем.
					Через день будет поздно, через час будет поздно,
					Через миг будет уже не встать.
					Если к дверям не подходят ключи, вышиби двери плечом.

					Мама, мы все тяжело больны...
					Мама, я знаю, мы все сошли с ума...

					Сталь между пальцев, сжатый кулак.
					Удар выше кисти, терзающий плоть,
					Но вместо крови в жилах застыл яд, медленный яд.
					Разрушенный мир, разбитые лбы, разломанный надвое хлеб.
					И вот кто-то плачет, а кто-то молчит,
					А кто-то так рад, кто-то так рад...
					Мама, мы все тяжело больны...
					Мама, я знаю, мы все сошли с ума...

					Ты должен быть сильным, ты должен уметь сказать:
					Руки прочь, прочь от меня!
					Ты должен быть сильным, иначе зачем тебе быть.
					Что будет стоить тысячи слов,
					Когда важна будет крепость руки?
					И вот ты стоишь на берегу и думаешь: "Плыть или не плыть?"

					Мама, мы все тяжело больны...
					Мама, я знаю, мы все сошли с ума...
					Мама, мы все тяжело больны...
					Мама, я знаю, мы все сошли с ума...
					""";

				List<String> actual = StringRegexp.findDomain(value);

				assertThat(actual).isEmpty();
			}
			/**
			 * Проверка метода {@link StringRegexp#findDomain(String)} с несколькими корректными значениями.
			 */
			@Test
			public void findDomainWithMultipleValue()
			{
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

				List<String> expected = List.of("kalenchukov.dev", "regexp.string.kalenchukov.dev",
													"aleksey.123.kalenchukov.ru", "123.aleksey.kalenchukov.ru"
				);

				List<String> actual = StringRegexp.findDomain(value);

				assertThat(actual).containsExactlyElementsOf(expected);
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#findUrlHttp(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class FindUrlHttp
		{
			/**
			 * Проверка метода {@link StringRegexp#findUrlHttp(String)} без корректных значений.
			 */
			@Test
			public void findUrlHttpWithZeroValue()
			{
				String value = """
					Тот, кто в пятнадцать лет убежал из дома
					Вряд ли поймёт того, кто учился в спецшколе
					Тот, у кого есть хороший жизненный план
					Вряд ли будет думать о чём-то другом

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
					
					Все говорят, что мы вместе
					Все говорят, но немногие знают в каком,
					А из наших труб идёт необычный дым
					Стой, опасная зона, работа мозга

					Бошетунмай, бошетунмай
					Бошетунмай, бошетунмай
					Бошетунмай, бошетунмай
					Бошетунмай, бошетунмай
					Бошетунмай, бошетунмай
					""";

				List<String> actual = StringRegexp.findUrlHttp(value);

				assertThat(actual).isEmpty();
			}

			/**
			 * Проверка метода {@link StringRegexp#findUrlHttp(String)} с несколькими корректными значениями.
			 */
			@Test
			public void findUrlHttpWithMultipleValue()
			{
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

				List<String> expected = List.of(
					"http://www.kalenchukov.dev/string/regexp/?java=18&isUrlHttp=test123#readme",
					"https://kalenchukov.dev/#readme", "https://www.kalenchukov.dev/string/regexp/",
					"http://kalenchukov.dev/?isUrlHttp=test123"
				);

				List<String> actual = StringRegexp.findUrlHttp(value);

				assertThat(actual).containsExactlyElementsOf(expected);
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#findCountryCodeAlpha2(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class FindCountryCodeAlpha2
		{
			/**
			 * Проверка метода {@link StringRegexp#findCountryCodeAlpha2(String)} без корректных значений.
			 */
			@Test
			public void findCountryCodeAlpha2WithZeroValue()
			{
				String value = """
					О-o, это странное место Камчатка
					О-o, это сладкое слово «Камчатка»
					Но на этой земле я не вижу тебя
					Я не вижу твоих кораблей
					Я не вижу реки, я не вижу моста
					Ну и пусть...
					О-o, это странное место Камчатка
					О-o, это сладкое слово «Камчатка»
					Я нашел здесь руду, я нашел здесь любовь
					Я пытаюсь забыть, забываю и вновь
					Вспоминаю собаку, она, как звезда
					Ну и пусть...
					О-o, это странное место Камчатка
					О-o, это сладкое слово «Камчатка»
					Я не вижу здесь их, я не вижу здесь нас
					Я искал здесь вино, а нашел третий глаз
					Мои руки из дуба, голова из свинца
					Ну и пусть...
					""";

				List<String> actual = StringRegexp.findCountryCodeAlpha2(value);

				assertThat(actual).isEmpty();
			}

			/**
			 * Проверка метода {@link StringRegexp#findCountryCodeAlpha2(String)} с несколькими корректными значениями.
			 */
			@Test
			public void findCountryCodeAlpha2WithMultipleValue()
			{
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

				List<String> expected = List.of("RU", "BY");

				List<String> actual = StringRegexp.findCountryCodeAlpha2(value);

				assertThat(actual).containsExactlyElementsOf(expected);
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#findCountryCodeAlpha3(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class FindCountryCodeAlpha3
		{
			/**
			 * Проверка метода {@link StringRegexp#findCountryCodeAlpha3(String)} без корректных значений.
			 */
			@Test
			public void findCountryCodeAlpha3WithZeroValue()
			{
				String value = """
					Каждый день ты приходишь домой, когда темно.
					Каждый день долго едешь в метро, когда темно.
					А она живет в центре всех городов,
					И ты хочешь быть рядом,
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

				List<String> actual = StringRegexp.findCountryCodeAlpha3(value);

				assertThat(actual).isEmpty();
			}

			/**
			 * Проверка метода {@link StringRegexp#findCountryCodeAlpha3(String)} с несколькими корректными значениями.
			 */
			@Test
			public void findCountryCodeAlpha3WithMultipleValue()
			{
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

				List<String> expected = List.of("RUS", "BLR");

				List<String> actual = StringRegexp.findCountryCodeAlpha3(value);

				assertThat(actual).containsExactlyElementsOf(expected);
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#findCountryCodeNumeric3(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class FindCountryCodeNumeric3
		{
			/**
			 * Проверка метода {@link StringRegexp#findCountryCodeNumeric3(String)} без корректных значений.
			 */
			@Test
			public void findCountryCodeNumeric3WithZeroValue()
			{
				String value = """
					Стань птицей, живущей в моём небе
					Помни, что нет тюрьмы страшнее, чем в голове
					Стань птицей, не думай о хлебе
					Я стану дорогой
					Я помню прозрачность воды моря
					Я вижу прозрачность горящего газа
					Стань сердцем, бейся в моём теле
					Я стану кровью
					Я буду делать всё, как умею
					Стань книгой, ложись в мои руки
					Стань песней, живи на моих губах
					Я стану словами
					""";

				List<String> actual = StringRegexp.findCountryCodeNumeric3(value);

				assertThat(actual).isEmpty();
			}

			/**
			 * Проверка метода {@link StringRegexp#findCountryCodeNumeric3(String)} с несколькими корректными значениями.
			 */
			@Test
			public void findCountryCodeNumeric3WithMultipleValue()
			{
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

				List<String> expected = List.of("052", "643", "112");

				List<String> actual = StringRegexp.findCountryCodeNumeric3(value);

				assertThat(actual).containsExactlyElementsOf(expected);
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#findRgbHex(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class FindRgbHex
		{
			/**
			 * Проверка метода {@link StringRegexp#findRgbHex(String)} без корректных значений.
			 */
			@Test
			public void findRgbHexWithZeroValue()
			{
				String value = """
					Крыши домов дрожат под тяжестью дней
					Небесный пастух пасёт облака
					Город стреляет в ночь дробью огней
					Но ночь сильней, её власть велика

					Тем, кто ложится спать
					Спокойного сна, спокойная ночь
					Тем, кто ложится спать, спокойного сна
					Спокойная ночь

					Я ждал это время, и вот это время пришло
					Те, кто молчал перестали молчать
					Те, кому нечего ждать, садятся в седло
					Их не догнать, уже не догнать

					А тем, кто ложится спать
					Спокойного сна, спокойная ночь
					Тем, кто ложится спать, спокойного сна
					Спокойная ночь

					Соседи приходят, им слышится стук копыт
					Мешает уснуть, тревожит их сон
					Те, кому нечего ждать, отправляются в путь
					Те, кто спасён, те, кто спасён

					А тем, кто ложится спать
					Спокойного сна, спокойная ночь
					Тем, кто ложится спать, спокойного сна
					Спокойная ночь
					""";

				List<String> actual = StringRegexp.findRgbHex(value);

				assertThat(actual).isEmpty();
			}

			/**
			 * Проверка метода {@link StringRegexp#findRgbHex(String)} с несколькими корректными значениями.
			 */
			@Test
			public void findRgbHexWithMultipleValue()
			{
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

				List<String> expected = List.of("#FFFFFF", "#000000", "#D0E9F8", "#1A2B3C");

				List<String> actual = StringRegexp.findRgbHex(value);

				assertThat(actual).containsExactlyElementsOf(expected);
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#findRgbHexIgnoreCase(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class FindRgbHexIgnoreCase
		{
			/**
			 * Проверка метода {@link StringRegexp#findRgbHexIgnoreCase(String)} без корректных значений.
			 */
			@Test
			public void findRgbHexIgnoreCaseWithZeroValue()
			{
				String value = """
					Застоялся мой поезд в депо.
					Снова я уезжаю. Пора...
					На пороге ветер заждался меня.
					На пороге осень — моя сестра.

					После красно-желтых дней начнется и кончится зима.
					Горе ты мое от ума, не печалься, гляди веселей.
					И я вернусь домой со щитом, а, может быть, на щите,
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
					Я проснулся и понял — беда...

					После красно-желтых дней начнется и кончится зима.
					Горе ты мое от ума, не печалься, гляди веселей.
					И я вернусь домой со щитом, а, может быть, на щите,
					В серебре, а, может быть, в нищете, но как можно скорей.
					""";

				List<String> actual = StringRegexp.findRgbHexIgnoreCase(value);

				assertThat(actual).isEmpty();
			}

			/**
			 * Проверка метода {@link StringRegexp#findRgbHexIgnoreCase(String)} с несколькими корректными значениями.
			 */
			@Test
			public void findRgbHexIgnoreCaseWithMultipleValue()
			{
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

				List<String> expected = List.of("#fffFFF", "#000000", "#d0e9F8", "#1a2B3C");

				List<String> actual = StringRegexp.findRgbHexIgnoreCase(value);

				assertThat(actual).containsExactlyElementsOf(expected);
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#findMacAddress(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class FindMacAddress
		{
			/**
			 * Проверка метода {@link StringRegexp#findMacAddress(String)} без корректных значений.
			 */
			@Test
			public void findMacAddressWithZeroValue()
			{
				String value = """
					В этом мотиве есть какая-то фальшь,
					Но где найти тех, что услышат ее?
					Подросший ребенок, воспитанный жизнью за шкафом,
					Теперь ты видишь Солнце, возьми - это твое!

					Я объявляю свой дом
					Безъядерной зоной!
					Я объявляю свой двор
					Безъядерной зоной!
					Я объявляю свой город
					Безъядерной зоной!
					яю свой...

					Как не прочны стены наших квартир,
					Но кто-то один не подставит за всех плечо.
					Я вижу дом, я беру в руки мел,
					Нет замка, но я владею ключом.
					
					Я объявляю свой дом
					Безъядерной зоной!
					Я объявляю свой двор
					Безъядерной зоной!
					Я объявляю свой город
					Безъядерной зоной!
					Я объявляю свой...
					""";

				List<String> actual = StringRegexp.findMacAddress(value);

				assertThat(actual).isEmpty();
			}

			/**
			 * Проверка метода {@link StringRegexp#findMacAddress(String)} с несколькими корректными значениями.
			 */
			@Test
			public void findMacAddressWithMultipleValue()
			{
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

				List<String> expected = List.of("00-EF-CD-EF-11-22", "00:EF:CD:EF:11:22", "00:EF:CD:DF:11:22",
													"00-EF-CD-CE-11-22"
				);

				List<String> actual = StringRegexp.findMacAddress(value);

				assertThat(actual).containsExactlyElementsOf(expected);
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#findMacAddressIgnoreCase(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class FindMacAddressIgnoreCase
		{
			/**
			 * Проверка метода {@link StringRegexp#findMacAddressIgnoreCase(String)} без корректных значений.
			 */
			@Test
			public void findMacAddressIgnoreCaseWithZeroValue()
			{
				String value = """
					Я вижу, как волны смывают следы на песке
					Я слышу, как ветер поет свою странную песню
					Я слышу, как струны деревьев играют ее
					Музыку волн, музыку ветра

					Здесь трудно сказать, что такое асфальт
					Здесь трудно сказать, что такое машина
					Здесь нужно руками кидать воду вверх
					Музыка волн, музыка ветра
					
					Кто из вас вспомнит о тех, кто сбился с дороги?
					Кто из вас вспомнит о тех, кто смеялся и пел?
					Кто из вас вспомнит, чувствуя холод приклада
					Музыку волн, музыку ветра?
					Музыку волн, музыку ветра?
					""";

				List<String> actual = StringRegexp.findMacAddressIgnoreCase(value);

				assertThat(actual).isEmpty();
			}

			/**
			 * Проверка метода {@link StringRegexp#findMacAddressIgnoreCase(String)} с несколькими корректными значениями.
			 */
			@Test
			public void findMacAddressIgnoreCaseWithMultipleValue()
			{
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

				List<String> expected = List.of("00-EF-CD-ef-11-22", "00:EF:CD:EF:11:22", "00:EF:cd:DF:11:22",
													"00-ef-cd-ce-11-22"
				);

				List<String> actual = StringRegexp.findMacAddressIgnoreCase(value);

				assertThat(actual).containsExactlyElementsOf(expected);
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#findUuid(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class FindUuid
		{
			/**
			 * Проверка метода {@link StringRegexp#findUuid(String)} без корректных значений.
			 */
			@Test
			public void findUuidWithZeroValue()
			{
				String value = """
					Ты так любишь эти фильмы
					Мне знакомы эти песни
					Ты так любишь кинотеатры
					Мы вряд ли сможем быть вместе
					""";

				List<String> actual = StringRegexp.findUuid(value);

				assertThat(actual).isEmpty();
			}

			/**
			 * Проверка метода {@link StringRegexp#findUuid(String)} с несколькими корректными значениями.
			 */
			@Test
			public void findUuidWithMultipleValue()
			{
				String value = """
					Ты так любишь эти фильмы
					Мне знакомы 123e4567-e89b-12d3-a456-426655440000 эти песни
					Ты так любишь кинотеатры
					Мы вряд ли сможем быть вместе 00000000-0000-0000-0000-000000000000
					""";

				List<String> expected = List.of("123e4567-e89b-12d3-a456-426655440000",
													"00000000-0000-0000-0000-000000000000"
				);

				List<String> actual = StringRegexp.findUuid(value);

				assertThat(actual).containsExactlyElementsOf(expected);
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#findInet4Address(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class FindInet4Address
		{
			/**
			 * Проверка метода {@link StringRegexp#findInet4Address(String)} без корректных значений.
			 */
			@Test
			public void findInet4AddressWithZeroValue()
			{
				String value = """
					День как день, только ты почему-то грустишь.
					И вокруг все поют, только ты один молчишь.
					Потерял аппетит и не хочешь сходить в кино.
					Ты идешь в магазин, чтобы купить вино.

					Солнце светит и растет трава,
					Но тебе она не нужна.
					Все не так и все не то,
					Когда твоя девушка больна
					Когда твоя девушка больна
					Когда больна.

					Ты идешь в магазин, головою поник,
					Как будто иссяк чистый горный родник.
					Она где-то лежит, ест мед и пьет аспирин,
					И вот ты идешь на вечеринку один.

					Солнце светит и растет трава,
					Но тебе она не нужна.
					Все не так и все не то,
					Когда твоя девушка больна.
					На вечеринку - один.
					Когда твоя девушка больна.
					На вечеринку - один.
					Когда твоя девушка больна.

					Когда твоя девушка больна.
					Когда твоя девушка больна.
					""";

				List<String> actual = StringRegexp.findInet4Address(value);

				assertThat(actual).isEmpty();
			}

			/**
			 * Проверка метода {@link StringRegexp#findInet4Address(String)} с несколькими корректными значениями.
			 */
			@Test
			public void findInet4AddressWithMultipleValue()
			{
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

				List<String> expected = List.of("192.168.1.1", "1.1.1.1", "0.0.0.0", "10.222.170.80");

				List<String> actual = StringRegexp.findInet4Address(value);

				assertThat(actual).containsExactlyElementsOf(expected);
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#findRgbNumeric(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class FindRgbNumeric
		{
			/**
			 * Проверка метода {@link StringRegexp#findRgbNumeric(String)} без корректных значений.
			 */
			@Test
			public void findRgbNumericWithZeroValue()
			{
				String value = """
					Наши реки бедны водой
					В наших окнах не видно дня
					Наше утро похоже на ночь
					Ну, а ночь — для меня
					Глядя в жидкое зеркало луж
					На часы, что полвека стоят
					На до дыр зацелованный флаг
					Я полцарства отдам за коня

					Играй!
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
					Мы сидим, мы ждём

					Играй!
					Невесёлая песня моя
					Играй!
					Играй!
					Играй!
					Невесёлая песня моя
					Играй!
					Играй!
					""";

				List<String> actual = StringRegexp.findRgbNumeric(value);

				assertThat(actual).isEmpty();
			}

			/**
			 * Проверка метода {@link StringRegexp#findRgbNumeric(String)} с несколькими корректными значениями.
			 */
			@Test
			public void findRgbNumericWithMultipleValue()
			{
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

				List<String> expected = List.of("255,150,50", "0, 0, 0", "50, 50,50", "113,13,3");

				List<String> actual = StringRegexp.findRgbNumeric(value);

				assertThat(actual).containsExactlyElementsOf(expected);
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#findTelegram(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class FindTelegram
		{
			/**
			 * Проверка метода {@link StringRegexp#findTelegram(String)} без корректных значений.
			 */
			@Test
			public void findTelegramWithZeroValue()
			{
				String value = """
					Ты часто проходишь мимо, не видя меня
					С кем-то другим, я стою не дыша
					Я знаю, что ты живешь в соседнем дворе
					Ты идешь не спеша, не спеша...

					Ооооу, но это не любовь...
					Ооооу, но это не любовь...

					А вечером я стою под твоим окном
					Ты поливаешь цветы, поливаешь цветы
					А я дотемна стою и сгораю огнем
					И виной тому ты, только ты...

					Ооооу, но это не любовь...
					Ооооу, но это не любовь...

					Научи меня всему тому, что умеешь ты
					Я хочу это знать и уметь
					Сделай так, чтобы сбылись все мои мечты
					Мне нельзя больше ждать, я могу умереть...
					
					Ооооу, но это не любовь...
					Ооооу, но это не любовь...
					""";

				List<String> actual = StringRegexp.findTelegram(value);

				assertThat(actual).isEmpty();
			}

			/**
			 * Проверка метода {@link StringRegexp#findTelegram(String)} с несколькими корректными значениями.
			 */
			@Test
			public void findTelegramWithMultipleValue()
			{
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

				List<String> expected = List.of("@kalenchukov", "@Kalenchukov", "@KALENCHUKOV", "@kalen_CHUKOV");

				List<String> actual = StringRegexp.findTelegram(value);

				assertThat(actual).containsExactlyElementsOf(expected);
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#findInet6Address(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class FindInet6Address
		{
			/**
			 * Проверка метода {@link StringRegexp#findInet6Address(String)} без корректных значений.
			 */
			@Test
			public void findInet6AddressWithZeroValue()
			{
				String value = """
					В последнее время я редко был дома,
					Так что даже отвыкли звонить мне друзья.
					В разъездах, разгулах конца лета симптомы
					Совсем перестали вдруг мучить меня.

					И я подумал что Осень - это тоже не плохо,
					И что Осенью слякоть и сер первый снег,
					И что холод ветров я буду чувствовать боком,
					Опьяненный сознаньем того, что я человек.

					И этой Осенью много дней чьих-то рождений
					И уж я постараюсь на них побывать,
					А потом, игнорируя лужи и слякоть,
					Я приду домой пьяный и мешком повалюсь на кровать.

					И утром рано я встану и отправлюсь учиться,
					И с похмелья я буду смеяться над всем.
					Скоро будет Зима, чтоб в Весне раствориться,
					А потом будет Лето - неизвестно зачем.

					И я начал за здравие, а кончу я плохо,
					Написав наш порядковый номер - 600.
					С чьих-то старых столов подбираю я крохи,
					И не в силах сказать, что принес этот год.
					""";

				List<String> actual = StringRegexp.findInet6Address(value);

				assertThat(actual).isEmpty();
			}

			/**
			 * Проверка метода {@link StringRegexp#findInet6Address(String)} с несколькими корректными значениями.
			 */
			@Test
			public void findInet6AddressWithMultipleValue()
			{
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

				List<String> expected = List.of(
					"2001:0DB8:11A3:09D7:1F34:8A2E:07A0:765D",
					"AD12::AE21::",
					"::AE21:AD12",
					"2001:DB8::AE21:AD12"
				);

				List<String> actual = StringRegexp.findInet6Address(value);

				assertThat(actual).containsExactlyElementsOf(expected);
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#findInet6AddressIgnoreCase(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class FindInet6AddressIgnoreCase
		{
			/**
			 * Проверка метода {@link StringRegexp#findInet6AddressIgnoreCase(String)} без корректных значений.
			 */
			@Test
			public void findInet6AddressIgnoreCaseWithZeroValue()
			{
				String value = """
					За окнами солнце, за окнами свет - это день.
					Ну, а я всегда любил ночь.
					И это мое дело - любить ночь,
					И это мое право - уйти в тень.

					Я люблю ночь за то, что в ней меньше машин,
					Я люблю дым и пепел своих папирос,
					Я люблю кухни за то, что они хранят тайны,
					Я люблю свой дом, но вряд ли это всерьез.

					И эта ночь и ее электрический свет бьет мне в глаза,
					И эта ночь и ее электрический дождь бьет мне в окно,
					И эта ночь и ее электрический голос манит меня к себе,
					И я не знаю, как мне прожить следующий день.

					Я один, но это не значит, что я одинок,
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
					И я не знаю, как мне прожить следующий день.
					""";

				List<String> actual = StringRegexp.findInet6AddressIgnoreCase(value);

				assertThat(actual).isEmpty();
			}

			/**
			 * Проверка метода {@link StringRegexp#findInet6AddressIgnoreCase(String)} с несколькими корректными значениями.
			 */
			@Test
			public void findInet6AddressIgnoreCaseWithMultipleValue()
			{
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

				List<String> expected = List.of(
					"2001:0db8:11a3:09d7:1F34:8A2E:07A0:765D",
					"ad12::AE21::",
					"::ae21:AD12",
					"2001:db8::AE21:AD12"
				);

				List<String> actual = StringRegexp.findInet6AddressIgnoreCase(value);

				assertThat(actual).containsExactlyElementsOf(expected);
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#findTag(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class FindTag
		{
			/**
			 * Проверка метода {@link StringRegexp#findTag(String)} без корректных значений.
			 */
			@Test
			public void findTagWithZeroValue()
			{
				String value = """
					Гуляю. Я один гуляю.
					Что дальше делать, я не знаю.
					Нет дома. Никого нет дома.
					Я лишний, словно куча лома, у-у.

					Припев:
					Я бездельник, о-о, мама, мама, я бездельник, у-у...
					Я бездельник, о-о, мама, мама.

					В толпе я как иголка в сене.
					Я снова человек без цели.
					Болтаюсь, целый день гуляю.
					Не знаю, я ничего не знаю, у-у.

					Припев:
					Я бездельник, о-о, мама, мама, я бездельник, у-у...
					Я бездельник, о-о, мама, мама.

					у-у... я бездельник, о-о, мама, мама.
					Я бездельник, у-у...
					Я бездельник, о-о, мама, мама, я бездельник, у-у...
					Я бездельник, у-у...
					""";

				List<String> actual = StringRegexp.findTag(value);

				assertThat(actual).isEmpty();
			}

			/**
			 * Проверка метода {@link StringRegexp#findTag(String)} с несколькими корректными значениями.
			 */
			@Test
			public void findTagWithMultipleValue()
			{
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

				List<String> expected = List.of("#tag", "#Tag", "#TAG", "#ta_g");

				List<String> actual = StringRegexp.findTag(value);

				assertThat(actual).containsExactlyElementsOf(expected);
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#findDigitBinary(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class FindDigitBinary
		{
			/**
			 * Проверка метода {@link StringRegexp#findDigitBinary(String)} без корректных значений.
			 */
			@Test
			public void findDigitBinaryWithZeroValue()
			{
				String value = """
					Мы хотим видеть дальше, чем окна дома напротив
					Мы хотим жить, мы живучи, как кошки
					И вот мы пришли заявить о своих правах, да
					Слышишь шелест плащей? Это мы

					Дальше действовать будем мы
					Дальше действовать будем мы
					Дальше действовать будем мы
					Дальше действовать будем мы
					
					Мы родились в тесных квартирах новых районов
					Мы потеряли невинность в боях за любовь
					Нам уже стали тесны одежды,
					Сшитые вами для нас одежды
					И вот мы пришли сказать вам о том, что дальше...

					Дальше действовать будем мы
					Дальше действовать будем мы
					Дальше действовать будем мы
					Дальше действовать будем мы

					Дальше действовать будем мы
					Дальше действовать будем мы
					Дальше действовать будем мы
					Дальше действовать будем мы
					""";

				List<String> actual = StringRegexp.findDigitBinary(value);

				assertThat(actual).isEmpty();
			}

			/**
			 * Проверка метода {@link StringRegexp#findDigitBinary(String)} с несколькими корректными значениями.
			 */
			@Test
			public void findDigitBinaryWithMultipleValue()
			{
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

				List<String> expected = List.of("010101", "1101", "10101", "01010101");

				List<String> actual = StringRegexp.findDigitBinary(value);

				assertThat(actual).containsExactlyElementsOf(expected);
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#findDigitTernary(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class FindDigitTernary
		{
			/**
			 * Проверка метода {@link StringRegexp#findDigitTernary(String)} без корректных значений.
			 */
			@Test
			public void findDigitTernaryWithZeroValue()
			{
				String value = """
					Струн провода, ток по рукам,
					Телефон на все голоса говорит: «Пока!» Пора...
					И пальто на гвозде, шарф в рукаве
					И перчатки в карманах шепчут:
					«Подожди до утра!» До утра...

					Но странный стук зовёт: «В дорогу!»
					Может сердца, а может стук в дверь.
					И, когда я обернусь на пороге,
					Я скажу одно лишь слово: «Верь!»

					И опять на вокзал, и опять к поездам,
					И опять проводник выдаст бельё и чай,
					И опять не усну, и опять сквозь грохот колёс
					Мне послышится слово: «Прощай!»

					Но странный стук зовёт: «В дорогу!»
					Может сердца, а может стук в дверь.
					И, когда я обернусь на пороге,
					Я скажу одно лишь слово: «Верь!»

					Но странный стук зовёт: «В дорогу!»
					Может сердца, а может стук в дверь.
					И, когда я обернусь на пороге,
					Я скажу одно лишь слово: «Верь!»
					""";

				List<String> actual = StringRegexp.findDigitTernary(value);

				assertThat(actual).isEmpty();
			}

			/**
			 * Проверка метода {@link StringRegexp#findDigitTernary(String)} с несколькими корректными значениями.
			 */
			@Test
			public void findDigitTernaryWithMultipleValue()
			{
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

				List<String> expected = List.of("0101201", "11021", "102101", "010210101");

				List<String> actual = StringRegexp.findDigitTernary(value);

				assertThat(actual).containsExactlyElementsOf(expected);
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#findDigitOctal(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class FindDigitOctal
		{
			/**
			 * Проверка метода {@link StringRegexp#findDigitOctal(String)} без корректных значений.
			 */
			@Test
			public void findDigitOctalWithZeroValue()
			{
				String value = """
					Над землёй мороз, что ни тронь - всё лёд.
					Лишь во сне моём, поёт капель.
					А снег идёт стеной, а снег идёт весь день,
					А за той стеной - стоит Апрель.

					А он придёт и приведёт за собой Весну,
					И рассеет серых туч войска,
					А когда мы все посмотрим в глаза его,
					На нас из глаз его посмотрит тоска.
					И откроются двери домов,
					Да ты садись, а то в ногах правды нет.
					И когда мы все посмотрим в глаза его,
					То увидим в тех глазах солнца свет.
					
					На теле ран не счесть, не легки шаги,
					Лишь в груди горит звезда.
					И умрёт Апрель, и родится вновь,
					И придёт уже навсегда.

					А он придёт и приведёт за собой Весну,
					И рассеет серых туч войска.
					А когда мы все посмотрим в глаза его,
					На нас из глаз его посмотрит тоска.
					И откроются двери домов,
					Да ты садись, а то в ногах правды нет,
					И когда мы все посмотрим в глаза его,
					То увидим в тех глазах солнца свет.
					""";

				List<String> actual = StringRegexp.findDigitOctal(value);

				assertThat(actual).isEmpty();
			}

			/**
			 * Проверка метода {@link StringRegexp#findDigitOctal(String)} с несколькими корректными значениями.
			 */
			@Test
			public void findDigitOctalWithMultipleValue()
			{
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

				List<String> expected = List.of("010317201", "1160215", "170241031", "01021034567101");

				List<String> actual = StringRegexp.findDigitOctal(value);

				assertThat(actual).containsExactlyElementsOf(expected);
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#findDigitDuodecimal(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class FindDigitDuodecimal
		{
			/**
			 * Проверка метода {@link StringRegexp#findDigitDuodecimal(String)} без корректных значений.
			 */
			@Test
			public void findDigitDuodecimalWithZeroValue()
			{
				String value = """
					Крыши домов дрожат под тяжестью дней
					Небесный пастух пасёт облака 
					Город стреляет в ночь дробью огней
					Но ночь сильней, её власть велика

					Тем, кто ложится спать
					Спокойного сна, спокойная ночь
					Тем, кто ложится спать, спокойного сна
					Спокойная ночь
					
					Я ждал это время, и вот это время пришло
					Те, кто молчал перестали молчать
					Те, кому нечего ждать, садятся в седло
					Их не догнать, уже не догнать

					А тем, кто ложится спать
					Спокойного сна, спокойная ночь
					Тем, кто ложится спать, спокойного сна
					Спокойная ночь

					Соседи приходят, им слышится стук копыт
					Мешает уснуть, тревожит их сон
					Те, кому нечего ждать, отправляются в путь
					Те, кто спасён, те, кто спасён

					А тем, кто ложится спать
					Спокойного сна, спокойная ночь
					Тем, кто ложится спать, спокойного сна
					Спокойная ночь
					""";

				List<String> actual = StringRegexp.findDigitDuodecimal(value);

				assertThat(actual).isEmpty();
			}

			/**
			 * Проверка метода {@link StringRegexp#findDigitDuodecimal(String)} с несколькими корректными значениями.
			 */
			@Test
			public void findDigitDuodecimalWithMultipleValue()
			{
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

				List<String> expected = List.of("01031A9b7201", "11602B15", "17024A1031", "B010A210394567101");

				List<String> actual = StringRegexp.findDigitDuodecimal(value);

				assertThat(actual).containsExactlyElementsOf(expected);
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#findDigitHexadecimal(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class FindDigitHexadecimal
		{
			/**
			 * Проверка метода {@link StringRegexp#findDigitHexadecimal(String)} без корректных значений.
			 */
			@Test
			public void findDigitHexadecimalWithZeroValue()
			{
				String value = """
					На холодной земле стоит город большой.
					Там горят фонари, и машины гудят.
					А над городом ночь, а над ночью луна,
					И сегодня луна каплей крови красна.

					Дом стоит, свет горит, из окна видна даль.
					Так откуда взялась печаль?
					И вроде, жив и здоров, и вроде жить, не тужить.
					Так откуда взялась печаль?

					А вокруг благодать - ни черта не видать,
					А вокруг красота - не видать ни черта.
					И все кричат: "Ура!" и все бегут вперед,
					И над этим всем, новый день встает.

					Дом стоит, свет горит, из окна видна даль.
					Так откуда взялась, печаль?
					И вроде, жив и здоров, и вроде жить, не тужить.
					Так откуда взялась, печаль?

					Дом стоит, свет горит, из окна видна даль.
					Так откуда взялась, печаль?
					И вроде, жив и здоров, и вроде жить, не тужить.
					Так откуда взялась, печаль?
					""";

				List<String> actual = StringRegexp.findDigitHexadecimal(value);

				assertThat(actual).isEmpty();
			}

			/**
			 * Проверка метода {@link StringRegexp#findDigitHexadecimal(String)} с несколькими корректными значениями.
			 */
			@Test
			public void findDigitHexadecimalWithMultipleValue()
			{
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

				List<String> expected = List.of(
					"0103f1A9b720d1", "1160de2B15", "17024cA103d1", "B010A210cde3945f67101");

				List<String> actual = StringRegexp.findDigitHexadecimal(value);

				assertThat(actual).containsExactlyElementsOf(expected);
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#findLocalization(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class FindLocalization
		{
			/**
			 * Проверка метода {@link StringRegexp#findLocalization(String)} без корректных значений.
			 */
			@Test
			public void findLocalizationWithZeroValue()
			{
				String value = """
					Застоялся мой поезд в депо.
					Снова я уезжаю. Пора...
					На пороге ветер заждался меня.
					На пороге осень — моя сестра.

					После красно-желтых дней начнется и кончится зима.
					Горе ты мое от ума, не печалься, гляди веселей.
					И я вернусь домой со щитом, а, может быть, на щите,
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
					Я проснулся и понял — беда...

					После красно-желтых дней начнется и кончится зима.
					Горе ты мое от ума, не печалься, гляди веселей.
					И я вернусь домой со щитом, а, может быть, на щите,
					В серебре, а, может быть, в нищете, но как можно скорей.
					""";

				List<String> actual = StringRegexp.findLocalization(value);

				assertThat(actual).isEmpty();
			}

			/**
			 * Проверка метода {@link StringRegexp#findLocalization(String)} с несколькими корректными значениями.
			 */
			@Test
			public void findLocalizationWithMultipleValue()
			{
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

				List<String> expected = List.of("ru-RU", "en-EN", "by-BY", "it-IT");

				List<String> actual = StringRegexp.findLocalization(value);

				assertThat(actual).containsExactlyElementsOf(expected);
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#findDigitDecimal(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class FindDigitDecimal
		{
			/**
			 * Проверка метода {@link StringRegexp#findDigitDecimal(String)} без корректных значений.
			 */
			@Test
			public void findDigitDecimalWithZeroValue()
			{
				String value = """
					Ты часто проходишь мимо, не видя меня,
					С кем-то другим, я стою не дыша.
					Я знаю, что ты живешь в соседнем дворе,
					Ты идешь не спеша, не спеша...

					О, но это не любовь...

					А вечером я стою под твоим окном,
					Ты поливаешь цветы, поливаешь цветы.
					А я дотемна стою и сгораю огнем,
					И виной тому ты, только ты...

					О, но это не любовь...

					Научи меня всему тому, что умеешь ты,
					Я хочу это знать и уметь.
					Сделай так, чтобы сбылись все мои мечты,
					Мне нельзя больше ждать, я могу умереть...

					О, но это не любовь...
					""";

				List<String> actual = StringRegexp.findDigitDecimal(value);

				assertThat(actual).isEmpty();
			}

			/**
			 * Проверка метода {@link StringRegexp#findDigitDecimal(String)} с несколькими корректными значениями.
			 */
			@Test
			public void findDigitDecimalWithMultipleValue()
			{
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

				List<String> expected = List.of("0", "12", "123", "0123456789");

				List<String> actual = StringRegexp.findDigitDecimal(value);

				assertThat(actual).containsExactlyElementsOf(expected);
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#findNumber(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class FindNumber
		{
			/**
			 * Проверка метода {@link StringRegexp#findNumber(String)} без корректных значений.
			 */
			@Test
			public void findNumberWithZeroValue()
			{
				String value = """
					Ночь коротка, цель далека;
					Ночью так часто хочется пить,
					Ты выходишь на кухню, но вода здесь горька;
					Ты не можешь здесь спать, ты не хочешь здесь жить.

					Доброе утро, последний герой!
					Доброе утро, — тебе, и таким, как ты!
					Доброе утро, последний герой!
					Здравствуй, последний герой!

					Ты хотел быть один — это быстро прошло,
					Ты хотел быть один, но не смог быть один.
					Твоя ноша легка, но немеет рука;
					И ты встречаешь рассвет за игрой в «Дурака».

					Доброе утро, последний герой!
					Доброе утро, тебе, и таким, как ты!
					Доброе утро, последний герой!
					Здравствуй, последний герой!

					Утром ты стремишься скорее уйти,
					Телефонный звонок, как команда «Вперёд!»
					Ты уходишь туда, куда не хочешь идти;
					Ты уходишь туда, но тебя там никто не ждёт!

					Доброе утро, последний герой!
					Доброе утро, — тебе, и таким, как ты!
					Доброе утро, последний герой!
					Здравствуй, последний герой!
					""";

				List<String> actual = StringRegexp.findNumber(value);

				assertThat(actual).isEmpty();
			}

			/**
			 * Проверка метода {@link StringRegexp#findNumber(String)} с несколькими корректными значениями.
			 */
			@Test
			public void findNumberWithMultipleValue()
			{
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

				List<String> expected = List.of("1.100", "0,1", "1,000,00", "1.222,123");

				List<String> actual = StringRegexp.findNumber(value);

				assertThat(actual).containsExactlyElementsOf(expected);
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#findWord(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class FindWord
		{
			/**
			 * Проверка метода {@link StringRegexp#findWord(String)} без корректных значений.
			 */
			@Test
			public void findWordWithZeroValue()
			{
				String value = """
					0101 .0 13 .0
					64*$#83 --
					""";

				List<String> actual = StringRegexp.findWord(value);

				assertThat(actual).isEmpty();
			}

			/**
			 * Проверка метода {@link StringRegexp#findWord(String)} с несколькими корректными значениями.
			 */
			@Test
			public void findWordWithMultipleValue()
			{
				String value = """
					Красно-жёлтые дни.

					Застоялся мой поезд в депо.
					Снова я уезжаю. Пора...
					На пороге ветер заждался меня.
					На пороге осень — моя сестра.
					""";

				List<String> expected = List.of("Красно-жёлтые", "дни", "Застоялся", "мой", "поезд", "в", "депо",
													"Снова", "я", "уезжаю", "Пора", "На", "пороге", "ветер", "заждался",
													"меня", "На", "пороге", "осень", "моя", "сестра"
				);

				List<String> actual = StringRegexp.findWord(value);

				assertThat(actual).containsExactlyElementsOf(expected);
			}
		}

		/**
		 * Класс проверки метода {@link StringRegexp#findLetter(String)}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class FindLetter
		{
			/**
			 * Проверка метода {@link StringRegexp#findLetter(String)} без корректных значений.
			 */
			@Test
			public void findLetterWithZeroValue()
			{
				String value = """
					937 45635 68234
					/*-+32 88 *^%3
					""";

				List<String> actual = StringRegexp.findLetter(value);

				assertThat(actual).isEmpty();
			}

			/**
			 * Проверка метода {@link StringRegexp#findLetter(String)} с несколькими корректными значениями.
			 */
			@Test
			public void findLetterWithMultipleValue()
			{
				String value = """
					Малыш
					""";

				List<String> expected = List.of("М", "а", "л", "ы", "ш");

				List<String> actual = StringRegexp.findLetter(value);

				assertThat(actual).containsExactlyElementsOf(expected);
			}
		}
	}
}