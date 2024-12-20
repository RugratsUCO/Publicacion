package com.publicacion.publicacion.utils;

public final class UtilNumber {

    private static final UtilNumber INSTANCE = new UtilNumber();
    public static final Number NUMBER_DEFAULT = 0;

    private UtilNumber() {
        super();
    }

    public static UtilNumber getUtilInt(){
        return INSTANCE;
    }

    public static Number getDefaultValue() {
        return NUMBER_DEFAULT;
    }
    public static Integer getIntegerDefaultValue() {
        return getDefaultValue().intValue();
    }
    public static Double getRealDefaultValue() {
        return getDefaultValue().doubleValue();
    }

    public static Double getDefaultReal(Double number){
        return isNull(number) ? getRealDefaultValue() : number;
    }

    public static Integer getDefaultInt(Integer number){
        return isNull(number) ? getIntegerDefaultValue() : number;
    }

    public static Number getDefault(final Number number, final Number default_value){
        Number result = number;
        if (isNull(number)){
            result = isNull(default_value)? NUMBER_DEFAULT: default_value;
        }
        return result;
    }

    public static boolean isNull(final Number number){
        return UtilObject.isNull(number);
    }

}
