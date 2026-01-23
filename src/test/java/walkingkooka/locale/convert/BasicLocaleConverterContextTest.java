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

import org.junit.jupiter.api.Test;
import walkingkooka.convert.ConverterContext;
import walkingkooka.convert.ConverterContexts;
import walkingkooka.convert.Converters;
import walkingkooka.datetime.DateTimeContexts;
import walkingkooka.datetime.DateTimeSymbols;
import walkingkooka.locale.LocaleContext;
import walkingkooka.locale.LocaleContexts;
import walkingkooka.math.DecimalNumberContexts;
import walkingkooka.text.Indentation;
import walkingkooka.text.LineEnding;

import java.math.MathContext;
import java.text.DateFormatSymbols;
import java.time.LocalDateTime;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class BasicLocaleConverterContextTest implements LocaleConverterContextTesting<BasicLocaleConverterContext> {

    private final static Locale LOCALE = Locale.ENGLISH;

    private final static ConverterContext CONVERTER_CONTEXT = ConverterContexts.basic(
        false, // canNumbersHaveGroupSeparator
        Converters.EXCEL_1904_DATE_SYSTEM_OFFSET,
        Indentation.SPACES2,
        LineEnding.NL,
        ',', // valueSeparator
        Converters.simple(),
        DateTimeContexts.basic(
            DateTimeSymbols.fromDateFormatSymbols(
                new DateFormatSymbols(LOCALE)
            ),
            LOCALE,
            1950,
            50,
            LocalDateTime::now
        ),
        DecimalNumberContexts.american(MathContext.DECIMAL32)
    );

    private final static LocaleContext LOCALE_CONTEXT = LocaleContexts.jre(LOCALE);

    @Test
    public void testWithNullConverterContextFails() {
        assertThrows(
            NullPointerException.class,
            () -> BasicLocaleConverterContext.with(
                null,
                LOCALE_CONTEXT
            )
        );
    }

    @Test
    public void testWithNullLocaleContextFails() {
        assertThrows(
            NullPointerException.class,
            () -> BasicLocaleConverterContext.with(
                CONVERTER_CONTEXT,
                null
            )
        );
    }

    @Override
    public BasicLocaleConverterContext createContext() {
        return BasicLocaleConverterContext.with(
            CONVERTER_CONTEXT,
            LOCALE_CONTEXT
        );
    }

    @Override
    public MathContext mathContext() {
        return CONVERTER_CONTEXT.mathContext();
    }

    @Override
    public String currencySymbol() {
        return CONVERTER_CONTEXT.currencySymbol();
    }

    @Override
    public int decimalNumberDigitCount() {
        return CONVERTER_CONTEXT.decimalNumberDigitCount();
    }

    @Override
    public char decimalSeparator() {
        return CONVERTER_CONTEXT.decimalSeparator();
    }

    @Override
    public String exponentSymbol() {
        return CONVERTER_CONTEXT.exponentSymbol();
    }

    @Override
    public char groupSeparator() {
        return CONVERTER_CONTEXT.groupSeparator();
    }

    @Override
    public String infinitySymbol() {
        return CONVERTER_CONTEXT.infinitySymbol();
    }

    @Override
    public char monetaryDecimalSeparator() {
        return CONVERTER_CONTEXT.monetaryDecimalSeparator();
    }

    @Override
    public String nanSymbol() {
        return CONVERTER_CONTEXT.nanSymbol();
    }

    @Override
    public char percentSymbol() {
        return CONVERTER_CONTEXT.percentSymbol();
    }

    @Override
    public char permillSymbol() {
        return CONVERTER_CONTEXT.permillSymbol();
    }

    @Override
    public char negativeSign() {
        return CONVERTER_CONTEXT.negativeSign();
    }

    @Override
    public char positiveSign() {
        return CONVERTER_CONTEXT.positiveSign();
    }

    @Override
    public char zeroDigit() {
        return CONVERTER_CONTEXT.zeroDigit();
    }

    // class............................................................................................................

    @Override
    public Class<BasicLocaleConverterContext> type() {
        return BasicLocaleConverterContext.class;
    }
}
