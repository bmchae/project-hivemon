import groovy.lang.Binding
import groovy.util.GroovyScriptEngine

class HivemonCli {   
    String osuser = System.getProperty('user.name')
    String  dir   
    boolean debug
   
    static void main(args) {
        def hivemonCli = new HivemonCli()   
   
        def cli = new CliBuilder(usage: 'hivemon [option]')
        
        /*   
        cli.h(longOpt: 'help'   ,'usage information' ,required: false          )   
        cli.d(longOpt: 'debug'  ,'enable debugging'  ,required: false          )  
        cli.l(longOpt: 'loc'    ,'go to directory'   ,required: true   ,args: 1)   
        */
       
        cli.with {
                h longOpt: 'help'               ,'usage information'    ,required: false    
                e longOpt: 'excelcheck'         ,'ExcelCheck'           ,required: false 
                i longOpt: 'item'               ,'Item Loading'         ,required: false, args: 1 
                r longOpt: 'itemclean'          ,'Item Clean'           ,required: false 
                m longOpt: 'multi'              ,'loading'              ,required: false   
                x longOpt: 'multiclean'         ,'clean'                ,required: false   
                //v longOpt: 'valueset'         ,''                     ,required: false          
        }
         
        OptionAccessor opt = cli.parse(args)   

        /**
         * print usage if -h, --help, or no argument is given
         */ 
        if (!opt || opt.h) {  // { opt.arguments().isEmpty()) {   
            cli.usage()   
            return
        }
        
        /**
         * debug
         */
        if (opt.d) {   
            hivemonCli.debug = true   
        }
        
        /**
         * processing
         */         
        if (opt.e) { 
            println 'excel check ....'
            def gs = new GroovyShell()
	}
    }
}
