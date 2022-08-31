package ru.netology;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.page.LoginPage;

import java.sql.DriverManager;

import static com.codeborne.selenide.Selenide.open;

public class TestSQL {
    @BeforeEach
    public void setup() {
        open("http://localhost:9999");
    }

    @Test
    @SneakyThrows
    void stubTest() {
        var userSQL = "SELECT login, password FROM users where login = 'vasya';";
        var codeSQL = "SELECT * FROM auth_codes;";
        var runner = new QueryRunner();

        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass12345"
                );
        ) {
            var loginPage = new LoginPage();
            var authInfo = runner.query(conn, userSQL,new ScalarHandler<>());
            var verificationPage = loginPage.validLogin(authInfo);
            var verificationCode = runner.query(conn,codeSQL,new ScalarHandler<>());
            var dashBoardPage = verificationPage.validVerify(verificationCode);
        }
    }
}
