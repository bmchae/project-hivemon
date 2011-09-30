def p = """all jps""".execute()
p.waitFor()
def output = p.in.text
def jps = [:]
output.eachLine { line ->
	def m = line =~ /^(.*?):\s+(.*?)\s+(.*?)$/
	if (m.size() > 0 && m[0].size() > 3) {
		if (jps[m[0][1]] == null)
			jps[m[0][1]] = []
		switch (m[0][3]) {
	    case 'GroovyStarter' :
	    case 'Jps' :
	    case '' :
			break
	    case 'Child' :
			jps[m[0][1]] << '<' + m[0][2] + '>'
		    break
		default :
		    jps[m[0][1]] << m[0][3]
			break
		}
	}
}

jps.sort({it.key}).each { k, v ->
	printf '%s : %s\n', k, v.sort()
}
