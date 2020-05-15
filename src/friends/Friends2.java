package friends;

import java.util.ArrayList;
import structures.Queue;
import structures.Stack;

public class Friends2
{

/**
 * Finds the shortest chain of people from p1 to p2. Chain is returned as a
 * sequence of names starting with p1, and ending with p2. Each pair (n1,n2) of
 * consecutive names in the returned chain is an edge in the graph.
 * 
 * @param g  Graph for which shortest chain is to be found.
 * @param p1 Person with whom the chain originates
 * @param p2 Person at whom the chain terminates
 * @return The shortest chain from p1 to p2. Null or empty array list if there
 *         is no path from p1 to p2
 */
public static int shortestChainint(Graph g, String p1, String p2)
{

	/** COMPLETE THIS METHOD **/

	// FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY
	// CHANGE AS REQUIRED FOR YOUR IMPLEMENTATION
	if (g.map.size() == 0)
	{
		return 0;
	}
	Queue<Person> r = new Queue<Person>();
	int[] visited = new int[g.members.length];
	boolean[] visited2 = new boolean[g.members.length];
	if(p1.equalsIgnoreCase(p2))
	{
		return 0;
	}
	for (int i = 0; i < visited.length; i++)
	{
		visited[i] = -2;
	}
	for (int i = 0; i < visited2.length; i++)
	{
		visited2[i] = false;
	}
	p1 = containsCase(p1, g.members);
	if (p1 == null)
	{
		return 0;
	}
	int c = g.map.get(p1);
	r.enqueue(g.members[c]);
	while (r.isEmpty() == false)
	{
		Person fl = r.dequeue();
		int tem = g.map.get(fl.name);
		if (fl.name.equalsIgnoreCase(p1))  //start
		{
			visited[tem] = 0;
		}
		if (fl.name.equalsIgnoreCase(p2))  //end
		{
			return(visited[tem]);
		}
		while ((g.members[tem].first != null) && visited2[tem] == false)       //if has child
		{
			Friend temp = g.members[tem].first;
			if (visited2[temp.fnum] == false)             //enqueue friend and give them next number
			{
				r.enqueue(g.members[temp.fnum]);
				int ck = temp.fnum;
				if (visited[ck] == -2)
				{
					visited[ck] = visited[tem] + 1;
				}
			}
			g.members[tem].first = g.members[tem].first.next;
		}
		visited2[tem] = true;
	}
	return -1;
}
public static ArrayList<String> shortestChain(Graph g, String p1, String p2)
{

	/** COMPLETE THIS METHOD **/

	// FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY
	// CHANGE AS REQUIRED FOR YOUR IMPLEMENTATION
	if (g.map.size() == 0)
	{
		return null;
	}
	Friend tempo = null;
	boolean yu = true;
	ArrayList<String> p = new ArrayList<String>();
	ArrayList<String> pm = new ArrayList<String>();
	Queue<Person> r = new Queue<Person>();
	int[] visited = new int[g.members.length];
	boolean[] visited2 = new boolean[g.members.length];
	for (int i = 0; i < visited.length; i++)
	{
		visited[i] = -2;
	}
	for (int i = 0; i < visited2.length; i++)
	{
		visited2[i] = false;
	}
	p1 = containsCase(p1, g.members);
	if (p1 == null)
	{
		return null;
	}
	int c = g.map.get(p1);
	int jv = 0;
	int y = 0;
	r.enqueue(g.members[c]);
	while (r.isEmpty() == false)
	{
		Person fl = r.dequeue();
		int tem = g.map.get(fl.name);
		if (fl.name.equalsIgnoreCase(p1))
		{
			visited[tem] = 0;
		}
		if (fl.name.equalsIgnoreCase(p2))
		{
			yu = false;
			p.add(fl.name);
			pm.add(fl.name);
			break;
		}
		if (visited2[tem] == false)
		{
			p.add(fl.name);
		}
		if (g.members[tem].first != null && visited2[tem] == false)
		{
			tempo = g.members[tem].first;
		}
		while ((g.members[tem].first != null) && visited2[tem] == false)
		{
			Friend temp = g.members[tem].first;
			if (visited2[temp.fnum] == false)
			{
				r.enqueue(g.members[temp.fnum]);
				int ck = temp.fnum;
				if (visited[ck] == -2)
				{
					visited[ck] = visited[tem] + 1;
				}
			}
			g.members[tem].first = g.members[tem].first.next;
			jv++;
		}
		if (jv != 0)
		{
			g.members[tem].first = tempo;
		}
		jv = 0;
		visited2[tem] = true;
	}
	if (yu == true)
	{
		return null;
	}
	p2 = containsCase(p2, g.members);
	if (p2 == null)
	{
		return null;
	}
	y = g.map.get(p2);
	y = visited[y] - 1;
	String v = p2;
	jv = 0;
	boolean rm = true;
	for (int i = 0; i < visited.length; i++)
	{
		if (visited[i] == y)
		{
			if (g.members[i].first != null)
			{
				tempo = g.members[i].first;
			}
			while ((g.members[i].first != null) && visited2[i] == true)
			{
				Friend temp = g.members[i].first;
				if (g.members[temp.fnum].name.equalsIgnoreCase(v))
				{
					pm.add(g.members[i].name);
					v = g.members[i].name;
					y--;
					rm = false;
					break;
				}
				else
				{
					g.members[i].first = g.members[i].first.next;
				}
				jv++;
			}
			if (jv != 0)
			{
				g.members[i].first = tempo;
			}
			if (rm == false)
			{
				i = -1;
			}
			if (y == -1)
			{
				break;
			}
			jv = 0;
			rm = true;
		}
	}
	ArrayList<String> actual = new ArrayList<String>();
	for (int i = pm.size() - 1; i >= 0; i--)
	{
		actual.add(pm.get(i));
	}
	return actual;
}

public static ArrayList<String> bfs(Graph g)
{

	/** COMPLETE THIS METHOD **/

	// FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY
	// CHANGE AS REQUIRED FOR YOUR IMPLEMENTATION
	if (g.map.size() == 0)
	{
		return null;
	}
	ArrayList<String> p = new ArrayList<String>();
	Queue<Person> r = new Queue<Person>();
	boolean[] visited2 = new boolean[g.members.length];
	for (int i = 0; i < visited2.length; i++)
	{
		visited2[i] = false;
	}
	for (int x = 0; x < g.members.length; x++)
	{
		r.enqueue(g.members[x]);

		while (r.isEmpty() == false)
		{
			Person fl = r.dequeue();
			int tem = g.map.get(fl.name);
			if (visited2[tem] == false)
			{
				p.add(fl.name);
			}
			while ((g.members[tem].first != null) && visited2[tem] == false)
			{
				Friend temp = g.members[tem].first;
				if (visited2[temp.fnum] == false)
				{
					r.enqueue(g.members[temp.fnum]);
				}
				g.members[tem].first = g.members[tem].first.next;
			}
			visited2[tem] = true;
		}
	}
	return p;
}

public static ArrayList<String> bfstopsort(Graph g)
{

	/** COMPLETE THIS METHOD **/

	// FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY
	// CHANGE AS REQUIRED FOR YOUR IMPLEMENTATION
	if (g.map.size() == 0)
	{
		return null;
	}
	ArrayList<String> p = new ArrayList<String>();
	Queue<Person> r = new Queue<Person>();
	int ru = 0;
	int[] visited2 = new int[g.members.length];
	Person[] visited3 = new Person[g.members.length];
	for (int i = 0; i < visited2.length; i++)
	{
		visited2[i] = 0;
	}
	for (int i = 0; i < visited2.length; i++)
	{
		if (visited2[i] == 0)
		{
			visited3[ru] = g.members[i];
			ru++;
			r.enqueue(g.members[i]);
		}
	}

	while (r.isEmpty() == false)
	{
		Person fl = r.dequeue();
		int tem = g.map.get(fl.name);
		while ((g.members[tem].first != null))
		{
			visited2[tem]--;
			Friend temp = g.members[tem].first;
			if (visited2[tem] == 0)
			{
				visited3[ru] = g.members[tem];
				ru++;
				r.enqueue(g.members[tem]);
			}
			g.members[tem].first = g.members[tem].first.next;
		}
	}

	return p;
}

/**
 * Finds all cliques of students in a given school.
 * 
 * Returns an array list of array lists - each constituent array list contains
 * the names of all students in a clique.
 * 
 * @param g      Graph for which cliques are to be found.
 * @param school Name of school
 * @return Array list of clique array lists. Null or empty array list if there
 *         is no student in the given school
 */
public static ArrayList<ArrayList<String>> cliques(Graph g, String school)
{

	/** COMPLETE THIS METHOD **/

	// FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY
	// CHANGE AS REQUIRED FOR YOUR IMPLEMENTATION
	if (g.map.size() == 0)
	{
		return null;
	}
	Graph m = g;
	Friend tempo = null;
	ArrayList<ArrayList<String>> j = new ArrayList<ArrayList<String>>();
	ArrayList<String> p = new ArrayList<String>();
	Queue<Friend> r = new Queue<Friend>();
	boolean[] visited = new boolean[m.members.length];
	for (int i = 0; i < visited.length; i++)
	{
		visited[i] = false;
	}
	int c = 0;
	int cv = 0;
	int jv = 0;
	while (cv != visited.length)
	{
		if (m.members[c].school == null)
		{
			visited[c] = true;
			if (r.isEmpty() == false)
			{
				Friend l = r.dequeue();
				c = l.fnum;
			}
			else
			{
				if (p.size() != 0)
				{
					j.add(p);
					p = new ArrayList<String>();
				}
				c++;
				cv++;
				if (cv == visited.length)
				{
					break;
				}
				c = cv;
			}
		}
		else if (m.members[c].school.equalsIgnoreCase(school) && visited[c] == false)
		{
			visited[c] = true;
			p.add(m.members[c].name);
			if (g.members[c].first != null)
			{
				tempo = g.members[c].first;
			}
			while (m.members[c].first != null)
			{
				r.enqueue(m.members[c].first);
				m.members[c].first = m.members[c].first.next;
				jv++;
			}
			if (jv != 0)
			{
				g.members[c].first = tempo;
			}
			jv = 0;
			if (r.isEmpty() == false)
			{
				Friend l = r.dequeue();
				c = l.fnum;
			}
			else
			{
				if (p.size() != 0)
				{
					j.add(p);
					p = new ArrayList<String>();
				}
				c++;
				cv++;
				if (cv == visited.length)
				{
					break;
				}
				c = cv;
			}
		}
		else
		{
			visited[c] = true;
			if (r.isEmpty() == false)
			{
				Friend l = r.dequeue();
				c = l.fnum;
			}
			else
			{
				if (p.size() != 0)
				{
					j.add(p);
					p = new ArrayList<String>();
				}
				c++;
				cv++;
				if (cv == visited.length)
				{
					break;
				}
				c = cv;
			}
		}

	}

	return j;

}

/**
 * Finds and returns all connectors in the graph.
 * 
 * @param m Graph for which connectors needs to be found.
 * @return Names of all connectors. Null or empty array list if there are no
 *         connectors.
 */
public static ArrayList<String> connectors(Graph g)
{

	/** COMPLETE THIS METHOD **/

	// FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY
	// CHANGE AS REQUIRED FOR YOUR IMPLEMENTATION
	if (g.map.size() == 0)
	{
		return null;
	}
	ArrayList<String> stuff = new ArrayList<String>();
	Graph m;
	m = g;
	Friend[] min = new Friend[m.members.length];
	for (int i = 0; i < min.length; i++)
	{
		min[i] = g.members[i].first;
	}
	Stack<Person> r = new Stack<Person>();
	int[] dfsnum = new int[m.members.length];
	int[] start = new int[m.members.length];
	int[] back = new int[m.members.length];
	boolean[] visited2 = new boolean[m.members.length];
	for (int i = 0; i < start.length; i++)
	{
		start[i] = 0;
	}
	int c = 0;
	boolean once = true;
	boolean twice = true;
	int f = -3000;
	for (int l = 0; l < m.members.length; l++)
	{
		for (int i = 0; i < dfsnum.length; i++)
		{
			dfsnum[i] = -2;
		}
		for (int i = 0; i < visited2.length; i++)
		{
			visited2[i] = false;
		}
		for (int i = 0; i < back.length; i++)
		{
			back[i] = -2;
		}
		f = l;
		c = 0;
		while (f != -200)
		{
			int temo = m.map.get(m.members[f].name);
			r.push(m.members[temo]);
			twice = true;
			while (r.isEmpty() == false)
			{
				if (twice == true)
				{
					Person k = null;
					k = r.peek();
					int tem = m.map.get(k.name);
					if (visited2[tem] == false)
					{
						c++;
						dfsnum[tem] = c;
						back[tem] = c;
						once = false;
					}
					else
					{
						k = r.pop();
						if (r.isEmpty())
						{
							break;
						}
						Person nm = r.peek();
						int evenmore = m.map.get(nm.name);
						back[evenmore] = Math.min(dfsnum[tem], back[evenmore]);
						tem = evenmore;
					}
					if ((m.members[tem].first == null))
					{
						if (once == true)
						{
							k = r.pop();
							int ru = m.map.get(k.name);
							if (r.isEmpty())
							{
								break;
							}
							Person nm = r.peek();
							int evenmore = m.map.get(nm.name);
							if (back[ru] >= dfsnum[evenmore])
							{
								if (!stuff.contains(nm.name))
								{
									stuff.add(nm.name);
								}
							}
							else
							{
								back[evenmore] = Math.min(back[evenmore], back[ru]);
							}
							if (m.members[evenmore].first != null)
							{
								Friend temp = m.members[evenmore].first;
								r.push(m.members[temp.fnum]);
								m.members[evenmore].first = m.members[evenmore].first.next;
							}
							else
							{
								twice = false;
							}
						}
					}
					else
					{
						Friend temp = m.members[tem].first;
						r.push(m.members[temp.fnum]);
						m.members[tem].first = m.members[tem].first.next;
					}
					visited2[tem] = true;
					once = true;
				}
				else
				{
					Person k = null;
					k = r.pop();
					int ru = m.map.get(k.name);
					if (r.isEmpty())
					{
						break;
					}
					Person nm = r.peek();
					int evenmore = m.map.get(nm.name);
					if (back[ru] >= dfsnum[evenmore])
					{
						if (!stuff.contains(nm.name))
						{
							stuff.add(nm.name);
						}
					}
					else
					{
						back[evenmore] = Math.min(back[evenmore], back[ru]);
					}
					if (m.members[evenmore].first != null)
					{
						Friend temp = m.members[evenmore].first;
						r.push(m.members[temp.fnum]);
						m.members[evenmore].first = m.members[evenmore].first.next;
						twice = true;
					}
					else
					{
						twice = false;
					}
				}
			}
			f = test(visited2);
		}
		for (int i = 0; i < min.length; i++)
		{
			g.members[i].first = min[i];
		}
		for (int pl = 0; pl < stuff.size(); pl++)
		{
			String rk = stuff.get(pl);
			int rk2 = m.map.get(rk);
			start[rk2]++;
		}
		stuff.clear();
	}
	for (int i = 0; i < start.length; i++)
	{
		if (start[i] == start.length)
		{
			stuff.add(m.members[i].name);
		}
	}
	return stuff;

}

private static int test(boolean[] r)
{
	for (int i = 0; i < r.length; i++)
	{
		if (r[i] == false)
		{
			return i;
		}
	}
	return -200;
}

private static String containsCase(String r, Person[] s)
{
	for (int i = 0; i < s.length; i++)
	{
		String rm = s[i].name;
		if (r.equalsIgnoreCase(rm))
		{
			return s[i].name;
		}
	}
	return null;
}

}
