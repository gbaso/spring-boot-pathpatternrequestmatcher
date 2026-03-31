package com.github.gbaso.ppb;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.client.RestTestClient;

import static org.assertj.core.api.Assertions.assertThat;


class GreetingsControllerTest {

	@Nested
	@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
	@AutoConfigureRestTestClient
	class Servlet {

		@Autowired
		RestTestClient rest;

		@Test
		void hello() {
			rest.get().uri("/hello").exchange()
				.expectStatus().isOk();
		}

	}

	@Nested
	@WebMvcTest(GreetingsController.class)
	class Mock {

		@Autowired
		MockMvcTester mvc;

		@Nested
		class WithDefaultSecurity {

			@Test
			void hello() {
				assertThat(mvc.get().uri("/hello"))
					.hasStatus(HttpStatus.UNAUTHORIZED);
			}

		}

		@Nested
		@Import(SecurityConfiguration.WithHttpSecurityCustomizer.class)
		class WithHttpSecurityCustomizer {

			@Test
			void hello() {
				assertThat(mvc.get().uri("/hello"))
					.hasStatusOk();
			}

		}

		@Nested
		@Import(SecurityConfiguration.WithSecurityFilterChain.class)
		class WithSecurityFilterChain {

			@Test
			void hello() {
				assertThat(mvc.get().uri("/hello"))
					.hasStatusOk();
			}

		}

	}

}
