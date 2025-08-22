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

/**
 * A {@link Converter} that supports several different source types to return a {@link DateTimeSymbols}.
 */
final class LocaleConverterDateTimeSymbols<C extends LocaleConverterContext> extends LocaleConverter<C> {

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

    // TryingShortCircuitingConverter...................................................................................

    @Override
    public boolean canConvert(final Object value,
                              final Class<?> type,
                              final C c) {
        return (value instanceof DateTimeSymbols ||
            value instanceof HasDateTimeSymbols ||
            value instanceof HasOptionalDateTimeSymbols) &&
            DateTimeSymbols.class == type;
    }

    @Override
    public Object tryConvertOrFail(final Object value,
                                   final Class<?> type,
                                   final C context) {
        Object result;

        if (value instanceof DateTimeSymbols) {
            result = value;
        } else {
            if (value instanceof HasDateTimeSymbols) {
                result = ((HasDateTimeSymbols) value).dateTimeSymbols();
            } else {
                if (value instanceof HasOptionalDateTimeSymbols) {
                    result = ((HasOptionalDateTimeSymbols) value).dateTimeSymbols()
                        .orElse(null);
                } else {
                    throw new IllegalArgumentException("Unknown value " + value);
                }
            }
        }

        return result;
    }

    // Object...........................................................................................................

    @Override
    public String toString() {
        return DateTimeSymbols.class.getSimpleName();
    }
}
