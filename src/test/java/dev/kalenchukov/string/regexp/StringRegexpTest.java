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

import org.junit.Test;

import static org.junit.Assert.*;

public class StringRegexpTest
{
	/**
	 * Проверка корректной локализации.
	 */
	@Test
	public void isLocalizationCorrect()
	{
		assertTrue(StringRegexp.isLocalization("ru-RU"));
	}

	/**
	 * Проверка некорректной локализации.
	 */
	@Test
	public void isLocalizationNotCorrect()
	{
		assertFalse(StringRegexp.isLocalization(""));
		assertFalse(StringRegexp.isLocalization(" "));

		assertFalse(StringRegexp.isLocalization("RU-RU"));
		assertFalse(StringRegexp.isLocalization("RU-ru"));
		assertFalse(StringRegexp.isLocalization("ru-ru"));

		assertFalse(StringRegexp.isLocalization("ru"));
		assertFalse(StringRegexp.isLocalization("RU"));
	}

	/**
	 * Проверка поиска локализаций.
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

		String string = """
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

		assertArrayEquals(localization, StringRegexp.findLocalization(string).toArray());
	}

	/**
	 * Проверка корректного IP-адреса четвёртой версии.
	 */
	@Test
	public void isIpAddressVersion4Correct()
	{
		assertTrue(StringRegexp.isInet4Address("192.168.1.1"));
		assertTrue(StringRegexp.isInet4Address("1.1.1.1"));
		assertTrue(StringRegexp.isInet4Address("0.0.0.0"));
	}

	/**
	 * Проверка некорректного IP-адреса четвёртой версии.
	 */
	@Test
	public void isIpAddressVersion4NotCorrect()
	{
		assertFalse(StringRegexp.isInet4Address(""));
		assertFalse(StringRegexp.isInet4Address(" "));

		assertFalse(StringRegexp.isInet4Address("1.1.1.1."));
		assertFalse(StringRegexp.isInet4Address(".6.6.6.6"));

		assertFalse(StringRegexp.isInet4Address("2.2.2.2.2"));
		assertFalse(StringRegexp.isInet4Address("3"));
		assertFalse(StringRegexp.isInet4Address("4."));
		assertFalse(StringRegexp.isInet4Address("5.5"));

		assertFalse(StringRegexp.isInet4Address("257.168.1.1"));
		assertFalse(StringRegexp.isInet4Address("192.257.1.1"));
		assertFalse(StringRegexp.isInet4Address("192.168.257.1"));
		assertFalse(StringRegexp.isInet4Address("192.168.1.256"));
	}

