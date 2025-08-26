package users;

import org.junit.Assert;
import org.junit.Test;

public class UserTest {

    @Test
    public void getNameTest() {
        // arrange
        String expectedName = "Igli";
        User user = new User(expectedName);

        // act
        String actualName = user.getName();

        // assert
        Assert.assertEquals(expectedName, actualName);
    }

    @Test
    public void setNameTest() {
        // arrange
        String expectedName = "Chris";
        User user = new User(expectedName);

        // act
        user.setName(expectedName);
        String actualName = user.getName();

        // assert
        Assert.assertEquals(expectedName, actualName);
    }
}
