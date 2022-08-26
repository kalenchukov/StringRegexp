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
		assertTrue(StringRegexp.isInet6Address("2001:0DB8:11A3:09D7:1F34:8A2E:07A0:765D"));

		assertTrue(StringRegexp.isInet6Address("2001:0DB8:0000:0000:0000:0000:AE21:AD12"));
		assertTrue(StringRegexp.isInet6Address("0000:0000:0000:0000:0000:0000:AE21:AD12"));

		assertTrue(StringRegexp.isInet6Address("2001:0DB8:0000:0000:0000:0000:AE21:1"));
		assertTrue(StringRegexp.isInet6Address("2001:0DB8:0000:1:0000:0000:AE21:AD12"));
		assertTrue(StringRegexp.isInet6Address("1:0000:0000:0000:0000:0000:AE21:AD12"));

		assertTrue(StringRegexp.isInet6Address("::0DB8:11A3:09D7:1F34:8A2E:07A0:765D"));
		assertTrue(StringRegexp.isInet6Address("0DB8:11A3:09D7:1F34::07A0:765D"));
		assertTrue(StringRegexp.isInet6Address("2001:0DB8:11A3:09D7:1F34:8A2E:07A0::"));

		assertTrue(StringRegexp.isInet6Address("::AE21:AD12"));
		assertTrue(StringRegexp.isInet6Address("AD12::AE21::"));

		assertTrue(StringRegexp.isInet6Address("2001::"));
		assertTrue(StringRegexp.isInet6Address("::2001"));

		assertTrue(StringRegexp.isInet6Address("::"));

		assertTrue(StringRegexp.isInet6Address("::1"));
		assertTrue(StringRegexp.isInet6Address("1::"));

		assertTrue(StringRegexp.isInet6Address("EF98:3:0:0:0:0:2F3B:7654"));
		assertTrue(StringRegexp.isInet6Address("EF98:3::2F3B:7654"));
		assertTrue(StringRegexp.isInet6Address("2001:DB8::AE21:AD12"));
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

		assertFalse(StringRegexp.isInet6Address("2001:0DB8:11A3:09D7:1F34:8A2E:07A0:765D:765D"));

		assertFalse(StringRegexp.isInet6Address(":0DB8:11A3:09D7:1F34:8A2E:07A0:765D"));
		assertFalse(StringRegexp.isInet6Address("2001:0DB8:11A3:09D7:1F34:8A2E:07A0:"));

		assertFalse(StringRegexp.isInet6Address(":::0DB8:11A3:09D7:1F34:8A2E:07A0:765D"));
		assertFalse(StringRegexp.isInet6Address("2001:0DB8:11A3:09D7:1F34:8A2E:07A0:::"));

		assertFalse(StringRegexp.isInet6Address("::::0DB8:11A3:09D7:1F34:8A2E:07A0:765D"));
		assertFalse(StringRegexp.isInet6Address("0DB8:11A3:09D7::::1F34:8A2E:07A0:765D"));
		assertFalse(StringRegexp.isInet6Address("2001:0DB8:11A3:09D7:1F34:8A2E:07A0::::"));

		assertFalse(StringRegexp.isInet6Address(":0DB8:11A3:09D7:1F34:8A2E:07A0:765D:1000"));
		assertFalse(StringRegexp.isInet6Address("1000:2001:0DB8:11A3:09D7:1F34:8A2E:07A0:"));

		assertFalse(StringRegexp.isInet6Address(":0DB8:11A3:09D7:1F34:8A2E:07A0:765D:1"));
		assertFalse(StringRegexp.isInet6Address("1:2001:0DB8:11A3:09D7:1F34:8A2E:07A0:"));

		assertFalse(StringRegexp.isInet6Address("::0DB8:11A3:09D7:1F34:8A2E:07A0:765D:1"));
		assertFalse(StringRegexp.isInet6Address("1:2001:0DB8:11A3:09D7:1F34:8A2E:07A0::"));

		assertFalse(StringRegexp.isInet6Address(":::0DB8:11A3:09D7:1F34:8A2E:07A0:765D:1"));
		assertFalse(StringRegexp.isInet6Address("1:2001:0DB8:11A3:09D7:1F34:8A2E:07A0:::"));

		assertFalse(StringRegexp.isInet6Address("0DB8:11A3::::8A2E:07A0:765D"));
		assertFalse(StringRegexp.isInet6Address("0DB8:11A3::::::8A2E:07A0:765D"));
	}

	/**
	 * Проверка поиска IP адресов шестой версии.
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
	 * Проверка корректного RGB в числовом представлении.
	 */
	@Test
	public void isRgbCorrect()
	{
		assertTrue(StringRegexp.isRgb("215,200,166"));
		assertTrue(StringRegexp.isRgb("0,0,0"));

		assertTrue(StringRegexp.isRgb("215, 200, 166"));
		assertTrue(StringRegexp.isRgb("0, 0, 0"));

		assertTrue(StringRegexp.isRgb("255,155, 55"));
		assertTrue(StringRegexp.isRgb("1,1,1"));
	}

	/**
	 * Проверка некорректного RGB в числовом представлении.
	 */
	@Test
	public void isRgbNotCorrect()
	{
		assertFalse(StringRegexp.isRgb(""));
		assertFalse(StringRegexp.isRgb(" "));

		assertFalse(StringRegexp.isRgb("0,0"));
		assertFalse(StringRegexp.isRgb("0"));

		assertFalse(StringRegexp.isRgb("215,  200, 166"));
		assertFalse(StringRegexp.isRgb("215, 200,  166"));

		assertFalse(StringRegexp.isRgb("-1, 0, 0"));
		assertFalse(StringRegexp.isRgb("0, -1, 0"));
		assertFalse(StringRegexp.isRgb("0, 0, -1"));

		assertFalse(StringRegexp.isRgb("215,200,"));
		assertFalse(StringRegexp.isRgb("1,1,1,"));

		assertFalse(StringRegexp.isRgb("256,155,55"));
		assertFalse(StringRegexp.isRgb("255,256,55"));
		assertFalse(StringRegexp.isRgb("255,155,256"));
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
	 * Проверка поиска RGB в числовом представлении.
	 */
	@Test
	public void findRgb()
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

		assertArrayEquals(rgb, StringRegexp.findRgb(string).toArray());
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
}