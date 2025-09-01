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

import walkingkooka.convert.ConverterContext;
import walkingkooka.convert.ConverterContextDelegator;
import walkingkooka.locale.LocaleContext;
import walkingkooka.locale.LocaleContextDelegator;

import java.util.Locale;
import java.util.Objects;

final class BasicLocaleConverterContext implements LocaleConverterContext,
    ConverterContextDelegator,
    LocaleContextDelegator {

    static BasicLocaleConverterContext with(final ConverterContext converterContext,
                                            final LocaleContext localeContext) {
        return new BasicLocaleConverterContext(
                Objects.requireNonNull(converterContext, "converterContext"),
            Objects.requireNonNull(localeContext, "localeContext")
        );
    }

    private BasicLocaleConverterContext(final ConverterContext converterContext,
                                        final LocaleContext localeContext) {
        this.converterContext = converterContext;
        this.localeContext = localeContext;
    }

    // ConverterContextDelegator........................................................................................

    @Override
    public ConverterContext converterContext() {
        return this.converterContext;
    }

    private final ConverterContext converterContext;

    // LocaleContextDelegator...........................................................................................

    @Override
    public Locale locale() {
        return this.localeContext.locale();
    }

    @Override
    public LocaleContext setLocale(final Locale locale) {
        this.localeContext.setLocale(locale);
        return this;
    }

    @Override
    public LocaleContext localeContext() {
        return this.localeContext;
    }

    private final LocaleContext localeContext;

    // Object...........................................................................................................

    @Override
    public String toString() {
        return this.converterContext.toString();
    }
}
