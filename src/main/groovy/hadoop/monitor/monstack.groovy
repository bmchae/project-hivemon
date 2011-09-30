def p = """all jps | grep Child""".execute()
p.waitFor()
def output = p.in.text
def jps = [:]
output.eachLine { line ->
	def m = line =~ /^(.*?):\s+(.*?)\s+(.*?)$/
	if (m.size() > 0 && m[0].size() > 3) {
		if (jps[m[0][1]] == null)
			jps[m[0][1]] = []
		def p = "ssh nexr@${m[0][1]} jstack ${m[0][2]}".execute()
		p.waitFor()
		println p.in.text
	}
}
