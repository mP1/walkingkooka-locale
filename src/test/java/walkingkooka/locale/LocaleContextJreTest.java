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

package walkingkooka.locale;

import org.junit.jupiter.api.Test;
import walkingkooka.HashCodeEqualsDefinedTesting2;
import walkingkooka.collect.set.Sets;
import walkingkooka.datetime.HasDateTimeSymbolsTesting;
import walkingkooka.math.HasDecimalNumberSymbolsTesting;
import walkingkooka.reflect.ThrowableTesting;
import walkingkooka.util.HasLocaleTesting;

import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class LocaleContextJreTest implements LocaleContextTesting2<LocaleContextJre>,
    HashCodeEqualsDefinedTesting2<LocaleContextJre>,
    HasDateTimeSymbolsTesting,
    HasDecimalNumberSymbolsTesting,
    HasLocaleTesting,
    ThrowableTesting {

    @Test
    public void testWithNullLocaleFails() {
        assertThrows(
            NullPointerException.class,
            () -> LocaleContextJre.with(null)
        );
    }

    @Test
    public void testAvailableLocales() {
        this.checkNotEquals(
            Sets.empty(),
            this.createContext()
                .availableLocales()
        );
    }

    @Test
    public void testDateTimeSymbolsForLocaleWithEmpty() {
        this.dateTimeSymbolsForLocaleAndCheck(
            this.createContext(),
            Locale.forLanguageTag("")
        );
    }

    @Test
    public void testDateTimeSymbolsForLocaleWithUnd() {
        this.dateTimeSymbolsForLocaleAndCheck(
            this.createContext(),
            Locale.forLanguageTag("UND")
        );
    }

    @Test
    public void testDateTimeSymbols() {
        this.dateTimeSymbolsForLocaleAndCheck(
            this.createContext(),
            LOCALE,
            DATE_TIME_SYMBOLS
        );
    }

    @Test
    public void testDecimalNumberSymbolsForLocaleWithEmpty() {
        this.decimalNumberSymbolsForLocaleAndCheck(
            this.createContext(),
            Locale.forLanguageTag("")
        );
    }

    @Test
    public void testDecimalNumberSymbolsForLocaleWithUnd() {
        this.decimalNumberSymbolsForLocaleAndCheck(
            this.createContext(),
            Locale.forLanguageTag("UND")
        );
    }

    @Test
    public void testDecimalNumberSymbolsForLocale() {
        this.decimalNumberSymbolsForLocaleAndCheck(
            this.createContext(),
            LOCALE,
            DECIMAL_NUMBER_SYMBOLS
        );
    }

    @Test
    public void testFindLocaleByText() {
        this.findLocaleByTextAndCheck(
            this.createContext(),
            "German",
            0,
            3,
            Locale.forLanguageTag("de"),
            Locale.forLanguageTag("de-AT"),
            Locale.forLanguageTag("de-BE")
        );
    }

    @Test
    public void testFindLocaleByText2() {
        this.findLocaleByTextAndCheck(
            this.createContext(),
            "German",
            1,
            3,
            Locale.forLanguageTag("de-AT"),
            Locale.forLanguageTag("de-BE"),
            Locale.forLanguageTag("de-CH")
        );
    }

    @Test
    public void testFindLocaleByTextDifferentCase() {
        this.findLocaleByTextAndCheck(
            this.createContext(),
            "GERman",
            1,
            3,
            Locale.forLanguageTag("de-AT"),
            Locale.forLanguageTag("de-BE"),
            Locale.forLanguageTag("de-CH")
        );
    }

    @Test
    public void testLocaleForLanguageTag() {
        this.localeForLanguageTagAndCheck(
            this.createContext(),
            LocaleLanguageTag.fromLocale(LOCALE),
            LOCALE
        );
    }

    // localeText.......................................................................................................

    @Test
    public void testLocaleTextWithEmptyLocale() {
        this.localeTextAndCheck(
            this.createContext(),
            new Locale("")
        );
    }

    @Test
    public void testLocaleTextWithUndefinedLocale() {
        this.localeTextAndCheck(
            this.createContext(),
            new Locale("und")
        );
    }

    @Test
    public void testLocaleTextWithUndefinedLocale2() {
        this.localeTextAndCheck(
            this.createContext(),
            new Locale("UND")
        );
    }

    @Test
    public void testLocaleTextWithLocale() {
        this.localeTextAndCheck(
            this.createContext(),
            LOCALE,
            "English (Australia)"
        );
    }

    @Test
    public void testLocaleTextForAllAvailableLocales() {
        for (final Locale locale : Locale.getAvailableLocales()) {
            final LocaleContextJre context = LocaleContextJre.with(locale);
            this.checkNotEquals(
                Optional.of(""),
                context.localeText(locale),
                locale::toLanguageTag
            );
        }
    }

    // setLocale........................................................................................................

    @Test
    public void testSetLocaleWithEmptyLocaleFails() {
        final IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> this.createContext()
                .setLocale(
                    new Locale("")
                )
        );

        this.getMessageAndCheck(
            thrown,
            "Invalid or undefined locale \"\""
        );
    }

    @Test
    public void testSetLocaleWithUndefinedLocaleFails() {
        final IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> this.createContext()
                .setLocale(
                    new Locale("UND")
                )
        );

        this.getMessageAndCheck(
            thrown,
            "Invalid or undefined locale \"und\""
        );
    }

    @Test
    public void testRequireValidLocaleAllLocales() {
        for(Locale locale : this.createContext().availableLocales()) {
            LocaleLanguageTag.requireValidLocale(LOCALE);
        }
    }

    @Override
    public LocaleContextJre createContext() {
        return LocaleContextJre.with(LOCALE);
    }

    // hashCode/equals..................................................................................................

    @Test
    public void testEqualsDifferentLocale() {
        this.checkNotEquals(
            LocaleContextJre.with(DIFFERENT_LOCALE)
        );
    }

    @Override
    public LocaleContextJre createObject() {
        return this.createContext();
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(
            LocaleContextJre.with(LOCALE),
            "JRE en-AU"
        );
    }

    // class............................................................................................................

    @Override
    public Class<LocaleContextJre> type() {
        return LocaleContextJre.class;
    }

    @Override
    public void testTypeNaming() {
        throw new UnsupportedOperationException();
    }
}
