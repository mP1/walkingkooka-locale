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

import walkingkooka.convert.Converter;
import walkingkooka.datetime.DateTimeSymbols;
import walkingkooka.datetime.HasDateTimeSymbols;
import walkingkooka.datetime.HasOptionalDateTimeSymbols;

import java.util.Locale;

/**
 * A {@link Converter} that supports several different source types to return a {@link DateTimeSymbols}.
 */
final class LocaleConverterDateTimeSymbols<C extends LocaleConverterContext> extends LocaleConverter<DateTimeSymbols, C> {

    /**
     * Type safe getter.
     */
    static <C extends LocaleConverterContext> LocaleConverterDateTimeSymbols<C> instance() {
        return INSTANCE;
    }

    /**
     * Singleton
     */
    private final static LocaleConverterDateTimeSymbols INSTANCE = new LocaleConverterDateTimeSymbols();

    private LocaleConverterDateTimeSymbols() {
        super();
    }

    // LocaleConverter..................................................................................................

    @Override
    Class<DateTimeSymbols> targetType() {
        return DateTimeSymbols.class;
    }

    @Override
    boolean canConvertNotString(final Object value,
                                final C context) {
        return (value instanceof DateTimeSymbols ||
            value instanceof HasDateTimeSymbols ||
            value instanceof HasOptionalDateTimeSymbols);
    }

    @Override
    DateTimeSymbols tryConvertLocale(final Locale locale,
                                     final C context) {
        return context.dateTimeSymbolsForLocale(locale)
            .orElse(null);
    }

    @Override
    DateTimeSymbols tryConvertNonLocale(final Object value,
                                        final C context) {
        DateTimeSymbols result;

        if (value instanceof DateTimeSymbols) {
            result = (DateTimeSymbols)value;
        } else {
            if (value instanceof HasDateTimeSymbols) {
                result = ((HasDateTimeSymbols) value).dateTimeSymbols();
            } else {
                if (value instanceof HasOptionalDateTimeSymbols) {
                    result = ((HasOptionalDateTimeSymbols) value).dateTimeSymbols()
                        .orElse(null);
                } else {
                    result = this.tryConvertLocale(
                        context.convertOrFail(
                            value,
                            Locale.class
                        ),
                        context
                    );
                }
            }
        }

        return result;
    }
}
