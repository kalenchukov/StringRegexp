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

package dev.kalenchukov.string.regexp;

import dev.kalenchukov.string.regexp.resources.Regexp;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс содержит статические методы для проверки корректности строк с помощью регулярных выражений.
 */
public class StringRegexp
{
	/**
	 * Конструктор для {@code StringRegexp}.
	 */
	private StringRegexp() {}

	/**
	 * Проверяет, является ли строка именем телеграм канала.
	 *
	 * @param string строка.
	 * @return {@code true}, если строка является именем телеграм канала, иначе {@code false}.
	 */
	public static boolean isTelegram(@NotNull final String string)
	{
		return StringRegexp.is(
			string,
			Regexp.TELEGRAM,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
	}

	/**
	 * Проверяет, является ли строка RGB в числовом представлении.
	 *
	 * @param string строка.
	 * @return {@code true}, если строка является RGB в числовом представлении, иначе {@code false}.
	 */
	public static boolean isRgbNumeric(@NotNull final String string)
	{
		return StringRegexp.is(string, Regexp.RGB_NUMERIC);
	}

	/**
	 * Проверяет, является ли строка двухбуквенным кодом страны.
	 *
	 * @param string строка.
	 * @return {@code true}, если строка является двухбуквенным кодом страны, иначе {@code false}.
	 */
	public static boolean isCountryCodeAlpha2(@NotNull final String string)
	{
		return StringRegexp.is(string, Regexp.COUNTRY_CODE_ALPHA2);
	}

	/**
	 * Проверяет, является ли строка трёхбуквенным кодом страны.
	 *
	 * @param string строка.
	 * @return {@code true}, если строка является трёхбуквенным кодом страны, иначе {@code false}.
	 */
	public static boolean isCountryCodeAlpha3(@NotNull final String string)
	{
		return StringRegexp.is(string, Regexp.COUNTRY_CODE_ALPHA3);
	}

	/**
	 * Проверяет, является ли строка трёхцифровым кодом страны.
	 *
	 * @param string строка.
	 * @return {@code true}, если строка является трёхцифровым кодом страны, иначе {@code false}.
	 */
	public static boolean isCountryCodeNumeric3(@NotNull final String string)
	{
		return StringRegexp.is(string, Regexp.COUNTRY_CODE_NUMERIC3);
	}

	/**
	 * Проверяет, является ли строка RGB в шестнадцатеричной системе счисления.
	 *
	 * @param string строка.
	 * @return {@code true}, если строка является RGB в шестнадцатеричной системе счисления, иначе {@code false}.
	 */
	public static boolean isRgbHex(@NotNull final String string)
	{
		return StringRegexp.is(string, Regexp.RGB_HEX);
	}

	/**
	 * Проверяет, является ли строка RGB в шестнадцатеричной системе счисления без учёта регистра букв.
	 *
	 * @param string строка.
	 * @return {@code true}, если строка является RGB в шестнадцатеричной системе счисления, иначе {@code false}.
	 */
	public static boolean isRgbHexIgnoreCase(@NotNull final String string)
	{
		return StringRegexp.is(
			string,
			Regexp.RGB_HEX,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
	}

	/**
	 * Проверяет, является ли строка MAC-адресом.
	 *
	 * @param string строка.
	 * @return {@code true}, если строка является MAC-адресом, иначе {@code false}.
	 */
	public static boolean isMacAddress(@NotNull final String string)
	{
		return StringRegexp.is(string, Regexp.MAC_ADDRESS);
	}

	/**
	 * Проверяет, является ли строка MAC-адресом без учёта регистра букв.
	 *
	 * @param string строка.
	 * @return {@code true}, если строка является MAC-адресом, иначе {@code false}.
	 */
	public static boolean isMacAddressIgnoreCase(@NotNull final String string)
	{
		return StringRegexp.is(
			string,
			Regexp.MAC_ADDRESS,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
	}

	/**
	 * Проверяет, является ли строка локализацией.
	 *
	 * @param string строка.
	 * @return {@code true}, если строка является локализацией, иначе {@code false}.
	 */
	public static boolean isLocalization(@NotNull final String string)
	{
		return StringRegexp.is(string, Regexp.LOCALIZATION);
	}

	/**
	 * Проверяет, является ли строка адресом электронной почты.
	 *
	 * @param string строка.
	 * @return {@code true}, если строка является адресом электронной почты, иначе {@code false}.
	 */
	public static boolean isEmailAddress(@NotNull final String string)
	{
		return StringRegexp.is(
			string,
			Regexp.EMAIL_ADDRESS,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
	}

	/**
	 * Проверяет, является ли строка доменным именем.
	 *
	 * @param string строка.
	 * @return {@code true}, если строка является доменным именем, иначе {@code false}.
	 */
	public static boolean isDomain(@NotNull final String string)
	{
		return StringRegexp.is(
			string,
			Regexp.DOMAIN,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
	}

	/**
	 * Проверяет, является ли строка HTTP URL.
	 *
	 * @param string строка.
	 * @return {@code true}, если строка является HTTP URL, иначе {@code false}.
	 */
	public static boolean isUrlHttp(@NotNull final String string)
	{
		return StringRegexp.is(
			string,
			Regexp.URL_HTTP,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
	}

	/**
	 * Проверяет, является ли строка IP-адресом четвёртой версии.
	 *
	 * @param string строка.
	 * @return {@code true}, если строка является IP-адресом четвёртой версии, иначе {@code false}.
	 */
	public static boolean isInet4Address(@NotNull final String string)
	{
		return StringRegexp.is(string, Regexp.INET_4_ADDRESS);
	}

	/**
	 * Проверяет, является ли строка IP-адресом шестой версии.
	 *
	 * @param string строка.
	 * @return {@code true}, если строка является IP-адресом шестой версии, иначе {@code false}.
	 */
	public static boolean isInet6Address(@NotNull final String string)
	{
		return StringRegexp.is(string, Regexp.INET_6_ADDRESS);
	}

	/**
	 * Проверяет, является ли строка IP-адресом шестой версии без учёта регистра букв.
	 *
	 * @param string строка.
	 * @return {@code true}, если строка является IP-адресом шестой версии, иначе {@code false}.
	 */
	public static boolean isInet6AddressIgnoreCase(@NotNull final String string)
	{
		return StringRegexp.is(
			string,
			Regexp.INET_6_ADDRESS,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
	}

	/**
	 * Проверяет, является ли строка меткой.
	 *
	 * @param string строка.
	 * @return {@code true}, если строка является меткой, иначе {@code false}.
	 */
	public static boolean isTag(@NotNull final String string)
	{
		return StringRegexp.is(string, Regexp.TAG, Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE);
	}

	/**
	 * Проверяет, является ли строка цифрой двоичной системы счисления.
	 *
	 * @param string строка.
	 * @return {@code true}, если строка является цифрой двоичной системы счисления, иначе {@code false}.
	 */
	public static boolean isDigitBinary(@NotNull final String string)
	{
		return StringRegexp.is(string, Regexp.DIGIT_BINARY);
	}

	/**
	 * Проверяет, является ли строка цифрой десятеричной системы счисления.
	 *
	 * @param string строка.
	 * @return {@code true}, если строка является цифрой десятеричной системы счисления, иначе {@code false}.
	 */
	public static boolean isDigitDecimal(@NotNull final String string)
	{
		return StringRegexp.is(string, Regexp.DIGIT_DECIMAL);
	}

	/**
	 * Проверяет, является ли строка цифрой троичной системы счисления.
	 *
	 * @param string строка.
	 * @return {@code true}, если строка является цифрой троичной системы счисления, иначе {@code false}.
	 */
	public static boolean isDigitTernary(@NotNull final String string)
	{
		return StringRegexp.is(string, Regexp.DIGIT_TERNARY);
	}

	/**
	 * Проверяет, является ли строка цифрой восьмеричной системы счисления.
	 *
	 * @param string строка.
	 * @return {@code true}, если строка является цифрой восьмеричной системы счисления, иначе {@code false}.
	 */
	public static boolean isDigitOctal(@NotNull final String string)
	{
		return StringRegexp.is(string, Regexp.DIGIT_OCTAL);
	}

	/**
	 * Проверяет, является ли строка цифрой двенадцатеричной системы счисления.
	 *
	 * @param string строка.
	 * @return {@code true}, если строка является цифрой двенадцатеричной системы счисления, иначе {@code false}.
	 */
	public static boolean isDigitDuodecimal(@NotNull final String string)
	{
		return StringRegexp.is(
			string, Regexp.DIGIT_DUODECIMAL,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
	}

	/**
	 * Проверяет, является ли строка цифрой шестнадцатеричной системы счисления.
	 *
	 * @param string строка.
	 * @return {@code true}, если строка является цифрой шестнадцатеричной системы счисления, иначе {@code false}.
	 */
	public static boolean isDigitHexadecimal(@NotNull final String string)
	{
		return StringRegexp.is(
			string, Regexp.DIGIT_HEXADECIMAL,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
	}

	/**
	 * Проверяет, является ли строка числом.
	 *
	 * @param string строка.
	 * @return {@code true}, если строка является числом, иначе {@code false}.
	 */
	public static boolean isNumber(@NotNull final String string)
	{
		return StringRegexp.is(string, Regexp.NUMBER);
	}

	/**
	 * Проверяет, является ли строка словом.
	 *
	 * @param string строка.
	 * @return {@code true}, если строка является словом, иначе {@code false}.
	 */
	public static boolean isWord(@NotNull final String string)
	{
		return StringRegexp.is(string, Regexp.WORD, Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE);
	}

	/**
	 * Проверяет, является ли строка буквой.
	 *
	 * @param string строка.
	 * @return {@code true}, если строка является буквой, иначе {@code false}.
	 */
	public static boolean isLetter(@NotNull final String string)
	{
		return StringRegexp.is(string, Regexp.LETTER, Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE);
	}

	/**
	 * Проверяет, является ли строка HTML комментарием.
	 *
	 * @param string строка.
	 * @return {@code true}, если строка является HTML комментарием, иначе {@code false}.
	 */
	public static boolean isHtmlComment(@NotNull final String string)
	{
		return StringRegexp.is(
			string,
			Regexp.HTML_COMMENT,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE + Pattern.DOTALL
		);
	}

	/**
	 * Проверяет, является ли строка HTML сущностью в виде имени.
	 *
	 * @param string строка.
	 * @return {@code true}, если строка является HTML сущностью в виде имени, иначе {@code false}.
	 */
	public static boolean isHtmlEntityName(@NotNull final String string)
	{
		return StringRegexp.is(
			string,
			Regexp.HTML_ENTITY_NAME,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
	}

	/**
	 * Проверяет, является ли строка HTML сущностью в виде числа.
	 *
	 * @param string строка.
	 * @return {@code true}, если строка является HTML сущностью в виде числа, иначе {@code false}.
	 */
	public static boolean isHtmlEntityNumeric(@NotNull final String string)
	{
		return StringRegexp.is(string, Regexp.HTML_ENTITY_NUMERIC);
	}

	/**
	 * Проверяет, является ли строка HTML сущностью в виде unicode.
	 *
	 * @param string строка.
	 * @return {@code true}, если строка является HTML сущностью в виде unicode, иначе {@code false}.
	 */
	public static boolean isHtmlEntityUnicode(@NotNull final String string)
	{
		return StringRegexp.is(
			string,
			Regexp.HTML_ENTITY_UNICODE,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
	}

	/**
	 * Проверяет, является ли строка типом HTML документа.
	 *
	 * @param string строка.
	 * @return {@code true}, если строка является типом HTML документа, иначе {@code false}.
	 */
	public static boolean isHtmlDoctype(@NotNull final String string)
	{
		return StringRegexp.is(
			string,
			Regexp.HTML_DOCTYPE,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE + Pattern.DOTALL
		);
	}

	/**
	 * Проверяет, является ли строка областью CDATA.
	 *
	 * @param string строка.
	 * @return {@code true}, если строка является областью CDATA, иначе {@code false}.
	 */
	public static boolean isCData(@NotNull final String string)
	{
		return StringRegexp.is(
			string,
			Regexp.CDATA,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE + Pattern.DOTALL
		);
	}

	/**
	 * Проверяет, является ли строка закрывающим HTML тегом.
	 *
	 * @param string строка.
	 * @return {@code true}, если строка является закрывающим HTML тегом, иначе {@code false}.
	 */
	public static boolean isHtmlEndTag(@NotNull final String string)
	{
		return StringRegexp.is(
			string,
			Regexp.HTML_END_TAG,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
	}

	/**
	 * Проверяет, является ли строка открывающим HTML тегом.
	 *
	 * @param string строка.
	 * @return {@code true}, если строка является открывающим HTML тегом, иначе {@code false}.
	 */
	public static boolean isHtmlStartTag(@NotNull final String string)
	{
		return StringRegexp.is(
			string,
			Regexp.HTML_START_TAG,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE + Pattern.DOTALL
		);
	}

	/**
	 * Проверяет, является ли строка самозакрывающимся HTML тегом.
	 *
	 * @param string строка.
	 * @return {@code true}, если строка является самозакрывающимся HTML тегом, иначе {@code false}.
	 */
	public static boolean isHtmlSelfClosingTag(@NotNull final String string)
	{
		return StringRegexp.is(
			string,
			Regexp.HTML_SELF_CLOSING_TAG,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE + Pattern.DOTALL
		);
	}

	/**
	 * Выполняет поиск самозакрывающихся HTML тегов.
	 *
	 * @param string строка.
	 * @return коллекцию с найденными самозакрывающихся HTML тегами.
	 */
	@Unmodifiable
	@NotNull
	public static List<@NotNull String> findHtmlSelfClosingTag(@NotNull final String string)
	{
		return StringRegexp.find(
			string,
			Regexp.HTML_SELF_CLOSING_TAG,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE + Pattern.DOTALL
		);
	}

	/**
	 * Выполняет поиск открывающих HTML тегов.
	 *
	 * @param string строка.
	 * @return коллекцию с найденными открывающими HTML тегами.
	 */
	@Unmodifiable
	@NotNull
	public static List<@NotNull String> findHtmlStartTag(@NotNull final String string)
	{
		return StringRegexp.find(
			string,
			Regexp.HTML_START_TAG,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE + Pattern.DOTALL
		);
	}

	/**
	 * Выполняет поиск закрывающих HTML тегов.
	 *
	 * @param string строка.
	 * @return коллекцию с найденными закрывающими HTML тегами.
	 */
	@Unmodifiable
	@NotNull
	public static List<@NotNull String> findHtmlEndTag(@NotNull final String string)
	{
		return StringRegexp.find(
			string,
			Regexp.HTML_END_TAG,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
	}

	/**
	 * Выполняет поиск областей CDATA.
	 *
	 * @param string строка.
	 * @return коллекцию с найденными областями CDATA.
	 */
	@Unmodifiable
	@NotNull
	public static List<@NotNull String> findCData(@NotNull final String string)
	{
		return StringRegexp.find(
			string,
			Regexp.CDATA,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE + Pattern.DOTALL
		);
	}

	/**
	 * Выполняет поиск типов HTML документа.
	 *
	 * @param string строка.
	 * @return коллекцию с найденными типами HTML документа.
	 */
	@Unmodifiable
	@NotNull
	public static List<@NotNull String> findHtmlDoctype(@NotNull final String string)
	{
		return StringRegexp.find(
			string,
			Regexp.HTML_DOCTYPE,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE + Pattern.DOTALL
		);
	}

	/**
	 * Выполняет поиск HTML-сущностей в виде имени.
	 *
	 * @param string строка.
	 * @return коллекцию с найденными HTML сущностями в виде имени.
	 */
	@Unmodifiable
	@NotNull
	public static List<@NotNull String> findHtmlEntityName(@NotNull final String string)
	{
		return StringRegexp.find(
			string,
			Regexp.HTML_ENTITY_NAME,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
	}

	/**
	 * Выполняет поиск HTML-сущностей в виде числа.
	 *
	 * @param string строка.
	 * @return коллекцию с найденными HTML сущностями в виде числа.
	 */
	@Unmodifiable
	@NotNull
	public static List<@NotNull String> findHtmlEntityNumeric(@NotNull final String string)
	{
		return StringRegexp.find(string, Regexp.HTML_ENTITY_NUMERIC);
	}

	/**
	 * Выполняет поиск HTML-сущностей в виде unicode.
	 *
	 * @param string строка.
	 * @return коллекцию с найденными HTML сущностями в виде unicode.
	 */
	@Unmodifiable
	@NotNull
	public static List<@NotNull String> findHtmlEntityUnicode(@NotNull final String string)
	{
		return StringRegexp.find(
			string,
			Regexp.HTML_ENTITY_UNICODE,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
	}

	/**
	 * Выполняет поиск HTML комментариев.
	 *
	 * @param string строка.
	 * @return коллекцию с найденными HTML комментариями.
	 */
	@Unmodifiable
	@NotNull
	public static List<@NotNull String> findHtmlComment(@NotNull final String string)
	{
		return StringRegexp.find(
			string,
			Regexp.HTML_COMMENT,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE + Pattern.DOTALL
		);
	}

	/**
	 * Выполняет поиск букв.
	 *
	 * @param string строка.
	 * @return коллекцию с найденными буквами.
	 */
	@Unmodifiable
	@NotNull
	public static List<@NotNull String> findLetter(@NotNull final String string)
	{
		return StringRegexp.find(
			string,
			Regexp.LETTER,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
	}

	/**
	 * Выполняет поиск слов.
	 *
	 * @param string строка.
	 * @return коллекцию с найденными словами.
	 */
	@Unmodifiable
	@NotNull
	public static List<@NotNull String> findWord(@NotNull final String string)
	{
		return StringRegexp.find(
			string,
			Regexp.WORD,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
	}

	/**
	 * Выполняет поиск цифр двоичной системы счисления.
	 *
	 * @param string строка.
	 * @return коллекцию с найденными цифрами двоичной системы счисления.
	 */
	@Unmodifiable
	@NotNull
	public static List<@NotNull String> findDigitBinary(@NotNull final String string)
	{
		return StringRegexp.find(string, Regexp.DIGIT_BINARY);
	}

	/**
	 * Выполняет поиск цифр троичной системы счисления.
	 *
	 * @param string строка.
	 * @return коллекцию с найденными цифрами троичной системы счисления.
	 */
	@Unmodifiable
	@NotNull
	public static List<@NotNull String> findDigitTernary(@NotNull final String string)
	{
		return StringRegexp.find(string, Regexp.DIGIT_TERNARY);
	}

	/**
	 * Выполняет поиск цифр восьмеричной системы счисления.
	 *
	 * @param string строка.
	 * @return коллекцию с найденными цифрами восьмеричной системы счисления.
	 */
	@Unmodifiable
	@NotNull
	public static List<@NotNull String> findDigitOctal(@NotNull final String string)
	{
		return StringRegexp.find(string, Regexp.DIGIT_OCTAL);
	}

	/**
	 * Выполняет поиск цифр двенадцатеричной системы счисления.
	 *
	 * @param string строка.
	 * @return коллекцию с найденными цифрами двенадцатеричной системы счисления.
	 */
	@Unmodifiable
	@NotNull
	public static List<@NotNull String> findDigitDuodecimal(@NotNull final String string)
	{
		return StringRegexp.find(
			string,
			Regexp.DIGIT_DUODECIMAL,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
	}

	/**
	 * Выполняет поиск цифр шестнадцатеричной системы счисления.
	 *
	 * @param string строка.
	 * @return коллекцию с найденными цифрами шестнадцатеричной системы счисления.
	 */
	@Unmodifiable
	@NotNull
	public static List<@NotNull String> findDigitHexadecimal(@NotNull final String string)
	{
		return StringRegexp.find(
			string,
			Regexp.DIGIT_HEXADECIMAL,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
	}

	/**
	 * Выполняет поиск цифр десятеричной системы счисления.
	 *
	 * @param string строка.
	 * @return коллекцию с найденными цифрами десятеричной системы счисления.
	 */
	@Unmodifiable
	@NotNull
	public static List<@NotNull String> findDigitDecimal(@NotNull final String string)
	{
		return StringRegexp.find(string, Regexp.DIGIT_DECIMAL);
	}

	/**
	 * Выполняет поиск чисел.
	 *
	 * @param string строка.
	 * @return коллекцию с найденными числами.
	 */
	@Unmodifiable
	@NotNull
	public static List<@NotNull String> findNumber(@NotNull final String string)
	{
		return StringRegexp.find(string, Regexp.NUMBER);
	}

	/**
	 * Выполняет поиск имён телеграм каналов.
	 *
	 * @param string строка.
	 * @return коллекцию с найденными именами телеграм каналов.
	 */
	@Unmodifiable
	@NotNull
	public static List<@NotNull String> findTelegram(@NotNull final String string)
	{
		return StringRegexp.find(
			string,
			Regexp.TELEGRAM,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
	}

	/**
	 * Выполняет поиск меток.
	 *
	 * @param string строка.
	 * @return коллекцию с найденными метками.
	 */
	@Unmodifiable
	@NotNull
	public static List<@NotNull String> findTag(@NotNull final String string)
	{
		return StringRegexp.find(
			string,
			Regexp.TAG,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
	}

	/**
	 * Выполняет поиск двухбуквенного кода страны.
	 *
	 * @param string строка.
	 * @return коллекцию с найденными двухбуквенными кодами стран.
	 */
	@Unmodifiable
	@NotNull
	public static List<@NotNull String> findCountryCodeAlpha2(@NotNull final String string)
	{
		return StringRegexp.find(string, Regexp.COUNTRY_CODE_ALPHA2);
	}

	/**
	 * Выполняет поиск трёхбуквенного кода страны.
	 *
	 * @param string строка.
	 * @return коллекцию с найденными трёхбуквенными кодами стран.
	 */
	@Unmodifiable
	@NotNull
	public static List<@NotNull String> findCountryCodeAlpha3(@NotNull final String string)
	{
		return StringRegexp.find(string, Regexp.COUNTRY_CODE_ALPHA3);
	}

	/**
	 * Выполняет поиск трёхциферного кода страны.
	 *
	 * @param string строка.
	 * @return коллекцию с найденными трёхциферными кодами стран.
	 */
	@Unmodifiable
	@NotNull
	public static List<@NotNull String> findCountryCodeNumeric3(@NotNull final String string)
	{
		return StringRegexp.find(string, Regexp.COUNTRY_CODE_NUMERIC3);
	}

	/**
	 * Выполняет поиск RGB в числовом представлении.
	 *
	 * @param string строка.
	 * @return коллекцию с найденными RGB в числовом представлении.
	 */
	@Unmodifiable
	@NotNull
	public static List<@NotNull String> findRgbNumeric(@NotNull final String string)
	{
		return StringRegexp.find(string, Regexp.RGB_NUMERIC);
	}

	/**
	 * Выполняет поиск RGB в шестнадцатеричной системе счисления.
	 *
	 * @param string строка.
	 * @return коллекцию с найденными RGB в шестнадцатеричной системе счисления.
	 */
	@Unmodifiable
	@NotNull
	public static List<@NotNull String> findRgbHex(@NotNull final String string)
	{
		return StringRegexp.find(string, Regexp.RGB_HEX);
	}

	/**
	 * Выполняет поиск RGB в шестнадцатеричной системе счисления без учётна регистра букв.
	 *
	 * @param string строка.
	 * @return коллекцию с найденными RGB в шестнадцатеричной системе счисления.
	 */
	@Unmodifiable
	@NotNull
	public static List<@NotNull String> findRgbHexIgnoreCase(@NotNull final String string)
	{
		return StringRegexp.find(
			string,
			Regexp.RGB_HEX,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
	}

	/**
	 * Выполняет поиск MAC-адресов.
	 *
	 * @param string строка.
	 * @return коллекцию с найденными MAC-адресами.
	 */
	@Unmodifiable
	@NotNull
	public static List<@NotNull String> findMacAddress(@NotNull final String string)
	{
		return StringRegexp.find(string, Regexp.MAC_ADDRESS);
	}

	/**
	 * Выполняет поиск MAC-адресов без учётна регистра букв.
	 *
	 * @param string строка.
	 * @return коллекцию с найденными MAC-адресами.
	 */
	@Unmodifiable
	@NotNull
	public static List<@NotNull String> findMacAddressIgnoreCase(@NotNull final String string)
	{
		return StringRegexp.find(
			string,
			Regexp.MAC_ADDRESS,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
	}

	/**
	 * Выполняет поиск локализаций.
	 *
	 * @param string строка.
	 * @return коллекцию с найденными локализациями.
	 */
	@Unmodifiable
	@NotNull
	public static List<@NotNull String> findLocalization(@NotNull final String string)
	{
		return StringRegexp.find(string, Regexp.LOCALIZATION);
	}

	/**
	 * Выполняет поиск IP-адресов четвёртой версии.
	 *
	 * @param string строка.
	 * @return коллекцию с найденными IP-адресами четвёртой версии.
	 */
	@Unmodifiable
	@NotNull
	public static List<@NotNull String> findInet4Address(@NotNull final String string)
	{
		return StringRegexp.find(string, Regexp.INET_4_ADDRESS);
	}

	/**
	 * Выполняет поиск IP-адресов шестой версии.
	 *
	 * @param string строка.
	 * @return коллекцию с найденными IP-адресами шестой версии.
	 */
	@Unmodifiable
	@NotNull
	public static List<@NotNull String> findInet6Address(@NotNull final String string)
	{
		return StringRegexp.find(string, Regexp.INET_6_ADDRESS);
	}

	/**
	 * Выполняет поиск IP-адресов шестой версии без учёта регистра букв.
	 *
	 * @param string строка.
	 * @return коллекцию с найденными IP-адресами шестой версии.
	 */
	@Unmodifiable
	@NotNull
	public static List<@NotNull String> findInet6AddressIgnoreCase(@NotNull final String string)
	{
		return StringRegexp.find(
			string,
			Regexp.INET_6_ADDRESS,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
	}

	/**
	 * Выполняет поиск адресов электронной почты.
	 *
	 * @param string строка.
	 * @return коллекцию с найденными адресами электронной почты.
	 */
	@Unmodifiable
	@NotNull
	public static List<@NotNull String> findEmailAddress(@NotNull final String string)
	{
		return StringRegexp.find(
			string,
			Regexp.EMAIL_ADDRESS,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
	}

	/**
	 * Выполняет поиск доменных имён.
	 *
	 * @param string строка.
	 * @return коллекцию с найденными доменными именами.
	 */
	@Unmodifiable
	@NotNull
	public static List<@NotNull String> findDomain(@NotNull final String string)
	{
		return StringRegexp.find(
			string,
			Regexp.DOMAIN,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
	}

	/**
	 * Выполняет поиск URL HTTP.
	 *
	 * @param string строка.
	 * @return коллекцию с найденными URL HTTP.
	 */
	@Unmodifiable
	@NotNull
	public static List<@NotNull String> findUrlHttp(@NotNull final String string)
	{
		return StringRegexp.find(
			string,
			Regexp.URL_HTTP,
			Pattern.UNICODE_CASE + Pattern.CASE_INSENSITIVE
		);
	}

	/**
	 * Проверяет, является ли строка значением.
	 *
	 * @param regexp регулярное выражение.
	 * @param string строка.
	 * @return {@code true}, если строка является значением, иначе {@code false}.
	 */
	private static boolean is(@NotNull final String string, @NotNull final Regexp regexp)
	{
		return StringRegexp.is(string, regexp, 0);
	}

	/**
	 * Проверяет, является ли строка значением.
	 *
	 * @param regexp регулярное выражение.
	 * @param flags флаги регулярного выражения.
	 * @param string строка.
	 * @return {@code true}, если строка является значением, иначе {@code false}.
	 */
	private static boolean is(@NotNull final String string,
							  @NotNull final Regexp regexp,
							  @NotNull final Integer flags)
	{
		final Pattern pattern = Pattern.compile(regexp.getPattern(), flags);
		final Matcher matcher = pattern.matcher(string);

		return matcher.matches();
	}

	/**
	 * Выполняет поиск значений.
	 *
	 * @param string строка.
	 * @return коллекцию с найденными значениями.
	 */
	@Unmodifiable
	@NotNull
	private static List<@NotNull String> find(@NotNull final String string, @NotNull final Regexp regexp)
	{
		return StringRegexp.find(string, regexp, 0);
	}

	/**
	 * Выполняет поиск значений.
	 *
	 * @param string строка.
	 * @param flags флаги регулярного выражения.
	 * @return коллекцию с найденными значениями.
	 */
	@Unmodifiable
	@NotNull
	private static List<@NotNull String> find(@NotNull final String string,
											  @NotNull final Regexp regexp,
											  @NotNull final Integer flags)
	{
		final List<String> localizations = new ArrayList<>();

		final Pattern pattern = Pattern.compile(regexp.getPattern(), flags);
		final Matcher matcher = pattern.matcher(string);

		while (matcher.find()) {
			localizations.add(matcher.group(regexp.getGroup()));
		}

		return Collections.unmodifiableList(localizations);
	}
}
