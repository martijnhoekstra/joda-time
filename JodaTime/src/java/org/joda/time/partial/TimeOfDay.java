/*
 * Joda Software License, Version 1.0
 *
 *
 * Copyright (c) 2001-2004 Stephen Colebourne.  
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer. 
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:  
 *       "This product includes software developed by the
 *        Joda project (http://www.joda.org/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The name "Joda" must not be used to endorse or promote products
 *    derived from this software without prior written permission. For
 *    written permission, please contact licence@joda.org.
 *
 * 5. Products derived from this software may not be called "Joda",
 *    nor may "Joda" appear in their name, without prior written
 *    permission of the Joda project.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE JODA AUTHORS OR THE PROJECT
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Joda project and was originally 
 * created by Stephen Colebourne <scolebourne@joda.org>. For more
 * information on the Joda project, please see <http://www.joda.org/>.
 */
package org.joda.time.partial;

import java.io.Serializable;
import java.util.Locale;

import org.joda.time.Chronology;
import org.joda.time.DateTimeField;

/**
 * TimeOfDay is an immutable partial instant supporting the hour, minute, second
 * and millisecond fields.
 * <p>
 * Calculations on TimeOfDay are performed using a {@link Chronology}.
 * This chronology is set to be in the UTC time zone for all calculations.
 * <p>
 * Each individual field can be queried in two ways:
 * <ul>
 * <li><code>getHourOfDay()</code>
 * <li><code>hourOfDay().get()</code>
 * </ul>
 * The second technique also provides access to other useful methods on the
 * field:
 * <ul>
 * <li>numeric value
 * <li>text value
 * <li>short text value
 * <li>maximum/minimum values
 * <li>add/subtract
 * <li>set
 * <li>rounding
 * </ul>
 * <p>
 * TimeOfDay is thread-safe and immutable, provided that the Chronology is as well.
 * All standard Chronology classes supplied are thread-safe and immutable.
 *
 * @author Stephen Colebourne
 * @author Brian S O'Neill
 * @since 1.0
 */
public final class TimeOfDay extends AbstractPartialInstant implements PartialInstant, Serializable {

    /** Serialization version */
    private static final long serialVersionUID = 3633353405803318660L;

    /** The index of the hourOfDay field in the field array */
    public static final int HOUR_OF_DAY = 0;
    /** The index of the minuteOfHour field in the field array */
    public static final int MINUTE_OF_HOUR = 1;
    /** The index of the secondOfMinute field in the field array */
    public static final int SECOND_OF_MINUTE = 2;
    /** The index of the millisOfSecond field in the field array */
    public static final int MILLIS_OF_SECOND = 3;

    // Constructors
    //-----------------------------------------------------------------------
    /**
     * Constructs a TimeOfDay with the current time, using ISOChronology in
     * the default zone to extract the fields.
     * <p>
     * The constructor uses the default time zone, resulting in the local time
     * being initialised. Once the constructor is complete, all further calculations
     * are performed without reference to a timezone (by switching to UTC).
     */
    public TimeOfDay() {
        super();
    }

    /**
     * Constructs a TimeOfDay with the current time, using the specified chronology
     * and zone to extract the fields.
     * <p>
     * The constructor uses the time zone of the chronology specified.
     * Once the constructor is complete, all further calculations are performed
     * without reference to a timezone (by switching to UTC).
     *
     * @param chronology  the chronology, null means ISOChronology in the default zone
     */
    public TimeOfDay(Chronology chronology) {
        super(chronology);
    }

    /**
     * Constructs a TimeOfDay extracting the partial fields from the specified
     * milliseconds using the ISOChronology in the default zone.
     * <p>
     * The constructor uses the default time zone, resulting in the local time
     * being initialised. Once the constructor is complete, all further calculations
     * are performed without reference to a timezone (by switching to UTC).
     *
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z
     */
    public TimeOfDay(long instant) {
        super(instant);
    }

    /**
     * Constructs a TimeOfDay extracting the partial fields from the specified
     * milliseconds using the chronology provided.
     * <p>
     * The constructor uses the time zone of the chronology specified.
     * Once the constructor is complete, all further calculations are performed
     * without reference to a timezone (by switching to UTC).
     *
     * @param instant  the milliseconds from 1970-01-01T00:00:00Z
     * @param chronology  the chronology, null means ISOChronology in the default zone
     */
    public TimeOfDay(long instant, Chronology chronology) {
        super(instant, chronology);
    }

    /**
     * Constructs a TimeOfDay from an Object that represents a time.
     * <p>
     * The recognised object types are defined in
     * {@link org.joda.time.convert.ConverterManager ConverterManager} and
     * include ReadableInstant, String, Calendar and Date.
     *
     * @param instant  the datetime object, must not be null
     * @throws IllegalArgumentException if the date is null
     */
    public TimeOfDay(Object instant) {
        super(instant);
    }

