package Repositories;

import Entity.User;
import java.sql.SQLException;

public interface UserRepositoryInterface {
    User createUser(User user) throws SQLException;
    User findUser(String userName, String email) throws SQLException;
}
