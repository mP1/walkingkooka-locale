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
import walkingkooka.collect.list.Lists;
import walkingkooka.collect.set.Sets;
import walkingkooka.collect.set.SortedSets;
import walkingkooka.reflect.JavaVisibility;
import walkingkooka.reflect.PublicStaticHelperTesting;

import java.lang.reflect.Method;
import java.util.Locale;
import java.util.Set;

public final class LocaleContextsTest implements PublicStaticHelperTesting<LocaleContexts> {

    // LANGUAGE_TAG_COMPARATOR..........................................................................................

    @Test
    public void testLanguageTagComparatorSort() {
        final Locale en = Locale.ENGLISH;
        final Locale enAu = Locale.forLanguageTag("en-AU");
        final Locale enCA = Locale.forLanguageTag("en-CA");
        final Locale enNZ = Locale.forLanguageTag("en-NZ");

        final Set<Locale> sortedByLanguageTag = SortedSets.tree(LocaleContexts.LANGUAGE_TAG_COMPARATOR);
        sortedByLanguageTag.add(en);
        sortedByLanguageTag.add(enNZ);
        sortedByLanguageTag.add(enAu);
        sortedByLanguageTag.add(enCA);

        this.checkEquals(
                Lists.of(
                        en,
                        enAu,
                        enCA,
                        enNZ
                ),
                Lists.of(
                        sortedByLanguageTag.toArray()
                )
        );
    }

    // class............................................................................................................

    @Override
    public boolean canHavePublicTypes(final Method method) {
        return false;
    }

    @Override
    public Class<LocaleContexts> type() {
        return LocaleContexts.class;
    }

    @Override
    public JavaVisibility typeVisibility() {
        return JavaVisibility.PUBLIC;
    }
}
