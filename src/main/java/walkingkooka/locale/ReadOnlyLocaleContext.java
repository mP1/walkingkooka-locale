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

import java.util.Locale;
import java.util.Objects;

/**
 * Wraps another {@link LocaleContext} and blocks attempts to set the {@link Locale}.
 */
final class ReadOnlyLocaleContext implements LocaleContext,
    LocaleContextDelegator {

    static ReadOnlyLocaleContext with(final LocaleContext context) {
        final ReadOnlyLocaleContext readOnlyLocaleContext;

        if(context instanceof ReadOnlyLocaleContext) {
            readOnlyLocaleContext = (ReadOnlyLocaleContext) context;
        } else {
            readOnlyLocaleContext = new ReadOnlyLocaleContext(
                Objects.requireNonNull(context, "context")
            );
        }

        return readOnlyLocaleContext;
    }

    private ReadOnlyLocaleContext(final LocaleContext context) {
        super();
        this.context = context;
    }

    @Override
    public void setLocale(final Locale locale) {
        Objects.requireNonNull(locale, "locale");

        if (false == this.locale().equals(locale)) {
            throw new UnsupportedOperationException("Unable to change locale to " + locale);
        }
    }

    // LocaleContextDelegator...........................................................................................

    @Override
    public LocaleContext localeContext() {
        return this.context;
    }

    private final LocaleContext context;

    // String...........................................................................................................

    @Override
    public String toString() {
        return "ReadOnly " + this.context;
    }
}
