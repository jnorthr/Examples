public class Hammer{

    public static void main(String[] args)
    {
        Hammer h = new Hammer();
        def ud = System.getProperty("user.dir");
        println "... user.dir="+ud;
        def uh = System.getProperty("user.home");
        println "... user.home="+uh;
        def un = System.getProperty("user.name");
        println "... user.name="+un;
        Map<String, String> env = System.getenv();
        for (String envName : env.keySet()) {
            System.out.format("... %s=%s%n",
                              envName,
                              env.get(envName));
        }
        println "... waiting"
        sleep(30000);
        println "--- the end ---"
    } // end of main
} // end of class