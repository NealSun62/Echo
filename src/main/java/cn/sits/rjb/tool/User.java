package cn.sits.rjb.tool;

/**
 * @author Neal.Sun
 * @version V1.0
 * @Package cn.sits.rjb.tool
 * @date 2020/03/27 16:11
 */
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class User implements Serializable{
    private String username;
    private String password;
}