package cache;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import play.Logger;
import play.cache.Cache;

public class CacheService extends Cache {

	public static final int CACHE_LIFETIME = 3600;

	public enum KeyType {
		DETAIL("detail"), LIST("list");

		private final String name;

		private KeyType(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	public static String createKey(Class<?> className, KeyType type,
			String[] keys) {
		StringBuilder builder = new StringBuilder(className.getSimpleName())
				.append("_").append(type.getName());
		for (String key : keys) {
			builder.append("_").append(key);
		}
		return builder.toString();
	}

	public static Object getCache(String key) {
		return Cache.get(key);
	}

	public static void setCache(String key, Object object) {
		Cache.set(key, object, CACHE_LIFETIME);
	}

	public static Object getObject(Class<?> className, KeyType type,
			String[] keys, String methodName, Class<?>[] param,Object[] arguments) {
		Object result = new Object();
		try {
			String createKey = createKey(className, type, keys);
			result = getCache(createKey);
			if (result == null) {
				Class<?> cls = className;
				Object instance = cls.newInstance();
				Method method = cls.getMethod(methodName,param);
				result = method.invoke(instance, arguments);
				setCache(createKey, result);
			}
		} catch (SecurityException e) {
			Logger.error("[CacheService]", e);
		} catch (IllegalArgumentException e) {
			Logger.error("[CacheService]", e);
		} catch (IllegalAccessException e) {
			Logger.error("[CacheService]", e);
		} catch (InvocationTargetException e) {
			Logger.error("[CacheService]", e);
		} catch (InstantiationException e) {
			Logger.error("[CacheService]", e);
		} catch (NoSuchMethodException e) {
			Logger.error("[CacheService]", e);
		}
		return result;
	}
}
