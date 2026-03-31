package com.github.gbaso.ppb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest(GreetingsController.class)
class MockWithDefaultSecurityTest {

	@Autowired
	MockMvcTester mvc;

	@Test
	void hello() {
		assertThat(mvc.get().uri("/hello"))
			.hasStatus(HttpStatus.UNAUTHORIZED);
	}

}
