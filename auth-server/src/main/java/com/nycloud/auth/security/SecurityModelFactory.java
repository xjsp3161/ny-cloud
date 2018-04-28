package com.nycloud.auth.security;

import com.nycloud.auth.model.SysUser;
import com.nycloud.auth.security.userDetail.UserDetailImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import java.util.Collection;
import java.util.Date;

/**
 * Created by super on 2017/12/4.
 */
public class SecurityModelFactory {

    public static UserDetailImpl create(SysUser user) {
        Collection<? extends GrantedAuthority> authorities;
        try {
            authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthorities());
        } catch (Exception e) {
            authorities = null;
        }

        Date lastPasswordReset = new Date();
        lastPasswordReset.setTime(user.getLastPasswordChange());
        return new UserDetailImpl(
                user.getUsername(),
                user.getUsername(),
                user.getPassword(),
                lastPasswordReset,
                authorities,
                user.getEnable()
        );
    }

}
