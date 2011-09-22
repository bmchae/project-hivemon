package hive

import hive.parser.HiveJobParser
import config.Config
import config.HadoopJobConfig


println '~'*100
printf "%-19s | %10s | %-50s | \n", 'time', 'size', 'file'
println '~'*100
files = []
[Config.HIVE_LOG_DIR].each { dirname -> new File(dirname).eachFileMatch(~/^hive_job_log.+/) { files += it } }
files = files.sort({a,b -> a.lastModified() <=> b.lastModified()}) //.reverse()	

if (args.length == 0  || args[0] != '-all') {
	println '>>> ' + files.last()
	files = [files.last()]
} 

if (args.length > 0 && args[0] =~ /^job_.+/) {
	println '~'*100
	println 'parameters'
	println '~'*100

	params = [:]
	conf = new XmlSlurper().parseText(new File(Config.HADOOP_LOG_DIR + '/' + args[0] + '_conf.xml').text)
	conf.property.each { prop ->
		if (HadoopJobConfig.map[prop.name.text()] != null) {
			params[prop.name.text()] = prop.value.text()
		}
	}
	params.sort({it.key}).each { k, v ->
		printf "%60s : %s\n", v, k
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
