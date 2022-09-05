package com.fevertime.coinvillage.controller.api;

import com.fevertime.coinvillage.domain.Member;
import com.fevertime.coinvillage.dto.login.*;
import com.fevertime.coinvillage.jwt.JwtFilter;
import com.fevertime.coinvillage.jwt.TokenProvider;
import com.fevertime.coinvillage.repository.MemberRepository;
import com.fevertime.coinvillage.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("member")
@Api(tags = "로그인, 회원가입")
public class MemberApiController {
    private final MemberService memberService;
    private final TokenProvider tokenProvider;
    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping("ruler/signup")
    @ApiOperation(value = "선생님 회원가입")
    public ResponseEntity<MemberResponseDto> signupRuler(
        @Valid @RequestBody MemberRequestDto memberRequestDto
    ) {
        return ResponseEntity.ok(memberService.signupRuler(memberRequestDto));
    }

    @PostMapping("nation/signup")
    @ApiOperation(value = "학생 회원가입")
    public ResponseEntity<MemberResponseDto> signupNation(
            @Valid @RequestBody MemberRequestDto memberRequestDto
    ) {
        return ResponseEntity.ok(memberService.signupNation(memberRequestDto));
    }

    @PostMapping("authenticate")
    @ApiOperation(value = "로그인")
    public ResponseEntity<TokenDto> authorize(@Valid @RequestBody LoginDto loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();

        String accessToken = tokenProvider.createToken(authentication);
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, accessToken);

        Member member = memberRepository.findByEmail(loginDto.getEmail());
        MemberResponseDto memberResponseDto = new MemberResponseDto(member);

        return new ResponseEntity<>(new TokenDto(accessToken, memberResponseDto), httpHeaders, HttpStatus.OK);
    }
    
    @GetMapping
    @ApiOperation(value = "회원 전체보기 테스트")
    public ResponseEntity<List<CountryResponseDto>> findCountries() {
        return ResponseEntity.ok(memberService.findCountries());
    }
}