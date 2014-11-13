package com.flyn.test.hastesql.entity;

import com.flyn.hastesql.core.HasteModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by flyn on 2014-11-12.
 */
public class TestMultiModel implements HasteModel
{
    private final int testFinal = 2;
    private static int testStatic = 3;
    private static final int testStaticFinal = 4;
    private String string1;
    private CharSequence string2;
    private char char1;
    private Character char2;
    private int int1;
    private Integer int2;
    private short short1;
    private Short short2;
    private long long1;
    private Long long2;
    private byte[] data1;
    private Byte[] data2;
    private boolean boolean1;
    private Boolean boolean2;
    private double double1;
    private Double double2;
    private float float1;
    private Float float2;
    private Date date1;
    private transient int testTransient;
    private Object testObject;
    private Number testObject2;
    private volatile StringBuffer testObject3;


    public TestMultiModel()
    {
        this.string1 = "aaa";
        this.string2 = "bbb";
        this.char1 = 'a';
        this.char2 = Character.valueOf('b');
        this.int1 = 4;
        this.int2 = 5;
        this.short1 = 1;
        this.short2 = 2;
        this.long1 = 666666776;
        this.long2 = Long.valueOf(777777777);
        this.data1 = new byte[]{'1', '2'};
        this.data2 = new Byte[]{'3', '4'};
        this.boolean1 = true;
        this.boolean2 = false;
        this.double1 = 1.2453;
        this.double2 = 4.5454;
        this.float1 = 12.12F;
        this.float2 = 12.2f;
        this.date1 = new Date();
        this.testTransient = 1;
        this.testObject = new Object();
        this.testObject2 = new BigDecimal(1);
        this.testObject3 = new StringBuffer();
    }

    public String getString1()
    {
        return string1;
    }

    public void setString1(String string1)
    {
        this.string1 = string1;
    }

    public CharSequence getString2()
    {
        return string2;
    }

    public void setString2(CharSequence string2)
    {
        this.string2 = string2;
    }

    public char getChar1()
    {
        return char1;
    }

    public void setChar1(char char1)
    {
        this.char1 = char1;
    }

    public Character getChar2()
    {
        return char2;
    }

    public void setChar2(Character char2)
    {
        this.char2 = char2;
    }

    public int getInt1()
    {
        return int1;
    }

    public void setInt1(int int1)
    {
        this.int1 = int1;
    }

    public Integer getId2()
    {
        return int2;
    }

    public void setId2(Integer id2)
    {
        this.int2 = id2;
    }

    public short getShort1()
    {
        return short1;
    }

    public void setShort1(short short1)
    {
        this.short1 = short1;
    }

    public Short getShort2()
    {
        return short2;
    }

    public void setShort2(Short short2)
    {
        this.short2 = short2;
    }

    public long getLong1()
    {
        return long1;
    }

    public void setLong1(long long1)
    {
        this.long1 = long1;
    }

    public Long getLong2()
    {
        return long2;
    }

    public void setLong2(Long long2)
    {
        this.long2 = long2;
    }

    public byte[] getData1()
    {
        return data1;
    }

    public void setData1(byte[] data1)
    {
        this.data1 = data1;
    }

    public Byte[] getData2()
    {
        return data2;
    }

    public void setData2(Byte[] data2)
    {
        this.data2 = data2;
    }

    public boolean isBoolean1()
    {
        return boolean1;
    }

    public void setBoolean1(boolean boolean1)
    {
        this.boolean1 = boolean1;
    }

    public Boolean getBoolean2()
    {
        return boolean2;
    }

    public void setBoolean2(Boolean boolean2)
    {
        this.boolean2 = boolean2;
    }

    public double getDouble1()
    {
        return double1;
    }

    public void setDouble1(double double1)
    {
        this.double1 = double1;
    }

    public Double getDouble2()
    {
        return double2;
    }

    public void setDouble2(Double double2)
    {
        this.double2 = double2;
    }

    public float getFloat1()
    {
        return float1;
    }

    public void setFloat1(float float1)
    {
        this.float1 = float1;
    }

    public Float getFloat2()
    {
        return float2;
    }

    public void setFloat2(Float float2)
    {
        this.float2 = float2;
    }

    public Date getDate1()
    {
        return date1;
    }

    public void setDate1(Date date1)
    {
        this.date1 = date1;
    }

    public int getTestTransient()
    {
        return testTransient;
    }

    public void setTestTransient(int testTransient)
    {
        this.testTransient = testTransient;
    }

    public Object getTestObject()
    {
        return testObject;
    }

    public void setTestObject(Object testObject)
    {
        this.testObject = testObject;
    }

    public Number getTestObject2()
    {
        return testObject2;
    }

    public void setTestObject2(Number testObject2)
    {
        this.testObject2 = testObject2;
    }

    public StringBuffer getTestObject3()
    {
        return testObject3;
    }

    public void setTestObject3(StringBuffer testObject3)
    {
        this.testObject3 = testObject3;
    }
}
