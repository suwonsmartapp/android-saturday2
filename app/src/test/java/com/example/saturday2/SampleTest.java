package com.example.saturday2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by junsuk on 2016. 12. 29..
 */
public class SampleTest {
    @Before
    public void setUp() throws Exception {
        // 준비 : 인스턴스 생성 등
    }

    @After
    public void tearDown() throws Exception {
        // 종료 : 메모리 해제 등
    }

    @Test
    public void isTest() throws Exception {
        Sample sample = new Sample();
        assertEquals(true, sample.isTest());
    }

    @Test
    public void isNotTest() throws Exception {
        Sample sample = new Sample();
        assertEquals(true, sample.isNotTest());
    }
}