package example.identity.management.webstore.config;

//@EnableWebSecurity // if you do not use authentication, comment out this line.
public class SecurityConfig {
//extends WebSecurityConfigurerAdapter {

//	@Override
//    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//        InputStream in = SecurityConfig.class.getClassLoader().getResourceAsStream("users.properties");
//        Properties properties = new Properties();
//        properties.load(in);
//        authenticationManagerBuilder.userDetailsService(new InMemoryUserDetailsManager(properties));
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic().and().authorizeRequests().anyRequest().authenticated();
//    }
}
