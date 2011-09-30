package hive

import hive.parser.HiveJobParser
import config.Config
import config.HadoopJobConfig


verbose = false


println '~'*100
printf "%-19s | %10s | %-50s | \n", 'time', 'size', 'file'
println '~'*100
files = []
[Config.HIVE_LOG_DIR].each { dirname -> new File(dirname).eachFileMatch(~/^hive_job_log.+/) { files += it } }
files = files.sort({a,b -> a.lastModified() <=> b.lastModified()}) //.reverse()	

if (args.length == 0  || args[0] != '-all') {
	println '>>> ' + files.last()
	files.each { f ->
		if (f.lastModified() > System.currentTimeMillis() - 1000*60*60*24)
			files = [f]
	}
} 

if (args.length > 0  && args[0] == '-v') {
	verbose = true;
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
		if (k != 'hive.query.string' && k != 'mapred.job.name')
		printf "%60s : %s\n", v, k
	}
	
	println ' mapred.job.name '.center(100, '~')
	println params['mapred.job.name']
	
	println ' hive.query.string '.center(100, '~')
	println params['hive.query.string']
	println '~' * 100
	
	
	
	def p = "hive -e 'explain ${params['hive.query.string']};' 2>&1".execute()
	p.waitFor()
	def output = p.in.text
	println ' plan '.center(100, '~')
	println "hive -e 'explain ${params['hive.query.string']};' 2>&1"
	println output
	println '~' * 100
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
		new HiveJobParser().parse(f, verbose)
	}
}
