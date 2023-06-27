package com.demo.contorller;

import com.demo.dto.UserRegistrationRequest;
import com.demo.utils.JdbcExecutor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/demo")
public class DemoController implements DemoApi {
    private final JdbcExecutor jdbcExecutor;
    private final AsyncTaskExecutor asyncTaskExecutor;
    @SuppressWarnings("unused")
    private static final UserRegistrationRequest userRegistrationRequest = UserRegistrationRequest.builder()
            .name("user")
            .secondName("secondName")
            .email("email@gmail.com")
            .password("password")
            .address("address")
            .phoneNumber("phoneNumber")
            .build();

    @Override
    @PostMapping("/test")
    public void test() {
        log.info("Start");
        jdbcExecutor.createTest();
    }

    @Override
    @PostMapping("/fill")
    public void fillDatabase() {
        log.info("Start");
        asyncTaskExecutor.execute(() -> {
            for (int i = 0; i < 100_000_000; i++) {
                if (i % 10_000 == 0) log.info("Create user: {}", i);
                try {
                    jdbcExecutor.monoCreate();
                } catch (RuntimeException e) {
                    log.error("An exception has been caught with message: {}", e.getMessage());
                }
            }
        });
    }
}
