package cn.llman.exception;

/**
 * @author
 * @date 2019/1/7
 */
public class UserNotExistException extends RuntimeException {

    public UserNotExistException() {
        super("用户不存在!");
    }
}
