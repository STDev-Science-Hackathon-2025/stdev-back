package stdev.hackathon.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stdev.hackathon.user.dto.ChangePasswordRequestDto;
import stdev.hackathon.user.dto.UserLoginRequestDto;
import stdev.hackathon.user.dto.UserSignUpRequestDto;
import stdev.hackathon.user.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원가입")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "회원가입 성공"),
        @ApiResponse(responseCode = "400", description = "회원가입 실패 (아이디/이메일 중복)")
    })
    @PostMapping("/signup")
    public String signup(@RequestBody UserSignUpRequestDto requestDto, HttpServletResponse response) {
        userService.signUp(requestDto, response);
        return "회원가입 성공!";
    }

    @Operation(summary = "로그인")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "로그인 성공"),
        @ApiResponse(responseCode = "400", description = "로그인 실패 (아이디/비밀번호 불일치)")
    })
    @PostMapping("/login")
    public String login(@RequestBody UserLoginRequestDto requestDto, HttpServletResponse response) {
        userService.login(requestDto, response);
        return "로그인 성공!";
    }

    @Operation(summary = "비밀번호 변경")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "비밀번호 변경 성공"),
        @ApiResponse(responseCode = "400", description = "비밀번호 변경 실패 (기존 비밀번호 불일치)")
    })
    @PutMapping("/password")
    public ResponseEntity<String> changePassword(HttpServletRequest request, @RequestBody ChangePasswordRequestDto requestDto) {
        Long userId = userService.getUserIdFromToken(request);
        userService.changePassword(userId, requestDto);
        return ResponseEntity.ok("비밀번호 변경 성공");
    }

    @Operation(summary = "회원 탈퇴")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "회원 탈퇴 성공"),
        @ApiResponse(responseCode = "400", description = "회원 탈퇴 실패 (해당 아이디 없음)")
    })
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(HttpServletRequest request) {
        Long userId = userService.getUserIdFromToken(request);
        userService.deleteUser(userId);
        return ResponseEntity.ok("회원 탈퇴 성공");
    }
}
