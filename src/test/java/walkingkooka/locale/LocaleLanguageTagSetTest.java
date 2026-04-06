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
import walkingkooka.collect.set.ImmutableSortedSetTesting;
import walkingkooka.collect.set.Sets;
import walkingkooka.collect.set.SortedSets;
import walkingkooka.text.HasTextTesting;
import walkingkooka.text.printer.TreePrintableTesting;

import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class LocaleLanguageTagSetTest implements ImmutableSortedSetTesting<LocaleLanguageTagSet, LocaleLanguageTag>,
    HasTextTesting,
    TreePrintableTesting {

    private final static LocaleLanguageTag EN_AU = LocaleLanguageTag.fromLocale(
        Locale.forLanguageTag("en-AU")
    );

    private final static LocaleLanguageTag EN_NZ = LocaleLanguageTag.fromLocale(
        Locale.forLanguageTag("en-NZ")
    );

    @Test
    public void testWithNullFails() {
        assertThrows(
            NullPointerException.class,
            () -> LocaleLanguageTagSet.with(null)
        );
    }

    @Test
    public void testDeleteBecomesEmpty() {
        assertSame(
            LocaleLanguageTagSet.EMPTY,
            LocaleLanguageTagSet.EMPTY.concat(EN_AU)
                .delete(EN_AU)
        );
    }

    @Test
    public void testSetElementsWithLocaleLanguageTagSet() {
        final LocaleLanguageTagSet set = LocaleLanguageTagSet.with(
            SortedSets.of(
                LocaleLanguageTag.fromLocale(
                    Locale.US
                )
            )
        );

        assertSame(
            set,
            set.setElements(set)
        );
    }

    @Test
    public void testSetElementsWithLocaleLanguageTagSet2() {
        final LocaleLanguageTagSet set = this.createSet();
        final LocaleLanguageTagSet set2 = this.createSet();

        assertSame(
            set2,
            set.setElements(
                set2
            )
        );
    }

    @Override
    public LocaleLanguageTagSet createSet() {
        final SortedSet<LocaleLanguageTag> sortedSet = SortedSets.tree();

        sortedSet.add(
            LocaleLanguageTag.fromLocale(Locale.ENGLISH)
        );
        sortedSet.add(EN_AU);
        sortedSet.add(EN_NZ);

        return LocaleLanguageTagSet.with(
            SortedSets.of(
                LocaleLanguageTag.fromLocale(Locale.ENGLISH),
                EN_AU,
                EN_NZ
            )
        );
    }

    // filter...........................................................................................................

    private final static Locale ENAU = Locale.forLanguageTag("en-AU");
    private final static Locale ENNZ = Locale.forLanguageTag("en-NZ");
    private final static Locale FR = Locale.FRENCH;

    private final static String ENGLISH_AUSTRALIA_TEXT = "English (Australia)";
    private final static String ENGLISH_NEW_ZEALAND_TEXT = "English (New Zealand)";
    private final static String FRENCH_TEXT = "French 123";

    private final static LocaleContext CONTEXT = new FakeLocaleContext() {

        @Override
        public Set<Locale> findByLocaleText(final String text,
                                            final int offset,
                                            final int count) {
            return Sets.of(
                ENAU,
                ENNZ,
                FR
            );
        }

        @Override
        public Optional<String> localeText(final Locale locale) {
            return Optional.ofNullable(
                ENAU.equals(locale) ?
                    ENGLISH_AUSTRALIA_TEXT :
                    ENNZ.equals(locale) ?
                        ENGLISH_NEW_ZEALAND_TEXT :
                        FR.equals(locale) ?
                            FRENCH_TEXT :
                            null
            );
        }
    };

    @Test
    public void testFilterMatchesNone() {
        this.filterAndCheck(
            "Z",
            CONTEXT
        );
    }

    @Test
    public void testFilterMatchesSome() {
        this.filterAndCheck(
            "English",
            CONTEXT,
            LocaleLanguageTag.fromLocale(ENAU),
            LocaleLanguageTag.fromLocale(ENNZ)
        );
    }

    @Test
    public void testFilterMatchesSome2() {
        this.filterAndCheck(
            FRENCH_TEXT,
            CONTEXT,
            LocaleLanguageTag.fromLocale(FR)
        );
    }

    private void filterAndCheck(final String startsWith,
                                final LocaleContext context,
                                final LocaleLanguageTag... expected) {
        this.filterAndCheck(
            startsWith,
            context,
            Sets.of(expected)
        );
    }

    private void filterAndCheck(final String startsWith,
                                final LocaleContext context,
                                final Set<LocaleLanguageTag> expected) {
        this.checkEquals(
            expected,
            LocaleLanguageTagSet.filter(
                startsWith,
                context
            )
        );
    }

    // HasText..........................................................................................................

    @Test
    public void testTextWhenEmpty() {
        this.textAndCheck(
            LocaleLanguageTagSet.EMPTY,
            ""
        );
    }

    @Test
    public void testTextWhenNotEmpty() {
        this.textAndCheck(
            this.createSet(),
            "en,en-AU,en-NZ"
        );
    }

    // TreePrintable....................................................................................................

    @Test
    public void testTreePrint() {
        this.treePrintAndCheck(
            this.createSet(),
            "en\n" +
                "en-AU\n" +
                "en-NZ\n"
        );
    }

    // class............................................................................................................

    @Override
    public Class<LocaleLanguageTagSet> type() {
        return LocaleLanguageTagSet.class;
    }
}