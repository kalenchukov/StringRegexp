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

		String actual = regexp.getGroup();

		assertThat(actual).isEqualTo("localization");
	}

	/**
	 * Проверка метода {@link Regexp#getPattern()}.
	 */
	@Test
	public void getPattern()
	{
		Regexp regexp = Regexp.LOCALIZATION;

		String actual = regexp.getPattern();

		assertThat(actual).isNotEmpty();
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
			public void uuidWithValueValid(final String value)
			{
				Pattern pattern = Pattern.compile(Regexp.UUID.getPattern(), Pattern.UNICODE_CASE);
				Matcher matcher = pattern.matcher(value);

				boolean actual = matcher.matches();
				
				assertThat(actual).isTrue();
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
			public void uuidWithValueInvalid(final String value)
			{
				Pattern pattern = Pattern.compile(Regexp.UUID.getPattern(), Pattern.UNICODE_CASE);
				Matcher matcher = pattern.matcher(value);

				boolean actual = matcher.matches();
				
				assertThat(actual).isFalse();
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

				String actual = matcher.group("uuid");

				assertThat(actual).isEqualTo(value);
			}

			/**
			 * Проверка группы разделителя регулярного выражения константы {@link Regexp#UUID}.
			 */
			@Test
			public void uuidWithSeparator()
			{
				String value = "01234567-89ab-cdef-ABCD-EF0123456789";
				Pattern pattern = Pattern.compile(Regexp.UUID.getPattern(), Pattern.UNICODE_CASE);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actual = matcher.group("separator");

				assertThat(actual).isEqualTo("-");
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

				String actual = matcher.group("group1");

				assertThat(actual).isEqualTo("01234567");
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

				String actual = matcher.group("group2");

				assertThat(actual).isEqualTo("89ab");
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

				String actual = matcher.group("group3");

				assertThat(actual).isEqualTo("cdef");
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

				String actual = matcher.group("group4");

				assertThat(actual).isEqualTo("ABCD");
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

				String actual = matcher.group("group5");

				assertThat(actual).isEqualTo("EF0123456789");
			}
		}

		/**
		 * Класс проверки регулярного выражения константы перечисления {@link Regexp#LOCALIZATION}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class Localization
		{
			/**
			 * Проверка регулярного выражения константы {@link Regexp#LOCALIZATION} с корректными значениями.
			 */
			@Test
			public void localizationWithValueValid()
			{
				String value = "ru-RU";
				Pattern pattern = Pattern.compile(Regexp.LOCALIZATION.getPattern(), Pattern.UNICODE_CASE);
				Matcher matcher = pattern.matcher(value);

				boolean actual = matcher.matches();

				assertThat(actual).isTrue();
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
			 * Проверка основной группы регулярного выражения константы {@link Regexp#LOCALIZATION}.
			 */
			@Test
			public void localizationWithGroup()
			{
				String value = "ru-RU";
				Pattern pattern = Pattern.compile(Regexp.LOCALIZATION.getPattern(), Pattern.UNICODE_CASE);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actual = matcher.group("localization");

				assertThat(actual).isEqualTo("ru-RU");
			}

			/**
			 * Проверка группы языка регулярного выражения константы {@link Regexp#LOCALIZATION}.
			 */
			@Test
			public void localizationWithGroupLanguage()
			{
				String value = "ru-RU";
				Pattern pattern = Pattern.compile(Regexp.LOCALIZATION.getPattern(), Pattern.UNICODE_CASE);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actual = matcher.group("language");

				assertThat(actual).isEqualTo("ru");
			}

			/**
			 * Проверка группы страны регулярного выражения константы {@link Regexp#LOCALIZATION}.
			 */
			@Test
			public void localizationWithGroupCountry()
			{
				String value = "ru-RU";
				Pattern pattern = Pattern.compile(Regexp.LOCALIZATION.getPattern(), Pattern.UNICODE_CASE);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actual = matcher.group("country");

				assertThat(actual).isEqualTo("RU");
			}
		}

		/**
		 * Класс проверки регулярного выражения константы перечисления {@link Regexp#INET_4_ADDRESS}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class Inet4Address
		{
			/**
			 * Проверка регулярного выражения константы {@link Regexp#INET_4_ADDRESS} с корректными значениями.
			 */
			@Test
			public void inet4AddressWithValueValid()
			{
				String value = "192.168.1.100";
				Pattern pattern = Pattern.compile(Regexp.INET_4_ADDRESS.getPattern(), Pattern.UNICODE_CASE);
				Matcher matcher = pattern.matcher(value);

				boolean actual = matcher.matches();

				assertThat(actual).isTrue();
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
			 * Проверка основной группы регулярного выражения константы {@link Regexp#INET_4_ADDRESS}.
			 */
			@Test
			public void inet4AddressWithGroup()
			{
				String value = "192.168.1.100";
				Pattern pattern = Pattern.compile(Regexp.INET_4_ADDRESS.getPattern(), Pattern.UNICODE_CASE);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actual = matcher.group("inet4Address");

				assertThat(actual).isEqualTo("192.168.1.100");
			}


			/**
			 * Проверка группы первой части регулярного выражения константы {@link Regexp#INET_4_ADDRESS}.
			 */
			@Test
			public void inet4AddressWithGroupPart1()
			{
				String value = "192.168.1.100";
				Pattern pattern = Pattern.compile(Regexp.INET_4_ADDRESS.getPattern(), Pattern.UNICODE_CASE);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actual = matcher.group("part1");

				assertThat(actual).isEqualTo("192");
			}

			/**
			 * Проверка группы второй части регулярного выражения константы {@link Regexp#INET_4_ADDRESS}.
			 */
			@Test
			public void inet4AddressWithGroupPart2()
			{
				String value = "192.168.1.100";
				Pattern pattern = Pattern.compile(Regexp.INET_4_ADDRESS.getPattern(), Pattern.UNICODE_CASE);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actual = matcher.group("part2");

				assertThat(actual).isEqualTo("168");
			}

			/**
			 * Проверка группы третьей части регулярного выражения константы {@link Regexp#INET_4_ADDRESS}.
			 */
			@Test
			public void inet4AddressWithGroupPart3()
			{
				String value = "192.168.1.100";
				Pattern pattern = Pattern.compile(Regexp.INET_4_ADDRESS.getPattern(), Pattern.UNICODE_CASE);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actual = matcher.group("part3");

				assertThat(actual).isEqualTo("1");
			}

			/**
			 * Проверка группы четвёртой части регулярного выражения константы {@link Regexp#INET_4_ADDRESS}.
			 */
			@Test
			public void inet4AddressWithGroupPart4()
			{
				String value = "192.168.1.100";
				Pattern pattern = Pattern.compile(Regexp.INET_4_ADDRESS.getPattern(), Pattern.UNICODE_CASE);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actual = matcher.group("part4");

				assertThat(actual).isEqualTo("100");
			}
		}

		/**
		 * Класс проверки регулярного выражения константы перечисления {@link Regexp#INET_6_ADDRESS}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class Inet6Address
		{
			/**
			 * Проверка регулярного выражения константы {@link Regexp#INET_6_ADDRESS} с корректными значениями.
			 */
			@Test
			public void inet6AddressWithValueValid()
			{
				String value = "2001:0DB8:11A3:09D7:1F34:8A2E:07A0:765D";
				Pattern pattern = Pattern.compile(
					Regexp.INET_6_ADDRESS.getPattern(),
					Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
				);
				Matcher matcher = pattern.matcher(value);

				boolean actual = matcher.matches();

				assertThat(actual).isTrue();
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
			 * Проверка основной группы регулярного выражения константы {@link Regexp#INET_6_ADDRESS}.
			 */
			@Test
			public void inet6AddressWithGroup()
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
		}

		/**
		 * Класс проверки регулярного выражения константы перечисления {@link Regexp#EMAIL_ADDRESS}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class EmailAddress
		{
			/**
			 * Проверка регулярного выражения константы {@link Regexp#EMAIL_ADDRESS} с корректными значениями.
			 */
			@Test
			public void emailAddressWithValueValid()
			{
				String value = "aleksey.kalenchukov@yandex.ru";
				Pattern pattern = Pattern.compile(
					Regexp.EMAIL_ADDRESS.getPattern(),
					Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
				);
				Matcher matcher = pattern.matcher(value);

				boolean actual = matcher.matches();

				assertThat(actual).isTrue();
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
			 * Проверка основной группы регулярного выражения константы {@link Regexp#EMAIL_ADDRESS}.
			 */
			@Test
			public void emailAddressWithGroup()
			{
				String value = "aleksey.kalenchukov@yandex.ru";
				Pattern pattern = Pattern.compile(
					Regexp.EMAIL_ADDRESS.getPattern(),
					Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
				);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup1 = matcher.group("emailAddress");

				assertThat(actualGroup1).isEqualTo("aleksey.kalenchukov@yandex.ru");
			}

			/**
			 * Проверка группы имени регулярного выражения константы {@link Regexp#EMAIL_ADDRESS}.
			 */
			@Test
			public void emailAddressWithGroupLocal()
			{
				String value = "aleksey.kalenchukov@yandex.ru";
				Pattern pattern = Pattern.compile(
					Regexp.EMAIL_ADDRESS.getPattern(),
					Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
				);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup2 = matcher.group("local");

				assertThat(actualGroup2).isEqualTo("aleksey.kalenchukov");
			}

			/**
			 * Проверка группы домена регулярного выражения константы {@link Regexp#EMAIL_ADDRESS}.
			 */
			@Test
			public void emailAddressWithGroupDomain()
			{
				String value = "aleksey.kalenchukov@yandex.ru";
				Pattern pattern = Pattern.compile(
					Regexp.EMAIL_ADDRESS.getPattern(),
					Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
				);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup3 = matcher.group("domain");

				assertThat(actualGroup3).isEqualTo("yandex.ru");
			}

			/**
			 * Проверка группы TLD регулярного выражения константы {@link Regexp#EMAIL_ADDRESS}.
			 */
			@Test
			public void emailAddressWithGroupTld()
			{
				String value = "aleksey.kalenchukov@yandex.ru";
				Pattern pattern = Pattern.compile(
					Regexp.EMAIL_ADDRESS.getPattern(),
					Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
				);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup4 = matcher.group("tld");

				assertThat(actualGroup4).isEqualTo("ru");
			}
		}

		/**
		 * Класс проверки регулярного выражения константы перечисления {@link Regexp#MD5}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class Md5
		{
			/**
			 * Проверка регулярного выражения константы {@link Regexp#MD5} с корректными значениями.
			 */
			@Test
			public void md5WithValueValid()
			{
				String value = "1BC29B36F623BA82AAF6724FD3B16718";
				Pattern pattern = Pattern.compile(
					Regexp.MD5.getPattern(),
					Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
				);
				Matcher matcher = pattern.matcher(value);

				boolean actual = matcher.matches();

				assertThat(actual).isTrue();
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
			 * Проверка основной группы регулярного выражения константы {@link Regexp#MD5}.
			 */
			@Test
			public void md5WithGroup()
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
		}

		/**
		 * Класс проверки регулярного выражения константы перечисления {@link Regexp#DOMAIN}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class Domain
		{
			/**
			 * Проверка регулярного выражения константы {@link Regexp#DOMAIN} с корректными значениями.
			 */
			@Test
			public void domainWithValueValid()
			{
				String value = "kalenchukov.dev";
				Pattern pattern = Pattern.compile(
					Regexp.DOMAIN.getPattern(),
					Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
				);
				Matcher matcher = pattern.matcher(value);

				boolean actual = matcher.matches();

				assertThat(actual).isTrue();
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
			 * Проверка основной группы регулярного выражения константы {@link Regexp#DOMAIN}.
			 */
			@Test
			public void domainWithGroup()
			{
				String value = "kalenchukov.dev";
				Pattern pattern = Pattern.compile(
					Regexp.DOMAIN.getPattern(),
					Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
				);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup1 = matcher.group("domain");

				assertThat(actualGroup1).isEqualTo("kalenchukov.dev");
			}

			/**
			 * Проверка группы названия регулярного выражения константы {@link Regexp#DOMAIN}.
			 */
			@Test
			public void domainWithGroupName()
			{
				String value = "kalenchukov.dev";
				Pattern pattern = Pattern.compile(
					Regexp.DOMAIN.getPattern(),
					Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
				);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup2 = matcher.group("name");

				assertThat(actualGroup2).isEqualTo("kalenchukov");
			}

			/**
			 * Проверка группы TLD регулярного выражения константы {@link Regexp#DOMAIN}.
			 */
			@Test
			public void domainWithGroupTld()
			{
				String value = "kalenchukov.dev";
				Pattern pattern = Pattern.compile(
					Regexp.DOMAIN.getPattern(),
					Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
				);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup2 = matcher.group("tld");

				assertThat(actualGroup2).isEqualTo("dev");
			}
		}

		/**
		 * Класс проверки регулярного выражения константы перечисления {@link Regexp#URL_HTTP}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class UrlHttp
		{
			/**
			 * Проверка регулярного выражения константы {@link Regexp#URL_HTTP} с корректными значениями.
			 */
			@Test
			public void urlHttpWithValueValid()
			{
				String value = "http://www.kalenchukov.dev/hello/world/?java=18&hello=world123#anchor";
				Pattern pattern = Pattern.compile(
					Regexp.URL_HTTP.getPattern(),
					Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
				);
				Matcher matcher = pattern.matcher(value);

				boolean actual = matcher.matches();

				assertThat(actual).isTrue();
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
			 * Проверка основной группы регулярного выражения константы {@link Regexp#URL_HTTP}.
			 */
			@Test
			public void urlHttpWithGroup()
			{
				String value = "http://www.kalenchukov.dev/hello/world/?java=18&hello=world123#anchor";
				Pattern pattern = Pattern.compile(
					Regexp.URL_HTTP.getPattern(),
					Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
				);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup1 = matcher.group("url");

				assertThat(actualGroup1).isEqualTo("http://www.kalenchukov.dev/hello/world/?java=18&hello=world123#anchor");
			}

			/**
			 * Проверка группы протокола регулярного выражения константы {@link Regexp#URL_HTTP}.
			 */
			@Test
			public void urlHttpWithGroupProtocol()
			{
				String value = "http://www.kalenchukov.dev/hello/world/?java=18&hello=world123#anchor";
				Pattern pattern = Pattern.compile(
					Regexp.URL_HTTP.getPattern(),
					Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
				);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup2 = matcher.group("protocol");

				assertThat(actualGroup2).isEqualTo("http");
			}

			/**
			 * Проверка группы домена регулярного выражения константы {@link Regexp#URL_HTTP}.
			 */
			@Test
			public void urlHttpWithGroupDomain()
			{
				String value = "http://www.kalenchukov.dev/hello/world/?java=18&hello=world123#anchor";
				Pattern pattern = Pattern.compile(
					Regexp.URL_HTTP.getPattern(),
					Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
				);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup3 = matcher.group("domain");

				assertThat(actualGroup3).isEqualTo("kalenchukov.dev");
			}

			/**
			 * Проверка группы TLD регулярного выражения константы {@link Regexp#URL_HTTP}.
			 */
			@Test
			public void urlHttpWithGroupTld()
			{
				String value = "http://www.kalenchukov.dev/hello/world/?java=18&hello=world123#anchor";
				Pattern pattern = Pattern.compile(
					Regexp.URL_HTTP.getPattern(),
					Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
				);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup4 = matcher.group("tld");

				assertThat(actualGroup4).isEqualTo("dev");
			}

			/**
			 * Проверка группы пути регулярного выражения константы {@link Regexp#URL_HTTP}.
			 */
			@Test
			public void urlHttpWithGroupPath()
			{
				String value = "http://www.kalenchukov.dev/hello/world/?java=18&hello=world123#anchor";
				Pattern pattern = Pattern.compile(
					Regexp.URL_HTTP.getPattern(),
					Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
				);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup5 = matcher.group("path");

				assertThat(actualGroup5).isEqualTo("hello/world/");
			}

			/**
			 * Проверка группы параметра регулярного выражения константы {@link Regexp#URL_HTTP}.
			 */
			@Test
			public void urlHttpWithGroupParam()
			{
				String value = "http://www.kalenchukov.dev/hello/world/?java=18&hello=world123#anchor";
				Pattern pattern = Pattern.compile(
					Regexp.URL_HTTP.getPattern(),
					Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
				);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup6 = matcher.group("param");

				assertThat(actualGroup6).isEqualTo("java=18&hello=world123");
			}

			/**
			 * Проверка группы якоря регулярного выражения константы {@link Regexp#URL_HTTP}.
			 */
			@Test
			public void urlHttpWithGroupAnchor()
			{
				String value = "http://www.kalenchukov.dev/hello/world/?java=18&hello=world123#anchor";
				Pattern pattern = Pattern.compile(
					Regexp.URL_HTTP.getPattern(),
					Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
				);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup7 = matcher.group("anchor");

				assertThat(actualGroup7).isEqualTo("anchor");
			}
		}

		/**
		 * Класс проверки регулярного выражения константы перечисления {@link Regexp#RGB_NUMERIC}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class RgbNumeric
		{
			/**
			 * Проверка регулярного выражения константы {@link Regexp#RGB_NUMERIC} с корректными значениями.
			 */
			@Test
			public void rgbNumericWithValueValid()
			{
				String value = "215,200,166";
				Pattern pattern = Pattern.compile(Regexp.RGB_NUMERIC.getPattern(), Pattern.UNICODE_CASE);
				Matcher matcher = pattern.matcher(value);

				boolean actual = matcher.matches();

				assertThat(actual).isTrue();
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
			 * Проверка основной группы регулярного выражения константы {@link Regexp#RGB_NUMERIC}.
			 */
			@Test
			public void rgbNumericWithGroup()
			{
				String value = "215,200,166";
				Pattern pattern = Pattern.compile(Regexp.RGB_NUMERIC.getPattern(), Pattern.UNICODE_CASE);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup1 = matcher.group("rgb");

				assertThat(actualGroup1).isEqualTo("215,200,166");
			}

			/**
			 * Проверка группы красного цвета регулярного выражения константы {@link Regexp#RGB_NUMERIC}.
			 */
			@Test
			public void rgbNumericWithGroupRed()
			{
				String value = "215,200,166";
				Pattern pattern = Pattern.compile(Regexp.RGB_NUMERIC.getPattern(), Pattern.UNICODE_CASE);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup2 = matcher.group("red");

				assertThat(actualGroup2).isEqualTo("215");
			}

			/**
			 * Проверка группы зелёного цвета регулярного выражения константы {@link Regexp#RGB_NUMERIC}.
			 */
			@Test
			public void rgbNumericWithGroupGreen()
			{
				String value = "215,200,166";
				Pattern pattern = Pattern.compile(Regexp.RGB_NUMERIC.getPattern(), Pattern.UNICODE_CASE);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup3 = matcher.group("green");

				assertThat(actualGroup3).isEqualTo("200");
			}

			/**
			 * Проверка группы голубого цвета регулярного выражения константы {@link Regexp#RGB_NUMERIC}.
			 */
			@Test
			public void rgbNumericWithGroupBlue()
			{
				String value = "215,200,166";
				Pattern pattern = Pattern.compile(Regexp.RGB_NUMERIC.getPattern(), Pattern.UNICODE_CASE);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup4 = matcher.group("blue");

				assertThat(actualGroup4).isEqualTo("166");
			}
		}

		/**
		 * Класс проверки регулярного выражения константы перечисления {@link Regexp#RGB_HEX}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class RgbHex
		{
			/**
			 * Проверка регулярного выражения константы {@link Regexp#RGB_HEX} с корректными значениями.
			 */
			@Test
			public void rgbHexWithValueValid()
			{
				String value = "#ABCDEF";
				Pattern pattern = Pattern.compile(
					Regexp.RGB_HEX.getPattern(),
					Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
				);
				Matcher matcher = pattern.matcher(value);

				boolean actual = matcher.matches();

				assertThat(actual).isTrue();
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
			 * Проверка основной группы регулярного выражения константы {@link Regexp#RGB_HEX}.
			 */
			@Test
			public void rgbHexWithGroup()
			{
				String value = "#ABCDEF";
				Pattern pattern = Pattern.compile(
					Regexp.RGB_HEX.getPattern(),
					Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
				);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup1 = matcher.group("rgb");

				assertThat(actualGroup1).isEqualTo("#ABCDEF");
			}

			/**
			 * Проверка группы красного цвета регулярного выражения константы {@link Regexp#RGB_HEX}.
			 */
			@Test
			public void rgbHexWithGroupRed()
			{
				String value = "#ABCDEF";
				Pattern pattern = Pattern.compile(
					Regexp.RGB_HEX.getPattern(),
					Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
				);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup2 = matcher.group("red");

				assertThat(actualGroup2).isEqualTo("AB");
			}

			/**
			 * Проверка группы зелёного цвета регулярного выражения константы {@link Regexp#RGB_HEX}.
			 */
			@Test
			public void rgbHexWithGroupGreen()
			{
				String value = "#ABCDEF";
				Pattern pattern = Pattern.compile(
					Regexp.RGB_HEX.getPattern(),
					Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
				);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup3 = matcher.group("green");

				assertThat(actualGroup3).isEqualTo("CD");
			}

			/**
			 * Проверка группы голубого цвета регулярного выражения константы {@link Regexp#RGB_HEX}.
			 */
			@Test
			public void rgbHexWithGroupBlue()
			{
				String value = "#ABCDEF";
				Pattern pattern = Pattern.compile(
					Regexp.RGB_HEX.getPattern(),
					Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
				);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup4 = matcher.group("blue");

				assertThat(actualGroup4).isEqualTo("EF");
			}
		}

		/**
		 * Класс проверки регулярного выражения константы перечисления {@link Regexp#MAC_ADDRESS}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class MacAddress
		{
			/**
			 * Проверка регулярного выражения константы {@link Regexp#MAC_ADDRESS} с корректными значениями.
			 */
			@Test
			public void macAddressWithValueValid()
			{
				String value = "00-eF-cd-Ef-11-22";
				Pattern pattern = Pattern.compile(
					Regexp.MAC_ADDRESS.getPattern(),
					Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
				);
				Matcher matcher = pattern.matcher(value);

				boolean actual = matcher.matches();

				assertThat(actual).isTrue();
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
			 * Проверка основной группы регулярного выражения константы {@link Regexp#MAC_ADDRESS}.
			 */
			@Test
			public void macAddressWithGroup()
			{
				String value = "00-eF-cd-Ef-11-22";
				Pattern pattern = Pattern.compile(
					Regexp.MAC_ADDRESS.getPattern(),
					Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
				);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup1 = matcher.group("macAddress");

				assertThat(actualGroup1).isEqualTo("00-eF-cd-Ef-11-22");
			}

			/**
			 * Проверка группы разделителя регулярного выражения константы {@link Regexp#MAC_ADDRESS}.
			 */
			@Test
			public void macAddressWithGroupSeparator()
			{
				String value = "00-eF-cd-Ef-11-22";
				Pattern pattern = Pattern.compile(
					Regexp.MAC_ADDRESS.getPattern(),
					Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
				);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup2 = matcher.group("separator");

				assertThat(actualGroup2).isEqualTo("-");
			}

			/**
			 * Проверка группы первой части регулярного выражения константы {@link Regexp#MAC_ADDRESS}.
			 */
			@Test
			public void macAddressWithGroupPart1()
			{
				String value = "00-eF-cd-Ef-11-22";
				Pattern pattern = Pattern.compile(
					Regexp.MAC_ADDRESS.getPattern(),
					Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
				);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup3 = matcher.group("part1");

				assertThat(actualGroup3).isEqualTo("00");
			}

			/**
			 * Проверка группы второй части регулярного выражения константы {@link Regexp#MAC_ADDRESS}.
			 */
			@Test
			public void macAddressWithGroupPart2()
			{
				String value = "00-eF-cd-Ef-11-22";
				Pattern pattern = Pattern.compile(
					Regexp.MAC_ADDRESS.getPattern(),
					Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
				);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup4 = matcher.group("part2");

				assertThat(actualGroup4).isEqualTo("eF");
			}

			/**
			 * Проверка группы третьей части регулярного выражения константы {@link Regexp#MAC_ADDRESS}.
			 */
			@Test
			public void macAddressWithGroupPart3()
			{
				String value = "00-eF-cd-Ef-11-22";
				Pattern pattern = Pattern.compile(
					Regexp.MAC_ADDRESS.getPattern(),
					Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
				);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup5 = matcher.group("part3");

				assertThat(actualGroup5).isEqualTo("cd");
			}

			/**
			 * Проверка группы четвёртой части регулярного выражения константы {@link Regexp#MAC_ADDRESS}.
			 */
			@Test
			public void macAddressWithGroupPart4()
			{
				String value = "00-eF-cd-Ef-11-22";
				Pattern pattern = Pattern.compile(
					Regexp.MAC_ADDRESS.getPattern(),
					Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
				);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup6 = matcher.group("part4");

				assertThat(actualGroup6).isEqualTo("Ef");
			}

			/**
			 * Проверка группы пятой части регулярного выражения константы {@link Regexp#MAC_ADDRESS}.
			 */
			@Test
			public void macAddressWithGroupPart5()
			{
				String value = "00-eF-cd-Ef-11-22";
				Pattern pattern = Pattern.compile(
					Regexp.MAC_ADDRESS.getPattern(),
					Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
				);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup7 = matcher.group("part5");

				assertThat(actualGroup7).isEqualTo("11");
			}

			/**
			 * Проверка группы шестой части регулярного выражения константы {@link Regexp#MAC_ADDRESS}.
			 */
			@Test
			public void macAddressWithGroupPart6()
			{
				String value = "00-eF-cd-Ef-11-22";
				Pattern pattern = Pattern.compile(
					Regexp.MAC_ADDRESS.getPattern(),
					Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
				);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup8 = matcher.group("part6");

				assertThat(actualGroup8).isEqualTo("22");
			}
		}

		/**
		 * Класс проверки регулярного выражения константы перечисления {@link Regexp#TELEGRAM}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class Telegram
		{
			/**
			 * Проверка регулярного выражения константы {@link Regexp#TELEGRAM} с корректными значениями.
			 */
			@Test
			public void telegramWithValueValid()
			{
				String value = "@kalenchukov";
				Pattern pattern = Pattern.compile(
					Regexp.TELEGRAM.getPattern(),
					Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
				);
				Matcher matcher = pattern.matcher(value);

				boolean actual = matcher.matches();

				assertThat(actual).isTrue();
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
			 * Проверка основной группы регулярного выражения константы {@link Regexp#TELEGRAM}.
			 */
			@Test
			public void telegramWithGroup()
			{
				String value = "@kalenchukov";
				Pattern pattern = Pattern.compile(
					Regexp.TELEGRAM.getPattern(),
					Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
				);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup1 = matcher.group("telegram");

				assertThat(actualGroup1).isEqualTo("@kalenchukov");
			}

			/**
			 * Проверка группы названия регулярного выражения константы {@link Regexp#TELEGRAM}.
			 */
			@Test
			public void telegramWithGroupName()
			{
				String value = "@kalenchukov";
				Pattern pattern = Pattern.compile(
					Regexp.TELEGRAM.getPattern(),
					Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
				);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup2 = matcher.group("name");

				assertThat(actualGroup2).isEqualTo("kalenchukov");
			}
		}

		/**
		 * Класс проверки регулярного выражения константы перечисления {@link Regexp#TAG}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class Tag
		{
			/**
			 * Проверка регулярного выражения константы {@link Regexp#TAG} с корректными значениями.
			 */
			@Test
			public void tagWithValueValid()
			{
				String value = "#tag";
				Pattern pattern = Pattern.compile(
					Regexp.TAG.getPattern(),
					Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
				);
				Matcher matcher = pattern.matcher(value);

				boolean actual = matcher.matches();

				assertThat(actual).isTrue();
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
			 * Проверка основной группы регулярного выражения константы {@link Regexp#TAG}.
			 */
			@Test
			public void tagWithGroup()
			{
				String value = "#tag";
				Pattern pattern = Pattern.compile(
					Regexp.TAG.getPattern(),
					Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
				);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup1 = matcher.group("tag");

				assertThat(actualGroup1).isEqualTo("#tag");
			}

			/**
			 * Проверка группы названия регулярного выражения константы {@link Regexp#TAG}.
			 */
			@Test
			public void tagWithGroupName()
			{
				String value = "#tag";
				Pattern pattern = Pattern.compile(
					Regexp.TAG.getPattern(),
					Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
				);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup2 = matcher.group("name");

				assertThat(actualGroup2).isEqualTo("tag");
			}
		}

		/**
		 * Класс проверки регулярного выражения константы перечисления {@link Regexp#DIGIT_BINARY}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class DigitBinary
		{
			/**
			 * Проверка регулярного выражения константы {@link Regexp#DIGIT_BINARY} с корректными значениями.
			 */
			@Test
			public void digitBinaryWithValueValid()
			{
				String value = "0101010101";
				Pattern pattern = Pattern.compile(Regexp.DIGIT_BINARY.getPattern(), Pattern.UNICODE_CASE);
				Matcher matcher = pattern.matcher(value);

				boolean actual = matcher.matches();

				assertThat(actual).isTrue();
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
			 * Проверка основной группы регулярного выражения константы {@link Regexp#DIGIT_BINARY}.
			 */
			@Test
			public void digitBinaryWithGroup()
			{
				String value = "0101010101";
				Pattern pattern = Pattern.compile(Regexp.DIGIT_BINARY.getPattern(), Pattern.UNICODE_CASE);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup = matcher.group("digit");

				assertThat(actualGroup).isEqualTo("0101010101");
			}
		}

		/**
		 * Класс проверки регулярного выражения константы перечисления {@link Regexp#DIGIT_TERNARY}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class DigitTernary
		{
			/**
			 * Проверка регулярного выражения константы {@link Regexp#DIGIT_TERNARY} с корректными значениями.
			 */
			@Test
			public void digitTernaryWithValueValid()
			{
				String value = "210210210210";
				Pattern pattern = Pattern.compile(Regexp.DIGIT_TERNARY.getPattern(), Pattern.UNICODE_CASE);
				Matcher matcher = pattern.matcher(value);

				boolean actual = matcher.matches();

				assertThat(actual).isTrue();
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
			 * Проверка основной группы регулярного выражения константы {@link Regexp#DIGIT_TERNARY}.
			 */
			@Test
			public void digitTernaryWithGroup()
			{
				String value = "210210210210";
				Pattern pattern = Pattern.compile(Regexp.DIGIT_TERNARY.getPattern(), Pattern.UNICODE_CASE);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup = matcher.group("digit");

				assertThat(actualGroup).isEqualTo("210210210210");
			}
		}

		/**
		 * Класс проверки регулярного выражения константы перечисления {@link Regexp#DIGIT_OCTAL}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class DigitOctal
		{
			/**
			 * Проверка регулярного выражения константы {@link Regexp#DIGIT_OCTAL} с корректными значениями.
			 */
			@Test
			public void digitOctalWithValueValid()
			{
				String value = "65016716501710651467106";
				Pattern pattern = Pattern.compile(Regexp.DIGIT_OCTAL.getPattern(), Pattern.UNICODE_CASE);
				Matcher matcher = pattern.matcher(value);

				boolean actual = matcher.matches();

				assertThat(actual).isTrue();
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
			 * Проверка основной группы регулярного выражения константы {@link Regexp#DIGIT_OCTAL}.
			 */
			@Test
			public void digitOctalWithGroup()
			{
				String value = "65016716501710651467106";
				Pattern pattern = Pattern.compile(Regexp.DIGIT_OCTAL.getPattern(), Pattern.UNICODE_CASE);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup = matcher.group("digit");

				assertThat(actualGroup).isEqualTo("65016716501710651467106");
			}
		}

		/**
		 * Класс проверки регулярного выражения константы перечисления {@link Regexp#DIGIT_DECIMAL}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class DigitDecimal
		{
			/**
			 * Проверка регулярного выражения константы {@link Regexp#DIGIT_DECIMAL} с корректными значениями.
			 */
			@Test
			public void digitDecimalWithValueValid()
			{
				String value = "0123";
				Pattern pattern = Pattern.compile(Regexp.DIGIT_DECIMAL.getPattern(), Pattern.UNICODE_CASE);
				Matcher matcher = pattern.matcher(value);

				boolean actual = matcher.matches();

				assertThat(actual).isTrue();
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
			 * Проверка основной группы регулярного выражения константы {@link Regexp#DIGIT_DECIMAL}.
			 */
			@Test
			public void digitDecimalWithGroup()
			{
				String value = "0123";
				Pattern pattern = Pattern.compile(Regexp.DIGIT_DECIMAL.getPattern(), Pattern.UNICODE_CASE);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup = matcher.group("digit");

				assertThat(actualGroup).isEqualTo("0123");
			}
		}

		/**
		 * Класс проверки регулярного выражения константы перечисления {@link Regexp#DIGIT_DUODECIMAL}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class DigitDuodecimal
		{
			/**
			 * Проверка регулярного выражения константы {@link Regexp#DIGIT_DUODECIMAL} с корректными значениями.
			 */
			@Test
			public void digitDuodecimalWithValueValid()
			{
				String value = "8819a861bB14Aab04ab519a2baA3b7a9B";
				Pattern pattern = Pattern.compile(
					Regexp.DIGIT_DUODECIMAL.getPattern(),
					Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
				);
				Matcher matcher = pattern.matcher(value);

				boolean actual = matcher.matches();

				assertThat(actual).isTrue();
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
			 * Проверка основной группы регулярного выражения константы {@link Regexp#DIGIT_DUODECIMAL}.
			 */
			@Test
			public void digitDuodecimalWithGroup()
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
		}

		/**
		 * Класс проверки регулярного выражения константы перечисления {@link Regexp#DIGIT_HEXADECIMAL}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class DigitHexadecimal
		{
			/**
			 * Проверка регулярного выражения константы {@link Regexp#DIGIT_HEXADECIMAL} с корректными значениями.
			 */
			@Test
			public void digitHexadecimalWithValueValid()
			{
				String value = "0123456789ABCDEF";
				Pattern pattern = Pattern.compile(
					Regexp.DIGIT_HEXADECIMAL.getPattern(),
					Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
				);
				Matcher matcher = pattern.matcher(value);

				boolean actual = matcher.matches();

				assertThat(actual).isTrue();
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
			 * Проверка основной группы регулярного выражения константы {@link Regexp#DIGIT_HEXADECIMAL}.
			 */
			@Test
			public void digitHexadecimalWithGroup()
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
		}

		/**
		 * Класс проверки регулярного выражения константы перечисления {@link Regexp#NUMBER}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class Number
		{
			/**
			 * Проверка регулярного выражения константы {@link Regexp#NUMBER} с корректными значениями.
			 */
			@Test
			public void numberWithValueValid()
			{
				String value = "1.100";
				Pattern pattern = Pattern.compile(Regexp.NUMBER.getPattern(), Pattern.UNICODE_CASE);
				Matcher matcher = pattern.matcher(value);

				boolean actual = matcher.matches();

				assertThat(actual).isTrue();
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
			 * Проверка основной группы регулярного выражения константы {@link Regexp#NUMBER}.
			 */
			@Test
			public void numberWithGroup()
			{
				String value = "1.100";
				Pattern pattern = Pattern.compile(Regexp.NUMBER.getPattern(), Pattern.UNICODE_CASE);
				Matcher matcher = pattern.matcher(value);

				assertThat(matcher.matches()).isTrue();

				String actualGroup = matcher.group("number");

				assertThat(actualGroup).isEqualTo("1.100");
			}
		}

		/**
		 * Класс проверки регулярного выражения константы перечисления {@link Regexp#WORD}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class Word
		{
			/**
			 * Проверка регулярного выражения константы {@link Regexp#WORD} с корректными значениями.
			 */
			@Test
			public void wordWithValueValid()
			{
				String value = "Привет";
				Pattern pattern = Pattern.compile(
					Regexp.WORD.getPattern(),
					Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
				);
				Matcher matcher = pattern.matcher(value);

				boolean actual = matcher.matches();

				assertThat(actual).isTrue();
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
			 * Проверка основной группы регулярного выражения константы {@link Regexp#WORD}.
			 */
			@Test
			public void wordWithGroup()
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
		}

		/**
		 * Класс проверки регулярного выражения константы перечисления {@link Regexp#LETTER}.
		 *
		 * @author Алексей Каленчуков
		 */
		@Nested
		public class Letter
		{
			/**
			 * Проверка регулярного выражения константы {@link Regexp#LETTER} с корректными значениями.
			 */
			@Test
			public void letterWithValueValid()
			{
				String value = "Ъ";
				Pattern pattern = Pattern.compile(
					Regexp.LETTER.getPattern(),
					Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
				);
				Matcher matcher = pattern.matcher(value);

				boolean actual = matcher.matches();

				assertThat(actual).isTrue();
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

			/**
			 * Проверка основной группы регулярного выражения константы {@link Regexp#LETTER}.
			 */
			@Test
			public void letterWithGroup()
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
		}
	}
}