	/**
	 * Проверка поиска IP-адресов четвёртой версии.
	 */
	@Test
	public void findIpAddressVersion4()
	{
		String[] inet4Address = {
			"192.168.1.1",
			"1.1.1.1",
			"0.0.0.0",
			"10.222.170.80"
		};

		String string = """
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

		assertArrayEquals(inet4Address, StringRegexp.findInet4Address(string).toArray());
	}

	/**
	 * Проверка корректного IP-адреса шестой версии.
	 */
	@Test
	public void isIpAddressVersion6Correct()
	{
		assertTrue(StringRegexp.isInet6Address("2001:0DB8:11A3:09D7:1F34:8A2E:07A0:765D"));
	}

	/**
	 * Проверка некорректного IP-адреса шестой версии.
	 */
	@Test
	public void isIpAddressVersion6NotCorrect()
	{
		assertFalse(StringRegexp.isInet6Address("2001:0db8:11a3:09d7:1F34:8A2E:07A0:765D"));
	}

	/**
	 * Проверка корректного IP-адреса шестой версии без учёта регистра букв.
	 */
	@Test
	public void isIpAddressVersion6IgnoreCaseCorrect()
	{
		assertTrue(StringRegexp.isInet6AddressIgnoreCase("2001:0db8:11a3:09d7:1F34:8A2E:07A0:765D"));

		assertTrue(StringRegexp.isInet6AddressIgnoreCase("2001:0db8:0000:0000:0000:0000:ae21:AD12"));
		assertTrue(StringRegexp.isInet6AddressIgnoreCase("0000:0000:0000:0000:0000:0000:ae21:AD12"));

		assertTrue(StringRegexp.isInet6AddressIgnoreCase("2001:0DB8:0000:0000:0000:0000:ae21:1"));
		assertTrue(StringRegexp.isInet6AddressIgnoreCase("2001:0DB8:0000:1:0000:0000:ae21:AD12"));
		assertTrue(StringRegexp.isInet6AddressIgnoreCase("1:0000:0000:0000:0000:0000:ae21:AD12"));

		assertTrue(StringRegexp.isInet6AddressIgnoreCase("::0DB8:11A3:09D7:1F34:8A2E:07a0:765D"));
		assertTrue(StringRegexp.isInet6AddressIgnoreCase("0DB8:11A3:09D7:1F34::07a0:765D"));
		assertTrue(StringRegexp.isInet6AddressIgnoreCase("2001:0DB8:11A3:09D7:1F34:07a0:07A0::"));

		assertTrue(StringRegexp.isInet6AddressIgnoreCase("::ae21:AD12"));
		assertTrue(StringRegexp.isInet6AddressIgnoreCase("AD12::ae21::"));

		assertTrue(StringRegexp.isInet6AddressIgnoreCase("2001::"));
		assertTrue(StringRegexp.isInet6AddressIgnoreCase("::2001"));

		assertTrue(StringRegexp.isInet6AddressIgnoreCase("::"));

		assertTrue(StringRegexp.isInet6AddressIgnoreCase("::1"));
		assertTrue(StringRegexp.isInet6AddressIgnoreCase("1::"));

		assertTrue(StringRegexp.isInet6AddressIgnoreCase("EF98:3:0:0:0:0:2F3B:7654"));
		assertTrue(StringRegexp.isInet6AddressIgnoreCase("EF98:3::2f3b:7654"));
		assertTrue(StringRegexp.isInet6AddressIgnoreCase("2001:DB8::ae21:AD12"));
	}

	/**
	 * Проверка некорректного IP-адреса шестой версии без учёта регистра букв.
	 */
	@Test
	public void isIpAddressVersion6IgnoreCaseNotCorrect()
	{
		assertFalse(StringRegexp.isInet6AddressIgnoreCase(""));
		assertFalse(StringRegexp.isInet6AddressIgnoreCase(" "));

		assertFalse(StringRegexp.isInet6AddressIgnoreCase("1000"));
		assertFalse(StringRegexp.isInet6AddressIgnoreCase("1000:"));
		assertFalse(StringRegexp.isInet6AddressIgnoreCase(":1000"));

		assertFalse(StringRegexp.isInet6AddressIgnoreCase(":"));
		assertFalse(StringRegexp.isInet6AddressIgnoreCase(":::"));
		assertFalse(StringRegexp.isInet6AddressIgnoreCase("::::"));

		assertFalse(StringRegexp.isInet6AddressIgnoreCase("2001:0db8:11a3:09d7:1F34:8A2E:07A0:765D:765D"));

		assertFalse(StringRegexp.isInet6AddressIgnoreCase(":0db8:11a3:09d7:1f34:8A2E:07A0:765D"));
		assertFalse(StringRegexp.isInet6AddressIgnoreCase("2001:0db8:11a3:09d7:1F34:8A2E:07A0:"));

		assertFalse(StringRegexp.isInet6AddressIgnoreCase(":::0db8:11a3:09d7:1f34:8A2E:07A0:765D"));
		assertFalse(StringRegexp.isInet6AddressIgnoreCase("2001:0db8:11a3:09d7:1F34:8A2E:07A0:::"));

		assertFalse(StringRegexp.isInet6AddressIgnoreCase("::::0db8:11a3:09d7:1F34:8A2E:07A0:765D"));
		assertFalse(StringRegexp.isInet6AddressIgnoreCase("0db8:11a3:09d7::::1f34:8A2E:07A0:765D"));
		assertFalse(StringRegexp.isInet6AddressIgnoreCase("2001:0db8:11a3:09d7:1F34:8A2E:07A0::::"));

		assertFalse(StringRegexp.isInet6AddressIgnoreCase(":0db8:11a3:09d7:1f34:8A2E:07A0:765D:1000"));
		assertFalse(StringRegexp.isInet6AddressIgnoreCase("1000:2001:0db8:11a3:09D7:1F34:8A2E:07A0:"));

		assertFalse(StringRegexp.isInet6AddressIgnoreCase(":0db8:11a3:09d7:1f34:8A2E:07A0:765D:1"));
		assertFalse(StringRegexp.isInet6AddressIgnoreCase("1:2001:0db8:11a3:09d7:1f34:8A2E:07A0:"));

		assertFalse(StringRegexp.isInet6AddressIgnoreCase("::0db8:11a3:09d7:1f34:8A2E:07A0:765D:1"));
		assertFalse(StringRegexp.isInet6AddressIgnoreCase("1:2001:0db8:11a3:09d7:1f34:8A2E:07A0::"));

		assertFalse(StringRegexp.isInet6AddressIgnoreCase(":::0db8:11a3:09d7:1f34:8a2e:07A0:765D:1"));
		assertFalse(StringRegexp.isInet6AddressIgnoreCase("1:2001:0db8:11a3:09d7:1F34:8A2E:07A0:::"));

		assertFalse(StringRegexp.isInet6AddressIgnoreCase("0db8:11a3::::8A2E:07A0:765D"));
		assertFalse(StringRegexp.isInet6AddressIgnoreCase("0db8:11a3::::::8A2E:07A0:765D"));
	}

	/**
	 * Проверка корректного адреса электронной почты.
	 */
	@Test
	public void isEmailAddressCorrect()
	{
		assertTrue(StringRegexp.isEmailAddress("aleksey.kalenchukov@yandex.ru"));
		assertTrue(StringRegexp.isEmailAddress("aleksey.kalenchukov_@yandex.ru"));
		assertTrue(StringRegexp.isEmailAddress("_aleksey.kalenchukov_@yandex.ru"));
		assertTrue(StringRegexp.isEmailAddress("-aleksey.kalenchukov-@yandex.ru"));
		assertTrue(StringRegexp.isEmailAddress("aleksey.kalenchukov@mail.yandex.ru"));
		assertTrue(StringRegexp.isEmailAddress("alekseykalenchukov@yandex.ru"));
		assertTrue(StringRegexp.isEmailAddress("aleksey.kalenchukov@yandex.com"));
		assertTrue(StringRegexp.isEmailAddress("aleksey-kalenchukov@yandex.ru"));
		assertTrue(StringRegexp.isEmailAddress("aleksey_kalenchukov@yandex.ru"));
		assertTrue(StringRegexp.isEmailAddress("AlekseyKalenchukov@yandex.ru"));
		assertTrue(StringRegexp.isEmailAddress("АлексейКаленчуков@яндекс.ру"));
		assertTrue(StringRegexp.isEmailAddress("a.k@yandex.ru"));
		assertTrue(StringRegexp.isEmailAddress("k@yandex.ru"));
		assertTrue(StringRegexp.isEmailAddress("kalenchukov@yandex.ru"));
		assertTrue(StringRegexp.isEmailAddress("kalenchukov@y.ru"));
		assertTrue(StringRegexp.isEmailAddress("aleksey.kalenchukovkalenchukovkalenchukovkalenchukovkalenchukov@yandex.ru"));
		assertTrue(StringRegexp.isEmailAddress("aleksey123.kalenchukov123@123yandex.ru"));
		assertTrue(StringRegexp.isEmailAddress("aleksey.kalenchukov@123.yandex.ru"));
		assertTrue(StringRegexp.isEmailAddress("123aleksey.kalenchukov@123.yandex.ru"));
		assertTrue(StringRegexp.isEmailAddress("123.456@123.ru"));
		assertTrue(StringRegexp.isEmailAddress("123456@123.ru"));
	}

	/**
	 * Проверка некорректного адреса электронной почты.
	 */
	@Test
	public void isEmailAddressNotCorrect()
	{
		assertFalse(StringRegexp.isEmailAddress(""));
		assertFalse(StringRegexp.isEmailAddress(" "));

		assertFalse(StringRegexp.isEmailAddress(".aleksey.kalenchukov@yandex.ru"));
		assertFalse(StringRegexp.isEmailAddress("aleksey.kalenchukov.@yandex.ru"));
		assertFalse(StringRegexp.isEmailAddress("aleksey..kalenchukov@yandex.ru"));

		assertFalse(StringRegexp.isEmailAddress("aleksey.kalenchukov@yandex"));
		assertFalse(StringRegexp.isEmailAddress("aleksey.kalenchukov@yandex..ru"));
		assertFalse(StringRegexp.isEmailAddress("aleksey.kalenchukov.yandex.ru"));
		assertFalse(StringRegexp.isEmailAddress("aleksey.kalenchukov@yandex.r"));
		assertFalse(StringRegexp.isEmailAddress("aleksey.kalenchukov@-yandex.ru"));

		assertFalse(StringRegexp.isEmailAddress("aleksey.kalenchukovkalenchukovkalenchukovkalenchukovkalenchukovkalenchukov@yandex.ru"));
	}

	/**
	 * Проверка домена.
	 */
	@Test
	public void isDomainCorrect()
	{
		assertTrue(StringRegexp.isDomain("kalenchukov.dev"));
		assertTrue(StringRegexp.isDomain("regexp.string.kalenchukov.dev"));
		assertTrue(StringRegexp.isDomain("aleksey.123.kalenchukov.ru"));
		assertTrue(StringRegexp.isDomain("123.aleksey.kalenchukov.ru"));
	}

	/**
	 * Проверка домена.
	 */
	@Test
	public void isDomainNotCorrect()
	{
		assertFalse(StringRegexp.isDomain(""));
		assertFalse(StringRegexp.isDomain(" "));

		assertFalse(StringRegexp.isDomain("kalenchukov,dev"));
		assertFalse(StringRegexp.isDomain("regexp.string.kalenchukov.d"));
	}

	/**
	 * Проверка URL HTTP.
	 */
	@Test
	public void isUrlHttp()
	{
		assertTrue(StringRegexp.isUrlHttp("http://kalenchukov.dev/hello/world/?java=18&hello=world123#anchor"));
		assertTrue(StringRegexp.isUrlHttp("https://kalenchukov.dev/hello/world/?java=18&hello=world123#anchor"));

		assertTrue(StringRegexp.isUrlHttp("http://www.kalenchukov.dev/hello/world?java=18&hello=world123#anchor"));
		assertTrue(StringRegexp.isUrlHttp("http://www.kalenchukov.dev/hello/world/?java=18&hello=world123#anchor"));
		assertTrue(StringRegexp.isUrlHttp("http://www.kalenchukov.dev/hello/world/?java=18&hello=world123#"));

		assertTrue(StringRegexp.isUrlHttp("http://www.kalenchukov.dev/hello/world/?java=18&hello=world123"));
		assertTrue(StringRegexp.isUrlHttp("http://www.kalenchukov.dev/hello/world/?java=18&hello="));
		assertTrue(StringRegexp.isUrlHttp("http://www.kalenchukov.dev/hello/world/?java=18&"));
		assertTrue(StringRegexp.isUrlHttp("http://www.kalenchukov.dev/hello/world/?java=18"));
		assertTrue(StringRegexp.isUrlHttp("http://www.kalenchukov.dev/hello/world/?"));

		assertTrue(StringRegexp.isUrlHttp("http://www.kalenchukov.dev/hello/world/"));
		assertTrue(StringRegexp.isUrlHttp("http://www.kalenchukov.dev/hello/world"));

		assertTrue(StringRegexp.isUrlHttp("http://www.kalenchukov.dev/"));
		assertTrue(StringRegexp.isUrlHttp("http://www.kalenchukov.dev"));

		assertTrue(StringRegexp.isUrlHttp("http://www.kalenchukov.dev/hello/world"));
		assertTrue(StringRegexp.isUrlHttp("http://www.kalenchukov.dev/?java=18&hello=world123#anchor"));
		assertTrue(StringRegexp.isUrlHttp("http://www.kalenchukov.dev/#anchor"));
	}

	/**
	 * Проверка корректного RGB в числовом представлении.
	 */
	@Test
	public void isRgbNumericCorrect()
	{
		assertTrue(StringRegexp.isRgbNumeric("215,200,166"));
		assertTrue(StringRegexp.isRgbNumeric("0,0,0"));

		assertTrue(StringRegexp.isRgbNumeric("215, 200, 166"));
		assertTrue(StringRegexp.isRgbNumeric("0, 0, 0"));

		assertTrue(StringRegexp.isRgbNumeric("255,155, 55"));
		assertTrue(StringRegexp.isRgbNumeric("1,1,1"));
	}

	/**
	 * Проверка некорректного RGB в числовом представлении.
	 */
	@Test
	public void isRgbNumericNotCorrect()
	{
		assertFalse(StringRegexp.isRgbNumeric(""));
		assertFalse(StringRegexp.isRgbNumeric(" "));

		assertFalse(StringRegexp.isRgbNumeric("0,0"));
		assertFalse(StringRegexp.isRgbNumeric("0"));

		assertFalse(StringRegexp.isRgbNumeric("215,  200, 166"));
		assertFalse(StringRegexp.isRgbNumeric("215, 200,  166"));

		assertFalse(StringRegexp.isRgbNumeric("-1, 0, 0"));
		assertFalse(StringRegexp.isRgbNumeric("0, -1, 0"));
		assertFalse(StringRegexp.isRgbNumeric("0, 0, -1"));

		assertFalse(StringRegexp.isRgbNumeric("215,200,"));
		assertFalse(StringRegexp.isRgbNumeric("1,1,1,"));

		assertFalse(StringRegexp.isRgbNumeric("256,155,55"));
		assertFalse(StringRegexp.isRgbNumeric("255,256,55"));
		assertFalse(StringRegexp.isRgbNumeric("255,155,256"));
	}

	/**
	 * Проверка корректного двухбуквенного кода страны.
	 */
	@Test
	public void isCountryCodeAlpha2Correct()
	{
		assertTrue(StringRegexp.isCountryCodeAlpha2("RU"));
		assertTrue(StringRegexp.isCountryCodeAlpha2("BY"));
	}

	/**
	 * Проверка некорректного двухбуквенного кода страны.
	 */
	@Test
	public void isCountryCodeAlpha2NotCorrect()
	{
		assertFalse(StringRegexp.isCountryCodeAlpha2(""));
		assertFalse(StringRegexp.isCountryCodeAlpha2(" "));

		assertFalse(StringRegexp.isCountryCodeAlpha2("ru"));

		assertFalse(StringRegexp.isCountryCodeAlpha2("rub"));

		assertFalse(StringRegexp.isCountryCodeAlpha2("#RU"));
		assertFalse(StringRegexp.isCountryCodeAlpha2("0RU"));
	}

	/**
	 * Проверка корректного трёхбуквенного кода страны.
	 */
	@Test
	public void isCountryCodeAlpha3Correct()
	{
		assertTrue(StringRegexp.isCountryCodeAlpha3("RUS"));
		assertTrue(StringRegexp.isCountryCodeAlpha3("BLR"));
	}

	/**
	 * Проверка некорректного трёхбуквенного кода страны.
	 */
	@Test
	public void isCountryCodeAlpha3NotCorrect()
	{
		assertFalse(StringRegexp.isCountryCodeAlpha3(""));
		assertFalse(StringRegexp.isCountryCodeAlpha3(" "));

		assertFalse(StringRegexp.isCountryCodeAlpha3("rus"));

		assertFalse(StringRegexp.isCountryCodeAlpha3("rub"));

		assertFalse(StringRegexp.isCountryCodeAlpha3("#RUS"));
		assertFalse(StringRegexp.isCountryCodeAlpha3("0RUS"));
	}

	/**
	 * Проверка корректного трёхциферного кода страны.
	 */
	@Test
	public void isCountryCodeNumeric3Correct()
	{
		assertTrue(StringRegexp.isCountryCodeNumeric3("052"));
		assertTrue(StringRegexp.isCountryCodeNumeric3("112"));
		assertTrue(StringRegexp.isCountryCodeNumeric3("643"));
	}

	/**
	 * Проверка некорректного трёхциферного кода страны.
	 */
	@Test
	public void isCountryCodeNumeric3NotCorrect()
	{
		assertFalse(StringRegexp.isCountryCodeNumeric3(""));
		assertFalse(StringRegexp.isCountryCodeNumeric3(" "));

		assertFalse(StringRegexp.isCountryCodeNumeric3("52"));

		assertFalse(StringRegexp.isCountryCodeNumeric3("0052"));

		assertFalse(StringRegexp.isCountryCodeNumeric3("#643"));
	}

	/**
	 * Проверка корректного RGB в шестнадцатеричной системе счисления.
	 */
	@Test
	public void isRgbHexCorrect()
	{
		assertTrue(StringRegexp.isRgbHex("#FFFFFF"));
		assertTrue(StringRegexp.isRgbHex("#ABCDEF"));

		assertTrue(StringRegexp.isRgbHex("#000000"));
		assertTrue(StringRegexp.isRgbHex("#123456"));
	}

	/**
	 * Проверка некорректного RGB в шестнадцатеричной системе счисления.
	 */
	@Test
	public void isRgbHexNotCorrect()
	{
		assertFalse(StringRegexp.isRgbHex(""));
		assertFalse(StringRegexp.isRgbHex(" "));

		assertFalse(StringRegexp.isRgbHex("FFFFFF"));

		assertFalse(StringRegexp.isRgbHex("#23394W"));
		assertFalse(StringRegexp.isRgbHex("#ARDBZG"));
	}

	/**
	 * Проверка корректного RGB в шестнадцатеричной системе счисления без учёта регистра букв.
	 */
	@Test
	public void isRgbHexIgnoreCaseCorrect()
	{
		assertTrue(StringRegexp.isRgbHexIgnoreCase("#fffFFF"));
		assertTrue(StringRegexp.isRgbHexIgnoreCase("#abcDEF"));

		assertTrue(StringRegexp.isRgbHexIgnoreCase("#000000"));
		assertTrue(StringRegexp.isRgbHexIgnoreCase("#123456"));
	}

	/**
	 * Проверка некорректного RGB в шестнадцатеричной системе счисления без учёта регистра букв.
	 */
	@Test
	public void isRgbHexIgnoreCaseNotCorrect()
	{
		assertFalse(StringRegexp.isRgbHexIgnoreCase(""));
		assertFalse(StringRegexp.isRgbHexIgnoreCase(" "));

		assertFalse(StringRegexp.isRgbHexIgnoreCase("fffFFF"));

		assertFalse(StringRegexp.isRgbHexIgnoreCase("#23394w"));
		assertFalse(StringRegexp.isRgbHexIgnoreCase("#ardBZG"));
	}

	/**
	 * Проверка корректного MAC-адреса.
	 */
	@Test
	public void isMacAddressCorrect()
	{
		assertTrue(StringRegexp.isMacAddress("00-EF-CD-EF-11-22"));
		assertTrue(StringRegexp.isMacAddress("00:EF:CD:EF:11:22"));
	}

	/**
	 * Проверка некорректного MAC-адреса.
	 */
	@Test
	public void isMacAddressNotCorrect()
	{
		assertFalse(StringRegexp.isMacAddress(""));
		assertFalse(StringRegexp.isMacAddress(" "));

		assertFalse(StringRegexp.isMacAddress("-00-EF-CD-EF-11-22"));
		assertFalse(StringRegexp.isMacAddress("-00-EF-CD-EF-11-22-"));
		assertFalse(StringRegexp.isMacAddress("00-EF-CD-EF-11-22-"));

		assertFalse(StringRegexp.isMacAddress(":00:EF:CD:EF:11:22"));
		assertFalse(StringRegexp.isMacAddress(":00:EF:CD:EF:11:22:"));
		assertFalse(StringRegexp.isMacAddress("00:EF:CD:EF:11:22:"));

		assertFalse(StringRegexp.isMacAddress("00:EF:CD:EF:11:2"));
		assertFalse(StringRegexp.isMacAddress("00:EW:CD:EF:11:22"));
		assertFalse(StringRegexp.isMacAddress("00:ef:CD:EF:11:22"));
		assertFalse(StringRegexp.isMacAddress("00:EF:CD::EF:11:22"));
		assertFalse(StringRegexp.isMacAddress("00:EF::EF:11:22"));
	}

	/**
	 * Проверка корректного MAC-адреса без учёта регистра букв.
	 */
	@Test
	public void isMacAddressIgnoreCaseCorrect()
	{
		assertTrue(StringRegexp.isMacAddressIgnoreCase("00-EF-cd-EF-11-22"));
		assertTrue(StringRegexp.isMacAddressIgnoreCase("00:ef:CD:EF:11:22"));
	}

	/**
	 * Проверка некорректного MAC-адреса без учёта регистра букв.
	 */
	@Test
	public void isMacAddressIgnoreCaseNotCorrect()
	{
		assertFalse(StringRegexp.isMacAddressIgnoreCase(""));
		assertFalse(StringRegexp.isMacAddressIgnoreCase(" "));

		assertFalse(StringRegexp.isMacAddress("00:EF:CD:EF:11:2"));
		assertFalse(StringRegexp.isMacAddress("00:ew:CD:EF:11:22"));
		assertFalse(StringRegexp.isMacAddress("00:ef:CD:EF:11:22"));
		assertFalse(StringRegexp.isMacAddress("00:EF:CD::ef:11:22"));
		assertFalse(StringRegexp.isMacAddress("00:EF::ef:11:22"));
	}

	/**
	 * Проверка корректного телеграм канала.
	 */
	@Test
	public void isTelegramCorrect()
	{
		assertTrue(StringRegexp.isTelegram("@kalenchukov"));
		assertTrue(StringRegexp.isTelegram("@Kalenchukov"));
		assertTrue(StringRegexp.isTelegram("@KALENCHUKOV"));

		assertTrue(StringRegexp.isTelegram("@kalen_chukov"));
		assertTrue(StringRegexp.isTelegram("@kalenchukov_"));
		assertTrue(StringRegexp.isTelegram("@_kalenchukov"));
	}

	/**
	 * Проверка некорректного телеграм канала.
	 */
	@Test
	public void isTelegramNotCorrect()
	{
		assertFalse(StringRegexp.isTelegram(""));
		assertFalse(StringRegexp.isTelegram(" "));

		assertFalse(StringRegexp.isTelegram("kalenchukov"));

		assertFalse(StringRegexp.isTelegram("@kalenchukov."));
		assertFalse(StringRegexp.isTelegram("@kalenchukov-"));
		assertFalse(StringRegexp.isTelegram("@kalenchukov+"));

		assertFalse(StringRegexp.isTelegram("@kalen-chukov"));
	}

	/**
	 * Проверка корректной метки.
	 */
	@Test
	public void isTagCorrect()
	{
		assertTrue(StringRegexp.isTag("#tag"));
		assertTrue(StringRegexp.isTag("#Tag"));
		assertTrue(StringRegexp.isTag("#TAG"));

		assertTrue(StringRegexp.isTag("#ta_g"));

		assertTrue(StringRegexp.isTag("#123tag"));
		assertTrue(StringRegexp.isTag("#tag123"));
		assertTrue(StringRegexp.isTag("#tag_123"));

		assertTrue(StringRegexp.isTag("#тег"));
		assertTrue(StringRegexp.isTag("#тег123"));
		assertTrue(StringRegexp.isTag("#тег_123"));
	}

	/**
	 * Проверка некорректной метки.
	 */
	@Test
	public void isTagNotCorrect()
	{
		assertFalse(StringRegexp.isTag(""));
		assertFalse(StringRegexp.isTag(" "));

		assertFalse(StringRegexp.isTag("tag"));

		assertFalse(StringRegexp.isTag("#t"));
		assertFalse(StringRegexp.isTag("#ta"));

		assertFalse(StringRegexp.isTag("#tag."));
		assertFalse(StringRegexp.isTag("#tag-"));
		assertFalse(StringRegexp.isTag("#ta-g"));
		assertFalse(StringRegexp.isTag("#tag+tag"));

		assertFalse(StringRegexp.isTag("#_tag"));
		assertFalse(StringRegexp.isTag("#tag_"));

		assertFalse(StringRegexp.isTag("#_"));
		assertFalse(StringRegexp.isTag("#__"));
		assertFalse(StringRegexp.isTag("#___"));

		assertFalse(StringRegexp.isTag("#123"));
		assertFalse(StringRegexp.isTag("#123_"));
		assertFalse(StringRegexp.isTag("#_123"));
	}

	/**
	 * Проверка корректного HTML комментария.
	 */
	@Test
	public void isHtmlCommentCorrect()
	{
		assertTrue(StringRegexp.isHtmlComment("<!---->"));
		assertTrue(StringRegexp.isHtmlComment("<!-- -->"));
		assertTrue(StringRegexp.isHtmlComment("<!--  -->"));

		assertTrue(StringRegexp.isHtmlComment("<!-- 12345 -->"));
		assertTrue(StringRegexp.isHtmlComment("<!-- Комментарий -->"));
		assertTrue(StringRegexp.isHtmlComment("<!-- Comment -->"));

		assertTrue(StringRegexp.isHtmlComment("<!-- Comment-->"));
		assertTrue(StringRegexp.isHtmlComment("<!--Комментарий -->"));

		assertTrue(StringRegexp.isHtmlComment("""
			<!--

			-->"""));

		assertTrue(StringRegexp.isHtmlComment("""
			<!--
				12345
			-->"""));

		assertTrue(StringRegexp.isHtmlComment("""
			<!--
				12345 -->"""));
	}

	/**
	 * Проверка некорректного HTML комментария.
	 */
	@Test
	public void isHtmlCommentNotCorrect()
	{
		assertFalse(StringRegexp.isHtmlComment(""));
		assertFalse(StringRegexp.isHtmlComment(" "));

		assertFalse(StringRegexp.isHtmlComment("34546<!-- Comment -->"));

		assertFalse(StringRegexp.isHtmlComment("<!- Комментарий -->"));
		assertFalse(StringRegexp.isHtmlComment("<-- Комментарий -->"));

		assertFalse(StringRegexp.isHtmlComment("<!-- Комментарий ->"));
		assertFalse(StringRegexp.isHtmlComment("<!-- Комментарий --"));

		assertFalse(StringRegexp.isHtmlComment("<!--< Comment -->"));
		assertFalse(StringRegexp.isHtmlComment("<!---> Comment -->"));
		assertFalse(StringRegexp.isHtmlComment("<!-- Comment<!--->"));

		assertFalse(StringRegexp.isHtmlComment("<!-- Comm<!--ent -->"));
		assertFalse(StringRegexp.isHtmlComment("<!-- Comm-->ent -->"));
		assertFalse(StringRegexp.isHtmlComment("<!-- Comm--!>ent -->"));

		assertFalse(StringRegexp.isHtmlComment("<!-- <!--Comment -->"));
		assertFalse(StringRegexp.isHtmlComment("<!-- -->Comment -->"));
		assertFalse(StringRegexp.isHtmlComment("<!-- --!>Comment -->"));

		assertFalse(StringRegexp.isHtmlComment("<!-- Comment<!-- -->"));
		assertFalse(StringRegexp.isHtmlComment("<!-- Comment--> -->"));
		assertFalse(StringRegexp.isHtmlComment("<!-- Comment--!> -->"));

		// Оканчивается на "-->\n", а должно на "-->".
		assertFalse(StringRegexp.isHtmlComment("""
			<!--
				Comment
			-->
			"""));
	}

	/**
	 * Проверка корректной HTML сущности в виде имени.
	 */
	@Test
	public void isHtmlEntityNameCorrect()
	{
		assertTrue(StringRegexp.isHtmlEntityName("&dd;"));
		assertTrue(StringRegexp.isHtmlEntityName("&dollar;"));
		assertTrue(StringRegexp.isHtmlEntityName("&DownArrowBar;"));
		assertTrue(StringRegexp.isHtmlEntityName("&ecir;"));
		assertTrue(StringRegexp.isHtmlEntityName("&DD;"));
		assertTrue(StringRegexp.isHtmlEntityName("&frac14;"));
	}

	/**
	 * Проверка некорректной HTML сущности в виде имени.
	 */
	@Test
	public void isHtmlEntityNameNotCorrect()
	{
		assertFalse(StringRegexp.isHtmlEntityName(""));
		assertFalse(StringRegexp.isHtmlEntityName(" "));

		assertFalse(StringRegexp.isHtmlEntityName("34546&DownArrowBar;"));

		assertFalse(StringRegexp.isHtmlEntityName("&"));
		assertFalse(StringRegexp.isHtmlEntityName(";"));
		assertFalse(StringRegexp.isHtmlEntityName("&;"));
		assertFalse(StringRegexp.isHtmlEntityName("&d;"));
		assertFalse(StringRegexp.isHtmlEntityName("ecir;"));
		assertFalse(StringRegexp.isHtmlEntityName("&ecir"));
		assertFalse(StringRegexp.isHtmlEntityName("&3124;"));
		assertFalse(StringRegexp.isHtmlEntityName("&1DownArrowBar;"));
	}

	/**
	 * Проверка корректной HTML сущности в виде числа.
	 */
	@Test
	public void isHtmlEntityNumericCorrect()
	{
		assertTrue(StringRegexp.isHtmlEntityNumeric("&#038;"));
		assertTrue(StringRegexp.isHtmlEntityNumeric("&#38;"));
		assertTrue(StringRegexp.isHtmlEntityNumeric("&#256;"));
		assertTrue(StringRegexp.isHtmlEntityNumeric("&#0038;"));
		assertTrue(StringRegexp.isHtmlEntityNumeric("&#8501;"));
		assertTrue(StringRegexp.isHtmlEntityNumeric("&#10590;"));
		assertTrue(StringRegexp.isHtmlEntityNumeric("&#010590;"));
		assertTrue(StringRegexp.isHtmlEntityNumeric("&#0010590;"));
		assertTrue(StringRegexp.isHtmlEntityNumeric("&#0000000000000010590;"));
	}

	/**
	 * Проверка некорректной HTML сущности в виде числа.
	 */
	@Test
	public void isHtmlEntityNumericNotCorrect()
	{
		assertFalse(StringRegexp.isHtmlEntityNumeric(""));
		assertFalse(StringRegexp.isHtmlEntityNumeric(" "));

		assertFalse(StringRegexp.isHtmlEntityNumeric("34546&#8501;"));

		assertFalse(StringRegexp.isHtmlEntityNumeric("&#3;"));

		assertFalse(StringRegexp.isHtmlEntityNumeric("#256;"));
		assertFalse(StringRegexp.isHtmlEntityNumeric("&256;"));
		assertFalse(StringRegexp.isHtmlEntityNumeric("&#256"));

		assertFalse(StringRegexp.isHtmlEntityNumeric("&#2D56;"));
	}

	/**
	 * Проверка корректной HTML сущности в виде unicode.
	 */
	@Test
	public void isHtmlEntityUnicodeCorrect()
	{
		assertTrue(StringRegexp.isHtmlEntityUnicode("&#XB0;"));
		assertTrue(StringRegexp.isHtmlEntityUnicode("&#x394;"));
		assertTrue(StringRegexp.isHtmlEntityUnicode("&#X2223;"));
		assertTrue(StringRegexp.isHtmlEntityUnicode("&#X154;"));
		assertTrue(StringRegexp.isHtmlEntityUnicode("&#x00000000000000BB;"));
	}

	/**
	 * Проверка некорректной HTML сущности в виде unicode.
	 */
	@Test
	public void isHtmlEntityUnicodeNotCorrect()
	{
		assertFalse(StringRegexp.isHtmlEntityUnicode(""));
		assertFalse(StringRegexp.isHtmlEntityUnicode(" "));

		assertFalse(StringRegexp.isHtmlEntityUnicode("34546&#XB0;"));

		assertFalse(StringRegexp.isHtmlEntityUnicode("&#x3;"));

		assertFalse(StringRegexp.isHtmlEntityUnicode("#XB0;"));
		assertFalse(StringRegexp.isHtmlEntityUnicode("&XB0;"));
		assertFalse(StringRegexp.isHtmlEntityUnicode("&#B0;"));
		assertFalse(StringRegexp.isHtmlEntityUnicode("&#B0"));
	}

	/**
	 * Проверка корректной типа HTML документа.
	 */
	@Test
	public void isHtmlDoctypeCorrect()
	{
		assertTrue(StringRegexp.isHtmlDoctype("<!DOCTYPE html>"));
		assertTrue(StringRegexp.isHtmlDoctype("<!DOCTYPE  html >"));

		assertTrue(StringRegexp.isHtmlDoctype("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">"));
		assertTrue(StringRegexp.isHtmlDoctype("<!DOCTYPE HTML PUBLIC \"+//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">"));

		assertTrue(StringRegexp.isHtmlDoctype("<!DOCTYPE HTML PUBLIC   '-//W3C//DTD HTML 4.01 Transitional//EN' \"http://www.w3.org/TR/html4/loose.dtd\">"));
		assertTrue(StringRegexp.isHtmlDoctype("<!DOCTYPE HTML  PUBLIC \"-//W3C//DTD HTML 4.01 Frameset//EN\"  'http://www.w3.org/TR/html4/frameset.dtd'>"));
		assertTrue(StringRegexp.isHtmlDoctype("<!DOCTYPE html  PUBLIC  '-//W3C//DTD XHTML 1.0 Strict//EN'   'http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd' >"));

		assertTrue(StringRegexp.isHtmlDoctype("""
			<!DOCTYPE html PUBLIC
				'-//W3C//DTD XHTML 1.0 Frameset//EN'
			'http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd'
			>"""
		));
		assertTrue(StringRegexp.isHtmlDoctype("""
			<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
				"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">"""
		));
	}

	/**
	 * Проверка некорректного типа HTML документа.
	 */
	@Test
	public void isHtmlDoctypeNotCorrect()
	{
		assertFalse(StringRegexp.isHtmlDoctype(""));
		assertFalse(StringRegexp.isHtmlDoctype(" "));

		assertFalse(StringRegexp.isHtmlDoctype("<DOCTYPE>"));

		assertFalse(StringRegexp.isHtmlDoctype("<DOCTYPE html>"));

		assertFalse(StringRegexp.isHtmlDoctype("text<!DOCTYPE html>"));

		assertFalse(StringRegexp.isHtmlDoctype("<!DOCTYPE HTML PUBLIC>"));

		assertFalse(StringRegexp.isHtmlDoctype("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"\">"));
		assertFalse(StringRegexp.isHtmlDoctype("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\">"));
		assertFalse(StringRegexp.isHtmlDoctype("<!DOCTYPE HTML PUBLIC \"//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">"));
		assertFalse(StringRegexp.isHtmlDoctype("<!DOCTYPE HTML PUBLIC \"-//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">"));
		assertFalse(StringRegexp.isHtmlDoctype("<!DOCTYPE HTML PUBLIC \"-//W3C//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">"));
		assertFalse(StringRegexp.isHtmlDoctype("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01\" \"http://www.w3.org/TR/html4/strict.dtd\">"));
		assertFalse(StringRegexp.isHtmlDoctype("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//\" \"http://www.w3.org/TR/html4/strict.dtd\">"));

		assertFalse(StringRegexp.isHtmlDoctype("<!DOCTYPE HTML PUBLIC \"\" \"http://www.w3.org/TR/html4/strict.dtd\">"));
		assertFalse(StringRegexp.isHtmlDoctype("<!DOCTYPE HTML PUBLIC \"http://www.w3.org/TR/html4/strict.dtd\">"));

		assertFalse(StringRegexp.isHtmlDoctype("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR'/html4/strict.dtd\">"));
		assertFalse(StringRegexp.isHtmlDoctype("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" 'http://www.w3.org/TR\"/html4/strict.dtd'>"));

		assertFalse(StringRegexp.isHtmlDoctype("""
			<!DOCTYPE html PUBLIC
				'-//W3C//DTD XHTML 1.0 Frameset//EN'
			'http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd'
			>
			"""
		));
	}

	/**
	 * Проверка корректного закрывающего HTML тега.
	 */
	@Test
	public void isHtmlEndTagCorrect()
	{
		assertTrue(StringRegexp.isHtmlEndTag("</form>"));
		assertTrue(StringRegexp.isHtmlEndTag("</form >"));
		assertTrue(StringRegexp.isHtmlEndTag("</form  >"));
	}

	/**
	 * Проверка некорректного закрывающего HTML тега.
	 */
	@Test
	public void isHtmlEndTagNotCorrect()
	{
		assertFalse(StringRegexp.isHtmlEndTag(""));
		assertFalse(StringRegexp.isHtmlEndTag(" "));

		assertFalse(StringRegexp.isHtmlEndTag("</ form>"));
		assertFalse(StringRegexp.isHtmlEndTag("< /form>"));

		assertFalse(StringRegexp.isHtmlEndTag("text</form>"));
	}

	/**
	 * Проверка корректного самозакрывающегося HTML тега.
	 */
	@Test
	public void isHtmlSelfClosingTagCorrect()
	{
		assertTrue(StringRegexp.isHtmlSelfClosingTag("<meta/>"));
		assertTrue(StringRegexp.isHtmlSelfClosingTag("<meta />"));

		assertTrue(StringRegexp.isHtmlSelfClosingTag("<meta charset='UTF-8' />"));
		assertTrue(StringRegexp.isHtmlSelfClosingTag("<meta charset=\"UTF-8\"/>"));

		assertTrue(StringRegexp.isHtmlSelfClosingTag("<meta />"));
		assertTrue(StringRegexp.isHtmlSelfClosingTag("<meta  />"));

		assertTrue(StringRegexp.isHtmlSelfClosingTag("<meta value/>"));
		assertTrue(StringRegexp.isHtmlSelfClosingTag("<meta value=yes/>"));
		assertTrue(StringRegexp.isHtmlSelfClosingTag("<meta value = yes />"));
		assertTrue(StringRegexp.isHtmlSelfClosingTag("<meta value  =  yes  />"));

		assertTrue(StringRegexp.isHtmlSelfClosingTag("<input id=''/>"));
		assertTrue(StringRegexp.isHtmlSelfClosingTag("<input id=\"\"/>"));

		assertTrue(StringRegexp.isHtmlSelfClosingTag("<meta charset='UTF-8'/>"));
		assertTrue(StringRegexp.isHtmlSelfClosingTag("<meta charset=\"UTF-8\"/>"));

		assertTrue(StringRegexp.isHtmlSelfClosingTag("<meta name='123'/>"));
		assertTrue(StringRegexp.isHtmlSelfClosingTag("<meta name=' текст'/>"));
		assertTrue(StringRegexp.isHtmlSelfClosingTag("<meta name=' текст 123'/>"));

		assertTrue(StringRegexp.isHtmlSelfClosingTag("<meta name=\"input name\"/>"));
		assertTrue(StringRegexp.isHtmlSelfClosingTag("<meta name=\"0123456789\"/>"));

		assertTrue(StringRegexp.isHtmlSelfClosingTag("<input id='input-id'/>"));
		assertTrue(StringRegexp.isHtmlSelfClosingTag("<input id=\"input-id-123\"/>"));

		assertTrue(StringRegexp.isHtmlSelfClosingTag("<input name=\"viewport\" content=\"width=device-width, initial-scale=1, user-scalable=0\"/>"));
		assertTrue(StringRegexp.isHtmlSelfClosingTag("<input name=viewport content=\"width=device-width, initial-scale=1, user-scalable=0\"/>"));

		assertTrue(StringRegexp.isHtmlSelfClosingTag("<input id  ='ID' qwe-attr =  \"my_attribute\"  />"));
		assertTrue(StringRegexp.isHtmlSelfClosingTag("<input qwe_attr =  \"my_attribute\"  />"));

		assertTrue(StringRegexp.isHtmlSelfClosingTag("""
			<link
				rel="icon"
			type='image/png'
				sizes=""
				href=''/>"""));
	}

	/**
	 * Проверка некорректного самозакрывающегося HTML тега.
	 */
	@Test
	public void isHtmlSelfClosingTagNotCorrect()
	{
		assertFalse(StringRegexp.isHtmlSelfClosingTag(""));
		assertFalse(StringRegexp.isHtmlSelfClosingTag(" "));

		assertFalse(StringRegexp.isHtmlSelfClosingTag("text<input/>"));

		assertFalse(StringRegexp.isHtmlSelfClosingTag("<link href=/>"));

		assertFalse(StringRegexp.isHtmlSelfClosingTag("<input name='text\"/>"));

		assertFalse(StringRegexp.isHtmlSelfClosingTag("<input name=text'/>"));
		assertFalse(StringRegexp.isHtmlSelfClosingTag("<input name='text/>"));
		assertFalse(StringRegexp.isHtmlSelfClosingTag("<input name='te'xt'/>"));

		assertFalse(StringRegexp.isHtmlSelfClosingTag("<input name=text\"/>"));
		assertFalse(StringRegexp.isHtmlSelfClosingTag("<input name=\"text/>"));
		assertFalse(StringRegexp.isHtmlSelfClosingTag("<input name=\"te\"xt\"/>"));

		assertFalse(StringRegexp.isHtmlSelfClosingTag("< input/>"));

		assertFalse(StringRegexp.isHtmlSelfClosingTag("<input/"));
		assertFalse(StringRegexp.isHtmlSelfClosingTag("input/>"));

		assertFalse(StringRegexp.isHtmlSelfClosingTag("<input id=='input-id'/>"));
		assertFalse(StringRegexp.isHtmlSelfClosingTag("<input id = = 'input-id'/>"));

		assertFalse(StringRegexp.isHtmlSelfClosingTag("<input id=''input-id'/>"));
		assertFalse(StringRegexp.isHtmlSelfClosingTag("<input id='input-id''/>"));

		assertFalse(StringRegexp.isHtmlSelfClosingTag("""
			<link
				rel="icon"
			type='image/png'
				sizes=""
				href=/>
			"""));
	}

	/**
	 * Проверка корректного открывающего HTML тега.
	 */
	@Test
	public void isHtmlStartTagCorrect()
	{
		assertTrue(StringRegexp.isHtmlStartTag("<form>"));
		assertTrue(StringRegexp.isHtmlStartTag("<form >"));
		assertTrue(StringRegexp.isHtmlStartTag("<form  >"));

		assertTrue(StringRegexp.isHtmlStartTag("<input value>"));
		assertTrue(StringRegexp.isHtmlStartTag("<input value=yes>"));
		assertTrue(StringRegexp.isHtmlStartTag("<input value = yes >"));
		assertTrue(StringRegexp.isHtmlStartTag("<input value  =  yes  >"));

		assertTrue(StringRegexp.isHtmlStartTag("<input id=''>"));
		assertTrue(StringRegexp.isHtmlStartTag("<input id=\"\">"));

		assertTrue(StringRegexp.isHtmlStartTag("<input type=\"checkbox\">"));
		assertTrue(StringRegexp.isHtmlStartTag("<input type='checkbox'>"));

		assertTrue(StringRegexp.isHtmlStartTag("<input name='123'>"));
		assertTrue(StringRegexp.isHtmlStartTag("<input name=' текст'>"));
		assertTrue(StringRegexp.isHtmlStartTag("<input name=' текст 123'>"));

		assertTrue(StringRegexp.isHtmlStartTag("<input name=\"input name\">"));
		assertTrue(StringRegexp.isHtmlStartTag("<input name=\"0123456789\">"));

		assertTrue(StringRegexp.isHtmlStartTag("<input id='input-id'>"));
		assertTrue(StringRegexp.isHtmlStartTag("<input id=\"input-id-123\">"));

		assertTrue(StringRegexp.isHtmlStartTag("<meta charset=\"UTF-8\">"));

		assertTrue(StringRegexp.isHtmlStartTag("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, user-scalable=0\">"));
		assertTrue(StringRegexp.isHtmlStartTag("<meta name=viewport content=\"width=device-width, initial-scale=1, user-scalable=0\">"));

		assertTrue(StringRegexp.isHtmlStartTag("<input id  ='ID' qwe-attr =  \"my_attribute\"  >"));
		assertTrue(StringRegexp.isHtmlStartTag("<input qwe_attr =  \"my_attribute\"  >"));

		assertTrue(StringRegexp.isHtmlStartTag("""
			<link
				rel="icon"
			type='image/png'
				sizes=""
				href=''>"""));
	}

	/**
	 * Проверка некорректного открывающего HTML тега.
	 */
	@Test
	public void isHtmlStartTagNotCorrect()
	{
		assertFalse(StringRegexp.isHtmlStartTag(""));
		assertFalse(StringRegexp.isHtmlStartTag(" "));

		assertFalse(StringRegexp.isHtmlStartTag("text<input>"));

		assertFalse(StringRegexp.isHtmlStartTag("<link href=>"));

		assertFalse(StringRegexp.isHtmlStartTag("<input name='text\">"));

		assertFalse(StringRegexp.isHtmlStartTag("<input name=text'>"));
		assertFalse(StringRegexp.isHtmlStartTag("<input name='text>"));
		assertFalse(StringRegexp.isHtmlStartTag("<input name='te'xt'>"));

		assertFalse(StringRegexp.isHtmlStartTag("<input name=text\">"));
		assertFalse(StringRegexp.isHtmlStartTag("<input name=\"text>"));
		assertFalse(StringRegexp.isHtmlStartTag("<input name=\"te\"xt\">"));

		assertFalse(StringRegexp.isHtmlStartTag("< input>"));

		assertFalse(StringRegexp.isHtmlStartTag("<input"));
		assertFalse(StringRegexp.isHtmlStartTag("input>"));

		assertFalse(StringRegexp.isHtmlStartTag("<input id=='input-id'>"));
		assertFalse(StringRegexp.isHtmlStartTag("<input id = = 'input-id'>"));

		assertFalse(StringRegexp.isHtmlStartTag("<input id=''input-id'>"));
		assertFalse(StringRegexp.isHtmlStartTag("<input id='input-id''>"));

		assertFalse(StringRegexp.isHtmlStartTag("""
			<link
				rel="icon"
			type='image/png'
				sizes=""
				href=>
			"""));
	}

	/**
	 * Проверка корректной области CDATA.
	 */
	@Test
	public void isCDataCorrect()
	{
		assertTrue(StringRegexp.isCData("<![CDATA[]]>"));
		assertTrue(StringRegexp.isCData("<![CDATA[ ]]>"));
		assertTrue(StringRegexp.isCData("<![CDATA[  ]]>"));

		assertTrue(StringRegexp.isCData("<![CDATA[ 12345 ]]>"));
		assertTrue(StringRegexp.isCData("<![CDATA[ Текст ]]>"));
		assertTrue(StringRegexp.isCData("<![CDATA[ Text ]]>"));

		assertTrue(StringRegexp.isCData("<![CDATA[ Text]]>"));
		assertTrue(StringRegexp.isCData("<![CDATA[Текст ]]>"));

		assertTrue(StringRegexp.isCData("""
			<![CDATA[

			]]>"""));

		assertTrue(StringRegexp.isCData("""
			<![CDATA[
				12345
			]]>"""));

		assertTrue(StringRegexp.isCData("""
			<![CDATA[
				12345 ]]>"""));
	}

	/**
	 * Проверка некорректной области CDATA.
	 */
	@Test
	public void isCDataNotCorrect()
	{
		assertFalse(StringRegexp.isCData(""));
		assertFalse(StringRegexp.isCData(" "));

		assertFalse(StringRegexp.isCData("2134<![CDATA[ Text ]]>"));

		assertFalse(StringRegexp.isCData("<[CDATA[ Текст ]]>"));

		assertFalse(StringRegexp.isCData("<![CDATA[ Текст ]>"));
		assertFalse(StringRegexp.isCData("<![CDATA[ Текст ]]"));

		assertFalse(StringRegexp.isCData("<![CDATA[ Te]]>xt ]]>"));
		assertFalse(StringRegexp.isCData("<![CDATA[ ]]>Text ]]>"));
		assertFalse(StringRegexp.isCData("<![CDATA[ Text]]> ]]>"));

		// Оканчивается на "]]>\n", а должно на "]]>".
		assertFalse(StringRegexp.isCData("""
			<![CDATA[
				Text
			]]>
			"""));
	}

	/**
	 * Проверка корректной цифры двоичной системы счисления.
	 */
	@Test
	public void isDigitBinaryCorrect()
	{
		assertTrue(StringRegexp.isDigitBinary("0"));
		assertTrue(StringRegexp.isDigitBinary("1"));
		assertTrue(StringRegexp.isDigitBinary("01010101"));
		assertTrue(StringRegexp.isDigitBinary("1010101"));
	}

	/**
	 * Проверка некорректной цифры двоичной системы счисления.
	 */
	@Test
	public void isDigitBinaryNotCorrect()
	{
		assertFalse(StringRegexp.isDigitBinary(""));
		assertFalse(StringRegexp.isDigitBinary(" "));

		assertFalse(StringRegexp.isDigitBinary("1.0"));
		assertFalse(StringRegexp.isDigitBinary("012"));
		assertFalse(StringRegexp.isDigitBinary("1a0"));
		assertFalse(StringRegexp.isDigitBinary("0a"));
	}

	/**
	 * Проверка корректной цифры троичной системы счисления.
	 */
	@Test
	public void isDigitTernaryCorrect()
	{
		assertTrue(StringRegexp.isDigitTernary("0"));
		assertTrue(StringRegexp.isDigitTernary("1"));
		assertTrue(StringRegexp.isDigitTernary("2"));
		assertTrue(StringRegexp.isDigitTernary("0101201012"));
		assertTrue(StringRegexp.isDigitTernary("120102101"));
	}

	/**
	 * Проверка некорректной цифры троичной системы счисления.
	 */
	@Test
	public void isDigitTernaryNotCorrect()
	{
		assertFalse(StringRegexp.isDigitTernary(""));
		assertFalse(StringRegexp.isDigitTernary(" "));

		assertFalse(StringRegexp.isDigitTernary("1.0"));
		assertFalse(StringRegexp.isDigitTernary("1a0"));
		assertFalse(StringRegexp.isDigitTernary("0a"));
	}

	/**
	 * Проверка корректной цифры восьмеричной системы счисления.
	 */
	@Test
	public void isDigitOctalCorrect()
	{
		assertTrue(StringRegexp.isDigitOctal("01234567"));
		assertTrue(StringRegexp.isDigitOctal("12014302101"));
	}

	/**
	 * Проверка некорректной цифры восьмеричной системы счисления.
	 */
	@Test
	public void isDigitOctalNotCorrect()
	{
		assertFalse(StringRegexp.isDigitOctal(""));
		assertFalse(StringRegexp.isDigitOctal(" "));
	}

	/**
	 * Проверка корректной цифры десятеричной системы счисления.
	 */
	@Test
	public void isDigitDecimalCorrect()
	{
		assertTrue(StringRegexp.isDigitDecimal("0"));
		assertTrue(StringRegexp.isDigitDecimal("0123"));
		assertTrue(StringRegexp.isDigitDecimal("6516166848498494"));
	}

	/**
	 * Проверка некорректной цифры десятеричной системы счисления.
	 */
	@Test
	public void isDigitDecimalNotCorrect()
	{
		assertFalse(StringRegexp.isDigitDecimal(""));
		assertFalse(StringRegexp.isDigitDecimal(" "));

		assertFalse(StringRegexp.isDigitDecimal("1.1"));
		assertFalse(StringRegexp.isDigitDecimal("1.1"));
		assertFalse(StringRegexp.isDigitDecimal("1a1"));
		assertFalse(StringRegexp.isDigitDecimal("1a"));
	}

	/**
	 * Проверка корректной цифры двенадцатеричной системы счисления.
	 */
	@Test
	public void isDigitDuodecimalCorrect()
	{
		assertTrue(StringRegexp.isDigitDuodecimal("0123456789AB"));
		assertTrue(StringRegexp.isDigitDuodecimal("0123456789ab"));
		assertTrue(StringRegexp.isDigitDuodecimal("120143023649aB101"));
	}

	/**
	 * Проверка некорректной цифры двенадцатеричной системы счисления.
	 */
	@Test
	public void isDigitDuodecimalNotCorrect()
	{
		assertFalse(StringRegexp.isDigitDuodecimal(""));
		assertFalse(StringRegexp.isDigitDuodecimal(" "));
	}

	/**
	 * Проверка корректной цифры шестнадцатеричной системы счисления.
	 */
	@Test
	public void isDigitHexadecimalCorrect()
	{
		assertTrue(StringRegexp.isDigitHexadecimal("0123456789ABCDEF"));
		assertTrue(StringRegexp.isDigitHexadecimal("0123456789abbcdef"));
		assertTrue(StringRegexp.isDigitHexadecimal("120ef143023649aB10cd1"));
	}

	/**
	 * Проверка некорректной цифры шестнадцатеричной системы счисления.
	 */
	@Test
	public void isDigitHexadecimalNotCorrect()
	{
		assertFalse(StringRegexp.isDigitHexadecimal(""));
		assertFalse(StringRegexp.isDigitHexadecimal(" "));
	}

	/**
	 * Проверка корректного числа.
	 */
	@Test
	public void isNumberCorrect()
	{
		assertTrue(StringRegexp.isNumber("0"));
		assertTrue(StringRegexp.isNumber("100"));

		assertTrue(StringRegexp.isNumber("0.1"));
		assertTrue(StringRegexp.isNumber("0,1"));

		assertTrue(StringRegexp.isNumber("1.100"));
		assertTrue(StringRegexp.isNumber("1,100"));

		assertTrue(StringRegexp.isNumber("1,100,123"));
		assertTrue(StringRegexp.isNumber("1.100.123"));
	}

	/**
	 * Проверка некорректного числа.
	 */
	@Test
	public void isNumberNotCorrect()
	{
		assertFalse(StringRegexp.isNumber(""));
		assertFalse(StringRegexp.isNumber(" "));

		assertFalse(StringRegexp.isNumber("."));
		assertFalse(StringRegexp.isNumber(","));

		assertFalse(StringRegexp.isNumber(".1"));
		assertFalse(StringRegexp.isNumber(",2"));

		assertFalse(StringRegexp.isNumber("3,"));
		assertFalse(StringRegexp.isNumber("4."));

		assertFalse(StringRegexp.isNumber("1. 1"));
		assertFalse(StringRegexp.isNumber("1, 1"));

		assertFalse(StringRegexp.isNumber("1. 100"));
		assertFalse(StringRegexp.isNumber("1, 100"));

		assertFalse(StringRegexp.isNumber("1 .100"));
		assertFalse(StringRegexp.isNumber("1 ,100"));

		assertFalse(StringRegexp.isNumber("1 . 100"));
		assertFalse(StringRegexp.isNumber("1 , 100"));

		assertFalse(StringRegexp.isNumber("1,100, 123"));
		assertFalse(StringRegexp.isNumber("1.100. 123"));
	}

	/**
	 * Проверка корректного слова.
	 */
	@Test
	public void isWordCorrect()
	{
		assertTrue(StringRegexp.isWord("привет"));
		assertTrue(StringRegexp.isWord("ПРИВЕТ"));

		assertTrue(StringRegexp.isWord("Hello"));
		assertTrue(StringRegexp.isWord("WoRlD"));

		assertTrue(StringRegexp.isWord("Hello-World"));
	}

	/**
	 * Проверка некорректного слова.
	 */
	@Test
	public void isWordNotCorrect()
	{
		assertFalse(StringRegexp.isWord(""));
		assertFalse(StringRegexp.isWord(" "));

		assertFalse(StringRegexp.isWord("-привет"));
		assertFalse(StringRegexp.isWord("привет-"));

		assertFalse(StringRegexp.isWord(" привет"));
		assertFalse(StringRegexp.isWord("привет "));

		assertFalse(StringRegexp.isWord("Hello- World"));
		assertFalse(StringRegexp.isWord("Hello -World"));
		assertFalse(StringRegexp.isWord("Hello - World"));
	}

	/**
	 * Проверка корректной буквы.
	 */
	@Test
	public void isLetterCorrect()
	{
		assertTrue(StringRegexp.isLetter("А"));
		assertTrue(StringRegexp.isLetter("Ж"));

		assertTrue(StringRegexp.isLetter("W"));
		assertTrue(StringRegexp.isLetter("Z"));
	}

	/**
	 * Проверка некорректной буквы.
	 */
	@Test
	public void isLetterNotCorrect()
	{
		assertFalse(StringRegexp.isLetter(""));
		assertFalse(StringRegexp.isLetter(" "));

		assertFalse(StringRegexp.isLetter("-"));
		assertFalse(StringRegexp.isLetter("0"));
		assertFalse(StringRegexp.isLetter("?"));
	}

	/**
	 * Проверка поиска адресов электронной почты.
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

		String string = """
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

		assertArrayEquals(emailAddress, StringRegexp.findEmailAddress(string).toArray());
	}

