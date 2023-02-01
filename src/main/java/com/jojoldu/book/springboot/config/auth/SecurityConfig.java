package com.jojoldu.book.springboot.config.auth;

import lombok.RequiredArgsConstructor;
import com.jojoldu.book.springboot.domain.user.Role;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor // 선언된 모든 final 필드가 포함된 생성자를 생성해 줍니다. (중요!!!!!!!!!!!!!!!!!!!!!!!!!)
@EnableWebSecurity       // Spring Security 설정들을 활성화시켜줍니다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // http.csrf().disable().headers().frameOptions().disable() :: h2-console 화면을 사용하기 위해 해당 옵션들을 disable 합니다.
        // ~.authorizeRequests()                                    :: url별 권한 관리를 설정하는 옵션의 시작점 입니다.
        // ~.authorizeRequests().andMatchers                        :: authorizeRequests 선언이 되어야 다음 andMatchers의 옵션을 사용할수가 있습니다.
        // authorizeRequests                                        :: 권한 관리대상을 지정하는 옵션, permitAll옵션을 통해 전체 열람권한을 주었습니다.
        // /api/v1/**                                               :: 주소를 가진 api는 user 권한을 가진 사람만 가능하도록 했습니다.
        // andRequest                                               :: 설정된 값의 이외 나머지 url들을 나타냅니다 but, authenticated()를 추가하여 나머지 url들은 모두 인증된 사용자들에게만 허용하게 합니다.
        // .logout().logoutSuccessUrl("/")                          :: 로그아웃 기능에 대한 여러 설정의 진입점입니다, 성공시 / 주소로 이동
        // oauth2Login :: OAuth 2 로그인 기능에 대한 여러 설정의 진입점.
        // userInfoEndpoint :: 로그인 성공후에 가져올 설정들을 담당 customOAuth2UserService가 되겠죠 이 소스상에서는
        // UserService :: 인터페이스의 구현체를 등록합니다. 로그인 성공한 후에 후속 조치를 취할
        http.csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated()
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                    .userInfoEndpoint()
                    .userService(customOAuth2UserService);
    }
}