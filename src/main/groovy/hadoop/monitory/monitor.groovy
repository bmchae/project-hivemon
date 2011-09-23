def output = 'all jps'.execute().text

def jps = [:]
output.eachLine { line ->
	def m = line =~ /^(.*?):\s+(.*?)\s+(.*?)$/
	if (m.size() > 0 && m[0].size() > 3) {
		if (jps[m[0][1]] == null)
			jps[m[0][1]] = []
		if (jps[m[0][1]] == /(GroovyStarter|Jps)/)
			return
		jps[m[0][1]] << (m[0][3] == '' ? 'X' : m[0][3])
	}
}

jps.sort({it.key}).each { k, v ->
	printf '%s : %s\n', k, v.sort()
}