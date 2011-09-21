HIVE_LOG_DIR = "/tmp/" + System.getProperty('user.name')

println '~'*100
printf "%-19s | %10s | %-50s | \n", 'time', 'size', 'file'
println '~'*100
files = []
[HIVE_LOG_DIR].each { dirname -> new File(dirname).eachFileRecurse { files += it } }
files = files.sort({a,b -> a.lastModified() <=> b.lastModified()}) //.reverse()	

files.each { f ->
	//if (!f.isDirectory() && f.getPath() =~ /\[?[0-9]+.+sql$/) {
	if (!f.isDirectory() && f.getName() =~ /^hive_job_.*\.txt$/) {
		println ''
		printf '%-19s | %10s | %-50s | \n', new java.text.SimpleDateFormat('yyyy-MM-dd HH:mm:ss').format(new Date(f.lastModified())), 
		                                   f.size(), 
										   f.getName()
		parse(f)
	}
}


def parse(f) {
	def jobMap = [:]
	
	def task = null
		
	f.eachLine { line ->
		switch(line) {
			case ~/^QueryStart QUERY_STRING=.+/ :
				m = line =~ /^QueryStart QUERY_STRING="(.+)"[ ]+QUERY_ID="(.+)"[ ]+TIME="(.+)"/
				printf '         * %s | %s | %s\n', new java.text.SimpleDateFormat('HH:mm:ss').format(Long.parseLong(m[0][3])),
													md5(m[0][1]),
				                                    m[0][1].trim().substring(0, Math.min(50, m[0][1].trim().size()))
			    break
			default:
			    break
		}
	}
}

def md5(String plain) {
	java.security.MessageDigest digest = java.security.MessageDigest.getInstance("MD5")
	digest.update(plain.bytes)
	BigInteger big = new BigInteger(1,digest.digest())
	return big.toString(16).padLeft(32,"0")
}