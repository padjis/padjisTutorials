package com.padjistutorial.LingoProject;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.padjistutorial.LingoProject.classes.LingoGame;

public class LingoGameTest {
	
	ApplicationContext ctx;

	@Before
	public void setUp() throws Exception {
		ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	@Test
	public void test() {
		//fail("Not yet implemented");
		LingoGame lingoGame=(LingoGame) ctx.getBean("myLingoGame");
		lingoGame.StartGame();
	}

}
