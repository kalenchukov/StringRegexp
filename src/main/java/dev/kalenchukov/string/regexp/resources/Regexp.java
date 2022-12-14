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
	 * IP-адрес четвёртой версии.
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
	 * IP-адрес шестой версии.
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
	 * MAC-адрес.
	 *
	 * <p>Требования:</p>
	 * <ul>
	 * 		<li>Состоит из цифр шестнадцатеричной системы счисления;</li>
	 * 		<li>Не может начинаться с символов "-", ":";</li>
	 * 		<li>После каждых двух цифр, должен быть символ "-" или ":"</li>
	 * 		<li>Не может заканчиваться символами "-", ":";</li>
	 * 		<li>Не может быть меньше 17 символов;</li>
	 * 		<li>Не может быть больше 17 символов.</li>
	 * </ul>
	 */
	MAC_ADDRESS("macAddress", """
		(?<macAddress>
			(?=.{17})
			(?!:|-)
			(?<part1>[0-9A-F]{2})
			(?<separator>:|-)
			(?<part2>[0-9A-F]{2})
			\\3
			(?<part3>[0-9A-F]{2})
			\\3
			(?<part4>[0-9A-F]{2})
			\\3
			(?<part5>[0-9A-F]{2})
			\\3
			(?<part6>[0-9A-F]{2})
			(?<!:|-)
		)
		"""),

	/**
	 * Адрес электронной почты.
	 *
	 * <p>Требования:</p>
	 * <ul>
	 * 		<li>Имя может быть из букв, цифр, и символов "_", "-", ".";</li>
	 * 		<li>Имя не может быть меньше 1 символов;</li>
	 * 		<li>Имя не может быть больше 64 символов;</li>
	 * 		<li>Содержит символ "@" между именем и доменом;</li>
	 * 		<li>Домен не может начинаться с символа "-";</li>
	 * 		<li>Домен может быть из букв, цифр и символов "-", ".";</li>
	 * 		<li>Домен верхнего уровня не может быть меньше 2 символов.</li>
	 * </ul>
	 */
	EMAIL_ADDRESS("emailAddress", """
		(?<emailAddress>
			(?=.{1,64}@)
			(?<local>
				([\\p{L}0-9_-]+)
				(\\.[\\p{L}0-9_-]+)*
			)
			@
			(?<domain>
				[^-]
				([\\p{L}0-9-]+)*
				(\\.[\\p{L}0-9-]+)*
				\\.(?<tld>(\\p{L}{2,}))
			)
		)
		"""),

	/**
	 * Домен.
	 *
	 * <p>Требования:</p>
	 * <ul>
	 * 		<li>Может быть из букв, цифр и символов "-", ".";</li>
	 * 		<li>Не может начинаться с символа "-";</li>
	 * 		<li>Домен верхнего уровня не может быть меньше 2 символов.</li>
	 * </ul>
	 */
	DOMAIN("domain", """
		(?<domain>
			(?!-)
			([\\p{L}0-9-]+)*
			(\\.[\\p{L}0-9-]+)*
			\\.(?<tld>(\\p{L}{2,}))
		)
		"""),

	/**
	 * HTTP URL.
	 *
	 * <p>Требования:</p>
	 * [&lt;схема&gt;://][www.]&lt;домен&gt;[/&lt;путь&gt;][?&lt;параметры&gt;][#&lt;якорь&gt;]
	 */
	URL_HTTP("url", """
		(?<url>
			(?<protocol>
				https?
			)
			://
			(
				www\\.
			)?
			(?<domain>
				(?!-)
				([\\p{L}0-9-]+)*
				(\\.[\\p{L}0-9-]+)*
				\\.(?<tld>(\\p{L}{2,}))
			)
			(
				/
				(?<path>
					[\\p{L}0-9\\-_+\\.=/]*
				)
			)?
			(
				\\?
				(?<param>
					[\\p{L}0-9\\-_+\\.=@\\]\\[~,:;)(&!$'*]*
				)
			)?
			(
				#
				(?<anchor>
					[\\p{L}0-9\\-_+\\.=]*
				)
			)?
		)
		"""),

	/**
	 * Локализация.
	 *
	 * <p>Требования:</p>
	 * <ul>
	 * 		<li>Начинается с двухбуквенного кода языка;</li>
	 * 		<li>Заканчивается двухбуквенным кодом страны;</li>
	 * 		<li>Содержит символ "-" между кодами;</li>
	 * 		<li>Не может быть меньше 5 символов;</li>
	 * 		<li>Не может быть больше 5 символов.</li>
	 * </ul>
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
	 * <p>Требования:</p>
	 * <ul>
	 * 		<li>Состоит из трёх цифр от 0 до 255;</li>
	 * 		<li>Содержат символ "," между цифрами;</li>
	 * 		<li>Может содержать символ пробела после символа ",";</li>
	 * 		<li>Не может быть меньше 5 символов;</li>
	 * 		<li>Не может быть больше 11 символов.</li>
	 * </ul>
	 */
	RGB_NUMERIC("rgb", """
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
	 * <p>Требования:</p>
	 * <ul>
	 * 		<li>Начинается с символа "#";</li>
	 * 		<li>Состоит из цифр шестнадцатеричной системы;</li>
	 * 		<li>Не может быть меньше 4 символов;</li>
	 * 		<li>Не может быть больше 7 символов.</li>
	 * </ul>
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
	 * <p>Требования:</p>
	 * <ul>
	 * 		<li>Начинается с символа "@";</li>
	 * 		<li>Может состоять из латинских букв, цифр и символа "_";</li>
	 * 		<li>Не может быть меньше 6 символов.</li>
	 * </ul>
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
	 * <p>Требования:</p>
	 * <ul>
	 * 		<li>Начинается с символа "#";</li>
	 * 		<li>Может состоять из букв, цифр и символа "_";</li>
	 * 		<li>Не может начинаться с символа "_";</li>
	 * 		<li>Не может заканчиваться символом "_";</li>
	 * 		<li>Не может состоять только из цифр;</li>
	 * 		<li>Не может быть меньше 4 символов.</li>
	 * </ul>
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
	 * Двухбуквенный код страны.
	 *
	 * <p>Соответствует стандарту ISO 3166-1 alpha-2.</p>
	 * <p>Требования:</p>
	 * <ul>
	 * 		<li>Может состоять из букв латинского алфавита;</li>
	 * 		<li>Все буквы должны быть в прописном регистре;</li>
	 * 		<li>Не может быть меньше 2 символов;</li>
	 * 		<li>Не может быть больше 2 символов.</li>
	 * </ul>
	 */
	COUNTRY_CODE_ALPHA2("countryCode", """
		(?<countryCode>
			[A-Z]{2}
		)
		"""),

	/**
	 * Двухбуквенный код страны.
	 *
	 * <p>Соответствует стандарту ISO 3166-1 alpha-3.</p>
	 * <p>Требования:</p>
	 * <ul>
	 * 		<li>Может состоять из букв латинского алфавита;</li>
	 * 		<li>Все буквы должны быть в прописном регистре;</li>
	 * 		<li>Не может быть меньше 3 символов;</li>
	 * 		<li>Не может быть больше 3 символов.</li>
	 * </ul>
	 */
	COUNTRY_CODE_ALPHA3("countryCode", """
		(?<countryCode>
			[A-Z]{3}
		)
		"""),

	/**
	 * Двухбуквенный код страны.
	 *
	 * <p>Соответствует стандарту ISO 3166-1 numeric.</p>
	 * <p>Требования:</p>
	 * <ul>
	 * 		<li>Может состоять из цифр;</li>
	 * 		<li>Не может быть меньше 3 символов;</li>
	 * 		<li>Не может быть больше 3 символов.</li>
	 * </ul>
	 */
	COUNTRY_CODE_NUMERIC3("countryCode", """
		(?<countryCode>
			[0-9]{3}
		)
		"""),

	/**
	 * Цифра двоичной системы счисления.
	 *
	 * <p>Требования:</p>
	 * <ul>
	 * 		<li>Может состоять из цифр "0", "1";</li>
	 * 		<li>Не может быть меньше 1 символа.</li>
	 * </ul>
	 */
	DIGIT_BINARY("digit", """
		(?<digit>
			[0-1]+
		)
		"""),

	/**
	 * Цифра троичной системы счисления.
	 *
	 * <p>Требования:</p>
	 * <ul>
	 * 		<li>Может состоять из цифр от 0 до 2;</li>
	 * 		<li>Не может быть меньше 1 символа.</li>
	 * </ul>
	 */
	DIGIT_TERNARY("digit", """
		(?<digit>
			[0-2]+
		)
		"""),

	/**
	 * Цифра восьмеричной системы счисления.
	 *
	 * <p>Требования:</p>
	 * <ul>
	 * 		<li>Может состоять из цифр от 0 до 7;</li>
	 * 		<li>Не может быть меньше 1 символа.</li>
	 * </ul>
	 */
	DIGIT_OCTAL("digit", """
		(?<digit>
			[0-7]+
		)
		"""),

	/**
	 * Цифра десятеричной системы счисления.
	 *
	 * <p>Требования:</p>
	 * <ul>
	 * 		<li>Может состоять из цифр;</li>
	 * 		<li>Не может быть меньше 1 символа.</li>
	 * </ul>
	 */
	DIGIT_DECIMAL("digit", """
		(?<digit>
			[0-9]+
		)
		"""),

	/**
	 * Цифра двенадцатеричной системы счисления.
	 *
	 * <p>Требования:</p>
	 * <ul>
	 * 		<li>Может состоять из букв "A", "B" и цифр;</li>
	 * 		<li>Не может быть меньше 1 символа.</li>
	 * </ul>
	 */
	DIGIT_DUODECIMAL("digit", """
		(?<digit>
			[0-9A-B]+
		)
		"""),

	/**
	 * Цифра шестнадцатеричной системы счисления.
	 *
	 * <p>Требования:</p>
	 * <ul>
	 * 		<li>Может состоять из букв от "A" до "F" и цифр;</li>
	 * 		<li>Не может быть меньше 1 символа.</li>
	 * </ul>
	 */
	DIGIT_HEXADECIMAL("digit", """
		(?<digit>
			[0-9A-F]+
		)
		"""),

	/**
	 * Число десятеричной системы счисления.
	 *
	 * <p>Требования:</p>
	 * <ul>
	 * 		<li>Не может начинаться с символов ".", ",";</li>
	 * 		<li>Не может заканчиваться символами ".", ",";</li>
	 * 		<li>Может состоять из цифр и символов ".", ",";</li>
	 * 		<li>Не может быть меньше 1 символа.</li>
	 * </ul>
	 */
	NUMBER("number", """
		(?<number>
			(?!(\\.|,))
			([0-9,.]+)
			(?<!(\\.|,))
		)
		"""),

	/**
	 * Слово.
	 *
	 * <p>Требования:</p>
	 * <ul>
	 * 		<li>Может состоять из букв и символа "-";</li>
	 * 		<li>Не может быть меньше 1 символа.</li>
	 * </ul>
	 */
	WORD("word", """
		(?<word>
			\\b
			[\\p{L}-]+
			\\b
		)
		"""),

	/**
	 * Буква.
	 *
	 * <p>Требования:</p>
	 * <ul>
	 * 		<li>Может состоять из букв;</li>
	 * 		<li>Не может быть меньше 1 символа;</li>
	 * 		<li>Не может быть больше 1 символа.</li>
	 * </ul>
	 */
	LETTER("letter", """
		(?<letter>
			[\\p{L}]{1}
		)
		"""),

	/**
	 * Область CDATA.
	 *
	 * <p>Требования:</p>
	 * <ul>
	 * 		<li>Должен начинаться со строки "&lt;![CDATA[";</li>
	 * 		<li>Не может содержать строк "]]&gt;";</li>
	 * 		<li>Должен заканчиваться строкой "]]&gt;".</li>
	 * </ul>
	 */
	CDATA("cdata", """
		(?<cdata>
			<!\\[CDATA\\[
			(?<value>((?!\\]\\]>).)*?)
			\\]\\]>
		)
		"""),

	 /**
	 * Самозакрывающийся HTML-тег.
	 *
	 * <p>Требования:</p>
	 * <ul>
	 * 		<li>Должен начинаться с символа "&lt;";</li>
	 * 		<li>Название тега должно состоять из букв;</li>
	 * 		<li>Название параметра должно состоять из букв, цифр и символов "-", "_";</li>
	 * 		<li>Значение параметра в двойных кавычках не может содержать символ "&quot;";</li>
	 * 		<li>Значение параметра в одинарных кавычках не может содержать символы "&#39;";</li>
	 * 		<li>Значение параметра без кавычек не может содержать пробелы и символы "&quot;", "&#39;", "&lt;", "&gt;", "&#96;";</li>
	 * 		<li>Должен заканчиваться строкой "/&gt;".</li>
	 * </ul>
	 */
	HTML_SELF_CLOSING_TAG("tag", """
		(?<tag>
		<
		(?<name>[a-z]+)
		(?<params>
			(
				\\s+
				[0-9a-z\\-_]+
				(
					\\s*=\\s*
					(
						(
							(?:")
							[^"]*
							(?:")
						)
						|
						(
							(?:')
							[^']*
							(?:')
						)
						|
						(
							[^"'=\\s<>`]+
						)
					)
				)?
			)+
		)*
		\\s*
		/>
		)
		"""),

	/**
	 * Открывающий HTML-тег.
	 *
	 * <p>Требования:</p>
	 * <ul>
	 * 		<li>Должен начинаться с символа "&lt;";</li>
	 * 		<li>Название тега должно состоять из букв;</li>
	 * 		<li>Название параметра должно состоять из букв, цифр и символов "-", "_";</li>
	 * 		<li>Значение параметра в двойных кавычках не может содержать символ "&quot;";</li>
	 * 		<li>Значение параметра в одинарных кавычках не может содержать символы "&#39;";</li>
	 * 		<li>Значение параметра без кавычек не может содержать пробелы и символы "&quot;", "&#39;", "&lt;", "&gt;", "&#96;";</li>
	 * 		<li>Должен заканчиваться символом "&gt;".</li>
	 * </ul>
	 */
	HTML_START_TAG("tag", """
		(?<tag>
			<
			(?<name>[a-z]+)
			(?<params>
				(
					\\s+
					[0-9a-z\\-_]+
					(
						\\s*=\\s*
						(
							(
								(?:")
								[^"]*
								(?:")
							)
							|
							(
								(?:')
								[^']*
								(?:')
							)
							|
							(
								[^"'=\\s<>`]+
							)
						)
					)?
				)+
			)*
			\\s*
			>
		)
		"""),

	/**
	 * Закрывающий HTML-тег.
	 *
	 * <p>Требования:</p>
	 * <ul>
	 * 		<li>Должен начинаться со строки "&lt;/";</li>
	 * 		<li>Должен состоять из букв;</li>
	 * 		<li>Должен заканчиваться символом "&gt;".</li>
	 * </ul>
	 */
	HTML_END_TAG("tag", """
		(?<tag>
			</
			(?<name>[a-z]+)
			\\s*
			>
		)
		"""),

	/**
	 * HTML-комментарий.
	 *
	 * <p>Требования:</p>
	 * <ul>
	 * 		<li>Должен начинаться со строки "&lt;!--";</li>
	 * 		<li>Не может начинаться с символа "&lt;";</li>
	 * 		<li>Не может начинаться со строки "-&gt;";</li>
	 * 		<li>Не может содержать строк "&lt;!--", "--&gt;", "--!&gt;";</li>
	 * 		<li>Не может заканчиваться строкой "&lt;!-";</li>
	 * 		<li>Должен заканчиваться строкой "--&gt;".</li>
	 * </ul>
	 */
	HTML_COMMENT("comment", """
		(?<comment>
			<!--
			(?!(<|->))
			(?<value>((?!<!--|-->|--!>).)*?)
			(?!(<!-))
			-->
		)
		"""),

	/**
	 * Тип HTML-документа.
	 *
	 * <p>Требования:</p>
	 * <ul>
	 * 		<li>Должен начинаться со строки "&lt;!DOCTYPE";</li>
	 * 		<li>Корневой элемент должен быть из букв;</li>
	 * 		<li>Публичность должна быть из букв;</li>
	 * 		<li>DTD должно быть заключён в одинарные или двойные кавычки;</li>
	 * 		<li>Регистрация должна быть из символа "+" или "-";</li>
	 * 		<li>Название организации должно быть из букв, цифр, пробела и символов "." и "-";</li>
	 * 		<li>Тип документа должно быть из букв, цифр, пробела и символа ".";</li>
	 * 		<li>Код языка должен быть из букв;</li>
	 * 		<li>URL должен быть заключён в одинарные или двойные кавычки;</li>
	 * 		<li>URL должен быть из любых символов;</li>
	 * 		<li>Должен заканчиваться символом "&gt;".</li>
	 * </ul>
	 */
	HTML_DOCTYPE("doctype", """
		(?<doctype>
			<!DOCTYPE
			\\s+
			(?<rootElement>[A-Z]+)
			(
				\\s+
				(?<public>[A-Z]+)
				\\s+
				("|')
				(?<dtd>
					(?<registration>[+-])
					//
					(?<organization>[0-9A-Z.\\-\\s]+)
					//
					(?<documentType>[0-9A-Z.\\s]+)
					//
					(?<language>[A-Z]+)
				)
				\\5
				\\s*
				("|')
				(?<url>[^"']+?)
				\\11
			)?
			\\s*
			>
		)
		"""),

	/**
	 * HTML-сущность в виде имени.
	 *
	 * <p>Требования:</p>
	 * <ul>
	 * 		<li>Должен начинаться с символа "&amp;";</li>
	 * 		<li>Не может начинаться c цифр;</li>
	 * 		<li>Не может быть только из цифр;</li>
	 * 		<li>Может состоять из букв и цифр;</li>
	 * 		<li>Не может быть меньше 4 символов;</li>
	 * 		<li>Должен заканчиваться символом ";".</li>
	 * </ul>
	 */
	HTML_ENTITY_NAME("entity", """
		(?<entity>
			(?=.{4,})
			&
			(?![0-9]+)
			(?<name>[0-9A-Z]+)
			;
		)
		"""),

	/**
	 * HTML-сущность в виде unicode.
	 *
	 * <p>Требования:</p>
	 * <ul>
	 * 		<li>Должен начинаться со строки "&amp;#X";</li>
	 * 		<li>Может состоять из диапазона букв A-F и цифр;</li>
	 * 		<li>Не может быть меньше 6 символов;</li>
	 * 		<li>Должен заканчиваться символом ";".</li>
	 * </ul>
	 */
	HTML_ENTITY_UNICODE("entity", """
		(?<entity>
			(?=.{6,})
			&#X
			(?<unicode>
				0*
				(?<unicodeLeast>[0-9A-F]+)
			)
			;
		)
		"""),

	/**
	 * HTML-сущность в виде числа.
	 *
	 * <p>Требования:</p>
	 * <ul>
	 * 		<li>Должен начинаться со строки "&amp;#";</li>
	 * 		<li>Может состоять из цифр;</li>
	 * 		<li>Не может быть меньше 5 символов;</li>
	 * 		<li>Должен заканчиваться символом ";".</li>
	 * </ul>
	 */
	HTML_ENTITY_NUMERIC("entity", """
		(?<entity>
			(?=.{5,})
			&#
			(?<numeric>
				0*
				(?<numericLeast>[0-9]+)
			)
			;
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
	 * @param pattern шаблон регулярного выражения.
	 */
	Regexp(@NotNull final String group, @NotNull final String pattern)
	{
		this.group = group;
		this.pattern = pattern;
	}

	/**
	 * Возвращает основную группу регулярного выражения.
	 *
	 * @return основную группу регулярного выражения.
	 */
	@NotNull
	public String getGroup()
	{
		return this.group;
	}

	/**
	 * Возвращает шаблон регулярного выражения.
	 *
	 * @return шаблон регулярного выражения.
	 */
	@NotNull
	public String getPattern()
	{
		return this.pattern.replaceAll("[\n\t\s]*", "");
	}
}
