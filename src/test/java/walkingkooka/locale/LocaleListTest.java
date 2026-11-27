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
import walkingkooka.collect.list.HasCsvStringListTsvStringListTesting2;
import walkingkooka.collect.list.ImmutableListTesting;
import walkingkooka.collect.list.Lists;
import walkingkooka.collect.list.StringLikeList;
import walkingkooka.collect.list.StringList;
import walkingkooka.util.HasLocaleTesting;

import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class LocaleListTest implements ImmutableListTesting<LocaleList, Locale>,
    HasLocaleTesting,
    HasCsvStringListTsvStringListTesting2<LocaleList>,
    HashCodeEqualsDefinedTesting2<LocaleList>,
    LocaleContextTesting {

    @Test
    public void testFromStringLikeListWithNullFails() {
        assertThrows(
            NullPointerException.class,
            () -> LocaleList.fromStringLikeList(null)
        );
    }

    @Test
    public void testFromStringLikeListWithEmpty() {
        this.fromStringLikeList(
            StringList.EMPTY,
            LocaleList.EMPTY
        );
    }

    @Test
    public void testFromStringLikeListWithNotEmpty() {
        this.fromStringLikeList(
            StringList.EMPTY.concat(LOCALE.toLanguageTag())
                .concat(DIFFERENT_LOCALE.toLanguageTag()),
            LocaleList.EMPTY.concat(LOCALE)
                .concat(DIFFERENT_LOCALE)
        );
    }

    @Test
    public void testFromStringLikeListWithNotEmptyWithNull() {
        this.fromStringLikeList(
            StringList.EMPTY.concat(LOCALE.toLanguageTag())
                .concat(null)
                .concat(DIFFERENT_LOCALE.toLanguageTag()),
            LocaleList.EMPTY.concat(LOCALE)
                .concat(null)
                .concat(DIFFERENT_LOCALE)
        );
    }

    @Test
    public void testFromStringLikeListWithNotEmptyWithEmpty() {
        this.fromStringLikeList(
            StringList.EMPTY.concat(LOCALE.toLanguageTag())
                .concat("")
                .concat(DIFFERENT_LOCALE.toLanguageTag()),
            LocaleList.EMPTY.concat(LOCALE)
                .concat(null)
                .concat(DIFFERENT_LOCALE)
        );
    }

    private void fromStringLikeList(final StringLikeList strings,
                                    final LocaleList expected) {
        this.checkEquals(
            expected,
            LocaleList.fromStringLikeList(strings),
            strings::toString
        );
    }

    // setElements......................................................................................................

    @Test
    public void testSetElementsWithListIncludingNull() {
        final List<Locale> list = Lists.of(
            DIFFERENT_LOCALE,
            null
        );

        this.setElementsAndCheck(
            new LocaleList(
                Lists.of(LOCALE)
            ),
            list,
            new LocaleList(list)
        );
    }

    @Test
    public void testSetElementsWithDifferent() {
        this.setElementsAndCheck(
            new LocaleList(
                Lists.of(LOCALE)
            ),
            Lists.of(DIFFERENT_LOCALE),
            new LocaleList(
                Lists.of(DIFFERENT_LOCALE)
            )
        );
    }

    @Test
    public void testSetElementsWithDifferentEmpty() {
        this.setElementsAndCheck(
            new LocaleList(
                Lists.of(LOCALE)
            ),
            Lists.empty(),
            LocaleList.EMPTY
        );
    }

    // subList..........................................................................................................

    private final static Locale LOCALE1 = LOCALE;
    private final static Locale LOCALE2 = DIFFERENT_LOCALE;
    private final static Locale LOCALE3 = Locale.forLanguageTag("en-CA");
    private final static Locale LOCALE4 = Locale.forLanguageTag("en-UK");
    private final static Locale LOCALE5 = Locale.forLanguageTag("en-US");

    private final static List<Locale> ELEMENTS = Lists.of(
        LOCALE1,
        LOCALE2,
        LOCALE3,
        LOCALE4,
        LOCALE5
    );

    @Test
    public void testSubList() {
        this.subListAndCheck(
            new LocaleList(ELEMENTS),
            0,
            2,
            new LocaleList(
                Lists.of(
                    LOCALE1,
                    LOCALE2
                )
            )
        );
    }

    @Test
    public void testSubList2() {
        this.subListAndCheck(
            new LocaleList(ELEMENTS),
            2,
            4,
            new LocaleList(
                Lists.of(
                    LOCALE3,
                    LOCALE4
                )
            )
        );
    }

    @Test
    public void testSubListSubList() {
        this.subListAndCheck(
            new LocaleList(ELEMENTS)
                .subList(
                    1,
                    4
                ), // 2, 3, 4
            1,
            3,
            new LocaleList(
                Lists.of(
                    LOCALE3,
                    LOCALE4
                )
            )
        );
    }

    @Override
    public LocaleList createList() {
        return LocaleList.EMPTY;
    }

    @Override
    public LocaleList createHasCsvStringListTsvStringList() {
        return this.createList();
    }

    // HasCsvStringList.................................................................................................

    @Test
    public void testCsvStringList() {
        this.csvStringListAndCheck(
            LocaleList.EMPTY.concat(LOCALE)
                .concat(DIFFERENT_LOCALE),
            "en-AU,en-NZ"
        );
    }

    @Test
    public void testCsvStringListWithNull() {
        this.csvStringListAndCheck(
            LocaleList.EMPTY.concat(LOCALE)
                .concat(null)
                .concat(DIFFERENT_LOCALE),
            "en-AU,,en-NZ"
        );
    }

    // HasTsvStringList.................................................................................................

    @Test
    public void testTsvStringList() {
        this.tsvStringListAndCheck(
            LocaleList.EMPTY.concat(LOCALE)
                .concat(null)
                .concat(DIFFERENT_LOCALE),
            "en-AU\t\ten-NZ"
        );
    }

    @Test
    public void testTsvStringListIncludesNull() {
        this.tsvStringListAndCheck(
            LocaleList.EMPTY.concat(LOCALE)
                .concat(null)
                .concat(DIFFERENT_LOCALE),
            "en-AU\t\ten-NZ"
        );
    }

    // hashCode/equals..................................................................................................

    @Test
    public void testEqualsNotEmpty() {
        this.checkEquals(
            LocaleList.EMPTY.concat(LOCALE)
                .concat(DIFFERENT_LOCALE),
            LocaleList.EMPTY.concat(LOCALE)
                .concat(DIFFERENT_LOCALE)
        );
    }

    @Test
    public void testEqualsNotEmptyDifferent() {
        this.checkNotEquals(
            LocaleList.EMPTY.concat(LOCALE),
            LocaleList.EMPTY.concat(DIFFERENT_LOCALE)
        );
    }

    @Test
    public void testEqualsAllLocaleContextAvailableLocale() {
        for(final Locale locale : LOCALE_CONTEXT.availableLocales()) {
            if(locale.toString().equalsIgnoreCase("no_NO_NY")) {
                continue;
            }

            this.checkEquals(
                LocaleList.EMPTY.concat(locale),
                LocaleList.EMPTY.concat(
                    Locale.forLanguageTag(locale.toLanguageTag())
                )
            );
        }
    }

    @Override
    public LocaleList createObject() {
        return this.createList();
    }
    
    // class............................................................................................................

    @Override
    public Class<LocaleList> type() {
        return LocaleList.class;
    }
}
