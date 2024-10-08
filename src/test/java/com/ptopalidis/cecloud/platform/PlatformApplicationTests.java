package com.ptopalidis.cecloud.platform;

import com.ptopalidis.cecloud.platform.pdfgenerator.service.PdfGeneratorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;

@SpringBootTest
class PlatformApplicationTests {

	@Mock
	private ResourceLoader resourceLoader;


	@InjectMocks
	private PdfGeneratorService pdfGeneratorService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	@Test
	void contextLoads() throws IOException {

	}

}