	/**
	 * Проверка поиска доменных имён.
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

		String string = """
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

		assertArrayEquals(domain, StringRegexp.findDomain(string).toArray());
	}

	/**
	 * Проверка поиска URL HTTP.
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

		String string = """
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

		assertArrayEquals(domain, StringRegexp.findUrlHttp(string).toArray());
	}

	/**
	 * Проверка поиска двухбуквенных кодов стран.
	 */
	@Test
	public void findCountryCodeAlpha2()
	{
		String[] countryCodeAlpha2 = {
			"RU",
			"BY"
		};

		String string = """
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

		assertArrayEquals(countryCodeAlpha2, StringRegexp.findCountryCodeAlpha2(string).toArray());
	}

	/**
	 * Проверка поиска трёхбуквенных кодов стран.
	 */
	@Test
	public void findCountryCodeAlpha3()
	{
		String[] countryCodeAlpha3 = {
			"RUS",
			"BLR"
		};

		String string = """
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

		assertArrayEquals(countryCodeAlpha3, StringRegexp.findCountryCodeAlpha3(string).toArray());
	}

	/**
	 * Проверка поиска трёхциферных кодов стран.
	 */
	@Test
	public void findCountryCodeNumeric3()
	{
		String[] countryCodeNumeric3 = {
			"052",
			"643",
			"112"
		};

		String string = """
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

		assertArrayEquals(countryCodeNumeric3, StringRegexp.findCountryCodeNumeric3(string).toArray());
	}

	/**
	 * Проверка поиска RGB в шестнадцатеричной системе счисления.
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

		String string = """
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

		assertArrayEquals(rgbHex, StringRegexp.findRgbHex(string).toArray());
	}

