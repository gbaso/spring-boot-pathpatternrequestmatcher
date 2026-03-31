# Path Pattern Builder in WebMVC tests

Class [GreetingsControllerTest.java](src/test/java/com/github/gbaso/ppb/GreetingsControllerTest.java) contains tests for a secured endpoint.

`GreetingsControllerTest.Servlet` uses the default security configuration and a real servlet environment.

`GreetingsControllerTest.Mock.WithDefaultSecurity` uses the default security configuration and a mock servlet environment.

`GreetingsControllerTest.Mock.WithHttpSecurityCustomizer` uses a customizer to allow unauthenticated access to the endpoint that matches `/hello` using a matcher built from `PathPatternRequestMatcher.Builder`.

`GreetingsControllerTest.Mock.WithSecurityFilterChain` uses a custom SecurityFilterChain bean to allow unauthenticated access to the endpoint that matches `/hello` using a matcher built from `PathPatternRequestMatcher.Builder`.

Both tests that use `PathPatternRequestMatcher.Builder` fail with the same error:

```log
org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher$Builder' available
```

The conditions evaluation report shows:

```log
MockMvcAutoConfiguration#dispatcherServletPath matched:
  - @ConditionalOnMissingBean (types: org.springframework.boot.webmvc.autoconfigure.DispatcherServletPath; SearchStrategy: all) did not find any beans (OnBeanCondition)

...

ServletWebSecurityAutoConfiguration.PathPatternRequestMatcherBuilderConfiguration:
  Did not match:
     - @ConditionalOnBean (types: org.springframework.boot.webmvc.autoconfigure.DispatcherServletPath; SearchStrategy: all) did not find any beans of type org.springframework.boot.webmvc.autoconfigure.DispatcherServletPath (OnBeanCondition)
  Matched:
     - @ConditionalOnClass found required class 'org.springframework.boot.webmvc.autoconfigure.DispatcherServletPath' (OnClassCondition)
```
