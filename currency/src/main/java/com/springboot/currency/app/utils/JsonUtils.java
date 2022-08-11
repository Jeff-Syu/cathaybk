package com.springboot.currency.app.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.core.type.TypeReference;


public class JsonUtils implements Serializable {

	private static final long serialVersionUID = 1L;

	private JsonUtils() {

	}

	/**
	 * Json字串轉換為物件
	 * <p>
	 * List<Test> testList = getObject(s, new TypeReference<List<Test>>() {
	 * </p>
	 * 
	 * @param <POJO>
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <POJO> POJO getObject(String json, final Class<POJO> clazz) {
		if (json == null || json == "") {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			mapper.setVisibility(PropertyAccessor.ALL, Visibility.NONE);
			mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
			
			return mapper.readValue(json, clazz);
		}
		catch (Exception e) {
			System.out.println("JsonReader Errorm" + e);
			return null;
		}
	}

	/**
	 * Json字串轉換為List物件
	 * <p>
	 * List<Test> testList = getObject(s, new TypeReference<List<Test>>() {
	 * </p>
	 * 
	 * @param <POJO>
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <POJO> POJO getObject(String json, final TypeReference<POJO> type) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			mapper.setVisibility(PropertyAccessor.ALL, Visibility.NONE);
			mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
			
			return mapper.readValue(json, type);
		}
		catch (Exception e) {
			System.out.println("JsonReader Errorm" + e);
			return null;
		}
	}

	/**
	 * 物件轉換為Json字串
	 * 
	 * @param <POJO>
	 * @param object
	 * @return
	 */
	public static <POJO> String getJson(POJO object) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.setVisibility(PropertyAccessor.ALL, Visibility.NONE);
			mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
					
			mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

			return mapper.writeValueAsString(object);
		}
		catch (Exception e) {

			System.out.println(e);
			return null;
		}
	}

	/**
	 * Json字串轉換為Map物件
	 * 
	 * @param json
	 * @return
	 */
	public static Map<String, String> getMap(String json) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			return mapper.readValue(json, new TypeReference<Hashtable<String, String>>() {});
		}
		catch (Exception e) {
			System.out.println("JsonReader Errorm" + e);
			return null;
		}
	}

	/**
	 * Json字串轉換為Map物件
	 * 
	 * @param json
	 * @return
	 */
	public static Map<String, String[]> getMapValueArray(String json) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			return mapper.readValue(json, new TypeReference<Hashtable<String, String[]>>() {});
		}
		catch (Exception e) {
			System.out.println("JsonReader Errorm" + e);
			return null;
		}
	}

	/**
	 * Json字串轉換為List物件
	 * 
	 * @param json
	 * @return
	 */
	public static List<String> getList(String json) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			return mapper.readValue(json, new TypeReference<ArrayList<String>>() {});
		}
		catch (Exception e) {
			System.out.println("JsonReader Errorm" + e);
			return null;
		}
	}

	/**
	 * Json字串轉換為List<Long>物件
	 * 
	 * @param json
	 * @return
	 */
	public static List<Long> getLongList(String json) {
		if (json == null || json == "") {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			return mapper.readValue(json, new TypeReference<ArrayList<Long>>() {});
		}
		catch (Exception e) {
			System.out.println("JsonReader Errorm" + e);
			return null;
		}
	}

}
