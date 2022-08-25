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
	 * Конструктор для {@code StringRegexp} запрещающий создавать объект класса.
	 */
	private StringRegexp() {}

	/**
	 * Проверяет, является ли строка RGB в числовом представлении.
	 *
	 * @param string Строка
	 * @return {@code True}, если строка является RGB в числовом представлении, иначе {@code false}.
	 */
	public static boolean isRgb(@NotNull final String string)
	{
		return StringRegexp.is(string, Regexp.RGB);
	}

	/**
	 * Проверяет, является ли строка RGB в шестнадцатеричной системе счисления.
	 *
	 * @param string Строка
	 * @return {@code True}, если строка является RGB в шестнадцатеричной системе счисления, иначе {@code false}.
	 */
	public static boolean isRgbHex(@NotNull final String string)
	{
		return StringRegexp.is(string, Regexp.RGB_HEX);
	}

	/**
	 * Выполняет поиск RGB в числовом представлении.
	 *
	 * @param string Строка.
	 * @return Коллекцию с найденными RGB в числовом представлении.
	 */
	@Unmodifiable
	@NotNull
	public static List<@NotNull String> findRgb(@NotNull final String string)
	{
		return StringRegexp.find(string, Regexp.RGB);
	}

	/**
	 * Выполняет поиск RGB в шестнадцатеричной системе счисления.
	 *
	 * @param string Строка.
	 * @return Коллекцию с найденными RGB в шестнадцатеричной системе счисления.
	 */
	@Unmodifiable
	@NotNull
	public static List<@NotNull String> findRgbHex(@NotNull final String string)
	{
		return StringRegexp.find(string, Regexp.RGB_HEX);
	}

	/**
	 * Проверяет, является ли строка локализацией.
	 *
	 * @param string Строка
	 * @return {@code True}, если строка является локализацией, иначе {@code false}.
	 */
	public static boolean isLocale(@NotNull final String string)
	{
		return StringRegexp.is(string, Regexp.LOCALE);
	}

	/**
	 * Выполняет поиск локализаций.
	 *
	 * @param string Строка.
	 * @return Коллекцию с найденными локализациями.
	 */
	@Unmodifiable
	@NotNull
	public static List<@NotNull String> findLocale(@NotNull final String string)
	{
		return StringRegexp.find(string, Regexp.LOCALE);
	}

	/**
	 * Проверяет, является ли строка IP адресом четвёртой версии.
	 *
	 * @param string Строка
	 * @return {@code True}, если строка является IP адресом четвёртой версии, иначе {@code false}.
	 */
	public static boolean isInet4Address(@NotNull final String string)
	{
		return StringRegexp.is(string, Regexp.INET_4_ADDRESS);
	}

	/**
	 * Выполняет поиск IP адресов четвёртой версии.
	 *
	 * @param string Строка.
	 * @return Коллекцию с найденными IP адресами четвёртой версии.
	 */
	@Unmodifiable
	@NotNull
	public static List<@NotNull String> findInet4Address(@NotNull final String string)
	{
		return StringRegexp.find(string, Regexp.INET_4_ADDRESS);
	}

	/**
	 * Проверяет, является ли строка IP адресом шестой версии.
	 *
	 * @param string Строка
	 * @return {@code True}, если строка является IP адресом шестой версии, иначе {@code false}.
	 */
	public static boolean isInet6Address(@NotNull final String string)
	{
		return StringRegexp.is(string, Regexp.INET_6_ADDRESS);
	}

	/**
	 * Выполняет поиск IP адресов шестой версии.
	 *
	 * @param string Строка.
	 * @return Коллекцию с найденными IP адресами шестой версии.
	 */
	@Unmodifiable
	@NotNull
	public static List<@NotNull String> findInet6Address(@NotNull final String string)
	{
		return StringRegexp.find(string, Regexp.INET_6_ADDRESS);
	}

	/**
	 * Проверяет, является ли строка адресом электронной почты.
	 *
	 * @param string Строка
	 * @return {@code True}, если строка является адресом электронной почты, иначе {@code false}.
	 */
	public static boolean isEmailAddress(@NotNull final String string)
	{
		return StringRegexp.is(string, Regexp.EMAIL_ADDRESS, Pattern.CASE_INSENSITIVE);
	}

	/**
	 * Выполняет поиск адресов электронной почты.
	 *
	 * @param string Строка.
	 * @return Коллекцию с найденными адресами электронной почты.
	 */
	@Unmodifiable
	@NotNull
	public static List<@NotNull String> findEmailAddress(@NotNull final String string)
	{
		return StringRegexp.find(string, Regexp.EMAIL_ADDRESS, Pattern.CASE_INSENSITIVE);
	}

	/**
	 * Проверяет, является ли строка значением.
	 *
	 * @param regexp Регулярное выражение.
	 * @param string Строка.
	 * @return {@code True}, если строка является значением, иначе {@code false}.
	 */
	private static boolean is(@NotNull final String string, @NotNull final Regexp regexp)
	{
		return StringRegexp.is(string, regexp, 0);
	}

	/**
	 * Проверяет, является ли строка значением.
	 *
	 * @param regexp Регулярное выражение.
	 * @param flags Флаги регулярного выражения.
	 * @param string Строка.
	 * @return {@code True}, если строка является значением, иначе {@code false}.
	 */
	private static boolean is(@NotNull final String string,
							  @NotNull final Regexp regexp,
							  @NotNull final Integer flags)
	{
		final Pattern pattern = Pattern.compile("^" + regexp.getPattern() + "$", flags);
		final Matcher matcher = pattern.matcher(string);

		return matcher.matches();
	}

	/**
	 * Выполняет поиск значений.
	 *
	 * @param string Строка.
	 * @return Коллекцию с найденными значениями.
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
	 * @param string Строка.
	 * @param flags Флаги регулярного выражения.
	 * @return Коллекцию с найденными значениями.
	 */
	@Unmodifiable
	@NotNull
	private static List<@NotNull String> find(@NotNull final String string,
											  @NotNull final Regexp regexp,
											  @NotNull final Integer flags)
	{
		final List<String> locales = new ArrayList<>();

		final Pattern pattern = Pattern.compile(regexp.getPattern(), flags);
		final Matcher matcher = pattern.matcher(string);

		while (matcher.find()) {
			locales.add(matcher.group(regexp.getGroup()));
		}

		return Collections.unmodifiableList(locales);
	}
}
