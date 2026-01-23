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
import walkingkooka.convert.ConverterContexts;
import walkingkooka.convert.Converters;
import walkingkooka.datetime.DateTimeContexts;
import walkingkooka.locale.LocaleContexts;
import walkingkooka.math.DecimalNumberContexts;
import walkingkooka.text.Indentation;
import walkingkooka.text.LineEnding;
import walkingkooka.util.HasLocale;
import walkingkooka.util.HasOptionalLocale;

import java.util.Locale;
import java.util.Optional;

public final class LocaleConverterToLocaleTest extends LocaleConverterToTestCase<LocaleConverterToLocale<LocaleConverterContext>, Locale> {

    private final static Locale LOCALE = Locale.ENGLISH;

    @Test
    public void testConvertLocale() {
        this.convertAndCheck(LOCALE);
    }

    @Test
    public void testConvertHasLocale() {
        this.convertAndCheck(
            new HasLocale() {
                @Override
                public Locale locale() {
                    return LOCALE;
                }
            },
            LOCALE
        );
    }

    @Test
    public void testConvertHasOptionalLocale() {
        this.convertAndCheck(
            new HasOptionalLocale() {
                @Override
                public Optional<Locale> locale() {
                    return Optional.of(LOCALE);
                }
            },
            LOCALE
        );
    }

    @Test
    public void testConvertHasOptionalLocaleMissing() {
        this.convertAndCheck(
            new HasOptionalLocale() {
                @Override
                public Optional<Locale> locale() {
                    return Optional.empty();
                }
            },
            Locale.class,
            null
        );
    }

    @Test
    public void testConvertStringToLocale() {
        this.convertAndCheck(
            LOCALE.toLanguageTag(),
            LOCALE
        );
    }

    @Override
    public LocaleConverterToLocale<LocaleConverterContext> createConverter() {
        return LocaleConverterToLocale.instance();
    }

    @Override
    public LocaleConverterContext createContext() {
        return LocaleConverterContexts.basic(
            ConverterContexts.basic(
                false, // canNumbersHaveGroupSeparator
                Converters.EXCEL_1904_DATE_SYSTEM_OFFSET,
                Indentation.SPACES2,
                LineEnding.NL,
                ',', // valueSeparator
                Converters.characterOrCharSequenceOrHasTextOrStringToCharacterOrCharSequenceOrString(),
                DateTimeContexts.fake(),
                DecimalNumberContexts.fake()
            ),
            LocaleContexts.jre(Locale.FRANCE)
        );
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(
            LocaleConverterToLocale.instance(),
            Locale.class.getSimpleName()
        );
    }

    // class............................................................................................................


    @Override
    public Class<LocaleConverterToLocale<LocaleConverterContext>> type() {
        return Cast.to(LocaleConverterToLocale.class);
    }
}
