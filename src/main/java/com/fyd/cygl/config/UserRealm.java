package com.fyd.cygl.config;

import com.fyd.cygl.entity.Permission;
import com.fyd.cygl.entity.Role;
import com.fyd.cygl.entity.User;
import com.fyd.cygl.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 把获取角色和权限信息的userService的两个接口提供给Shiro，让Shiro有法可依。
 *
 首先，UserRealm这个类继承了AuthorizingRealm，
        这个类的作用是两处获取信息，
                    一处是Subject即用户传过来的信息；
                    一处是我们提供给shiro的userService接口以获取权限信息和角色信息。
        拿这两个信息之后AuthorizingRealm会自动进行比较，判断用户名密码，用户权限等等。
 然后，拿用户凭证信息的是doGetAuthenticationInfo接口，
       拿角色权限信息的是doGetAuthorizationInfo接口
 然后，两个重要参数，AuthenticationToken是我们可以自己实现的用户凭证/密钥信息，
                    PrincipalCollection是用户凭证信息集合。注意Principals表示凭证（比如用户名、密码等）
 最后，配置完成对比的两方之后Subject.login(token)的时候就会调用doGetAuthenticationInfo方法；
        涉及到Subject.hasRole或者Subject.hasPermission的时候就会调用doGetAuthorizationInfo方法；


 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    /**
     * 角色权限和对应权限添加
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置-->UserRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //获取登录uname
        String uname = (String)principals.getPrimaryPrincipal();
        System.out.println("uname="+uname);
        //通过获取的uid查询该用户是什么角色
        List<Role> roles = userService.findRoles(uname);
        //存角色名字信息
        Set<String> roleNames = new HashSet<>(roles.size());
        for(Role role:roles){
            roleNames.add(role.getName());
        }
        System.out.println(roleNames);
        //此处把当前subject对应的所有角色信息交给shiro，调用hasROle的时候就根据这些role信息判断
        authorizationInfo.setRoles(roleNames);
        //此处同上操作。只不过存的是该用户拥有的对应权限信息。
        List<Permission> permissions = userService.findPermissions(uname);
        Set<String> permissionNames = new HashSet<>(permissions.size());
        for (Permission permission:permissions){
            permissionNames.add(permission.getName());
        }
        System.out.println("hahaha"+permissionNames);
        //此处把当前subject对应的所有权限信息交给shiro，调用hasPermission的时候就根据这些role信息判断
        authorizationInfo.setStringPermissions(permissionNames);
        return authorizationInfo;
    }

    /**
     *主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (token.getPrincipal() == null) {
            return null;
        }
        System.out.println("UserRealm.doGetAuthenticationInfo()");
        //获取用户输入的username
        String username = (String) token.getPrincipal();

        //通过username从数据库中查找 User对象
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        User user = userService.findUserByUsername(username);
        if (user == null){
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                username,  //表示凭证，可以随便设，跟token里的一致就行
                user.getPassword(),//表示密钥如密码，你可以自己随便设，跟token里的一致就行
                ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt  采用明文访问时，不需要此句
                getName()  //当前的realm名
        );
        //authenticationInfo信息交个shiro，调用login的时候会自动比较这里的token和authenticationInfo
       // ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt  采用明文访问时，不需要此句
        return authenticationInfo;
    }
}