	/**
	 * Проверка поиска RGB в шестнадцатеричной системе счисления без учёта регистра.
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

		String string = """
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

		assertArrayEquals(rgbHex, StringRegexp.findRgbHexIgnoreCase(string).toArray());
	}

	/**
	 * Проверка поиска MAC-адресов.
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

		String string = """
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

		assertArrayEquals(macAddress, StringRegexp.findMacAddress(string).toArray());
	}

	/**
	 * Проверка поиска MAC-адресов без учёта регистра.
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

		String string = """
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

		assertArrayEquals(macAddress, StringRegexp.findMacAddressIgnoreCase(string).toArray());
	}

	/**
	 * Проверка поиска RGB в числовом представлении.
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

		String string = """
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

		assertArrayEquals(rgb, StringRegexp.findRgbNumeric(string).toArray());
	}

	/**
	 * Проверка поиска телеграм каналов.
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

		String string = """
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

		assertArrayEquals(telegram, StringRegexp.findTelegram(string).toArray());
	}

	/**
	 * Проверка поиска IP-адресов шестой версии.
	 */
	@Test
	public void findIpAddressVersion6()
	{
		String[] inet6Address = {
			"2001:0DB8:11A3:09D7:1F34:8A2E:07A0:765D",
			"AD12::AE21::",
			"::AE21:AD12",
			"2001:DB8::AE21:AD12"
		};

		String string = """
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

		assertArrayEquals(inet6Address, StringRegexp.findInet6Address(string).toArray());
	}

	/**
	 * Проверка поиска IP-адресов шестой версии без учёта регистра.
	 */
	@Test
	public void findIpAddressVersion6IgnoreCase()
	{
		String[] inet6Address = {
			"2001:0db8:11a3:09d7:1F34:8A2E:07A0:765D",
			"ad12::AE21::",
			"::ae21:AD12",
			"2001:db8::AE21:AD12"
		};

		String string = """
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

		assertArrayEquals(inet6Address, StringRegexp.findInet6AddressIgnoreCase(string).toArray());
	}

