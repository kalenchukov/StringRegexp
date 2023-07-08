# Журнал изменений
## v1.7.0 - 08.07.2023
### Изменено
* Изменена версия Java на 17.

## v1.6.0 - 14.05.2023
### Добавлено
* Добавлено регулярное выражение Regexp.MD5.
* Добавлен метод StringRegexp.isMd5(String).
* Добавлен метод StringRegexp.isMd5IgnoreCase(String).

## v1.5.0 - 12.02.2023
### Удалено
* Удалено регулярное выражение Regexp.CDATA.
* Удалено регулярное выражение Regexp.HTML_SELF_CLOSING_TAG.
* Удалено регулярное выражение Regexp.HTML_START_TAG.
* Удалено регулярное выражение Regexp.HTML_END_TAG.
* Удалено регулярное выражение Regexp.HTML_COMMENT.
* Удалено регулярное выражение Regexp.HTML_DOCTYPE.
* Удалено регулярное выражение Regexp.HTML_ENTITY_NAME.
* Удалено регулярное выражение Regexp.HTML_ENTITY_UNICODE.
* Удалено регулярное выражение Regexp.HTML_ENTITY_NUMERIC.
* Удалён метод StringRegexp.isCData()
* Удалён метод StringRegexp.isHtmlComment()
* Удалён метод StringRegexp.isHtmlDoctype()
* Удалён метод StringRegexp.isHtmlEndTag()
* Удалён метод StringRegexp.isHtmlEntityName()
* Удалён метод StringRegexp.isHtmlEntityNumeric()
* Удалён метод StringRegexp.isHtmlEntityUnicode()
* Удалён метод StringRegexp.isHtmlSelfClosingTag()
* Удалён метод StringRegexp.isHtmlStartTag()
* Удалён метод StringRegexp.findCData()
* Удалён метод StringRegexp.findHtmlComment()
* Удалён метод StringRegexp.findHtmlDoctype()
* Удалён метод StringRegexp.findHtmlEndTag()
* Удалён метод StringRegexp.findHtmlEntityName()
* Удалён метод StringRegexp.findHtmlEntityNumeric()
* Удалён метод StringRegexp.findHtmlEntityUnicode()
* Удалён метод StringRegexp.findHtmlSelfClosingTag()
* Удалён метод StringRegexp.findHtmlStartTag()

## v1.4.0 - 03.12.2022
### Добавлено
* Добавлено регулярное выражение Regexp.COUNTRY_CODE_ALPHA2.
* Добавлено регулярное выражение Regexp.COUNTRY_CODE_ALPHA3.
* Добавлено регулярное выражение Regexp.COUNTRY_CODE_NUMERIC.
* Добавлен метод StringRegexp.isCountryCodeAlpha2(String).
* Добавлен метод StringRegexp.isCountryCodeAlpha3(String).
* Добавлен метод StringRegexp.isCountryCodeNumeric3(String).
* Добавлен метод StringRegexp.findCountryCodeAlpha2(String).
* Добавлен метод StringRegexp.findCountryCodeAlpha3(String).
* Добавлен метод StringRegexp.findCountryCodeNumeric3(String).
* Добавлен AUTHOR.MD.
* Добавлен INSTALL.MD.
* Добавлен CHANGELOG.MD.

### Изменено
* Обновлён README.MD.

## v1.3.0 - 16.10.2022
### Добавлено
* Добавлен метод StringRegexp.findDigitBinary(String).
* Добавлен метод StringRegexp.findDigitDuodecimal(String).
* Добавлен метод StringRegexp.findDigitHexadecimal(String).
* Добавлен метод StringRegexp.findDigitOctal(String).
* Добавлен метод StringRegexp.findDigitTernary(String).
* Добавлен метод StringRegexp.findUrlHttp(String).
* Добавлен метод StringRegexp.isDigitBinary(String).
* Добавлен метод StringRegexp.isDigitDuodecimal(String).
* Добавлен метод StringRegexp.isDigitHexadecimal(String).
* Добавлен метод StringRegexp.isDigitOctal(String).
* Добавлен метод StringRegexp.isDigitTernary(String).
* Добавлен метод StringRegexp.isUrlHttp(String).
* Добавлен плагин maven-assembly в pom.xml.
* Добавлен плагин maven-compiler в pom.xml.
* Добавлен плагин maven-dependency в pom.xml.
* Добавлен плагин maven-javadoc в pom.xml.
* Добавлен плагин maven-source в pom.xml.
* Добавлено регулярное выражение Regexp.DIGIT_DUODECIMAL.
* Добавлено регулярное выражение Regexp.DIGIT_HEXADECIMAL.
* Добавлено регулярное выражение Regexp.DIGIT_OCTAL.
* Добавлено регулярное выражение Regexp.DIGIT_TERNARY.
* Добавлено регулярное выражение Regexp.RGB.
* Добавлено регулярное выражение Regexp.RGB_NUMERIC.
* Добавлено регулярное выражение Regexp.URL_HTTP.
* Добавлены методы StringRegexp.isDomain(String) и StringRegexp.findDomain(String).
* Добавлены методы StringRegexp.isMacAddress*(String) и StringRegexp.findMacAddress*(String).

