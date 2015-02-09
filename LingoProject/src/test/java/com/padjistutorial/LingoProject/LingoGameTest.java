package com.padjistutorial.LingoProject;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.padjistutorial.LingoProject.classes.Datasource;
import com.padjistutorial.LingoProject.classes.LingoGame;

@RunWith(MockitoJUnitRunner.class)
public class LingoGameTest {
	
	ApplicationContext ctx;
	
	private static List<String> strings;
	
	@Mock
	Datasource datasource;
	
	@BeforeClass
	public static void setUpGeneral(){
		strings=new ArrayList<String>();
		strings.add("tutorial");
		strings.add("padjis");
	}

	@Before
	public void setUp() throws Exception {
		ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		
		Mockito.when(datasource.getWordsFromFackedDataSource()).thenReturn(strings);
	}

	@Test
	public void test() {
		//fail("Not yet implemented");
		LingoGame lingoGame=(LingoGame) ctx.getBean("myLingoGame");
		lingoGame.StartGame();
	}
	
	//mockito test
	@Test
	public void testMockedMethod(){
		System.out.println(datasource.getWordsFromFackedDataSource());
	}
	
	
	//we are going to test the entire system of the lingo game
	@Test
	public void testLingoGame(){
		//instantiating the lingoGame with the spring context
		LingoGame lingoGame=(LingoGame) ctx.getBean("myLingoGame");
		
		//displays "the game has started"
		lingoGame.startGame();
		
		//we are going to use mockito in order to provide a fake set of words in which the playable one
		//will be choosen
		lingoGame.setWordsFromDataSource(datasource.getWordsFromFackedDataSource());
		
		//selects a word between all the words of the faked Datasource
		lingoGame.selectAWordFromTheFakedDataSource();
		
		//displays some of the letters of the selected word in the right order
		//i.e: for the word "TUTORIAL", the display could be "T.T..I..."
		lingoGame.initLayout();
		
		//looping for the number of chances of the player
		for(int i=0;i<lingoGame.getNumberOfChances();i++){
			
			//waits for the user to seize his answer
			lingoGame.waitForAnswer();
			
			//checks whether the answer has correctly been seized
			//if a word is well located, it will appear in capital letter
			//if not, it will be in small letter
			if(lingoGame.checkAnswer()){
				//displays "the game has been won"
				lingoGame.theGameIsWon();
				//reinitializes all the variables
				lingoGame.stopGame();
			}
		}
		//displays "the game has been Lost"
		lingoGame.theGameIsLost();
		//reinitializes all the variables
		lingoGame.stopGame();
	}
	

}
