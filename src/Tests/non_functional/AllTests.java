package Tests.non_functional;
import java.util.ArrayList;

import junit.framework.*;



public class AllTests {
   	@SuppressWarnings("serial")
   	private static final ArrayList<Class<?>> class_list = new ArrayList<Class<?>>(){{
   		add(non_functional_test_1.class);
   	}};
    public static Test suite(){
    	TestSuite suite= new TestSuite("Database");
    	for(Class<?> c : class_list)
    		suite.addTest(new TestSuite(c));
        return suite;
    }
    public static ArrayList<Class<?>> get_class_list(){
      	return AllTests.class_list;
    }    
}
