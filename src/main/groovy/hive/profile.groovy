package hive

import hive.parser.HiveJobParser


HIVE_LOG_DIR = "/tmp/" + System.getProperty('user.name')

println '~'*100
printf "%-19s | %10s | %-50s | \n", 'time', 'size', 'file'
println '~'*100
files = []
[HIVE_LOG_DIR].each { dirname -> new File(dirname).eachFileRecurse { files += it } }
files = files.sort({a,b -> a.lastModified() <=> b.lastModified()}) //.reverse()	

files.each { f ->
	if (!f.isDirectory() && f.getName() =~ /^hive_job_.*\.txt$/) {
		println ''
		printf '%-19s | %10s | %-50s | \n', new java.text.SimpleDateFormat('yyyy-MM-dd HH:mm:ss').format(new Date(f.lastModified())), 
		                                   f.size(), 
										   f.getName()
		new HiveJobParser().parse(f)
	}
}
