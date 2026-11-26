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

import walkingkooka.collect.list.CsvStringList;
import walkingkooka.collect.list.HasCsvStringListTsvStringList;
import walkingkooka.collect.list.ImmutableListDefaults;
import walkingkooka.collect.list.Lists;
import walkingkooka.collect.list.StringLikeList;
import walkingkooka.text.CharSequences;

import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A {@link List} that holds nullable {@link Locale locales}.
 */
public final class LocaleList extends AbstractList<Locale>
    implements ImmutableListDefaults<LocaleList, Locale>,
    HasCsvStringListTsvStringList {

    public final static LocaleList EMPTY = new LocaleList(Lists.empty());

    /**
     * Accepts a {@link StringLikeList} converting non null and non empty into a {@link Locale}. Null or empty strings
     * will become null {@link Locale}.
     */
    public static LocaleList fromStringLikeList(final StringLikeList strings) {
        Objects.requireNonNull(strings, "strings");

        return EMPTY.setElements(
            strings.stream()
                .map(
                    (final String string) -> CharSequences.isNullOrEmpty(string) ?
                        null :
                        Locale.forLanguageTag(string)
                ).collect(Collectors.toList())
        );
    }

    // @VisibleForTesting
    LocaleList(final List<Locale> list) {
        super();
        this.list = list;
    }

    @Override
    public Locale get(final int index) {
        return this.list.get(index);
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public LocaleList setElements(final Collection<Locale> collection) {
        final LocaleList localeList;

        if (collection instanceof LocaleList) {
            localeList = (LocaleList) collection;
        } else {
            final List<Locale> copy = Lists.array();
            copy.addAll(collection);

            localeList = this.equals(copy) ?
                this :
                this.replaceLocale(copy);
        }

        return localeList;
    }

    @Override
    public LocaleList subList(final int from,
                              final int to) {
        return 0 == from && this.size() == to ?
            this :
            this.replaceLocale(
                this.list.subList(
                    from,
                    to
                )
            );
    }

    private LocaleList replaceLocale(final List<Locale> locales) {
        return locales.isEmpty() ?
            EMPTY :
            new LocaleList(locales);
    }

    @Override
    public void elementCheck(final Locale locale) {
        // null is allowed
    }

    private final List<Locale> list;

    // HasCsvStringList.................................................................................................

    @Override
    public CsvStringList csvStringList() {
        return CsvStringList.makeCsvStringList(
            this.list,
            (Locale locale) -> null == locale ?
                "" :
                locale.toLanguageTag()
        );
    }
}
