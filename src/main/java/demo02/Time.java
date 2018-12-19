package demo02;

import java.security.PublicKey;
import java.util.Date;

/**
 * @Auther: 0
 * @Date: 2018/12/19 14:56
 * @Description:
 */

public class Time {
    private final long value;

    public Time() {

        this.value =(System.currentTimeMillis() / 1000l);
        System.out.println(value);
    }

    public Time(long value) {
        this.value = value;
    }

    public long value() {
        return value;
    }

    @Override
    public String toString() {
        return new Date((value() * 1000L)).toString();
    }
}
