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

package dev.kalenchukov.string.regexp.types;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

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

		assertThat(actualGroup).isEqualTo("localization");
	}

	/**
	 * Проверка метода {@link Regexp#getPattern()}.
	 */
	@Test
	public void getPattern()
	{
		Regexp regexp = Regexp.LOCALIZATION;

		String actualPattern = regexp.getPattern();

		assertThat(actualPattern).isNotEmpty();
	}

	/**
	 * Класс проверки регулярного выражения констант перечисления {@link Regexp}.
	 *
	 * @author Алексей Каленчуков
	 */
	@Nested
	public class PatternRegexp
	{
		/**
		 * Класс проверки регулярного выражения константы перечисления {@link Regexp#UUID}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class UUID
		{
			/**
			 * Проверка регулярного выражения константы {@link Regexp#UUID} с корректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				// На различный регистр цифр
				"01234567-89ab-cdef-ABCD-EF0123456789",
				"01234567-89AB-CDEF-ABCD-EF0123456789",
				"01234567-89ab-cdef-abcd-ef0123456789",

				// На все минимальные и максимальные цифры
				"00000000-0000-0000-0000-000000000000",
				"FFFFFFFF-FFFF-FFFF-FFFF-FFFFFFFFFFFF"
			})
			public void uuidWithValid(final String value)
			{
				Pattern pattern = Pattern.compile(Regexp.UUID.getPattern(), Pattern.UNICODE_CASE);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();
			}

			/**
			 * Проверка регулярного выражения константы {@link Regexp#UUID} с некорректными значениями.
			 */
			@ParameterizedTest
			@ValueSource(strings = {
				// Недобор цифр в разных группах
				"0123456-89ab-cdef-ABCD-EF0123456789",
				"01234567-89a-cdef-ABCD-EF0123456789",
				"01234567-89ab-cde-ABCD-EF0123456789",
				"01234567-89ab-cdef-ABC-EF0123456789",
				"01234567-89ab-cdef-ABCD-EF012345678",

				// Перебор цифр в разных группах
				"012345678-89AB-CDEF-ABCD-EF0123456789",
				"01234567-89AB0-CDEF-ABCD-EF0123456789",
				"01234567-89AB-CDEF0-ABCD-EF0123456789",
				"01234567-89AB-CDEF-ABCD0-EF0123456789",
				"01234567-89AB-CDEF-ABCD-EF01234567890",

				// Посторонние символы в разных группах
				"0123.567-89ab-cdef-abcd-ef0123456789",
				"01234567-89.b-cdef-abcd-ef0123456789",
				"01234567-89ab-cd.f-abcd-ef0123456789",
				"01234567-89ab-cdef-ab.d-ef0123456789",
				"01234567-89ab-cdef-abcd-ef.123456789",

				// Разделитель некорректный в разных группах
				"01234567_89ab-cdef-ABCD-EF0123456789",
				"01234567-89ab_cdef-ABCD-EF0123456789",
				"01234567-89ab-cdef_ABCD-EF0123456789",
				"01234567-89ab-cdef-ABCD_EF0123456789"

			})
			public void uuidWithInvalid(final String value)
			{
				Pattern pattern = Pattern.compile(Regexp.UUID.getPattern(), Pattern.UNICODE_CASE);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isFalse();
			}

			/**
			 * Проверка основной группы регулярного выражения константы {@link Regexp#UUID}.
			 */
			@Test
			public void uuidWithGroup()
			{
				String value = "01234567-89ab-cdef-ABCD-EF0123456789";
				Pattern pattern = Pattern.compile(Regexp.UUID.getPattern(), Pattern.UNICODE_CASE);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup = matcher.group("uuid");

				assertThat(actualGroup).isEqualTo(value);
			}

			/**
			 * Проверка разделителя регулярного выражения константы {@link Regexp#UUID}.
			 */
			@Test
			public void uuidWithSeparator()
			{
				String value = "01234567-89ab-cdef-ABCD-EF0123456789";
				Pattern pattern = Pattern.compile(Regexp.UUID.getPattern(), Pattern.UNICODE_CASE);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualSeparator = matcher.group("separator");

				assertThat(actualSeparator).isEqualTo("-");
			}

			/**
			 * Проверка первой группы регулярного выражения константы {@link Regexp#UUID}.
			 */
			@Test
			public void uuidWithGroup1()
			{
				String value = "01234567-89ab-cdef-ABCD-EF0123456789";
				Pattern pattern = Pattern.compile(Regexp.UUID.getPattern(), Pattern.UNICODE_CASE);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup1 = matcher.group("group1");

				assertThat(actualGroup1).isEqualTo("01234567");
			}

			/**
			 * Проверка второй группы регулярного выражения константы {@link Regexp#UUID}.
			 */
			@Test
			public void uuidWithGroup2()
			{
				String value = "01234567-89ab-cdef-ABCD-EF0123456789";
				Pattern pattern = Pattern.compile(Regexp.UUID.getPattern(), Pattern.UNICODE_CASE);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup2 = matcher.group("group2");

				assertThat(actualGroup2).isEqualTo("89ab");
			}

			/**
			 * Проверка третьей группы регулярного выражения константы {@link Regexp#UUID}.
			 */
			@Test
			public void uuidWithGroup3()
			{
				String value = "01234567-89ab-cdef-ABCD-EF0123456789";
				Pattern pattern = Pattern.compile(Regexp.UUID.getPattern(), Pattern.UNICODE_CASE);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup3 = matcher.group("group3");

				assertThat(actualGroup3).isEqualTo("cdef");
			}

			/**
			 * Проверка четвёртой группы регулярного выражения константы {@link Regexp#UUID}.
			 */
			@Test
			public void uuidWithGroup4()
			{
				String value = "01234567-89ab-cdef-ABCD-EF0123456789";
				Pattern pattern = Pattern.compile(Regexp.UUID.getPattern(), Pattern.UNICODE_CASE);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup4 = matcher.group("group4");

				assertThat(actualGroup4).isEqualTo("ABCD");
			}

			/**
			 * Проверка пятой группы регулярного выражения константы {@link Regexp#UUID}.
			 */
			@Test
			public void uuidWithGroup5()
			{
				String value = "01234567-89ab-cdef-ABCD-EF0123456789";
				Pattern pattern = Pattern.compile(Regexp.UUID.getPattern(), Pattern.UNICODE_CASE);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup5 = matcher.group("group5");

				assertThat(actualGroup5).isEqualTo("EF0123456789");
			}
		}

		/**
		 * Проверка регулярного выражения константы {@link Regexp#LOCALIZATION}.
		 */
		@Test
		public void localization()
		{
			String value = "ru-RU";
			Pattern pattern = Pattern.compile(Regexp.LOCALIZATION.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);
			assertThat(matcher.matches()).isTrue();

			String actualGroup1 = matcher.group("localization");
			String actualGroup2 = matcher.group("language");
			String actualGroup3 = matcher.group("country");

			assertThat(actualGroup1).isEqualTo("ru-RU");
			assertThat(actualGroup2).isEqualTo("ru");
			assertThat(actualGroup3).isEqualTo("RU");
		}

		/**
		 * Проверка константы {@link Regexp#LOCALIZATION} с некорректным значением.
		 */
		@Test
		public void localizationWithValueInvalid()
		{
			String value = "ru/RU";
			Pattern pattern = Pattern.compile(Regexp.LOCALIZATION.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);

			boolean actual = matcher.matches();

			assertThat(actual).isFalse();
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
			assertThat(matcher.matches()).isTrue();

			String actualGroup1 = matcher.group("inet4Address");
			String actualGroup2 = matcher.group("part1");
			String actualGroup3 = matcher.group("part2");
			String actualGroup4 = matcher.group("part3");
			String actualGroup5 = matcher.group("part4");

			assertThat(actualGroup1).isEqualTo("192.168.1.100");
			assertThat(actualGroup2).isEqualTo("192");
			assertThat(actualGroup3).isEqualTo("168");
			assertThat(actualGroup4).isEqualTo("1");
			assertThat(actualGroup5).isEqualTo("100");
		}

		/**
		 * Проверка константы {@link Regexp#INET_4_ADDRESS} с некорректным значением.
		 */
		@Test
		public void inet4AddressWithValueInvalid()
		{
			String value = "f645654y45t34vtfd4";
			Pattern pattern = Pattern.compile(Regexp.INET_4_ADDRESS.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);

			boolean actual = matcher.matches();

			assertThat(actual).isFalse();
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
			assertThat(matcher.matches()).isTrue();

			String actualGroup = matcher.group("inet6Address");

			assertThat(actualGroup).isEqualTo("2001:0DB8:11A3:09D7:1F34:8A2E:07A0:765D");
		}

		/**
		 * Проверка константы {@link Regexp#INET_6_ADDRESS} с некорректным значением.
		 */
		@Test
		public void inet6AddressWithValueInvalid()
		{
			String value = "vy45yg45tyf45t4f5";
			Pattern pattern = Pattern.compile(Regexp.INET_6_ADDRESS.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);

			boolean actual = matcher.matches();

			assertThat(actual).isFalse();
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
			assertThat(matcher.matches()).isTrue();

			String actualGroup1 = matcher.group("emailAddress");
			String actualGroup2 = matcher.group("local");
			String actualGroup3 = matcher.group("domain");
			String actualGroup4 = matcher.group("tld");

			assertThat(actualGroup1).isEqualTo("aleksey.kalenchukov@yandex.ru");
			assertThat(actualGroup2).isEqualTo("aleksey.kalenchukov");
			assertThat(actualGroup3).isEqualTo("yandex.ru");
			assertThat(actualGroup4).isEqualTo("ru");
		}

		/**
		 * Проверка константы {@link Regexp#EMAIL_ADDRESS} с некорректным значением.
		 */
		@Test
		public void emailAddressWithValueInvalid()
		{
			String value = "45f645g6y45hy45g5";
			Pattern pattern = Pattern.compile(Regexp.EMAIL_ADDRESS.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);

			boolean actual = matcher.matches();

			assertThat(actual).isFalse();
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
			assertThat(matcher.matches()).isTrue();

			String actualGroup = matcher.group("md5");

			assertThat(actualGroup).isEqualTo("1BC29B36F623BA82AAF6724FD3B16718");
		}

		/**
		 * Проверка константы {@link Regexp#MD5} с некорректным значением.
		 */
		@Test
		public void md5WithValueInvalid()
		{
			String value = "34546457546nhgewrxs";
			Pattern pattern = Pattern.compile(Regexp.MD5.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);

			boolean actual = matcher.matches();

			assertThat(actual).isFalse();
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
			assertThat(matcher.matches()).isTrue();

			String actualGroup1 = matcher.group("domain");
			String actualGroup2 = matcher.group("tld");

			assertThat(actualGroup1).isEqualTo("kalenchukov.dev");
			assertThat(actualGroup2).isEqualTo("dev");
		}

		/**
		 * Проверка константы {@link Regexp#DOMAIN} с некорректным значением.
		 */
		@Test
		public void domainWithValueInvalid()
		{
			String value = "54gy657h65u76";
			Pattern pattern = Pattern.compile(Regexp.DOMAIN.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);

			boolean actual = matcher.matches();

			assertThat(actual).isFalse();
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
			assertThat(matcher.matches()).isTrue();

			String actualGroup1 = matcher.group("url");
			String actualGroup2 = matcher.group("protocol");
			String actualGroup3 = matcher.group("domain");
			String actualGroup4 = matcher.group("tld");
			String actualGroup5 = matcher.group("path");
			String actualGroup6 = matcher.group("param");
			String actualGroup7 = matcher.group("anchor");

			assertThat(actualGroup1).isEqualTo("http://www.kalenchukov.dev/hello/world/?java=18&hello=world123#anchor");
			assertThat(actualGroup2).isEqualTo("http");
			assertThat(actualGroup3).isEqualTo("kalenchukov.dev");
			assertThat(actualGroup4).isEqualTo("dev");
			assertThat(actualGroup5).isEqualTo("hello/world/");
			assertThat(actualGroup6).isEqualTo("java=18&hello=world123");
			assertThat(actualGroup7).isEqualTo("anchor");
		}

		/**
		 * Проверка константы {@link Regexp#URL_HTTP} с некорректным значением.
		 */
		@Test
		public void urlHttpWithValueInvalid()
		{
			String value = "://www.kalenchukov.dev/h";
			Pattern pattern = Pattern.compile(Regexp.URL_HTTP.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);

			boolean actual = matcher.matches();

			assertThat(actual).isFalse();
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
			assertThat(matcher.matches()).isTrue();

			String actualGroup1 = matcher.group("rgb");
			String actualGroup2 = matcher.group("red");
			String actualGroup3 = matcher.group("green");
			String actualGroup4 = matcher.group("blue");

			assertThat(actualGroup1).isEqualTo("215,200,166");
			assertThat(actualGroup2).isEqualTo("215");
			assertThat(actualGroup3).isEqualTo("200");
			assertThat(actualGroup4).isEqualTo("166");
		}

		/**
		 * Проверка константы {@link Regexp#RGB_NUMERIC} с некорректным значением.
		 */
		@Test
		public void rgbNumericWithValueInvalid()
		{
			String value = "45g657h65uj";
			Pattern pattern = Pattern.compile(Regexp.RGB_NUMERIC.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);

			boolean actual = matcher.matches();

			assertThat(actual).isFalse();
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
			assertThat(matcher.matches()).isTrue();

			String actualGroup1 = matcher.group("rgb");
			String actualGroup2 = matcher.group("red");
			String actualGroup3 = matcher.group("green");
			String actualGroup4 = matcher.group("blue");

			assertThat(actualGroup1).isEqualTo("#ABCDEF");
			assertThat(actualGroup2).isEqualTo("AB");
			assertThat(actualGroup3).isEqualTo("CD");
			assertThat(actualGroup4).isEqualTo("EF");
		}

		/**
		 * Проверка константы {@link Regexp#RGB_HEX} с некорректным значением.
		 */
		@Test
		public void rgbHexWithValueInvalid()
		{
			String value = "45y65y65";
			Pattern pattern = Pattern.compile(Regexp.RGB_HEX.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);

			boolean actual = matcher.matches();

			assertThat(actual).isFalse();
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
			assertThat(matcher.matches()).isTrue();

			String actualGroup1 = matcher.group("macAddress");
			String actualGroup2 = matcher.group("separator");
			String actualGroup3 = matcher.group("part1");
			String actualGroup4 = matcher.group("part2");
			String actualGroup5 = matcher.group("part3");
			String actualGroup6 = matcher.group("part4");
			String actualGroup7 = matcher.group("part5");
			String actualGroup8 = matcher.group("part6");

			assertThat(actualGroup1).isEqualTo("00-eF-cd-Ef-11-22");
			assertThat(actualGroup2).isEqualTo("-");
			assertThat(actualGroup3).isEqualTo("00");
			assertThat(actualGroup4).isEqualTo("eF");
			assertThat(actualGroup5).isEqualTo("cd");
			assertThat(actualGroup6).isEqualTo("Ef");
			assertThat(actualGroup7).isEqualTo("11");
			assertThat(actualGroup8).isEqualTo("22");
		}

		/**
		 * Проверка константы {@link Regexp#MAC_ADDRESS} с некорректным значением.
		 */
		@Test
		public void macAddressWithValueInvalid()
		{
			String value = "00-eF-cdEf1122";
			Pattern pattern = Pattern.compile(Regexp.MAC_ADDRESS.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);

			boolean actual = matcher.matches();

			assertThat(actual).isFalse();
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
			assertThat(matcher.matches()).isTrue();

			String actualGroup1 = matcher.group("telegram");
			String actualGroup2 = matcher.group("name");

			assertThat(actualGroup1).isEqualTo("@kalenchukov");
			assertThat(actualGroup2).isEqualTo("kalenchukov");
		}

		/**
		 * Проверка константы {@link Regexp#TELEGRAM} с некорректным значением.
		 */
		@Test
		public void telegramWithValueInvalid()
		{
			String value = "45f645yg65y";
			Pattern pattern = Pattern.compile(Regexp.TELEGRAM.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);

			boolean actual = matcher.matches();

			assertThat(actual).isFalse();
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
			assertThat(matcher.matches()).isTrue();

			String actualGroup1 = matcher.group("tag");
			String actualGroup2 = matcher.group("name");

			assertThat(actualGroup1).isEqualTo("#tag");
			assertThat(actualGroup2).isEqualTo("tag");
		}

		/**
		 * Проверка константы {@link Regexp#TAG} с некорректным значением.
		 */
		@Test
		public void tagWithValueInvalid()
		{
			String value = "4v5y4vt";
			Pattern pattern = Pattern.compile(Regexp.TAG.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);

			boolean actual = matcher.matches();

			assertThat(actual).isFalse();
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
			assertThat(matcher.matches()).isTrue();

			String actualGroup = matcher.group("digit");

			assertThat(actualGroup).isEqualTo("0101010101");
		}

		/**
		 * Проверка константы {@link Regexp#DIGIT_BINARY} с некорректным значением.
		 */
		@Test
		public void digitBinaryWithValueInvalid()
		{
			String value = "4f34tf45t45";
			Pattern pattern = Pattern.compile(Regexp.DIGIT_BINARY.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);

			boolean actual = matcher.matches();

			assertThat(actual).isFalse();
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
			assertThat(matcher.matches()).isTrue();

			String actualGroup = matcher.group("digit");

			assertThat(actualGroup).isEqualTo("210210210210");
		}

		/**
		 * Проверка константы {@link Regexp#DIGIT_TERNARY} с некорректным значением.
		 */
		@Test
		public void digitTernaryWithValueInvalid()
		{
			String value = "4f6456f45y45dgf";
			Pattern pattern = Pattern.compile(Regexp.DIGIT_TERNARY.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);

			boolean actual = matcher.matches();

			assertThat(actual).isFalse();
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
			assertThat(matcher.matches()).isTrue();

			String actualGroup = matcher.group("digit");

			assertThat(actualGroup).isEqualTo("65016716501710651467106");
		}

		/**
		 * Проверка константы {@link Regexp#DIGIT_OCTAL} с некорректным значением.
		 */
		@Test
		public void digitOctalWithValueInvalid()
		{
			String value = "56vy56y46y545";
			Pattern pattern = Pattern.compile(Regexp.DIGIT_OCTAL.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);

			boolean actual = matcher.matches();

			assertThat(actual).isFalse();
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
			assertThat(matcher.matches()).isTrue();

			String actualGroup = matcher.group("digit");

			assertThat(actualGroup).isEqualTo("0123");
		}

		/**
		 * Проверка константы {@link Regexp#DIGIT_DECIMAL} с некорректным значением.
		 */
		@Test
		public void digitDecimalWithValueInvalid()
		{
			String value = "45f65434d34ffd";
			Pattern pattern = Pattern.compile(Regexp.DIGIT_DECIMAL.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);

			boolean actual = matcher.matches();

			assertThat(actual).isFalse();
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
			assertThat(matcher.matches()).isTrue();

			String actualGroup = matcher.group("digit");

			assertThat(actualGroup).isEqualTo("8819a861bB14Aab04ab519a2baA3b7a9B");
		}

		/**
		 * Проверка константы {@link Regexp#DIGIT_DUODECIMAL} с некорректным значением.
		 */
		@Test
		public void digitDuodecimalWithValueInvalid()
		{
			String value = "457N678H6734X3ZS2";
			Pattern pattern = Pattern.compile(Regexp.DIGIT_DUODECIMAL.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);

			boolean actual = matcher.matches();

			assertThat(actual).isFalse();
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
			assertThat(matcher.matches()).isTrue();

			String actualGroup = matcher.group("digit");

			assertThat(actualGroup).isEqualTo("0123456789ABCDEF");
		}

		/**
		 * Проверка константы {@link Regexp#DIGIT_HEXADECIMAL} с некорректным значением.
		 */
		@Test
		public void digitHexadecimalWithValueInvalid()
		{
			String value = "345F45Y56G5";
			Pattern pattern = Pattern.compile(Regexp.DIGIT_HEXADECIMAL.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);

			boolean actual = matcher.matches();

			assertThat(actual).isFalse();
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
			assertThat(matcher.matches()).isTrue();

			String actualGroup = matcher.group("number");

			assertThat(actualGroup).isEqualTo("1.100");
		}

		/**
		 * Проверка константы {@link Regexp#NUMBER} с некорректным значением.
		 */
		@Test
		public void numberWithValueInvalid()
		{
			String value = "asd";
			Pattern pattern = Pattern.compile(Regexp.NUMBER.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);

			boolean actual = matcher.matches();

			assertThat(actual).isFalse();
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
			assertThat(matcher.matches()).isTrue();

			String actualGroup = matcher.group("word");

			assertThat(actualGroup).isEqualTo("Привет");
		}

		/**
		 * Проверка константы {@link Regexp#WORD} с некорректным значением.
		 */
		@Test
		public void wordWithValueInvalid()
		{
			String value = "34546";
			Pattern pattern = Pattern.compile(Regexp.WORD.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);

			boolean actual = matcher.matches();

			assertThat(actual).isFalse();
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
			assertThat(matcher.matches()).isTrue();

			String actualGroup = matcher.group("letter");

			assertThat(actualGroup).isEqualTo("Ъ");
		}

		/**
		 * Проверка константы {@link Regexp#LETTER} с некорректным значением.
		 */
		@Test
		public void letterWithValueInvalid()
		{
			String value = "0";
			Pattern pattern = Pattern.compile(Regexp.LETTER.getPattern(), Pattern.UNICODE_CASE);
			Matcher matcher = pattern.matcher(value);

			boolean actual = matcher.matches();

			assertThat(actual).isFalse();
		}
	}
}
