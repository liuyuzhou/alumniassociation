package com.alumniassociation.common.constant;

public class NumberConstant {

    public enum IntEnum {

        IN1(-1),
        I0(0),
        I1(1),
        I2(2),
        I3(3),
        I4(4),
        I5(5),
        I6(6),
        I7(7),
        I8(8),
        I9(9),
        I10(10),
        I53(53),
        I1000(1000),
        I9999(9999);

        private Integer val;

        private IntEnum(Integer intValue){
            this.val = intValue;
        }

        public Integer getVal() {
            return val;
        }
    }

    public enum StringEnum {

        S0("0"),
        S1("1"),
        S2("2"),
        S3("3"),
        S4("4"),
        S5("5"),
        S6("6"),
        S7("7"),
        S8("8"),
        S9("9");

        private String val;

        private StringEnum(String intValue){
            this.val = intValue;
        }

        public String getVal() {
            return val;
        }
    }


    public enum CharEnum {

        C0('0'),
        C1('1'),
        C2('2'),
        C3('3'),
        C4('4'),
        C5('5'),
        C6('6'),
        C7('7'),
        C8('8'),
        C9('9');

        private Character val;

        private CharEnum(Character intValue){
            this.val = intValue;
        }

        public Character getVal() {
            return val;
        }
    }

    public enum ShortEnum {

        S0("0"),
        S1("1"),
        S2("2"),
        S3("3"),
        S4("4"),
        S5("5"),
        S6("6"),
        S7("7"),
        S8("8"),
        S9("9");

        private Short val;

        private ShortEnum(String shortValue){
            this.val = Short.valueOf(shortValue);
        }

        public Short getVal() {
            return val;
        }
    }

    public enum LongEnum {

        L0("0"),
        L1("1"),
        L2("2"),
        L3("3"),
        L4("4"),
        L5("5"),
        L6("6"),
        L7("7"),
        L8("8"),
        L9("9");

        private Long val;

        private LongEnum(String longValue){
            this.val = Long.valueOf(longValue);
        }

        public Long getVal() {
            return val;
        }
    }





}
