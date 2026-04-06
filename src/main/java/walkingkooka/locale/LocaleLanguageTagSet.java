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

import walkingkooka.collect.iterator.Iterators;
import walkingkooka.collect.set.ImmutableSortedSetDefaults;
import walkingkooka.collect.set.Sets;
import walkingkooka.collect.set.SortedSets;
import walkingkooka.text.CharacterConstant;
import walkingkooka.text.HasText;
import walkingkooka.text.printer.IndentingPrinter;
import walkingkooka.text.printer.TreePrintable;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * An immutable {@link Set} containing unique {@link LocaleLanguageTag languageTag}.
 */
public final class LocaleLanguageTagSet extends AbstractSet<LocaleLanguageTag>
    implements ImmutableSortedSetDefaults<LocaleLanguageTagSet, LocaleLanguageTag>,
    HasText,
    TreePrintable {

    /**
     * An empty {@link LocaleLanguageTagSet}.
     */
    public static final LocaleLanguageTagSet EMPTY = new LocaleLanguageTagSet(SortedSets.empty());

    /**
     * The comma which separates the CSV text representation.
     */
    public static final CharacterConstant SEPARATOR = CharacterConstant.COMMA;

    /**
     * Handy filter that creates a {@link LocaleLanguageTag} for each locale that starts with that given text
     */
    public static Set<LocaleLanguageTag> filter(final String startsWith,
                                                final LocaleContext context) {

        final Set<LocaleLanguageTag> matched = Sets.ordered();

        for (final Locale locale : context.findByLocaleText(
            startsWith,
            0,
            Integer.MAX_VALUE
        )) {
            final String localeText = context.localeText(locale)
                .orElse(null);

            if (null != localeText && (LocaleContexts.CASE_SENSITIVITY.startsWith(localeText, startsWith) || LocaleContexts.CASE_SENSITIVITY.equals(localeText, startsWith))) {
                matched.add(
                    LocaleLanguageTag.fromLocale(locale)
                );
            }
        }

        return matched;
    }

    /**
     * Factory that creates {@link LocaleLanguageTagSet} with the given languageTag.
     */
    public static LocaleLanguageTagSet with(final Collection<LocaleLanguageTag> languageTag) {
        return EMPTY.setElements(languageTag);
    }

    private static LocaleLanguageTagSet withCopy(final SortedSet<LocaleLanguageTag> languageTag) {
        return languageTag.isEmpty() ?
            EMPTY :
            new LocaleLanguageTagSet(languageTag);
    }

    private LocaleLanguageTagSet(final SortedSet<LocaleLanguageTag> languageTags) {
        super();
        this.languageTags = languageTags;
    }

    // ImmutableSortedSet...............................................................................................

    @Override
    public Iterator<LocaleLanguageTag> iterator() {
        return Iterators.readOnly(
            this.languageTags.iterator()
        );
    }

    @Override
    public int size() {
        return this.languageTags.size();
    }

    @Override
    public Comparator<LocaleLanguageTag> comparator() {
        return null;
    }

    @Override
    public LocaleLanguageTagSet subSet(final LocaleLanguageTag from,
                                       final LocaleLanguageTag to) {
        return withCopy(
            this.languageTags.subSet(
                from,
                to
            )
        );
    }

    @Override
    public LocaleLanguageTagSet headSet(final LocaleLanguageTag locale) {
        return withCopy(
            this.languageTags.headSet(locale)
        );
    }

    @Override
    public LocaleLanguageTagSet tailSet(final LocaleLanguageTag locale) {
        return withCopy(
            this.languageTags.tailSet(locale)
        );
    }

    @Override
    public LocaleLanguageTag first() {
        return this.languageTags.first();
    }

    @Override
    public LocaleLanguageTag last() {
        return this.languageTags.last();
    }

    @Override
    public SortedSet<LocaleLanguageTag> toSet() {
        return new TreeSet<>(this.languageTags);
    }

    @Override
    public LocaleLanguageTagSet setElements(final Collection<LocaleLanguageTag> languageTags) {
        final LocaleLanguageTagSet languageTagset;

        if (languageTags instanceof LocaleLanguageTagSet) {
            languageTagset = (LocaleLanguageTagSet) languageTags;
        } else {
            final TreeSet<LocaleLanguageTag> copy = new TreeSet<>(
                Objects.requireNonNull(languageTags, "languageTag")
            );
            languageTagset = this.languageTags.equals(copy) ?
                this :
                withCopy(copy);
        }

        return languageTagset;
    }

    private final SortedSet<LocaleLanguageTag> languageTags;

    @Override
    public void elementCheck(final LocaleLanguageTag locale) {
        Objects.requireNonNull(locale, "locale");
    }

    // HasText..........................................................................................................

    @Override
    public String text() {
        if (null == this.text) {
            this.text = SEPARATOR.toSeparatedString(
                this,
                LocaleLanguageTag::value
            );
        }
        return this.text;
    }

    private String text;

    // TreePrintable....................................................................................................

    @Override
    public void printTree(final IndentingPrinter printer) {
        for (final LocaleLanguageTag localeCode : this) {
            localeCode.printTree(printer);
        }
    }
}
