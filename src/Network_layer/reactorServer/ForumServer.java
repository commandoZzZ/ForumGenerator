package Network_layer.reactorServer;

import java.util.Vector;

import Domain_layer.ForumComponent.Forum;
import Domain_layer.ForumComponent.IForum;
import Domain_layer.ForumComponent.Policy;
import Network_layer.reactorServer.reactor.Reactor;
import Network_layer.reactorServer.tokenizer.ForumMessage;

public class ForumServer {
	/**
	 * Main program, used for demonstration purposes. Create and run a
	 * Reactor-based server for the Echo protocol. Listening port number and
	 * number of threads in the thread pool are read from the command line.
	 */
	public static void main(String args[]) {
		
		try {
			int port = 4444;
			int poolSize = 10;
			
			//create forum components
			Policy p = new Policy();
			Vector<String[]> admins = new  Vector<String[]>(); 
		    String[] a1 = {"oren" , "pass"} , a2 =  {"liraz" , "1111"}  , a3  = {"mira_123" , "jhgJGG"};
			admins.add(a1);
			admins.add(a2);
			admins.add(a3);	
			
			//create forum		
			IForum forum = Forum.createForum( "hadaramran" , "123456789" ,p ,admins, "WorkShop Forum");	
			Reactor<ForumMessage> reactor = Reactor.startForumServer(port, poolSize ,forum);
			Thread thread = new Thread(reactor);
			thread.start();			
			Reactor.logger_info("Reactor is ready on port " + reactor.getPort());
			thread.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
