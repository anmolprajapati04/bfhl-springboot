package com.chitkara.bfhl.service;

import com.chitkara.bfhl.dto.BfhlRequestDto;
import com.chitkara.bfhl.dto.BfhlResponseDto;

public interface BfhlService {

    /**
     * Processes the input data array and returns a categorized response.
     *
     * @param requestDto the incoming request containing the data array
     * @return BfhlResponseDto with all categorized arrays, sum, and concat string
     */
    BfhlResponseDto processData(BfhlRequestDto requestDto);
}
