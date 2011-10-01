//def p = """all jps | grep Child""".execute()
def p = """all jps | grep TaskTracker""".execute()
p.waitFor()
def output = p.in.text
def jps = [:]
output.eachLine { line ->
	def m = line =~ /^(.*?):\s+(.*?)\s+(.*?)$/
	if (m.size() > 0 && m[0].size() > 3) {
		if (jps[m[0][1]] == null)
			jps[m[0][1]] = []
		//def pp = "ssh nexr@${m[0][1]} jstack ${m[0][2]}".execute()
		def pp = "jstack ${m[0][2]} ".execute()
		pp.waitFor()
		
		println " ${m[0][1]} - ${m[0][2]} ".center(100, '~')
		//def mm = (pp.in.text =~ /(?m)"main"(.+?)^$/)
		def mm = java.util.regex.Pattern.compile(/(?m)"main"(.+?)^$/, java.util.regex.Pattern.MULTILINE | java.util.regex.Pattern.DOTALL).matcher(pp.in.text)

		if (mm.size() > 0)
			println mm[0][0]
	}
}
