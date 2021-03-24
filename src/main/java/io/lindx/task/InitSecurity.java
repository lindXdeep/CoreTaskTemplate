package io.lindx.task;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Order(2)
public class InitSecurity extends AbstractSecurityWebApplicationInitializer {
  // пустой класс, использующийся для регистрации модуля в spring-контейнере
}
