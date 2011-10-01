//def p = """all jps | grep Child""".execute()
def p = """all jps | grep -E '(Child|Tracker|Node)'""".execute()
p.waitFor()
def output = p.in.text
def jps = [:]
output.eachLine { line ->
	def m = line =~ /^(.*?):\s+(.*?)\s+(.*?)$/
	if (m.size() > 0 && m[0].size() > 3) {
		if (jps[m[0][1]] == null)
			jps[m[0][1]] = []

		println " ${m[0][1]} | ${m[0][3]} - ${m[0][2]} ".center(100, '~')

		println "ssh ${m[0][3]} \". ~/.bash_profile; jstack ${m[0][2]}\" 2>&1"
		def pp = "ssh ${m[0][3]} \". ~/.bash_profile; jstack ${m[0][2]}\" 2>&1".execute()
		//def pp = "ssh ${m[0][1]} \". ~/.bash_profile; jps\" 2>&1".execute()
		//def pp = "jstack ${m[0][2]}".execute()
		pp.waitFor()
		println pp.in.text
		
		//def mm = (pp.in.text =~ /(?m)"main"(.+?)^$/)
		//def mm = java.util.regex.Pattern.compile(/(?m)"main"(.+?)^$/, java.util.regex.Pattern.MULTILINE | java.util.regex.Pattern.DOTALL).matcher(pp.in.text)

		//if (mm.size() > 0)
		//	println mm[0][0]
	}
}