	/**
	 * Проверка поиска меток.
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

		String string = """
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

		assertArrayEquals(tag, StringRegexp.findTag(string).toArray());
	}

	/**
	 * Проверка поиска цифр двоичной системы счисления.
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

		String string = """
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

		assertArrayEquals(digitBinary, StringRegexp.findDigitBinary(string).toArray());
	}

	/**
	 * Проверка поиска цифр троичной системы счисления.
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

		String string = """
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

		assertArrayEquals(digitTernary, StringRegexp.findDigitTernary(string).toArray());
	}

	/**
	 * Проверка поиска цифр восьмеричной системы счисления.
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

		String string = """
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

		assertArrayEquals(digitOctal, StringRegexp.findDigitOctal(string).toArray());
	}

	/**
	 * Проверка поиска цифр восьмеричной системы счисления.
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

		String string = """
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

		assertArrayEquals(digitDuodecimal, StringRegexp.findDigitDuodecimal(string).toArray());
	}

	/**
	 * Проверка поиска цифр шестнадцатеричной системы счисления.
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

		String string = """
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

		assertArrayEquals(digitHexadecimal, StringRegexp.findDigitHexadecimal(string).toArray());
	}

	/**
	 * Проверка поиска цифр десятеричной системы счисления.
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

		String string = """
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

		assertArrayEquals(digitDecimal, StringRegexp.findDigitDecimal(string).toArray());
	}

	/**
	 * Проверка поиска числа.
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

		String string = """
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

		assertArrayEquals(number, StringRegexp.findNumber(string).toArray());
	}

	/**
	 * Проверка поиска цифр.
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

		String string = """
			Красно-жёлтые дни.
						
			Застоялся мой поезд в депо.
			Снова я уезжаю. Пора...
			На пороге ветер заждался меня.
			На пороге осень — моя сестра.
			""";

		assertArrayEquals(word, StringRegexp.findWord(string).toArray());
	}

	/**
	 * Проверка поиска букв.
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

		String string = """
			Малыш
			""";

		assertArrayEquals(letter, StringRegexp.findLetter(string).toArray());
	}

	/**
	 * Проверка поиска HTML комментариев.
	 */
	@Test
	public void findHtmlComment()
	{
		String[] htmlComment = {
			"<!--Всё в свое время, зима и весна-->",
			"<!-- яблоку место упасть\nКаждому вору возможность украсть\nКаждой собаке палку и кость-->",
			"<!--,-->",
			"<!---->"
		};

		String string = """
			Песня без слов, ночь без сна
			<!--Всё в свое время, зима и весна-->
			Каждой звезде свой неба кусок
			Каждому морю дождя глоток
			Каждому <!-- яблоку место упасть
			Каждому вору возможность украсть
			Каждой собаке палку и кость-->
			И каждому волку зубы и злость
			   
			Снова за окнами белый день
			День вызывает меня на бой
			Я чувствую, закрывая глаза
			Весь мир идёт на меня войной
			   
			Если есть стадо, есть пастух
			Если есть тело, должен быть дух
			Если есть шаг<!--,--> должен быть след
			Если есть тьма, должен быть свет
			Хочешь ли ты изменить этот мир?
			Сможешь ли ты принять как есть?
			Встать и выйти из ряда вон?
			Сесть на электрический стул или трон?
			<!---->
			Снова за окнами белый день
			День вызывает меня на бой
			Я чувствую, закрывая глаза
			Весь мир идёт на меня войной
			""";

		assertArrayEquals(htmlComment, StringRegexp.findHtmlComment(string).toArray());
	}

