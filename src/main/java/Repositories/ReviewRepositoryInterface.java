package Repositories;

import MainPackage.Reviews;
import java.sql.SQLException;

public interface ReviewRepositoryInterface {
    Reviews getReviewByUserId(int userId) throws SQLException;
}
