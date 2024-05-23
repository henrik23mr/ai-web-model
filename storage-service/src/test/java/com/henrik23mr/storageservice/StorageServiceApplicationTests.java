package com.henrik23mr.storageservice;

import com.henrik23mr.storageservice.service.StorageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StorageServiceApplicationTests {

	@Mock
	private StorageService service;

	@InjectMocks
	private StorageServiceApplication storageServiceApplication;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(storageServiceApplication).build();
	}

	@Test
	public void testUploadImage() throws Exception {
		MockMultipartFile mockFile = new MockMultipartFile(
				"image",
				"test.jpg",
				MediaType.IMAGE_JPEG_VALUE,
				"Test Image Content".getBytes()
		);

		when(service.uploadImage(any(MultipartFile.class))).thenReturn("Image uploaded successfully");

		mockMvc.perform(multipart("/image")
						.file(mockFile)
						.contentType(MediaType.MULTIPART_FORM_DATA))
				.andExpect(status().isOk())
				.andExpect(content().string("Image uploaded successfully"));
	}
}