	/**
	 * Проверка поиска областей CDATA.
	 */
	@Test
	public void findCData()
	{
		String[] cData = {
			"<![CDATA[Здесь не понятно, где лицо, а где рыло,]]>",
			"<![CDATA[Здесь в сено не втыкаются вилы,\nА рыба проходит сквозь сеть.\nИ не ясно, где море, где суша,\nГде золото, а где медь.]]>",
			"<![CDATA[...]]>",
			"<![CDATA[]]>"
		};

		String string = """
			<![CDATA[Здесь не понятно, где лицо, а где рыло,]]>
			И не понятно, где пряник, где плеть.
			<![CDATA[Здесь в сено не втыкаются вилы,
			А рыба проходит сквозь сеть.
			И не ясно, где море, где суша,
			Где золото, а где медь.]]>
			Что построить, и что разрушить,
			И кому, и зачем здесь петь?
			
			Нам с тобой: голубых небес навес.
			Нам с тобой: станет лес глухой стеной.
			Нам с тобой: из заплеванных колодцев не пить.
			План такой – нам с тобой<![CDATA[...]]>
			
			Здесь камни похожи на мыло,
			А сталь похожа на жесть,
			И слабость, как сила,
			И правда, как лесть.
			И не ясно, где мешок, а где шило,
			И не ясно, где обида, а где месть.
			И мне не нравится то, что здесь было,
			И мне не нравится то, что здесь есть.
			<![CDATA[]]>
			Нам с тобой: голубых небес навес.
			Нам с тобой: станет лес глухой стеной.
			Нам с тобой: из заплеванных колодцев не пить.
			План такой – нам с тобой...
			
			Чёрная ночь да в реке вода - нам с тобой.
			И беда станет не беда. Уезжай...
			Так, была не была, прости и прощай.
			План такой - нам с тобой...
			""";

		assertArrayEquals(cData, StringRegexp.findCData(string).toArray());
	}

