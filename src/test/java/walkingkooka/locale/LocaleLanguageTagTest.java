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
import walkingkooka.HasValueTesting;
import walkingkooka.compare.ComparableTesting2;
import walkingkooka.reflect.ClassTesting2;
import walkingkooka.reflect.JavaVisibility;
import walkingkooka.reflect.ThrowableTesting;
import walkingkooka.test.ParseStringTesting;
import walkingkooka.text.HasLineEndingTesting;
import walkingkooka.text.printer.TreePrintableTesting;
import walkingkooka.util.HasLocaleTesting;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class LocaleLanguageTagTest implements ComparableTesting2<LocaleLanguageTag>,
    ClassTesting2<LocaleLanguageTag>,
    HasLineEndingTesting,
    HasLocaleTesting,
    ParseStringTesting<LocaleLanguageTag>,
    ThrowableTesting,
    TreePrintableTesting,
    HasValueTesting {

    // isValidLocale....................................................................................................

    @Test
    public void testIsValidLocaleWithNullFails() {
        assertThrows(
            NullPointerException.class,
            () -> LocaleLanguageTag.isValidLocale(null)
        );
    }

    @Test
    public void testIsValidWithEmptyLocale() {
        this.isValidLocaleAndCheck(
            new Locale(""),
            false
        );
    }

    @Test
    public void testIsValidWithUndLocale() {
        this.isValidLocaleAndCheck(
            new Locale("und"),
            false
        );
    }

    @Test
    public void testIsValidWithUNDLocale() {
        this.isValidLocaleAndCheck(
            new Locale("UND"),
            false
        );
    }

    @Test
    public void testIsValidWithLocale() {
        this.isValidLocaleAndCheck(
            HasLocaleTesting.LOCALE,
            true
        );
    }

    private void isValidLocaleAndCheck(final Locale locale,
                                       final boolean expected) {
        this.checkEquals(
            expected,
            LocaleLanguageTag.isValidLocale(locale),
            locale::toString
        );
    }

    // fromLocale.......................................................................................................

    @Test
    public void testFromLocaleNullLocaleFails() {
        assertThrows(
            NullPointerException.class,
            () -> LocaleLanguageTag.fromLocale(null)
        );
    }

    @Test
    public void testFromLocaleWithEmptyLocaleFails() {
        final IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> LocaleLanguageTag.fromLocale(
                new Locale("")
            )
        );

        this.getMessageAndCheck(
            thrown,
            "Invalid locale \"\""
        );
    }

    @Test
    public void testFromLocaleWitUndFails() {
        final IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> LocaleLanguageTag.fromLocale(
                new Locale("und")
            )
        );

        this.getMessageAndCheck(
            thrown,
            "Invalid locale \"und\""
        );
    }

    @Test
    public void testFromLocaleWitUNDFails() {
        final IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> LocaleLanguageTag.fromLocale(
                new Locale("UND")
            )
        );

        this.getMessageAndCheck(
            thrown,
            "Invalid locale \"und\""
        );
    }

    @Test
    public void testFromLocale() {
        final LocaleLanguageTag localeLanguageTag = LocaleLanguageTag.fromLocale(LOCALE);
        this.valueAndCheck(
            localeLanguageTag,
            LOCALE.toLanguageTag()
        );
    }

    @Test
    public void testFromLocaleAllLocales() {
        for(final Locale locale : Locale.getAvailableLocales()) {
            final String languageTag = locale.toLanguageTag();
            if(languageTag.isEmpty() || languageTag.equals("und")) {
                continue;
            }

            final LocaleLanguageTag localeLanguageTag = LocaleLanguageTag.parse(languageTag);
            this.valueAndCheck(
                localeLanguageTag,
                languageTag
            );
        }
    }

    @Test
    public void testFromLocaleAndParse() {
        this.checkEquals(
            LocaleLanguageTag.fromLocale(LOCALE),
            LocaleLanguageTag.parse(LOCALE.toLanguageTag())
        );
    }

    // parse............................................................................................................

    @Test
    public void testParseLocaleLanguageTag() {
        final String languageTag = "en-AU";

        final LocaleLanguageTag localeLanguageTag = LocaleLanguageTag.parse(languageTag);
        this.valueAndCheck(
            localeLanguageTag,
            languageTag
        );
    }

    @Test
    public void testParse() {
        this.parseStringAndCheck(
            LOCALE.toLanguageTag(),
            LocaleLanguageTag.fromLocale(LOCALE)
        );
    }

    @Test
    public void testParseAllLocales() {
        final StringBuilder invalid = new StringBuilder();

        for (final Locale locale : Locale.getAvailableLocales()) {
            final String languageTag = locale.toLanguageTag();
            if(languageTag.isEmpty() || languageTag.equals("und")) {
                continue;
            }

            try {
                LocaleLanguageTag.parse(languageTag);
            } catch (final IllegalArgumentException cause) {
                invalid.append(languageTag + LINE_ENDING);
            }
        }

        this.checkEquals(
            "",
            invalid.toString()
        );
    }

    @Override
    public LocaleLanguageTag parseString(final String text) {
        return LocaleLanguageTag.parse(text);
    }

    @Override
    public Class<? extends RuntimeException> parseStringFailedExpected(final Class<? extends RuntimeException> thrown) {
        return thrown;
    }

    @Override
    public RuntimeException parseStringFailedExpected(final RuntimeException thrown) {
        return thrown;
    }

    // comparable.......................................................................................................

    @Test
    public void testComparableLess() {
        this.compareToAndCheckLess(
            LocaleLanguageTag.parse(
                Locale.FRANCE.toLanguageTag()
            )
        );
    }

    @Test
    public void testEqualsDifferentCase() {
        this.compareToAndCheckEquals(
            LocaleLanguageTag.parse("en-AU"),
            LocaleLanguageTag.parse("EN-AU")
        );
    }

    @Override
    public LocaleLanguageTag createComparable() {
        return LocaleLanguageTag.fromLocale(LOCALE);
    }

    // TreePrintable....................................................................................................

    @Test
    public void testPrintTree() {
        this.treePrintAndCheck(
            LocaleLanguageTag.parse("en-AU"),
            "en-AU\n"
        );
    }

    // class............................................................................................................

    @Override
    public Class<LocaleLanguageTag> type() {
        return LocaleLanguageTag.class;
    }

    @Override
    public JavaVisibility typeVisibility() {
        return JavaVisibility.PUBLIC;
    }
}
