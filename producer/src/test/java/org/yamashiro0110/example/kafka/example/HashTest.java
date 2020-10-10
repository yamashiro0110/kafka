package org.yamashiro0110.example.kafka.example;

import com.google.common.hash.Hashing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

public class HashTest {
    private static final Logger log = LoggerFactory.getLogger(HashTest.class);

    @Test
    public void test() {
        final String hash = Hashing.sha256().newHasher()
                .putString("sample kafka msg key", StandardCharsets.UTF_8)
                .hash()
                .toString();

        Assertions.assertEquals("fe3ec6fd6fa01fec79e7b2b59d8aa6dbdc648962fa4e100298a82bd39ea56a0d", hash);
    }

}
