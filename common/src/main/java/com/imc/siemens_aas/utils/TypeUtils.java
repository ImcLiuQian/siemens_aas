package com.imc.siemens_aas.utils;


public class TypeUtils {
    /**
     * 判断数据是否为基本数据类型或包装类，或者String
     *
     * @param clazz
     * @return
     */
    public static boolean isPrimitive(Class<?> clazz) {
        try {
            if (clazz.isPrimitive() || (clazz == String.class)) {
                return true;
            }
            return ((Class<?>) clazz.getField("TYPE").get(null)).isPrimitive();
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            return false;
        }
    }
}
