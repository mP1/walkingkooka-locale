/*
 * Copyright 2025 Miroslav Pokorny (github.com/mP1)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package walkingkooka.locale.convert;

import org.junit.jupiter.api.Test;
import walkingkooka.Cast;
import walkingkooka.Either;
import walkingkooka.collect.list.Lists;
import walkingkooka.convert.Converter;
import walkingkooka.convert.Converters;
import walkingkooka.datetime.DateTimeSymbols;
import walkingkooka.datetime.HasDateTimeSymbols;
import walkingkooka.datetime.HasOptionalDateTimeSymbols;

import java.text.DateFormatSymbols;
import java.util.Locale;
import java.util.Optional;

public final class LocaleConverterDateTimeSymbolsTest extends LocaleConverterTestCase<LocaleConverterDateTimeSymbols<LocaleConverterContext>, DateTimeSymbols> {

    private final static DateTimeSymbols DATE_TIME_SYMBOLS = DateTimeSymbols.fromDateFormatSymbols(
        new DateFormatSymbols(Locale.ENGLISH)
    );

    @Test
    public void testConvertDateTimeSymbols() {
        this.convertAndCheck(DATE_TIME_SYMBOLS);
    }

    @Test
    public void testConvertHasDateTimeSymbols() {
        this.convertAndCheck(
            new HasDateTimeSymbols() {
                @Override
                public DateTimeSymbols dateTimeSymbols() {
                    return DATE_TIME_SYMBOLS;
                }
            },
            DATE_TIME_SYMBOLS
        );
    }

    @Test
    public void testConvertHasOptionalDateTimeSymbols() {
        this.convertAndCheck(
            new HasOptionalDateTimeSymbols() {
                @Override
                public Optional<DateTimeSymbols> dateTimeSymbols() {
                    return Optional.of(DATE_TIME_SYMBOLS);
                }
            },
            DATE_TIME_SYMBOLS
        );
    }

    @Test
    public void testConvertHasOptionalDateTimeSymbolsWhenEmpty() {
        this.convertAndCheck(
            new HasOptionalDateTimeSymbols() {
                @Override
                public Optional<DateTimeSymbols> dateTimeSymbols() {
                    return Optional.empty();
                }
            },
            DateTimeSymbols.class,
            null // expected null
        );
    }

    @Test
    public void testConvertStringToDateSymbolsWithLocaleLanguageTag() {
        final Locale locale = Locale.ENGLISH;

        this.convertAndCheck(
            locale.toLanguageTag(),
            DateTimeSymbols.class,
            new FakeLocaleConverterContext() {

                @Override
                public boolean canConvert(final Object value,
                                          final Class<?> type) {
                    return this.converter.canConvert(
                        value,
                        type,
                        this
                    );
                }

                @Override
                public <T> Either<T, String> convert(final Object value,
                                                     final Class<T> target) {
                    return this.converter.convert(
                        value,
                        target,
                        this
                    );
                }

                private final Converter<LocaleConverterContext> converter = Converters.collection(
                    Lists.of(
                        Converters.characterOrCharSequenceOrHasTextOrStringToCharacterOrCharSequenceOrString(),
                        LocaleConverters.locale()
                    )
                );

                @Override
                public Optional<DateTimeSymbols> dateTimeSymbolsForLocale(final Locale locale) {
                    return Optional.of(DATE_TIME_SYMBOLS);
                }
            },
            DateTimeSymbols.fromDateFormatSymbols(
                new DateFormatSymbols(locale)
            )
        );
    }

    @Override
    public LocaleConverterDateTimeSymbols<LocaleConverterContext> createConverter() {
        return LocaleConverterDateTimeSymbols.instance();
    }

    @Override
    public LocaleConverterContext createContext() {
        return LocaleConverterContexts.fake();
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(
            LocaleConverterDateTimeSymbols.instance(),
            DateTimeSymbols.class.getSimpleName()
        );
    }

    // class............................................................................................................

    @Override
    public Class<LocaleConverterDateTimeSymbols<LocaleConverterContext>> type() {
        return Cast.to(LocaleConverterDateTimeSymbols.class);
    }
}
