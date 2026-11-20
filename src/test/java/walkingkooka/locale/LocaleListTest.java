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
import walkingkooka.collect.list.HasCsvStringListTesting;
import walkingkooka.collect.list.HasTsvStringListTesting;
import walkingkooka.collect.list.ImmutableListTesting;
import walkingkooka.collect.list.Lists;
import walkingkooka.collect.list.StringLikeList;
import walkingkooka.collect.list.StringList;
import walkingkooka.util.HasLocaleTesting;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class LocaleListTest implements ImmutableListTesting<LocaleList, Locale>,
    HasLocaleTesting,
    HasCsvStringListTesting,
    HasTsvStringListTesting {

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

    @Override
    public LocaleList createList() {
        return LocaleList.EMPTY;
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

    // class............................................................................................................

    @Override
    public Class<LocaleList> type() {
        return LocaleList.class;
    }
}
