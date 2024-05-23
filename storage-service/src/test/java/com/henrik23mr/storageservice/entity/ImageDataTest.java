package com.henrik23mr.storageservice.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ImageDataTest {

    @Test
    public void testEquals() {
        ImageData imageData1 = ImageData.builder()
                .id(1L)
                .name("image1")
                .type("jpg")
                .imagesData(new byte[]{1, 2, 3})
                .build();

        ImageData imageData2 = ImageData.builder()
                .id(1L)
                .name("image1")
                .type("jpg")
                .imagesData(new byte[]{1, 2, 3})
                .build();

        ImageData imageData3 = ImageData.builder()
                .id(2L)
                .name("image2")
                .type("png")
                .imagesData(new byte[]{4, 5, 6})
                .build();

        assertThat(imageData1).isEqualTo(imageData2);
        assertThat(imageData1).isNotEqualTo(imageData3);
    }

    @Test
    public void testHashCode() {
        ImageData imageData1 = ImageData.builder()
                .id(1L)
                .name("image1")
                .type("jpg")
                .imagesData(new byte[]{1, 2, 3})
                .build();

        ImageData imageData2 = ImageData.builder()
                .id(1L)
                .name("image1")
                .type("jpg")
                .imagesData(new byte[]{1, 2, 3})
                .build();

        ImageData imageData3 = ImageData.builder()
                .id(2L)
                .name("image2")
                .type("png")
                .imagesData(new byte[]{4, 5, 6})
                .build();

        assertThat(imageData1.hashCode()).isEqualTo(imageData2.hashCode());
        assertThat(imageData1.hashCode()).isNotEqualTo(imageData3.hashCode());
    }

    @Test
    public void testToString() {
        ImageData imageData = ImageData.builder()
                .id(1L)
                .name("image1")
                .type("jpg")
                .imagesData(new byte[]{1, 2, 3})
                .build();

        String expectedString = "ImageData(id=1, name=image1, type=jpg, imagesData=[1, 2, 3])";
        assertThat(imageData.toString()).isEqualTo(expectedString);
    }
}
