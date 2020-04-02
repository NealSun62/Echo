package cn.sits.rjb.config.authentication;

/**
 * Token管理器
 * 
 * @description 创建 token 与检查 token 有效性
 * @author louis.lu
 * @since 1.0.0
 */
public interface TokenManager {
	
	String createToken(String userId);

	boolean checkToken(String userId, String token);
}
