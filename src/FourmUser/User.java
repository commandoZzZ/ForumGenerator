package FourmUser;

import java.util.Date;
import java.util.Vector;

import ForumComponent.Forum;
import ForumComponent.Post;
import ForumComponent.SubForum;
import ForumComponent.MemberType;


public class User {
	
	public enum Status {
	    GUEST, MEMBER, ADMINISTRATOR, SUPER_ADMINISTRATOR
	}
	private String _username;
	private String _password;
	private Forum _forum;
	private Vector<Post> _threads;
	private Vector<Post> _reaplayPosts; 
	private Vector<User> _friends;
	private Vector<Complaint> _complaints;
	private Date _start_date;
	private Status _status;	
	private String _email; 
	private MemberType _type;

	public User(String username, String password ,String status ) {
		this._status = convertStringToStatus(status) ;
		this._username = username ;
		this._password = password ;
		this._threads = new Vector<Post>();
		this._reaplayPosts = new Vector<Post>();
		this._friends = new Vector<User>();
		this._complaints = new Vector<Complaint>();
		this._start_date= new Date();
		this._email = "";
	}
	public User(Forum forum ,String username, String password ,String status ) {
		this._username = username ;
		this._password = password ;
		this._forum = forum;
		this._threads = new Vector<Post>();
		this._reaplayPosts = new Vector<Post>();
		this._friends = new Vector<User>();
		this._complaints = new Vector<Complaint>();
		this._start_date= new Date();
		this._status =convertStringToStatus(status) ;	
		this._email = "";
		this._type = this._forum.getMemberTypeByName("Default");
		
		
	}

	public User(Forum forum ,String username, String password ,String status  , String email) {
		this._username = username ;
		this._password = password ;
		this._forum = forum;
		this._threads = new Vector<Post>();
		this._reaplayPosts = new Vector<Post>();
		this._friends = new Vector<User>();
		this._complaints = new Vector<Complaint>();
		this._start_date= new Date();
		this._status =convertStringToStatus(status) ;	
		this._email = email;
		this._type = this._forum.getMemberTypeByName("Default");
		
	}
	



	public User(Forum forum, String username) {
		this._username =username ; 
		this._password = "" ;
		this._forum = forum;
		this._threads = new Vector<Post>();
		this._reaplayPosts = new Vector<Post>();
		this._friends = new Vector<User>();
		this._complaints = new Vector<Complaint>();
		this._start_date= new Date();
		this._status = Status.GUEST;	
		this._email = "";
		this._type = this._forum.getMemberTypeByName("Default");

	}
	

	public void set_type(MemberType _type) {
		this._type = _type;
	}
	public String get_username() {
		return _username;
	}
	public Forum get_forum() {
		return _forum;
	}
	public void set_username(String _username) {
		this._username = _username;
	}

	public void set_password(String _password) {
		this._password = _password;
	}
	
	public void set_forum(Forum _forum) {
		this._forum = _forum;
	}
	
	public boolean isUser(String string) {
		return convertStringToStatus(string)== this._status;

	}
	
	private Status convertStringToStatus(String string){
		if(string.equals("GUEST"))
			return 	Status.GUEST;
		if(string.equals("MEMBER"))
			return Status.MEMBER;
		if(string.equals("ADMINISTRATOR"))
			return Status.ADMINISTRATOR;
		if(string.equals("SUPER_ADMINISTRATOR"))
			return Status.SUPER_ADMINISTRATOR;
		return null;
	}
	
	public String get_password() {
		return _password;
	}

	public void add_thread(Post p) {
		this._threads.add(p);
	}

	public void add_replyPost(Post p) {
		this._reaplayPosts.add(p);
	}


	public Vector<Post> get_threads() {
		return _threads;
	}

	public Vector<Post> get_reaplayPosts() {
		return _reaplayPosts;
	}

	public void deletePost(Post post) {
		for(int i=0 ; i< this._reaplayPosts.size() ; i++)
			if(this._reaplayPosts.get(i) == post)
				this._reaplayPosts.remove(i);
		for(int i=0 ; i< this._threads.size(); i++ )
			if(this._threads.get(i) == post)
				this._threads.remove(i);				
		
	}

	public boolean isPostedInSubForum(SubForum sub_fourm) {
		for(int i = 0 ; i< this._threads.size(); i++)
			if(this._threads.get(i).get_subForum()==sub_fourm)
				return true;
		for(int i = 0 ; i< this._reaplayPosts.size(); i++)
			if(this._reaplayPosts.get(i).get_subForum()==sub_fourm)
				return true;
		return false;
	}

	public void add_complaint(Complaint complaint) {
		this._complaints.add(complaint);
		
	}




//----------------------------------------------------------------------------------------------

	

}
