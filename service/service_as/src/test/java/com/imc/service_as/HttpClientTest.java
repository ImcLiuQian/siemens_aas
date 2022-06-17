package com.imc.service_as;


import com.imc.siemens_aas.aasenv.submodel.Submodel;
import com.imc.siemens_aas.httpclient.HttpClientHelper;
import org.apache.http.HttpException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HttpClientTest {

    List<Integer> ints;
    Integer a;
    Integer b;
    Integer c;
    {
        ints = new ArrayList<>(3);
    }

    @Test
    void test1() {
        String url ="http://localhost:8001/aas/AS/test";
        String s = HttpClientHelper.doGet(url);
        System.out.println("s = " + s);
    }

    @Test
    void testList() {
        ints.clear();
        a = 1;
        b = 2;
        c = 3;
        ints.addAll(Arrays.asList(a, b, c));
        System.out.println("ints = " + ints);
        a = 4;
        b = 5;
        c = 6;
        ints.clear();
        ints.addAll(Arrays.asList(a, b, c));
        System.out.println("ints = " + ints);
    }
}
