package com.hf.test;


public class EmbededTomcat {
	/*private final Log log=LogFactory.getLog(getClass());
	private static String CONTEXT_PATH = "/HelloMaven";
	private static String PROJECT_PATH = System.getProperty("user.dir");
	private static String WEB_APP_PATH = PROJECT_PATH + File.separatorChar + "src\\main\\webapp";
	private static String CATALINA_HOME = PROJECT_PATH + File.separatorChar + "TomcatEmbed";
	private Tomcat tomcat = new Tomcat();
    private int port;
	
    public EmbededTomcat(int port){
        this.port=port;
    }
    
	public void start()throws Exception{
		tomcat.setPort(port);
		tomcat.setBaseDir(CATALINA_HOME);
		tomcat.getHost().setAppBase(WEB_APP_PATH);
		try{
			StandardServer server = (StandardServer)tomcat.getServer();
		    AprLifecycleListener listener = new AprLifecycleListener();
		    server.addLifecycleListener(listener);
			tomcat.addWebapp(CONTEXT_PATH, WEB_APP_PATH);
		}catch(ServletException e){
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}
		try{
			tomcat.start();
			System.out.println("[INFO] Server running at http://127.0.0.1:" + port+CONTEXT_PATH);
			tomcat.getServer().await();
		}catch(LifecycleException e){
			e.printStackTrace();
			log.error(e.getMessage());
			throw e;
		}
		log.info("Tomcat started.");
	}
	
	public void stop()throws Exception{
            try{
                tomcat.stop();
            }
            catch(LifecycleException ex){
                ex.printStackTrace();
                log.error(ex.getMessage());
                throw ex;
            }
            log.info("Tomcat stoped");
	}
        
    public void setPort(int port){
        this.port=port;
    }
    public int getPort(){
        return this.port;
    }
    
    public static void main(String[] args) throws Exception {
    	EmbededTomcat embededTomcat = new EmbededTomcat(8080);
    	embededTomcat.start();
    	
	}*/
}


