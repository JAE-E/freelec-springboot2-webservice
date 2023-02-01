package com.jojoldu.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 어노테이션이 생성될수있는 위치를 지정합니다.
// 파라미터로 지정했으니, 메소드의 파라미터로 선.언.된 객체에서만 사용이 가능합니다.
// 이외에도 클래스 선언문에 쓸수있는 타입등이있음미다.

// interface 이 파일을 어노테이션 클래스로 지정합니다.

// => LoginUser라는 이름을 가진 어노테이션이 생성되었다고 보면 됩니다.(중요)
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
}
