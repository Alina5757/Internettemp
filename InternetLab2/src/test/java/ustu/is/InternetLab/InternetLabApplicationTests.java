package ustu.is.InternetLab;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ustu.is.InternetLab.calculator.service.CalculatorServise;

@SpringBootTest
class InternetLabApplicationTests {

	private final Logger log = LoggerFactory.getLogger(InternetLabApplicationTests.class);

	@Autowired
	CalculatorServise calculatorServise;

	@Test
	void testCalcSumm() {
		final String calcstr = calculatorServise.say("U", "summ", 100, 20);
		log.info("Stroka: " + calcstr);
		log.info("Primer: I am summ:  Your summ (100 + 20): 120, U!");
		Assertions.assertEquals("I am summ:  Your summ (100 + 20): 120, U!", calcstr);
	}

	@Test
	void testCalcRazn() {
		final String calcstr = calculatorServise.say("Usr", "razn", 100, 20);
		log.info("Stroka: " + calcstr);
		log.info("Primer: I am razn:  Your razn (100 - 20): 80, Usr!");
		Assertions.assertEquals("I am razn:  Your razn (100 - 20): 80, Usr!", calcstr);
	}

	@Test
	void testCalcDel() {
		final String calcstr = calculatorServise.say("User", "del", 100, 20);
		log.info("Stroka: " + calcstr);
		log.info("Primer: I am del:  Your del (100 / 20): 5.0, User!");
		Assertions.assertEquals("I am del:  Your del (100 / 20): 5.0, User!", calcstr);
	}

	@Test
	void testCalcMult() {
		final String calcstr = calculatorServise.say("Uu", "mult", 50, 20);
		log.info("Stroka: " + calcstr);
		log.info("Primer: I am mult:  Your mult (50 * 20): 1000, Uu!");
		Assertions.assertEquals("I am mult:  Your mult (50 * 20): 1000, Uu!", calcstr);
	}
}
