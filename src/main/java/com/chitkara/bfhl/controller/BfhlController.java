package com.chitkara.bfhl.controller;

import com.chitkara.bfhl.dto.BfhlRequestDto;
import com.chitkara.bfhl.dto.BfhlResponseDto;
import com.chitkara.bfhl.service.BfhlService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bfhl")
public class BfhlController {

    private static final Logger log = LoggerFactory.getLogger(BfhlController.class);

    private final BfhlService bfhlService;

    public BfhlController(BfhlService bfhlService) {
        this.bfhlService = bfhlService;
    }

    /**
     * POST /bfhl
     * Accepts a JSON body with a "data" array and returns categorized results.
     */
    @PostMapping
    public ResponseEntity<BfhlResponseDto> processData(@Valid @RequestBody BfhlRequestDto requestDto) {
        log.info("Received POST /bfhl request with data: {}", requestDto.getData());
        BfhlResponseDto response = bfhlService.processData(requestDto);
        log.info("Returning response: {}", response);
        return ResponseEntity.ok(response);
    }
}