	/**
	 * Проверка поиска HTML-сущностей в виде имени.
	 */
	@Test
	public void findHtmlEntityName()
	{
		String[] htmlEntityName = {
			"&frac14;",
			"&amp;",
			"&commat;",
			"&lt;"
		};

		String string = """
			Как много веселых ребят,
			И все делают &frac14; велосипед,
			А один из них как-нибудь утром придумает порох.
			Ну а я здесь сижу без тебя,&amp;
			Мне до этих ребят дела нет,
			Лишь окурки лежат на полу, да мусора ворох.
			
			Расскажи мне историю этого мира,
			Удивись количеству прожитых лет,
			Расскажи, &commat;каково быть мишенью в тире,
			У меня есть вопрос, на который ты не дашь мне ответ.
			
			Так странно проходят часы,
			И так странно не хочется спать,
			И так странно, когда за окном проезжает машина.
			И я не знаю, точны ли весы,
			Но мне не хочется их проверять,
			Мне слишком нравится эта картина.
			
			Расскажи мне историю этого мира,
			Удивись количеству прожитых лет&lt;,
			Расскажи, каково быть мишенью в тире,
			У меня есть вопрос, на который ты не дашь мне ответ.
			""";

		assertArrayEquals(htmlEntityName, StringRegexp.findHtmlEntityName(string).toArray());
	}