    /**
     * Constructs a TimeOfDay from an Object that represents a time, using the
     * specified chronology.
     * <p>
     * The recognised object types are defined in
     * {@link org.joda.time.convert.ConverterManager ConverterManager} and
     * include ReadableInstant, String, Calendar and Date.
     * <p>
     * The constructor uses the time zone of the chronology specified.
     * Once the constructor is complete, all further calculations are performed
     * without reference to a timezone (by switching to UTC).
     *
     * @param instant  the datetime object, must not be null
     * @param chronology  the chronology, null means ISOChronology
     * @throws IllegalArgumentException if the date is null
     */
    public TimeOfDay(Object instant, Chronology chronology) {
        super(instant, chronology);
    }

    /**
     * Constructs a TimeOfDay with specified hour and minute and zero seconds and milliseconds
     * using <code>ISOChronology</code> in the default zone.
     * <p>
     * The constructor uses the no time zone initialising the fields as provided.
     * Once the constructor is complete, all further calculations
     * are performed without reference to a timezone (by switching to UTC).
     *
     * @param hourOfDay  the hour of the day
     * @param minuteOfHour  the minute of the hour
     */
    public TimeOfDay(int hourOfDay, int minuteOfHour) {
        this(hourOfDay, minuteOfHour, 0, 0, null);
    }

    /**
     * Constructs a TimeOfDay with specified hour and minute and zero seconds and milliseconds.
     * <p>
     * The constructor uses the time zone of the chronology specified.
     * Once the constructor is complete, all further calculations are performed
     * without reference to a timezone (by switching to UTC).
     *
     * @param hourOfDay  the hour of the day
     * @param minuteOfHour  the minute of the hour
     * @param chronology  the chronology, null means ISOChronology in the default zone
     */
    public TimeOfDay(int hourOfDay, int minuteOfHour, Chronology chronology) {
        this(hourOfDay, minuteOfHour, 0, 0, chronology);
    }

    /**
     * Constructs a TimeOfDay with specified time field values and zero milliseconds
     * using <code>ISOChronology</code> in the default zone.
     * <p>
     * The constructor uses the no time zone initialising the fields as provided.
     * Once the constructor is complete, all further calculations
     * are performed without reference to a timezone (by switching to UTC).
     *
     * @param hourOfDay  the hour of the day
     * @param minuteOfHour  the minute of the hour
     * @param secondOfMinute  the second of the minute
     */
    public TimeOfDay(int hourOfDay, int minuteOfHour, int secondOfMinute) {
        this(hourOfDay, minuteOfHour, secondOfMinute, 0, null);
    }

    /**
     * Constructs a TimeOfDay with specified time field values and zero milliseconds.
     * <p>
     * The constructor uses the time zone of the chronology specified.
     * Once the constructor is complete, all further calculations are performed
     * without reference to a timezone (by switching to UTC).
     *
     * @param hourOfDay  the hour of the day
     * @param minuteOfHour  the minute of the hour
     * @param secondOfMinute  the second of the minute
     * @param chronology  the chronology, null means ISOChronology in the default zone
     */
    public TimeOfDay(int hourOfDay, int minuteOfHour, int secondOfMinute, Chronology chronology) {
        this(hourOfDay, minuteOfHour, secondOfMinute, 0, chronology);
    }

    /**
     * Constructs a TimeOfDay with specified time field values using
     * <code>ISOChronology</code> in the default zone.
     * <p>
     * The constructor uses the no time zone initialising the fields as provided.
     * Once the constructor is complete, all further calculations
     * are performed without reference to a timezone (by switching to UTC).
     *
     * @param hourOfDay  the hour of the day
     * @param minuteOfHour  the minute of the hour
     * @param secondOfMinute  the second of the minute
     * @param millisOfSecond  the millisecond of the second
     */
    public TimeOfDay(int hourOfDay, int minuteOfHour, int secondOfMinute, int millisOfSecond) {
        this(hourOfDay, minuteOfHour, secondOfMinute, millisOfSecond, null);
    }

    /**
     * Constructs a TimeOfDay with specified time field values and chronology.
     * <p>
     * The constructor uses the time zone of the chronology specified.
     * Once the constructor is complete, all further calculations are performed
     * without reference to a timezone (by switching to UTC).
     *
     * @param hourOfDay  the hour of the day
     * @param minuteOfHour  the minute of the hour
     * @param secondOfMinute  the second of the minute
     * @param millisOfSecond  the millisecond of the second
     * @param chronology  the chronology, null means ISOChronology in the default zone
     */
    public TimeOfDay(int hourOfDay, int minuteOfHour,
            int secondOfMinute, int millisOfSecond, Chronology chronology) {
        super(new int[] {hourOfDay, minuteOfHour, secondOfMinute, millisOfSecond}, chronology);
    }

