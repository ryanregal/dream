
public class RethrowingExceptions
{
	public static void someMethod2() throws Exception
	{
		System.out.println("Initialize the exception.");
		throw new Exception("First Throw");
	}
	
	public static void someMethod() throws Exception
	{
		try
		{
			someMethod2();
		}
		catch(Exception e)
		{
			System.out.println("Rethrow the exception.");
			throw new Exception("Rethrow Throw");
		}
	}
	
	public static void main(String[] args) 
	{
		try
		{
			someMethod();
		}
		catch(Exception e)
		{
			System.out.println("Exception in main.");
			e.printStackTrace();
		}
	}
}
