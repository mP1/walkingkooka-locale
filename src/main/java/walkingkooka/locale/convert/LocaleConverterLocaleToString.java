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

import walkingkooka.Either;
import walkingkooka.convert.ShortCircuitingConverter;

import java.util.Locale;

/**
 * A {@link walkingkooka.convert.Converter} supports converting {@link Locale} to {@link String} returning the language-tag.
 */
final class LocaleConverterLocaleToString<C extends LocaleConverterContext> extends LocaleConverter<C>
    implements ShortCircuitingConverter<C> {

    /**
     * Type safe getter.
     */
    static <C extends LocaleConverterContext> LocaleConverterLocaleToString<C> instance() {
        return INSTANCE;
    }

    /**
     * Singleton
     */
    private final static LocaleConverterLocaleToString INSTANCE = new LocaleConverterLocaleToString();


    LocaleConverterLocaleToString() {
        super();
    }


    @Override
    public boolean canConvert(final Object value,
                              final Class<?> type,
                              final C context) {
        return value instanceof Locale &&
            String.class == type;
    }

    @Override
    public <T> Either<T, String> doConvert(final Object value,
                                           final Class<T> type,
                                           final C context) {
        return this.successfulConversion(
            ((Locale)value).toLanguageTag(),
            type
        );
    }

    // Object...........................................................................................................

    @Override
    public String toString() {
        return "Locale to String";
    }
}
