package com.hc.converter.conversion;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Convert utility class
 * @author hc
 *
 */
public final class Convert {
	public static final int SERIALIZE_TYPE_JSON = 1;
	public static final int SERIALIZE_TYPE_XML = 2;
	
	private Convert() {
	}
	
	private static final HashMap<Class<?>, TypeConverter> converters;
	private static final HashSet<String> primitiveNumberTypes;
	private static final Object convertersLock = new Object();
	private static final Log log = LogFactory.getLog(Convert.class);
	private static final Gson gson = new GsonBuilder().create();
	
	static {
		converters = new HashMap<Class<?>, TypeConverter>();
		
		converters.put(Boolean.class, new BooleanConverter());
		converters.put(boolean.class, new BooleanConverter());
		converters.put(String.class, new StringConverter());
		converters.put(Character.class, new CharacterConverter());
		converters.put(char.class, new CharacterConverter());
		converters.put(Byte.class, new ByteConverter());
		converters.put(byte.class, new ByteConverter());
		converters.put(Short.class, new ShortConverter());
		converters.put(short.class, new ShortConverter());
		converters.put(Integer.class, new IntegerConverter());
		converters.put(int.class, new IntegerConverter());
		converters.put(Long.class, new LongConverter());
		converters.put(long.class, new LongConverter());
		converters.put(Float.class, new FloatConverter());
		converters.put(float.class, new FloatConverter());
		converters.put(Double.class, new DoubleConverter());
		converters.put(double.class, new DoubleConverter());
		converters.put(BigInteger.class, new BigIntegerConverter());
		converters.put(BigDecimal.class, new BigDecimalConverter());
		converters.put(Date.class, new DateConverter());
		converters.put(Class.class, new ClassConverter());

		primitiveNumberTypes = new HashSet<String>();
		primitiveNumberTypes.add("short");
		primitiveNumberTypes.add("int");
		primitiveNumberTypes.add("long");
		primitiveNumberTypes.add("float");
		primitiveNumberTypes.add("double");
	}

	/**
	 * Convert value to specified type
	 */
	@SuppressWarnings("unchecked")
	public static <T> T convertTo(Object value, Class<T> toType) {
		return convertTo(value, toType, null);
	}

	/**
	 * Convert value to specified type
	 * 
	 * will find if there is registered converter and use it for type conversion
	 * 
	 * if there is no converter, then will try cast, if fail and the value is String 
	 * type, then try JSON, fail again, an exception will be thrown
	 * 
	 * Although this conversion method supports JSON conversion
	 * but still DO NOT use for JSON conversion at performance sensitive scenario
	 * 
	 * @param value value 
	 * @param toType to type
	 * @param format the data format, such as number format, date format
	 * @return converted value
	 */
	@SuppressWarnings("unchecked")
	public static <T> T convertTo(Object value, Class<T> toType, String format) {
		if(toType==null)
			throw new IllegalArgumentException("toType must be non-null value.");

		if(converters.containsKey(toType)) {
			TypeConverter converter = converters.get(toType);

			if(FormattableConverter.class.isAssignableFrom(converter.getClass()))
				return (T) ((FormattableConverter) converter).convertValue(value, format);
			else
				return (T) converter.convertValue(value);
		}
		else {
			if(value!=null && toType.isAssignableFrom(value.getClass()))
				return (T)value;
			//try json conversion
			if(value!=null && value.getClass().equals(String.class)) {
				try {
					return gson.fromJson((String)value, toType);
				} catch(Exception e) {
					//keep silence
				}
			}

			String info = value==null?"null":value.getClass().getName();

			throw new ConversionException("Not supported conversion from '"+info+"' to "+toType.getClass().getName()+"'.");
		}
	}
	
	/**
	 * register custom converter
	 * 
	 * @param toType to type
	 * @param converter converter
	 */
	public static void registerConverter(Class<?> toType, TypeConverter converter) {
		if(converter==null)
			throw new IllegalArgumentException("converter must be non-null value.");
		if(converters.containsKey(toType)) {
			synchronized (convertersLock) {
				log.warn("replacing converter '"+toType+"' with '"+converter.getClass().getName()+"'.");
				
				converters.remove(toType);
				converters.put(toType, converter);
			}
		}
		else {
			synchronized (convertersLock) {
				if(!converters.containsKey(toType)) {
					converters.put(toType, converter);
				}
			}
			
		}
	}

	/**
	 * check if the type if primitive number type
	 * @param clz class
	 */
	public static boolean isPrimitiveNumberType(Class clz) {
		if(clz == null)
			return false;
		if(!clz.isPrimitive())
			return false;
		return primitiveNumberTypes.contains(clz.getName());
	}

	/**
	 * Convert value to string
	 * @param value value
	 * @return string value
	 */
	public static String toString(Object value) {
		return toString(value, null);
	}
	
	/**
	 * Convert value to string
	 * @param value value
	 * @return string value
	 */
	public static String toString(Object value, String format) {
		return (String) ((FormattableConverter)converters.get(String.class)).convertValue(value, format);
	}
	
	/**
	 * Convert value to short
	 * @param value value
	 * @return short value
	 */
	public static short toShort(Object value) {
		return toShort(value, null);
	}

	/**
	 * Convert value to short
	 * @param value value
	 * @return short value
	 */
	public static short toShort(Object value, String format) {
		return (Short) ((FormattableConverter)converters.get(Short.class)).convertValue(value, format);
	}

