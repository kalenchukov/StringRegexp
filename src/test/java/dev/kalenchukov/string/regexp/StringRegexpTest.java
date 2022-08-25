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
	public void isLocaleCorrect()
	{
		assertTrue(StringRegexp.isLocale("ru-RU"));
	}

	/**
	 * Проверка некорректной локализации.
	 */
	@Test
	public void isLocaleNotCorrect()
	{
		assertFalse(StringRegexp.isLocale(""));
		assertFalse(StringRegexp.isLocale(" "));

		assertFalse(StringRegexp.isLocale("RU-RU"));
		assertFalse(StringRegexp.isLocale("RU-ru"));
		assertFalse(StringRegexp.isLocale("ru-ru"));

		assertFalse(StringRegexp.isLocale("ru"));
		assertFalse(StringRegexp.isLocale("RU"));
	}

	/**
	 * Проверка поиска локализаций.
	 */
	@Test
	public void findLocale()
	{
		String[] locale = {
			"ru-RU",
			"en-EN",
			"by-BY",
			"it-IT"
		};

		String string = """
			Застоялся мой поезд в депо.
			Снова я уезжаю. Пора…
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
			Я проснулся и понял — беда…
			
			После красно-желтых дней начнется и кончится зима.
			Горе ты мое от ума, не печалься, гляди веселей.
			И я вернусь домой со щитом, а, может быть, на щите,
			В серебре, а, может быть, в нищете, но как можно скорей.
			""";

		assertArrayEquals(locale, StringRegexp.findLocale(string).toArray());
	}

	/**
	 * Проверка корректного IP адреса четвёртой версии.
	 */
	@Test
	public void isIpAddressVersion4Correct()
	{
		assertTrue(StringRegexp.isInet4Address("192.168.1.1"));
		assertTrue(StringRegexp.isInet4Address("1.1.1.1"));
		assertTrue(StringRegexp.isInet4Address("0.0.0.0"));
	}

	/**
	 * Проверка некорректного IP адреса четвёртой версии.
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
	 * Проверка поиска IP адресов четвёртой версии.
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
	 * Проверка корректного IP адреса шестой версии.
	 */
	@Test
	public void isIpAddressVersion6Correct()
	{
		assertTrue(StringRegexp.isInet6Address("2001:0db8:11a3:09d7:1f34:8a2e:07a0:765d"));

		assertTrue(StringRegexp.isInet6Address("2001:0db8:0000:0000:0000:0000:ae21:ad12"));
		assertTrue(StringRegexp.isInet6Address("0000:0000:0000:0000:0000:0000:ae21:ad12"));

		assertTrue(StringRegexp.isInet6Address("2001:0db8:0000:0000:0000:0000:ae21:1"));
		assertTrue(StringRegexp.isInet6Address("2001:0db8:0000:1:0000:0000:ae21:ad12"));
		assertTrue(StringRegexp.isInet6Address("1:0000:0000:0000:0000:0000:ae21:ad12"));

		assertTrue(StringRegexp.isInet6Address("::0db8:11a3:09d7:1f34:8a2e:07a0:765d"));
		assertTrue(StringRegexp.isInet6Address("0db8:11a3:09d7:1f34::07a0:765d"));
		assertTrue(StringRegexp.isInet6Address("2001:0db8:11a3:09d7:1f34:8a2e:07a0::"));

		assertTrue(StringRegexp.isInet6Address("::ae21:ad12"));
		assertTrue(StringRegexp.isInet6Address("ad12::ae21::"));

		assertTrue(StringRegexp.isInet6Address("2001::"));
		assertTrue(StringRegexp.isInet6Address("::2001"));

		assertTrue(StringRegexp.isInet6Address("::"));

		assertTrue(StringRegexp.isInet6Address("::1"));
		assertTrue(StringRegexp.isInet6Address("1::"));

		assertTrue(StringRegexp.isInet6Address("EF98:3:0:0:0:0:2F3B:7654"));
		assertTrue(StringRegexp.isInet6Address("EF98:3::2F3B:7654"));
		assertTrue(StringRegexp.isInet6Address("2001:db8::ae21:ad12"));
	}

	/**
	 * Проверка некорректного IP адреса шестой версии.
	 */
	@Test
	public void isIpAddressVersion6NotCorrect()
	{
		assertFalse(StringRegexp.isInet6Address(""));
		assertFalse(StringRegexp.isInet6Address(" "));

		assertFalse(StringRegexp.isInet6Address("1000"));
		assertFalse(StringRegexp.isInet6Address("1000:"));
		assertFalse(StringRegexp.isInet6Address(":1000"));

		assertFalse(StringRegexp.isInet6Address(":"));
		assertFalse(StringRegexp.isInet6Address(":::"));
		assertFalse(StringRegexp.isInet6Address("::::"));

		assertFalse(StringRegexp.isInet6Address("2001:0db8:11a3:09d7:1f34:8a2e:07a0:765d:765d"));

		assertFalse(StringRegexp.isInet6Address(":0db8:11a3:09d7:1f34:8a2e:07a0:765d"));
		assertFalse(StringRegexp.isInet6Address("2001:0db8:11a3:09d7:1f34:8a2e:07a0:"));

		assertFalse(StringRegexp.isInet6Address(":::0db8:11a3:09d7:1f34:8a2e:07a0:765d"));
		assertFalse(StringRegexp.isInet6Address("2001:0db8:11a3:09d7:1f34:8a2e:07a0:::"));

		assertFalse(StringRegexp.isInet6Address("::::0db8:11a3:09d7:1f34:8a2e:07a0:765d"));
		assertFalse(StringRegexp.isInet6Address("0db8:11a3:09d7::::1f34:8a2e:07a0:765d"));
		assertFalse(StringRegexp.isInet6Address("2001:0db8:11a3:09d7:1f34:8a2e:07a0::::"));

		assertFalse(StringRegexp.isInet6Address(":0db8:11a3:09d7:1f34:8a2e:07a0:765d:1000"));
		assertFalse(StringRegexp.isInet6Address("1000:2001:0db8:11a3:09d7:1f34:8a2e:07a0:"));

		assertFalse(StringRegexp.isInet6Address(":0db8:11a3:09d7:1f34:8a2e:07a0:765d:1"));
		assertFalse(StringRegexp.isInet6Address("1:2001:0db8:11a3:09d7:1f34:8a2e:07a0:"));

		assertFalse(StringRegexp.isInet6Address("::0db8:11a3:09d7:1f34:8a2e:07a0:765d:1"));
		assertFalse(StringRegexp.isInet6Address("1:2001:0db8:11a3:09d7:1f34:8a2e:07a0::"));

		assertFalse(StringRegexp.isInet6Address(":::0db8:11a3:09d7:1f34:8a2e:07a0:765d:1"));
		assertFalse(StringRegexp.isInet6Address("1:2001:0db8:11a3:09d7:1f34:8a2e:07a0:::"));

		assertFalse(StringRegexp.isInet6Address("0db8:11a3::::8a2e:07a0:765d"));
		assertFalse(StringRegexp.isInet6Address("0db8:11a3::::::8a2e:07a0:765d"));
	}

	/**
	 * Проверка поиска IP адресов шестой версии.
	 */
	@Test
	public void findIpAddressVersion6()
	{
		String[] inet6Address = {
			"2001:0db8:11a3:09d7:1f34:8a2e:07a0:765d",
			"ad12::ae21::",
			"::ae21:ad12",
			"2001:db8::ae21:ad12"
		};

		String string = """
			В последнее время я редко был дома,
			Так что даже отвыкли звонить мне друзья.
			В разъездах, разгулах 2001:0db8:11a3:09d7:1f34:8a2e:07a0:765d конца лета симптомы
			Совсем перестали вдруг мучить меня.
			
			И я подумал что Осень - это тоже не плохо,
			И что Осенью слякоть и сер первый снег, ad12::ae21::
			И что холод ветров я буду чувствовать боком,
			Опьяненный сознаньем того, что я человек.
			
			И этой Осенью много дней чьих-то рождений
			И уж я постараюсь на них побывать,
			::ae21:ad12 А потом, игнорируя лужи и слякоть,
			Я приду домой пьяный и мешком повалюсь на кровать.
			
			И утром рано я встану и отправлюсь учиться,
			И с похмелья я буду смеяться над всем.
			Скоро будет Зима, чтоб в Весне раствориться,
			А потом будет Лето - неизвестно зачем.
			
			И я начал за здравие, а кончу я плохо,
			Написав наш порядковый номер - 600.
			С чьих-то старых столов подбираю я крохи,
			И не в силах сказать,2001:db8::ae21:ad12 что принес этот год.
			""";

		assertArrayEquals(inet6Address, StringRegexp.findInet6Address(string).toArray());
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
	 * Проверка поиска RGB в шестнадцатеричной системе счисления.
	 */
	@Test
	public void findRgbHex()
	{
		String[] RgbHex = {
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

		assertArrayEquals(RgbHex, StringRegexp.findRgbHex(string).toArray());
	}
}