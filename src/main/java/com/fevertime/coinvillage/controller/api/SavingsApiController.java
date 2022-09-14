package com.fevertime.coinvillage.controller.api;

import com.fevertime.coinvillage.dto.savings.SavingsRequestDto;
import com.fevertime.coinvillage.dto.savings.SavingsResponseDto;
import com.fevertime.coinvillage.dto.savings.SavingsSettingRequestDto;
import com.fevertime.coinvillage.dto.savings.SavingsSettingResponseDto;
import com.fevertime.coinvillage.service.SavingsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("savings")
@Api(tags = "적금")
public class SavingsApiController {
    private final SavingsService savingsService;

    @GetMapping
    @ApiOperation(value = "적금 목록")
    public ResponseEntity<List<SavingsResponseDto>> showSavings(Authentication authentication) {
        return ResponseEntity.ok(savingsService.showSavings(authentication.getName()));
    }

    @PutMapping("setting")
    @ApiOperation(value = "적금, 세금 세팅하기")
    public ResponseEntity<List<SavingsSettingResponseDto>> stackSavingsSetting(Authentication authentication, @RequestBody SavingsSettingRequestDto savingsSettingRequestDto) {
        return ResponseEntity.ok(savingsService.stackSavings(authentication.getName(), savingsSettingRequestDto));
    }

    @PutMapping("change")
    @ApiOperation(value = "적금, 세금 수정하기")
    public ResponseEntity<SavingsSettingResponseDto> modSavingsSetting(Authentication authentication, @RequestBody SavingsSettingRequestDto savingsSettingRequestDto) {
        return ResponseEntity.ok(savingsService.modSavings(authentication.getName(), savingsSettingRequestDto));
    }
}