	/**
	 * Convert value to boolean
	 * @param value
	 * @return
	 */
	public static boolean toBoolean(Object value) {
		return (Boolean)converters.get(Boolean.class).convertValue(value);
	}
	
	/**
	 * Convert value to byte
	 * @param value
	 * @return
	 */
	public static byte toByte(Object value) {
		return (Byte)converters.get(Byte.class).convertValue(value);
	}
	
	/**
	 * Convert value to integer
	 * @param value
	 * @return
	 */
	public static int toInteger(Object value) {
		return toInteger(value, null);
	}

	/**
	 * Convert value to integer
	 * @param value
	 * @return
	 */
	public static int toInteger(Object value, String format) {
		return (Integer) ((FormattableConverter)converters.get(Integer.class)).convertValue(value, format);
	}

	/**
	 * Convert value to long
	 * @param value
	 * @return
	 */
	public static long toLong(Object value) {
		return toLong(value, null);
	}

	/**
	 * Convert value to long
	 * @param value
	 * @return
	 */
	public static long toLong(Object value, String format) {
		return (Long) ((FormattableConverter)converters.get(Long.class)).convertValue(value, format);
	}
	
	/**
	 * Convert value to BigInteger
	 * @param value
	 * @return
	 */
	public static BigInteger toBigInteger(Object value) {
		return toBigInteger(value, null);
	}

	/**
	 * Convert value to BigInteger
	 * @param value
	 * @return
	 */
	public static BigInteger toBigInteger(Object value, String format) {
		return (BigInteger) ((FormattableConverter)converters.get(BigInteger.class)).convertValue(value, format);
	}
	
	/**
	 * Convert value to float
	 * @param value
	 * @return
	 */
	public static float toFloat(Object value) {
		return toFloat(value, null);
	}

	/**
	 * Convert value to float
	 * @param value
	 * @return
	 */
	public static float toFloat(Object value, String format) {
		return (Float) ((FormattableConverter)converters.get(Float.class)).convertValue(value, format);
	}
	
	/**
	 * Convert value to float
	 * @param value
	 * @return
	 */
	public static double toDouble(Object value) {
		return toDouble(value, null);
	}

	/**
	 * Convert value to float
	 * @param value
	 * @return
	 */
	public static double toDouble(Object value, String format) {
		return (Double) ((FormattableConverter)converters.get(Double.class)).convertValue(value, format);
	}
	
	/**
	 * Convert value to BigDecimal
	 * @param value
	 * @return
	 */
	public static BigDecimal toBigDecimal(Object value) {
		return toBigDecimal(value, null);
	}

	/**
	 * Convert value to BigDecimal
	 * @param value
	 * @return
	 */
	public static BigDecimal toBigDecimal(Object value, String format) {
		return (BigDecimal) ((FormattableConverter)converters.get(BigDecimal.class)).convertValue(value, format);
	}
	
	/**
	 * Convert value to float
	 * @param value
	 * @return
	 */
	public static Date toDate(Object value) {
		return toDate(value, null);
	}

	/**
	 * Convert value to float
	 * @param value
	 * @return
	 */
	public static Date toDate(Object value, String format) {
		return (Date) ((FormattableConverter)converters.get(Date.class)).convertValue(value, format);
	}
	
	/**
	 * serialize object
	 * 
	 * Supports 2 serialize method for now, JSON and XML
	 * 
	 * if serialize type is JSON, then will use GSON to serialize, GSON annotations will work
	 *
	 * if serialize type is XML, then will use JAXB to serialize, please use JAXB related annotation to mark properties
	 * 
	 * @param obj object to serialize
	 * @param serializeType serialize type, either be SERIALIZE_TYPE_JSON or SERIALIZE_TYPE_XML
	 * @return
	 */
	public static String serialize(Object obj, int serializeType) {
		if(serializeType == SERIALIZE_TYPE_JSON)
			return gson.toJson(obj);
		else if(serializeType == SERIALIZE_TYPE_XML) {
			try {
				StringWriter writer = new StringWriter();
				JAXBContext.newInstance(obj.getClass()).createMarshaller().marshal(obj, writer);

				return writer.getBuffer().toString();
			} catch (JAXBException e) {
				log.error(e);
				throw new ConversionException("error when serialize type");
			}
		}
		else
			throw new ConversionException("not supported serialize type");
	}
	
	/**
	 * deserialize object
	 * 
	 * Supports 2 serialize method for now, JSON and XML
	 * 
	 * if serialize type is JSON, then will use GSON to deserialize, GSON annotations will work
	 *
	 * if serialize type is XML, then will use JAXB to serialize, please use JAXB related annotation to mark properties
	 * 
	 * @param value string value
	 * @param serializeType serialize type, either be SERIALIZE_TYPE_JSON or SERIALIZE_TYPE_XML
	 * @param type object type
	 * @return
	 */
	public static <T> T deserialize(String value, int serializeType, Class<T> type) {
		if(serializeType == SERIALIZE_TYPE_JSON)
			return gson.fromJson(value, type);
		else if(serializeType == SERIALIZE_TYPE_XML)
			try {
				return (T) JAXBContext.newInstance(type).createUnmarshaller().unmarshal(new StringReader(value));
			} catch (JAXBException e) {
				log.error(e);
				throw new ConversionException("error when serialize type");
			}
		else
			throw new ConversionException("not supported serialize type");
	}
}