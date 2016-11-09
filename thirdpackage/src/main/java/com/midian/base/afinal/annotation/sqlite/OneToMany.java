package com.midian.base.afinal.annotation.sqlite;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME) 
public @interface OneToMany {
	
	 public String manyColumn();

     public String orderColumn() default "";

     public boolean orderDesc() default false;
}
