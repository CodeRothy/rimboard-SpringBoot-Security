package kr.rimu.rimboard.config;

import kr.rimu.rimboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@EnableJpaAuditing
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;

    @Override
    public void configure(WebSecurity web) { // static 하위 파일 목록(css, js, img) 인증 무시
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {  // http 관련 인증 설정
        http
                .authorizeRequests() // 접근에 대한 인증 설정
                    .antMatchers("/login", "/signup", "/user","/post/list").permitAll() // 누구나 접근 허용
                    .antMatchers("/post").hasRole("USER") // USER, ADMIN만 접근 가능
                    .antMatchers("/admin").hasRole("ADMIN") // ADMIN만 접근 가능
                    .anyRequest().authenticated() // 나머지 요청들은 권한의 종류에 상관 없이 권한이 있어야 접근 가능
                .and()
                    .formLogin() // 로그인에 관한 설정
                        .loginPage("/login") // 로그인 페이지 링크
                        .defaultSuccessUrl("/post/list") // 로그인 성공 후 리다이렉트 주소
                .and()
                    .logout() // 로그아웃
                        .logoutSuccessUrl("/post/list") // 로그아웃 성공시 리다이렉트 주소
                        .invalidateHttpSession(true) // 세션 종료
        ;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception { // 9
        auth.userDetailsService(userService)
                .passwordEncoder(new BCryptPasswordEncoder());
        // 해당 서비스(userService)에서는 UserDetailsService를 implements해서
        // loadUserByUsername() 구현해야함 (서비스 참고)
    }
}
