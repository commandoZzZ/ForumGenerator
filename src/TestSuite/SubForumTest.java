package TestSuite;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ForumComponent.Post;
import ForumComponent.SubForum;
import FourmUser.User;

public class SubForumTest {
	
	private SubForum subForum;
	private User mod1;
	private User mod2;

	@Before
	public void setUp() throws Exception {
		mod1 = new User("aaronf", "hello", "MEMBER");
		mod2 = new User("hadara", "hello", "MEMBER");
		Vector<User> mods = new Vector<User>();
		mods.add(mod1);
		mods.add(mod2);
		Vector<Date> dates = new Vector<Date>();
		dates.add(new Date());
		dates.add(new Date());
		subForum = new SubForum("Test", mods, dates);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testOpenThread() {
		mod1.add_thread(new Post("Welcome", "First thread", mod1, subForum));
		assertFalse(subForum.showThreads().contains(mod1.get_threads().get(0)));
		subForum.openThread(mod1.get_threads().get(0));
		assertTrue(subForum.showThreads().contains(mod1.get_threads().get(0)));
	}

	@Test
	public void testGet_theme() {
		assertTrue(subForum.get_theme().equals("Test"));
	}

	@Test
	public void testIsModerator() {
		assertTrue(subForum.isModerator("aaronf"));
		assertTrue(subForum.isModerator("hadara"));
		assertFalse(subForum.isModerator("hoda"));
	}

	@Test
	public void testShowThreads() {
		Vector<Post> threads = new Vector<Post>();
		Post p1 = new Post("Welcome", "First thread", mod1, subForum);
		Post p2 = new Post("Welcome2", "Second thread", mod2, subForum);
		threads.add(p1);
		threads.add(p2);
		mod1.add_thread(p1);
		mod2.add_thread(p2);
		subForum.openThread(mod1.get_threads().get(0));
		subForum.openThread(mod2.get_threads().get(0));
		
		assertTrue(subForum.showThreads().equals(threads));
	}

	@Test
	public void testDeletePost() {
		Post p1 = new Post("Welcome", "First thread", mod1, subForum);
		Post p2 = new Post("Welcome2", "Second thread", mod2, subForum);
		mod1.add_thread(p1);
		mod2.add_thread(p2);
		subForum.openThread(mod1.get_threads().get(0));
		subForum.openThread(mod2.get_threads().get(0));
		
		assertTrue(subForum.showThreads().contains(p1));
		subForum.deletePost(p1);
		assertFalse(subForum.showThreads().contains(p1));
	}

	@Test
	public void testDelete() {
		Post p1 = new Post("Welcome", "First thread", mod1, subForum);
		Post p2 = new Post("Welcome2", "Second thread", mod2, subForum);
		mod1.add_thread(p1);
		mod2.add_thread(p2);
		subForum.openThread(mod1.get_threads().get(0));
		subForum.openThread(mod2.get_threads().get(0));
		
		assertTrue(subForum.showThreads().contains(p1));
		assertTrue(subForum.showThreads().contains(p2));
		subForum.delete();
		assertFalse(subForum.showThreads().contains(p1));
		assertFalse(subForum.showThreads().contains(p2));
	}

	@Test
	public void testGetModerator() {
		User mod = subForum.getModerator("aaronf");
		assertTrue(mod.get_username().equals(mod1.get_username()));
		mod = subForum.getModerator("hadara");
		assertTrue(mod.get_username().equals(mod2.get_username()));
		mod = subForum.getModerator("hoda");
		assertTrue(mod == null);
	}

}
