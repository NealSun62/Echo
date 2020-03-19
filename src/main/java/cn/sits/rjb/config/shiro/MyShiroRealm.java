package cn.sits.rjb.config.shiro;

import cn.sits.rjb.system.model.po.SysUser;
import cn.sits.rjb.system.service.impl.SysUserServiceImpl;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    @Lazy
    private SysUserServiceImpl sysUserServiceImpl;



    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SysUser User = (SysUser) principals.getPrimaryPrincipal();

        try {
//            List<SysRole> roles = sysRoleService.findAllBySysUserId(User.getSysUserId());
//            for (SysRole role : roles) {
//                authorizationInfo.addRole(role.getSysRoleName());//角色存储
//                //此处如果多个角色都拥有某项权限，会数据重复，内部用的是Set
//                List<SysModule> sysPermissions = sysModuleService.findAllBySysRoleId(role.getSysRoleId());
//                for (SysModule perm : sysPermissions) {
//                    authorizationInfo.addStringPermission(perm.getPermission());//权限存储
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        //获取用户的输入的账号.
        String username = (String) token.getPrincipal();
        //声明一个user用来获取sessionDao中的user
//        SysUser userSession = null;
        System.out.println(token.getCredentials());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        SysUser user = sysUserServiceImpl.findBySysUserCode(username);
        if (user == null) {
            return null;
        }
        //假设这个盐值是从数据库中查出的
        String salt = "lilei";
        ByteSource credentialsSalt = ByteSource.Util.bytes(salt);
//        if (false){ //判断账户是否冻结
////        if (!user.getActive()) { //判断账户是否冻结
//            throw new LockedAccountException();
//        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user, //用户名
                user.getPassWord(), //密码
//                credentialsSalt,//盐值
                getName()  //realm name
        );
        return authenticationInfo;
    }
    }
