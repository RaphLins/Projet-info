import static org.junit.Assert.*;

import model.characters.AdultWizard;
import model.characters.Character;
import org.junit.Test;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class UnitTests {
    @Test(expected=RuntimeException.class)
    public void test1(){
        Character c = new AdultWizard("M");
    }
}