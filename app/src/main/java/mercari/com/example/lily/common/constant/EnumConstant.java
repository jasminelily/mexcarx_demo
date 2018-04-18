package mercari.com.example.lily.common.constant;

public class EnumConstant {

    public enum Request_Status {
        Success(0), Failure(-10), Error_Other(-1),Error_Net_Connect(-2), Unknow(1000);

        private final int value;

        private Request_Status(final int newValue) {
            value = newValue;
        }

        public int getValue() {
            return value;
        }
    }

}
