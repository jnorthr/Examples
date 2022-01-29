import groovy.beans.Bindable 

@Bindable
class ClientTransaction 
{ 
    String id, client="", reason=""
    Boolean flag    
    Boolean usage = false;
    int seq = 2;
    int ccy = 1; // euros

    String toString() { "ClientTransaction[id=$id,ccy=$ccy, flag=$flag, reason=$reason, client=$client, usage=$usage,seq=$seq]" }

    static void main(String[] args) {
        println "hello"
        def ClientTransaction ct = new ClientTransaction(id:0, ccy:1, client:"Jim", reason:"home", flag:false, usage:false, seq:2)
        ct.seq += 1;
        println ct;
        println "--- the end ---"
    } // end of main

} // end of class

