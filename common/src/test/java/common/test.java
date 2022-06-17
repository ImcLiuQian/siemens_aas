package common;

import com.imc.siemens_aas.aasenv.submodel.Submodel;
import com.imc.siemens_aas.utils.TypeUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

public class test {
    @Test
    void test1() {
        String aaa = "!";
        boolean primitive = TypeUtils.isPrimitive(aaa.getClass());
        System.out.println("primitive = " + primitive);
    }

    @Test
    void test2() throws NoSuchFieldException {//测试字段类型判断
        Class<Submodel> submodelClass = Submodel.class;
        Field semanticId = submodelClass.getDeclaredField("semanticId");
        Class<?> type = semanticId.getType();
        String s = type.toString();
        System.out.println("s = " + s);

        Field keys = type.getDeclaredField("keys");
        String s1 = keys.getType().toString();
        System.out.println("s1 = " + s1);
    }

    @Test
    void test3() throws NoSuchFieldException {//测试字段类型判断
        Object a = 'a';
        Class<?> aClass = a.getClass();
        System.out.println("aClass = " + aClass);
    }
}
