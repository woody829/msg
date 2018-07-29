package base.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

/**
 * Created by woody on 2017/3/29.
 */
public class BaseDTO implements Serializable {
    public String toString( ) {
        return ReflectionToStringBuilder.toString( this );
    }
}
