package hive

import hive.parser.HiveJobParser


HIVE_LOG_DIR = "/tmp/" + System.getProperty('user.name')
HADOOP_LOG_DIR = System.getenv()['HADOOP_HOME'] + '/logs'

println '~'*100
printf "%-19s | %10s | %-50s | \n", 'time', 'size', 'file'
println '~'*100
files = []
[HIVE_LOG_DIR].each { dirname -> new File(dirname).eachFileRecurse { files += it } }
files = files.sort({a,b -> a.lastModified() <=> b.lastModified()}) //.reverse()	

if (args.length == 0  || args[0] != '-all') {
	println files.last()
	files = [files.last()]
} else if (args.legnth > 0 && args[0] =~ /^job_.+/) {
	new File(HADOOP_LOG_DIR + '/' + args[0] + '_conf.xml').eachLine { line ->
		println line
		//tweets = new XmlSlurper().parseText(output)
		//tweets.status.each { tweet->
		//	println "${tweet.user.name}: ${tweet.text}"
		//}
	}
}


files.each { f ->
	if (!f.isDirectory() && f.getName() =~ /^hive_job_.*\.txt$/) {
		def line = new BufferedReader(new FileReader(f)).readLine()
		def m = line =~ /^SessionStart.*TIME="(.+?)".*/
		def sessionStartTime = Long.parseLong(m[0][1])
		
		println ''
		printf '%-19s | %10s | %-50s | \n', new java.text.SimpleDateFormat('yyyy-MM-dd HH:mm:ss').format(new Date(f.lastModified())), 
		                                   f.lastModified() - sessionStartTime, 
										   f.getName()
		new HiveJobParser().parse(f)
	}
}
