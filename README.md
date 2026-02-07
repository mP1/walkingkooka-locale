[![Build Status](https://github.com/mP1/walkingkooka-locale/actions/workflows/build.yaml/badge.svg)](https://github.com/mP1/walkingkooka-locale/actions/workflows/build.yaml/badge.svg)
[![Coverage Status](https://coveralls.io/repos/github/mP1/walkingkooka-locale/badge.svg)](https://coveralls.io/github/mP1/walkingkooka-locale)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/mP1/walkingkooka-locale.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/mP1/walkingkooka-locale/context:java)
[![Total alerts](https://img.shields.io/lgtm/alerts/g/mP1/walkingkooka-locale.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/mP1/walkingkooka-locale/alerts/)
![](https://tokei.rs/b1/github/mP1/walkingkooka-locale)
[![J2CL compatible](https://img.shields.io/badge/J2CL-compatible-brightgreen.svg)](https://github.com/mP1/j2cl-central)

# walkingkooka-locale
Defines some extra Context interface related to working with Locales and data related to Locales

## [LocaleConverters](https://github.com/mP1/walkingkooka-locale/blob/master/src/main/java/walkingkooka/locale/convert/LocaleConverters.java)

These `Converters` support converting values to a `Locale` or [DateTimeSymbols](https://github.com/mP1/walkingkooka-datetime/blob/master/src/main/java/walkingkooka/datetime/DateTimeSymbols.java)
or [DecimalNumberSymbols](https://github.com/mP1/walkingkooka-math/blob/master/src/main/java/walkingkooka/math/DecimalNumberSymbols.java)
with the later two often providing a source such as a `Locale`

- [dateTimeSymbols](https://github.com/mP1/walkingkooka-locale/blob/master/src/main/java/walkingkooka/locale/convert/LocaleConverterToDateTimeSymbols.java)
- [decimalNumberSymbols](https://github.com/mP1/walkingkooka-locale/blob/master/src/main/java/walkingkooka/locale/convert/LocaleConverterToDecimalNumberSymbols.java)
- [locale](https://github.com/mP1/walkingkooka-locale/blob/master/src/main/java/walkingkooka/locale/convert/LocaleConverterToLocale.java)
- [localeToString](https://github.com/mP1/walkingkooka-locale/blob/master/src/main/java/walkingkooka/locale/convert/LocaleConverterLocaleToString.java)