### Изменено
* Переименовано регулярное выражение Regexp.DIGIT -> Regexp.DIGIT_DECIMAL.

### Исправлено
* Исправлен баг в регулярном выражении Regexp.HTML_*_TAG.
* Исправлен баг в регулярном выражении Regexp.HTML_SELF_CLOSING_TAG.
* Исправлен баг в регулярном выражении Regexp.HTML_START_TAG.

## v1.2.1 - 14.09.2022
### Изменено
* Переименовано Regexp.HTML_ENTITY_MNEMONIC -> Regexp.HTML_ENTITY_NAME.

## v1.2.0 - 12.09.2022
### Добавлено
* Добавлен метод StringRegexp.findCData(String).
* Добавлен метод StringRegexp.findHtmlComment(String).
* Добавлен метод StringRegexp.findHtmlDoctype(String).
* Добавлен метод StringRegexp.findHtmlEndTag(String).
* Добавлен метод StringRegexp.findHtmlEntityMnemonic(String).
* Добавлен метод StringRegexp.findHtmlEntityNumeric(String).
* Добавлен метод StringRegexp.findHtmlEntityUnicode(String).
* Добавлен метод StringRegexp.findHtmlSelfClosingTag(String).
* Добавлен метод StringRegexp.findHtmlStartTag(String).
* Добавлен метод StringRegexp.isCData(String).
* Добавлен метод StringRegexp.isHtmlComment(String).
* Добавлен метод StringRegexp.isHtmlDoctype(String).
* Добавлен метод StringRegexp.isHtmlEndTag(String).
* Добавлен метод StringRegexp.isHtmlEntityMnemonic(String).
* Добавлен метод StringRegexp.isHtmlEntityNumeric(String).
* Добавлен метод StringRegexp.isHtmlEntityUnicode(String).
* Добавлен метод StringRegexp.isHtmlSelfClosingTag(String).
* Добавлен метод StringRegexp.isHtmlStartTag(String).
* Для проверки буквы добавлена независимость регистра.
* Для проверки слова добавлена независимость регистра.
* Добавлена группа в Regexp.HTML_ENTITY_NUMERIC.

### Изменено
* Изменены названия групп в Regexp.EMAIL_ADDRESS.

### Исправлено
* Исправлен баг в Regexp.HTML_ENTITY_NUMERIC.

## v1.1.0 - 31.08.2022
### Добавлено
* Добавлен метод StringRegexp.isDigit(String).
* Добавлен метод StringRegexp.findDigit(String).
* Добавлен метод StringRegexp.isInet6AddressIgnoreCase(String).
* Добавлен метод StringRegexp.findInet6AddressIgnoreCase(String).
* Добавлен метод StringRegexp.isLetter(String).
* Добавлен метод StringRegexp.findLetter(String).
* Добавлен метод StringRegexp.isNumber(String).
* Добавлен метод StringRegexp.findNumber(String).
* Добавлен метод StringRegexp.isRgb(String).
* Добавлен метод StringRegexp.findRgb(String).
* Добавлен метод StringRegexp.isRgbHex(String).
* Добавлен метод StringRegexp.findRgbHex(String).
* Добавлен метод StringRegexp.isRgbHexIgnoreCase(String).
* Добавлен метод StringRegexp.findRgbHexIgnoreCase(String).
* Добавлен метод StringRegexp.isTag(String).
* Добавлен метод StringRegexp.findTag(String).
* Добавлен метод StringRegexp.isTelegram(String).
* Добавлен метод StringRegexp.findTelegram(String).
* Добавлен метод StringRegexp.isWord(String).
* Добавлен метод StringRegexp.findWord(String).

### Изменено
* Переименованы методы Locale -> Localization.