    /**
     * Constructs a TimeOfDay with specified fields, values and chronology.
     *
     * @param partial  the partial to base this new instance on
     * @param values  the new set of values
     */
    TimeOfDay(TimeOfDay partial, int[] values) {
        super();
        iChronology = partial.iChronology;
        iValues = values;
    }

    //-----------------------------------------------------------------------
    /**
     * Initialize the array of fields.
     * 
     * @param chrono  the chronology to use
     */
    protected DateTimeField[] initFields(Chronology chrono) {
        return new DateTimeField[] {
            chrono.hourOfDay(),
            chrono.minuteOfHour(),
            chrono.secondOfMinute(),
            chrono.millisOfSecond(),
        };
    }

    /**
     * Initialize the array of values.
     * 
     * @param instant  the instant to use
     * @param chrono  the chronology to use
     */
    protected int[] initValues(long instant, Chronology chrono) {
        return new int[] {
            chrono.hourOfDay().get(instant),
            chrono.minuteOfHour().get(instant),
            chrono.secondOfMinute().get(instant),
            chrono.millisOfSecond().get(instant),
        };
    }

    //-----------------------------------------------------------------------
    /**
     * Get the hour of day (0-23) field value.
     *
     * @return the hour of day
     */
    public int getHourOfDay() {
        return getValue(HOUR_OF_DAY);
    }

    /**
     * Get the minute of hour field value.
     *
     * @return the minute of hour
     */
    public int getMinuteOfHour() {
        return getValue(MINUTE_OF_HOUR);
    }

    /**
     * Get the second of minute field value.
     *
     * @return the second of minute
     */
    public int getSecondOfMinute() {
        return getValue(SECOND_OF_MINUTE);
    }

    /**
     * Get the millis of second field value.
     *
     * @return the millis of second
     */
    public int getMillisOfSecond() {
        return getValue(MILLIS_OF_SECOND);
    }

    //-----------------------------------------------------------------------
    /**
     * Get the hour of day (0-23) field property
     * 
     * @return the hour of day property
     */
    public Property hourOfDay() {
        return new Property(this, HOUR_OF_DAY);
    }

    /**
     * Get the minute of hour field property
     * 
     * @return the minute of hour property
     */
    public Property minuteOfHour() {
        return new Property(this, MINUTE_OF_HOUR);
    }

    /**
     * Get the second of minute field property
     * 
     * @return the second of minute property
     */
    public Property secondOfMinute() {
        return new Property(this, SECOND_OF_MINUTE);
    }

    /**
     * Get the millis of second property
     * 
     * @return the millis of second property
     */
    public Property millisOfSecond() {
        return new Property(this, MILLIS_OF_SECOND);
    }

    //-----------------------------------------------------------------------
    /**
     * The property class for TimeOfDay.
     */
    public static class Property extends AbstractPartialFieldProperty {

        /** The instant */
        private final TimeOfDay iInstant;
        /** The field index */
        private final int iFieldIndex;

        /**
         * Constructs a property.
         * 
         * @param instant  the partial instant
         * @param field  the field
         * @param fieldIndex  the index in the instant
         */
        Property(TimeOfDay instant, int fieldIndex) {
            super();
            iInstant = instant;
            iFieldIndex = fieldIndex;
        }

        /**
         * Gets the field that this property uses.
         * 
         * @return the field
         */
        public DateTimeField getField() {
            return iInstant.getField(iFieldIndex);
        }

        /**
         * Gets the instant that this property belongs to.
         * 
         * @return the partial instant
         */
        public PartialInstant getPartialInstant() {
            return iInstant;
        }

        /**
         * Gets the instant that this property belongs to.
         * 
         * @return the partial instant
         */
        public TimeOfDay getTimeOfDay() {
            return iInstant;
        }

        /**
         * Gets the value of the field that the partial instant is set to.
         * 
         * @return the field value
         */
        public int get() {
            return iInstant.getValue(iFieldIndex);
        }

