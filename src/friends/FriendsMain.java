package friends;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class FriendsMain {

	public static void main(String[] args)throws FileNotFoundException {
		// TODO Auto-generated method stub
	Scanner s=new Scanner(new File("Test"));
	Graph r=new Graph(s);
	Friends j=new Friends();
	Friends2 i=new Friends2();
	System.out.println(j.connectors(r));
	System.out.println(j.shortestChain(r,"sam", "aparna"));
	System.out.println(j.cliques(r,"rutgers"));
	s.close();
	System.out.println(i.shortestChainint(r,"sam","kaitlin"));
	}
}
