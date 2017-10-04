/*package student.json.mapper;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;

public class Mapper {
    private final Mappings mappings;
    private final Locale locale;
    private final String dateFormat;
    private final String currency;
    private final ValidationResult vr;
    private final DynaActionForm form;
    private final HttpServletRequest request;
    private final Map<String, Object> map;
    private final Object businessObject;

    public Mapper(Mappings mappings, Locale locale, String dateFormat, String currency, DynaActionForm form,
                  HttpServletRequest request, Map<String, ?> map, Object businessObject, ValidationResult vr) {
        this.mappings = mappings;
        this.locale = locale;
        this.dateFormat = dateFormat;
        this.currency = currency;
        this.form = form;
        this.request = request;
        @SuppressWarnings("unchecked")
        Map<String, Object> tMap = (Map<String, Object>) map;
        this.map = tMap;
        this.vr = vr;
        this.businessObject = businessObject;
    }

    public Boolean toBoolean(Mapping<Boolean> mapping, String text) {
        if ("".equals(text)) {
            return addMessage(mapping, MessageName.REQUIRED, Boolean.FALSE);
        }
        return parseBoolean(text);
    }

    public static Boolean parseBoolean(String text) {
        return Boolean.valueOf("on".equalsIgnoreCase(text) || "true".equalsIgnoreCase(text));
    }

    public String toText(Mapping<String> mapping, String text) {
        if ("".equals(text)) {
            return addMessage(mapping, MessageName.REQUIRED, "");
        }
        return text;
    }

    public Integer toInteger(Mapping<Integer> mapping, String numberText) {
        if ("".equals(numberText)) {
            return addMessage(mapping, MessageName.REQUIRED, 0);
        }
        try {
            return Integer.parseInt(numberText);
        } catch (NumberFormatException e) {
            return addMessage(mapping, MessageName.INVALID, 0);
        }
    }

    public Timestamp toDate(Mapping<Timestamp> mapping, String dateText) {
        if ("".equals(dateText)) {
            return addMessage(mapping, MessageName.REQUIRED, null);
        }
        try {
            return DateUtil.parseDate(dateText, dateFormat);
        } catch (IllegalArgumentException e) {
            return addMessage(mapping, MessageName.INVALID, null);
        }
    }
    //CR : 302118 : Added an method to parse java.sql.Date. 
    public Date toPlainDate(Mapping<Date> mapping, String dateText) {
        if ("".equals(dateText)) {
            return addMessage(mapping, MessageName.REQUIRED, null);
        }
        try {
            return DateUtil.parseDateWithoutTimestamp(dateText, dateFormat);
        } catch (IllegalArgumentException e) {
            return addMessage(mapping, MessageName.INVALID, null);
        }
    }

    public Money toMoney(Mapping<Money> mapping, String numberText) {
        if ("".equals(numberText)) {
            return addMessage(mapping, MessageName.REQUIRED, new Money("0.0", currency));
        }
        try {
            return new Money(NumberUtil.parseNumber(locale, numberText), currency);
        } catch (Exception e) {
            return addMessage(mapping, MessageName.INVALID, new Money("0.0", currency));
        }
    }

    public BigDecimal toNumber(Mapping<BigDecimal> mapping, String numberText) {
        if ("".equals(numberText)) {
            return addMessage(mapping, MessageName.REQUIRED, BigDecimal.ZERO);
        }
        try {
            return NumberUtil.parseNumber(locale, numberText, mapping.scale);
        } catch (NumberFormatException e) {
            return addMessage(mapping, MessageName.INVALID, BigDecimal.ZERO);
        }
    }

    protected String getFormValue(String formPropertyName, MappingSource source) {
        if (source == MappingSource.FORM) {
            return toString(form.get(formPropertyName));
        } else if (source == MappingSource.MAP) {
            return toString(map.get(formPropertyName));
        }
        return request.getParameter(formPropertyName);
    }

    public String formatDate(Mapping<Timestamp> mapping, Timestamp date) {
        return DateUtil.toDate(date, dateFormat);
    }

    public String formatSQLDate(Mapping<Date> mapping, Date date) {
        return DateUtil.toDate(date, dateFormat);
    }

    public String formatMoney(Mapping<Money> mapping, Money money) {
        return NumberUtil.formatNumber(locale, money.getAmount().toString(), mapping.scale);
    }

    public String formatNumber(Mapping<BigDecimal> mapping, BigDecimal number) {
        return NumberUtil.formatNumber(locale, number.toString(), mapping.scale);
    }

    public String formatAmount(Mapping<BigDecimal> mapping, BigDecimal number) {
        return NumberUtil.formatAmount(locale, number);
    }

    protected void setFormValue(String formPropertyName, Object value, MappingSource source) {
        if (source == MappingSource.FORM) {
            form.set(formPropertyName, value);
            return;
        } else if (source == MappingSource.MAP) {
            map.put(formPropertyName, value);
            return;
        }
        request.setAttribute(formPropertyName, value);
    }

    private static String toString(Object value) {
        if (value == null) {
            return null;
        }
        if (String.class.isInstance(value)) {
            return (String) value;
        }
        return value.toString();
    }

    private <T> T addMessage(Mapping<T> mapping, MessageName validation, T defaultValue) {
        MappingMessage message = mapping.messages.get(validation.name());
        if (message != null) {
            addMessage(message);
        }
        return defaultValue;
    }

    private void addMessage(MappingMessage mappingMessage) {
        vr.addError(mappingMessage.section, new ActionError(mappingMessage.key, mappingMessage.args));
    }

    private static final PropertyUtilsBean propertyUtils = new PropertyUtilsBean();

    public void mapToBo() {
        for (Mapping<?> mapping : mappings.values()) {
            if (mapping.direction == MappingDirection.TO_FORM_ONLY) {
                continue;
            }

            String textValue = getFormValue(mapping.formPropertyName, mapping.source);
            //textValue = textValue != null ? textValue.trim() : textValue; // CR 485667 - No guarantee that textValue won't be null
            //no need to do trim() here for CR 485667 and CR 483871 as it's checked in the next step by blankunull function

            if (mapping.trim) {
                textValue = StringUtil.blanknull(textValue);
            }
            if (mapping.skipBlanks && StringUtil.isEmpty(textValue)) {
                continue;
            }

            //CR 689002 - The following piece of code is added to remove the non-breaking white space characters.
            if(StringUtil.isEmpty(textValue) == false) {
                textValue = textValue.replaceAll("\u00A0", "");
            }

            Object value = parseValue(mapping, textValue);
            try {
                propertyUtils.setProperty(businessObject, mapping.boPropertyName, value);
            } catch (RuntimeException e) {
                throw e;
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void mapToForm() {
        for (Mapping<?> mapping : mappings.values()) {
            if (mapping.direction == MappingDirection.TO_BO_ONLY) {
                continue;
            }
            setFormValue(mapping.formPropertyName, formatValue(mapping, getBoPropertyValue(mapping.boPropertyName)),
                    mapping.source);
        }
    }

    private Object getBoPropertyValue(String name) {
        Object value = null;
        try {
            value = propertyUtils.getProperty(businessObject, name);
        } catch (NestedNullException e) {
            value = null;
        } catch (RuntimeException e) {
            throw e;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return value;
    }

    @SuppressWarnings("unchecked")
    private Object parseValue(Mapping<?> mapping, String textValue) {
        Class<?> type = mapping.type;
        Object value = null;
        if (Money.class.isAssignableFrom(type)) {
            value = toMoney((Mapping<Money>) mapping, textValue);
        } else if (BigDecimal.class.isAssignableFrom(type)) {
            value = toNumber((Mapping<BigDecimal>) mapping, textValue);
        } else if (Timestamp.class.isAssignableFrom(type)) {
            value = toDate((Mapping<Timestamp>) mapping, textValue);
        } else if (Time.class.isAssignableFrom(type)) {
            value = toTime((Mapping<Time>) mapping, textValue);
        } else if(Date.class.isAssignableFrom(type)){
            value = toPlainDate((Mapping<Date>) mapping, textValue);
        }else if (java.util.Date.class.isAssignableFrom(type)) {
            value = toDate((Mapping<Timestamp>) mapping, textValue);
        } else if (Integer.class.isAssignableFrom(type) || int.class.isAssignableFrom(type)) {
            value = toInteger((Mapping<Integer>) mapping, textValue);
        } else if (String.class.isAssignableFrom(type)) {
            value = toText((Mapping<String>) mapping, textValue);
        } else if (Boolean.class.isAssignableFrom(type) || boolean.class.isAssignableFrom(type)) {
            value = toBoolean((Mapping<Boolean>) mapping, textValue);
        } else if (SingleSelectList.class.isAssignableFrom(type)) {
            value = toSingleSelectList((Mapping<SingleSelectList<?>>) mapping, textValue);
        } else if (Enum.class.isAssignableFrom(type)) {
            value = toEnum(mapping, textValue, type);
        }else {
            throw new MappingException(//
                    "Mapping to " + mapping.boPropertyName + "(" + type + ") is not supported.");
        }
        return value;
    }

    private Time toTime(Mapping<Time> mapping, String textValue) {
        try {
            return new Time(new SimpleDateFormat("HH:mm").parse(textValue).getTime());
        } catch (ParseException e) {
            return addMessage(mapping, MessageName.INVALID, null);
        }
    }

    private Object toEnum(Mapping<?> mapping, String textValue, Class<?> type) {
        Enum<?>[] enumConstants = (Enum<?>[]) type.getEnumConstants();
        for (Enum<?> e : enumConstants) {
            if (e.name().equals(textValue)) {
                return e;
            }
        }
        return addMessage(mapping, MessageName.INVALID, null);
    }

    private SingleSelectList<?> toSingleSelectList(Mapping<SingleSelectList<?>> mapping, String textValue) {
        @SuppressWarnings("unchecked")
        SingleSelectList<Object> value = (SingleSelectList<Object>) getBoPropertyValue(mapping.boPropertyName);
        if (value == null) {
            throw new MappingException("Cannot determine key data-type for DataList : Found null DataList value in "
                    + businessObject.getClass().getName() + "." + mapping.boPropertyName);
        }
        Class<Object> keyClass = (Class<Object>) value.getKeyClass();
        Mapping<?> tMapping = createMappingWithType(mapping, keyClass);
        if ("".equals(textValue)) {
            @SuppressWarnings("unchecked")
            Mapping<String> sMapping = (Mapping<String>) createMappingWithType(mapping, String.class);
            addMessage(sMapping, MessageName.REQUIRED, "");
            return value;
        }
        Object key = parseValue(tMapping, textValue);
        try {
            value.setSelectedKey(key);
        } catch (DataListException e) {
            addMessage(mapping, MessageName.INVALID, null);
        }
        return value;
    }

    private static Mapping<?> createMappingWithType(Mapping<SingleSelectList<?>> mapping, Class<?> type) {
        @SuppressWarnings("unchecked")
        Class<Object> tClass = (Class<Object>) type;
        Mapping<?> tMapping = new Mapping<Object>(//
                mapping.source, //
                mapping.formPropertyName, //
                mapping.boPropertyName, //
                tClass, //
                mapping.scale, //
                mapping.trim);
        tMapping.direction = mapping.direction;
        tMapping.messages.putAll(mapping.messages);
        return tMapping;
    }

    @SuppressWarnings("unchecked")
    private String formatValue(Mapping<?> mapping, Object value) {
        if (value == null) {
            return "";
        }
        Class<?> type = mapping.type;
        if (Money.class.isAssignableFrom(type)) {
            return formatMoney((Mapping<Money>) mapping, (Money) value);
        } else if (BigDecimal.class.isAssignableFrom(type)) {
            if (mapping.useAmountFormatter) {
                return formatAmount((Mapping<BigDecimal>) mapping, (BigDecimal) value);
            }
            return formatNumber((Mapping<BigDecimal>) mapping, (BigDecimal) value);
        } else if (Timestamp.class.isAssignableFrom(type)) {
            return formatDate((Mapping<Timestamp>) mapping, (Timestamp) value);
        } else if (Date.class.isAssignableFrom(type)) {
            return formatSQLDate((Mapping<Date>) mapping, (Date) value);
        } else if (Integer.class.isAssignableFrom(type) || int.class.isAssignableFrom(type)) {
            return ((Integer) value).toString();
        } else if (String.class.isAssignableFrom(type)) {
            return StringUtil.blanknull((String) value);
        } else if (Boolean.class.isAssignableFrom(type) || boolean.class.isAssignableFrom(type)) {
            return ((Boolean) value).toString();
        } else if (SingleSelectList.class.isAssignableFrom(type)) {
            Object key = ((SingleSelectList<Object>) value).getSelectedKey();
            if (key == null || String.class.isInstance(key)) {
                return (String) key;
            }
            return key.toString();
        } else {
            throw new MappingException(//
                    "Mapping to " + mapping.boPropertyName + "(" + type + ") is not supported.");
        }
    }
}
*/