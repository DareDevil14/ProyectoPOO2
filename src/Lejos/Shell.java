package Lejos;
public class Shell {
	String osName = System.getProperty("os.name");
	String cmd[] = new String[3];
	public void ejecutaComandos(String cmd[]){
		try
	    {
			Runtime.getRuntime().exec(cmd);
	    } catch (Exception t)
	      {
	    	System.out.println("NO SE EJEXUTÓ NADA");
	        t.printStackTrace();
	      }
	}
	public void ejecutaComando(String comando){
		try
	    {     
			/**NOTA: para hacerlo funcional para linux descomentar y componer código :D***/
			
			System.out.println(comando);
//			System.out.println(osName);
//			if(osName.equalsIgnoreCase("Windows XP") || osName.equalsIgnoreCase("Windows Vista") 
//					||osName.equalsIgnoreCase("Windows 7") ||osName.equalsIgnoreCase("Windows 8") 
//					||osName.equalsIgnoreCase("Windows 8.1") )
//			{
				Runtime.getRuntime().exec("cmd /C "+comando);
			//}
//			else
//			{
//				System.out.println("entra en else");
//				Runtime rt = Runtime.getRuntime();
//		        Process proc = rt.exec(comando);
//		        int exitVal = proc.exitValue();
//		        System.out.println("Process exitValue: " + exitVal);
//			}
	        
	    } catch (Throwable t)
	      {
	        t.printStackTrace();
	      }
	}
	
	
}
