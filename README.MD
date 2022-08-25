# StringRegexp

Операции над строками с помощью регулярных выражений.

## Использование

### Проверка

#### Проверка локализации

```java
StringRegexp.isLocale("ru-RU");
// Результат выполнения: true
```

#### Проверка адреса электронной почты

```java
StringRegexp.isEmailAddress("aleksey.kalenchukov@yandex.ru");
// Результат выполнения: true
```

#### Проверка IP адреса четвёртой версии

```java
StringRegexp.isInet4Address("192.168.1.1");
// Результат выполнения: true
```

#### Проверка IP адреса шестой версии

```java
StringRegexp.isInet6Address("EF98:3:0:0:0:0:2F3B:7654");
// Результат выполнения: true
```

### Поиск

#### Поиск локализаций

```java
String text = """
    Застоялся мой поезд в депо.
    Снова я by-BY уезжаю. Пора…
    На пороге ветер заждался меня.
    На пороге осень — моя сестра.ru-RU
    """;

StringRegexp.findLocale(text);
/*
Результат выполнения: 
{
    by-BY,
    ru-RU
}
*/
```

#### Поиск адресов электронной почты

```java
String text = """
    И я должен прийти к девяти, на работу свою, aleksey.kalenchukov@yandex.ru
    Но сейчас уже без десяти, а я только встаю
    На столе моем 123alekseykalenchukov@yandex.ru завтрак стоит, от него не уйти
    И наверное, я к девяти не смогу подойти
    """;

StringRegexp.findEmailAddress(text);
/*
Результат выполнения: 
{
    aleksey.kalenchukov@yandex.ru,
    123alekseykalenchukov@yandex.ru
}
*/
```

#### Поиск IP адресов четвёртой версии

```java
String text = """
    День как день,10.222.170.80 только ты почему-то грустишь.
    И вокруг все поют, только ты один молчишь.
    Потерял аппетит и не 192.168.1.1 хочешь сходить в кино.
    Ты идешь в магазин, чтобы купить вино.
    """;

StringRegexp.findInet4Address(text);
/*
Результат выполнения: 
{
    10.222.170.80,
    192.168.1.1
}
*/
```

#### Поиск IP адресов шестой версии

```java
String text = """
    В последнее время я редко был дома,
    Так ad12::ae21:: что даже отвыкли звонить мне друзья.
    В разъездах, разгулах 2001:0db8:11a3:09d7:1f34:8a2e:07a0:765d конца лета симптомы
    Совсем перестали вдруг мучить меня.
    """;

StringRegexp.findInet6Address(text);
/*
Результат выполнения: 
{
    ad12::ae21::,
    2001:0db8:11a3:09d7:1f34:8a2e:07a0:765d
}
*/
```

## Установка

Для установки этого проекта, введите в терминале:

```bash
git clone https://github.com/kalenchukov/StringRegexp.git
```

## Версии

Доступные [версии](https://github.com/kalenchukov/StringRegexp/releases).

## Авторы

[Алексей Каленчуков](https://github.com/kalenchukov)

## Лицензия

[MIT](https://opensource.org/licenses/MIT)