        //-----------------------------------------------------------------------
// TODO
//        /**
//         * Adds to this field in a copy of this TimeOfDay.
//         * <p>
//         * The TimeOnly attached to this property is unchanged by this call.
//         * 
//         * @param value  the value to add to the field in the copy
//         * @return a copy of the TimeOnly with the field value changed
//         * @throws IllegalArgumentException if the value isn't valid
//         */
//        public TimeOfDay addToCopy(int value) {
//            int[] newValues = getField().add(getInstant(), value);
//            return new TimeOfDay(getInstant(), newValues);
//        }
//
//        /**
//         * Adds to this field, possibly wrapped, in a copy of this TimeOfDay.
//         * A wrapped operation only changes this field.
//         * Thus 12:59:00 addWrapped one minute goes to 12:00:00.
//         * <p>
//         * The TimeOfDay attached to this property is unchanged by this call.
//         * 
//         * @param value  the value to add to the field in the copy
//         * @return a copy of the TimeOfDay with the field value changed
//         * @throws IllegalArgumentException if the value isn't valid
//         */
//        public TimeOfDay addWrappedToCopy(int value) {
//            int[] newValues = getField().addWrapped(getInstant(), value);
//            return new TimeOfDay(getInstant(), newValues);
//        }
//
        //-----------------------------------------------------------------------
        /**
         * Sets this field in a copy of the TimeOfDay.
         * <p>
         * The TimeOfDay attached to this property is unchanged by this call.
         * 
         * @param value  the value to set the field in the copy to
         * @return a copy of the TimeOfDay with the field value changed
         * @throws IllegalArgumentException if the value isn't valid
         */
        public TimeOfDay setCopy(int value) {
            int[] newValues = iInstant.getValues();
            getField().set(iInstant, iFieldIndex, newValues, value);
            return new TimeOfDay(iInstant, newValues);
        }

        /**
         * Sets this field in a copy of the TimeOfDay to a parsed text value.
         * <p>
         * The TimeOfDay attached to this property is unchanged by this call.
         * 
         * @param text  the text value to set
         * @param locale  optional locale to use for selecting a text symbol
         * @return a copy of the TimeOfDay with the field value changed
         * @throws IllegalArgumentException if the text value isn't valid
         */
        public TimeOfDay setCopy(String text, Locale locale) {
            int[] newValues = iInstant.getValues();
            getField().set(iInstant, iFieldIndex, newValues, text, locale);
            return new TimeOfDay(iInstant, newValues);
        }

        /**
         * Sets this field in a copy of the TimeOfDay to a parsed text value.
         * <p>
         * The TimeOfDay attached to this property is unchanged by this call.
         * 
         * @param text  the text value to set
         * @return a copy of the TimeOfDay with the field value changed
         * @throws IllegalArgumentException if the text value isn't valid
         */
        public TimeOfDay setCopy(String text) {
            return setCopy(text, null);
        }

// TODO
//        //-----------------------------------------------------------------------
//        /**
//         * Rounds to the lowest whole unit of this field on a copy of this TimeOfDay.
//         *
//         * @return a copy of the TimeOfDay with the field value changed
//         */
//        public TimeOfDay roundFloorCopy() {
//            TimeOfDay instant = iInstant;
//            return (TimeOfDay) instant.withMillis(iField.roundFloor(instant.getMillis()));
//        }
//
//        /**
//         * Rounds to the highest whole unit of this field on a copy of this TimeOfDay.
//         *
//         * @return a copy of the TimeOfDay with the field value changed
//         */
//        public TimeOfDay roundCeilingCopy() {
//            TimeOfDay instant = iInstant;
//            return (TimeOfDay) instant.withMillis(iField.roundCeiling(instant.getMillis()));
//        }
//
//        /**
//         * Rounds to the nearest whole unit of this field on a copy of this TimeOfDay,
//         * favoring the floor if halfway.
//         *
//         * @return a copy of the TimeOfDay with the field value changed
//         */
//        public TimeOfDay roundHalfFloorCopy() {
//            TimeOfDay instant = iInstant;
//            return (TimeOfDay) instant.withMillis(iField.roundHalfFloor(instant.getMillis()));
//        }
//
//        /**
//         * Rounds to the nearest whole unit of this field on a copy of this TimeOfDay,
//         * favoring the ceiling if halfway.
//         *
//         * @return a copy of the TimeOfDay with the field value changed
//         */
//        public TimeOfDay roundHalfCeilingCopy() {
//            TimeOfDay instant = iInstant;
//            return (TimeOfDay) instant.withMillis(iField.roundHalfCeiling(instant.getMillis()));
//        }
//
//        /**
//         * Rounds to the nearest whole unit of this field on a copy of this TimeOfDay.
//         * If halfway, the ceiling is favored over the floor only if it makes this field's value even.
//         *
//         * @return a copy of the TimeOfDay with the field value changed
//         */
//        public TimeOfDay roundHalfEvenCopy() {
//            TimeOfDay instant = iInstant;
//            return (TimeOfDay) instant.withMillis(iField.roundHalfEven(instant.getMillis()));
//        }
    }

}
