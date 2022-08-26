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

import org.jetbrains.annotations.NotNull;

/**
 * Перечисление шаблонов регулярных выражений.
 */
public enum Regexp
{
	/**
	 * IP адрес четвёртой версии.
	 */
	INET_4_ADDRESS("inet4Address","""
		(?<inet4Address>
			(?=.{7,16})
			(?<part1>25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.
			(?<part2>25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.
			(?<part3>25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.
			(?<part4>25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)
		)
		"""),

	/**
	 * IP адрес шестой версии.
	 */
	INET_6_ADDRESS("inet6Address", """
		(?<inet6Address>
			(?=.{2,40})
			(?=(:{2}|[0-9A-F]{1,4}:))
			(?!:{3,})
			(([0-9A-F]{1,4})(:|::){1}|::)?
			(?<!:{3,})
			(([0-9A-F]{1,4})(:|::){1}|::)?
			(?<!:{3,})
			(([0-9A-F]{1,4})(:|::){1}|::)?
			(?<!:{3,})
			(([0-9A-F]{1,4})(:|::){1}|::)?
			(?<!:{3,})
			(([0-9A-F]{1,4})(:|::){1}|::)?
			(?<!:{3,})
			(([0-9A-F]{1,4})(:|::){1}|::)?
			(?<!:{3,})
			(([0-9A-F]{1,4})(:|::){1}|::)?
			(?<!:{3,})
			([0-9A-F]{1,4})?
			((?<=:{2})|(?<!:))
		)
		"""),

	/**
	 * Адрес электронной почты.
	 */
	EMAIL_ADDRESS("emailAddress", """
		(?<emailAddress>
			(?=.{1,64}@)
			(?<localPart>
				([\\p{L}0-9_-]+)
				(\\.[\\p{L}0-9_-]+)*
			)
			@
			(?<domain>
				[^-]
				([\\p{L}0-9-]+)*
				(\\.[\\p{L}0-9-]+)*
				\\.(?<domainTld>(\\p{L}{2,}))
			)
		)
		"""),

	/**
	 * Локализация.
	 */
	LOCALE("locale", """
		(?<locale>
			(?<language>[a-z]{2})
			-
			(?<country>[A-Z]{2})
		)
		"""),

	/**
	 * RGB в числовом представлении.
	 */
	RGB("rgb", """
		(?<rgb>
			(?=.{5,11})
			(?<red>25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?),\\s?
			(?<green>25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?),\\s?
			(?<blue>25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)
		)
		"""),

	/**
	 * RGB в шестнадцатеричной системе счисления.
	 */
	RGB_HEX("rgb", """
		(?<rgb>
			(?=.{4,7})
			#
			(?<red>[0-9A-F]{2})
			(?<green>[0-9A-F]{2})
			(?<blue>[0-9A-F]{2})
		)
		"""),

	/**
	 * Телеграм канал.
	 */
	TELEGRAM("telegram", """
		(?<telegram>
			(?=.{6,})
			@
			(?<name>[0-9A-Z_]{5,})
		)
		""");

	/**
	 * Основная группа регулярного выражения.
	 */
	@NotNull
	private final String group;

	/**
	 * Шаблон регулярного выражения.
	 */
	@NotNull
	private final String pattern;

	/**
	 * Конструктор для {@code Regexp}.
	 *
	 * @param pattern Шаблон регулярного выражения.
	 */
	Regexp(@NotNull final String group, @NotNull final String pattern)
	{
		this.group = group;
		this.pattern = pattern;
	}

	/**
	 * Возвращает основную группу регулярного выражения.
	 *
	 * @return Основную группу регулярного выражения.
	 */
	@NotNull
	public String getGroup()
	{
		return this.group;
	}

	/**
	 * Возвращает шаблон регулярного выражения.
	 *
	 * @return Шаблон регулярного выражения.
	 */
	@NotNull
	public String getPattern()
	{
		return this.pattern.replaceAll("[\n\t]*", "");
	}
}
