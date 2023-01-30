package com.uniqueauction;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

/** 통합 테스트시 외부 요인으로인해 기대하던 데이터를 받지못해 테스트 실패하는경우 발생.
 *  예를들면 H2 메모리 디비사용시 Mysql에서 동작하던게 테스트 환경에서 에러발생.
 *  그로인해 기존 테스트 코드 수정하는 번거로움 , 쿼리사용 제약이 생김.
 *  결론적으로 Mysql 독립 테스트환경을 위해 docker를 이용해 Mysql띄우고 데이터소스로 사용하는 방법이
 *  테스트 컨테이너. (docker 컨테이너를 사용할 수 있또록 해주는 오픈소스라이브러리)
 **/

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@ActiveProfiles("docker-test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public @interface TestContainerBase {
}
