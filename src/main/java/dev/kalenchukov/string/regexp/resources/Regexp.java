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
	 *
	 * <p>Требования соответствуют стандарту.</p>
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
	 *
	 * <p>Требования соответствуют стандарту.</p>
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
	 *
	 * <p>Требования:
	 *     <ul>
	 *         <li>Имя может быть из букв, цифр, и символов "_", "-", ".";</li>
	 *         <li>Имя не может быть меньше 1 символов;</li>
	 *         <li>Имя не может быть больше 64 символов;</li>
	 *         <li>Содержит символ "@" между именем и доменом;</li>
	 *         <li>Домен не может начинаться с символа "-";</li>
	 *         <li>Домен может быть из букв, цифр и символов "-", ".";</li>
	 *         <li>Домен верхнего уровня не может быть меньше 2 символов.</li>
	 *     </ul>
	 * </p>
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
	 *
	 * <p>Требования:
	 *     <ul>
	 *         <li>Начинается с двухбуквенного кода языка;</li>
	 *         <li>Заканчивается двухбуквенным кодом страны;</li>
	 *         <li>Содержит символ "-" между кодами;</li>
	 *         <li>Не может быть меньше 5 символов;</li>
	 *         <li>Не может быть больше 5 символов.</li>
	 *     </ul>
	 * </p>
	 */
	LOCALIZATION("localization", """
		(?<localization>
			(?<language>[a-z]{2})
			-
			(?<country>[A-Z]{2})
		)
		"""),

	/**
	 * RGB в числовом представлении.
	 *
	 * <p>Требования:
	 *     <ul>
	 *         <li>Состоит из трёх цифр от 0 до 255;</li>
	 *         <li>Содержат символ "," между цифрами;</li>
	 *         <li>Может содержать символ пробела после символа ",";</li>
	 *         <li>Не может быть меньше 5 символов;</li>
	 *         <li>Не может быть больше 11 символов.</li>
	 *     </ul>
	 * </p>
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
	 *
	 * <p>Требования:
	 *     <ul>
	 *         <li>Начинается с символа "#";</li>
	 *         <li>Состоит из цифр шестнадцатеричной системы;</li>
	 *         <li>Не может быть меньше 4 символов;</li>
	 *         <li>Не может быть больше 7 символов.</li>
	 *     </ul>
	 * </p>
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
	 *
	 * <p>Требования:
	 *     <ul>
	 *         <li>Начинается с символа "@";</li>
	 *         <li>Может состоять из латинских букв, цифр и символа "_";</li>
	 *         <li>Не может быть меньше 6 символов.</li>
	 *     </ul>
	 * </p>
	 */
	TELEGRAM("telegram", """
		(?<telegram>
			(?=.{6,})
			@
			(?<name>[0-9A-Z_]{5,})
		)
		"""),

	/**
	 * Метка.
	 *
	 * <p>Требования:
	 *     <ul>
	 *         <li>Начинается с символа "#";</li>
	 *         <li>Может состоять из букв, цифр и символа "_";</li>
	 *         <li>Не может начинаться с символа "_";</li>
	 *         <li>Не может заканчиваться символом "_";</li>
	 *         <li>Не может состоять только из цифр;</li>
	 *         <li>Не может быть меньше 4 символов.</li>
	 *     </ul>
	 * </p>
	 */
	TAG("tag", """
		(?<tag>
			(?=.{4,})
			#
			(?!_)
			(?<name>
				\\p{L}*
				([0-9_]*)?
				(\\p{L}+[0-9_]*)+
			)
			(?<!_)
		)
		"""),

	/**
	 * Цифро десятеричной системы счисления.
	 */
	DIGIT("digit", """
		(?<digit>
			[0-9]+
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