	/**
	 * Проверка поиска HTML-сущностей в виде числа.
	 */
	@Test
	public void findHtmlEntityNumeric()
	{
		String[] htmlEntityNumeric = {
			"&#44;",
			"&#169;",
			"&#10589;",
			"&#10590;"
		};

		String string = """
			В моем доме не видно стены,
			В моем небе &#44;не видно луны.
			Я слеп, но я вижу тебя,
			Я глух, но я слышу тебя.
			Я не сплю, &#169; но я вижу сны,
			Здесь нет моей вины,
			Я нем, но ты слышишь меня,
			И этим мы сильны.
			
			И снова приходит ночь,
			Я пьян, но я слышу дождь,
			Дождь для нас...
			Квартира пуста, но мы здесь,
			Здесь мало, что есть, но мы есть.
			Дождь для нас...
			
			Ты видишь мою звезду,
			Ты веришь, что я пойду.
			Я слеп, я не вижу звезд,
			Я пьян, но я помню свой пост.
			Ты смотришь на Млечный Путь,
			Я - ночь, а ты -&#10589; утра суть.
			Я - сон, я не видим тебе,
			Я слеп, но я вижу свет.
			
			И снова приходит ночь,
			Я пьян, но я слышу дождь,
			Дождь для нас...&#10590;
			Квартира пуста, но мы здесь,
			Здесь мало, что есть, но мы есть.
			Дождь для нас...
			""";

		assertArrayEquals(htmlEntityNumeric, StringRegexp.findHtmlEntityNumeric(string).toArray());
	}

	/**
	 * Проверка поиска HTML-сущностей в виде unicode.
	 */
	@Test
	public void findHtmlEntityUnicode()
	{
		String[] htmlEntityUnicode = {
			"&#X22C8;",
			"&#x224E;",
			"&#X0000141;",
			"&#X000000BB;"
		};

		String string = """
			Ночь, день -
			Спать &#X22C8;лень.
			Есть дым -&#x224E;
			Чёрт с ним.
			Сна нет -
			Есть сон лет.
			Кино -
			Кончилось давно.
			
			Мой дом,
			Я в нём
			Сижу -
			Пень пнём.
			Есть свет&#X0000141;,
			Сна нет.
			Есть ночь -
			Уже уходит прочь.
			
			Стоит таз,
			Горит газ.
			Щелчок -
			&#X000000BB;И газ погас.
			Пора спать -
			В кровать.
			Вставать,
			Завтра вставать.
			""";

		assertArrayEquals(htmlEntityUnicode, StringRegexp.findHtmlEntityUnicode(string).toArray());
	}

	/**
	 * Проверка поиска типов HTML документа.
	 */
	@Test
	public void findHtmlDoctype()
	{
		String[] htmlDoctype = {
			"<!DOCTYPE html>",
			"<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Strict//EN'\n'http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd'>",
			"<!DOCTYPE html PUBLIC \"+//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">",
			"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" 'http://www.w3.org/TR/html4/loose.dtd'>"
		};

		String string = """
			<!DOCTYPE html>Они говорят: им нельзя рисковать,
			Потому что у них есть дом,
			В доме горит свет.
			И я не знаю точно, <!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Strict//EN'
			'http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd'> кто из нас прав,
			Меня ждет на улице дождь,
			Их ждет дома обед.
			<!DOCTYPE html PUBLIC "+//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
			Закрой за мной дверь.
			Я ухожу.
			
			И если тебе вдруг наскучит твой ласковый свет,
			Тебе найдется место у нас,
			Дождя хватит на всех.
			Посмотри на часы, посмотри на портрет на стене,
			Прислушайся –<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 'http://www.w3.org/TR/html4/loose.dtd'> там, за окном,
			Ты услышишь наш смех.
			
			Закрой за мной дверь.
			Я ухожу.
			""";

		assertArrayEquals(htmlDoctype, StringRegexp.findHtmlDoctype(string).toArray());
	}

	/**
	 * Проверка поиска закрывающих HTML тегов.
	 */
	@Test
	public void findHtmlEndTag()
	{
		String[] htmlEndTag = {
			"</form>",
			"</b >",
			"</table>",
			"</strong>",
		};

		String string = """
			О-o, это странное место Камчатка,
			О-o, это сладкое</form> слово "Камчатка".
			Но на этой земле я не вижу тебя,
			Я не вижу твоих кораблей,
			Я не вижу реки, я не вижу моста,
			Ну и пусть...</b >
			
			О-o, это странное место Камчатка,
			О-o, это сладкое слово "Камчатка".
			Я нашел здесь руду</table>, я нашел здесь любовь,
			Я пытаюсь забыть, забываю и вновь
			Вспоминаю собаку, она, как звезда,
			Ну и пусть...
			
			О-o, это странное место Камчатка,
			О-o, это сладкое слово "Камчатка"</strong>.
			Я не вижу здесь их, я не вижу здесь нас,
			Я искал здесь вино, а нашел третий глаз,
			Мои руки из дуба, голова из свинца,
			Ну и пусть...
			""";

		assertArrayEquals(htmlEndTag, StringRegexp.findHtmlEndTag(string).toArray());
	}

	/**
	 * Проверка поиска открывающих HTML тегов.
	 */
	@Test
	public void findHtmlStartTag()
	{
		String[] htmlStartTag = {
			"<b>",
			"<meta charset='UTF-8'>",
			"<input type='checkbox'  >",
			"<input value =  yes>",
		};

		String string = """
			Солдат шел по улице домой
			И <b>увидел этих ребят.
			"Кто ваша мама, ребята?" -
			<meta charset='UTF-8'>Спросил у ребят солдат.
			
			Мама - Анархия,
			Папа - стакан портвейна.
			
			Все они в кожаных куртках,
			Все небольшого роста,
			Хотел солдат пройти мимо<input type='checkbox'  >,
			Но это было не просто.
			
			Мама - Анархия,
			Папа - стакан портвейна.
			
			Довольно веселую шутку
			Сыграли с солдатом ребята:<input value =  yes>
			Раскрасили красным и синим,
			Заставляли ругаться матом.
			
			Мама - Анархия,
			Папа - стакан портвейна.
			""";

		assertArrayEquals(htmlStartTag, StringRegexp.findHtmlStartTag(string).toArray());
	}

	/**
	 * Проверка поиска самозакрывающихся HTML тегов.
	 */
	@Test
	public void findHtmlSelfClosingTag()
	{
		String[] htmlStartTag = {
			"<br/>",
			"<meta charset='UTF-8'/>",
			"<input type='checkbox'  />",
			"<input value =  yes/>",
		};

		String string = """
			У меня есть дом, только нет ключей,
			У меня есть солнце, но оно среди туч,<br/>
			Есть голова<meta charset='UTF-8'/>, только нет плечей,
			Но я вижу, как тучи режут солнечный луч.
			У меня есть слово, но в нем нет букв,
			У меня есть лес, но нет топоров,
			У меня есть время, но нет сил ждать,
			И есть еще ночь, но в ней нет снов.
			
			И есть еще белые, белые дни,
			Белые горы и белый лед.
			Но все, что мне нужно <input type='checkbox'  />- это несколько слов
			И место для шага вперед.
			
			У меня река, только нет моста,
			У меня есть мыши, но нет кота,
			У меня есть парус, но ветра нет
			И есть еще краски, но нет холста.
			У меня на <input value =  yes/> кухне из крана вода,
			У меня есть рана, но нет бинта,
			У меня есть братья, но нет родных
			И есть рука, и она пуста.
			
			И есть еще белые, белые дни,
			Белые горы и белый лед.
			Но все, что мне нужно - это несколько слов
			И место для шага вперед.
			""";

		assertArrayEquals(htmlStartTag, StringRegexp.findHtmlSelfClosingTag(string).toArray());
	}
}