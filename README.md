[![Build Status](https://github.com/mP1/walkingkooka-locale/actions/workflows/build.yaml/badge.svg)](https://github.com/mP1/walkingkooka-locale/actions/workflows/build.yaml/badge.svg)
[![Coverage Status](https://coveralls.io/repos/github/mP1/walkingkooka-locale/badge.svg?branch=master)](https://coveralls.io/repos/github/mP1/walkingkooka-locale?branch=master)
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

- [color](https://github.com/mP1/walkingkooka-spreadsheet/blob/master/src/main/java/walkingkooka/spreadsheet/convert/SpreadsheetConverters.java)
- [color-to-number](https://github.com/mP1/walkingkooka-color/blob/master/src/main/java/walkingkooka/color/convert/ColorToNumberConverter.java)
- [color-to-text](https://github.com/mP1/walkingkooka-color/blob/master/src/main/java/walkingkooka/color/convert/ColorToTextConverter.java)
- [error-throwing](https://github.com/mP1/walkingkooka-spreadsheet/blob/master/src/main/java/walkingkooka/spreadsheet/convert/SpreadsheetConverterSpreadsheetErrorThrowing.java)
- [error-to-number](https://github.com/mP1/walkingkooka-spreadsheet/blob/master/src/main/java/walkingkooka/spreadsheet/convert/SpreadsheetConverterSpreadsheetErrorToNumber.java)
- [format-pattern-to-string](https://github.com/mP1/walkingkooka-spreadsheet/blob/master/src/main/java/walkingkooka/spreadsheet/convert/SpreadsheetConverterFormatPatternToString.java)
- [form-and-validation](https://github.com/mP1/walkingkooka-spreadsheet/blob/master/src/main/java/walkingkooka/spreadsheet/convert/SpreadsheetConverters.java)
- [general](https://github.com/mP1/walkingkooka-spreadsheet/blob/master/src/main/java/walkingkooka/spreadsheet/convert/SpreadsheetConverterGeneral.java)
- [has-style-style](https://github.com/mP1/walkingkooka-tree-text/blob/master/src/main/java/walkingkooka/tree/text/convert/HasTextStyleToTextStyleConverter.java)
- [json](https://github.com/mP1/walkingkooka-spreadsheet/blob/master/src/main/java/walkingkooka/spreadsheet/convert/SpreadsheetConverters.java)
- [json-to](https://github.com/mP1/walkingkooka-tree-json-convert/blob/master/src/main/java/walkingkooka/tree/json/convert/JsonNodeToUnmarshallingConverter.java)
- [locale](https://github.com/mP1/walkingkooka-spreadsheet/blob/master/src/main/java/walkingkooka/spreadsheet/convert/SpreadsheetConverters.java)
- [null-to-number](https://github.com/mP1/walkingkooka-spreadsheet/blob/master/src/main/java/walkingkooka/spreadsheet/convert/SpreadsheetConverterNullToNumber.java) Handles converting null/missing cell values into 0, 10 + B2 when B2 is missing becomes 10 + 0. 
