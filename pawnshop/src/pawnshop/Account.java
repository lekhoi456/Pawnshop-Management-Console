package pawnshop;

/**
 * @author KhoiLq
 */

public class Account {
    // Declare variables
    private String userId;
    private String password;

    Account(int i, String userId, String password) throws AccountException {
        throw new AccountException("Not supported yet.");
    }

    /**
     * The Get User ID Method
     *
     * @return
     */
    public String getUserId() {
        return userId;
    }

    /**
     * The Set User ID Method
     *
     * @param userId
     * @throws AccountException
     */
    public void setUserId(String userId) throws AccountException {
        if (userId.trim().equals("")) {
            throw new AccountException("The User ID can't be empty!");
        } else {
            this.userId = userId;
        }
    }

    /**
     * The Get Password Method
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * The Set Password Method
     *
     * @param password
     * @throws AccountException
     */
    public void setPassword(String password) throws AccountException {
        if (password.trim().equals("")) {
            throw new AccountException("The password can't be empty!");
        } else {
            this.password = password;
        }
    }

    /**
     * Create new Account
     *
     * @param userId
     * @param password
     */
    public Account(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

}



